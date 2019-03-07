package com.wonders.fzb.legislate.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.beans.vo.UserInfoBean;
import com.wonders.fzb.legislate.beans.vo.TeamInfoBean;
import com.wonders.fzb.legislate.dao.CommonDao;

@Repository("commonDao")
public class CommonDaoImpl extends BaseSupportDao implements CommonDao {

	
	/**
	 * 执行HQL查询语句
	 */
	private List<Object[]> executeHqlQuery(String hql){
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
	private List<Object[]> executeSqlQuery(String sql,int pageNo,int pageSize){
		Session session = getSession();
		Query query = session.createSQLQuery(sql);
		
		if(pageNo > 0){
			if(pageSize == 0)
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
	private List<TeamInfoBean> packageTeamInfo(List<Object[]> results){
		/* 查询一次数据库会出现多次关联多条数据,以下是通过Map多对多逻辑关联和去重算法 */
		Map<TeamInfo,LinkedList<MOR>> resultArray = new LinkedHashMap<TeamInfo, LinkedList<MOR>>();//改为LinkedHashMap以保证次序不乱 liujun
		List<TeamInfoBean> teams = new LinkedList<TeamInfoBean>();
		for(Object[] result : results){
			TeamInfo item = (TeamInfo)result[0];
			LinkedList<MOR> mor = new LinkedList<MOR>();
			if(resultArray.get(item) != null)
				mor = resultArray.get(item);
			mor.add((MOR)result[1]);
			
			resultArray.put(item, mor);
		}
		
		for(TeamInfo team : resultArray.keySet()){
			//team.setMor(resultArray.get(team).get(0));
			TeamInfoBean a = new  TeamInfoBean();
			a.setUnitName(team.getUnitName());
			MOR mor = resultArray.get(team).get(0);
			a.setOrgId(mor.getTeamCid());
			a.setOrgPid(mor.getTeamPid());
			a.setMorId(mor.getId());
			teams.add(a);
		}
		
		return teams;
	}

	@Override
	public List<TeamInfoBean> findTeamInfoList(String moduleId, String orgType) {
		/* 为了避免hibernate many to many 查询出现的N+1的情况 使用HQL来做left join 只开销一次数据库*/
		String hql = "SELECT team,mor FROM TeamInfo team LEFT JOIN MOR mor ON ";
		hql += " mor.teamCid = team.id ";
		hql += " WHERE mor.orgType = '"+orgType+"' AND mor.moduleId = '"+moduleId+"'  order by mor.teamPid ,mor.sort asc";
		
		List<Object[]> results =  executeHqlQuery(hql);
		
		return packageTeamInfo(results);
	}

	@Override
	public List<UserInfoBean> findUserInfoListForOrgId(String moduleId,
			String orgId) {
		/* 为了避免hibernate many to many 查询出现的N+1的情况 使用HQL来做left join 只开销一次数据库*/
		String hql = "SELECT userInfo FROM UserInfo userInfo LEFT JOIN  OUR our ON ";
		hql += " our.userId = userInfo.userId ";
		hql += " WHERE our.morId = '"+orgId+"' and( our.type like '%主任%' or our.type like '%处长%')" ;
		
		Session session = getSession();
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<UserInfo> results = query.list();
		session.flush();
		List<UserInfoBean> userBeans = new ArrayList<UserInfoBean>();
		for(UserInfo result : results){
			UserInfoBean userInfoBean = new UserInfoBean();
			userInfoBean.setUserId(result.getUserId());
			userInfoBean.setName(result.getName());
			userBeans.add(userInfoBean);
		}
		return userBeans;
	}
	
}