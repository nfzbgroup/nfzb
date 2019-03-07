package com.wonders.fzb.framework.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.OUR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;
import com.wonders.fzb.framework.dao.PlatformDao;

@Repository("platformDao")
public class PlatformDaoImpl extends BaseSupportDao implements PlatformDao {

	@Override
	public String addTeamInfo(TeamInfo teaminfo) {
		super.save(teaminfo);
		return teaminfo.getId();
	}

	@Override
	public String updateTeamInfo(TeamInfo teaminfo) {
		super.update(teaminfo);
		return teaminfo.getId();
	}

	@Override
	public void updateMor(MOR mor) {
		// super.saveOrUpdate(mor);
		super.setInjectedEntity("com.wonders.fzb.framework.beans.MOR");
		super.update(mor);
		super.setInjectedEntity(null);
	}

	@Override
	public void deleteTeamInfo(TeamInfo teaminfo) {
		super.delete(teaminfo);
	}

	@Override
	public TeamInfo findTeamInfoByTeamId(String moduleId, String teamId) {
		TeamInfo result = (TeamInfo) getSession().get(TeamInfo.class, teamId);

		Session session = getSession();
		Query query = session.createQuery("FROM MOR mor WHERE mor.moduleId = '" + moduleId + "' AND mor.teamCid = '" + teamId + "'");
		@SuppressWarnings("unchecked")
		List<MOR> mors = query.list();
		session.flush();

		result.setMor(mors);

		return result;
	}

	@Override
	public Page<TeamInfo> findTeamInfoPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "TeamInfo");
	}

	@Override
	public List<TeamInfo> findTeamInfoList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return super.findByList(condMap, sortMap, "TeamInfo");
	}

	@Override
	public List<MOR> findMorList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return super.findByList(condMap, sortMap, "MOR");
	}

	@Override
	public List<TeamInfo> findTeamInfoSubList(String moduleId, String pid) {
		/* 为了避免hibernate many to many 查询出现的N+1的情况 使用HQL来做left join 只开销一次数据库 */
		String hql = "SELECT team,mor FROM TeamInfo team LEFT JOIN MOR mor ON ";
		hql += " mor.teamCid = team.id ";
		hql += " WHERE mor.teamPid = '" + pid + "' AND mor.moduleId = '" + moduleId + "' order by team.unitType, team.id";

		List<Object[]> results = executeHqlQuery(hql);

		return packageTeamInfo(results);
	}

	@Override
	public List<MOR> findMorSubList(String moduleId, String pid) {
		Map<String, Object> condMap = new HashMap<String, Object>();
		LinkedHashMap<String, String> sortMap = new LinkedHashMap<String, String>();
		condMap.put("moduleId", moduleId);
		condMap.put("teamPid", pid);
		sortMap.put("sort", "ASC");
		return super.findByList(condMap, sortMap, "MOR");
	}

	@Override
	public List<MOR> findMorTypeList(String moduleId, String orgType) {
		Map<String, Object> condMap = new HashMap<String, Object>();
		LinkedHashMap<String, String> sortMap = new LinkedHashMap<String, String>();
		condMap.put("moduleId", moduleId);
		if (orgType != null && !"".equals(orgType)) {
			condMap.put("orgType", orgType);
		}
		condMap.put("teamPid" + "IsNotNull", ""); // 去除跟节点
		sortMap.put("sort", "ASC");
		return super.findByList(condMap, sortMap, "MOR");
	}

	@Override
	public List<TeamInfo> findTeamInfoSuperList(String moduleId, String cid) {
		/* 为了避免hibernate many to many 查询出现的N+1的情况 使用HQL来做left join 只开销一次数据库 */
		String hql = "SELECT team,mor FROM TeamInfo team ";
		hql += " LEFT JOIN MOR mor ON  mor.teamCid = team.id ";
		// hql += " LEFT JOIN OUR our ON mor.id = our.morId ";
		// hql += " LEFT JOIN UserInfo user ON our.userId = user.userId ";
		hql += " WHERE mor.teamCid = '" + cid + "' AND mor.moduleId = '" + moduleId + "'";

		List<Object[]> results = executeHqlQuery(hql);

		return packageTeamInfo(results);
	}

	@Override
	public List<TeamInfo> findTeamInfoSuperListRi(String moduleId, String cid) {
		/* 为了避免hibernate many to many 查询出现的N+1的情况 使用HQL来做left join 只开销一次数据库 */
		String hql = "SELECT team,mor FROM TeamInfo team ";
		hql += " LEFT JOIN MOR mor ON  mor.teamPid = team.id ";
		// hql += " LEFT JOIN OUR our ON mor.id = our.morId ";
		// hql += " LEFT JOIN UserInfo user ON our.userId = user.userId ";
		hql += " WHERE mor.teamCid = '" + cid + "' AND mor.moduleId = '" + moduleId + "'";

		List<Object[]> results = executeHqlQuery(hql);

		return packageTeamInfo(results);
	}

	@Override
	public List<TeamInfo> findTeamInfoByUserId(String moduleId, String userId) {
		String hql = "SELECT team,mor FROM OUR our LEFT JOIN MOR mor ON our.morId = mor.id LEFT JOIN TeamInfo team ON mor.teamCid = team.id WHERE mor.moduleId = '" + moduleId + "' AND ";
		hql += " our.userId = '" + userId + "'";

		List<Object[]> results = executeHqlQuery(hql);

		return packageTeamInfo(results);
	}

	@Override
	public String addUser(UserInfo userInfo) {
		userInfo.setAccountStatus("y");
		super.save(userInfo);
		return userInfo.getUserId();
	}

	@Override
	public String addMor(MOR mor) {
		super.save(mor);
		return mor.getId();
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		super.setInjectedEntity("com.wonders.fzb.framework.beans.UserInfo");
		super.update(userInfo);
		super.setInjectedEntity(null);
	}

	@Override
	public List<UserInfo> findUsersByTeam(String moduleId, String teamId) {
		String hql = "SELECT user FROM MOR mor ";
		hql += " LEFT JOIN  OUR our ON our.morId = mor.id ";
		hql += " LEFT JOIN UserInfo user ON user.userId = our.userId ";
		hql += " WHERE mor.teamCid = '" + teamId + "' AND mor.moduleId = '" + moduleId + "'";

		Session session = getSession();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<UserInfo> results = query.list();
		session.flush();

		return results;
	}

	@Override
	public List<UserInfo> findUsersByModule(String moduleId) {
		String hql = "SELECT user FROM MOR mor ";
		hql += " LEFT JOIN  OUR our ON our.morId = mor.id ";
		hql += " LEFT JOIN UserInfo user ON user.userId = our.userId ";
		hql += " WHERE mor.moduleId = '" + moduleId + "'";

		Session session = getSession();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<UserInfo> results = query.list();
		session.flush();

		return results;
	}

	@Override
	public Page<UserInfoBean> findUserListView(String sql, int pageNo, int pageSize) {
		String baseSql = " FROM WFC_C_USER_INFO usr ";
		baseSql += " LEFT JOIN WFC_C_OU_RELATE ou ";
		baseSql += " ON ou.user_id = usr.user_id ";
		baseSql += " LEFT JOIN WFC_C_MO_RELATE mo ";
		baseSql += " ON mo.id = ou.org_rela_id ";
		baseSql += " LEFT JOIN WFC_C_TEAM_INFO team ";
		baseSql += " ON team.id = mo.org_cid ";
		baseSql += " LEFT JOIN WFC_C_SYS_MODULE sm ";
		baseSql += " ON sm.uuid = mo.module_id ";
		baseSql += sql;
		String propView = "SELECT ";
		propView += "  usr.user_id AS userId,";
		propView += "  team.id as teamId,";
		propView += "  sm.uuid as moduleId,";
		propView += "  mo.id as morId,";
		propView += "  ou.id as ourId,";
		propView += "  usr.name as userName,";
		propView += "  team.unitname as teamName,";
		propView += "  sm.name as moduleName,";
		propView += "  usr.account as account,";
		propView += "  usr.password as password,";
		propView += "  usr.salt as salt,";
		propView += "  usr.abbr as abbr,";
		propView += "  usr.phone as phone,";
		propView += "  usr.accountstatus as accountstatus ";
		String totalView = "SELECT COUNT(1) ";

		List<UserInfoBean> users = packageUserInfoBean(executeSqlQuery(propView + baseSql, pageNo, pageSize));
		int totalSize = ((BigDecimal) (Object) executeSqlQuery(totalView + baseSql, pageNo, pageSize).get(0)).intValue();
		if (pageSize == 0)
			pageSize = totalSize;
		return new Page<UserInfoBean>(Page.getStartOfAnyPage(pageNo, pageSize), users.size(), totalSize, pageSize, users);
	}

	@Override
	public List<MOR> findMorListByUserId(String moduleId, String userId) {
		String hql = "SELECT mor,our FROM OUR our LEFT JOIN MOR mor ON our.morId = mor.id  WHERE mor.moduleId = '" + moduleId + "' AND ";
		hql += " our.userId = '" + userId + "'";
		List<Object[]> results = executeHqlQuery(hql);
		return packageMorInfo(results);
	}

	@Override
	public List<OUR> findOurListByUserId(String moduleId, String userId) {
		String hql = "SELECT mor,our FROM OUR our LEFT JOIN MOR mor ON our.morId = mor.id  WHERE mor.moduleId = '" + moduleId + "' AND ";
		hql += " our.userId = '" + userId + "'";
		List<Object[]> results = executeHqlQuery(hql);
		return packageOurInfo(results);
	}

	/**
	 * 根据返回结果封装成Mor
	 */
	private List<MOR> packageMorInfo(List<Object[]> results) {
		List<MOR> mors = new LinkedList<MOR>();
		for (Object[] result : results) {
			mors.add((MOR) result[0]);
		}
		return mors;
	}

	/**
	 * 根据返回结果封装成Mor
	 */
	private List<OUR> packageOurInfo(List<Object[]> results) {
		List<OUR> ours = new LinkedList<OUR>();
		for (Object[] result : results) {
			ours.add((OUR) result[1]);
		}
		return ours;
	}

	/**
	 * 执行HQL查询语句
	 */
	private List<Object[]> executeHqlQuery(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.list();
		session.flush();

		return results;
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

	/**
	 * 根据返回结果封装成TeamInfo
	 */
	private List<TeamInfo> packageTeamInfo(List<Object[]> results) {
		/* 查询一次数据库会出现多次关联多条数据,以下是通过Map多对多逻辑关联和去重算法 */
		Map<TeamInfo, LinkedList<MOR>> resultArray = new LinkedHashMap<TeamInfo, LinkedList<MOR>>();// 改为LinkedHashMap以保证次序不乱 liujun
		List<TeamInfo> teams = new LinkedList<TeamInfo>();
		for (Object[] result : results) {
			TeamInfo item = (TeamInfo) result[0];
			LinkedList<MOR> mor = new LinkedList<MOR>();
			if (resultArray.get(item) != null)
				mor = resultArray.get(item);
			mor.add((MOR) result[1]);

			resultArray.put(item, mor);
		}

		for (TeamInfo team : resultArray.keySet()) {
			team.setMor(resultArray.get(team));
			teams.add(team);
		}

		return teams;
	}

	private LinkedList<UserInfoBean> packageUserInfoBean(List<Object[]> results) {
		LinkedList<UserInfoBean> users = new LinkedList<UserInfoBean>();
		for (Object[] array : results) {
			UserInfoBean bean = new UserInfoBean();
			bean.setUserId(array[0].toString());
			bean.setTeamId(array[1] == null ? "" : array[1].toString());
			bean.setModuleId(array[2] == null ? "" : array[2].toString());
			bean.setMorId(array[3] == null ? "" : array[3].toString());
			bean.setOurId(array[4] == null ? "" : array[4].toString());
			bean.setName(array[5].toString());
			bean.setTeamName(array[6] == null ? "暂无组织" : array[6].toString());
			bean.setModuleName(array[7] == null ? "暂无系统" : array[7].toString());
			bean.setAccount(array[8].toString());
			bean.setPassword(array[9].toString());
			bean.setSalt(array[10].toString());
			bean.setAbbr(array[11] == null ? "暂无" : array[11].toString());
			bean.setPhone(array[12] == null ? "暂无" : array[12].toString());
			bean.setAccountStatus(array[13].toString());

			users.add(bean);
		}
		return users;
	}

	@Override
	public UserInfo findUserInfoByUserId(String userId) {
		String hql = "FROM UserInfo u WHERE u.userId = :userId";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setString("userId", userId);
		List<UserInfo> userlist = query.list();
		if (userlist.size() > 0) {
			return userlist.get(0);
		}
		return null;
	}

	@Override
	public UserInfo findUserInfoByAccount(String account) {
		String hql = "FROM UserInfo u WHERE u.account = :account";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setString("account", account);
		List<UserInfo> userlist = query.list();
		if (userlist.size() > 0) {
			return userlist.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<UserInfo> findUserInfoPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo, int pageSize) throws FzbDaoException {
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "UserInfo");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findUserInfoList(Map<String, Object> condMap, Map<String, String> sortMap) {
		return super.findByList(condMap, sortMap, "UserInfo");
	}

	@Override
	public List<MOR> findMorListByUnitId(String moduleId, String unitId) {
		String hql = "SELECT mor FROM  MOR mor WHERE mor.moduleId = '" + moduleId + "' AND  mor.teamCid = '" + unitId + "'";
		List<MOR> results = executeHqlQueryMor(hql);
		return results;
	}

	@Override
	public List<OUR> findAllOur(String condtion) {
		String hql = "SELECT our FROM  OUR our WHERE our.userId like '" + condtion + "%'";
		List<OUR> results = executeHqlQueryOur(hql);
		return results;
	}

	@Override
	public List<OUR> findOurByMorId(String condtion) {
		String hql = "SELECT our FROM  OUR our WHERE our.morId like '" + condtion + "%'";
		List<OUR> results = executeHqlQueryOur(hql);
		return results;
	}

	private List<MOR> executeHqlQueryMor(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<MOR> results = query.list();
		session.flush();

		return results;

	}

	private List<OUR> executeHqlQueryOur(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<OUR> results = query.list();
		session.flush();

		return results;

	}

	@Override
	public UserInfo findUserInfoByAccount(String account, String salt) {
		String hql = "FROM UserInfo u WHERE u.account = :account and u.salt = :salt";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setString("account", account);
		query.setString("salt", salt);
		List<UserInfo> userlist = query.list();
		if (userlist.size() > 0) {
			return userlist.get(0);
		}
		return null;
	}

	// @Override
	// public List<UserInfo> findUserInfoTest(String string) {
	// Session session = getSession();
	// Query query = session.createQuery("select t from UserInfo t where t.userId = '" + string + "'");
	// @SuppressWarnings("unchecked")
	// List<UserInfo> userInfos = query.list();
	// session.flush();
	// return userInfos;
	// }

	@Override
	public List<UserInfo> findAll() {
		String hql = "SELECT user FROM MOR mor ";
		hql += " LEFT JOIN  OUR our ON our.morId = mor.id ";
		hql += " LEFT JOIN UserInfo user ON user.userId = our.userId ";
		hql += " ORDER BY user.name DESC ";
		Session session = getSession();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<UserInfo> results = query.list();
		session.flush();

		return results;
	}

	@Override
	public String addPublicityMor(MOR mor) {
		mor.setId(super.getId("seq_publicity_mor", "MO_RE_BEAHIVOR_", 4));
		super.save(mor);
		return mor.getId();
	}

}