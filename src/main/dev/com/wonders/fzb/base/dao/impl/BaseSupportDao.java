package com.wonders.fzb.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import com.wonders.fzb.base.beans.BaseBean;
import com.wonders.fzb.base.beans.HqlParameter;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.base.kit.StringKit;

@Transactional
public class BaseSupportDao extends HibernateDaoSupport implements BaseDao {

	protected String injectedEntity;
	protected static Log logger = LogFactory.getLog(BaseSupportDao.class);

	@Resource(name = "sessionFactory") // 为父类HibernateDaoSupport注入sessionFactory的值
	// @Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private List<Session> sessionList = new LinkedList<Session>();

	/**
	 * 获取一个新的Session
	 */
	protected Session getSession() {
		// if(sessionList.size() > 0){
		// for(Session session : sessionList){
		// if(session.isOpen()){
		// session.flush();
		// session.close();
		// }
		// }
		// sessionList.clear();
		// }
		Session session = getSessionFactory().getCurrentSession();
		// sessionList.add(session);
		return session;
	}

	public Object[] formWhereClause(Map<String, Object> condMap, String tableAlias) {
		String whereClause = "";
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		if (!condMap.isEmpty()) {
			for (Iterator<String> i = condMap.keySet().iterator(); i.hasNext();) {
				whereClause += " and ";
				String paramName = i.next();
				Object paramValue = condMap.get(paramName);
				String pn = paramName.replace(".", "_");
				if (paramName.startsWith("clause:")) {
					// 外部传入子句，用于多表关联查询等
					String clause = "(" + paramName.substring(7) + ")";
					whereClause += clause;
					if (clause.contains(":")) {
						if (clause.indexOf(":") == clause.lastIndexOf(":")) {// 一个参数

							clause = clause.substring(clause.indexOf(":") + 1);
							String paName = clause.substring(0, clause.indexOf(" "));
							args.add(new HqlParameter(paName, paramValue));
						} else {// 多个参数
							@SuppressWarnings("unchecked")
							List<Object> paValueList = (List<Object>) paramValue;
							int count = 0;
							do {
								clause = clause.substring(clause.indexOf(":") + 1);
								String paName = clause.substring(0, clause.indexOf(" "));
								args.add(new HqlParameter(paName, paValueList.get(count++)));
							} while (clause.contains(":"));
						}
					}
				} else if (paramName.endsWith("NotLike")) {
					// 模糊查询的字段，字段名后加“NotLike”，则添加not like子句
					whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 7) + " not like :" + pn;
					args.add(new HqlParameter(pn, "%" + paramValue + "%"));
				} else if (paramName.endsWith("Like")) {
					// 模糊查询的字段，字段名后加“Like”，则添加like子句
					whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 4) + " like :" + pn;
					args.add(new HqlParameter(pn, "%" + paramValue + "%"));
				} else if (paramName.endsWith("Ge")) {
					// 大于等于的字段，字段名后加“Ge”，则添加>=子句
					whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 2) + " >= :" + pn;
					args.add(new HqlParameter(pn, paramValue));
				} else if (paramName.endsWith("Le")) {
					// 小于等于的字段，字段名后加“Le”，则添加<=子句
					whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 2) + " <= :" + pn;
					args.add(new HqlParameter(pn, paramValue));
				} else if (paramName.endsWith("Gt")) {
					// 大于的字段，字段名后加“Gt”，则添加>子句
					whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 2) + " > :" + pn;
					args.add(new HqlParameter(pn, paramValue));
				} else if (paramName.endsWith("Lt")) {
					// 小于的字段，字段名后加“Lt”，则添加<子句
					whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 2) + " < :" + pn;
					args.add(new HqlParameter(pn, paramValue));
				} else if (paramName.endsWith("NotEq")) {
					// 不等于的字段，字段名后加“NotEq”，则添加<>子句
					whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 5) + " <> :" + pn;
					args.add(new HqlParameter(pn, paramValue));
				} else if (paramName.endsWith("IsNull")) {
					// 为空的字段，字段名后加“IsNull”，则添加is null子句
					whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 6) + " is null";
				} else if (paramName.endsWith("IsNotNull")) {
					// 不为空的字段，字段名后加“IsNotNull”，则添加is not null子句
					whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 9) + " is not null";
				} else if (paramName.endsWith("NotInList")) {
					// 在集合中查的字段，字段名后加“NotInList”，则添加not in子句
					List valueList = (List) paramValue;
					if (valueList != null && valueList.size() > 0) {
						whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 9) + " not in (:" + pn + ")";
						args.add(new HqlParameter(pn, valueList));
					} else {
						whereClause += " 1=1 ";
					}
				} else if (paramName.endsWith("List")) {
					// 在集合中查的字段，字段名后加“List”，则添加in子句
					List valueList = (List) paramValue;
					if (valueList != null && valueList.size() > 0) {
						whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 4) + " in (:" + pn + ")";
						args.add(new HqlParameter(pn, valueList));
					} else {
						whereClause += " 1=1 ";
					}
				} else if (paramName.endsWith("Between")) {
					//
					List valueList = (List) paramValue;
					if (valueList != null && valueList.size() <= 2) {
						whereClause += tableAlias + "." + paramName.substring(0, paramName.length() - 7) + " between to_date('" + valueList.get(0) + "','yyyy-mm-dd hh24:mi:ss')" + " and to_date('" + valueList.get(1) + "','yyyy-mm-dd hh24:mi:ss')";
						// args.add(new HqlParameter(pn, valueList));
					} else {
						whereClause += " 1=1 ";
					}

				}

				else {
					// 精确查询的字段，添加=子句
					whereClause += tableAlias + "." + paramName + " = :" + pn;
					args.add(new HqlParameter(pn, paramValue));
				}
			}
		}

		Object[] ret = new Object[2];
		ret[0] = whereClause;
		ret[1] = args;
		return ret;
	}

	public String formOrderClause(Map<String, String> sortMap, String tableAlias, boolean addOrderByStr) {
		String orderClause = "";
		if (!sortMap.isEmpty()) {
			if (addOrderByStr)
				orderClause += " order by ";
			for (Iterator<String> i = sortMap.keySet().iterator(); i.hasNext();) {
				String paramName = i.next();
				if (paramName != null && paramName.startsWith("to_number.")) {
					orderClause += " to_number(" + tableAlias + "." + paramName.substring(10) + ") " + sortMap.get(paramName);
				} else {
					orderClause += " " + tableAlias + "." + paramName + " " + sortMap.get(paramName);
				}
				if (i.hasNext())
					orderClause += " , ";
			}
		}
		return orderClause;
	}

	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize, String className) throws FzbDaoException {
		if (condMap == null)
			condMap = new HashMap<String, Object>();
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		String hql = "select t from " + className + " t ";
		String countHql = "select count(*) from " + className + " t ";
		Object[] whereClause = this.formWhereClause(condMap, "t");
		String filterPart = " where  1=1 " + (String) whereClause[0];
		@SuppressWarnings("unchecked")
		List<HqlParameter> args = (List<HqlParameter>) whereClause[1];
		filterPart += this.formOrderClause(sortMap, "t", true);
		// return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
		// countHql + filterPart);

		// System.out.println("hql + filterPart-----" + hql + filterPart);
		// System.out.println("countHql + filterPart-----" + countHql + filterPart);

		Page retPage = findByHQLWithPage(hql + filterPart, args, pageNo, pageSize, countHql + filterPart);
		if (retPage.getTotalSize() == 0)
			retPage = new Page();
		return retPage;
	}

	public List findByList(Map<String, Object> condMap, Map<String, String> sortMap, String className) {
		if (condMap == null)
			condMap = new HashMap<String, Object>();
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		String hql = "select t from " + className + " t ";
		// String countHql = "select count(*) from " + className + " t ";
		Object[] whereClause = this.formWhereClause(condMap, "t");
		String filterPart = " where  1=1 " + (String) whereClause[0];
		@SuppressWarnings("unchecked")
		List<HqlParameter> args = (List<HqlParameter>) whereClause[1];
		filterPart += this.formOrderClause(sortMap, "t", true);
		List retList = findByHQL(hql + filterPart, args);
		return retList;
	}

	/**
	 * 根据sequence获得主键值，根据长度自动补零，再加前缀
	 * 
	 * @param sequenceName
	 *            sequenceName名称
	 * @param prefix
	 *            主键前缀
	 * @param seqLength
	 *            主键长度
	 * @return
	 */
	public String getId(String sequenceName, String prefix, int seqLength) {
		String sql = "select " + sequenceName + ".nextval from dual";
		Query query = getSession().createSQLQuery(sql);
		List list = query.list();
		BigDecimal bigDecimal = (BigDecimal) list.get(0);
		String seq = bigDecimal.toString();
		while (seq.length() < seqLength)
			seq = "0" + seq;
		return prefix + seq;
	}

	/**
	 * 复议应诉系统中已经有了DB.PK,还有一个业务PK，由{prefix}_0000{db.pk}组成。lj
	 * @param sequenceName
	 * @param prefix
	 * @param seqLength
	 * @return
	 */
	public String getPrefixWithSeqId(String seq, String prefix, int seqLength) {
		while (seq.length() < seqLength)
			seq = "0" + seq;
		return prefix + seq;
	}

	public int getTotal(String objName) {
		String hql = "SELECT count(*) from " + objName;
		Query query = getSession().createQuery(hql);
		return (Integer) (query.uniqueResult());
	}

	public void updateByCondMap(Map<String, Object> setMap, Map<String, Object> whereMap, String className) {
		String hql = "update " + className + " t ";
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		if (setMap != null && !setMap.isEmpty()) {
			boolean flag = true;
			for (Iterator<String> i = setMap.keySet().iterator(); i.hasNext();) {
				if (flag) {
					hql += " set ";
					flag = false;
				} else {
					hql += " , ";
				}
				String paramName = i.next();
				hql += "t." + paramName + " = :" + paramName;
				args.add(new HqlParameter(paramName, setMap.get(paramName)));
			}
		} else {
			return;
		}
		Object[] whereClause = this.formWhereClause(whereMap, "t");
		hql += " where  1=1 " + (String) whereClause[0];
		@SuppressWarnings("unchecked")
		List<HqlParameter> whereArgs = (List<HqlParameter>) whereClause[1];
		args.addAll(whereArgs);
		Query query = getSession().createQuery(hql);
		setQueryParameters(query, args);
		query.executeUpdate();
	}

	public void deleteByCondMap(Map<String, Object> condMap, String className) {
		String hql = "delete from " + className + " t ";
		Object[] whereClause = this.formWhereClause(condMap, "t");
		hql += " where  1=1 " + (String) whereClause[0];
		@SuppressWarnings("unchecked")
		List<HqlParameter> args = (List<HqlParameter>) whereClause[1];
		Query query = getSession().createQuery(hql);
		setQueryParameters(query, args);
		query.executeUpdate();
	}

	protected Class getEntityClass() {
		String entityName = null != this.injectedEntity ? this.injectedEntity : getEntityClassName();
		try {
			return Class.forName(entityName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected String getEntityClassName() {
		String daoClazzName = getClass().getName();
		String entityName = daoClazzName.replaceFirst("\\.dao\\.", ".beans.").replaceFirst("\\.impl\\.", ".").replaceFirst("DaoImpl", "");

		return entityName;
	}

	public void logMetadata() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(getEntityClass());
		String[] propertyNames = meta.getPropertyNames();
		Type[] propertyTypes = meta.getPropertyTypes();

		for (int i = 0; i < propertyNames.length; i++)
			if ((!propertyTypes[i].isEntityType()) && (!propertyTypes[i].isCollectionType()))
				logger.info("property name - " + propertyNames[i] + " & type - " + propertyTypes[i].getName());
	}

	protected Criteria createCriteria() {
		return getSession().createCriteria(getEntityClass());
	}

	public void saveOrUpdate(Object object) {
		if (getEntityClass().isInstance(object)) {
			getSession().saveOrUpdate(object);
		}
	}

	public void save(Object object) {
		// if (getEntityClass().isInstance(object)) {
		getSession().save(object);
		// }
	}

	public void update(Object object) {
		if (getEntityClass().isInstance(object)) {
			getSession().update(object);
		}
	}

	@SuppressWarnings("unchecked")
	public Object load(Serializable id) {
		Object result = null;
		try {
			result = getSession().get(getEntityClass(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Object loadWithLazy(Serializable id, String[] propertyNames) {
		Object object = load(id);
		if (object == null) {
			// throw new DAOException("Can not loadWithLazy " + getEntityClassName() + " with " + id.toString() + ": this object does not exist");
		}

		BeanWrapper beanWrapper = new BeanWrapperImpl(object);
		for (int i = 0; i < propertyNames.length; i++) {
			try {
				Object result = beanWrapper.getPropertyValue(propertyNames[i]);
				Iterator it;
				if ((Collection.class.isInstance(result)) && (result != null)) {
					Collection c = (Collection) result;
					for (it = c.iterator(); it.hasNext();)
						Hibernate.initialize(it.next());
				} else {
					Hibernate.initialize(result);
				}
			} catch (HibernateException e) {
				// throw new DAOException("Can not lazyload " + getEntityClassName() + " with " + id.toString(), e);
				e.printStackTrace();
			}
		}
		return object;
	}

	public List findByHQL(String qryHql, List args) {
		Session session = getSession();
		Query query = session.createQuery(qryHql);
		setQueryParameters(query, args);
		List result = query.list();
		 session.flush();
		// session.close();
		return result;
	}

	public List findByLike(Map filter) {
		Criteria criteria = getSession().createCriteria(getEntityClass());
		for (Iterator i = filter.keySet().iterator(); i.hasNext();) {
			String name = (String) i.next();
			if (filterProperty(name)) {
				criteria.add(Restrictions.like(name, (String) filter.get(name), MatchMode.ANYWHERE));
			} else {
				// throw new DAOException("Could not resolve property:" + name);
			}
		}
		return criteria.list();
	}

	public void remove(BaseBean object) throws FzbDaoException {
		if (getEntityClass().isInstance(object)) {
			object.setRemoved(1);
			getSession().update(object);
		} else {
			throw new FzbDaoException("此Dao不支持实体类：" + getEntityClassName());
		}
	}

	public void removeById(Serializable id) throws FzbDaoException {
		Object object = load(id);
		if (BaseBean.class.isInstance(object)) {
			remove((BaseBean) load(id));
		} else
			throw new FzbDaoException("实体类：" + getEntityClassName() + " 的id：" + id + " 不可删除.");
	}

	public void delete(Object object) {
		//if (getEntityClass() == null || getEntityClass().isInstance(object)) {
			getSession().delete(object);
		//}
	}

	public void deleteById(Serializable id) {
		delete(load(id));
	}

	public int countByHQL(String hql) {
		Query query = getSession().createQuery(hql);
		return ((Number) query.uniqueResult()).intValue();
	}

	public int countByHQL(String hql, List args) throws FzbDaoException {
		try {
			//由于sqlserver 不支持 count（*） order by 字段 这种写法 所以把order by 部分过滤掉
			Query query = getSession().createQuery(hql.substring(0,hql.indexOf("order by")));
			setQueryParameters(query, args);
			return ((Number) query.uniqueResult()).intValue();
		} catch (Exception ex) {
			throw new FzbDaoException(ex);
		}
	}

	public Page findByHQLWithPage(String qryHql, int pageNo, int pageSize, String countHql) throws FzbDaoException {
		return findByHQLWithPage(qryHql, null, pageNo, pageSize, countHql);
	}

	public Page findByHQLWithPage(String qryHql, List args, int pageNo, int pageSize, String countHql) throws FzbDaoException {
		return findByHQLWithPage(qryHql, args, pageNo, pageSize, countHql, false);
	}

	public Page findByHQLWithPage(String qryHql, List args, int pageNo, int pageSize, String countHql, boolean filterRemoved) throws FzbDaoException {
		if (qryHql == null) {
			throw new IllegalArgumentException("NULL is not a valid string");
		}
		if (countHql == null) {
			countHql = "select count(*) " + qryHql.substring(qryHql.indexOf("from "));

			if (filterRemoved) {
				countHql = filterRemovable(countHql);
			}
		}
		int totalCount = countByHQL(countHql, args);
		if (totalCount < 1) {
			return new Page();
		}

		if (filterRemoved) {
			qryHql = filterRemovable(qryHql);
		}
		Query query = getSession().createQuery(qryHql);
		setQueryParameters(query, args);

		if (pageNo < 1) {
			pageNo = 1;
		}
		int startIndex = Page.getStartOfAnyPage(pageNo, pageSize);
		List list = query.setFirstResult(startIndex - 1).setMaxResults(pageSize).list();
		int avaCount = list == null ? 0 : list.size();
		return new Page(startIndex, avaCount, totalCount, pageSize, list);
	}

	private boolean isExtBase() throws FzbDaoException {
		boolean result = false;
		try {
			result = BaseBean.class.isInstance(getEntityClass().newInstance());
		} catch (Exception e) {
			throw new FzbDaoException(e);
		}

		return result;
	}

	private void filterRemovable(Criteria criteria) throws FzbDaoException {
		if (isExtBase())
			criteria.add(Restrictions.eq("removed", new Integer(0)));
	}

	private String filterRemovable(String hql) throws FzbDaoException {
		String newHql = hql;
		if ((isExtBase()) && (newHql.indexOf(" removed") == -1)) {
			String trimHql = newHql.trim();
			if ((trimHql.endsWith("and")) || (trimHql.endsWith("where"))) {
				newHql = newHql.concat(" removed = 0");
			} else if (trimHql.indexOf("where") > -1) {
				newHql = newHql.concat(" and removed = 0");
			} else {
				newHql = newHql.concat(" where removed = 0");
			}
		}
		return newHql;
	}

	public Page findByCriteriaWithPage(Criteria criteria, int pageNo, int pageSize) {
		if (pageNo < 1) {
			pageNo = 1;
		}
		int startIndex = Page.getStartOfAnyPage(pageNo, pageSize);
		int totalCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		List list = criteria.setFirstResult(startIndex - 1).setMaxResults(pageSize).list();
		int avaCount = list == null ? 0 : list.size();

		return new Page(startIndex, avaCount, totalCount, pageSize, list);
	}

	public String getInjectedEntity() {
		return this.injectedEntity;
	}

	public void setInjectedEntity(String injectedEntity) {
		this.injectedEntity = injectedEntity;
	}

	protected void sortCriteria(Criteria criteria, Map sortMap) {
		Iterator i;
		if ((sortMap != null) && (!sortMap.isEmpty()))
			for (i = sortMap.keySet().iterator(); i.hasNext();) {
				String fieldName = i.next().toString();
				String orderType = sortMap.get(fieldName).toString();

				if (fieldName.indexOf('.') != -1) {
					String alias = StringKit.substringBefore(fieldName, ".");
					criteria.createAlias(alias, alias);
				}
				if ("asc".equalsIgnoreCase(orderType)) {
					criteria.addOrder(Order.asc(fieldName));
				} else
					criteria.addOrder(Order.desc(fieldName));
			}
	}

	private void setQueryParameters(Query query, List args) {
		if (args != null)
			for (int i = 0; i < args.size(); i++) {
				HqlParameter arg = (HqlParameter) args.get(i);
				String argName = arg.getName();
				Object argValue = arg.getValue();
				Type argType = arg.getType();

				if (argName == null) {
					if (argType == null) {
						query.setParameter(i, argValue);
					} else {
						query.setParameter(i, argValue, argType);
					}

				} else if (argType == null) {
					if (Collection.class.isInstance(argValue)) {
						query.setParameterList(argName, (Collection) argValue);
					} else {
						query.setParameter(argName, argValue);
					}

				} else if (Collection.class.isInstance(argValue)) {
					query.setParameterList(argName, (Collection) argValue, argType);
				} else
					query.setParameter(argName, argValue, argType);
			}
	}

	private boolean filterProperty(String keyValue) {
		String[] propertyParts = keyValue.split("\\.");
		try {
			Class clazz = getEntityClass();
			Field field = null;
			while (field == null) {
				try {
					field = clazz.getDeclaredField(propertyParts[0]);
				} catch (NoSuchFieldException e) {
					clazz = clazz.getSuperclass();
					if (clazz.equals(Object.class)) {
						throw e;
					}
				}
			}
			for (int i = 1; i < propertyParts.length; i++)
				field = field.getType().getDeclaredField(propertyParts[i]);
		} catch (Exception e) {
			logger.error("Could not resolve property:" + keyValue, e);
			return false;
		}
		return true;
	}
	
	/**
	 * 执行SQL查询语句
	 */
	protected List<Map> executeSqlQuery(String sql){
		Session session = getSession();
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("unchecked")
		List<Map> results = query.list();
		session.flush();

		return results;
	}
	
	/**
	 * 执行SQL查询语句，返回List<Map>
	 * @param sql 要执行的SQL语句
	 * @param pageSize 每页多少条
	 * @param pageNo 当前多少页
	 */
	protected List<Map> executeSqlQueryMap(String sql,int pageNo,int pageSize){
		Session session = getSession();
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		if(pageNo > 0){
			if(pageSize == 0)
				pageSize = 10;
			query.setFirstResult(Page.getStartOfAnyPage(pageNo, pageSize) - 1);
			query.setMaxResults(pageSize);
		}
		@SuppressWarnings("unchecked")
		List<Map> results = query.list();
		session.flush();
		
		return results;
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void bulkUpdate(String queryString) {
		getHibernateTemplate().bulkUpdate(queryString);
	}

	public void bulkUpdate(String hql, Object param) {
		getHibernateTemplate().bulkUpdate(hql, param);
	}

	public void bulkUpdate(String hql, Object[] params) {
		getHibernateTemplate().bulkUpdate(hql, params);
	}


	/**
	 * 执行SQL查询语句
	 * @param sql 要执行的SQL语句
	 */
	public List<Object[]> executeSqlQueryWithoutPage(String sql) {
		Session session = getSession();
		Query query = session.createSQLQuery(sql);

		List<Object[]> results = query.list();
		session.flush();

		return results;
	}

	/**
	 * 执行SQL查询语句
	 * @param sql 要执行的SQL语句
	 */
	public void executeSqlUpdate(String sql) {
		Session session = getSession();
		session.createQuery(sql).executeUpdate();
		session.flush();
	}
}
