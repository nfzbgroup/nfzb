package com.wonders.fzb.checkmeeting.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingItem;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTaskd;
import com.wonders.fzb.checkmeeting.services.*;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.UserInfoService;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.plan.beans.LegislationPlan;
import com.wonders.fzb.plan.services.LegislationPlanService;
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
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * LegislationCheckmeeting action接口
 * 
 * @author scalffold created by lj
 */

@Namespace("/legislationCheckmeeting")
@Controller
@Scope("prototype")
public class LegislationCheckmeetingAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCheckmeetingTaskService")
	private LegislationCheckmeetingTaskService legislationCheckmeetingTaskService;

	@Autowired
	@Qualifier("legislationCheckmeetingService")
	private LegislationCheckmeetingService legislationCheckmeetingService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;

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
	@Qualifier("legislationCheckmeetingItemService")
	private LegislationCheckmeetingItemService legislationCheckmeetingItemService;
	
	@Autowired
	@Qualifier("legislationCheckmeetingDealService")
	private LegislationCheckmeetingDealService legislationCheckmeetingDealService;

	@Autowired
	@Qualifier("legislationCheckmeetingTaskdService")
	private LegislationCheckmeetingTaskdService legislationCheckmeetingTaskdService;
	
	@Autowired
	@Qualifier("legislationSendNoticeService")
	LegislationSendNoticeService legislationSendNoticeService;
	@Autowired
	private UserInfoService userInfoService;
	private int pageNo = 1;
	private int pageSize = 10;

	// LegislationCheckmeeting的修改
	@Action(value = "legislationCheckmeeting_add", results = { @Result(name = SUCCESS, location = "/LegislationCheckmeeting.jsp"), @Result(name = "List", location = "/legislationCheckmeeting_list.jsp") })
	public String legislationCheckmeeting_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationCheckmeeting> legislationCheckmeetingList = new ArrayList<LegislationCheckmeeting>();
		LegislationCheckmeeting legislationCheckmeeting = new LegislationCheckmeeting();
		legislationCheckmeetingService.add(legislationCheckmeeting);
		return SUCCESS;
	}

	@Action(value = "checkmeeting_info", results = {
			@Result(name = "checkMeetingStart_old", location = "/checkmeeting/ckeckMeeting_start.jsp"),
			@Result(name = "checkmeeting_add", location = "/checkmeeting/checkmeeting_add.jsp"),
			@Result(name = "checkmeeting_addnew", location = "/checkmeeting/checkmeeting_addnew.jsp"),
			@Result(name = "checkmeeting_additem", location = "/checkmeeting/checkmeeting_additem.jsp"),
			@Result(name = "checkmeeting_feedback", location = "/checkmeeting/checkmeeting_feedback.jsp"),
			@Result(name = "checkmeeting_input", location = "/checkmeeting/checkmeeting_input.jsp"),
			@Result(name = "checkmeeting_affirm", location = "/checkmeeting/checkmeeting_affirm.jsp"),
			@Result(name = "checkmeeting_end", location = "/checkmeeting/checkmeeting_end.jsp") })
	public String draft_doc_info() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}

	/**
	 * 打开审核会议页面
	 * 
	 * @return
	 */
	private String checkmeeting_add() {
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stEnable is null order by d.dtCreateDate desc");
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		
		// 可选择的计划
//		List<LegislationPlan> legislationPlanList = legislationPlanService.findByHQL("select d from LegislationPlan d inner join LegislationPlanTask t on d.stPlanId=t.stPlanId where 1=1 and t.stNodeId='NOD_0000000210' and t.stTaskStatus='TODO' and t.stEnable is null order by d.dtCreateDate desc");
//		request.setAttribute("legislationPlanList", legislationPlanList);

		String stMeetingId = request.getParameter("stMeetingId");
		request.setAttribute("stPersonsId",null);
		request.setAttribute("otherPersonsName",null);
		if (StringUtil.isEmpty(stMeetingId)) {
			request.setAttribute("legislationCheckmeeting", new LegislationCheckmeeting());
		} else {
			LegislationCheckmeeting auditMeeting = legislationCheckmeetingService.findById(stMeetingId);
			request.setAttribute("legislationCheckmeeting", auditMeeting);
			List<LegislationCheckmeetingTask> legislationCheckmeetingTaskList = legislationCheckmeetingTaskService.findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'");
			if(legislationCheckmeetingTaskList.size()>0){
				List<LegislationCheckmeetingTaskd> legislationCheckmeetingTaskdList=legislationCheckmeetingTaskdService.findByHQL("from LegislationCheckmeetingTaskd t where 1=1 and t.stTaskId='"+legislationCheckmeetingTaskList.get(0).getStTaskId()+"' and t.stTaskStatus='TODO'");
				if(legislationCheckmeetingTaskdList.size()>0){
					request.setAttribute("stPersonsId",legislationCheckmeetingTaskdList.get(0).getStPersonId());
					request.setAttribute("otherPersonsName",legislationCheckmeetingTaskdList.get(0).getStBak1());
				}
			}
		}
		request.setAttribute("stTaskStatus", "TODO");
		//查询事项
		List<LegislationCheckmeetingItem> legislationCheckmeetingItems = legislationCheckmeetingItemService.findByHQL("from LegislationCheckmeetingItem t where t.stMeetingId='" + stMeetingId + "'");
		request.setAttribute("legislationCheckmeetingItems", legislationCheckmeetingItems);
		
		//回显上传材料
		String method = "checkmeeting_TODO";
		String nodeIdStatus[] = method.split("_");
		String nodeStatus = nodeIdStatus[1];
				if (true) {
					Map<String, Object> condMap = new HashMap<>();
					Map<String, String> sortMap = new HashMap<>();
					condMap.put("stParentId", stMeetingId);
					condMap.put("stNodeId", stNodeId);
					condMap.put("stNodeStatus", nodeStatus);
					sortMap.put("dtPubDate", "ASC");
					List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
						List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesListByNodeStatus(stNodeId, nodeStatus, legislationFilesList);
						request.setAttribute("LegislationExampleList", legislationExampleFilesList);
					
						String stStyle = "style ='display: none;'";
						request.setAttribute("stStyle", stStyle);
					request.setAttribute("nodeStatus", nodeStatus);
					request.setAttribute("legislationFilesList", legislationFilesList);
					//request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
				}
		
		return pageController();
	}
	//审核会议新接口
	@SuppressWarnings("unused")
	private String checkmeeting_addnew() {
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		String stItemIds =  request.getParameter("stItemId");
		String[] strs = stItemIds.split(",");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			builder.append("'"+ strs[i] + (i!=strs.length-1?"',":"'"));
		}
		List<LegislationCheckmeetingItem> checkmeetingItems = legislationCheckmeetingItemService.findByHQL("FROM LegislationCheckmeetingItem T WHERE T.stItemId in ("+builder.toString()+")");
		request.setAttribute("checkmeetingItems", checkmeetingItems);
		request.setAttribute("stItemId", stItemIds);
		
		
		return pageController();
	}
	
	//审核会议无sourceid项目添加
	@SuppressWarnings("unused")
	private String checkmeeting_additem() {
		String stNodeId = request.getParameter("stNodeId");
		
		String method = "checkmeeting_additem";
		//String stDocId = auditMeeting.getStMeetingId();
	//	String stNodeId = request.getParameter("stNodeId");
		String nodeIdStatus[] = method.split("_");
		
		String nodeStatus = nodeIdStatus[1];
		//LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		//List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);

		
		String stItemId = request.getParameter("stItemIds");
		String stItemIds =  request.getParameter("stItemId");
		String[] strs = stItemIds.split(",");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			builder.append("'"+ strs[i] + (i!=strs.length-1?"',":"'"));
		}
		//回显上传材料
		if (true) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stItemId);
			condMap.put("stNodeId", stNodeId);
			condMap.put("stNodeStatus", nodeStatus);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesListByNodeStatus(stNodeId, nodeStatus, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
			request.setAttribute("nodeStatus", nodeStatus);
			request.setAttribute("legislationFilesList", legislationFilesList);
			//request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}
		
		
		List<LegislationCheckmeetingItem> checkmeetingItems = legislationCheckmeetingItemService.findByHQL("FROM LegislationCheckmeetingItem T WHERE T.stItemId in ("+builder.toString()+")");
		request.setAttribute("checkmeetingItems", checkmeetingItems);
		request.setAttribute("stItemId", stItemIds);
		return pageController();
	}
	
	
	
	@SuppressWarnings("unused")
	private String checkmeeting_feedback() {
		String stNodeId = request.getParameter("stNodeId");
		String stMeetingId = request.getParameter("stMeetingId");
		
		
		LegislationCheckmeeting auditMeeting = legislationCheckmeetingService.findById(stMeetingId);
		request.setAttribute("legislationCheckmeeting", auditMeeting);
		
		LegislationCheckmeetingTask legislationCheckmeetingTask = legislationCheckmeetingTaskService.findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'").get(0);
		request.setAttribute("stTaskStatus", legislationCheckmeetingTask.getStTaskStatus());
		
		//事项
		List<LegislationCheckmeetingItem> legislationCheckmeetingItems = legislationCheckmeetingItemService.findByHQL("from LegislationCheckmeetingItem t where t.stMeetingId='" + stMeetingId + "'");
		request.setAttribute("legislationCheckmeetingItems", legislationCheckmeetingItems);
		
		//回显上传材料
		String method = "checkmeeting_FEEDBACK";
		//String stDocId = auditMeeting.getStMeetingId();
	    //String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = method.split("_");
		String nodeStatus = nodeIdStatus[1];
		if (true) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stMeetingId);
			condMap.put("stNodeId", stNodeId);
			condMap.put("stNodeStatus", nodeStatus);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			@SuppressWarnings("rawtypes")
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesListByNodeStatus(stNodeId, nodeStatus, legislationFilesList);
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);

			String stStyle = "style ='display: none;'";
			request.setAttribute("stStyle", stStyle);
			request.setAttribute("nodeStatus", nodeStatus);
			request.setAttribute("legislationFilesList", legislationFilesList);
			// request.setAttribute("legislationProcessTask",
			// legislationProcessTaskList.get(0));
		}
		
		//参会人员信息
		Map<String, Object> condMap = new HashMap<>();
		condMap.put("stDocId", stMeetingId);
		Map<String, String> sortMap = new HashMap<>();
	    List<LegislationSendNotice> legislationSendNotices = legislationSendNoticeService.findByList(condMap, sortMap);			
	    request.setAttribute("legislationSendNotices", legislationSendNotices);
	    
	    
//		List stPersonList = new ArrayList();
//		// 获得参会人员信息
//		String stPerson = auditMeeting.getStPersons();
//		if(stPerson.indexOf("、")>-1) {
//			String[] stPersons = stPerson.split("、");
//			for (String str : stPersons) {
//				stPersonList.add(str);
//			}
//		}else {
//			stPersonList.add(stPerson);
//		}
//		// System.out.println("legislationCheckmeetingTask.getStFeedback();" +
//		// legislationCheckmeetingTask.getStFeedback());
//		// legislationCheckmeetingTask.getStFeedback();
//
//		// 后台获得 jsonArray字符串
//		String jsonMessage = legislationCheckmeetingTask.getStFeedback();
//		// 将字符串转换成jsonArray对象
//		JSONArray tableData = JSONArray.parseArray(jsonMessage);
//		// JsonArray对象转成成 json对象
//		List<Map<String, Object>> mapListJson = (List) tableData;
//
//		request.setAttribute("stPersonList", stPersonList);
//		request.setAttribute("mapListJson", mapListJson);
		
		
		return pageController();
	}
	
	@Deprecated
	private String checkmeeting_feedback_old() {
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		String stMeetingId = request.getParameter("stMeetingId");
		LegislationCheckmeeting auditMeeting = legislationCheckmeetingService.findById(stMeetingId);
		request.setAttribute("legislationCheckmeeting", auditMeeting);
		LegislationCheckmeetingTask legislationCheckmeetingTask = legislationCheckmeetingTaskService.findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'").get(0);
		request.setAttribute("legislationCheckmeetingTask", legislationCheckmeetingTask);
		request.setAttribute("stTaskStatus", legislationCheckmeetingTask.getStTaskStatus());

		// 获得已经选择的草案 DFT_0000000104 根据 legislation_process_doc
		String stDocSource = auditMeeting.getStDocSource();
		// stDocSource = "DFT_1200000000000127#DFT_0000000000000104";
		String[] stDocSources = stDocSource.split("#");
		List<String> stParentIdList = new ArrayList<String>();
		for (String str : stDocSources) {
			stParentIdList.add(str);
		}
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stDocId" + "List", stParentIdList);
		sortMap.put("dtCreateDate", "ASC");
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByList(condMap, sortMap);

		        // 获得已经选择的计划
				String stPlanSource = auditMeeting.getStPlanSource();
				List<LegislationPlan> legislationPlanList =new ArrayList<>();
				if(StringUtil.isNotEmpty(stPlanSource)) {
					String[] stPlanSources = stPlanSource.split("#");
					List<String> stParentIdList1 = new ArrayList<String>();
					for (String str : stPlanSources) {
						stParentIdList1.add(str);
					}
					Map<String, Object> condMap1 = new HashMap<>();
					Map<String, String> sortMap1 = new HashMap<>();
					condMap1.put("stPlanId" + "List", stParentIdList1);
					sortMap1.put("dtCreateDate", "ASC");
			        legislationPlanList = legislationPlanService.findByList(condMap1, sortMap1);
						
				}
		List stPersonList = new ArrayList();
		// 获得参会人员信息
		String stPerson = auditMeeting.getStPersons();
		String[] stPersons = stPerson.split("、");
		for (String str : stPersons) {
			stPersonList.add(str);
		}
		// System.out.println("legislationCheckmeetingTask.getStFeedback();" +
		// legislationCheckmeetingTask.getStFeedback());
		// legislationCheckmeetingTask.getStFeedback();

		// 后台获得 jsonArray字符串
		String jsonMessage = legislationCheckmeetingTask.getStFeedback();
		// 将字符串转换成jsonArray对象
		JSONArray tableData = JSONArray.parseArray(jsonMessage);
		// JsonArray对象转成成 json对象
		List<Map<String, Object>> mapListJson = (List) tableData;

		// System.out.println("legislationProcessDocList--" +
		// legislationProcessDocList.size());
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		request.setAttribute("legislationPlanList", legislationPlanList);
		request.setAttribute("stPersonList", stPersonList);
		request.setAttribute("mapListJson", mapListJson);

		return pageController();
	}

	private String checkmeeting_input() {
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		String stMeetingId = request.getParameter("stMeetingId");
		LegislationCheckmeeting auditMeeting = legislationCheckmeetingService.findById(stMeetingId);
		request.setAttribute("legislationCheckmeeting", auditMeeting);
		LegislationCheckmeetingTask legislationCheckmeetingTask = legislationCheckmeetingTaskService.findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'").get(0);
		request.setAttribute("legislationCheckmeetingTask", legislationCheckmeetingTask);
		request.setAttribute("stTaskStatus", legislationCheckmeetingTask.getStTaskStatus());
		//回显上传材料
		String method = "checkmeeting_INPUT";
		//String stDocId = auditMeeting.getStMeetingId();
	//	String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = method.split("_");
		
		String nodeStatus = nodeIdStatus[1];
				if (true) {
					Map<String, Object> condMap = new HashMap<>();
					Map<String, String> sortMap = new HashMap<>();
					condMap.put("stParentId", stMeetingId);
					condMap.put("stNodeId", stNodeId);
					condMap.put("stNodeStatus", nodeStatus);
					sortMap.put("dtPubDate", "ASC");
					List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
						List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesListByNodeStatus(stNodeId, nodeStatus, legislationFilesList);
						request.setAttribute("LegislationExampleList", legislationExampleFilesList);
					
						String stStyle = "style ='display: none;'";
						request.setAttribute("stStyle", stStyle);
					request.setAttribute("nodeStatus", nodeStatus);
					request.setAttribute("legislationFilesList", legislationFilesList);
					//request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
				}

		return pageController();
	}

	private String checkmeeting_affirm() {
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		String stMeetingId = request.getParameter("stMeetingId");
		LegislationCheckmeeting auditMeeting = legislationCheckmeetingService.findById(stMeetingId);
		request.setAttribute("legislationCheckmeeting", auditMeeting);
		LegislationCheckmeetingTask legislationCheckmeetingTask = legislationCheckmeetingTaskService.findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'").get(0);
		request.setAttribute("legislationCheckmeetingTask", legislationCheckmeetingTask);
		request.setAttribute("stTaskStatus", legislationCheckmeetingTask.getStTaskStatus());

		//回显上传材料
		String method = "checkmeeting_ARRIRM";
		//String stDocId = auditMeeting.getStMeetingId();
	//	String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = method.split("_");
		
		String nodeStatus = nodeIdStatus[1];
				if (true) {
					Map<String, Object> condMap = new HashMap<>();
					Map<String, String> sortMap = new HashMap<>();
					condMap.put("stParentId", stMeetingId);
					condMap.put("stNodeId", stNodeId);
					condMap.put("stNodeStatus", nodeStatus);
					sortMap.put("dtPubDate", "ASC");
					List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
						List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesListByNodeStatus(stNodeId, nodeStatus, legislationFilesList);
						request.setAttribute("LegislationExampleList", legislationExampleFilesList);
					
						String stStyle = "style ='display: none;'";
						request.setAttribute("stStyle", stStyle);
					request.setAttribute("nodeStatus", nodeStatus);
					request.setAttribute("legislationFilesList", legislationFilesList);
					//request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
				}
				
		// 所有的109草案任务返回前台，展示信息
		
		
		//查看事项
		Map<String, Object> condMap = new HashMap<>();
		condMap.put("stMeetingId", stMeetingId);
		Map<String, String> sortMap = new HashMap<>();
		List<LegislationCheckmeetingItem> checkmeetingItem = legislationCheckmeetingItemService.findByList(condMap, sortMap);
		for (LegislationCheckmeetingItem lci : checkmeetingItem) {
			String stTypeName = lci.getStTypeName();
			String stSourceId = lci.getStSourceId();
			if(null==stSourceId) {
				continue;
			}
			if("草案".endsWith(stTypeName)) {
				List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stNodeId='NOD_0000000109' and t.stDocId='" + stSourceId + "'");
				lci.setStSource(legislationProcessTaskList.get(0));
			}else if("立法计划".equals(stTypeName)) {//查询立法计划
				LegislationPlan legislationPlan = legislationPlanService.findById(stSourceId);
				lci.setStSource(legislationPlan);
			}
		}
//		List<LegislationProcessDoc> legislationProcessDocAll = new ArrayList<LegislationProcessDoc>();
//		Map<String, LegislationProcessTask> legislationProcessTaskMap = new HashMap<String, LegislationProcessTask>();
//		String[] stDocIdArray = auditMeeting.getStDocSource().split("#");
//		for (int i = 0; i < stDocIdArray.length; i++) {
//			String newDocId = stDocIdArray[i];
//			if (StringUtil.isNotEmpty(newDocId)) {
//				LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(newDocId);
//				legislationProcessDocAll.add(legislationProcessDoc);
//				// 草案的109的TODO都进行确认，结束了DONE
//				List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stNodeId='NOD_0000000109' and t.stDocId='" + stDocIdArray[i] + "'");
//				
//			}
//		}
//		request.setAttribute("legislationProcessDocAll", legislationProcessDocAll);
		request.setAttribute("checkmeetingItem", checkmeetingItem);
		return pageController();
	}

	private String checkmeeting_end() {
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		String stMeetingId = request.getParameter("stMeetingId");
		LegislationCheckmeeting auditMeeting = legislationCheckmeetingService.findById(stMeetingId);
		request.setAttribute("legislationCheckmeeting", auditMeeting);
		LegislationCheckmeetingTask legislationCheckmeetingTask = legislationCheckmeetingTaskService.findByHQL("from LegislationCheckmeetingTask t where t.stMeetingId='" + stMeetingId + "' and t.stNodeId='NOD_0000000170'").get(0);
		request.setAttribute("legislationCheckmeetingTask", legislationCheckmeetingTask);
		request.setAttribute("stTaskStatus", legislationCheckmeetingTask.getStTaskStatus());

		// 所有的109草案任务返回前台，展示信息
		
		//查看事项
		Map<String, Object> condMap = new HashMap<>();
		condMap.put("stMeetingId", stMeetingId);
		Map<String, String> sortMap = new HashMap<>();
		List<LegislationCheckmeetingItem> checkmeetingItem = legislationCheckmeetingItemService.findByList(condMap, sortMap);
		for (LegislationCheckmeetingItem lci : checkmeetingItem) {
			String stTypeName = lci.getStTypeName();
			String stSourceId = lci.getStSourceId();
			if(null==stSourceId) {
				continue;
			}
			if("草案".endsWith(stTypeName)) {
				List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stNodeId='NOD_0000000109' and t.stDocId='" + stSourceId + "'");
				lci.setStSource(legislationProcessTaskList.get(0));
			}else if("立法计划".equals(stTypeName)) {//查询立法计划
				LegislationPlan legislationPlan = legislationPlanService.findById(stSourceId);
				lci.setStSource(legislationPlan);
			}
		}
		
//		List<LegislationProcessDoc> legislationProcessDocAll = new ArrayList<LegislationProcessDoc>();
//		Map<String, LegislationProcessTask> legislationProcessTaskMap = new HashMap<String, LegislationProcessTask>();
//		String[] stDocIdArray = auditMeeting.getStDocSource().split("#");
//		for (int i = 0; i < stDocIdArray.length; i++) {
//			String newDocId = stDocIdArray[i];
//			if (StringUtil.isNotEmpty(newDocId)) {
//				LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(newDocId);
//				legislationProcessDocAll.add(legislationProcessDoc);
//				// 草案的109的TODO都进行确认，结束了DONE
//				List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stNodeId='NOD_0000000109' and t.stDocId='" + stDocIdArray[i] + "'");
//				legislationProcessTaskMap.put(newDocId, legislationProcessTaskList.get(0));
//			}
//		}
//		request.setAttribute("legislationProcessDocAll", legislationProcessDocAll);
//		request.setAttribute("legislationProcessTaskMap", legislationProcessTaskMap);
		request.setAttribute("checkmeetingItem", checkmeetingItem);
		return pageController();
	}
	/**
	 * 提交数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private String commitCheckmeeting() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//保存审核会议数据
		
		String stTaskStatus = request.getParameter("stTaskStatus");
		String stType = request.getParameter("stType");
		String stAddress = request.getParameter("stAddress");
		String stPersons = request.getParameter("stPersons");
		String stMeetingName = request.getParameter("stMeetingName");
		
		String stPersonsId = request.getParameter("stPersonsId");
		String stNodeName = request.getParameter("stNodeName");
		String dtBeginDate = request.getParameter("dtBeginDate");
		String stComent = request.getParameter("stComent");
		String stItemIds = request.getParameter("stItemIds");//itemid组
		
		String otherPersonsName = request.getParameter("otherPersonsName");
		
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		
		// 审核会议数据
		LegislationCheckmeeting checkmeeting = new LegislationCheckmeeting();
		checkmeeting.setStMeetingName(stMeetingName);
		checkmeeting.setStAddress(stAddress);
		checkmeeting.setStPersons(stPersons);
		checkmeeting.setStType(stType);
		checkmeeting.setDtCreateDate(new Date());
		checkmeeting.setDtBeginDate(formatter.parse(dtBeginDate));// 会议时间
		checkmeeting.setStNodeId("NOD_0000000170");
		checkmeeting.setStNodeName(stNodeName);
		checkmeeting.setStTopic(stComent);
		checkmeeting.setDtCreateDate(new Date());
		checkmeeting.setStUserId(userId);
		checkmeeting.setStUserName(userName);
		checkmeeting.setStUnitId(unitId);
		checkmeeting.setStUnitName(unitName);
		String stMeetingId  = legislationCheckmeetingService.addObj(checkmeeting);
		
		//生成Task
		LegislationCheckmeetingTask info = new LegislationCheckmeetingTask();
		info.setStMeetingId(stMeetingId);
		info.setStNodeId("NOD_0000000170");
		info.setStNodeName("审核会议处理(单独)");
		info.setStTaskStatus("FEEDBACK");//TODO
		info.setStFlowId("");
		info.setDtOpenDate(new Date());
		info.setStUserId(userId);
		info.setStUserName(userName);
		// 一个任务的角色由节点配置定，而不是当前人，万一当前人多个角色呢？LJ
		info.setStRoleId(session.getAttribute("userRoleId").toString());
		info.setStRoleName(session.getAttribute("userRole").toString());
		info.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
		info.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
		legislationCheckmeetingTaskService.add(info);
		
		//更新Item表
		String[] itemIds = null; 
		if(!StringUtils.isEmpty(stItemIds)){
			itemIds = stItemIds.split(",");
		}
		if(null!=itemIds&&itemIds.length>0) {
			for (String string : itemIds) {
				try {
					LegislationCheckmeetingItem checkmeetingItem = legislationCheckmeetingItemService.findById(string);
					checkmeetingItem.setStMeetingId(stMeetingId);
					checkmeetingItem.setStStatus("DOING");
					if(null!=checkmeetingItem)
						legislationCheckmeetingItemService.update(checkmeetingItem);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		legislationFilesService.updateParentIdById(request,stMeetingId);
		
		//发送消息给参会人员
		String[] userIdArray=stPersonsId.split(",");
		if(!StringUtils.isEmpty(stPersonsId)) {
			for (String s:userIdArray) {
				UserInfo userInfo=userInfoService.findByUserId(s);
				LegislationSendNotice legislationSendNotice=new LegislationSendNotice();
				legislationSendNotice.setStDocId(stMeetingId);
				legislationSendNotice.setDtOpenDate(new Date());
				legislationSendNotice.setStUserId(userInfo.getUserId());
				legislationSendNotice.setStUserName(userInfo.getName());
				legislationSendNotice.setStModelName("审核会议");
				legislationSendNotice.setStNodeName("审核会议发送通知");
				legislationSendNotice.setStNoticeStatus("已发送");
				legislationSendNotice.setStNoticeContent("参加审核会议: "+stMeetingName+"的通知");
				legislationSendNoticeService.add(legislationSendNotice);
			}
		}
		if(!StringUtils.isEmpty(otherPersonsName)){
			LegislationSendNotice legislationSendNotice=new LegislationSendNotice();
			legislationSendNotice.setStDocId(stMeetingId);
			legislationSendNotice.setDtOpenDate(new Date());
			legislationSendNotice.setStUserName(otherPersonsName);
			legislationSendNotice.setStModelName("审核会议");
			legislationSendNotice.setStNodeName("审核会议发送通知");
			legislationSendNotice.setStNoticeStatus("已发送");
			legislationSendNotice.setStNoticeContent("参加审核会议: "+stMeetingName+"的通知");
			legislationSendNoticeService.add(legislationSendNotice);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}
	
	/**
	 * 保存审核会议添加无sourceid项目
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private String commitCheckmeetingItem() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String stTaskStatus = request.getParameter("stTaskStatus");
		String stType = request.getParameter("stType");
		String stContent = request.getParameter("stPersons");
		String stMeetingName = request.getParameter("stMeetingName");
		String dtBeginDate = request.getParameter("dtBeginDate");
		String stMeetingId = request.getParameter("stMeetingId");
		String stItemId = request.getParameter("stItemId");
		String stNodeName = request.getParameter("stNodeName");
		String stComent = request.getParameter("stComent");
		String stItemIds = request.getParameter("stItemIds");//itemid缁�
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();
		//
		LegislationCheckmeetingItem checkmeetingItem = new LegislationCheckmeetingItem();
		checkmeetingItem.setStMeetingId(stMeetingId);
		checkmeetingItem.setStItemName(stMeetingName);
		checkmeetingItem.setDtCreateDate(new Date());
		checkmeetingItem.setStTypeName(stType );
		checkmeetingItem.setStStatus("TODO");
		checkmeetingItem.setStContent(stContent);
		checkmeetingItem.setStUserName(userName);
		legislationCheckmeetingItemService.add(checkmeetingItem);
		//
		legislationFilesService.updateParentIdById(request, checkmeetingItem.getStItemId());	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}
	
	
	
	
	/**
	 * 保存审核会议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private String saveCheckmeeting() throws Exception {
		String stMeetingId = legislationCheckmeetingTaskService.saveAuditMeeting(request, session);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);

		// PrintWriter out = response.getWriter();
		// out.write(stMeetingId);
		// out.flush();

		return null;
	}

	/**
	 * 页面控制
	 * 
	 * @return
	 */
	private String pageController() {
		String stMeetingId = request.getParameter("stMeetingId");

		String methodStr = request.getParameter("method");

		String stNodeId = request.getParameter("stNodeId");

		String stTaskId = request.getParameter("stTaskId");

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stMeetingId", stMeetingId);
		request.setAttribute("stTaskId", stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return methodStr;
	}

	/**
	 * 审核会议保存
	 * 
	 * @return
	 */
	@Deprecated
	private String saveCheckMeetingStart() {
		String stNodeId = request.getParameter("stNodeId");
		String stTaskStatus = request.getParameter("stTaskStatus");
		String stMeetingId = request.getParameter("stMeetingId");
		String methodStr = request.getParameter("method");
		String stMeetingName = request.getParameter("stMeetingName");
		String stTopic = request.getParameter("stTopic");
		String dtBeginDate = request.getParameter("dtBeginDate");
		String dtEndDate = request.getParameter("dtEndDate");
		String stAddress = request.getParameter("stAddress");
		String stPersons = request.getParameter("stPersons");
		String type = request.getParameter("type");
		if (type == null && (null == stMeetingId || stMeetingId.equals("") || stMeetingId.equals("null"))) {
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("requestUrl", request.getRequestURI());
			return methodStr;
		}

		LegislationCheckmeetingTask legislationCheckMeetingTask = new LegislationCheckmeetingTask();
		LegislationCheckmeeting legislationCheckmeeting = new LegislationCheckmeeting();
		if (StringUtils.hasText(stMeetingId) && !stMeetingId.equals("null")) {
			legislationCheckmeeting = legislationCheckmeetingService.findById(stMeetingId);
			List<LegislationCheckmeetingTask> list = legislationCheckmeetingTaskService.findByHQL("from LegislationCheckmeetingTask t where 1=1 and t.stMeetingId ='" + legislationCheckmeeting.getStMeetingId() + "' and t.stNodeId='" + legislationCheckmeeting.getStNodeId() + "' and t.stEnable is null");
			for (LegislationCheckmeetingTask legislationTask : list) {
				legislationCheckMeetingTask = legislationTask;
			}
		}
		// 如果查不到，说明是发起操作
		/*
		 * if(type==null&&((null==legislationCheckmeeting||legislationCheckmeeting.
		 * getStMeetingId().length()==0)||(legislationCheckMeetingTask.getStMeetingId().
		 * length()==0))) { request.setAttribute("nodeId", stNodeId); return methodStr;
		 * }
		 */
		WegovSimpleNode node = (WegovSimpleNode) wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='" + stNodeId + "'").get(0);
		String[] statusCodeArray = node.getStDoneName().split("#");

		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationCheckMeetingTask.getStMeetingId());
		condMap.put("stNodeId", legislationCheckmeeting.getStNodeId());
		sortMap.put("dtPubDate", "ASC");
		// 文件查询还有问题
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);

		request.setAttribute("stMeetingId", stMeetingId);
		request.setAttribute("legislationCheckMeeting", legislationCheckmeeting);
		request.setAttribute("legislationCheckMeetingTask", legislationCheckMeetingTask);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("statusCodeArray", statusCodeArray);

		// 第一次的发起保存或者提交 ，新建doc以及task记录
		if (type != null && (null == stMeetingId || stMeetingId.equals("") || stMeetingId.equals("null"))) {
			if (null == legislationCheckmeeting) {
				legislationCheckmeeting = new LegislationCheckmeeting();
			}
			legislationCheckmeeting.setStMeetingName(stMeetingName);
			UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
			legislationCheckmeeting.setStUnitId((currentPerson.getTeamInfos().get(0)).getId());
			legislationCheckmeeting.setStUnitName((currentPerson.getTeamInfos().get(0)).getTeamName());
			legislationCheckmeeting.setStUserId(currentPerson.getUserId());
			legislationCheckmeeting.setStUserName(currentPerson.getName());
			legislationCheckmeeting.setStNodeId(stNodeId);
			legislationCheckmeeting.setStNodeName(node.getStNodeName());
			legislationCheckmeeting.setStTopic(stTopic);
			if (StringUtils.hasText(dtBeginDate)) {
				legislationCheckmeeting.setDtBeginDate(new Date(dtBeginDate));
			}
			if (StringUtils.hasText(dtEndDate)) {
				legislationCheckmeeting.setDtEndDate(new Date(dtEndDate));
			}
			legislationCheckmeeting.setStAddress(stAddress);
			legislationCheckmeeting.setStPersons(stPersons);
			legislationCheckmeeting.setStStatus("TODO");
			legislationCheckmeeting.setStType("审核会议");

			stMeetingId = legislationCheckmeetingService.addObj(legislationCheckmeeting);

			if (null == legislationCheckMeetingTask) {
				legislationCheckMeetingTask = new LegislationCheckmeetingTask();
			}
			legislationCheckMeetingTask.setStMeetingId(stMeetingId);
			legislationCheckMeetingTask.setStFlowId(legislationCheckmeeting.getStMeetingName());
			legislationCheckMeetingTask.setStNodeId(stNodeId);
			legislationCheckMeetingTask.setStNodeName(node.getStNodeName());
			legislationCheckMeetingTask.setStTaskStatus("TODO");
			legislationCheckMeetingTask.setDtOpenDate(new Date());
			legislationCheckMeetingTask.setStUserId(currentPerson.getUserId());
			legislationCheckMeetingTask.setStUserName(currentPerson.getName());
			legislationCheckMeetingTask.setStRoleId(session.getAttribute("userRoleId").toString());
			legislationCheckMeetingTask.setStRoleName(session.getAttribute("userRole").toString());
			legislationCheckMeetingTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
			legislationCheckMeetingTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());

			String taskId = legislationCheckmeetingTaskService.addObj(legislationCheckMeetingTask);

			if ("submit".equals(type)) {// 提交 修改doc 和task
				legislationCheckmeeting = legislationCheckmeetingService.findById(stMeetingId);
				legislationCheckmeeting.setDtCreateDate(new Date());
				legislationCheckmeetingService.update(legislationCheckmeeting);

				legislationCheckMeetingTask = legislationCheckmeetingTaskService.findById(taskId);
				int position = -1;
				for (int i = 0; i < statusCodeArray.length; i++) {
					String each = statusCodeArray[i];
					if (each.equals(stTaskStatus))
						position = i;
				}
				legislationCheckMeetingTask.setStTaskStatus(statusCodeArray[position + 1]);
				legislationCheckmeetingTaskService.update(legislationCheckMeetingTask);
			}
		}

		return methodStr;
	}

}
