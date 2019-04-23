package com.wonders.fzb.plan.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
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
 * LegislationPlanItem action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationPlanItem")
@Controller
@Scope("prototype")
public class LegislationPlanItemAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationPlanItemService")
	private LegislationPlanItemService legislationPlanItemService;
	@Autowired
	@Qualifier("legislationPlanTaskService")
	private LegislationPlanTaskService legislationPlanTaskService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationPlanItem的修改
	@Action(value = "legislationPlanItem_add", results = {@Result(name = SUCCESS, location = "/LegislationPlanItem.jsp"), @Result(name = "List", location = "/legislationPlanItem_list.jsp")})
	public String legislationPlanItem_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationPlanItem> legislationPlanItemList = new ArrayList<LegislationPlanItem>();
		LegislationPlanItem legislationPlanItem = new LegislationPlanItem();
		legislationPlanItemService.add(legislationPlanItem);
		return SUCCESS;
	}

	/**
	 * 检查征集通知所属项目是否都已审核
	 * @throws IOException
	 */
	@Action(value = "checkPlanItem")
	public void checkPlanItem() throws IOException {
		JSONObject jsonObject = new JSONObject();
		String stTaskId = request.getParameter("stTaskId");
		LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stPlanId", legislationPlanTask.getStPlanId());
		List<LegislationPlanItem> legislationPlanItemList=legislationPlanItemService.findByList(condMap,sortMap);
		condMap.put("stNodeIdGe","NOD_0000000205");
		condMap.put("stTaskStatus","DONE");
		condMap.put("stEnableIsNull","null");
		List<LegislationPlanTask> legislationPlanTaskList=legislationPlanTaskService.findByList(condMap,sortMap);
		if(legislationPlanItemList.size()==legislationPlanTaskList.size()){
			jsonObject.put("success",true);
		}else{
			jsonObject.put("success",false);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}

	/**
	 * 检查征集通知所属项目是否都有归属
	 * @throws IOException
	 */
	@Action(value = "checkProjectAscription")
	public void checkProjectAscription() throws IOException {
		JSONObject jsonObject = new JSONObject();
		String stTaskId = request.getParameter("stTaskId");
		LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stPlanId", legislationPlanTask.getStPlanId());
		condMap.put("stSuggestIsNull", "");
		List<LegislationPlanItem> legislationPlanItemList=legislationPlanItemService.findByList(condMap,sortMap);
		if(legislationPlanItemList.size()>0){
			jsonObject.put("success",false);
		}else{
			jsonObject.put("success",true);
		}
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}
}
