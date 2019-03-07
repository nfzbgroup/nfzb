package com.wonders.fzb.platform.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.platform.beans.RightOpRangeInfo;

/**
 * RIGHT_OP_RANGE_INFO dao接口
 * @author scalffold
 */
public abstract interface RightOpRangeInfoDao extends BaseDao {
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize);
	
	public List<RightOpRangeInfo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);
}