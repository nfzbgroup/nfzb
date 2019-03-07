package com.wonders.fzb.platform.dao;

import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.platform.beans.AppInfo;

/**
 * WFC_CODE_INFO dao接口
 * @author scalffold
 */
public abstract interface AppInfoDao extends BaseDao {
	
	public void saveOrUpdate(AppInfo appInfo);
	
	public Page findByPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize);
	
	public List<AppInfo> findByList(Map<String, Object> condMap, Map<String, String> sortMap);


}