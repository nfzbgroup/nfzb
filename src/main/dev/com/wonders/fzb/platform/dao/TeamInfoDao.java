package com.wonders.fzb.platform.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.platform.beans.TeamInfoOld;

/**
 * WFC_F_TEAM_INFO dao接口
 * @author scalffold
 */
public abstract interface TeamInfoDao extends BaseDao {
	
	public Page<TeamInfoOld> findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize);
	
	public List<TeamInfoOld> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<TeamInfoOld> findByUserId(String userId);
	
	public List<TeamInfoOld> findByTeamGroup(String teamGroup);
	
	public void setInjectedEntity(String injectedEntity) ;
}