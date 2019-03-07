package com.wonders.fzb.base.logging.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.logging.beans.LogInfo;
import com.wonders.fzb.base.logging.beans.LoggingQueryBean;
import com.wonders.fzb.base.logging.dao.LoggingDao;
import com.wonders.fzb.base.logging.services.LoggingService;

@Transactional(readOnly=false)
@Service("loggingService")
public class LoggingServiceImpl implements LoggingService {

	@Autowired
	private LoggingDao loggingDao;

	@Override
	public void log(LogInfo info) {
		loggingDao.save(info);
	}

	@Override
	public Page<LogInfo> findByPage(int page,LoggingQueryBean lsc) {
		StringBuffer hql = new StringBuffer("");
		hql.append(lsc.analysis());
		hql.append("ORDER BY l.time DESC ");
		return loggingDao.findPageByHQL(page, hql.toString());
	}
}
