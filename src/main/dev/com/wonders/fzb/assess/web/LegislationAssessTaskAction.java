package com.wonders.fzb.assess.web;

import com.wonders.fzb.assess.beans.LegislationAssess;
import com.wonders.fzb.assess.beans.LegislationAssessItem;
import com.wonders.fzb.assess.beans.LegislationAssessTask;
import com.wonders.fzb.assess.services.LegislationAssessItemService;
import com.wonders.fzb.assess.services.LegislationAssessService;
import com.wonders.fzb.assess.services.LegislationAssessTaskService;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LegislationAssessTask action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationAssessTask")
@Controller
@Scope("prototype")
public class LegislationAssessTaskAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationAssessTaskService")
	private LegislationAssessTaskService legislationAssessTaskService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	@Qualifier("legislationAssessItemService")
	private LegislationAssessItemService legislationAssessItemService;

	@Autowired
	@Qualifier("legislationAssessService")
	private LegislationAssessService legislationAssessService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationAssessTask的修改
	@Action(value = "legislationAssessTask_add", results = {@Result(name = SUCCESS, location = "/LegislationAssessTask.jsp"), @Result(name = "List", location = "/legislationAssessTask_list.jsp")})
	public String legislationAssessTask_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationAssessTask> legislationAssessTaskList = new ArrayList<LegislationAssessTask>();
		LegislationAssessTask legislationAssessTask = new LegislationAssessTask();
		legislationAssessTaskService.add(legislationAssessTask);
		return SUCCESS;
	}

	@Actions({ @Action(value = "assess_task_list", results = {
			@Result(name = SUCCESS, location = "/assess/legislationAssess_list.jsp"),
			@Result(name = "QueryTable", location = "/assess/legislationAssess_table.jsp") }) })
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
	@SuppressWarnings("unused")
	private String queryTable() throws ParseException, FzbDaoException {
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
			if("NOD_0000000264".equals(stNodeId)){
				condMap.put("stNodeId","NOD_0000000258");
			}else{
				condMap.put("stNodeId",stNodeId);
			}
			if("NOD_0000000254".equals(stNodeId)||"NOD_0000000256".equals(stNodeId)||"NOD_0000000257".equals(stNodeId)
					||"NOD_0000000258".equals(stNodeId)||"NOD_0000000259".equals(stNodeId)||"NOD_0000000261".equals(stNodeId)){
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
		if("NOD_0000000258".equals(stNodeId)){
			sortMap.put("stActive", "ASC");
		}
		Page<LegislationAssessTask> infoPage=legislationAssessTaskService.findByPage(condMap,sortMap,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		infoPage.getResult().forEach((LegislationAssessTask legislationAssessTask)->{
			if("NOD_0000000251".equals(stNodeId)||"NOD_0000000253".equals(stNodeId)||"NOD_0000000255".equals(stNodeId)
					||"NOD_0000000262".equals(stNodeId)||"NOD_0000000263".equals(stNodeId)){
				LegislationAssess legislationAssess=legislationAssessService.findById(legislationAssessTask.getStParentId());
				legislationAssessTask.setStNodeName(legislationAssess.getStNodeName());
			}else{
				LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
				legislationAssessTask.setStNodeName(legislationAssessItem.getStNodeName());
			}
		});

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
		return "QueryTable";
	}

	/**
	 * 立法后评估大节点流转
	 * @return
	 */
	@SuppressWarnings("unused")
	private String nextAssessProcess(){
		legislationAssessTaskService.nextAssessProcess(request,session);
		return null;
	}
}
