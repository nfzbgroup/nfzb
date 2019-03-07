package com.wonders.fzb.base.logging.dao;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.BaseDao;
import com.wonders.fzb.base.logging.beans.LogInfo;

public interface LoggingDao extends BaseDao {
	
	/**
	 * 持久化日志
	 * @param loginInfo 日志信息
	 */
	public void save(LogInfo loginInfo);
	
	public Page<LogInfo> findPageByHQL(int page,String hql);
}
