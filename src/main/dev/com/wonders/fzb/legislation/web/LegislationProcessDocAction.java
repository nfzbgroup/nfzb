package com.wonders.fzb.legislation.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingService;
import com.wonders.fzb.checkmeeting.services.LegislationCheckmeetingTaskService;
import com.wonders.fzb.citymeeting.beans.LegislationCitymeeting;
import com.wonders.fzb.citymeeting.services.LegislationCitymeetingService;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.services.*;
import com.wonders.fzb.plan.beans.LegislationPlan;
import com.wonders.fzb.plan.services.LegislationPlanService;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;
import dm.jdbc.util.StringUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * LegislationProcessDoc action接口
 * 
 * @author scalffold created by lj
 */

@Namespace("/legislationProcessDoc")
@Controller
@Scope("prototype")
public class LegislationProcessDocAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;

	@Autowired
	@Qualifier("legislationProcessTaskService")
	private LegislationProcessTaskService legislationProcessTaskService;
	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;

	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamInfoService;

	@Autowired
	@Qualifier("legislationProcessDealService")
	private LegislationProcessDealService legislationProcessDealService;
	
	@Autowired
	@Qualifier("legislationProcessTaskdetailService")
	private LegislationProcessTaskdetailService legislationProcessTaskdetailService;
	
	@Autowired
	@Qualifier("legislationCitymeetingService")
	private LegislationCitymeetingService legislationCitymeetingService;
	
	@Autowired
	@Qualifier("legislationCheckmeetingService")
	private LegislationCheckmeetingService legislationCheckmeetingService;
	 
	@Autowired
	@Qualifier("legislationCheckmeetingTaskService")
	private LegislationCheckmeetingTaskService legislationCheckmeetingTaskService;
	
	@Autowired
	@Qualifier("legislationPlanService")
	private LegislationPlanService legislationPlanService;

	private File upload;

	private String uploadContentType;

	private String uploadFileName;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Action(value = "draft_doc_info", results = { @Result(name = "openAddPage", location = "/legislation/legislationProcessManager_add.jsp"), @Result(name = "openEditPage", location = "/legislation/legislationProcessManager_add.jsp"), @Result(name = "draft_create_info", location = "/legislation/legislationProcessManager_info.jsp"), @Result(name = "openDraftHistoryPage", location = "/legislation/legislationProcessManager_draftHistory.jsp"),
			@Result(name = "openSeparatePage", location = "/legislation/legislationProcessManager_separate.jsp"), @Result(name = "openDemonstrationPage", location = "/legislation/legislationProcessManager_demonstration.jsp"), @Result(name = "openExpertDemonstrationPage", location = "/legislation/legislationProcessManager_expertDemonstration.jsp"), @Result(name = "openLegislationDemonstrationPage", location = "/legislation/legislationProcessManager_legislationDemonstration.jsp"),
			@Result(name = "openExpertInfoPage", location = "/legislation/legislationProcessManager_expertInfo.jsp"), @Result(name = "openLegislationInfoPage", location = "/legislation/legislationProcessManager_legislationInfo.jsp"), @Result(name = "openAddAuditMeetingPage", location = "/legislation/legislationProcessManager_auditMeeting.jsp"), @Result(name = "openEditAuditMeetingPage", location = "/legislation/legislationProcessManager_auditMeeting.jsp"),
			@Result(name = "openAuditMeetingFeedbackPage", location = "/legislation/legislationProcessManager_auditMeetingFeedback.jsp"), @Result(name = "openAuditMeetingInfoPage", location = "/legislation/legislationProcessManager_auditMeetingInfo.jsp"), @Result(name = "openSeparateTabPage", location = "/legislation/legislationProcessManager_separateTab.jsp"), @Result(name = "openInfoTabPage", location = "/legislation/legislationProcessManager_infoTab.jsp"),
			@Result(name = "openUnitTabPage", location = "/legislation/legislationProcessManager_unitTab.jsp"), @Result(name = "openAuditMeetingTabPage", location = "/legislation/legislationProcessManager_auditMeetingTab.jsp"), @Result(name = "openOnlineTabPage", location = "/legislation/legislationProcessManager_onlineTab.jsp"), @Result(name = "openHeartMeetingAddPage", location = "/legislation/legislationProcessManager_legislationForm.jsp"),
			@Result(name = "openHeartMeetingEditPage", location = "/legislation/legislationProcessManager_legislationForm.jsp"), @Result(name = "openHeartMeetingCheckInfoPage", location = "/legislation/legislationProcessManager_checkInfo.jsp"), @Result(name = "openProMeetCheckInfoPage", location = "/legislation/legislationProcessManager_checkInfo.jsp"), @Result(name = "openMeetingBeforeInfoPage", location = "/legislation/legislationProcessManager_legislationBeforeInfo.jsp"),
			@Result(name = "openMeetingAfterInfoPage", location = "/legislation/legislationProcessManager_legislationAfterInfo.jsp"), @Result(name = "openExpertAddPage", location = "/legislation/legislationProcessManager_expertForm.jsp"), @Result(name = "openCheckExplainPage", location = "/legislation/legislationProcessManager_checkExplain.jsp"), @Result(name = "openLeaderIdeaPage", location = "/legislation/legislationProcessManager_leaderIdea.jsp"),
			@Result(name = "openExpertEditPage", location = "/legislation/legislationProcessManager_expertForm.jsp"), @Result(name = "openUnitAddPage", location = "/legislation/legislationProcessManager_unitForm.jsp"), @Result(name = "openUnitEditPage", location = "/legislation/legislationProcessManager_unitForm.jsp"), @Result(name = "openUnitSeekPage", location = "/legislation/legislationProcessManager_unitSeek.jsp"),
			@Result(name = "openUnitReceivePage", location = "/legislation/legislationProcessManager_unitReceive.jsp"), @Result(name = "openEditMeetingPage", location = "/legislation/legislationProcessManager_auditMeeting.jsp"), @Result(name = "openUnitInfoPage", location = "/legislation/legislationProcessManager_unitInfo.jsp"), @Result(name = "openUnitAddOpinionPage", location = "/legislation/legislationProcessManager_unitAddOpinion.jsp"),
			@Result(name = "openHandleDemonstrationPage", location = "/legislation/legislationProcessManager_handleDemonstration.jsp"),
			@Result(name = "openInfoPage", location = "/legislation/legislationProcessManager_info.jsp"),

			@Result(name = "openProcessIndexPage_old", location = "/legislation/process_index.jsp"),
			// 处理环节的总图显示
			@Result(name = "openProcessIndexPage", location = "/legislation/flowDealPage.jsp"),

			// 首页显示
			@Result(name = "queryDocInfo", location = "/legislation/indexPage.jsp"),

			@Result(name = "legislation_saple_flow", location = "/legislation/flowSamplePage.jsp"),
			@Result(name = "sampleList", location = "/legislation/flowSampleDeal.jsp"),
			@Result(name = "sampleTable", location = "/legislation/flowSampleTable.jsp"),
			


			// -----------以下是部门征求意见--------
			// 部门意见征询发起
			@Result(name = "draft_deal_deptopinion_start", location = "/legislation/draft_deal_deptopinion_start.jsp"),
			// 部门意见征求发起OLD
			@Result(name = "openUnitDemonstrationPage", location = "/legislation/legislationProcessManager_unitDemonstration.jsp"),
			// 部门意见征询盖章
			@Result(name = "draft_deal_deptopinion_sign", location = "/legislation/draft_deal_deptopinion_sign.jsp"),
			// 部门意见征询发送部门
			@Result(name = "draft_deal_deptopinion_send", location = "/legislation/draft_deal_deptopinion_send.jsp"),
			// 部门意见征询接收反馈
			@Result(name = "draft_deal_deptopinion_input", location = "/legislation/draft_deal_deptopinion_input.jsp"),

			// -----------以上是部门征求意见--------

			@Result(name = "openLegislationReceivePage", location = "/legislation/legislationProcessManager_legislationReceive.jsp"), @Result(name = "openLegislationCensorshipPage", location = "/legislation/legislationProcessManager_legislationCensorship.jsp"), @Result(name = "openLegislationReleasePage", location = "/legislation/legislationProcessManager_legislationRelease.jsp"),
			@Result(name = "openLegislationRegistrationPage", location = "/legislation/legislationProcessManager_legislationRegistration.jsp"),

			// ----以下是公开征求意见节点------
			// 公开征求意见发起
			@Result(name = "draft_deal_net_start", location = "/legislation/draft_deal_net_start.jsp"),
			// 公开征求意见接收
			@Result(name = "draft_deal_net_deal__TODO", location = "/legislation/draft_deal_net_deal__TODO.jsp"),
			// 公开征求意见送审
			@Result(name = "draft_deal_net_deal__SEND", location = "/legislation/draft_deal_net_deal__SEND.jsp"),
			// 网上征求意见上网发布
			@Result(name = "draft_deal_net_deal__PUBLISH", location = "/legislation/draft_deal_net_deal__PUBLISH.jsp"),
			// 汇总整理分析
			@Result(name = "draft_deal_net_deal__GATHER", location = "/legislation/draft_deal_net_deal__GATHER.jsp"),
			// 采纳意见发布
			@Result(name = "draft_deal_net_deal__RESULT", location = "/legislation/draft_deal_net_deal__RESULT.jsp"),
            
			// 立法听证会发起
			@Result(name = "draft_deal_hearing_start", location = "/legislation/draft_deal_hearing_start.jsp"),
			// 立法听证会接收
			@Result(name = "draft_deal_hearing_deal__TODO", location = "/legislation/draft_deal_hearing_deal__TODO.jsp"),
			// 立法听证会送审
			@Result(name = "draft_deal_hearing_deal__SEND", location = "/legislation/draft_deal_hearing_deal__SEND.jsp"),
			// 立法听证会上网发布
			@Result(name = "draft_deal_hearing_deal__PUBLISH", location = "/legislation/draft_deal_hearing_deal__PUBLISH.jsp"),
			// 立法听证会网上报名
			@Result(name = "draft_deal_hearing_deal__GATHER", location = "/legislation/draft_deal_hearing_deal__GATHER.jsp"),
			// 立法听证会结果归档
			@Result(name = "draft_deal_hearing_deal__RESULT", location = "/legislation/draft_deal_hearing_deal__RESULT.jsp"),
	
			@Result(name = "openDraftTextPage", location = "/legislation/openDraftTextPage.jsp"),
		    
			@Result(name = "check_meeting__TODO", location = "/checkmeeting/checkmeeting_add.jsp"),
			
			@Result(name = "check_meeting__FEEDBACK", location = "/checkmeeting/checkmeeting_feedback.jsp"),
			
			@Result(name = "check_meeting__INPUT", location = "/checkmeeting/checkmeeting_input.jsp"),
			
			@Result(name = "check_meeting__AFFIRM", location = "/checkmeeting/checkmeeting_affirm.jsp"),

			// 专家论证会发起
			@Result(name = "draft_deal_expert_start", location = "/legislation/draft_deal_expert_start.jsp"),
			// 专家论证会处理
			@Result(name = "draft_deal_expert_deal", location = "/legislation/draft_deal_expert_deal.jsp"),

			// -----------以下是部门会签--------
			// 部门会签编辑
			@Result(name = "draft_deal_deptsign_start", location = "/legislation/draft_deal_deptsign_start.jsp"),
			// 部门会签盖章
			@Result(name = "draft_deal_deptsign_deal", location = "/legislation/draft_deal_deptsign_deal.jsp"),
			// 部门会签发送部门
			@Result(name = "draft_deal_deptsign_sign", location = "/legislation/draft_deal_deptsign_sign.jsp"),
			// 部门会签结果反馈接收
			@Result(name = "draft_deal_deptsign_input", location = "/legislation/draft_deal_deptsign_input.jsp"),
			
			@Result(name = "openOnlineDemonstrationPage", location = "/legislation/legislationProcessManager_onlineDemonstration.jsp"), @Result(name = "openOnlineInfoPage", location = "/legislation/legislationProcessManager_onlineInfo.jsp"), @Result(name = "openOnlineSealPage", location = "/legislation/legislationProcessManager_onlineSeal.jsp"), @Result(name = "openOnlineCensorshipPage", location = "/legislation/legislationProcessManager_onlineCensorship.jsp"),
			@Result(name = "openOnlineReleasePage", location = "/legislation/legislationProcessManager_onlineRelease.jsp"), @Result(name = "openOnlineSummaryPage", location = "/legislation/legislationProcessManager_onlineSummary.jsp"), @Result(name = "openOnlinePublishPage", location = "/legislation/legislationProcessManager_onlinePublish.jsp"), @Result(name = "openOnlineSummaryInfoPage", location = "/legislation/legislationProcessManager_onlineSummaryInfo.jsp"),
			@Result(name = "openOnlinePublishPage", location = "/legislation/legislationProcessManager_onlineSummaryInfo.jsp"),

			@Result(name = "draftReportStart", location = "/legislation/legislationProcessManager_draftReportStart.jsp"), @Result(name = "draftReportAudit", location = "/legislation/legislationProcessManager_draftReportAudit.jsp"), @Result(name = "openDepartmentSeekPage", location = "/legislation/legislationProcessManager_unitSeek.jsp"), @Result(name = "openDepartmentDemonstrationPage", location = "/legislation/legislationProcessManager_departmentDemonstration.jsp"),
			@Result(name = "openDepartmentInfoPage", location = "/legislation/legislationProcessManager_departmentInfo.jsp"), @Result(name = "openDepartmentReceivePage", location = "/legislation/legislationProcessManager_unitReceive.jsp"), @Result(name = "openDepartmentAddOpinionPage", location = "/legislation/legislationProcessManager_departmentAddOpinion.jsp"),
			// 审核会议征询意见、审核会议意见反馈、待审核草案
			@Result(name = "draftPromeetInfo", location = "/legislation/draft_promeet_info_audit.jsp"),
			@Result(name = "draftCheckmeetFeedback", location = "/legislation/draft_checkmeet_feedback.jsp"),
			
			@Result(name = "draft_promeet_info__TODO", location = "/legislation/draft_promeet_info_audit.jsp"),
			
			//发起常务会议，把草案对应到议题上，产生110的TODO
			@Result(name = "draft_citymeet_deal", location = "/legislation/draft_citymeet_deal.jsp"),

			//样本新增
			@Result(name = "openAddExamplePage", location = "/legislation/flowSampleForm.jsp"),
			//样本编辑
			@Result(name = "openEditExamplePage", location = "/legislation/flowSampleForm.jsp"),
			//样本查看
			@Result(name = "openExampleInfoPage", location = "/legislation/flowSampleForm.jsp")
	
	})

	public String legislationProcessDoc_form() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}

	/**
	 * 展示办理流程图
	 * 
	 * @return
	 */
	private String openProcessIndexPage() {
		String stDocId = request.getParameter("stDocId");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId = currentPerson.getTeamInfos().get(0).getId();
		// List<LegislationProcessTask>
		// unitEditList=legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId,stNodeId);
		LegislationProcessDoc docInfo = legislationProcessDocService.findById(stDocId);
		request.setAttribute("docInfo", docInfo);
		request.setAttribute("requestUrl", request.getRequestURI());
		return pageController();
	}
	
	
	private String legislation_saple_flow() {
		LegislationProcessDoc docInfo = new LegislationProcessDoc();
		request.setAttribute("docInfo", docInfo);
		request.setAttribute("requestUrl", request.getRequestURI());
		return pageController();
	}
	
	
	private String sampleList() {
		return pageController();
	}

	private String sampleTable() {
		String[] stNodeId = request.getParameter("stNodeId").split("__");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", stNodeId[0]);
		condMap.put("stStatus", "USED");
		if(stNodeId.length>1){
			condMap.put("stNodeStatus", stNodeId[1]);
		}
		List<LegislationExample> legislationExampleFilesList = legislationExampleService.findByList(condMap, sortMap);
		request.setAttribute("legislationExampleFilesList", legislationExampleFilesList);
		return pageController();
	}

	// 流程图页面上，ajax加载当前草案的各节点的信息 lj
	private String openProcessIndexPage_ajax() throws IOException {
		String stDocId = request.getParameter("stDocId");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRole = (String) session.getAttribute("userRole");
		String teamId = currentPerson.getTeamInfos().get(0).getId();
		// List<LegislationProcessTask>
		// unitEditList=legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId,stNodeId);

		JSONObject retJson = new JSONObject();
		JSONArray nodeInfoArray = new JSONArray();
		
		Map<String, Object> condMap1 = new HashMap<>();
		Map<String, String> sortMap1 = new HashMap<>();
		condMap1.put("stDocSource", stDocId);
		List<LegislationCheckmeeting> findByList = legislationCheckmeetingService.findByList(condMap1, sortMap1);
		String stMeetingId="";
		if(findByList.size()>0) {
			  LegislationCheckmeeting legislationCheckmeeting = findByList.get(0);
			   stMeetingId = legislationCheckmeeting.getStMeetingId();
		  }
		condMap1.clear();
		sortMap1.clear();
		condMap1.put("stMeetingId", stMeetingId);
		List<LegislationCheckmeetingTask> findByList2 = legislationCheckmeetingTaskService.findByList(condMap1, sortMap1);
		String stTaskStatus="";
		String stNodeId1="";
		if(findByList2.size()>0) {
			LegislationCheckmeetingTask legislationCheckmeetingTask = findByList2.get(0);
			stTaskStatus= legislationCheckmeetingTask.getStTaskStatus();
			stNodeId1 = legislationCheckmeetingTask.getStNodeId();
		WegovSimpleNode node1 = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId='" + stNodeId1 + "'").get(0);	
		JSONObject nodeChange1 = new JSONObject();
		if (node1.getStDoneName().split("#").length > 2) {
			nodeChange1.put("node", stNodeId1);
			nodeChange1.put("colorSet", "bcg_blue");
			nodeChange1.put("nodeHref", node1.getStInfoUrl());
			nodeInfoArray.add(nodeChange1);
			System.out.println("此节点是多个状态!" + node1.getStDoneName());
			String nodeStatusArr[] = node1.getStDoneName().split("#");
			for (int i = 0; i < nodeStatusArr.length; i++) {
				nodeChange1 = new JSONObject();
				// 当前节点task状态和 simpleNode 表中的状态字段做比对
				if (stTaskStatus.equals(nodeStatusArr[i])) {
					System.out.println("each.getStTaskStatus()-------" + stTaskStatus);
					// 如果当前task的状态与节点状态一致
					nodeChange1.put("node", stNodeId1 + "__" + nodeStatusArr[i]);
					nodeChange1.put("colorSet", "bcg_green");
					// nodeChange.put("otherSet", "sdfesfwec");
					System.out.println(stNodeId1 + "__" + nodeStatusArr[i] + "[color]：" + "bcg_green");
					// 当前正在做的，用权限控制一下 如果权限表里 处室单位不为空，并且处室单位 包含这个处室
					if (node1.getStSubmitRole() != null && (node1.getStSubmitRole().indexOf(userRole) >= 0)) {
						nodeChange1.put("nodeHref", node1.getStInfoUrl() + "__" + nodeStatusArr[i]);
						System.out.println(stNodeId1 + "__" + nodeStatusArr[i] + "[URL]：" + node1.getStInfoUrl() + "__" + nodeStatusArr[i]);
					}
					nodeInfoArray.add(nodeChange1);
					break;// 等于后，后面没出来的状态就不要了
				} else {
					System.out.println(stNodeId1 + "__" + nodeStatusArr[i] + "[color]：" + "bcg_blue");
					nodeChange1.put("node", stNodeId1 + "__" + nodeStatusArr[i]);
					nodeChange1.put("colorSet", "bcg_blue");
					// nodeChange.put("nodeHref", node.getStInfoUrl() + "__"
					// + nodeStatusArr[i]);
					// 当前正在做的，用权限控制一下 这里注释一下 只有绿色的需要控制 蓝色的可以打开
					// 2019年4月10日15:14:45 sy
					// if (node.getStSubmitRole() != null &&
					// (node.getStSubmitRole().indexOf(userRole) >= 0)) {
					nodeChange1.put("nodeHref", node1.getStInfoUrl() + "__" + nodeStatusArr[i]);
					System.out.println(stNodeId1 + "__" + nodeStatusArr[i] + "[URL]：" + node1.getStInfoUrl() + "__" + nodeStatusArr[i]);
					// }
					nodeInfoArray.add(nodeChange1);
				}
			}
		}
		
	}
		
		
		// 找出当前草案下的所有任务，设置到节点的状态上
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stDocId", stDocId);
		// 获得草案对象
		LegislationProcessDoc docInfo = legislationProcessDocService.findById(stDocId);
		// 获得草案主流程task的信息
		List<LegislationProcessTask> taskList = legislationProcessTaskService.findByList(condMap, sortMap);
		String allNodeId = "";
		allNodeId += stNodeId1 + "#";
		for (LegislationProcessTask each : taskList) {
			WegovSimpleNode node = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId='" + each.getStNodeId() + "'").get(0);
			String statusColor = "";
			statusColor = "bcg_blue";
			allNodeId += each.getStNodeId() + "#";
			JSONObject nodeChange = new JSONObject();
			System.out.println("this is not done node!!!!!");
			// 如果是多状态情况
			if (node.getStDoneName().split("#").length > 2) {
				System.out.println("此节点是多个状态!" + node.getStDoneName());
				String nodeStatusArr[] = node.getStDoneName().split("#");
				for (int i = 0; i < nodeStatusArr.length; i++) {
					nodeChange = new JSONObject();
					// 当前节点task状态和 simpleNode 表中的状态字段做比对
					if (each.getStTaskStatus().equals(nodeStatusArr[i])) {
						System.out.println("each.getStTaskStatus()-------" + each.getStTaskStatus());
						// 如果当前task的状态与节点状态一致
						nodeChange.put("node", each.getStNodeId() + "__" + nodeStatusArr[i]);
						nodeChange.put("colorSet", "bcg_green");
						// nodeChange.put("otherSet", "sdfesfwec");
						System.out.println(each.getStNodeId() + "__" + nodeStatusArr[i] + "[color]：" + "bcg_green");
						// 当前正在做的，用权限控制一下 如果权限表里 处室单位不为空，并且处室单位 包含这个处室
						if (node.getStSubmitRole() != null && (node.getStSubmitRole().indexOf(userRole) >= 0)) {
							nodeChange.put("nodeHref", node.getStInfoUrl() + "__" + nodeStatusArr[i]);
							System.out.println(each.getStNodeId() + "__" + nodeStatusArr[i] + "[URL]：" + node.getStInfoUrl() + "__" + nodeStatusArr[i]);
						}
						nodeInfoArray.add(nodeChange);
						break;// 等于后，后面没出来的状态就不要了
					} else {
						System.out.println(each.getStNodeId() + "__" + nodeStatusArr[i] + "[color]：" + "bcg_blue");
						nodeChange.put("node", each.getStNodeId() + "__" + nodeStatusArr[i]);
						nodeChange.put("colorSet", "bcg_blue");
						// nodeChange.put("nodeHref", node.getStInfoUrl() + "__"
						// + nodeStatusArr[i]);
						// 当前正在做的，用权限控制一下 这里注释一下 只有绿色的需要控制 蓝色的可以打开
						// 2019年4月10日15:14:45 sy
						// if (node.getStSubmitRole() != null &&
						// (node.getStSubmitRole().indexOf(userRole) >= 0)) {
						nodeChange.put("nodeHref", node.getStInfoUrl() + "__" + nodeStatusArr[i]);
						System.out.println(each.getStNodeId() + "__" + nodeStatusArr[i] + "[URL]：" + node.getStInfoUrl() + "__" + nodeStatusArr[i]);
						// }
						nodeInfoArray.add(nodeChange);
					}
				}
			} else {
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

			// WegovSimpleNode
			// node=wegovSimpleNodeService.findById(each.getStNodeId());
			// String nodeUrl="url_empty";
			// if(node.getStInfoUrl()==null || "".equals(node.getStInfoUrl()))
			// nodeUrl=node.getStInfoUrl();
			// System.out.println(each.getStNodeId() + "url：" +
			// node.getStInfoUrl());
		}

		// 找出所有的启动节点，根据当前人的角色进行设置
		JSONArray nodeStartInfoArray = new JSONArray();
		JSONObject nodeStartInfo = new JSONObject();
		// String userRole = (String) session.getAttribute("userRole");//
		Map startNode = new HashMap();
		List<WegovSimpleNode> startNodeList = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stFlowName='立法过程' and t.stStart='START' and t.stSubmitRole='" + userRole + "'");
		for (WegovSimpleNode nodeInfo : startNodeList) {
			if (allNodeId.indexOf(nodeInfo.getStNodeId()) < 0) {
				nodeStartInfo = new JSONObject();
				String node = nodeInfo.getStNodeId();
				String nodeHref = nodeInfo.getStInfoUrl();
				System.out.println(nodeInfo.getStNodeId() + "--启动节点---" + nodeInfo.getStInfoUrl());
				nodeStartInfo.put("node", node);
				nodeStartInfo.put("nodeHref", nodeHref);
				// System.out.println(each.getStNodeId() + "__" +
				// nodeStatusArr[i] + "[URL]：" +
				// node.getStInfoUrl() + "__" + nodeStatusArr[i]);
				nodeStartInfoArray.add(nodeStartInfo);

			}
		}

		// nodeStartInfo.put("node", "NOD_0000000130");
		// nodeStartInfo.put("nodeHref", "draft_deal_deptsign_start");
		// nodeInfoArray.add(nodeStartInfo);

		System.out.println("当前草案下的任务数：" + taskList.size());
		System.out.println("这些任务的NodeIds：" + allNodeId);

		// 如果某个流程走完了 状态是done 也是可以看到每个节点信息

		// 返回
		retJson.put("success", true);
		retJson.put("nodeInfoArray", nodeInfoArray);// 当前草案下的任务节点信息
		retJson.put("nodeStartInfoArray", nodeStartInfoArray);// 所有还没有开始的启动节点的信息
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(retJson);
		return null;
	}

	/**
	 * 跳转立法办理页面
	 * 
	 * @return
	 */
	@Deprecated
	private String openProcessIndexPage_old() {
		String unitEditClass = "cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
		Boolean unitEditDisabled = true;
		String unitSealClass = "cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_yellow";
		Boolean unitSealDisabled = true;
		String unitSendClass = "cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_red";
		Boolean unitSendDisabled = true;
		String unitReceiveClass = "cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red";
		Boolean unitReceiveDisabled = true;
		String expertBeforeClass = "cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
		Boolean expertBeforeDisabled = true;
		String expertAfterClass = "cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_t";
		Boolean expertAfterDisabled = true;
		String legislationEditClass = "cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
		Boolean legislationEditDisabled = true;
		String legislationReceiveClass = "cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red";
		Boolean legislationReceiveDisabled = true;
		String legislationCensorshipClass = "cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow";
		Boolean legislationCensorshipDisabled = true;
		String legislationReleaseClass = "cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple";
		Boolean legislationReleaseDisabled = true;
		String legislationRegistrationClass = "cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple";
		Boolean legislationRegistrationDisabled = true;
		String legislationFileClass = "cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_t";
		Boolean legislationFileDisabled = true;
		String onlineEditClass = "cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
		Boolean onlineEditDisabled = true;
		String onlineSealClass = "cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red";
		Boolean onlineSealDisabled = true;
		String onlineCensorshipClass = "cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow";
		Boolean onlineCensorshipDisabled = true;
		String onlineReleaseClass = "cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple";
		Boolean onlineReleaseDisabled = true;
		String onlineSummaryClass = "cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple";
		Boolean onlineSummaryDisabled = true;
		String onlinePublishClass = "cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_purple";
		Boolean onlinePublishDisabled = true;
		String departmentEditClass = "cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
		Boolean departmentEditDisabled = true;
		String departmentSealClass = "cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_yellow";
		Boolean departmentSealDisabled = true;
		String departmentSendClass = "cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_red";
		Boolean departmentSendDisabled = true;
		String departmentReceiveClass = "cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red";
		Boolean departmentReceiveDisabled = true;
		String stDocId = request.getParameter("stDocId");
		String stDocName = request.getParameter("stDocName");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId = currentPerson.getTeamInfos().get(0).getId();
		String stNodeId = "NOD_0000000120";
		List<LegislationProcessTask> unitEditList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (unitEditList.size() > 0) {
			if ("TODO".equals(unitEditList.get(0).getStTaskStatus())) {
				if ("U_3_7".equals(teamId)) {
					unitEditClass = "cell row_items row_item2 bcg_green border_width border_style border_radius border_color_t";
					unitEditDisabled = false;
				}
			} else {
				unitEditClass = "cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t";
				unitSealClass = "cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_yellow";
				if ("U_3_7".equals(teamId) || "U_3_1".equals(teamId)) {
					unitEditDisabled = false;
				}
			}
		} else {
			if ("U_3_7".equals(teamId)) {
				unitEditClass = "cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
				unitEditDisabled = false;
			}
		}
		stNodeId = "NOD_0000000121";
		List<LegislationProcessTask> unitSendList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (unitSendList.size() > 0) {
			if ("TODO".equals(unitSendList.get(0).getStTaskStatus())) {
				if ("U_3_1".equals(teamId)) {
					unitSendClass = "cell row_items row_item4 bcg_green border_width border_style border_radius border_color_red";
					unitSendDisabled = false;
				}
			} else {
				unitSendClass = "cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_red";
				if ("U_3_7".equals(teamId) || "U_3_1".equals(teamId)) {
					unitReceiveDisabled = false;
				}
			}
		}
		stNodeId = "NOD_0000000122";
		List<LegislationProcessTask> unitReceiveList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		List<LegislationProcessTask> unitReceiveFinishList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null and t.stComment2 is null");
		if (unitReceiveList.size() > 0) {
			if (unitReceiveFinishList.size() > 0) {
				if ("U_3_7".equals(teamId) || "U_3_1".equals(teamId)) {
					unitReceiveClass = "cell row_items row_item5 bcg_green border_width border_style border_radius border_color_red";
				}
			} else {
				unitReceiveClass = "cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_red";
			}
			for (LegislationProcessTask legislationProcessTask : unitReceiveList) {
				if (legislationProcessTask.getStParentId() != null && teamId.equals(legislationProcessTask.getStParentId())) {
					if (legislationProcessTask.getStComment2() != null) {
						unitReceiveClass = "cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_red";
					} else {
						unitReceiveClass = "cell row_items row_item5 bcg_green border_width border_style border_radius border_color_red";
					}
					unitReceiveDisabled = false;
				}
			}
		}
		stNodeId = "NOD_0000000150";
		List<LegislationProcessTask> expertBeforeList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (expertBeforeList.size() > 0) {
			if ("TODO".equals(expertBeforeList.get(0).getStTaskStatus())) {
				if ("U_3_7".equals(teamId)) {
					expertBeforeClass = "cell row_items row_item2 bcg_green border_width border_style border_radius border_color_t";
				}
			} else {
				expertBeforeClass = "cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t";
			}
			if ("U_3_7".equals(teamId)) {
				expertBeforeDisabled = false;
			}
		} else {
			if ("U_3_7".equals(teamId)) {
				expertBeforeDisabled = false;
			}
		}
		stNodeId = "NOD_0000000151";
		List<LegislationProcessTask> expertAfterList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (expertAfterList.size() > 0) {
			if ("TODO".equals(expertAfterList.get(0).getStTaskStatus())) {
				if ("U_3_7".equals(teamId)) {
					expertAfterClass = "cell row_items row_item3 bcg_green border_width border_style border_radius border_color_t";
				}
			} else {
				expertAfterClass = "cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_t";
			}
			if ("U_3_7".equals(teamId)) {
				expertAfterDisabled = false;
			}
		}
		stNodeId = "NOD_0000000140";
		List<LegislationProcessTask> legislationEditList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationEditList.size() > 0) {
			if ("TODO".equals(legislationEditList.get(0).getStTaskStatus())) {
				if ("U_3_7".equals(teamId)) {
					legislationEditClass = "cell row_items row_item2 bcg_green border_width border_style border_radius border_color_t";
					legislationEditDisabled = false;
				}
			} else {
				legislationEditClass = "cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t";
				if ("U_3_7".equals(teamId) || "U_3_1".equals(teamId)) {
					legislationEditDisabled = false;
				}
			}
		} else {
			if ("U_3_7".equals(teamId)) {
				legislationEditDisabled = false;
			}
		}
		stNodeId = "NOD_0000000141";
		List<LegislationProcessTask> legislationReceiveList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationReceiveList.size() > 0) {
			String stTaskStatus = legislationReceiveList.get(0).getStTaskStatus();
			if ("TODO".equals(stTaskStatus)) {
				if ("U_3_1".equals(teamId)) {
					legislationReceiveClass = "cell row_items row_item3 bcg_green border_width border_style border_radius border_color_red";
					legislationReceiveDisabled = false;
				}
			} else {
				legislationReceiveClass = "cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_red";
				if ("SEND".equals(stTaskStatus)) {
					if ("U_3_1".equals(teamId)) {
						legislationCensorshipClass = "cell row_items row_item4 bcg_green border_width border_style border_radius border_color_yellow";
						legislationCensorshipDisabled = false;
					}
				} else {
					legislationCensorshipClass = "cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_yellow";
					if ("PUBLISH".equals(stTaskStatus)) {
						if ("U_3_1".equals(teamId)) {
							legislationReleaseClass = "cell row_items row_item5 bcg_green border_width border_style border_radius border_color_purple";
							legislationReleaseDisabled = false;
							legislationCensorshipDisabled = false;
						}
					} else {
						legislationReleaseClass = "cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_purple";
						if ("GATHER".equals(stTaskStatus)) {
							if ("U_3_1".equals(teamId)) {
								legislationRegistrationClass = "cell row_items row_item6 bcg_green border_width border_style border_radius border_color_purple";
								legislationRegistrationDisabled = false;
								legislationCensorshipDisabled = false;
								legislationReleaseDisabled = false;
							}
						} else {
							legislationRegistrationClass = "cell row_items row_item6 bcg_blue border_width border_style border_radius border_color_purple";
							if ("RESULT".equals(stTaskStatus)) {
								if ("U_3_1".equals(teamId)) {
									legislationFileClass = "cell row_items row_item7 bcg_green border_width border_style border_radius border_color_t";
									legislationFileDisabled = false;
									legislationRegistrationDisabled = false;
									legislationCensorshipDisabled = false;
									legislationReleaseDisabled = false;
								}
							} else {
								legislationFileClass = "cell row_items row_item7 bcg_blue border_width border_style border_radius border_color_t";
								if ("U_3_1".equals(teamId)) {
									legislationFileDisabled = false;
									legislationRegistrationDisabled = false;
									legislationCensorshipDisabled = false;
									legislationReleaseDisabled = false;
								}
								if ("U_3_7".equals(teamId)) {
									legislationFileDisabled = false;
								}
							}
						}
					}
				}
			}
		}
		stNodeId = "NOD_0000000130";
		List<LegislationProcessTask> onlineEditList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (onlineEditList.size() > 0) {
			if ("TODO".equals(onlineEditList.get(0).getStTaskStatus())) {
				if ("U_3_7".equals(teamId)) {
					onlineEditClass = "cell row_items row_item2 bcg_green border_width border_style border_radius border_color_t";
					onlineEditDisabled = false;
				}
			} else {
				onlineEditClass = "cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t";
				if ("U_3_7".equals(teamId) || "U_3_1".equals(teamId)) {
					onlineEditDisabled = false;
				}
			}
		} else {
			if ("U_3_7".equals(teamId)) {
				onlineEditDisabled = false;
			}
		}
		stNodeId = "NOD_0000000131";
		List<LegislationProcessTask> onlineSealList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (onlineSealList.size() > 0) {
			String stTaskStatus = onlineSealList.get(0).getStTaskStatus();
			if ("TODO".equals(stTaskStatus)) {
				if ("U_3_1".equals(teamId)) {
					onlineSealClass = "cell row_items row_item3 bcg_green border_width border_style border_radius border_color_red";
					onlineSealDisabled = false;
				}
			} else {
				onlineSealClass = "cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_red";
				if ("SEND".equals(stTaskStatus)) {
					if ("U_3_1".equals(teamId)) {
						onlineCensorshipClass = "cell row_items row_item4 bcg_green border_width border_style border_radius border_color_yellow";
						onlineCensorshipDisabled = false;
					}
				} else {
					onlineCensorshipClass = "cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_yellow";
					if ("PUBLISH".equals(stTaskStatus)) {
						if ("U_3_1".equals(teamId)) {
							onlineReleaseClass = "cell row_items row_item5 bcg_green border_width border_style border_radius border_color_purple";
							onlineReleaseDisabled = false;
							onlineCensorshipDisabled = false;
						}
					} else {
						onlineReleaseClass = "cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_purple";
						if ("GATHER".equals(stTaskStatus)) {
							if ("U_3_1".equals(teamId)) {
								onlineSummaryClass = "cell row_items row_item6 bcg_green border_width border_style border_radius border_color_purple";
								onlineSummaryDisabled = false;
								onlineCensorshipDisabled = false;
								onlineReleaseDisabled = false;
							}
						} else {
							onlineSummaryClass = "cell row_items row_item6 bcg_blue border_width border_style border_radius border_color_purple";
							if ("RESULT".equals(stTaskStatus)) {
								if ("U_3_1".equals(teamId)) {
									onlinePublishClass = "cell row_items row_item7 bcg_green border_width border_style border_radius border_color_purple";
									onlinePublishDisabled = false;
									onlineSummaryDisabled = false;
									onlineCensorshipDisabled = false;
									onlineReleaseDisabled = false;
								}
							} else {
								onlinePublishClass = "cell row_items row_item7 bcg_blue border_width border_style border_radius border_color_purple";
								if ("U_3_1".equals(teamId)) {
									onlinePublishDisabled = false;
									onlineSummaryDisabled = false;
									onlineCensorshipDisabled = false;
									onlineReleaseDisabled = false;
								}
								if ("U_3_7".equals(teamId)) {
									onlinePublishDisabled = false;
								}
							}
						}
					}
				}
			}
		}
		stNodeId = "NOD_0000000160";
		List<LegislationProcessTask> departmentEditList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (departmentEditList.size() > 0) {
			if ("TODO".equals(departmentEditList.get(0).getStTaskStatus())) {
				if ("U_3_7".equals(teamId)) {
					departmentEditClass = "cell row_items row_item2 bcg_green border_width border_style border_radius border_color_t";
					departmentEditDisabled = false;
				}
			} else {
				departmentEditClass = "cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t";
				departmentSealClass = "cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_yellow";
				if ("U_3_7".equals(teamId) || "U_3_1".equals(teamId)) {
					departmentEditDisabled = false;
				}
			}
		} else {
			if ("U_3_7".equals(teamId)) {
				departmentEditClass = "cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
				departmentEditDisabled = false;
			}
		}
		stNodeId = "NOD_0000000161";
		List<LegislationProcessTask> departmentSendList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (departmentSendList.size() > 0) {
			if ("TODO".equals(departmentSendList.get(0).getStTaskStatus())) {
				if ("U_3_1".equals(teamId)) {
					departmentSendClass = "cell row_items row_item4 bcg_green border_width border_style border_radius border_color_red";
					departmentSendDisabled = false;
				}
			} else {
				departmentSendClass = "cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_red";
				if ("U_3_7".equals(teamId) || "U_3_1".equals(teamId)) {
					departmentReceiveDisabled = false;
				}
			}
		}
		stNodeId = "NOD_0000000162";
		List<LegislationProcessTask> departmentReceiveList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		List<LegislationProcessTask> departmentReceiveFinishList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null and t.stComment2 is null");
		if (departmentReceiveList.size() > 0) {
			if (departmentReceiveFinishList.size() > 0) {
				if ("U_3_7".equals(teamId) || "U_3_1".equals(teamId)) {
					departmentReceiveClass = "cell row_items row_item5 bcg_green border_width border_style border_radius border_color_red";
				}
			} else {
				departmentReceiveClass = "cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_red";
			}
			for (LegislationProcessTask legislationProcessTask : departmentReceiveList) {
				if (legislationProcessTask.getStParentId() != null && teamId.equals(legislationProcessTask.getStParentId())) {
					if (legislationProcessTask.getStComment2() != null) {
						departmentReceiveClass = "cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_red";
					} else {
						departmentReceiveClass = "cell row_items row_item5 bcg_green border_width border_style border_radius border_color_red";
					}
					departmentReceiveDisabled = false;
				}
			}
		}
		request.setAttribute("stDocName", stDocName);
		request.setAttribute("unitEditClass", unitEditClass);
		request.setAttribute("unitEditDisabled", unitEditDisabled);
		request.setAttribute("unitSealClass", unitSealClass);
		request.setAttribute("unitSealDisabled", unitSealDisabled);
		request.setAttribute("unitSendClass", unitSendClass);
		request.setAttribute("unitSendDisabled", unitSendDisabled);
		request.setAttribute("unitReceiveClass", unitReceiveClass);
		request.setAttribute("unitReceiveDisabled", unitReceiveDisabled);

		request.setAttribute("expertBeforeClass", expertBeforeClass);
		request.setAttribute("expertBeforeDisabled", expertBeforeDisabled);
		request.setAttribute("expertAfterClass", expertAfterClass);
		request.setAttribute("expertAfterDisabled", expertAfterDisabled);

		request.setAttribute("legislationEditClass", legislationEditClass);
		request.setAttribute("legislationEditDisabled", legislationEditDisabled);
		request.setAttribute("legislationReceiveClass", legislationReceiveClass);
		request.setAttribute("legislationReceiveDisabled", legislationReceiveDisabled);
		request.setAttribute("legislationCensorshipClass", legislationCensorshipClass);
		request.setAttribute("legislationCensorshipDisabled", legislationCensorshipDisabled);
		request.setAttribute("legislationReleaseClass", legislationReleaseClass);
		request.setAttribute("legislationReleaseDisabled", legislationReleaseDisabled);
		request.setAttribute("legislationRegistrationClass", legislationRegistrationClass);
		request.setAttribute("legislationRegistrationDisabled", legislationRegistrationDisabled);
		request.setAttribute("legislationFileClass", legislationFileClass);
		request.setAttribute("legislationFileDisabled", legislationFileDisabled);

		request.setAttribute("onlineEditClass", onlineEditClass);
		request.setAttribute("onlineEditDisabled", onlineEditDisabled);
		request.setAttribute("onlineSealClass", onlineSealClass);
		request.setAttribute("onlineSealDisabled", onlineSealDisabled);
		request.setAttribute("onlineCensorshipClass", onlineCensorshipClass);
		request.setAttribute("onlineCensorshipDisabled", onlineCensorshipDisabled);
		request.setAttribute("onlineReleaseClass", onlineReleaseClass);
		request.setAttribute("onlineReleaseDisabled", onlineReleaseDisabled);
		request.setAttribute("onlineSummaryClass", onlineSummaryClass);
		request.setAttribute("onlineSummaryDisabled", onlineSummaryDisabled);
		request.setAttribute("onlinePublishClass", onlinePublishClass);
		request.setAttribute("onlinePublishDisabled", onlinePublishDisabled);

		request.setAttribute("departmentEditClass", departmentEditClass);
		request.setAttribute("departmentEditDisabled", departmentEditDisabled);
		request.setAttribute("departmentSealClass", departmentSealClass);
		request.setAttribute("departmentSealDisabled", departmentSealDisabled);
		request.setAttribute("departmentSendClass", departmentSendClass);
		request.setAttribute("departmentSendDisabled", departmentSendDisabled);
		request.setAttribute("departmentReceiveClass", departmentReceiveClass);
		request.setAttribute("departmentReceiveDisabled", departmentReceiveDisabled);
		return pageController();
	}

	/**
	 * 跳转草案新增页面
	 * 
	 * @return
	 */
	private String openAddPage() {
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		return pageController();
	}

	/**
	 * 跳转草案详情页
	 * 
	 * @return
	 */
	private String draft_create_info() {
		String stDocId = request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);

		List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + stDocId + "' and t.stNodeId='NOD_0000000101' and t.stSampleId !='null' order by t.stSampleId ");
		List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + stDocId + "' and t.stNodeId='NOD_0000000101' and t.stSampleId='null' order by t.stFileId ");
		for (LegislationFiles legislationFiles : otherDocList) {
			docList.add(legislationFiles);
		}
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		request.setAttribute("docList", docList);
		return pageController();
	}

	private String openHeartMeetingCheckInfoPage() {
		String stDocId = request.getParameter("stDocId");

		request.setAttribute("checkDetails", queryDetails(stDocId, "NOD_0000000141"));
		return pageController();
	}

	private String openProMeetCheckInfoPage() {
		String stDocId = request.getParameter("stDocId");

		request.setAttribute("checkDetails", queryDetails(stDocId, "NOD_0000000104"));
		return pageController();
	}

	private List<LegislationProcessTaskdetail> queryDetails(String stDocId, String stNodeId) {
		List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
		List<LegislationProcessTask> list = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (list != null && list.size() > 0) {
			String stTaskId = list.get(0).getStTaskId();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='" + stTaskId + "' order by t.stTaskdetailId");
			for (LegislationProcessTaskdetail legislationProcessTaskdetail : taskDetails) {
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='" + legislationProcessTaskdetail.getStTaskdetailId() + "'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
		}
		return checkDetails;
	}
	
	
	private String openProMeetPage() {
		String stTaskStatus = request.getParameter("stTaskStatus");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");

		List<LegislationProcessTask> list = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		LegislationProcessTask legislationProcessTask = list.get(0);
		request.setAttribute("stTaskStatus", stTaskStatus);
		request.setAttribute("nodeId", legislationProcessTask.getStNodeId());
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("stTaskId", legislationProcessTask.getStTaskId());
		request.setAttribute("requestUrl", request.getRequestURI());
		return "openCheckExplainPage";
	}

	private String openCheckExplainPage() {
		String stTaskStatus = request.getParameter("stTaskStatus");
		request.setAttribute("stTaskStatus", stTaskStatus);
		return pageController();
	}

	/**
	 * 跳转草案修改页面
	 * 
	 * @return
	 */
	private String openEditPage() {
		String stNodeId = request.getParameter("stNodeId");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		String stDocId = request.getParameter("stDocId");
		condMap.put("stParentId", stDocId);
		condMap.put("stNodeId", stNodeId);
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		request.setAttribute("LegislationExampleList", legislationExampleFilesList);
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return pageController();
	}

	/**
	 * 跳转草案历史页面
	 * 
	 * @return
	 */
	private String openDraftHistoryPage() {
		String stDocId = request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stDocId", stDocId);
		sortMap.put("stDealId", "ASC");
		List<LegislationProcessDeal> dealList = legislationProcessDealService.findByList(condMap, sortMap);
		List<Map> legislationDeal = new ArrayList<Map>();
		for (int i = 0; i < dealList.size(); i++) {
			Map<String, Object> filesMap = new HashMap<>();
			filesMap.put("stActionName", dealList.get(i).getStActionName());
			filesMap.put("stBakOne", dealList.get(i).getStBakOne());
			filesMap.put("stUserName", dealList.get(i).getStUserName());
			filesMap.put("dtDealDate", dealList.get(i).getDtDealDate());
			List<LegislationFiles> docList = new ArrayList<>();
			if (dealList.get(i).getStActionId().startsWith("FIL")) {
				LegislationFiles legislationFiles = legislationFilesService.findById(dealList.get(i).getStActionId());
				docList.add(legislationFiles);
			} else {
				docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + stDocId + "' and t.stNodeId='" + dealList.get(i).getStActionId() + "' and t.stSampleId is not null order by t.stSampleId ");
				List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + stDocId + "' and t.stNodeId='" + dealList.get(i).getStActionId() + "' and t.stSampleId is null order by t.stFileId ");
				for (LegislationFiles legislationFiles : otherDocList) {
					docList.add(legislationFiles);
				}
			}
			filesMap.put("docList", docList);
			legislationDeal.add(filesMap);
		}

		request.setAttribute("legislationDeal", legislationDeal);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		request.setAttribute("dealList", dealList);
		return pageController();
	}

	/**
	 * 跳转草案分办页面
	 * 
	 * @return
	 */
	private String openSeparatePage() {
		String stDocId = request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, "NOD_0000000102");
		for (LegislationProcessTask legislationProcessTask : list) {
			request.setAttribute("legislationProcessTask", legislationProcessTask);
		}
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
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

	private String openLeaderIdeaPage() {
		String stDocId = request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return pageController();
	}

	private String openEditMeetingPage() {
		return pageController();
	}

	private String openDemonstrationPage() {
		return pageController();
	}

	/**
	 * 办理页面--跳转网上征求意见发起页面openOnlineDemonstrationPage
	 * 
	 * @return
	 */
	private String draft_deal_net_start() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_net_start";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_net_start";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "draft_deal_net_start";
		}

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}
	
	
	
	/**
	 * 办理页面--跳转立法听证会发起页面
	 * 
	 * @return
	 */
	private String draft_deal_hearing_start() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_hearing_start";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_hearing_start";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "draft_deal_hearing_start";
		}

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 公开征求意见接收页面
	 * 
	 * @return
	 * @author sy
	 */
	private String draft_deal_net_deal__TODO() {
		String method = "draft_deal_net_deal__TODO";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好TODO，就是操作页。否则是只读页面
			if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_net_deal__TODO";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_net_deal__TODO";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}
		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTaskList.get(0).getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}
	
	
	/**
	 * 立法听证会接收页面
	 * 
	 * @return
	 * @author sy
	 */
	private String draft_deal_hearing_deal__TODO() {
		String method = "draft_deal_hearing_deal__TODO";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好TODO，就是操作页。否则是只读页面
			if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_hearing_deal__TODO";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_hearing_deal__TODO";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}
		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTaskList.get(0).getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}
	
	
	/**
	 * 立法听证会送审页面
	 * 
	 * @return
	 * @author 
	 */
	private String draft_deal_hearing_deal__SEND() {
		String method = "draft_deal_hearing_deal__SEND";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好SEND，就是操作页。否则是只读页面
			if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_hearing_deal__SEND";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_hearing_deal__SEND";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}
		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTaskList.get(0).getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}
	
	
	
	/**
	 * 立法听证会上网发布
	 * 
	 * @return
	 * @author 
	 */
	private String draft_deal_hearing_deal__PUBLISH() {
		String method = "draft_deal_hearing_deal__PUBLISH";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好SEND，就是操作页。否则是只读页面
			if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_hearing_deal__PUBLISH";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_hearing_deal__PUBLISH";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}
		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTaskList.get(0).getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 立法听证会网上报名汇总
	 * 
	 * @return
	 * @author sy
	 */
	private String draft_deal_hearing_deal__GATHER() {
		String method = "draft_deal_hearing_deal__GATHER";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好GATHER，就是操作页。否则是只读页面
			if ("GATHER".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_hearing_deal__GATHER";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_hearing_deal__GATHER";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}

		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTaskList.get(0).getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 立法听证会结果归档
	 * 
	 * @return
	 * @author sy
	 */
	private String draft_deal_hearing_deal__RESULT() {
		String method = "draft_deal_hearing_deal__RESULT";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];

		LegislationProcessTask legislationProcessTask = new LegislationProcessTask();

		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			legislationProcessTask = legislationProcessTaskList.get(0);
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好RESULT，就是操作页。否则是只读页面
			if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_hearing_deal__RESULT";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_hearing_deal__RESULT";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}

		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTask.getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 公开征求意见送审页面
	 * 
	 * @return
	 * @author sy
	 */
	private String draft_deal_net_deal__SEND() {
		String method = "draft_deal_net_deal__SEND";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好SEND，就是操作页。否则是只读页面
			if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_net_deal__SEND";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_net_deal__SEND";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}
		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTaskList.get(0).getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 网上征求意见上网发布页面
	 * 
	 * @return
	 * @author sy
	 */
	private String draft_deal_net_deal__PUBLISH() {
		String method = "draft_deal_net_deal__PUBLISH";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好SEND，就是操作页。否则是只读页面
			if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_net_deal__PUBLISH";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_net_deal__PUBLISH";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}
		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTaskList.get(0).getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 汇总整理分析
	 * 
	 * @return
	 * @author sy
	 */
	private String draft_deal_net_deal__GATHER() {
		String method = "draft_deal_net_deal__GATHER";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好GATHER，就是操作页。否则是只读页面
			if ("GATHER".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_net_deal__GATHER";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_net_deal__GATHER";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}

		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTaskList.get(0).getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 采纳意见发布
	 * 
	 * @return
	 * @author sy
	 */
	private String draft_deal_net_deal__RESULT() {
		String method = "draft_deal_net_deal__RESULT";
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String nodeStatus = nodeIdStatus[1];

		LegislationProcessTask legislationProcessTask = new LegislationProcessTask();

		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			legislationProcessTask = legislationProcessTaskList.get(0);
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 如果当前节点正好RESULT，就是操作页。否则是只读页面
			if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_net_deal__RESULT";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_net_deal__RESULT";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}

		// 页面元素是legislation_process_taskdetail 表数据 bg
		LegislationProcessTaskdetail legislationProcessTaskdetail = new LegislationProcessTaskdetail();
		Map<String, Object> taskdetailCondMap = new HashMap<>();
		Map<String, String> taskdetailSortMap = new HashMap<>();
		taskdetailCondMap.put("stTaskId", legislationProcessTask.getStTaskId());
		taskdetailCondMap.put("stTaskStatus", nodeStatus);
		List<LegislationProcessTaskdetail> taskdetailList = legislationProcessTaskdetailService.findByList(taskdetailCondMap, taskdetailSortMap);
		if (taskdetailList.size() > 0) {
			legislationProcessTaskdetail = taskdetailList.get(0);
			request.setAttribute("legislationProcessTaskdetail", legislationProcessTaskdetail);
		}
		// 页面元素是legislation_process_taskdetail 表数据 ed

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeStatus", nodeStatus);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	
	
	/**
	 * 专家论证会发起draft_deal_expert_start
	 * 
	 * @return
	 */
	private String draft_deal_expert_start() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_expert_start";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_expert_start";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "draft_deal_expert_start";
		}

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}
	
	/**
	 * 专家论证会处理draft_deal_expert_Result
	 * 
	 * @return
	 */
	private String draft_deal_expert_deal() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_expert_deal";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_expert_deal";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "draft_deal_expert_deal";
		}

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}
	/**
	 * 部门会签编辑draft_deal_deptsign_start
	 * 
	 * @return
	 */
	private String draft_deal_deptsign_start() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_deptsign_start";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_deptsign_start";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "draft_deal_deptsign_start";
		}
		
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}
	/**
	 * 部门会签盖章draft_deal_deptsign_deal
	 * 
	 * @return
	 */
	private String draft_deal_deptsign_deal() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "draft_deal_deptsign_deal";
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("stStyle", stStyle);
				method = "draft_deal_deptsign_deal";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "draft_deal_deptsign_deal";
		}
		
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}
	/**
	 * 部门会签发送部门draft_deal_deptsign_sign
	 * 
	 * @return
	 */
	private String draft_deal_deptsign_sign() {
		String method = request.getParameter("method");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId = (currentPerson.getTeamInfos().get(0)).getId();
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		LegislationProcessTask legislationProcessTask = legislationProcessTaskList.get(0);
		String stTaskId = legislationProcessTask.getStTaskId();
		// 发送部门id所保存在task表中的字段
		String stBakOne = legislationProcessTask.getStBakOne();
		String deptIds[] = null;
		List<TeamInfo> teamInfoList = new ArrayList<TeamInfo>();
		if (stBakOne != null && !stBakOne.isEmpty()) {
			// 拿到部门id
			deptIds = stBakOne.split(",");
		}
		if ("TODO".equals(legislationProcessTask.getStTaskStatus())) {
			// 任务进行中
			// 保存的已选择的部门id
			request.setAttribute("deptIds", deptIds);
			teamInfoList = teamInfoService.findTeamInfoInModuleByType("MODULE_LEGISLATE", "委办局");
			method = "draft_deal_deptsign_sign";
		} else {
			// 任务已完成
			// 已发送的部门
			for (String id : deptIds) {
				TeamInfo teamInfo = teamInfoService.findTeamInfoByTeamId("MODULE_LEGISLATE", id);
				teamInfoList.add(teamInfo);
			}
		}
		request.setAttribute("teamInfoList", teamInfoList);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return demonstrationPageController(method, stTaskId);}
	/**
	 * 部门会签结果反馈draft_deal_deptsign_input
	 * 
	 * @return
	 */
	private String draft_deal_deptsign_input() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		boolean allDone = true;
		String method = "draft_deal_deptsign_input";
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			// 得到所有意见录入task
			request.setAttribute("legislationProcessTaskList", legislationProcessTaskList);
			// 是否已全部确认
			for (LegislationProcessTask task : legislationProcessTaskList) {
				if ("TODO".equals(task.getStTaskStatus())) {
					allDone = false;
				}
			}
		}
		request.setAttribute("nodeId", "NOD_0000000122");
		request.setAttribute("allDone", allDone);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}
	
	
	/**
	 * 办理页面--跳转网上征求意见盖章页面
	 * 
	 * @return
	 */
	private String openOnlineSealPage() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", stDocId);
		condMap.put("stNodeId", "NOD_0000000130");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		return pageController();
	}

	/**
	 * 办理页面--跳转网上征求意见送审页面
	 * 
	 * @return
	 */
	private String openOnlineCensorshipPage() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (!"SEND".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus in ('SEND','SEND-RETURN') order by t.stTaskdetailId");
			for (LegislationProcessTaskdetail legislationProcessTaskdetail : taskDetails) {
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='" + legislationProcessTaskdetail.getStTaskdetailId() + "'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails", checkDetails);
			return "openProMeetCheckInfoPage";
		} else {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", "NOD_0000000130");
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			Boolean sendDisabled = false;
			Boolean sendReturnDisabled = false;
			Integer disabledNumber = 0;
			String stTaskStatus = "SEND";
			List<LegislationProcessTaskdetail> sendList = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus='" + stTaskStatus + "' ");
			if (sendList.size() > 0) {
				sendDisabled = true;
				disabledNumber++;
			}
			stTaskStatus = "SEND-RETURN";
			List<LegislationProcessTaskdetail> sendReturnList = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus='" + stTaskStatus + "' ");
			if (sendReturnList.size() > 0) {
				sendReturnDisabled = true;
				disabledNumber++;
			}
			request.setAttribute("disabledNumber", disabledNumber);
			request.setAttribute("sendDisabled", sendDisabled);
			request.setAttribute("sendReturnDisabled", sendReturnDisabled);
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			request.setAttribute("buttonId", request.getParameter("buttonId"));
			return pageController();
		}
	}

	/**
	 * 办理页面--跳转网上征求意见上网发布页面
	 * 
	 * @return
	 */
	private String openOnlineReleasePage() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (!"PUBLISH".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus in ('SEND','SEND-RETURN','PUBLISH') order by t.stTaskdetailId");
			for (LegislationProcessTaskdetail legislationProcessTaskdetail : taskDetails) {
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='" + legislationProcessTaskdetail.getStTaskdetailId() + "'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails", checkDetails);
			return "openProMeetCheckInfoPage";
		} else {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", "NOD_0000000130");
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			Boolean publishListDisabled = false;
			List<LegislationProcessTaskdetail> publishList = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus='PUBLISH' ");
			if (publishList.size() > 0) {
				publishListDisabled = true;
			}
			request.setAttribute("publishListDisabled", publishListDisabled);
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			request.setAttribute("buttonId", request.getParameter("buttonId"));
			return pageController();
		}
	}

	/**
	 * 办理页面--跳转网上意见汇总页面
	 * 
	 * @return
	 */
	private String openOnlineSummaryPage() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", stDocId);
		condMap.put("stNodeId", stNodeId);
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		if ("GATHER".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
			Boolean summaryDisabled = false;
			if (null == legislationProcessTaskList.get(0).getStBakTwo()) {
				summaryDisabled = true;
			}
			request.setAttribute("summaryDisabled", summaryDisabled);
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "openOnlineSummaryPage";
		} else {
			request.setAttribute("legislationFilesList", legislationFilesList);
			method = "openOnlineSummaryInfoPage";
		}
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		return method;
	}

	/**
	 * 网上意见汇总保存
	 * 
	 * @return
	 */
	private String saveOnlineSummary() throws IOException {
		legislationProcessTaskService.saveOnlineSummary(request);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}

	/**
	 * 办理页面--跳转采纳意见发布页面
	 * 
	 * @return
	 */
	private String openOnlinePublishPage() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", stDocId);
		condMap.put("stNodeId", stNodeId);
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		return pageController();
	}

	/**
	 * 办理页面--跳转专家论证会发起页面和专家论证会结果归档页面
	 * 
	 * @return
	 */
	private String openExpertDemonstrationPage() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList("NOD_0000000150", legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "openExpertDemonstrationPage";
			} else {
				method = "openExpertInfoPage";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList("NOD_0000000150", null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "openExpertDemonstrationPage";
		}
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 办理页面--跳转立法听证会发起页面和立法听证会结果归档页面
	 * 
	 * @return
	 */
	private String openLegislationDemonstrationPage() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus()) || "RESULT".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList("NOD_0000000140", legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "openLegislationDemonstrationPage";
			} else {
				method = "openLegislationInfoPage";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList("NOD_0000000140", null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "openLegislationDemonstrationPage";
		}
		request.setAttribute("nodeId", request.getParameter("stNodeId"));
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 办理页面--跳转立法听证会接收页面
	 * 
	 * @return
	 */
	private String openLegislationReceivePage() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", stDocId);
		condMap.put("stNodeId", "NOD_0000000140");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		return pageController();
	}

	/**
	 * 办理页面--跳转立法听证会送审页面
	 * 
	 * @return
	 */
	private String openLegislationCensorshipPage() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (!"SEND".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus in ('SEND','SEND-RETURN') order by t.stTaskdetailId");
			for (LegislationProcessTaskdetail legislationProcessTaskdetail : taskDetails) {
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='" + legislationProcessTaskdetail.getStTaskdetailId() + "'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails", checkDetails);
			return "openProMeetCheckInfoPage";
		} else {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", "NOD_0000000140");
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			Boolean sendDisabled = false;
			Boolean sendReturnDisabled = false;
			Integer disabledNumber = 0;
			String stTaskStatus = "SEND";
			List<LegislationProcessTaskdetail> sendList = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus='" + stTaskStatus + "' ");
			if (sendList.size() > 0) {
				sendDisabled = true;
				disabledNumber++;
			}
			stTaskStatus = "SEND-RETURN";
			List<LegislationProcessTaskdetail> sendReturnList = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus='" + stTaskStatus + "' ");
			if (sendReturnList.size() > 0) {
				sendReturnDisabled = true;
				disabledNumber++;
			}
			request.setAttribute("disabledNumber", disabledNumber);
			request.setAttribute("sendDisabled", sendDisabled);
			request.setAttribute("sendReturnDisabled", sendReturnDisabled);
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			request.setAttribute("buttonId", request.getParameter("buttonId"));
			return pageController();
		}
	}

	/**
	 * 办理页面--跳转立法听证会上网发布页面
	 * 
	 * @return
	 */
	private String openLegislationReleasePage() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (!"PUBLISH".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus in ('SEND','SEND-RETURN','PUBLISH') order by t.stTaskdetailId");
			for (LegislationProcessTaskdetail legislationProcessTaskdetail : taskDetails) {
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='" + legislationProcessTaskdetail.getStTaskdetailId() + "'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails", checkDetails);
			return "openProMeetCheckInfoPage";
		} else {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", "NOD_0000000140");
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			Boolean publishListDisabled = false;
			List<LegislationProcessTaskdetail> publishList = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus='PUBLISH' ");
			if (publishList.size() > 0) {
				publishListDisabled = true;
			}
			request.setAttribute("publishListDisabled", publishListDisabled);
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			request.setAttribute("buttonId", request.getParameter("buttonId"));
			return pageController();
		}
	}

	/**
	 * 办理页面--跳转立法听证会网上报名页面
	 * 
	 * @return
	 */
	private String openLegislationRegistrationPage() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (!"GATHER".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus in ('SEND','SEND-RETURN','PUBLISH','GATHER-RETURN') order by t.stTaskdetailId");
			for (LegislationProcessTaskdetail legislationProcessTaskdetail : taskDetails) {
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='" + legislationProcessTaskdetail.getStTaskdetailId() + "'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails", checkDetails);
			return "openProMeetCheckInfoPage";
		} else {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", "NOD_0000000140");
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			Boolean gatherReturnDisabled = false;
			List<LegislationProcessTaskdetail> gatherReturnList = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='" + legislationProcessTaskList.get(0).getStTaskId() + "' and t.stTaskStatus='GATHER-RETURN' ");
			if (gatherReturnList.size() > 0) {
				gatherReturnDisabled = true;
			}
			request.setAttribute("gatherReturnDisabled", gatherReturnDisabled);
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			request.setAttribute("buttonId", request.getParameter("buttonId"));
			return pageController();
		}
	}

	private String openHandleDemonstrationPage() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stTaskStatus='DOING' and t.stEnable is null");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", stDocId);
		condMap.put("stNodeId", stNodeId);
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		request.setAttribute("legislationFilesList", legislationFilesList);
		return pageController();
	}

	private String saveHandleDemonstration() throws IOException {
		String stTaskId = legislationProcessTaskService.saveHandleDemonstration(request);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("stTaskId", stTaskId);
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}

	private String openExpertInfoPage() {
		return pageController();
	}

	private String openLegislationInfoPage() {
		return pageController();
	}

	/**
	 * 跳转新增审核会议信息页面
	 * 
	 * @return
	 */
	private String openAddAuditMeetingPage() {
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stEnable is null");
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		request.setAttribute("legislationProcessDoc", new LegislationProcessDoc());
		request.setAttribute("stTaskStatus", "TODO");
		return pageController();
	}

	/**
	 * 跳转编辑审核会议信息页面
	 * 
	 * @return
	 */
	private String openEditAuditMeetingPage() {
		String stDocId = request.getParameter("stDocId");
		String stTaskStatus = request.getParameter("stTaskStatus");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		String[] stDocIdArray = legislationProcessDoc.getStDocSource().split("#");
		List<Map> legislationProcessDocList = new ArrayList<>();
		for (int i = 0; i < stDocIdArray.length; i++) {
			LegislationProcessDoc checkedDoc = legislationProcessDocService.findById(stDocIdArray[i]);
			Map map = new HashMap();
			map.put("stDocId", checkedDoc.getStDocId());
			map.put("stDocName", checkedDoc.getStDocName());
			List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocIdArray[i], "NOD_0000000105");
			map.put("stActive", legislationProcessTaskList.get(0).getStActive() == null ? "true" : legislationProcessTaskList.get(0).getStActive());
			legislationProcessDocList.add(map);
		}
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNodeId", "NOD_0000000170");
		sortMap.put("dtPubDate", "ASC");
		if ("INPUT".equals(stTaskStatus)) {
			LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='" + stDocId + "' and t.stNodeId='NOD_0000000170' and t.stTaskStatus='INPUT' and t.stEnable is null").get(0);
			Map map = new HashMap();
			map.put("stDocId", legislationProcessTask.getStTaskId());
			if (legislationProcessTask.getStComment1() != null && "after".equals(legislationProcessTask.getStComment1())) {
				map.put("stActive", legislationProcessTask.getStActive());
				map.put("stDocName", legislationProcessTask.getStBakOne());
				map.put("stDocNo", legislationProcessTask.getStBakTwo());
				map.put("stNodeName", legislationProcessTask.getStNodeName());
				map.put("stComent", legislationProcessTask.getStComment2());
				map.put("dtCreateDate", legislationProcessTask.getDtBakDate());
				condMap.put("stParentId", legislationProcessTask.getStTaskId());
			} else {
				map.put("stActive", "true");
				map.put("stDocName", legislationProcessDoc.getStDocName());
				map.put("stDocNo", legislationProcessDoc.getStDocNo());
				map.put("stNodeName", legislationProcessDoc.getStNodeName());
				map.put("stComent", legislationProcessDoc.getStComent());
				map.put("dtCreateDate", legislationProcessDoc.getDtCreateDate());
				condMap.put("stParentId", "-1");
			}
			request.setAttribute("legislationProcessDoc", map);
		} else {
			request.setAttribute("legislationProcessDoc", legislationProcessDoc);
			condMap.put("stParentId", legislationProcessDoc.getStDocId());
		}
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList("NOD_0000000170", legislationFilesList);
		request.setAttribute("LegislationExampleList", legislationExampleFilesList);
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		request.setAttribute("stTaskStatus", stTaskStatus);
		return pageController();
	}

	/**
	 * 保存审核会议信息 已经移动到审核会议模块lj
	 * 
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	private String saveAuditMeeting() throws Exception {
		legislationProcessTaskService.saveAuditMeeting(request, session);
		return null;
	}

	// 已经移动到审核会议模块 LJ
	private String saveAuditMeeting_bak() throws Exception {
		String op = request.getParameter("op");
		System.out.println("op=" + op);
		String stDocId = request.getParameter("stDocId");
		String stDocName = request.getParameter("stDocName");
		String stDocNo = request.getParameter("stDocNo");
		String stDocSource = request.getParameter("stDocSource");
		String stNodeName = request.getParameter("stNodeName");
		String dtCreateDate = request.getParameter("dtCreateDate");
		String stComent = request.getParameter("stComent");
		String stComment1 = request.getParameter("stComment1");// feedback
		String stTaskStatus = request.getParameter("stTaskStatus");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();

		String[] stDocIdArray = new String[0];
		if (stDocSource != null && !"".equals(stDocSource)) {
			stDocIdArray = stDocSource.split("#");
			for (int i = 0; i < stDocIdArray.length; i++) {
				String newDocName = request.getParameter(stDocIdArray[i]);
				if (StringUtil.isNotEmpty(newDocName)) {
					LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocIdArray[i]);
					legislationProcessDoc.setStDocName(newDocName);
					legislationProcessDocService.update(legislationProcessDoc);
					List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='" + stDocIdArray[i] + "'");
					legislationProcessTaskList.forEach((LegislationProcessTask newLegislationProcessTask) -> {
						newLegislationProcessTask.setStFlowId(newDocName);
						legislationProcessTaskService.update(newLegislationProcessTask);
					});
					List<LegislationProcessDeal> legislationProcessDealList = legislationProcessDealService.findByHQL("from LegislationProcessDeal t where 1=1 and t.stDocId='" + stDocIdArray[i] + "'");
					legislationProcessDealList.forEach((LegislationProcessDeal newLegislationProcessDeal) -> {
						newLegislationProcessDeal.setStBakOne(newDocName);
						legislationProcessDealService.update(newLegislationProcessDeal);
					});
				}
				if ("INPUT".equals(stTaskStatus)) {
					String stActive = request.getParameter("stActive" + stDocIdArray[i]);
					List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='" + stDocIdArray[i] + "' and stNodeId='NOD_0000000105' and t.stEnable is null");
					legislationProcessTaskList.forEach((LegislationProcessTask legislationProcessTask) -> {
						legislationProcessTask.setStActive(stActive);
						legislationProcessTaskService.update(legislationProcessTask);
					});
				}
			}
		}
		// 如果是新增
		if (StringUtil.isEmpty(stDocId)) {
			LegislationProcessDoc auditMeeting = new LegislationProcessDoc();
			auditMeeting.setStDocName(stDocName);
			auditMeeting.setStDocNo(stDocNo);
			auditMeeting.setStNodeId("NOD_0000000170");
			auditMeeting.setStNodeName(stNodeName);
			auditMeeting.setStComent(stComent);
			auditMeeting.setDtCreateDate(DateUtils.parseDate(dtCreateDate, "yyyy-MM-dd"));
			auditMeeting.setStDocSource(stDocSource);
			auditMeeting.setStUserId(userId);
			auditMeeting.setStUserName(userName);
			auditMeeting.setStUnitId(unitId);
			auditMeeting.setStUnitName(unitName);
			stDocId = legislationProcessDocService.addObj(auditMeeting);

			LegislationProcessTask legislationProcessTask = new LegislationProcessTask();
			legislationProcessTask.setStDocId(stDocId);
			legislationProcessTask.setStNodeId("NOD_0000000170");
			legislationProcessTask.setStNodeName("审核会议处理(单独)");
			legislationProcessTask.setStTaskStatus("TODO");
			legislationProcessTask.setStFlowId(stDocName);
			legislationProcessTask.setDtOpenDate(new Date());
			legislationProcessTask.setStUserId(userId);
			legislationProcessTask.setStUserName(userName);
			legislationProcessTask.setStRoleId(session.getAttribute("userRoleId").toString());
			legislationProcessTask.setStRoleName(session.getAttribute("userRole").toString());
			legislationProcessTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
			legislationProcessTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
			legislationProcessTaskService.add(legislationProcessTask);

			for (int i = 0; i < stDocIdArray.length; i++) {
				LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocIdArray[i]);
				LegislationProcessDeal legislationProcessDeal = new LegislationProcessDeal();
				legislationProcessDeal.setStDocId(stDocIdArray[i]);
				legislationProcessDeal.setStActionId("NOD_0000000170");
				legislationProcessDeal.setStActionName("审核会议处理(单独)");
				legislationProcessDeal.setStUserId(userId);
				legislationProcessDeal.setStUserName(userName);
				legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
				legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
				legislationProcessDeal.setDtDealDate(new Date());
				legislationProcessDealService.add(legislationProcessDeal);
			}

		} else {
			// 如果是有ID的
			System.out.println("-----1-");
			if ("TODO".equals(stTaskStatus)) {
				LegislationProcessDoc auditMeeting = legislationProcessDocService.findById(stDocId);
				auditMeeting.setStDocName(stDocName);
				auditMeeting.setStDocNo(stDocNo);
				auditMeeting.setStNodeName(stNodeName);
				auditMeeting.setStComent(stComent);
				auditMeeting.setDtCreateDate(DateUtils.parseDate(dtCreateDate, "yyyy-MM-dd"));
				legislationProcessDocService.update(auditMeeting);
			} else if ("FEEDBACK".equals(stTaskStatus)) {
				LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='" + stDocId + "' and t.stNodeId='NOD_0000000170' and t.stEnable is null").get(0);
				legislationProcessTask.setStComment1(stComment1);
				legislationProcessTaskService.update(legislationProcessTask);
			} else {
				LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(stDocId);
				legislationProcessTask.setStBakOne(stDocName);
				legislationProcessTask.setStBakTwo(stDocNo);
				legislationProcessTask.setStNodeName(stNodeName);
				legislationProcessTask.setStComment2(stComent);
				legislationProcessTask.setStComment1("after");
				legislationProcessTask.setDtBakDate(DateUtils.parseDate(dtCreateDate, "yyyy-MM-dd"));
				legislationProcessTaskService.update(legislationProcessTask);
			}
			if ("submit".equals(op)) {
				// 完成当前的状态，变为下一个。
				LegislationProcessTask legislationProcessTask = null;
				if (!"INPUT".equals(stTaskStatus)) {//
					legislationProcessTask = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='" + stDocId + "' and t.stNodeId='NOD_0000000170' and t.stEnable is null").get(0);
				} else {
					legislationProcessTask = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stTaskId='" + stDocId + "' and t.stNodeId='NOD_0000000170' and t.stEnable is null").get(0);
				}
				WegovSimpleNode node = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='NOD_0000000170'").get(0);
				String[] statusCodeArray = node.getStDoneName().split("#");
				int position = -1;
				for (int i = 0; i < statusCodeArray.length; i++) {
					String each = statusCodeArray[i];
					if (each.equals(stTaskStatus))
						position = i;
				}
				// int positon = Arrays.binarySearch(statusCodeArray,
				// stTaskStatus);
				legislationProcessTask.setStTaskStatus(statusCodeArray[position + 1]);
				legislationProcessTaskService.update(legislationProcessTask);
			}
		}
		Enumeration keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = request.getParameter(key);
			if (value.startsWith("FIL_")) {
				legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='" + stDocId + "' where s.stFileId='" + value + "'");
			}
		}
		return null;
	}

	// 填写反馈的页面
	private String openAuditMeetingFeedbackPage() {
		String stDocId = request.getParameter("stDocId");
		String stTaskStatus = request.getParameter("stTaskStatus");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='" + stDocId + "' and t.stNodeId='NOD_0000000170' and t.stEnable is null").get(0);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		condMap.put("stParentId", legislationProcessDoc.getStDocId());
		request.setAttribute("stTaskStatus", stTaskStatus);
		return pageController();
	}

	/**
	 * 跳转审核会议详情页
	 * 
	 * @return
	 */
	private String openAuditMeetingInfoPage() {
		String stDocId = request.getParameter("stDocId");
		String stTaskStatus = request.getParameter("stTaskStatus");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		String[] stDocIdArray = legislationProcessDoc.getStDocSource().split("#");
		List<LegislationProcessDoc> legislationProcessDocList = new ArrayList<>();
		for (int i = 0; i < stDocIdArray.length; i++) {
			LegislationProcessDoc checkedDoc = legislationProcessDocService.findById(stDocIdArray[i]);
			legislationProcessDocList.add(checkedDoc);
		}
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNodeId", "NOD_0000000170");
		sortMap.put("dtPubDate", "ASC");
		if ("before".equals(stTaskStatus)) {
			request.setAttribute("legislationProcessDoc", legislationProcessDoc);
			condMap.put("stParentId", legislationProcessDoc.getStDocId());
		} else {
			LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, "NOD_0000000170").get(0);
			Map map = new HashMap();
			map.put("stDocId", legislationProcessTask.getStTaskId());
			map.put("stActive", legislationProcessTask.getStActive());
			map.put("stDocName", legislationProcessTask.getStBakOne());
			map.put("stDocNo", legislationProcessTask.getStBakTwo());
			map.put("stNodeName", legislationProcessTask.getStNodeName());
			map.put("stComent", legislationProcessTask.getStComment2());
			map.put("dtCreateDate", legislationProcessTask.getDtBakDate());
			request.setAttribute("legislationProcessDoc", map);
			condMap.put("stParentId", legislationProcessTask.getStTaskId());
		}
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		request.setAttribute("stTaskStatus", stTaskStatus);
		return pageController();
	}

	private String openInfoTabPage() {
		return draft_create_info();
	}
	
	private String openInfoPage() {
		return draft_create_info();
	}

	private String openSeparateTabPage() {
		String stDocId = request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, "NOD_0000000102");
		if (list != null && list.size() > 0) {
			request.setAttribute("legislationProcessTask", list.get(0));
		}
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return pageController();
	}

	private String openUnitTabPage() {
		return pageController();
	}

	private String openAuditMeetingTabPage() {
		return pageController();
	}

	private String openOnlineTabPage() {
		return pageController();
	}

	private String openHeartMeetingAddPage() {
		return addDemonstrationPage();
	}

	private String openHeartMeetingEditPage() {
		return editDemonstrationPage();
	}

	private String openExpertAddPage() {
		return addDemonstrationPage();
	}

	private String openExpertEditPage() {
		return editDemonstrationPage();
	}

	/**
	 * 办理页面--跳转部门征求意见编辑页面 openUnitDemonstrationPage
	 * 
	 * @return
	 */
	private String draft_deal_deptopinion_start() {
		String method = request.getParameter("method");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
		}
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 办理页面--跳转部门征求意见盖章页面 openUnitDemonstrationPage
	 * 
	 * @return
	 */
	private String draft_deal_deptopinion_sign() {
		String method = request.getParameter("method");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			// 可操作页面
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		}
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return demonstrationPageController(method, legislationProcessTaskList.get(0).getStTaskId());
	}


	/**
	 * 办理页面--跳转部门征求意见发送部门页面
	 * 
	 * @return
	 */
	private String draft_deal_deptopinion_send() {
		String method = request.getParameter("method");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId = (currentPerson.getTeamInfos().get(0)).getId();
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		LegislationProcessTask legislationProcessTask = legislationProcessTaskList.get(0);
		String stTaskId = legislationProcessTask.getStTaskId();
		// 发送部门id所保存在task表中的字段
		String stBakOne = legislationProcessTask.getStBakOne();
		String deptIds[] = null;
		List<TeamInfo> teamInfoList = new ArrayList<TeamInfo>();
		if (stBakOne != null && !stBakOne.isEmpty()) {
			// 拿到部门id
			deptIds = stBakOne.split(",");
		}
		if ("TODO".equals(legislationProcessTask.getStTaskStatus())) {
			// 任务进行中
			// 保存的已选择的部门id
			request.setAttribute("deptIds", deptIds);
			teamInfoList = teamInfoService.findTeamInfoInModuleByType("MODULE_LEGISLATE", "委办局");
			method = "draft_deal_deptopinion_send";
		} else {
			// 任务已完成
			// 已发送的部门
			for (String id : deptIds) {
				TeamInfo teamInfo = teamInfoService.findTeamInfoByTeamId("MODULE_LEGISLATE", id);
				teamInfoList.add(teamInfo);
			}
		}
		request.setAttribute("teamInfoList", teamInfoList);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return demonstrationPageController(method, stTaskId);
	}

	/**
	 * 办理页面--跳转部门征求意见录入页面 openUnitDemonstrationPage
	 * 
	 * @return
	 */
	private String draft_deal_deptopinion_input() {
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		boolean allDone = true;
		String method = "draft_deal_deptopinion_input";
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			// 得到所有意见录入task
			request.setAttribute("legislationProcessTaskList", legislationProcessTaskList);
			// 是否已全部确认
			for (LegislationProcessTask task : legislationProcessTaskList) {
				if ("TODO".equals(task.getStTaskStatus())) {
					allDone = false;
				}
			}
		}
		request.setAttribute("nodeId", "NOD_0000000122");
		request.setAttribute("allDone", allDone);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 办理页面--跳转部门征求意见接收反馈页面--确认
	 * 
	 * @return
	 * @throws Exception
	 */
	private String confirm() throws Exception {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		String stTaskId = request.getParameter("stTaskId");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		boolean allDone = true;
		// 确认反馈
		LegislationProcessTask legislationProcessTask = legislationProcessTaskService.confirm(request, currentPerson, userRoleId, userRole);
		// 获取确认时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dealDate = simpleDateFormat.format(legislationProcessTask.getDtDealDate());
		// 判断部门反馈是否已全部确认
		List<LegislationProcessTask> LegislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		for (LegislationProcessTask task : LegislationProcessTaskList) {
			if ("TODO".equals(task.getStTaskStatus())) {
				allDone = false;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allDone", allDone);
		jsonObject.put("stTaskId", stTaskId);
		jsonObject.put("dealDate", dealDate);
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}

	private String openUnitAddPage() {
		return addDemonstrationPage();
	}

	private String openUnitEditPage() {
		return editDemonstrationPage();
	}

	/**
	 * 办理页面--跳转部门征求意见发送部门页面
	 * 
	 * @return
	 */
	private String openUnitSeekPage() {
		String method = request.getParameter("method");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stTaskStatus='TODO' and t.stEnable is null");
		List<TeamInfo> teamInfoList = teamInfoService.findTeamInfoInModuleByType("MODULE_LEGISLATE", "委办局");
		request.setAttribute("teamInfoList", teamInfoList);
		return demonstrationPageController(method, legislationProcessTaskList.get(0).getStTaskId());
	}

	/**
	 * 发送部门
	 * 
	 * @return
	 */
	@Deprecated
	private String sendUnit() {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		legislationProcessTaskService.sendUnit(request, currentPerson, userRoleId, userRole);
		return null;
	}

	/**
	 * 填写意见
	 * 
	 * @return
	 */
	public String addOpinion() {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		legislationProcessTaskService.addOpinion(request, currentPerson);
		return null;
	}

	/**
	 * 办理页面--跳转部门征求意见接收反馈页面
	 * 
	 * @return
	 */
	private String openUnitReceivePage() {
		String stNodeId = request.getParameter("stNodeId");
		String stDocId = request.getParameter("stDocId");
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		List<Map> opinionList = new ArrayList<>();
		legislationProcessTaskList.forEach((LegislationProcessTask legislationProcessTask) -> {
			Map map = new HashMap();
			map.put("stTaskId", legislationProcessTask.getStTaskId());
			map.put("stTeamName", legislationProcessTask.getStComment1());
			map.put("stTaskStatus", legislationProcessTask.getStTaskStatus());
			map.put("dtBakDate", legislationProcessTask.getDtBakDate());
			if (StringUtil.isNotEmpty(legislationProcessTask.getStComment2())) {
				LegislationFiles legislationFiles = legislationFilesService.findById(legislationProcessTask.getStComment2());
				map.put("fileName", legislationFiles.getStTitle());
				map.put("fileUrl", legislationFiles.getStFileUrl());
			} else {
				map.put("fileName", null);
				map.put("fileUrl", null);
			}
			opinionList.add(map);
		});
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId = currentPerson.getTeamInfos().get(0).getId();
		request.setAttribute("teamId", teamId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("opinionList", opinionList);
		return pageController();
	}

	/**
	 * 办理页面--跳转部门会签编辑页面
	 * 
	 * @return
	 */
	private String openDepartmentDemonstrationPage() {
		String method;
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				method = "openDepartmentDemonstrationPage";
			} else {
				method = "openDepartmentInfoPage";
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			method = "openDepartmentDemonstrationPage";
		}
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 办理页面--跳转部门征求意见发送部门页面
	 * 
	 * @return
	 */
	private String openDepartmentSeekPage() {
		return openUnitSeekPage();
	}

	/**
	 * 办理页面--跳转部门会签接收反馈页面
	 * 
	 * @return
	 */
	private String openDepartmentReceivePage() {
		return openUnitReceivePage();
	}

	/**
	 * 部门跳转部门填写意见页面
	 * 
	 * @return
	 */
	private String openUnitAddOpinionPage() {
		String method;
		String buttonId = request.getParameter("buttonId");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId = (currentPerson.getTeamInfos().get(0)).getId();
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stParentId='" + teamId + "' and t.stEnable is null");
		if (legislationProcessTaskList.get(0).getStComment2() == null) {
			method = "openUnitAddOpinionPage";
		} else {
			LegislationFiles legislationFiles = legislationFilesService.findById(legislationProcessTaskList.get(0).getStComment2());
			request.setAttribute("legislationFiles", legislationFiles);
			method = "openUnitInfoPage";
		}
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", stDocId);
		condMap.put("stNodeId", "NOD_0000000120");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		request.setAttribute("buttonId", buttonId);
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return method;
	}

	/**
	 * 部门跳转部门进行会签页面
	 * 
	 * @return
	 */
	private String openDepartmentAddOpinionPage() {
		String method;
		String buttonId = request.getParameter("buttonId");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId = (currentPerson.getTeamInfos().get(0)).getId();
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='" + stDocId + "' and t.stNodeId='" + stNodeId + "' and t.stParentId='" + teamId + "' and t.stEnable is null");
		if (legislationProcessTaskList.get(0).getStComment2() == null) {
			method = "openDepartmentAddOpinionPage";
		} else {
			LegislationFiles legislationFiles = legislationFilesService.findById(legislationProcessTaskList.get(0).getStComment2());
			request.setAttribute("legislationFiles", legislationFiles);
			method = "openDepartmentInfoPage";
		}
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", stDocId);
		condMap.put("stNodeId", "NOD_0000000160");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		request.setAttribute("buttonId", buttonId);
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return method;
	}

	private String openUnitInfoPage() {
		String stTaskId = request.getParameter("stTaskId");
		LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(stTaskId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationProcessTask.getStDocId());
		condMap.put("stNodeId", "NOD_0000000120");
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		if (StringUtil.isNotEmpty(legislationProcessTask.getStComment2())) {
			LegislationFiles legislationFiles = legislationFilesService.findById(legislationProcessTask.getStComment2());
			request.setAttribute("legislationFiles", legislationFiles);
		}
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		return pageController();
	}

	private String addDemonstrationPage() {
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000103' and t.stTaskStatus='DOING' and t.stEnable is null and d.stDocId not in (select a.stDocId from LegislationProcessDeal a where a.stActionId='" + stNodeId + "')");
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		request.setAttribute("legislationProcessTask", new LegislationProcessTask());
		return pageController();
	}

	private String openMeetingBeforeInfoPage() {
		LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(request.getParameter("stTaskId"));
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		String stDocId = legislationProcessTask.getStDocId();
		String stNodeId = legislationProcessTask.getStNodeId();
		if (stNodeId.equals("NOD_0000000141")) {
			stNodeId = "NOD_0000000140";
		} else if (stNodeId.equals("NOD_0000000151")) {
			stNodeId = "NOD_0000000150";
		}
		condMap.put("stDocId", stDocId);
		condMap.put("stNodeId", stNodeId);

		legislationProcessTask = legislationProcessTaskService.findByList(condMap, sortMap).get(0);
		List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + legislationProcessTask.getStDocId() + "' and t.stNodeId ='" + stNodeId + "' and t.stSampleId !='null' order by t.stSampleId ");
		List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + legislationProcessTask.getStDocId() + "' and t.stNodeId ='" + stNodeId + "' and t.stSampleId='null' order by t.stFileId ");

		request.setAttribute("docList", docList);
		request.setAttribute("otherDocList", otherDocList);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		return pageController();
	}

	private String openMeetingAfterInfoPage() {
		LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(request.getParameter("stTaskId"));
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stDocId", legislationProcessTask.getStDocId());
		condMap.put("stTaskStatus", legislationProcessTask.getStTaskStatus());

		String stNodeId = legislationProcessTask.getStNodeId();

		if (stNodeId.equals("NOD_0000000140")) {
			stNodeId = "NOD_0000000141";
		} else if (stNodeId.equals("NOD_0000000150")) {
			stNodeId = "NOD_0000000151";
		}

		condMap.put("stNodeId", stNodeId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findByList(condMap, sortMap);
		if (list != null && list.size() > 0) {
			legislationProcessTask = legislationProcessTaskService.findByList(condMap, sortMap).get(0);
			List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + legislationProcessTask.getStDocId() + "' and t.stNodeId ='" + stNodeId + "' and t.stSampleId !='null' order by t.stSampleId ");
			List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + legislationProcessTask.getStDocId() + "' and t.stNodeId ='" + stNodeId + "' and t.stSampleId='null' order by t.stFileId ");

			request.setAttribute("docList", docList);
			request.setAttribute("otherDocList", otherDocList);
			request.setAttribute("legislationProcessTask", legislationProcessTask);
		}

		return pageController();
	}

	private String editDemonstrationPage() {
		String stTaskId = request.getParameter("stTaskId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(stTaskId);
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(legislationProcessTask.getStDocId());
		String exampleNode = stNodeId;
		if ("NOD_0000000141".equals(stNodeId)) {
			exampleNode = "NOD_0000000140";
		} else if ("NOD_0000000151".equals(stNodeId)) {
			exampleNode = "NOD_0000000150";
		}
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationProcessTask.getStDocId());
		condMap.put("stNodeId", stNodeId);
		sortMap.put("dtPubDate", "ASC");
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(exampleNode, legislationFilesList);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		request.setAttribute("LegislationExampleList", legislationExampleFilesList);
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		return pageController();
	}

	/**
	 * 页面控制
	 * 
	 * @return
	 */
	private String pageController() {
		String stDocId = request.getParameter("stDocId");

		String methodStr = request.getParameter("method");

		String stNodeId = request.getParameter("stNodeId");

		String stTaskId = request.getParameter("stTaskId");

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("stTaskId", stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return methodStr;
	}

	/**
	 * 办理页面控制
	 * 
	 * @return
	 */
	private String demonstrationPageController(String method, String stTaskId) {
		String stDocId = request.getParameter("stDocId");
		String buttonId = request.getParameter("buttonId");
		String stNodeId = request.getParameter("stNodeId");

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("buttonId", buttonId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("stTaskId", stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return method;
	}

	/**
	 * 新增修改草案
	 * 
	 * @return
	 * @throws FzbDaoException
	 */
	private String editLegislationProcessDoc() throws FzbDaoException {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		legislationProcessDocService.editLegislationProcessDoc(request, currentPerson, session.getAttribute("userRoleId").toString(), session.getAttribute("userRole").toString());
		return null;
	}

	// 立法办理点---【通用】节点保存表单数据
	private String saveLegislationDemonstration() throws Exception {
		String stNodeId = request.getParameter("stNodeId");
		String stTaskId = "";

		if ("NOD_0000000140".equals(stNodeId) || "NOD_0000000141".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "立法听证会发起");//不要传中文名称，从配置中取。LJ，暂时不修改。
		} else if ("NOD_0000000150".equals(stNodeId) || "NOD_0000000151".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "专家论证会发起");
		} else if ("NOD_0000000130".equals(stNodeId) || "NOD_0000000131".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "网上征求意见发起");
		} else if ("NOD_0000000160".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "部门会签发起");
		} else if ("NOD_0000000161".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "部门会签盖章");
		} else if ("NOD_0000000163".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "部门会签发送部门");
		} else if ("NOD_0000000162".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "部门会签发送结果反馈");
		} else if ("NOD_0000000131".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "公开征求意见接收");
		} else if ("NOD_0000000120".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "部门征求意见发起");
		} else if ("NOD_0000000121".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "部门征求意见处理");
		} else if ("NOD_0000000123".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "部门征求意见发送");
		} else if ("NOD_0000000122".equals(stNodeId)) {
			stTaskId = saveLegislation(stNodeId, "部门征求意见录入");
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("stTaskId", stTaskId);
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}

	
	//打开送常务会议页面
	private String draft_citymeet_deal() {
		String stTaskStatus = request.getParameter("stTaskStatus");
		String stDocId = request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		LegislationProcessTask legislationProcessTask=new LegislationProcessTask();
		List<LegislationProcessTask> list = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		if(list.size()>0) legislationProcessTask = list.get(0);
		
		//可用的议题
		List<LegislationCitymeeting> cityMeetingList = legislationCitymeetingService.findByHQL("select t from LegislationCitymeeting t,LegislationCitymeetingTask k where 1=1 and t.stTopicId =k.stTopicId and k.stNodeId='NOD_0000000180' and k.stTaskStatus ='AFFIRM' ");
		
		request.setAttribute("stTaskStatus", stTaskStatus);
		request.setAttribute("nodeId", legislationProcessTask.getStNodeId());
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		request.setAttribute("cityMeetingList", cityMeetingList);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		request.setAttribute("requestUrl", request.getRequestURI());
		return "draft_citymeet_deal";
	}
	
	//保存常务会议材料报送
	private String saveCitymeetingDraft() throws Exception {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		String stTaskId = request.getParameter("stTaskId");
		String stNodeId = request.getParameter("stNodeId");
		String op = request.getParameter("op");
		
		String stBakOne = request.getParameter("stBakOne");
		String stDocId = request.getParameter("stDocId");
		String stBakTwo = request.getParameter("stBakTwo");
		String dtBakDate = request.getParameter("dtBakDate");
		String stComment1 = request.getParameter("stComment1");
		String stComment2 = request.getParameter("stComment2");
		String stDocSource = request.getParameter("stDocSource");//议题ID
		String nodeStatus = request.getParameter("nodeStatus");// 可能有状态过来

		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		
		WegovSimpleNode curentNode = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + stNodeId + "'").get(0);
		
		if (stTaskId != null && !stTaskId.isEmpty()) {
			LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(stTaskId);
			stDocId = legislationProcessTask.getStDocId();
		}
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);

		if (StringUtil.isEmpty(stTaskId) || "null".equals(stTaskId)) {
			// 如果没有任务ID，就产生一个新的任务，任务节点就是传入的节点。
			LegislationProcessTask newTask = new LegislationProcessTask();
			newTask.setStDocId(stDocId);
			newTask.setStFlowId(legislationProcessDoc.getStDocName());
			newTask.setStBakOne(stBakOne);
			newTask.setStBakTwo(stBakTwo);
			if (StringUtil.isNotEmpty(dtBakDate)) {
				newTask.setDtBakDate(DateUtils.parseDate(dtBakDate, "yyyy-MM-dd"));
			} else {
				newTask.setDtBakDate(null);
			}
			newTask.setStComment1(stComment1);
			newTask.setStComment2(stComment2);
			newTask.setStNodeId(stNodeId);
			newTask.setStNodeName(curentNode.getStNodeName());
			newTask.setStTaskStatus("TODO");
			if("submit".equals(op)){
				newTask.setStTaskStatus("DONE");
			}
			newTask.setDtOpenDate(new Date());
			newTask.setStUserId(userId);
			newTask.setStUserName(userName);
			newTask.setStRoleId(userRoleId);
			newTask.setStRoleName(userRole);
			newTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
			newTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
			stTaskId = legislationProcessTaskService.addObj(newTask);

		} else {
			// 如果有任务，就修改保存任务
			LegislationProcessTask legislationProcessTask = legislationProcessTaskService.findById(stTaskId);

				legislationProcessTask.setStBakOne(stBakOne);
				legislationProcessTask.setStBakTwo(stBakTwo);
				if (StringUtil.isNotEmpty(dtBakDate)) {
					legislationProcessTask.setDtBakDate(DateUtils.parseDate(dtBakDate, "yyyy-MM-dd"));
				} else {
					legislationProcessTask.setDtBakDate(null);
				}
				legislationProcessTask.setStComment1(stComment1);
				legislationProcessTask.setStComment2(stComment2);
				if("submit".equals(op)){
					legislationProcessTask.setStTaskStatus("DONE");
				}
				legislationProcessTaskService.update(legislationProcessTask);
		}
		if("submit".equals(op)){
			//没有后面的节点，就结束了。
//			WegovSimpleNode nextNode = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where 1=1 and t.stNodeId ='" + curentNode.getStNextNode() + "'").get(0);
//			LegislationProcessTask newTask = new LegislationProcessTask();
//			newTask.setStDocId(stDocId);
//			newTask.setStFlowId(legislationProcessDoc.getStDocName());
//			newTask.setStBakOne(stBakOne);
//			newTask.setStBakTwo(stBakTwo);
//			if (StringUtil.isNotEmpty(dtBakDate)) {
//				newTask.setDtBakDate(DateUtils.parseDate(dtBakDate, "yyyy-MM-dd"));
//			} else {
//				newTask.setDtBakDate(null);
//			}
//			newTask.setStNodeId(nextNode.getStNodeId());
//			newTask.setStNodeName(nextNode.getStNodeName());
//			newTask.setStTaskStatus("TODO");
//			newTask.setDtOpenDate(new Date());
//			newTask.setStUserId(userId);
//			newTask.setStUserName(userName);
//			newTask.setStRoleId(userRoleId);
//			newTask.setStRoleName(userRole);
//			newTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
//			newTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
//			stTaskId = legislationProcessTaskService.addObj(newTask);
		}
		// 处理附件内容
//		legislationFilesService.updateParentIdById(request, stDocId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("stTaskId", stTaskId);
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}
	
	
	private String saveLegislation(String stNodeId, String stNodeName) throws Exception {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		return legislationProcessDocService.saveLegislation(request, currentPerson, userRoleId, userRole, stNodeId, stNodeName);
	}

	/**
	 * 提交分办
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "draft_dist_info", results = { @Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp") })
	public String draft_dist_info() throws Exception {
		legislationProcessDocService.draft_dist_info(request);
		return SUCCESS;
	}

	/**
	 * 审核操作
	 * 
	 * @return
	 */
	private String saveTaskCheck() throws ParseException {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		legislationProcessTaskService.saveTaskCheck(request, currentPerson, userRoleId, userRole);

		return null;
	}

	/**
	 * 报审发起
	 * 
	 * @return
	 */
	private String draftReportStart() {
		String stDocId = request.getParameter("stDocId");
		String methodStr = request.getParameter("method");
		String stComent = request.getParameter("stComent");
		String type = request.getParameter("type");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='NOD_0000000106' and t.stEnable is null");
		LegislationProcessTask legislationProcessTask = new LegislationProcessTask();
		for (LegislationProcessTask legislationTask : list) {
			legislationProcessTask = legislationTask;
		}
		if (!StringUtils.hasText(type)) {// 不为保存和提交
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", legislationProcessTask.getStDocId());
			condMap.put("stNodeId", legislationProcessDoc.getStNodeId());
			sortMap.put("dtPubDate", "ASC");
			// 文件查询还有问题
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);

			request.setAttribute("stDocId", stDocId);
			request.setAttribute("legislationProcessDoc", legislationProcessDoc);
			request.setAttribute("legislationProcessTask", legislationProcessTask);
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("legislationFilesList", legislationFilesList);
		} else {// 保存、提交操作
			if (StringUtils.hasText(stComent)) {
				UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
				legislationProcessTask.setStComment1(stComent);// 记录报审说明
				legislationProcessTask.setStUserId(currentPerson.getUserId());
				legislationProcessTask.setStUserName(currentPerson.getName());
				legislationProcessTask.setStRoleId(session.getAttribute("userRoleId").toString());
				legislationProcessTask.setStRoleName(session.getAttribute("userRole").toString());
				legislationProcessTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
				legislationProcessTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
				legislationProcessTaskService.update(legislationProcessTask);
			}

			if ("submit".equals(type)) {// 提交操作，修改task状态 并新增一个task
				legislationProcessTask.setStTaskStatus("DONE");
				legislationProcessTask.setDtCloseDate(new Date());
				legislationProcessTaskService.update(legislationProcessTask);
				// WegovSimpleNode nodeInfo =
				// wegovSimpleNodeService.findById(legislationProcessTask.getStNodeId());
				WegovSimpleNode nodeInfo = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='" + legislationProcessTask.getStNodeId() + "'").get(0);
				LegislationProcessTask nextlegislationProcessTask = new LegislationProcessTask();
				nextlegislationProcessTask.setStDocId(stDocId);
				nextlegislationProcessTask.setStNodeId(nodeInfo.getStNextNode());
				WegovSimpleNode nextNodeInfo = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='" + nodeInfo.getStNextNode() + "'").get(0);
				// nextlegislationProcessTask.setStNodeName(wegovSimpleNodeService.findById(nodeInfo.getStNextNode()).getStNodeName());
				nextlegislationProcessTask.setStNodeName(nextNodeInfo.getStNodeName());
				nextlegislationProcessTask.setStTaskStatus("TODO");
				nextlegislationProcessTask.setStFlowId(legislationProcessDoc.getStDocName());
				nextlegislationProcessTask.setDtOpenDate(new Date());
				nextlegislationProcessTask.setStComment1(legislationProcessTask.getStComment1());
				nextlegislationProcessTask.setStComment2(legislationProcessTask.getStComment2());
				legislationProcessTaskService.add(nextlegislationProcessTask);
			}

		}

		return methodStr;
	}

	/**
	 * 审核会议处理
	 * 
	 * @return
	 */
	private String draftReportAudit() {
		String stDocId = request.getParameter("stDocId");
		String methodStr = request.getParameter("method");
		String stComent = request.getParameter("stComent");
		String stNodeId = request.getParameter("stNodeId");
		String stTaskStatus = request.getParameter("stTaskStatus");
		String type = request.getParameter("type");
		String reportLeadershipReview = request.getParameter("reportLeadershipReview");// 报审领导
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='NOD_0000000107' and t.stEnable is null");
		LegislationProcessTask legislationProcessTask = new LegislationProcessTask();
		for (LegislationProcessTask legislationTask : list) {
			legislationProcessTask = legislationTask;
		}
		WegovSimpleNode node = (WegovSimpleNode) wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='NOD_0000000107'").get(0);
		String[] statusCodeArray = node.getStDoneName().split("#");

		List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='" + legislationProcessTask.getStTaskId() + "' order by t.stTaskdetailId");
		LegislationProcessTaskdetail taskDetail = new LegislationProcessTaskdetail();
		if (taskDetails.size() > 0) {
			taskDetail = taskDetails.get(0);
		} else {
			taskDetail.setStTaskId(legislationProcessTask.getStTaskId());
			taskDetail.setStTaskStatus(legislationProcessTask.getStTaskStatus());
			taskDetail.setStTitle(legislationProcessTask.getStNodeName());
			legislationProcessTaskdetailService.add(taskDetail);
		}
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationProcessTask.getStDocId());
		condMap.put("stNodeId", legislationProcessDoc.getStNodeId());
		sortMap.put("dtPubDate", "ASC");
		// 文件查询还有问题
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);

		request.setAttribute("stDocId", stDocId);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("statusCodeArray", statusCodeArray);
		request.setAttribute("taskDetail", taskDetail);
		if (statusCodeArray.length > 0 && statusCodeArray[0].equals(stTaskStatus)) {// 待审批
			if (StringUtils.hasText(type)) {// 保存、提交操作
				if (StringUtils.hasText(stComent) && StringUtils.hasText(reportLeadershipReview)) {
					legislationProcessTask.setStBakOne(stComent);// 记录报审说明
					legislationProcessTask.setStBakTwo(reportLeadershipReview);// 送审领导
				}
			}
		} else if (statusCodeArray.length > 0 && statusCodeArray[1].equals(stTaskStatus)) {// 审批录入
			if (StringUtils.hasText(type)) {// 保存、提交操作
				if (StringUtils.hasText(stComent)) {
					taskDetail.setStBak1(stComent);// 记录领导意见
					taskDetail.setStTaskStatus(stTaskStatus);
					legislationProcessTaskdetailService.update(taskDetail);
				}
			}
		} else if (statusCodeArray.length > 0 && statusCodeArray[2].equals(stTaskStatus)) {// 待报审
			if (StringUtils.hasText(type)) {// 保存、提交操作
				if (StringUtils.hasText(stComent)) {
					taskDetail.setStBak2(stComent);// 记录报办公厅报备说明
					taskDetail.setStTaskStatus(stTaskStatus);
					legislationProcessTaskdetailService.update(taskDetail);
				}
			}
		} else if (statusCodeArray.length > 0 && statusCodeArray[3].equals(stTaskStatus)) {// 报审录入
			if (StringUtils.hasText(type)) {// 保存、提交操作
				if (StringUtils.hasText(stComent)) {
					taskDetail.setStBak3(stComent);// 记录报办公厅反馈说明
					taskDetail.setStTaskStatus(stTaskStatus);
					legislationProcessTaskdetailService.update(taskDetail);
				}
			}
		}
		if (StringUtils.hasText(type)) {// 保存或提交操作
			UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
			legislationProcessTask.setStUserId(currentPerson.getUserId());
			legislationProcessTask.setStUserName(currentPerson.getName());
			legislationProcessTask.setStRoleId(session.getAttribute("userRoleId").toString());
			legislationProcessTask.setStRoleName(session.getAttribute("userRole").toString());
			legislationProcessTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
			legislationProcessTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
			legislationProcessTaskService.update(legislationProcessTask);
		}
		if ("submit".equals(type)) {// 提交操作，修改task状态 并新增一个task
			int position = -1;
			for (int i = 0; i < statusCodeArray.length; i++) {
				String each = statusCodeArray[i];
				if (each.equals(stTaskStatus))
					position = i;
			}
			legislationProcessTask.setStTaskStatus(statusCodeArray[position + 1]);
			legislationProcessTaskService.update(legislationProcessTask);

			if (statusCodeArray.length > 0 && statusCodeArray[statusCodeArray.length - 1].equals(stTaskStatus)) {// 最后一个状态的时候
				// 需要新添加下一个节点的TODO状态的task

			}
		}
		return methodStr;
	}
	
	
	private String draft_promeet_info__TODO() {

		String isbanli = "";
		String stDocId = request.getParameter("stDocId");
		String methodStr = request.getParameter("method");
		String stNodeId = request.getParameter("stNodeId");
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String stTaskStatus = nodeIdStatus[1];
		boolean infoPage=false;
		if (!(("NOD_0000000104".equals(stNodeId))||("NOD_0000000105".equals(stNodeId)))) {
			isbanli = "banli";
		}
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		//审核会议待审草案 
		if("NOD_0000000105".equals(stNodeId)) {
			 infoPage=true;
	    }
	    	stNodeId = "NOD_0000000104";
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null");
		LegislationProcessTask legislationProcessTask = new LegislationProcessTask();
		for (LegislationProcessTask legislationTask : list) {
			legislationProcessTask = legislationTask;
		}

	

		WegovSimpleNode node = (WegovSimpleNode) wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='" + stNodeId + "'").get(0);
		String[] statusCodeArray = node.getStDoneName().split("#");

	
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationProcessTask.getStDocId());
		condMap.put("stNodeId", legislationProcessDoc.getStNodeId());
		sortMap.put("dtPubDate", "ASC");
		// 文件查询还有问题
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);

		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("infoPage", infoPage);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("statusCodeArray", statusCodeArray);
		request.setAttribute("stTaskStatus", stTaskStatus);
        if(infoPage) {
        	request.setAttribute("nodeId", "NOD_0000000105");
        }
		// 如果是doc里的nodId=170 才可以从菜单里访问
		if (StringUtils.hasText(isbanli) && "banli".equals(isbanli) && null != legislationProcessDoc && !legislationProcessDoc.getStNodeId().equals("NOD_0000000101") && !legislationProcessDoc.getStNodeId().equals("NOD_0000000103")) {
			request.setAttribute("isbanli", isbanli);
		}
		    String strDisplay = "style ='display: none;'";
			request.setAttribute("strDisplay", strDisplay);
		return methodStr;
	}
	
	
	private String draft_promeet_info__DOING() {

		String isbanli = "";
		String stDocId = request.getParameter("stDocId");
		String methodStr = "draft_promeet_info__TODO";
		String stNodeId = request.getParameter("stNodeId");
		String nodeIdStatus[] = stNodeId.split("__");
		stNodeId = nodeIdStatus[0];
		String stTaskStatus = nodeIdStatus[1];
		boolean infoPage=false;
		if (!(("NOD_0000000104".equals(stNodeId))||("NOD_0000000105".equals(stNodeId)))) {
			isbanli = "banli";
		}
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		//审核会议待审草案 
		if("NOD_0000000105".equals(stNodeId)) {
			 infoPage=true;
	    }
	    	stNodeId = "NOD_0000000104";
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null");
		LegislationProcessTask legislationProcessTask = new LegislationProcessTask();
		for (LegislationProcessTask legislationTask : list) {
			legislationProcessTask = legislationTask;
		}
		WegovSimpleNode node = (WegovSimpleNode) wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='" + stNodeId + "'").get(0);
		String[] statusCodeArray = node.getStDoneName().split("#");

	
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationProcessTask.getStDocId());
		condMap.put("stNodeId", legislationProcessDoc.getStNodeId());
		sortMap.put("dtPubDate", "ASC");
		// 文件查询还有问题
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);

		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("infoPage", infoPage);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		legislationProcessTask.setStTaskStatus(stTaskStatus);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("statusCodeArray", statusCodeArray);
		request.setAttribute("stTaskStatus", stTaskStatus);
        if(infoPage) {
        	request.setAttribute("nodeId", "NOD_0000000105");
        }
		// 如果是doc里的nodId=170 才可以从菜单里访问
		if (StringUtils.hasText(isbanli) && "banli".equals(isbanli) && null != legislationProcessDoc && !legislationProcessDoc.getStNodeId().equals("NOD_0000000101") && !legislationProcessDoc.getStNodeId().equals("NOD_0000000103")) {
			request.setAttribute("isbanli", isbanli);
		}
		    String strDisplay = "style ='display: none;'";
			request.setAttribute("strDisplay", strDisplay);
		return methodStr;
	}

	/**
	 * 审核会议征询意见 draftPromeetInfo NOD_0000000104
	 * 
	 * @return
	 */
	private String draftPromeetInfo() {

		String isbanli = "";
		String stDocId = request.getParameter("stDocId");
		String methodStr = request.getParameter("method");
		String stComent = request.getParameter("stComent");
		String stNodeId = request.getParameter("stNodeId");
		boolean infoPage=false;
		if (!(("NOD_0000000104".equals(stNodeId))||("NOD_0000000105".equals(stNodeId)))) {
			isbanli = "banli";
		}
		String stTaskStatus = request.getParameter("stTaskStatus");
		String type = request.getParameter("type");
		String reportLeadershipReview = request.getParameter("reportLeadershipReview");// 报审领导
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		//审核会议待审草案 
		if("NOD_0000000105".equals(stNodeId)) {
			 infoPage=true;
	    }
	    	stNodeId = "NOD_0000000104";
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null");
		LegislationProcessTask legislationProcessTask = new LegislationProcessTask();
		for (LegislationProcessTask legislationTask : list) {
			legislationProcessTask = legislationTask;
		}

		if (!StringUtils.hasText(legislationProcessTask.getStTaskId()) && !StringUtils.hasText(legislationProcessTask.getStNodeId())) {
			// 新的task的时候允许弹出
			isbanli = "";
			legislationProcessTask.setStDocId(stDocId);
			legislationProcessTask.setStFlowId(legislationProcessDoc.getStDocName());
			legislationProcessTask.setStNodeId("NOD_0000000104");
			legislationProcessTask.setStNodeName("审核会议征询意见");
			legislationProcessTask.setStTaskStatus("TODO");
			legislationProcessTask.setDtOpenDate(new Date());

		}

		WegovSimpleNode node = (WegovSimpleNode) wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='" + stNodeId + "'").get(0);
		String[] statusCodeArray = node.getStDoneName().split("#");

		/*
		 * List<LegislationProcessTaskdetail> taskDetails =
		 * legislationProcessTaskdetailService.
		 * findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='"
		 * +legislationProcessTask.getStTaskId()+"' order by t.stTaskdetailId");
		 * LegislationProcessTaskdetail taskDetail=new LegislationProcessTaskdetail();
		 * if(taskDetails.size()>0) { taskDetail=taskDetails.get(0); }else {
		 * taskDetail.setStTaskId(legislationProcessTask.getStTaskId());
		 * taskDetail.setStTaskStatus(legislationProcessTask.getStTaskStatus());
		 * taskDetail.setStTitle(legislationProcessTask.getStNodeName());
		 * legislationProcessTaskdetailService.add(taskDetail); }
		 */
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationProcessTask.getStDocId());
		condMap.put("stNodeId", legislationProcessDoc.getStNodeId());
		sortMap.put("dtPubDate", "ASC");
		// 文件查询还有问题
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);

		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("infoPage", infoPage);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("statusCodeArray", statusCodeArray);
		request.setAttribute("stTaskStatus", legislationProcessTask.getStTaskStatus());
        if(infoPage) {
        	request.setAttribute("nodeId", "NOD_0000000105");
        }
		// 如果是doc里的nodId=170 才可以从菜单里访问
		if (StringUtils.hasText(isbanli) && "banli".equals(isbanli) && null != legislationProcessDoc && !legislationProcessDoc.getStNodeId().equals("NOD_0000000101") && !legislationProcessDoc.getStNodeId().equals("NOD_0000000103")) {
			request.setAttribute("isbanli", isbanli);
		}

		// request.setAttribute("taskDetail", taskDetail);
		if (statusCodeArray.length > 0 && statusCodeArray[0].equals(stTaskStatus)) {// 待审批
			if (StringUtils.hasText(type)) {// 保存、提交操作
				if (StringUtils.hasText(stComent) && StringUtils.hasText(reportLeadershipReview)) {
					legislationProcessTask.setStBakOne(stComent);// 记录报审说明
					legislationProcessTask.setStBakTwo(reportLeadershipReview);// 送审领导
				}
			}
		} else if (statusCodeArray.length > 0 && statusCodeArray[1].equals(stTaskStatus)) {// 录入意见
			if (StringUtils.hasText(type)) {// 保存、提交操作
				if (StringUtils.hasText(stComent)) {
					legislationProcessTask.setStComment1(stComent);// 记录领导意见
				}
			}
		}
		if (StringUtils.hasText(type)) {// 保存或提交操作
			if (!StringUtils.hasText(legislationProcessTask.getStTaskId())) {// 如果task没有id再添加
				String taskid = legislationProcessTaskService.addObj(legislationProcessTask);
				legislationProcessTask = legislationProcessTaskService.findById(taskid);
			}

			// doc里草案如果不是170 则改为170 审核会议
			if (!legislationProcessDoc.getStNodeId().equals("NOD_0000000170")) {
				WegovSimpleNode node170 = (WegovSimpleNode) wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='NOD_0000000170'").get(0);
				legislationProcessDoc.setStNodeId("NOD_0000000170");
				legislationProcessDoc.setStNodeName(node170.getStNodeName());
				legislationProcessDocService.update(legislationProcessDoc);
			}

			if (!StringUtils.hasText(legislationProcessTask.getStUserId())) {// 如果没有则再添加
				UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
				legislationProcessTask.setStUserId(currentPerson.getUserId());
				legislationProcessTask.setStUserName(currentPerson.getName());
				legislationProcessTask.setStRoleId(session.getAttribute("userRoleId").toString());
				legislationProcessTask.setStRoleName(session.getAttribute("userRole").toString());
				legislationProcessTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
				legislationProcessTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
				legislationProcessTaskService.update(legislationProcessTask);
			}

		}
		if ("submit".equals(type)) {// 提交操作，修改task状态 并新增一个task
			int position = -1;
			for (int i = 0; i < statusCodeArray.length; i++) {
				String each = statusCodeArray[i];
				if (each.equals(stTaskStatus))
					position = i;
			}
			legislationProcessTask.setStTaskStatus(statusCodeArray[position + 1]);

			if (statusCodeArray.length > 0 && statusCodeArray[statusCodeArray.length - 1].equals(legislationProcessTask.getStTaskStatus())) {// 最后一个状态的时候
				// 需要新添加下一个节点的TODO状态的task
				legislationProcessTask.setDtCloseDate(new Date());

				LegislationProcessTask nextlegislationProcessTask = new LegislationProcessTask();
				nextlegislationProcessTask.setStDocId(stDocId);
				nextlegislationProcessTask.setStNodeId(node.getStNextNode());
				nextlegislationProcessTask.setStNodeName(wegovSimpleNodeService.findById(node.getStNextNode()).getStNodeName());
				nextlegislationProcessTask.setStTaskStatus("TODO");
				nextlegislationProcessTask.setStFlowId(legislationProcessDoc.getStDocName());
				nextlegislationProcessTask.setDtOpenDate(new Date());
				legislationProcessTaskService.add(nextlegislationProcessTask);
			}
			legislationProcessTaskService.update(legislationProcessTask);
		}
		return methodStr;
	}

	/**
	 * 审核会议意见反馈 draftCheckmeetFeedback NOD_0000000109
	 * 
	 * @return
	 */
	private String draftCheckmeetFeedback() {
		String stDocId = request.getParameter("stDocId");
		String methodStr = request.getParameter("method");
		String stComent = request.getParameter("stComent");
		String stNodeId = request.getParameter("stNodeId");
		String stTaskStatus = request.getParameter("stTaskStatus");
		String type = request.getParameter("type");
		String reportLeadershipReview = request.getParameter("reportLeadershipReview");// 报审领导
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		if (!StringUtils.hasText(stNodeId)) {
			stNodeId = "NOD_0000000109";
		}
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='" + stNodeId + "' and t.stEnable is null");
		LegislationProcessTask legislationProcessTask = new LegislationProcessTask();
		for (LegislationProcessTask legislationTask : list) {
			legislationProcessTask = legislationTask;
		}
		//获得对应的审核会议对象
		LegislationCheckmeeting legislationCheckmeeting=new LegislationCheckmeeting();
		List<LegislationCheckmeeting> legislationCheckmeetingList = legislationCheckmeetingService.findByHQL("from LegislationCheckmeeting t where 1=1 and t.stDocSource like '" + legislationProcessDoc.getStDocId() + "' ");
		if(legislationCheckmeetingList.size()!=0) {
			 legislationCheckmeeting = legislationCheckmeetingList.get(0);
		}
		WegovSimpleNode node = (WegovSimpleNode) wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='" + stNodeId + "'").get(0);
		String[] statusCodeArray = node.getStDoneName().split("#");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId", legislationProcessTask.getStDocId());
		condMap.put("stNodeId", legislationProcessDoc.getStNodeId());
		sortMap.put("dtPubDate", "ASC");
		// 文件查询还有问题
		List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		request.setAttribute("legislationCheckmeeting", legislationCheckmeeting);
		request.setAttribute("legislationProcessTask", legislationProcessTask);
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationFilesList", legislationFilesList);
		request.setAttribute("statusCodeArray", statusCodeArray);

		if (StringUtils.hasText(type)) {// 保存、提交操作
			if ("TODO".equals(stTaskStatus)) {
				if (StringUtils.hasText(stComent)) {
					// 添加要保存的数据
					legislationProcessTask.setStComment1(stComent);
				}
			}

			if ("submit".equals(type)) {// 提交
				legislationProcessTask.setStTaskStatus("DONE");
				legislationProcessTask.setDtCloseDate(new Date());
			}
			legislationProcessTaskService.update(legislationProcessTask);
		}
		return methodStr;
	}
	
	/**
	 * 打开正式文本准备页面
	 * 
	 * @return
	 * @throws Exception
	 * @author sy
	 */
	@Action(value = "openDraftTextPage", results = { @Result(name = SUCCESS, location = "/legislation/openDraftTextPage.jsp") })
	public String openDraftTextPage() throws Exception {

		String stDocId = request.getParameter("stDocId") == null ? "" : request.getParameter("stDocId");
		String stNodeId = request.getParameter("stNodeId") == null ? "" : request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
		
		if (legislationProcessTaskList.size() > 0) {
			String stTaskStatus = legislationProcessTaskList.get(0).getStTaskStatus();
			request.setAttribute("nodeStatus", stTaskStatus);
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(stTaskStatus)) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			} else {
				//String stStyle = "style ='display: none;'";
				//request.setAttribute("stStyle", stStyle);
			}
			if(stTaskStatus.equals("DONE")) {
			WegovSimpleNode node = wegovSimpleNodeService.findById(stNodeId);
			String stNextNode = node.getStNextNode();
			if(stNextNode.equals("END")) {
				String strDisplay = "style ='display: none;'";
				request.setAttribute("strDisplay", strDisplay);
			}else {
			WegovSimpleNode nextNode = wegovSimpleNodeService.findById(stNextNode);
			List<LegislationProcessTask> legislationTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNextNode);
			    if(legislationTaskList.size()>0) {
                        	 String strDisplay = "style ='display: none;'";
         					request.setAttribute("strDisplay", strDisplay);
                         
			    }
			}
			    
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
		}
         
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		//request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		
		return SUCCESS;
	}
	
	
	    //保存正式文本处理，可能提交
		@Action(value = "saveSend")
		public void saveSend() throws Exception {
			JSONObject jsonObject = new JSONObject();
			String stDocId = request.getParameter("stDocId") == null ? "" : request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId") == null ? "" : request.getParameter("stNodeId");
			UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
			String userRoleId = session.getAttribute("userRoleId").toString();
			String userRole = session.getAttribute("userRole").toString();
			String stComment1 = request.getParameter("stComment1") == null ? "" : request.getParameter("stComment1");			
			String stComment2 = request.getParameter("stComment2") == null ? "" : request.getParameter("stComment2");
			String stBakOne = request.getParameter("stBakOne") == null ? "" : request.getParameter("stBakOne");
			String stBakTwo = request.getParameter("stBakTwo") == null ? "" : request.getParameter("stBakTwo");
			
			String op = request.getParameter("op") == null ? "" : request.getParameter("op");
			List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
			LegislationProcessTask legislationProcessTask=new LegislationProcessTask();
			    if (legislationProcessTaskList.size() > 0) {
				 legislationProcessTask = legislationProcessTaskList.get(0);
			    }
			        if(!stComment1.equals("")) {
				    legislationProcessTask.setStComment1(stComment1);
				    }
			        if(!stComment2.equals("")) {
			        	legislationProcessTask.setStComment2(stComment2);
					    }
			        if(!stBakOne.equals("")) {
			        	 legislationProcessTask.setStBakOne(stBakOne);
					    }
			        if(!stBakTwo.equals("")) {
			            legislationProcessTask.setStBakTwo(stBakTwo);
					    }
				    legislationProcessTaskService.update(legislationProcessTask);
			     
				//提交
				if ("submit".equals(op)) {
					String stTaskId = legislationProcessTask.getStTaskId();
					legislationFilesService.updateParentIdById(request,stTaskId);
					String stTaskStatus = legislationProcessTask.getStTaskStatus();
					if(stTaskStatus.equals("DONE")) {
						legislationProcessTaskService.nextProcess(stDocId,stNodeId,session);
					}else {
						legislationProcessTaskService.nextChildProcess(stDocId, stNodeId, userRoleId, userRole, currentPerson);	
						String str=legislationProcessTask.getStTaskStatus();
						String str1=legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId).get(0).getStTaskStatus();
						  if(str1.equals("DONE")) {
							  legislationProcessTaskService.nextProcess(stDocId,stNodeId,session);
						  }
					}
					
				}
			
			jsonObject.put("stTaskId", legislationProcessTaskList.get(0).getStTaskId());
			jsonObject.put("success", true);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(jsonObject);
			return;
		}

		/**
		 * 正式文本复核
		 * 
		 * @return
		 * @author 
		 */
		private String draft_formal__TODO() {
			String method = "draft_formal__TODO";
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
			String nodeIdStatus[] = stNodeId.split("__");
			stNodeId = nodeIdStatus[0];
			String nodeStatus = nodeIdStatus[1];
			LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
			List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
			if (legislationProcessTaskList.size() > 0) {
				Map<String, Object> condMap = new HashMap<>();
				Map<String, String> sortMap = new HashMap<>();
				condMap.put("stParentId", stDocId);
				condMap.put("stNodeId", stNodeId);
				sortMap.put("dtPubDate", "ASC");
				List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
				// 如果当前节点正好SEND，就是操作页。否则是只读页面
				if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
					List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
					request.setAttribute("LegislationExampleList", legislationExampleFilesList);
					method = "openDraftTextPage";
				} else {
					String stStyle = "style ='display: none;'";
					request.setAttribute("strDisplay", stStyle);
					method = "openDraftTextPage";
				}
				request.setAttribute("legislationFilesList", legislationFilesList);
				request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			}
			// 页面元素是legislation_process_taskdetail 表数据 ed
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stDocId", stDocId);
			request.setAttribute("nodeStatus", nodeStatus);
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("legislationProcessDoc", legislationProcessDoc);
			return method;
		}
		
		private String draft_formal_prepare() {
			String method = "draft_formal_prepare";
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");
	
			LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
			List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
			if (legislationProcessTaskList.size() > 0) {
				Map<String, Object> condMap = new HashMap<>();
				Map<String, String> sortMap = new HashMap<>();
				condMap.put("stParentId", stDocId);
				condMap.put("stNodeId", stNodeId);
				sortMap.put("dtPubDate", "ASC");
				List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			
					String stStyle = "style ='display: none;'";
					request.setAttribute("strDisplay", stStyle);
					method = "openLeaderIdeaPage";
				
				request.setAttribute("legislationFilesList", legislationFilesList);
				request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			}
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stDocId", stDocId);
			request.setAttribute("buttonStatus", null);
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("legislationProcessDoc", legislationProcessDoc);
			return method;
		}
		private String draft_formal__PRINT() {
			String method = "draft_formal__PRINT";
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
			String nodeIdStatus[] = stNodeId.split("__");
			stNodeId = nodeIdStatus[0];
			String nodeStatus = nodeIdStatus[1];
			LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
			List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
			if (legislationProcessTaskList.size() > 0) {
				Map<String, Object> condMap = new HashMap<>();
				Map<String, String> sortMap = new HashMap<>();
				condMap.put("stParentId", stDocId);
				condMap.put("stNodeId", stNodeId);
				sortMap.put("dtPubDate", "ASC");
				List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
				// 如果当前节点正好SEND，就是操作页。否则是只读页面
				if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
					List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
					request.setAttribute("LegislationExampleList", legislationExampleFilesList);
					method = "openDraftTextPage";
				} else {
					String stStyle = "style ='display: none;'";
					request.setAttribute("strDisplay", stStyle);
					method = "openDraftTextPage";
				}
				request.setAttribute("legislationFilesList", legislationFilesList);
				request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			}
			// 页面元素是legislation_process_taskdetail 表数据 ed
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stDocId", stDocId);
			request.setAttribute("nodeStatus", nodeStatus);
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("legislationProcessDoc", legislationProcessDoc);
			return method;
		}
		
		private String draft_formal__ONLINE() {
			String method = "draft_formal__ONLINE";
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
			String nodeIdStatus[] = stNodeId.split("__");
			stNodeId = nodeIdStatus[0];
			String nodeStatus = nodeIdStatus[1];
			LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
			List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
			if (legislationProcessTaskList.size() > 0) {
				Map<String, Object> condMap = new HashMap<>();
				Map<String, String> sortMap = new HashMap<>();
				condMap.put("stParentId", stDocId);
				condMap.put("stNodeId", stNodeId);
				sortMap.put("dtPubDate", "ASC");
				List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
				// 如果当前节点正好SEND，就是操作页。否则是只读页面
				if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
					List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
					request.setAttribute("LegislationExampleList", legislationExampleFilesList);
					method = "openDraftTextPage";
				} else {
					String stStyle = "style ='display: none;'";
					request.setAttribute("strDisplay", stStyle);
					method = "openDraftTextPage";
				}
				request.setAttribute("legislationFilesList", legislationFilesList);
				request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			}
			// 页面元素是legislation_process_taskdetail 表数据 ed
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stDocId", stDocId);
			request.setAttribute("nodeStatus", nodeStatus);
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("legislationProcessDoc", legislationProcessDoc);
			return method;
		}
		
		private String draft_bakup__TODO() {
			String method = "draft_bakup__TODO";
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
			String nodeIdStatus[] = stNodeId.split("__");
			stNodeId = nodeIdStatus[0];
			String nodeStatus = nodeIdStatus[1];
			LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
			List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
			if (legislationProcessTaskList.size() > 0) {
				Map<String, Object> condMap = new HashMap<>();
				Map<String, String> sortMap = new HashMap<>();
				condMap.put("stParentId", stDocId);
				condMap.put("stNodeId", stNodeId);
				sortMap.put("dtPubDate", "ASC");
				List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
				// 如果当前节点正好SEND，就是操作页。否则是只读页面
				if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
					List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
					request.setAttribute("LegislationExampleList", legislationExampleFilesList);
					method = "openDraftTextPage";
				} else {
					String stStyle = "style ='display: none;'";
					request.setAttribute("strDisplay", stStyle);
					method = "openDraftTextPage";
				}
				request.setAttribute("legislationFilesList", legislationFilesList);
				request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			}
			// 页面元素是legislation_process_taskdetail 表数据 ed
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stDocId", stDocId);
			request.setAttribute("nodeStatus", nodeStatus);
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("legislationProcessDoc", legislationProcessDoc);
			return method;
		}
		
		private String draft_bakup__DOING() {
			String method = "draft_bakup__DOING";
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");// 图中的节点，再状态
			String nodeIdStatus[] = stNodeId.split("__");
			stNodeId = nodeIdStatus[0];
			String nodeStatus = nodeIdStatus[1];
			LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
			List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
			if (legislationProcessTaskList.size() > 0) {
				Map<String, Object> condMap = new HashMap<>();
				Map<String, String> sortMap = new HashMap<>();
				condMap.put("stParentId", stDocId);
				condMap.put("stNodeId", stNodeId);
				sortMap.put("dtPubDate", "ASC");
				List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
				// 如果当前节点正好SEND，就是操作页。否则是只读页面
				if (nodeStatus.equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
					List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
					request.setAttribute("LegislationExampleList", legislationExampleFilesList);
					method = "openDraftTextPage";
				} else {
					String stStyle = "style ='display: none;'";
					request.setAttribute("strDisplay", stStyle);
					method = "openDraftTextPage";
				}
				request.setAttribute("legislationFilesList", legislationFilesList);
				request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			}
			// 页面元素是legislation_process_taskdetail 表数据 ed
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stDocId", stDocId);
			request.setAttribute("nodeStatus", nodeStatus);
			request.setAttribute("requestUrl", request.getRequestURI());
			request.setAttribute("legislationProcessDoc", legislationProcessDoc);
			return method;
		}
		
		
		
		private String check_meeting__TODO() {
			String method = request.getParameter("method");
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stDocSource", stDocId);
			List<LegislationCheckmeeting> findByList = legislationCheckmeetingService.findByList(condMap, sortMap);
			String stMeetingId="";
			LegislationCheckmeeting auditMeeting=new LegislationCheckmeeting();
			if(findByList.size()>0) {
				auditMeeting = findByList.get(0);
				   stMeetingId = auditMeeting.getStMeetingId();
			  }
			request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
			// 可选择的草案
			LegislationProcessDoc findById = legislationProcessDocService.findById(stDocId);
			List<LegislationProcessDoc> legislationProcessDocList = new ArrayList<LegislationProcessDoc>();
			legislationProcessDocList.add(findById);
			request.setAttribute("legislationProcessDocList", legislationProcessDocList);
			
			// 可选择的计划
			LegislationPlan findById2=new LegislationPlan();
			String stPlanSource = auditMeeting.getStPlanSource();
              if(StringUtil.isNotEmpty(stPlanSource)) {
            	   findById2 = legislationPlanService.findById(stPlanSource);  
              }
			List<LegislationPlan> legislationPlanList = new ArrayList<LegislationPlan>();
			legislationPlanList.add(findById2);
			request.setAttribute("legislationPlanList",legislationPlanList );
			if (StringUtil.isEmpty(stMeetingId)) {
				request.setAttribute("legislationCheckmeeting", new LegislationCheckmeeting());
			} else {
				request.setAttribute("legislationCheckmeeting", auditMeeting);
			}
			request.setAttribute("stTaskStatus", "TODO");
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stMeetingId", stMeetingId);
			request.setAttribute("requestUrl", request.getRequestURI());
			String stStyle = "style ='display: none;'";
			request.setAttribute("strDisplay", stStyle);
			return method;
		}
		
		
		private String check_meeting__FEEDBACK() {
			String method = request.getParameter("method");
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stDocSource", stDocId);
			List<LegislationCheckmeeting> findByList = legislationCheckmeetingService.findByList(condMap, sortMap);
			String stMeetingId="";
			LegislationCheckmeeting auditMeeting=new LegislationCheckmeeting();
			if(findByList.size()>0) {
				auditMeeting = findByList.get(0);
				   stMeetingId = auditMeeting.getStMeetingId();
			  }
			request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
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
			condMap.clear();
			sortMap.clear();
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
						condMap.clear();
						sortMap.clear();
						condMap.put("stPlanId" + "List", stParentIdList1);
						sortMap.put("dtCreateDate", "ASC");
					    legislationPlanList = legislationPlanService.findByList(condMap, sortMap);
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
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stMeetingId", stMeetingId);
			request.setAttribute("requestUrl", request.getRequestURI());
			String stStyle = "style ='display: none;'";
			request.setAttribute("strDisplay", stStyle);
			return method;
		}
		
		
		private String check_meeting__INPUT() {
			String method = request.getParameter("method");
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stDocSource", stDocId);
			List<LegislationCheckmeeting> findByList = legislationCheckmeetingService.findByList(condMap, sortMap);
			String stMeetingId="";
			LegislationCheckmeeting auditMeeting=new LegislationCheckmeeting();
			if(findByList.size()>0) {
				auditMeeting = findByList.get(0);
				   stMeetingId = auditMeeting.getStMeetingId();
			  }
			request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
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
			condMap.clear();
			sortMap.clear();
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
			condMap.clear();
			sortMap.clear();
			condMap.put("stPlanId" + "List", stParentIdList1);
			sortMap.put("dtCreateDate", "ASC");
			 legislationPlanList = legislationPlanService.findByList(condMap, sortMap);
			}
			request.setAttribute("legislationProcessDocList", legislationProcessDocList);
			request.setAttribute("legislationPlanList", legislationPlanList);
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stMeetingId", stMeetingId);
			request.setAttribute("requestUrl", request.getRequestURI());
			String stStyle = "style ='display: none;'";
			request.setAttribute("strDisplay", stStyle);
			return method;
		}
		
		
		private String check_meeting__AFFIRM() {
			String method = request.getParameter("method");
			String stDocId = request.getParameter("stDocId");
			String stNodeId = request.getParameter("stNodeId");
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stDocSource", stDocId);
			List<LegislationCheckmeeting> findByList = legislationCheckmeetingService.findByList(condMap, sortMap);
			String stMeetingId="";
			LegislationCheckmeeting legislationCheckmeeting=new LegislationCheckmeeting();
			if(findByList.size()>0) {
				   legislationCheckmeeting = findByList.get(0);
				   stMeetingId = legislationCheckmeeting.getStMeetingId();
			  }
			request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
	
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
			request.setAttribute("nodeId", stNodeId);
			request.setAttribute("stMeetingId", stMeetingId);
			request.setAttribute("requestUrl", request.getRequestURI());
			String stStyle = "style ='display: none;'";
			request.setAttribute("strDisplay", stStyle);
			return method;
		}
		@Action(value = "query_doc_num")
		public void query_doc_num() throws IOException {
			PrintWriter out = response.getWriter(); 
			net.sf.json.JSONArray array=new net.sf.json.JSONArray();
            List<Map> mapList = legislationProcessDocService.findMapByHQL("select new map(t.stNodeName as stNodeName ,count(t.stNodeId) as count) from LegislationProcessDoc d inner join WegovSimpleNode t on d.stNodeId=t.stNodeId where 1=1 and t.stParentNode='main' group by t.stNodeName");		  		
			if (mapList.size() > 0) {
				 for (Map map : mapList) {
						array.add(net.sf.json.JSONObject.fromObject(map));
				   }
				out.write(array.toString());
			} else {
				out.write("");
			}
		}
		
		@Action(value = "query_doc_num1")
		public void query_user_num() throws IOException {
			PrintWriter out = response.getWriter(); 
			net.sf.json.JSONArray array=new net.sf.json.JSONArray();
            List<Map> mapList = legislationProcessDocService.findMapByHQL("select new map(d.stUserName as stUserName,count(d.stUserName) as count) \r\n" + 
            		"  from LegislationProcessDoc d inner join LegislationProcessTask t\r\n" + 
            		"  on d.stDocId=t.stDocId where t.stNodeId='NOD_0000000103' and\r\n" + 
            		"  t.stTaskStatus='DOING'  group by d.stUserName");		  		
			if (mapList.size() > 0) {
				 for (Map map : mapList) {
						array.add(net.sf.json.JSONObject.fromObject(map));
				   }
				out.write(array.toString());
			} else {
				out.write("");
			}
		}
		
		@Action(value = "query_doc_info", results = { @Result(name = "queryDocInfo", location = "/legislation/indexPage.jsp")})				
		public String queryDocInfo() throws IOException, NumberFormatException, FzbDaoException {
            List<LegislationProcessDoc> docList = legislationProcessDocService.findByHQL("select d \r\n" + 
            		"  from LegislationProcessDoc d inner join LegislationProcessTask t\r\n" + 
            		"  on d.stDocId=t.stDocId where t.stNodeId='NOD_0000000103' and\r\n" + 
            		"  t.stTaskStatus='DOING'");		
            
            request.setAttribute("legislationProcessDocList", docList);
       
            return "queryDocInfo";
			
		}
		
		
		@Action(value = "query_doc_info1")
		public void query_doc_info1() throws IOException {
			PrintWriter out = response.getWriter(); 
			net.sf.json.JSONArray array=new net.sf.json.JSONArray();
			List<LegislationProcessDoc> docList = legislationProcessDocService.findByHQL("select d \r\n" + 
	            		"  from LegislationProcessDoc d inner join LegislationProcessTask t\r\n" + 
	            		"  on d.stDocId=t.stDocId where t.stNodeId='NOD_0000000103' and\r\n" + 
	            		"  t.stTaskStatus='DOING'");	  		
			if (docList.size() > 0) {
				 for (LegislationProcessDoc doc : docList) {
						array.add(net.sf.json.JSONObject.fromObject(doc));
				   }
				out.write(array.toString());
			} else {
				out.write("");
			}
		}
	private String dealFinish() {
		legislationProcessTaskService.dealFinish(request, session);

		return null;
	}

	/**
	 * 跳转新增样本页面
	 * @return
	 */
	private String openAddExamplePage(){
		LegislationExample legislationExample=new LegislationExample();
		request.setAttribute("legislationExample",legislationExample);
		request.setAttribute("button","add");
		return pageController();
	}

	/**
	 * 跳转编辑样本页面
	 * @return
	 */
	private String openEditExamplePage(){
		String stExampleId=request.getParameter("stExampleId");
		LegislationExample legislationExample=legislationExampleService.findById(stExampleId);
		request.setAttribute("legislationExample",legislationExample);
		request.setAttribute("button","edit");
		return pageController();
	}

	/**
	 *跳转查看样本页面
	 * @return
	 */
	private String openExampleInfoPage(){
		String stExampleId=request.getParameter("stExampleId");
		LegislationExample legislationExample=legislationExampleService.findById(stExampleId);
		request.setAttribute("legislationExample",legislationExample);
		request.setAttribute("button","info");
		return pageController();
	}

	/**
	 * 删除样本
	 * @return
	 */
	private String deleteSimple(){
		String stExampleId=request.getParameter("stExampleId");
		LegislationExample legislationExample=legislationExampleService.findById(stExampleId);
		legislationExample.setStStatus("NOUSED");
		legislationExampleService.update(legislationExample);
		return null;
	}

	/**
	 * 保存样本
	 * @return
	 * @throws IOException
	 */
	private String saveExampleFile() throws IOException {
		JSONObject jsonObject=new JSONObject();
		legislationExampleService.saveExampleFile(request,session,upload,uploadFileName);
		jsonObject.put("success",true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}
}
