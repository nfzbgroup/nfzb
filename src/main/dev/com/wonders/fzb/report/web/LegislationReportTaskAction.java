package com.wonders.fzb.report.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.report.beans.*;
import com.wonders.fzb.report.services.*;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;

/**
 * LegislationReportTask action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationReportTask")
@Controller
@Scope("prototype")
public class LegislationReportTaskAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationReportTaskService")
	private LegislationReportTaskService legislationReportTaskService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;
	
	private int pageNo = 1;
	private int pageSize = 10;
	Page<LegislationReport> infoPage;

	//LegislationReportTask的修改
	@Action(value = "legislationReportTask_add", results = {@Result(name = SUCCESS, location = "/LegislationReportTask.jsp"), @Result(name = "List", location = "/legislationReportTask_list.jsp")})
	public String legislationReportTask_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationReportTask> legislationReportTaskList = new ArrayList<LegislationReportTask>();
		LegislationReportTask legislationReportTask = new LegislationReportTask();
		legislationReportTaskService.add(legislationReportTask);
		return SUCCESS;
	}
	
	
	
	// 所有模块的所有菜单的列表，统一一下请求，请不要改动。根据nodeid返回默认状态的任务列表。 lj
	@Actions({ @Action(value = "report_task_list", results = { @Result(name = SUCCESS, location = "/report/report_list.jsp"), 
			@Result(name = "QueryTable", location = "/report/report_table.jsp") }) })
	public String listMethodManager() throws Exception {
		String methodStr = request.getParameter("method");
		if (StringUtil.isEmpty(methodStr)) {
			String stNodeId = request.getParameter("stNodeId");
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("nodeId", stNodeId);

			boolean isZhc = (boolean) session.getAttribute("isZhc");

			request.setAttribute("isZhc", isZhc);
			request.setAttribute("stTodoNameList", wegovSimpleNodeService.queryButtonInfo(stNodeId));
			return SUCCESS;
		} else {
			java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
			Object object = method.invoke(this);
			return object == null ? null : object.toString();
		}
	}
	
	
	private String queryTable() throws ParseException {
			queryReport();
		return "QueryTable";
	}

	private void queryReport() throws ParseException {
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String stNodeId = request.getParameter("stNodeId");
		String taskStatus = request.getParameter("taskStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String stReportName = request.getParameter("stReportName");

		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}

		WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
		String baseSql = "WHERE 1=1 ";

		if (null != stNodeId && !"".equals(stNodeId)) {
			baseSql += "and t.st_node_Id = '" + stNodeId + "' ";
		}
		if (null != startTime && !"".equals(startTime)) {
			baseSql += "and c.DT_CREATE_DATE >= TO_DATE('" + startTime + "','yyyy-mm-dd')";
		}
		if (StringUtil.isNotEmpty(endTime)) {
			baseSql += "and c.DT_CREATE_DATE <= TO_DATE('" + endTime + "','yyyy-mm-dd')";
		}
		if (null != stReportName && !"".equals(stReportName)) {
			baseSql += "and c.st_Report_name like '%" + stReportName + "%' ";
		}
		if (null != taskStatus && !"".equals(taskStatus)) {
			baseSql += "and t.st_task_status = '" + taskStatus + "' ";
		} else {
			baseSql += "and t.st_task_status = 'TODO' ";
		}
		//baseSql += "and c.st_node_Id = '" + stNodeId + "'";
		baseSql += "and t.st_enable is null ";
		//baseSql += "and t.st_team_Id = '" + session.getAttribute("unitCode") + "' ";

		String orderSql = " order by c.dt_create_date DESC";
		//orderSql = "";
		//System.out.println("baseSql + orderSql------------" + baseSql + orderSql);
		infoPage = legislationReportTaskService.findReportTasksByNodeId(baseSql + orderSql, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
 
 
		
		// List<LegislationCheckReport> resultList = new ArrayList<>();
		// List<LegislationCheckReport> docList = infoPage.getResult();
		// infoPage.setResult(resultList);
		if (StringUtil.isEmpty(taskStatus)) {
			//request.setAttribute("buttonStatus", "TODO");
			request.setAttribute("buttonStatus", "ALL");
		} else {
			request.setAttribute("buttonStatus", taskStatus);
		}
		request.setAttribute("nodeInfo", nodeInfo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("retPage", infoPage);
		request.setAttribute("nodeId", stNodeId);
	}


}
