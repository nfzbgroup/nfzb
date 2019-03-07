package com.wonders.fzb.base.logging.services;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.logging.beans.LogInfo;
import com.wonders.fzb.base.logging.beans.LoggingQueryBean;

/**
 * 日志记录服务类
 * @author ZSW
 */
public interface LoggingService {	
	/**
	 * 记录日志
	 */
	public void log(LogInfo info);
	
	/**
	 * 根据条件和页码查询
	 */
	public Page<LogInfo> findByPage(int pageNum,LoggingQueryBean lsc);
}
