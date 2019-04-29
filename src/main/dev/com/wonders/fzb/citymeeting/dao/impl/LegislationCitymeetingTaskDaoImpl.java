package com.wonders.fzb.citymeeting.dao.impl;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.citymeeting.beans.*;
import com.wonders.fzb.citymeeting.dao.*;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;

/**
 * LegislationCitymeetingTask dao实现
 * 
 * @author scalffold created by lj
 */
@SuppressWarnings("unchecked")
@Repository("legislationCitymeetingTaskDao")
public class LegislationCitymeetingTaskDaoImpl extends BaseSupportDao implements LegislationCitymeetingTaskDao {

	@Override
	public void save(Object object) {
		LegislationCitymeetingTask info = (LegislationCitymeetingTask) object;
		info.setStTaskId(super.getId("SEQ_LEGISLATION_CITYMEET_TASK", "CTK_", 16));
		super.save(info);
	}
	
	@Override
	public String saveObj(Object object) {
		LegislationCitymeetingTask info = (LegislationCitymeetingTask) object;
		String newId=super.getId("SEQ_LEGISLATION_CITYMEET_TASK", "CTK_", 16);
		info.setStTaskId(newId);
		super.save(info);
		return newId;
	}
	

	@Override
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException  {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("configName", CommonConst.ORDER_ASC);
		return super.findByPage(condMap, sortMap, pageNo, pageSize,"LegislationCitymeetingTask");
	}

	@Override
	public List<LegislationCitymeetingTask> findByList(Map<String, Object> condMap, Map<String, String> sortMap) {
		if (sortMap == null)
			sortMap = new LinkedHashMap<String, String>();
		//sortMap.put("appId", CommonConst.ORDER_ASC);
		return super.findByList(condMap, sortMap, "LegislationCitymeetingTask");
	}

	@Override
	public void saveOrUpdate(LegislationCitymeetingTask info) {
		super.saveOrUpdate(info);
	}
	
	@Override
	public List<LegislationCitymeetingTask> findByHQL(String hql){
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<LegislationCitymeetingTask> result = query.list();
		session.flush();
		return result;
	}

	
	/**
	 * 执行SQL查询语句
	 * @param sql 要执行的SQL语句
	 * @param pageSize 每页多少条
	 * @param pageNo 当前多少页
	 */
	private List<Object[]> executeSqlQuery(String sql, int pageNo, int pageSize) {
		Session session = getSession();
		Query query = session.createSQLQuery(sql);

		if (pageNo > 0) {
			if (pageSize == 0)
				pageSize = 10;
			query.setFirstResult(Page.getStartOfAnyPage(pageNo, pageSize) - 1);
			query.setMaxResults(pageSize);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.list();
		session.flush();

		return results;
	}
	
	private LinkedList<LegislationCitymeeting> packageDocInfoBean(List<Object[]> results) throws ParseException {
		LinkedList<LegislationCitymeeting> docInfos = new LinkedList<LegislationCitymeeting>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Object[] array : results) {
			LegislationCitymeeting docInfo = new LegislationCitymeeting();
			//d.st_doc_id,d.st_doc_name,d.st_node_name,d.st_node_id
			docInfo.setStTopicId(array[0].toString());
			docInfo.setStTopicName(array[1] == null ? "" : array[1].toString());
			docInfo.setStNodeName(array[2] == null ? "" : array[2].toString());
			docInfo.setStNodeId(array[3] == null ? "" : array[3].toString());
			docInfo.setDtCreateDate(array[4] == null ? null : dateFormat.parse(array[4].toString()));

			docInfo.setStUserName(array[5] == null ? "" : array[5].toString());
			docInfo.setStTopic(array[6] == null ? "" : array[6].toString());

			docInfos.add(docInfo);
		}
		return docInfos;
	}
	
	@Override
	public Page<LegislationCitymeeting> findTaskDocListByNodeId(String wheresql, int pageNo, int pageSize) throws ParseException {
		String baseSql = " FROM LEGISLATION_CITYMEETING d ";
		baseSql += " INNER JOIN LEGISLATION_CITYMEETING_TASK t ";
		baseSql += " ON d.st_topic_id = t.st_topic_id ";
		baseSql += wheresql;
		String propView = "SELECT d.st_topic_id,d.st_topic_name,d.st_node_name,t.st_node_id,d.DT_CREATE_DATE,d.ST_USER_NAME,d.ST_TOPIC";
		String totalView = "SELECT COUNT(1) ";

		List<LegislationCitymeeting> users = packageDocInfoBean(executeSqlQuery(propView + baseSql, pageNo, pageSize));
		int totalSize = ((BigDecimal) (Object) executeSqlQueryWithoutPage(totalView + baseSql).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<LegislationCitymeeting>(Page.getStartOfAnyPage(pageNo, pageSize), users.size(), totalSize, pageSize, users);
	}
}
