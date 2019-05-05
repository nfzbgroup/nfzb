package com.wonders.fzb.checkmeeting.web;

import java.text.ParseException;
import java.util.HashMap;
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
import org.springframework.util.StringUtils;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingItem;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingItemService;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.plan.beans.LegislationPlan;
import com.wonders.fzb.plan.beans.LegislationPlanItem;
import com.wonders.fzb.plan.services.LegislationPlanItemService;
import com.wonders.fzb.plan.services.LegislationPlanService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;

/**
 * LegislationCheckmeetingItem action接口
 * 审核会议事项相关接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationCheckmeetingItem")
@Controller
@Scope("prototype")
public class LegislationCheckmeetingItemAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCheckmeetingItemService")
	private LegislationCheckmeetingItemService legislationCheckmeetingItemService;
	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;
	@Autowired
	@Qualifier("legislationProcessTaskService")
	private LegislationProcessTaskService legislationProcessTaskService;
	@Autowired
	@Qualifier("legislationPlanService")
	private LegislationPlanService legislationPlanService;
	@Autowired
	@Qualifier("legislationPlanItemService")
	private LegislationPlanItemService legislationPlanItemService;
	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;
	
	Page<LegislationCheckmeetingItem> infoPage;
	
	//LegislationCheckmeetingItem的修改
	@Action(value = "legislationCheckmeetingItem_add", results = {@Result(name = SUCCESS, location = "/LegislationCheckmeetingItem.jsp"), @Result(name = "List", location = "/legislationCheckmeetingItem_list.jsp")})
	public String legislationCheckmeetingItem_add() throws FzbDaoException {
		LegislationCheckmeetingItem legislationCheckmeetingItem = new LegislationCheckmeetingItem();
		legislationCheckmeetingItemService.add(legislationCheckmeetingItem);
		return SUCCESS;
	}
	/*
	 * 查询审核会议事项列表
	 */
	@Actions({ @Action(value = "checkmeeting_item_list", results = {
			@Result(name = SUCCESS, location = "/checkmeeting/meeting_item_list.jsp"),
			@Result(name = "QueryItemTable", location = "/checkmeeting/meeting_item_table.jsp"),
			@Result(name = "openItemInfoPage", location = "/checkmeeting/meeting_item_info.jsp"),
			@Result(name = "openItemInfoPlanPage", location = "/checkmeeting/meeting_item_info_plan.jsp"),
			@Result(name = "openItemInfoProcessPage", location = "/checkmeeting/meeting_item_info_process.jsp"), }) })
	public String checkmeeting_item_list() throws Exception {
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
	 * @return
	 */
	@SuppressWarnings("unused")
	private String queryTable() throws ParseException {
		String node = request.getParameter("stNodeId");
		if("NOD_0000000105".equals(node)){//查询审核会议事项分节点处理
			queryCheckMeetingItem();
		}
		return "QueryItemTable";
	}
	/**
	 * 打开事项页面
	 * @return
	 */
	@SuppressWarnings("unused")
	private String openItemInfoPage() {
		String stItemId = request.getParameter("stItemId");
		LegislationCheckmeetingItem iteminfo = legislationCheckmeetingItemService.findById(stItemId);
		
		//分类查询事项：计划、草案等。
		String stSourceId = iteminfo.getStSourceId();
		String result = "openItemInfoPage";
		if(!StringUtils.isEmpty(stSourceId)) {
			String stTypeName = iteminfo.getStTypeName();
			if("立法计划".equals(stTypeName)) {//查询立法计划
				LegislationPlan legislationPlan = legislationPlanService.findById(stSourceId);
				result = "openItemInfoPlanPage";
				iteminfo.setStSource(legislationPlan);//设置源plan对象
				List<LegislationPlanItem> legislationPlanItems = legislationPlanItemService.findByHQL("from LegislationPlanItem t where 1=1 and t.stPlanId='"+stSourceId+"'");
				iteminfo.setStSourceitems(legislationPlanItems);//设置源planitem集合
			}else if("草案".equals(stTypeName)) {//查询草案
				LegislationProcessDoc processDoc = legislationProcessDocService.findById(stSourceId);
				result = "openItemInfoProcessPage";
				iteminfo.setStSource(processDoc);//设置源草案对象
				List<LegislationProcessTask> legislationProcessTasks = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stSourceId+"'");
				iteminfo.setStSourceitems(legislationProcessTasks);//设置源planitem集合
			}
			//..... 其他事项以后补充 
		}
		request.setAttribute("iteminfo", iteminfo);
		return result;
	}
	/**
	 * 查询审核会议事项分节点处理业务处理
	 */
	@SuppressWarnings("unchecked")
	private void queryCheckMeetingItem() {
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String stNodeId = request.getParameter("stNodeId");
		String taskStatus = request.getParameter("taskStatus");
//		String startTime = request.getParameter("startTime");
//		String endTime = request.getParameter("endTime");
//		String stMeetingName = request.getParameter("stMeetingName");
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize = StringUtil.isEmpty(pageSize)?"10":pageSize;
		WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap= new HashMap<>();
		condMap.put("stStatus", StringUtils.isEmpty(taskStatus)?"TODO":taskStatus);
		sortMap.put("dtCreateDate", "DESC");
		//查询审核会议事项
		try {
			Page<LegislationCheckmeetingItem> itemPage = legislationCheckmeetingItemService.findByPage(condMap, sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			request.setAttribute("nodeInfo", nodeInfo);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("retPage", itemPage);
			request.setAttribute("nodeId", stNodeId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FzbDaoException e) {
			e.printStackTrace();
		}
	}
}
