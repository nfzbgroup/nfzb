package com.wonders.fzb.checkmeeting.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingService;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingTaskService;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationProcessTaskService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;

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

	@Action(value = "checkmeeting_info", results = { @Result(name = "checkMeetingStart_old", location = "/checkmeeting/ckeckMeeting_start.jsp"), @Result(name = "checkmeeting_add", location = "/checkmeeting/checkmeeting_add.jsp"), @Result(name = "checkmeeting_feedback", location = "/checkmeeting/checkmeeting_feedback.jsp"), @Result(name = "checkmeeting_input", location = "/checkmeeting/checkmeeting_input.jsp"),
			@Result(name = "checkmeeting_affirm", location = "/checkmeeting/checkmeeting_affirm.jsp") })
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
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		// 可选择的草案
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stEnable is null order by d.dtCreateDate desc");
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);

		String stMeetingId = request.getParameter("stMeetingId");
		if (StringUtil.isEmpty(stMeetingId)) {
			request.setAttribute("legislationCheckmeeting", new LegislationCheckmeeting());
		} else {
			LegislationCheckmeeting auditMeeting = legislationCheckmeetingService.findById(stMeetingId);
			request.setAttribute("legislationCheckmeeting", auditMeeting);
		}
		request.setAttribute("stTaskStatus", "TODO");
		return pageController();
	}

	private String checkmeeting_feedback() {
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

		List stPersonList = new ArrayList();
		// 获得参会人员信息
		String stPerson = auditMeeting.getStPersons();
		String[] stPersons = stPerson.split("、");
		for (String str : stPersons) {
			stPersonList.add(str);
		}
		System.out.println("legislationCheckmeetingTask.getStFeedback();" + legislationCheckmeetingTask.getStFeedback());
		// legislationCheckmeetingTask.getStFeedback();
		String jsonMessage = legislationCheckmeetingTask.getStFeedback();
		JSONArray tableData = JSONArray.parseArray(jsonMessage);
		List<Map<String, Object>> mapListJson = (List) tableData;

		
		
	 
		System.out.println("legislationProcessDocList--" + legislationProcessDocList.size());
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
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

		// 所有的109草案任务返回前台，展示信息
		List<LegislationProcessDoc> legislationProcessDocAll = new ArrayList<LegislationProcessDoc>();
		Map<String, LegislationProcessTask> legislationProcessTaskMap = new HashMap<String, LegislationProcessTask>();
		String[] stDocIdArray = auditMeeting.getStDocSource().split("#");
		for (int i = 0; i < stDocIdArray.length; i++) {
			String newDocId = stDocIdArray[i];
			if (StringUtil.isNotEmpty(newDocId)) {
				LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(newDocId);
				legislationProcessDocAll.add(legislationProcessDoc);
				// 草案的109的TODO都进行确认，结束了DONE
				List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stNodeId='NOD_0000000109' and t.stDocId='" + stDocIdArray[i] + "'");
				legislationProcessTaskMap.put(newDocId, legislationProcessTaskList.get(0));
			}
		}
		request.setAttribute("legislationProcessDocAll", legislationProcessDocAll);
		request.setAttribute("legislationProcessTaskMap", legislationProcessTaskMap);
		return pageController();
	}

	/**
	 * 保存审核会议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	private String saveCheckmeeting() throws Exception {
		legislationCheckmeetingTaskService.saveAuditMeeting(request, session);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
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
