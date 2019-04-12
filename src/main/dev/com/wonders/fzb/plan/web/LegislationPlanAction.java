package com.wonders.fzb.plan.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.plan.beans.*;
import com.wonders.fzb.plan.services.*;

/**
 * LegislationPlan action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationPlan")
@Controller
@Scope("prototype")
public class LegislationPlanAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationPlanService")
	private LegislationPlanService legislationPlanService;
	@Autowired
	@Qualifier("legislationPlanTaskService")
	private LegislationPlanTaskService legislationPlanTaskService;
	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationPlan的修改
	@Action(value = "legislationPlan_add", results = {@Result(name = SUCCESS, location = "/LegislationPlan.jsp"), @Result(name = "List", location = "/legislationPlan_list.jsp")})
	public String legislationPlan_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationPlan> legislationPlanList = new ArrayList<LegislationPlan>();
		LegislationPlan legislationPlan = new LegislationPlan();
		legislationPlanService.add(legislationPlan);
		return SUCCESS;
	}

	@Action(value = "draft_plan_info", results = { @Result(name = "openPlanAddPage", location = "/plan/legislationPlan_form.jsp"),
			@Result(name = "openPlanEditPage", location = "/plan/legislationPlan_form.jsp"),
			@Result(name = "openPlanInfoPage", location = "/plan/legislationPlan_form.jsp") })
	public String legislationPlan() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}

	/**
	 * 页面控制
	 *
	 * @return
	 */
	private String pageController() {
		String stPlanId = request.getParameter("stPlanId");

		String methodStr = request.getParameter("method");

		String stNodeId = request.getParameter("stNodeId");

		String stTaskId = request.getParameter("stTaskId");

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stPlanId", stPlanId);
		request.setAttribute("stTaskId", stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return methodStr;
	}

	/**
	 * 立法项目发起
	 * @return
	 */
	private String openPlanAddPage(){
		request.setAttribute("legislationPlan",new LegislationPlan());
		request.setAttribute("legislationPlanTask",new LegislationPlanTask());
		return pageController();
	}

	/**
	 * 立法项目修改
	 * @return
	 */
	private String openPlanEditPage(){
		String stTaskId=request.getParameter("stTaskId");
		String stNodeId=request.getParameter("stNodeId");
		LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
		LegislationPlan legislationPlan=legislationPlanService.findById(legislationPlanTask.getStPlanId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationPlan.getStPlanId());
		condMap.put("stNodeId", stNodeId);
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationPlan",legislationPlan);
		request.setAttribute("legislationPlanTask",legislationPlanTask);
		return pageController();
	}

	/**
	 * 立法项目详情
	 * @return
	 */
	private String openPlanInfoPage(){
		return openPlanEditPage();
	}

	private String saveLegislationPlan(){

		return null;
	}
}
