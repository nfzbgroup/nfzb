package com.wonders.fzb.platform.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.platform.beans.ResRightInfo;

/**
 * RES_RIGHT_INFO dao接口
 * @author scalffold
 */
public abstract interface ResRightInfoDao extends BaseDao {
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize);
	
	public List<ResRightInfo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);

	public List<ResRightInfo> findByUserIdAndResIdAndOpId(String userId, String resId, String opId);

	public List<ResRightInfo> findByTeamIdAndResIdAndOpId(String teamId, String resId, String opId);
}