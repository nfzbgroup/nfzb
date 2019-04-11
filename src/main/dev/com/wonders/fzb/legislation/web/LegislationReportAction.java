package com.wonders.fzb.legislation.web;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.services.*;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import dm.jdbc.util.StringUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Namespace("/legislationReport")
@Controller
@Scope("prototype")
public class LegislationReportAction extends BaseAction {

	private static final long serialVersionUID = -5052398774978475642L;

	@Autowired
	@Qualifier("legislationProcessTaskService")
	private LegislationProcessTaskService legislationProcessTaskService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;
	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;
	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;
	@Autowired
	@Qualifier("legislationProcessTaskdetailService")
	private LegislationProcessTaskdetailService legislationProcessTaskdetailService;

	String stDocId = "";
	String stNodeId = "";
	String op = "";

	public String getStDocId() {
		return stDocId;
	}

	public void setStDocId(String stDocId) {
		this.stDocId = stDocId;
	}

	public String getStNodeId() {
		return stNodeId;
	}

	public void setStNodeId(String stNodeId) {
		this.stNodeId = stNodeId;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	/**
	 * 打开报审审签页面
	 * 
	 * @return
	 * @throws Exception
	 * @author sy
	 */
	@Action(value = "openDraftReportPage", results = { @Result(name = SUCCESS, location = "/report/openDraftReportPage.jsp") })
	public String openDraftReportPage() throws Exception {

		stDocId = request.getParameter("stDocId") == null ? "" : request.getParameter("stDocId");
		stNodeId = request.getParameter("stNodeId") == null ? "" : request.getParameter("stNodeId");

		return SUCCESS;
	}

	/**
	 * 打开报审审签按钮
	 * 
	 * @throws Exception
	 * @author sy
	 */
	@Action(value = "draftReportBt")
	public void draftReportBt() throws Exception {
		JSONObject jsonObject = new JSONObject();
		stDocId = request.getParameter("stDocId") == null ? "" : request.getParameter("stDocId");
		op = request.getParameter("stDocId") == null ? "" : request.getParameter("stDocId");
		if ("save".equals(op)) {
			// legislationProcessTaskService.
			// 在legislation_process_task表中添加一条 todo的信息

		}

		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}

}
