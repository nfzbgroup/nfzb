package com.wonders.fzb.plan.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
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
	@Autowired
	@Qualifier("legislationPlanItemService")
	private LegislationPlanItemService legislationPlanItemService;
	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamInfoService;
	@Autowired
	@Qualifier("legislationPlanTaskdetailService")
	private LegislationPlanTaskdetailService legislationPlanTaskdetailService;

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
			@Result(name = "openPlanInfoPage", location = "/plan/legislationPlan_form.jsp"),
			@Result(name = "openNoticeAddPage", location = "/plan/legislationNotice_form.jsp"),
			@Result(name = "openNoticeEditPage", location = "/plan/legislationNotice_form.jsp"),
			@Result(name = "openNoticeInfoPage", location = "/plan/legislationNotice_form.jsp"),
			@Result(name = "openPlanSeparatePage", location = "/plan/legislationPlan_separate.jsp"),
            @Result(name = "openPlanAuditPage", location = "/plan/legislationPlan_audit.jsp"),
			@Result(name = "openNoticeProjectInfoPage", location = "/plan/legislationNotice_projectInfo.jsp"),
			@Result(name = "openNoticeExplainPage", location = "/plan/legislationNotice_explain.jsp"),
			@Result(name = "openNoticeExplainInfoPage", location = "/plan/legislationNotice_explain.jsp"),
			@Result(name = "openPlanCheckExplainPage", location = "/plan/legislationPlan_checkExplain.jsp"),
			@Result(name = "openPlanCheckInfoPage", location = "/plan/legislationPlan_checkInfo.jsp"),
            @Result(name = "openProjectAscriptionPage", location = "/plan/legislationPlan_projectAscription.jsp")})
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
	 * 跳转立法项目发起
	 * @return
	 */
	private String openPlanAddPage(){
		List<LegislationPlan> legislationPlanList=legislationPlanService.findByHQL("from LegislationPlan t where 1=1 and t.stNodeId < 'NOD_0000000209' order by t.dtCreateDate desc");
		request.setAttribute("legislationPlanList",legislationPlanList);
		request.setAttribute("legislationPlanItem",new LegislationPlanItem());
		request.setAttribute("legislationPlanTask",new LegislationPlanTask());
		return pageController();
	}

	/**
	 * 跳转立法项目修改
	 * @return
	 */
	private String openPlanEditPage(){
		String stTaskId=request.getParameter("stTaskId");
		String stNodeId=request.getParameter("stNodeId");
		LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
		LegislationPlanItem legislationPlanItem=legislationPlanItemService.findById(legislationPlanTask.getStParentId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationPlanItem.getStItemId());
		if("NOD_0000000207".equals(stNodeId)){
			condMap.put("stNodeId", stNodeId);
		}else{
			condMap.put("stNodeId", "NOD_0000000202");
		}
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		List<LegislationPlan> legislationPlanList=legislationPlanService.findByHQL("from LegislationPlan t where 1=1 and t.stNodeId < 'NOD_0000000209' order by t.dtCreateDate desc");
		request.setAttribute("legislationPlanList",legislationPlanList);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationPlanItem",legislationPlanItem);
		request.setAttribute("legislationPlanTask",legislationPlanTask);
		return pageController();
	}

	/**
	 * 跳转立法项目详情
	 * @return
	 */
	private String openPlanInfoPage(){
		return openPlanEditPage();
	}

	/**
	 * 保存立法计划
	 * @return
	 */
	private String saveLegislationPlan(){
		legislationPlanItemService.saveLegislationPlan(request,session);
		return null;
	}

	/**
	 * 跳转征集通知发起
	 * @return
	 */
	private String openNoticeAddPage(){
		request.setAttribute("legislationPlan",new LegislationPlan());
		request.setAttribute("legislationPlanTask",new LegislationPlanTask());
		return pageController();
	}

	/**
	 * 跳转征集通知修改
	 * @return
	 */
	private String openNoticeEditPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
		LegislationPlan legislationPlan=legislationPlanService.findById(legislationPlanTask.getStPlanId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationPlan.getStPlanId());
		condMap.put("stNodeId", "NOD_0000000201");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationPlan",legislationPlan);
		request.setAttribute("legislationPlanTask",legislationPlanTask);
		return pageController();
	}

	/**
	 * 跳转征集通知详情
	 * @return
	 */
	private String openNoticeInfoPage(){
		return openNoticeEditPage();
	}

	/**
	 * 保存征集通知
	 * @return
	 */
	private String saveLegislationNotice(){
		legislationPlanService.saveLegislationNotice(request,session);
		return null;
	}

	/**
	 * 跳转立法计划分办页
	 * @return
	 */
	private String openPlanSeparatePage(){
		// 查询分办处
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("idLike", "U_3_");
		condMap.put("unitNameLike", "法规处");
		sortMap.put("id", "ASC");
		List<TeamInfo> teamInfoList = teamInfoService.findTeamInfoList(condMap, sortMap);
		request.setAttribute("teamList", teamInfoList);
		return pageController();
	}

	/**
	 * 保存分办/审核
	 * @return
	 */
	private String saveLegislationPlanSeparate(){
		legislationPlanTaskService.nextPlanProcess(request,session);
		return null;
	}

    /**
     * 跳转审核意见页面
     * @return
     */
	private String openPlanAuditPage(){
        String stTaskId=request.getParameter("stTaskId");
        LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
        request.setAttribute("legislationPlanTask",legislationPlanTask);
	    return pageController();
    }

	/**
	 * 跳转汇总项目详情列表页
	 * @return
	 */
	private String openNoticeProjectInfoPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
		List<Map<String, Object>> legislationPlanItemList=legislationPlanItemService.queryProjectByPlanId(legislationPlanTask.getStPlanId());
		request.setAttribute("legislationPlanItemList",legislationPlanItemList);
		request.setAttribute("status",legislationPlanTask.getStTaskStatus());
		return pageController();
	}

	/**
	 * 跳转立法计划说明编辑页
	 * @return
	 */
	private String openNoticeExplainPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
		LegislationPlan legislationPlan=legislationPlanService.findById(legislationPlanTask.getStPlanId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationPlan.getStPlanId());
		condMap.put("stNodeId", "NOD_0000000209");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationPlan",legislationPlan);
		request.setAttribute("legislationPlanTask",legislationPlanTask);
		return pageController();
	}

	/**
	 * 跳转立法计划说明详情页
	 * @return
	 */
	private String openNoticeExplainInfoPage(){
		return openNoticeExplainPage();
	}

	/**
	 *检查征集通知是否已添加计划说明
	 */
	@Action(value = "checkPlanExplain")
	public void checkPlanExplain() throws IOException {
		JSONObject jsonObject = new JSONObject();
		String stTaskId = request.getParameter("stTaskId");
		LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
		LegislationPlan legislationPlan=legislationPlanService.findById(legislationPlanTask.getStPlanId());
		if(legislationPlan.getDtGatherDate()!=null){
			jsonObject.put("success",true);
		}else{
			jsonObject.put("success",false);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}

	/**
	 * 跳转计划外立项审核说明页面/送审说明页面
	 * @return
	 */
	private String openPlanCheckExplainPage(){
		return pageController();
	}

	/**
	 * 保存计划外立项说明/送审说明
	 */
	private String savePlanTaskCheck(){
		legislationPlanTaskService.savePlanTaskCheck(request,session);
		return null;
	}

	/**
	 * 跳转立法计划外立项审核记录页面
	 * @return
	 */
	private String openPlanCheckInfoPage(){
		String stTaskId=request.getParameter("stTaskId");
		String stNodeId=request.getParameter("stNodeId");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stTaskId", stTaskId);
		condMap.put("stNodeId", stNodeId);
		sortMap.put("stTaskdetailId", "ASC");
		List<LegislationPlanTaskdetail> legislationPlanTaskdetailList=legislationPlanTaskdetailService.findByList(condMap,sortMap);
		legislationPlanTaskdetailList.forEach((LegislationPlanTaskdetail legislationPlanTaskdetail)->{
			condMap.clear();
			sortMap.clear();
			condMap.put("stParentId", legislationPlanTaskdetail.getStTaskdetailId());
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			legislationPlanTaskdetail.setFilesList(legislationFilesList);
		});
		request.setAttribute("legislationPlanTaskdetailList",legislationPlanTaskdetailList);
		return pageController();
	}

    /**
     * 项目归属页面
     * @return
     */
	private String openProjectAscriptionPage(){
        String stItemId=request.getParameter("stItemId");
        LegislationPlanItem legislationPlanItem=legislationPlanItemService.findById(stItemId);
        request.setAttribute("legislationPlanItem",legislationPlanItem);
	    return pageController();
    }

    /**
     * 保存项目归属
     * @return
     */
    private String saveLegislationProjectAscription() throws IOException {
        JSONObject jsonObject =legislationPlanItemService.saveLegislationProjectAscription(request,session);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(jsonObject);
	    return null;
    }
}
