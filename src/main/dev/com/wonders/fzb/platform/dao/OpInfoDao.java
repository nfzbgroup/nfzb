package com.wonders.fzb.platform.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.platform.beans.OpInfo;
/**
 * OP_INFO dao接口
 * @author scalffold
 */
public abstract interface OpInfoDao extends BaseDao {
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize);
	
	public List<OpInfo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);
	
	public List<OpInfo> findByUserId(String userId);
	
	public List<OpInfo> findByTeamId(String teamId);
	
	public List<OpInfo> findByRoleId(String roleId);
}