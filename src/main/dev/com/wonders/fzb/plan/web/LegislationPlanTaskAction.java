package com.wonders.fzb.plan.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import dm.jdbc.util.StringUtil;
import org.apache.commons.lang3.time.DateUtils;
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
import com.wonders.fzb.plan.beans.*;
import com.wonders.fzb.plan.services.*;

/**
 * LegislationPlanTask action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationPlanTask")
@Controller
@Scope("prototype")
public class LegislationPlanTaskAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationPlanTaskService")
	private LegislationPlanTaskService legislationPlanTaskService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationPlanTask的修改
	@Action(value = "legislationPlanTask_add", results = {@Result(name = SUCCESS, location = "/LegislationPlanTask.jsp"), @Result(name = "List", location = "/legislationPlanTask_list.jsp")})
	public String legislationPlanTask_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationPlanTask> legislationPlanTaskList = new ArrayList<LegislationPlanTask>();
		LegislationPlanTask legislationPlanTask = new LegislationPlanTask();
		legislationPlanTaskService.add(legislationPlanTask);
		return SUCCESS;
	}

	@Actions({ @Action(value = "plan_task_list", results = { @Result(name = SUCCESS, location = "/plan/legislationPlan_list.jsp"), @Result(name = "QueryTable", location = "/plan/legislationPlan_table.jsp") }) })
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

	/**
	 * table查询
	 *
	 * @return
	 */
	private String queryTable() throws ParseException, FzbDaoException {
		queryNoticeList();
		return "QueryTable";
	}

	private void queryNoticeList() throws ParseException, FzbDaoException {
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String stNodeId = request.getParameter("stNodeId");
		String stUserName = request.getParameter("stUserName");
		String stTaskStatus = request.getParameter("taskStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String stPlanName = request.getParameter("stPlanName");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();

		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}

		WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);

		if (StringUtil.isNotEmpty(stNodeId)) {
			condMap.put("stNodeId",stNodeId);
			if("NOD_0000000205".equals(stNodeId)){
				condMap.put("stTeamId",session.getAttribute("unitCode"));
			}
		}
		if (StringUtil.isNotEmpty(startTime)) {
			condMap.put("dtOpenDateGe",DateUtils.parseDate(startTime,"yyyy-MM-dd"));
		}
		if (StringUtil.isNotEmpty(endTime)) {
			condMap.put("dtOpenDateLe",DateUtils.parseDate(endTime,"yyyy-MM-dd"));
		}
		if (StringUtil.isNotEmpty(stUserName)) {
			condMap.put("stUserNameLike",stUserName);
		}
		if (StringUtil.isNotEmpty(stPlanName)) {
			condMap.put("stFlowIdLike",stPlanName);
		}
		if (StringUtil.isNotEmpty(stTaskStatus)) {
			condMap.put("stTaskStatus",stTaskStatus);
		} else {
			condMap.put("stTaskStatus","TODO");
		}
		condMap.put("stEnableIsNull","null");
		sortMap.put("dtOpenDate", "DESC");
		Page<LegislationPlanTask> infoPage = legislationPlanTaskService.findByPage(condMap,sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

		if (StringUtil.isEmpty(stTaskStatus)) {
			request.setAttribute("buttonStatus", "TODO");
		} else {
			request.setAttribute("buttonStatus", stTaskStatus);
		}
		request.setAttribute("nodeInfo", nodeInfo);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("retPage", infoPage);
		request.setAttribute("nodeId", stNodeId);
	}

	/**
	 * 立法计划大节点流转
	 * @return
	 */
	private String nextPlanProcess(){
		legislationPlanTaskService.nextPlanProcess(request,session);
		return null;
	}
}
