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
			@Result(name = "openAssessItemInfoPage", location = "/assess/legislationAssessItem_form.jsp"),
			@Result(name = "openAssessItemAuditPage", location = "/assess/legislationAssessItem_audit.jsp"),
			@Result(name = "openAssessItemPlanPage", location = "/assess/legislationAssessItem_plan.jsp"),
            @Result(name = "openAssessItemSuggestPage", location = "/assess/legislationAssessItem_suggest.jsp"),
            @Result(name = "openAssessItemSchedulePage", location = "/assess/legislationAssessItem_schedule.jsp"),
			@Result(name = "openAssessItemScheduleListPage", location = "/assess/legislationAssessItem_scheduleList.jsp"),
			@Result(name = "openAssessItemReportPage", location = "/assess/legislationAssessItem_report.jsp")

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
		String stNodeId = request.getParameter("stNodeId");
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
				if("NOD_0000000253".equals(stNodeId)){
					condMap.put("stNodeId","NOD_0000000252");
				}else if("NOD_0000000255".equals(stNodeId)){
					condMap.put("stNodeId","NOD_0000000254");
				}else {
					//stNodeId=NOD_0000000262
					condMap.put("stNodeId","NOD_0000000261");
				}
				condMap.put("stEnableIsNull","null");
				condMap.put("stTaskStatus","DONE");
				List<LegislationAssessTask> legislationAssessTaskList=legislationAssessTaskService.findByList(condMap,sortMap);
				if(legislationAssessTaskList.size() ==0){
					success=false;
					message="该评估规划有项目未处理完";
					break;
				}
			}
			if("NOD_0000000262".equals(stNodeId)&&null==legislationAssessTask.getStComment1()){
				success=false;
				message="请先录入情况报告";
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

	/**
	 * 跳转立法处初审页面
	 * @return
	 */
	private String openAssessItemAuditPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		request.setAttribute("legislationAssessTask",legislationAssessTask);
		return pageController();
	}

	/**
	 * 保存审核意见
	 * @return
	 */
	private String saveLegislationAssessItemAudit(){
		legislationAssessTaskService.nextAssessProcess(request,session);
		return null;
	}

	/**
	 * 跳转编辑/查看评估方案页面
	 * @return
	 */
	private String openAssessItemPlanPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationAssessItem.getStItemId());
		condMap.put("stNodeId", "NOD_0000000256");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationAssessItem",legislationAssessItem);
		request.setAttribute("legislationAssessTask",legislationAssessTask);
		return pageController();
	}

	/**
	 * 检查是否已编辑评估方案/建议/进度
	 */
	@Action(value = "checkAssessItemPlan")
	public void checkAssessItemPlan() throws IOException {
		JSONObject jsonObject = new JSONObject();
		String stTaskId = request.getParameter("stTaskId");
        String stNodeId = request.getParameter("stNodeId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
        Boolean success;
        String message="";
        if("NOD_0000000256".equals(stNodeId)){
            //检查评估方案
            if(legislationAssessItem.getStTypeName()!=null){
                success=true;
            }else{
                success=false;
                message="请补全评估方案信息!";
            }
        }else if("NOD_0000000257".equals(stNodeId)){
            if(legislationAssessItem.getStSuggest()!=null){
                success=true;
            }else{
                success=false;
                message="请补全评估方案建议!";
            }
        }else if("NOD_0000000258".equals(stNodeId)){
			if(legislationAssessTask.getStComment1()!=null){
				success=true;
			}else{
				success=false;
				message="请先反馈评估进度!";
			}
        }else {
            //stNodeId=NOD_0000000259
            if(legislationAssessTask.getStComment1()!=null){
                success=true;
            }else{
                success=false;
                message="请先录入评估报告!";
            }
        }
        jsonObject.put("success",success);
        jsonObject.put("message",message);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}

    /**
     * 跳转编辑/查看评估方案建议页面
     * @return
     */
	private String openAssessItemSuggestPage(){
        String stTaskId=request.getParameter("stTaskId");
        LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
        LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
        Map<String, Object> condMap = new HashMap<>();
        Map<String, String> sortMap = new HashMap<>();
        condMap.put("stParentId", legislationAssessItem.getStItemId());
        condMap.put("stNodeId", "NOD_0000000257");
        sortMap.put("dtPubDate", "ASC");
        List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
        request.setAttribute("legislationFilesList",legislationFilesList);
        request.setAttribute("legislationAssessItem",legislationAssessItem);
        request.setAttribute("legislationAssessTask",legislationAssessTask);
	    return pageController();
    }

    /**
     * 跳转编辑/查看反馈评估进度页面
     * @return
     */
    private String openAssessItemSchedulePage(){
        String stTaskId=request.getParameter("stTaskId");
        String stNodeId=request.getParameter("stNodeId");
        LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
        LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
		if("NOD_0000000264".equals(stNodeId)){
			if("TODO".equals(legislationAssessTask.getStTaskStatus())){
				legislationAssessTaskService.nextAssessProcess(request,session);
			}
			LegislationAssessTask legislationAssessTaskReal=legislationAssessTaskService.findByHQL("from LegislationAssessTask t where 1=1 and t.stParentId='"+legislationAssessItem.getStItemId()+"' and t.stNodeId='NOD_0000000258' and t.stActive='"+legislationAssessTask.getStActive()+"' and t.stEnable is null").get(0);
			stTaskId=legislationAssessTaskReal.getStTaskId();
		}
        Map<String, Object> condMap = new HashMap<>();
        Map<String, String> sortMap = new HashMap<>();
        condMap.put("stParentId", stTaskId);
        condMap.put("stNodeId", "NOD_0000000258");
        sortMap.put("dtPubDate", "ASC");
        List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
        request.setAttribute("legislationFilesList",legislationFilesList);
        request.setAttribute("legislationAssessItem",legislationAssessItem);
        request.setAttribute("legislationAssessTask",legislationAssessTask);
	    return pageController();
    }

	/**
	 * 查看一个评估方案中4个季度评估进度页面
	 * @return
	 */
	private String openAssessItemScheduleListPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationAssessTask.getStParentId());
		condMap.put("stNodeId", "NOD_0000000258");
		condMap.put("stEnableIsNull", "null");
		sortMap.put("stActive", "ASC");
		List<LegislationAssessTask> legislationAssessTaskList=legislationAssessTaskService.findByList(condMap,sortMap);
		request.setAttribute("legislationAssessTaskList",legislationAssessTaskList);
    	return pageController();
	}

	/**
	 * 跳转录入/查看评估报告页面
	 * @return
	 */
	private String openAssessItemReportPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationAssessTask legislationAssessTask=legislationAssessTaskService.findById(stTaskId);
		LegislationAssessItem legislationAssessItem=legislationAssessItemService.findById(legislationAssessTask.getStParentId());
		LegislationAssessTask legislationAssessTaskReport=legislationAssessTaskService.findByParentIdAndNodeId(legislationAssessItem.getStItemId(),"NOD_0000000259").get(0);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationAssessItem.getStItemId());
		condMap.put("stNodeId", "NOD_0000000259");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationAssessItem",legislationAssessItem);
		request.setAttribute("legislationAssessTask",legislationAssessTaskReport);
		return pageController();
	}
}
