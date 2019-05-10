package com.wonders.fzb.assess.web;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.assess.beans.LegislationAssess;
import com.wonders.fzb.assess.beans.LegislationAssessItem;
import com.wonders.fzb.assess.beans.LegislationAssessTask;
import com.wonders.fzb.assess.services.LegislationAssessItemService;
import com.wonders.fzb.assess.services.LegislationAssessService;
import com.wonders.fzb.assess.services.LegislationAssessTaskService;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LegislationAssessItem action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationAssessItem")
@Controller
@Scope("prototype")
public class LegislationAssessItemAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationAssessItemService")
	private LegislationAssessItemService legislationAssessItemService;

	@Autowired
	@Qualifier("legislationAssessTaskService")
	private LegislationAssessTaskService legislationAssessTaskService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	@Autowired
	@Qualifier("legislationAssessService")
	private LegislationAssessService legislationAssessService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationAssessItem的修改
	@Action(value = "legislationAssessItem_add", results = {@Result(name = SUCCESS, location = "/LegislationAssessItem.jsp"), @Result(name = "List", location = "/legislationAssessItem_list.jsp")})
	public String legislationAssessItem_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationAssessItem> legislationAssessItemList = new ArrayList<LegislationAssessItem>();
		LegislationAssessItem legislationAssessItem = new LegislationAssessItem();
		legislationAssessItemService.add(legislationAssessItem);
		return SUCCESS;
	}

	@Action(value = "assess_item_plan_info", results = { @Result(name = "openAssessItemAddPage", location = "/assess/legislationAssessItem_form.jsp"),
			@Result(name = "openAssessItemEditPage", location = "/assess/legislationAssessItem_form.jsp"),
			@Result(name = "openAssessItemInfoPage", location = "/assess/legislationAssessItem_form.jsp")
	})
	public String legislationAssess() throws Exception {
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
		String methodStr = request.getParameter("method");
		String stNodeId = request.getParameter("stNodeId");
		String stTaskId = request.getParameter("stTaskId");

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stTaskId", stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return methodStr;
	}

	/**
	 * 跳转评估项目发起页面
	 * @return
	 */
	private String openAssessItemAddPage(){
		List<LegislationAssess> legislationAssessList=legislationAssessService.findByHQL("from LegislationAssess t where 1=1 and t.stNodeId = 'NOD_0000000253' order by t.dtCreateDate desc");
		request.setAttribute("legislationAssessList",legislationAssessList);
		request.setAttribute("legislationAssessItem",new LegislationAssessItem());
		request.setAttribute("legislationAssessTask",new LegislationAssessTask());
		return pageController();
	}

	/**
	 * 跳转评估项目编辑页面
	 * @return
	 */
	private String openAssessItemEditPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationAssessItem.getStItemId());
		condMap.put("stNodeId", "NOD_0000000252");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		List<LegislationAssess> legislationAssessList=legislationAssessService.findByHQL("from LegislationAssess t where 1=1 and t.stNodeId = 'NOD_0000000253' order by t.dtCreateDate desc");
		request.setAttribute("legislationAssessList",legislationAssessList);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationAssessItem",legislationAssessItem);
		request.setAttribute("legislationAssessTask",legislationAssessTask);
		return pageController();
	}

	/**
	 * 跳转评估项目详情页面
	 * @return
	 */
	private String openAssessItemInfoPage(){
		return openAssessItemEditPage();
	}

	/**
	 * 保存评估项目
	 * @return
	 */
	private String saveLegislationAssessItem(){
		legislationAssessItemService.saveLegislationAssessItem(request,session);
		return null;
	}

	/**
	 * 检查评估项目是否已处理完
	 * @throws IOException
	 */
	@Action(value = "checkAssessItem")
	public void checkAssessItem() throws IOException {
		JSONObject jsonObject = new JSONObject();
		String stTaskId = request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stAssessId", legislationAssessTask.getStParentId());
		condMap.put("stIsDeleteIsNull", "null");
		List<LegislationAssessItem> legislationAssessItemList=legislationAssessItemService.findByList(condMap,sortMap);
		Boolean success=true;
		String message="";
		if(legislationAssessItemList.size()>0){
			for (LegislationAssessItem legislationAssessItem:legislationAssessItemList) {
				condMap.clear();
				condMap.put("stParentId",legislationAssessItem.getStItemId());
				condMap.put("stNodeId","NOD_0000000252");
				condMap.put("stEnableIsNull","null");
				condMap.put("stTaskStatus","TODO");
				List<LegislationAssessTask> legislationAssessTaskList=legislationAssessTaskService.findByList(condMap,sortMap);
				if(legislationAssessTaskList.size()>0){
					success=false;
					message="该评估规划有项目未处理完";
					break;
				}
			}
		}else{
			message="该评估规划还没有项目";
			success=false;
		}
		jsonObject.put("message",message);
		jsonObject.put("success",success);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}
}
