package com.wonders.fzb.plan.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.citymeeting.beans.LegislationCitymeeting;
import com.wonders.fzb.citymeeting.services.LegislationCitymeetingService;
import com.wonders.fzb.citymeeting.services.LegislationCitymeetingTaskService;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislation.beans.LegislationExample;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.plan.beans.LegislationPlan;
import com.wonders.fzb.plan.beans.LegislationPlanItem;
import com.wonders.fzb.plan.beans.LegislationPlanTask;
import com.wonders.fzb.plan.services.LegislationPlanItemService;
import com.wonders.fzb.plan.services.LegislationPlanService;
import com.wonders.fzb.plan.services.LegislationPlanTaskService;
import com.wonders.fzb.plan.services.LegislationPlanTaskdetailService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import dm.jdbc.util.StringUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Qualifier("legislationCitymeetingService")
	private LegislationCitymeetingService legislationCitymeetingService;
	@Autowired
	@Qualifier("legislationCitymeetingTaskService")
	private LegislationCitymeetingTaskService legislationCitymeetingTaskService;
	
	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;
	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;
	@Autowired
	@Qualifier("legislationSendNoticeService")
	private LegislationSendNoticeService legislationSendNoticeService;
	@Autowired
	@Qualifier("legislationPlanTaskdetailService")
	private LegislationPlanTaskdetailService legislationPlanTaskdetailService;
	
	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;
	
//	private int pageNo = 1;
//	private int pageSize = 10;


	//LegislationPlan的修改
	@Action(value = "legislationPlan_add", results = {@Result(name = SUCCESS, location = "/LegislationPlan.jsp"), @Result(name = "List", location = "/legislationPlan_list.jsp")})
	public String legislationPlan_add() throws FzbDaoException {
//		System.out.println("Begin....");
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		List<LegislationPlan> legislationPlanList = new ArrayList<LegislationPlan>();
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
			@Result(name = "plan_collect_info", location = "/plan/legislationNotice_form.jsp"),
			@Result(name = "plan_item_dist", location = "/plan/legislationPlan_form.jsp"),
			@Result(name = "plan_draft_info", location = "/plan/legislationNotice_form.jsp"),
			@Result(name = "plan_draft_modify", location = "/plan/legislationNotice_form.jsp"),
			@Result(name = "plan_item_check", location = "/plan/legislationPlan_form.jsp"),
			@Result(name = "plan_draft_fianl", location = "/plan/legislationNotice_form.jsp"),
			@Result(name = "plan_item_outplan", location = "/plan/legislationPlan_form.jsp"),
			@Result(name = "plan_creat_info", location = "/plan/legislationNotice_form.jsp"),
			@Result(name = "plan_item_recv", location = "/plan/legislationPlan_form.jsp"),
			@Result(name = "QueryTable", location = "/plan/legislationPlan_item.jsp"),
			@Result(name = "openPlanItemInfoPage", location = "/plan/legislationPlan_form.jsp"),
			//plan_item_upload
			@Result(name = "openPlanSeparatePage", location = "/plan/legislationPlan_separate.jsp"),
            @Result(name = "openPlanAuditPage", location = "/plan/legislationPlan_audit.jsp"),
			@Result(name = "openNoticeProjectInfoPage", location = "/plan/legislationNotice_projectInfo.jsp"),
			@Result(name = "openNoticeExplainPage", location = "/plan/legislationNotice_explain.jsp"),
			@Result(name = "openNoticeExplainInfoPage", location = "/plan/legislationNotice_explain.jsp"),
			@Result(name = "openNoticeDraftPage", location = "/plan/legislationNotice_draft.jsp"),
			@Result(name = "openNoticeDraftInfoPage", location = "/plan/legislationNotice_draft.jsp"),
			@Result(name = "openPlanCheckExplainPage", location = "/plan/legislationPlan_checkExplain.jsp"),
			@Result(name = "openProjectAscriptionPage", location = "/plan/legislationPlan_projectAscription.jsp"),
			@Result(name = "flowDealPage", location = "/plan/flowPlanDealPage.jsp"),
			@Result(name = "legislation_plan_flow", location = "/plan/flowPlanPage.jsp"),
			@Result(name = "openPlanDeleteReasonPage", location = "/plan/legislationPlan_deleteReason.jsp"),
			@Result(name = "openPlanBackReasonPage", location = "/plan/legislationPlan_deleteReason.jsp")
	})
	public String legislationPlan() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}
	
	private String legislation_plan_flow() {
		LegislationPlan planInfo = new LegislationPlan();
		request.setAttribute("planInfo", planInfo);
		request.setAttribute("requestUrl", request.getRequestURI());
		return pageController();
	}
	
	
	   private String openPlanAttachNum_ajax() throws IOException {
		    JSONObject retJson = new JSONObject();
			JSONArray nodeInfoArray = new JSONArray();
						
		    Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stFlowName", "立法计划");
			sortMap.put("dtExtendDate", "ASC");
			// 立法计划节点信息
			List<WegovSimpleNode> nodeList = wegovSimpleNodeService.findByList(condMap, sortMap);
			for (WegovSimpleNode wegovSimpleNode : nodeList) {
				JSONObject nodeChange = new JSONObject();
				String stNodeId = wegovSimpleNode.getStNodeId();
				nodeChange.put("node", stNodeId);	
				String sql="SELECT COUNT(1) FROM LEGISLATION_EXAMPLE WHERE ST_NODE='"+stNodeId+"' AND ST_STATUS='USED'";
				int queryExampleNum = legislationExampleService.queryExampleNum(sql);
				nodeChange.put("num", queryExampleNum);
				nodeInfoArray.add(nodeChange);
			}
			
			retJson.put("success", true);
			retJson.put("nodeInfoArray", nodeInfoArray);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(retJson);
		   return null;
	   }
	
	    // 流程图页面上，ajax加载当前计划的各节点的信息 lj
		private String openPlanIndexPage_ajax() throws IOException {
			String stPlanId = request.getParameter("stPlanId");
			UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
			String userRole = (String) session.getAttribute("userRole");
	
			JSONObject retJson = new JSONObject();
			JSONArray nodeInfoArray = new JSONArray();
			
			// 找出当前计划下的所有任务，设置到节点的状态上
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stPlanId", stPlanId);
			// 获得计划主流程task的信息
			List<LegislationPlanTask> taskList = legislationPlanTaskService.findByList(condMap, sortMap);
			String allNodeId = "";
			for (LegislationPlanTask each : taskList) {
				WegovSimpleNode node = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId='" + each.getStNodeId() + "'").get(0);
				String statusColor = "";
				statusColor = "bcg_blue";
				allNodeId += each.getStNodeId() + "#";
				JSONObject nodeChange = new JSONObject();
				System.out.println("this is not done node!!!!!");
				
					// 这里是普通的正在做的节点
					if (!"DONE".equals(each.getStTaskStatus())) {
						nodeChange.put("node", each.getStNodeId());
						nodeChange.put("colorSet", "bcg_green");
						// 当前正在做的，用权限控制一下
						if (node.getStSubmitRole() != null && (node.getStSubmitRole().indexOf(userRole) >= 0)) {
							nodeChange.put("nodeHref", node.getStInfoUrl());
						}
						System.out.println(each.getStNodeId() + "[color]：" + "bcg_green");
						System.out.println(each.getStNodeId() + "[URL]：" + node.getStInfoUrl());
						nodeInfoArray.add(nodeChange);
					} else {
						// 这里是完成的节点
						nodeChange.put("node", each.getStNodeId());
						nodeChange.put("colorSet", "bcg_blue");
						nodeChange.put("nodeHref", node.getStInfoUrl());
						System.out.println(" 这里是完成的节点" + each.getStNodeId() + "[color]：" + "bcg_blue");
						nodeInfoArray.add(nodeChange);
					}
			}

			
			System.out.println("当前草案下的任务数：" + taskList.size());
			System.out.println("这些任务的NodeIds：" + allNodeId);
			retJson.put("success", true);
			retJson.put("nodeInfoArray", nodeInfoArray);// 当前草案下的任务节点信息
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(retJson);
			return null;
		}
		private String plan_item_upload() throws ParseException, FzbDaoException {
			queryItemList();
			return "QueryTable";
		}
		
		@SuppressWarnings("unchecked")
		private void queryItemList() throws ParseException, FzbDaoException {
			String pageSize = request.getParameter("pageSize");
			String pageNo = request.getParameter("pageNo");
			//String stNodeId = "NOD_0000000205";
			String stNodeId="NOD_0000000205";
			String stPlanId="PLA_0000000000000061";
		
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();

			if (null == pageSize || "".equals(pageSize)) {
				pageSize = "10";
			}
			if (null == pageNo || "".equals(pageNo)) {
				pageNo = "1";
			}
			WegovSimpleNode nodeInfo = wegovSimpleNodeService.findById(stNodeId);
				condMap.put("stNodeId",stNodeId);
				condMap.put("stPlanId",stPlanId);
			//condMap.put("stEnableIsNull","null");
			sortMap.put("dtCreateDate", "DESC");
			@SuppressWarnings("unchecked")
			Page<LegislationPlanItem> infoPage;
			infoPage = legislationPlanItemService.findByPage(condMap, sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize));

			request.setAttribute("nodeInfo", nodeInfo);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("retPage", infoPage);
			request.setAttribute("nodeId", stNodeId);
		}
		
		
		@SuppressWarnings("unused")
		private String openPlanItemInfoPage(){
			String stItemId=request.getParameter("stItemId");
			String stNodeId=request.getParameter("stNodeId");
			LegislationPlanItem legislationPlanItem = legislationPlanItemService.findById(stItemId);
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
			request.setAttribute("legislationPlanTask",new LegislationPlanTask());
			return pageController();
		}
		
		
		private String planItemEditPage() {
			
			
			return pageController();
		}
		private String plan_item_recv(){
			String stNodeId=request.getParameter("stNodeId");
			String stPlanId=request.getParameter("stPlanId");
			String stTaskId="";
			Map<String, Object> condMap1 = new HashMap<>();
			Map<String, String> sortMap1 = new HashMap<>();
			condMap1.put("stPlanId", stPlanId);
			condMap1.put("stNodeId", stNodeId);
			List<LegislationPlanTask> findByList = legislationPlanTaskService.findByList(condMap1, sortMap1);
			if(findByList.size()>0) {
				stTaskId=findByList.get(0).getStTaskId();
			}
			LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			LegislationPlanItem legislationPlanItem=legislationPlanItemService.findById(legislationPlanTask.getStParentId());
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", legislationPlanItem.getStItemId());
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			List<LegislationPlan> legislationPlanList=legislationPlanService.findByHQL("from LegislationPlan t where 1=1 and t.stNodeId < 'NOD_0000000209' order by t.dtCreateDate desc");
			request.setAttribute("legislationPlanList",legislationPlanList);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationPlanItem",legislationPlanItem);
			request.setAttribute("legislationPlanTask",legislationPlanTask);
			return pageController();
		}
		
		private String plan_creat_info(){
			String stNodeId=request.getParameter("stNodeId");
			String stPlanId=request.getParameter("stPlanId");
			String stTaskId="";
			Map<String, Object> condMap1 = new HashMap<>();
			Map<String, String> sortMap1 = new HashMap<>();
			condMap1.put("stPlanId", stPlanId);
			condMap1.put("stNodeId", stNodeId);
			List<LegislationPlanTask> findByList = legislationPlanTaskService.findByList(condMap1, sortMap1);
			if(findByList.size()>0) {
				stTaskId=findByList.get(0).getStTaskId();
			}
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
		private String plan_item_outplan(){
			String stNodeId=request.getParameter("stNodeId");
			String stPlanId=request.getParameter("stPlanId");
			String stTaskId="";
			Map<String, Object> condMap1 = new HashMap<>();
			Map<String, String> sortMap1 = new HashMap<>();
			condMap1.put("stPlanId", stPlanId);
			condMap1.put("stNodeId", stNodeId);
			List<LegislationPlanTask> findByList = legislationPlanTaskService.findByList(condMap1, sortMap1);
			if(findByList.size()>0) {
				stTaskId=findByList.get(0).getStTaskId();
			}
			LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			LegislationPlanItem legislationPlanItem=legislationPlanItemService.findById(legislationPlanTask.getStParentId());
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", legislationPlanItem.getStItemId());
			if("NOD_0000000207".equals(stNodeId)){
				condMap.put("stNodeId", stNodeId);
			}else{
				condMap.put("stNodeId", stNodeId);
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
		
		private String plan_draft_fianl(){
			String stNodeId=request.getParameter("stNodeId");
			String stPlanId=request.getParameter("stPlanId");
			String stTaskId="";
			Map<String, Object> condMap1 = new HashMap<>();
			Map<String, String> sortMap1 = new HashMap<>();
			condMap1.put("stPlanId", stPlanId);
			condMap1.put("stNodeId", stNodeId);
			List<LegislationPlanTask> findByList = legislationPlanTaskService.findByList(condMap1, sortMap1);
			if(findByList.size()>0) {
				stTaskId=findByList.get(0).getStTaskId();
			}
			
			LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			LegislationPlan legislationPlan=legislationPlanService.findById(stPlanId);
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
		
		private String plan_item_dist(){
			String stNodeId=request.getParameter("stNodeId");
			String stPlanId=request.getParameter("stPlanId");
			String stTaskId="";
			Map<String, Object> condMap1 = new HashMap<>();
			Map<String, String> sortMap1 = new HashMap<>();
			condMap1.put("stPlanId", stPlanId);
			condMap1.put("stNodeId", stNodeId);
			List<LegislationPlanTask> findByList = legislationPlanTaskService.findByList(condMap1, sortMap1);
			if(findByList.size()>0) {
				stTaskId=findByList.get(0).getStTaskId();
			}
			LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			LegislationPlanItem legislationPlanItem=legislationPlanItemService.findById(legislationPlanTask.getStParentId());
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", legislationPlanItem.getStItemId());
		
				condMap.put("stNodeId", stNodeId);
			
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			List<LegislationPlan> legislationPlanList=legislationPlanService.findByHQL("from LegislationPlan t where 1=1 and t.stNodeId < 'NOD_0000000209' order by t.dtCreateDate desc");
			request.setAttribute("legislationPlanList",legislationPlanList);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationPlanItem",legislationPlanItem);
			request.setAttribute("legislationPlanTask",legislationPlanTask);
			return pageController();
		}
		
		private String plan_item_check(){
			String stNodeId=request.getParameter("stNodeId");
			String stPlanId=request.getParameter("stPlanId");
			String stTaskId="";
			Map<String, Object> condMap1 = new HashMap<>();
			Map<String, String> sortMap1 = new HashMap<>();
			condMap1.put("stPlanId", stPlanId);
			condMap1.put("stNodeId", stNodeId);
			List<LegislationPlanTask> findByList = legislationPlanTaskService.findByList(condMap1, sortMap1);
			if(findByList.size()>0) {
				stTaskId=findByList.get(0).getStTaskId();
			}
			LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			LegislationPlanItem legislationPlanItem=legislationPlanItemService.findById(legislationPlanTask.getStParentId());
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", legislationPlanItem.getStItemId());
			condMap.put("stNodeId", stNodeId);
			
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			List<LegislationPlan> legislationPlanList=legislationPlanService.findByHQL("from LegislationPlan t where 1=1 and t.stNodeId < 'NOD_0000000209' order by t.dtCreateDate desc");
			request.setAttribute("legislationPlanList",legislationPlanList);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationPlanItem",legislationPlanItem);
			request.setAttribute("legislationPlanTask",legislationPlanTask);
			return pageController();
		}
		
		//plan_draft_modify
		private String plan_collect_info(){
			String stNodeId=request.getParameter("stNodeId");
			String stPlanId=request.getParameter("stPlanId");
			
			//LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			LegislationPlan legislationPlan=legislationPlanService.findById(stPlanId);
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stPlanId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationPlan",legislationPlan);
			request.setAttribute("legislationPlanTask",new LegislationPlanTask());
			return pageController();
		}
		
		private String plan_draft_info(){
			String stNodeId=request.getParameter("stNodeId");
			String stPlanId=request.getParameter("stPlanId");
			//LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
			LegislationPlan legislationPlan=legislationPlanService.findById(stPlanId);
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stPlanId);
			condMap.put("stNodeId", "NOD_0000000201");
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationPlan",legislationPlan);
			request.setAttribute("legislationPlanTask",new LegislationPlanTask());
			return pageController();
		}
		
		private String plan_draft_modify(){
			String stNodeId=request.getParameter("stNodeId");
			String stPlanId=request.getParameter("stPlanId");

			LegislationPlan legislationPlan=legislationPlanService.findById(stPlanId);
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", legislationPlan.getStPlanId());
			condMap.put("stNodeId", "NOD_0000000201");
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationPlan",legislationPlan);
			request.setAttribute("legislationPlanTask",new LegislationPlanTask());
			return pageController();
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
	@SuppressWarnings("unused")
	private String openPlanAddPage(){
		List<LegislationPlan> legislationPlanList=legislationPlanService.findByHQL("from LegislationPlan t where 1=1 and t.stNodeId = 'NOD_0000000208' order by t.dtCreateDate desc");
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
		List<LegislationPlan> legislationPlanList=legislationPlanService.findByHQL("from LegislationPlan t where 1=1 and t.stNodeId = 'NOD_0000000208' order by t.dtCreateDate desc");
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
	@SuppressWarnings("unused")
	private String openPlanInfoPage(){
		return openPlanEditPage();
	}

	/**
	 * 保存立法计划
	 * @return
	 */
	@SuppressWarnings("unused")
	private String saveLegislationPlan(){
		legislationPlanItemService.saveLegislationPlan(request,session);
		return null;
	}

	/**
	 * 跳转征集通知发起
	 * @return
	 */
	@SuppressWarnings("unused")
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
	@SuppressWarnings("unused")
	private String openNoticeInfoPage(){
		return openNoticeEditPage();
	}

	/**
	 * 保存征集通知
	 * @return
	 */
	@SuppressWarnings("unused")
	private String saveLegislationNotice(){
		legislationPlanService.saveLegislationNotice(request,session);
		return null;
	}

	/**
	 * 跳转立法计划分办页
	 * @return
	 */
	@SuppressWarnings("unused")
	private String openPlanSeparatePage(){
		// 查询分办处
//		Map<String, Object> condMap = new HashMap<>();
//		Map<String, String> sortMap = new HashMap<>();
//		condMap.put("idLike", "U_3_");
//		condMap.put("unitNameLike", "法规处");
//		sortMap.put("id", "ASC");
//		List<TeamInfo> teamInfoList = teamInfoService.findTeamInfoList(condMap, sortMap);
		List<TeamInfo> teamInfoListRaw = teamInfoService.findTeamInfoInModuleByType("MODULE_LEGISLATE","市司法局处室");
		List<TeamInfo> teamInfoList=new ArrayList<TeamInfo>();
		for(TeamInfo each:teamInfoListRaw){
			if(each.getTeamName().indexOf("立法")>=0)  teamInfoList.add(each);
		}
		request.setAttribute("teamList", teamInfoList);
		return pageController();
	}

	/**
	 * 保存分办/审核
	 * @return
	 */
	@SuppressWarnings("unused")
	private String saveLegislationPlanSeparate(){
		legislationPlanTaskService.nextPlanProcess(request,session);
		return null;
	}

    /**
     * 跳转审核意见页面
     * @return
     */
	@SuppressWarnings("unused")
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
	@SuppressWarnings("unused")
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
	@SuppressWarnings("unused")
	private String openNoticeExplainInfoPage(){
		return openNoticeExplainPage();
	}

	
	/**
	 * 跳转立法计划草案送审稿
	 */
	private String openNoticeDraftPage(){
		String stTaskId=request.getParameter("stTaskId");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId="";
		if(currentPerson.getTeamInfos().size()>0) {
			teamId=currentPerson.getTeamInfos().get(0).getId();
		}
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		
		LegislationPlanTask legislationPlanTask=legislationPlanTaskService.findById(stTaskId);
		LegislationPlan legislationPlan=legislationPlanService.findById(legislationPlanTask.getStPlanId());
		if(null!=legislationPlanTask.getStTopicId()&&StringUtil.isNotEmpty(legislationPlanTask.getStTopicId())) {
			//已选常务会议议题名称
			String checkmeetingTaskId=legislationPlanTask.getStTopicId();
			LegislationCitymeeting legislationCitymeeting = legislationCitymeetingService.findById(checkmeetingTaskId);
			request.setAttribute("checkmeetingTaskId",legislationCitymeeting.getStTopicName());
		}
//		//常务会议立法
//		condMap.put("stTaskStatus", "AFFIRM");
//		sortMap.put("dtDeadDate", "ASC");
//		List<LegislationCitymeetingTask> legislationCitymeetingTaskList = legislationCitymeetingTaskService.findByList(condMap , sortMap);
		//可用的议题
		List<LegislationCitymeeting> cityMeetingList = legislationCitymeetingService.findByHQL("select k from LegislationCitymeeting k, LegislationSendNotice t where 1=1 and t.stDocId =k.stTopicId and t.stTeamId='" + teamId + "' and t.stNoticeStatus !='已反馈' ");		
				
//		condMap.clear();
//		sortMap.clear();
//		List<String> checkmeetingIds=new ArrayList<String>();
//		if(legislationCitymeetingTaskList.size()>0) {
//			for(LegislationCitymeetingTask legislationCitymeetingTask: legislationCitymeetingTaskList) {
//				checkmeetingIds.add(legislationCitymeetingTask.getStTopicId());
//			}
//		}
		//获得常务会议实体
		//condMap.put("stTopicId"+"List", checkmeetingIds);
		//List<LegislationCitymeeting> legislationCitymeetingList = legislationCitymeetingService.findByList(condMap , sortMap);
		//condMap.clear();
		//sortMap.clear();
		condMap.put("stParentId", legislationPlan.getStPlanId());
		condMap.put("stNodeId", "NOD_0000000209");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationPlan",legislationPlan);
		request.setAttribute("legislationPlanTask",legislationPlanTask);
		//request.setAttribute("legislationCitymeetingTaskList", legislationCitymeetingTaskList);
		request.setAttribute("legislationCitymeetingList", cityMeetingList);
		return pageController();
	}
	/**
	 * 跳转立法计划草案送审稿详情
	 */
	@SuppressWarnings("unused")
	private String openNoticeDraftInfoPage(){
		return openNoticeDraftPage();
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
	 * 跳转计划外立项审核说明页面
	 * @return
	 */
	@SuppressWarnings("unused")
	private String openPlanCheckExplainPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationPlanTask legislationPlanTask = legislationPlanTaskService.findById(stTaskId);
		if("DOING".equals(legislationPlanTask.getStTaskStatus())) {
			WegovSimpleNode node = wegovSimpleNodeService.findById(legislationPlanTask.getStNodeId());
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stDocId", legislationPlanTask.getStParentId());
			condMap.put("stNodeName", node.getStNodeName());
			//获得计划通知反馈集合
			List<LegislationSendNotice> legislationSendNoticeList = legislationSendNoticeService.findByList(condMap, sortMap);
			
			request.setAttribute("legislationSendNoticeList", legislationSendNoticeList);
		}
		request.setAttribute("legislationPlanTask", legislationPlanTask);
		return pageController();
	}

	/**
	 * 保存计划外立项说明
	 */
	@SuppressWarnings("unused")
	private String savePlanTaskCheck(){
		legislationPlanTaskService.nextPlanChildProcess(request,session);
		return null;
	}
    /**
     * 项目归属页面
     * @return
     */
	@SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
	private String saveLegislationProjectAscription() throws IOException {
        JSONObject jsonObject =legislationPlanItemService.saveLegislationProjectAscription(request,session);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(jsonObject);
	    return null;
    }
    
    //流程图展示
	private String flowDealPage(){
		String stPlanId = request.getParameter("stPlanId");
		LegislationPlan planInfo = legislationPlanService.findById(stPlanId);
		request.setAttribute("planInfo", planInfo);
		request.setAttribute("requestUrl", request.getRequestURI());
		return pageController();
	}

	/**
	 * 跳转项目删除原因页面
	 * @return
	 */
	private String openPlanDeleteReasonPage(){
		request.setAttribute("button","delete");
    	return pageController();
	}

	/**
	 * 跳转项目退回原因页面
	 * @return
	 */
	private String openPlanBackReasonPage(){
		request.setAttribute("button","back");
		return pageController();
	}

	/**
	 * 确认删除项目
	 * @return
	 */
	private String saveLegislationPlanDeleteReason(){
		legislationPlanTaskService.deletePlan(request,session);
		return null;
	}

	/**
	 * 确认退回项目
	 * @return
	 */
	private String goBackPlanProcess(){
		legislationPlanTaskService.goBackPlanProcess(request,session);
		return null;
	}
}
