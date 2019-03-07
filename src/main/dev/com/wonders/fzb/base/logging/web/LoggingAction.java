package com.wonders.fzb.base.logging.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.FastQueryCondition;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.FastQuery.Condition;
import com.wonders.fzb.base.consts.FastQuery.Symbol;
import com.wonders.fzb.base.kit.StringKit;
import com.wonders.fzb.base.consts.LogConst;
import com.wonders.fzb.base.logging.beans.LogInfo;
import com.wonders.fzb.base.logging.beans.LoggingQueryBean;
import com.wonders.fzb.base.logging.beans.LoggingSearchBean;
import com.wonders.fzb.base.logging.services.LoggingService;

@Namespace("/logging")
@Controller
@Scope("prototype")
public class LoggingAction extends BaseAction {
	private static final long serialVersionUID = 5639162495584887438L;

	@Autowired
	@Qualifier("loggingService")
	private LoggingService loggingService;

	private LoggingSearchBean searchBean = new LoggingSearchBean();

	// Page Entity
	Page<LogInfo> logs;

	private int pageNo = 1;

	@Action(value = "view", results = {@Result(name = SUCCESS, location = "/WEB-INF/pages/logging/list.jsp")})
	public String execute() {
		init();		
		return SUCCESS;
	}

	@Action(value = "logs", results = { @Result(name = SUCCESS, location = "/WEB-INF/pages/logging/list.jsp") })
	public String list() {
		init();
		return SUCCESS;
	}
	
	private void init(){
		LoggingQueryBean queryBean = new LoggingQueryBean(LogInfo.class);

		if(!StringKit.isEmpty(searchBean.getInfo())){
			queryBean.setCtx(new FastQueryCondition(Symbol.LIKE, searchBean.getInfo()));
		}
		
		if(!StringKit.isEmpty(searchBean.getMethod())){
			queryBean.setMethod(new FastQueryCondition(Symbol.LIKE, searchBean.getMethod()));
		}
		
		if(!StringKit.isEmpty(searchBean.getUsrInfo())){
			queryBean.setHost(new FastQueryCondition(Symbol.LIKE, searchBean.getUsrInfo()));
			queryBean.setUsr(new FastQueryCondition(Symbol.LIKE,Condition.OR, searchBean.getUsrInfo()));
		}
		
		if(!StringKit.isEmpty(searchBean.getCategory())){
			queryBean.setCategory(new FastQueryCondition(Symbol.IN,searchBean.getCategory()));
		}

		logs = loggingService.findByPage(pageNo, queryBean);

		request.setAttribute("naviga", "all");
	}

	@Action(value = "debug", results = { @Result(name = SUCCESS, location = "/WEB-INF/pages/logging/debug_list.jsp") })
	public String debug() {
		LoggingQueryBean queryBean = new LoggingQueryBean(LogInfo.class);
		queryBean.setCategory(new FastQueryCondition(Symbol.EQ, LogConst.Category.DEBUG));

		logs = loggingService.findByPage(pageNo, queryBean);

		request.setAttribute("naviga", "debug");
		return SUCCESS;
	}

	public LoggingService getLoggingService() {
		return loggingService;
	}

	public void setLoggingService(LoggingService loggingService) {
		this.loggingService = loggingService;
	}

	public Page<LogInfo> getLogs() {
		return logs;
	}

	public void setLogs(Page<LogInfo> logs) {
		this.logs = logs;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public LoggingSearchBean getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(LoggingSearchBean searchBean) {
		this.searchBean = searchBean;
	}
}