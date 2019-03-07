package com.wonders.fzb.platform.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.platform.beans.RoleInfo;

/**
 * ROLE_INFO dao接口
 * @author scalffold
 */
public abstract interface RoleInfoDao extends BaseDao {
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize);
	
	public List<RoleInfo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);
	
	public List<RoleInfo> findByOp(String opId);
	
	public List<RoleInfo> findByUserId(String userId);
	
	public List<RoleInfo> findByTeamId(String teamId);
	
}