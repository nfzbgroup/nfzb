package com.wonders.fzb.legislation.web;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.services.*;
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

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


/**
 * LegislationProcessDoc action接口
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

	@Action(value = "draft_doc_info", results = {@Result(name = "openAddPage", location = "/legislation/legislationProcessManager_add.jsp"),
			@Result(name = "openEditPage", location = "/legislation/legislationProcessManager_add.jsp"),
			@Result(name = "openInfoPage", location = "/legislation/legislationProcessManager_info.jsp"),
			@Result(name = "openDraftHistoryPage",location = "/legislation/legislationProcessManager_draftHistory.jsp"),
			@Result(name = "openSeparatePage",location = "/legislation/legislationProcessManager_separate.jsp"),
			@Result(name = "openDemonstrationPage",location = "/legislation/legislationProcessManager_demonstration.jsp"),
			@Result(name = "openExpertDemonstrationPage",location = "/legislation/legislationProcessManager_expertDemonstration.jsp"),
			@Result(name = "openLegislationDemonstrationPage",location = "/legislation/legislationProcessManager_legislationDemonstration.jsp"),
			@Result(name = "openUnitDemonstrationPage",location = "/legislation/legislationProcessManager_unitDemonstration.jsp"),
			@Result(name = "openExpertInfoPage",location = "/legislation/legislationProcessManager_expertInfo.jsp"),
			@Result(name = "openLegislationInfoPage",location = "/legislation/legislationProcessManager_legislationInfo.jsp"),
			@Result(name = "openAddAuditMeetingPage",location = "/legislation/legislationProcessManager_auditMeeting.jsp"),
			@Result(name = "openEditAuditMeetingPage",location = "/legislation/legislationProcessManager_auditMeeting.jsp"),
			@Result(name = "openAuditMeetingInfoPage",location = "/legislation/legislationProcessManager_auditMeetingInfo.jsp"),
			@Result(name = "openSeparateTabPage",location = "/legislation/legislationProcessManager_separateTab.jsp"),
			@Result(name = "openInfoTabPage",location = "/legislation/legislationProcessManager_infoTab.jsp"),
			@Result(name = "openUnitTabPage",location = "/legislation/legislationProcessManager_unitTab.jsp"),
			@Result(name = "openAuditMeetingTabPage",location = "/legislation/legislationProcessManager_auditMeetingTab.jsp"),
			@Result(name = "openOnlineTabPage",location = "/legislation/legislationProcessManager_onlineTab.jsp"),
			@Result(name = "openHeartMeetingAddPage",location = "/legislation/legislationProcessManager_legislationForm.jsp"),
			@Result(name = "openHeartMeetingEditPage",location = "/legislation/legislationProcessManager_legislationForm.jsp"),
			@Result(name = "openHeartMeetingCheckInfoPage",location = "/legislation/legislationProcessManager_checkInfo.jsp"),
			@Result(name = "openProMeetCheckInfoPage",location = "/legislation/legislationProcessManager_checkInfo.jsp"),
			@Result(name = "openMeetingBeforeInfoPage",location = "/legislation/legislationProcessManager_legislationBeforeInfo.jsp"),
			@Result(name = "openMeetingAfterInfoPage",location = "/legislation/legislationProcessManager_legislationAfterInfo.jsp"),
			@Result(name = "openExpertAddPage",location = "/legislation/legislationProcessManager_expertForm.jsp"),
			@Result(name = "openCheckExplainPage",location = "/legislation/legislationProcessManager_checkExplain.jsp"),
			@Result(name = "openLeaderIdeaPage",location = "/legislation/legislationProcessManager_leaderIdea.jsp"),
			@Result(name = "openExpertEditPage",location = "/legislation/legislationProcessManager_expertForm.jsp"),
			@Result(name = "openUnitAddPage",location = "/legislation/legislationProcessManager_unitForm.jsp"),
			@Result(name = "openUnitEditPage",location = "/legislation/legislationProcessManager_unitForm.jsp"),
			@Result(name = "openUnitSeekPage",location = "/legislation/legislationProcessManager_unitSeek.jsp"),
			@Result(name = "openUnitReceivePage",location = "/legislation/legislationProcessManager_unitReceive.jsp"),
			@Result(name = "openEditMeetingPage",location = "/legislation/legislationProcessManager_auditMeeting.jsp"),
			@Result(name = "openUnitInfoPage",location = "/legislation/legislationProcessManager_unitInfo.jsp"),
			@Result(name = "openUnitAddOpinionPage",location = "/legislation/legislationProcessManager_unitAddOpinion.jsp"),
			@Result(name = "openHandleDemonstrationPage",location = "/legislation/legislationProcessManager_handleDemonstration.jsp"),
			@Result(name = "openProcessIndexPage",location = "/legislation/process_index.jsp"),
			@Result(name = "openLegislationReceivePage",location = "/legislation/legislationProcessManager_legislationReceive.jsp"),
			@Result(name = "openLegislationCensorshipPage",location = "/legislation/legislationProcessManager_legislationCensorship.jsp"),
			@Result(name = "openLegislationReleasePage",location = "/legislation/legislationProcessManager_legislationRelease.jsp"),
			@Result(name = "openLegislationRegistrationPage",location = "/legislation/legislationProcessManager_legislationRegistration.jsp"),
            @Result(name = "openOnlineDemonstrationPage",location = "/legislation/legislationProcessManager_onlineDemonstration.jsp"),
            @Result(name = "openOnlineInfoPage",location = "/legislation/legislationProcessManager_onlineInfo.jsp"),
			@Result(name = "openOnlineSealPage",location = "/legislation/legislationProcessManager_onlineSeal.jsp"),
			@Result(name = "openOnlineCensorshipPage",location = "/legislation/legislationProcessManager_onlineCensorship.jsp"),
			@Result(name = "openOnlineReleasePage",location = "/legislation/legislationProcessManager_onlineRelease.jsp"),
			@Result(name = "openOnlineSummaryPage",location = "/legislation/legislationProcessManager_onlineSummary.jsp"),
			@Result(name = "openOnlinePublishPage",location = "/legislation/legislationProcessManager_onlinePublish.jsp"),
			@Result(name = "openOnlineSummaryInfoPage",location = "/legislation/legislationProcessManager_onlineSummaryInfo.jsp")})
	public String legislationProcessDoc_form() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}

	private String openProcessIndexPage(){
		String unitEditClass="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
		Boolean unitEditDisabled=true;
		String unitSealClass="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_yellow";
		Boolean unitSealDisabled=true;
		String unitSendClass="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_red";
		Boolean unitSendDisabled=true;
		String unitReceiveClass="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red";
		Boolean unitReceiveDisabled=true;
		String expertBeforeClass="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
		Boolean expertBeforeDisabled=true;
		String expertAfterClass="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_t";
		Boolean expertAfterDisabled=true;
		String legislationEditClass="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
		Boolean legislationEditDisabled=true;
		String legislationReceiveClass="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red";
		Boolean legislationReceiveDisabled=true;
		String legislationCensorshipClass="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow";
		Boolean legislationCensorshipDisabled=true;
		String legislationReleaseClass="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple";
		Boolean legislationReleaseDisabled=true;
		String legislationRegistrationClass="cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple";
		Boolean legislationRegistrationDisabled=true;
		String legislationFileClass="cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_t";
		Boolean legislationFileDisabled=true;
		String onlineEditClass="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
		Boolean onlineEditDisabled=true;
        String onlineSealClass="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red";
        Boolean onlineSealDisabled=true;
        String onlineCensorshipClass="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow";
        Boolean onlineCensorshipDisabled=true;
        String onlineReleaseClass="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple";
        Boolean onlineReleaseDisabled=true;
        String onlineSummaryClass="cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple";
        Boolean onlineSummaryDisabled=true;
        String onlinePublishClass="cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_purple";
        Boolean onlinePublishDisabled=true;
		String stDocId=request.getParameter("stDocId");
		String stDocName=request.getParameter("stDocName");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId=currentPerson.getTeamInfos().get(0).getId();
		String stNodeId="NOD_0000000120";
		List<LegislationProcessTask> unitEditList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(unitEditList.size()>0){
			if("TODO".equals(unitEditList.get(0).getStTaskStatus())){
				if("U_3_7".equals(teamId)){
					unitEditClass="cell row_items row_item2 bcg_green border_width border_style border_radius border_color_t";
					unitEditDisabled=false;
				}
			}else{
				unitEditClass="cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t";
				unitSealClass="cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_yellow";
				if("U_3_7".equals(teamId)||"U_3_1".equals(teamId)){
					unitEditDisabled=false;
				}
			}
		}else {
			if("U_3_7".equals(teamId)){
				unitEditClass="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t";
				unitEditDisabled=false;
			}
		}
		stNodeId="NOD_0000000121";
		List<LegislationProcessTask> unitSendList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(unitSendList.size()>0){
			if("TODO".equals(unitSendList.get(0).getStTaskStatus())){
				if("U_3_1".equals(teamId)){
					unitSendClass="cell row_items row_item4 bcg_green border_width border_style border_radius border_color_red";
					unitSendDisabled=false;
				}
			}else {
				unitSendClass="cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_red";
				if("U_3_7".equals(teamId)||"U_3_1".equals(teamId)){
					unitReceiveDisabled=false;
				}
			}
		}
		stNodeId="NOD_0000000122";
		List<LegislationProcessTask> unitReceiveList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		List<LegislationProcessTask> unitReceiveFinishList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null and t.stComment2 is null");
		if(unitReceiveList.size()>0){
			if(unitReceiveFinishList.size()>0){
				if("U_3_7".equals(teamId)||"U_3_1".equals(teamId)){
					unitReceiveClass="cell row_items row_item5 bcg_green border_width border_style border_radius border_color_red";
				}
			}else {
				unitReceiveClass="cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_red";
			}
		}
		stNodeId="NOD_0000000150";
		List<LegislationProcessTask> expertBeforeList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(expertBeforeList.size()>0){
			if("TODO".equals(expertBeforeList.get(0).getStTaskStatus())){
				if("U_3_7".equals(teamId)){
					expertBeforeClass="cell row_items row_item2 bcg_green border_width border_style border_radius border_color_t";
				}
			}else{
				expertBeforeClass="cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t";
			}
			if("U_3_7".equals(teamId)){
				expertBeforeDisabled=false;
			}
		}else {
			if("U_3_7".equals(teamId)){
				expertBeforeDisabled=false;
			}
		}
		stNodeId="NOD_0000000151";
		List<LegislationProcessTask> expertAfterList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(expertAfterList.size()>0){
			if("TODO".equals(expertAfterList.get(0).getStTaskStatus())){
				if("U_3_7".equals(teamId)){
					expertAfterClass="cell row_items row_item3 bcg_green border_width border_style border_radius border_color_t";
				}
			}else{
				expertAfterClass="cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_t";
			}
			if("U_3_7".equals(teamId)){
				expertAfterDisabled=false;
			}
		}
		stNodeId="NOD_0000000140";
		List<LegislationProcessTask> legislationEditList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(legislationEditList.size()>0){
			if("TODO".equals(legislationEditList.get(0).getStTaskStatus())){
				if("U_3_7".equals(teamId)){
					legislationEditClass="cell row_items row_item2 bcg_green border_width border_style border_radius border_color_t";
					legislationEditDisabled=false;
				}
			}else{
				legislationEditClass="cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t";
				if("U_3_7".equals(teamId)||"U_3_1".equals(teamId)){
					legislationEditDisabled=false;
				}
			}
		}else {
			if("U_3_7".equals(teamId)){
				legislationEditDisabled=false;
			}
		}
		stNodeId="NOD_0000000141";
		List<LegislationProcessTask> legislationReceiveList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(legislationReceiveList.size()>0){
			String stTaskStatus=legislationReceiveList.get(0).getStTaskStatus();
			if("TODO".equals(stTaskStatus)){
				if("U_3_1".equals(teamId)){
					legislationReceiveClass="cell row_items row_item3 bcg_green border_width border_style border_radius border_color_red";
					legislationReceiveDisabled=false;
				}
			}else{
				legislationReceiveClass="cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_red";
				if("SEND".equals(stTaskStatus)){
					if("U_3_1".equals(teamId)){
						legislationCensorshipClass="cell row_items row_item4 bcg_green border_width border_style border_radius border_color_yellow";
						legislationCensorshipDisabled=false;
					}
				}else{
					legislationCensorshipClass="cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_yellow";
					if ("PUBLISH".equals(stTaskStatus)) {
						if("U_3_1".equals(teamId)){
							legislationReleaseClass="cell row_items row_item5 bcg_green border_width border_style border_radius border_color_purple";
							legislationReleaseDisabled=false;
							legislationCensorshipDisabled=false;
						}
					} else {
						legislationReleaseClass="cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_purple";
						if ("GATHER".equals(stTaskStatus)) {
							if ("U_3_1".equals(teamId)) {
								legislationRegistrationClass="cell row_items row_item6 bcg_green border_width border_style border_radius border_color_purple";
								legislationRegistrationDisabled=false;
								legislationCensorshipDisabled=false;
								legislationReleaseDisabled=false;
							}
						} else {
							legislationRegistrationClass="cell row_items row_item6 bcg_blue border_width border_style border_radius border_color_purple";
							if ("RESULT".equals(stTaskStatus)) {
								if ("U_3_1".equals(teamId)) {
									legislationFileClass="cell row_items row_item7 bcg_green border_width border_style border_radius border_color_t";
									legislationFileDisabled=false;
									legislationRegistrationDisabled=false;
									legislationCensorshipDisabled=false;
									legislationReleaseDisabled=false;
								}
							} else {
								legislationFileClass="cell row_items row_item7 bcg_blue border_width border_style border_radius border_color_t";
								if ("U_3_1".equals(teamId)) {
									legislationFileDisabled=false;
									legislationRegistrationDisabled=false;
									legislationCensorshipDisabled=false;
									legislationReleaseDisabled=false;
								}
								if("U_3_7".equals(teamId)){
									legislationFileDisabled=false;
								}
							}
						}
					}
				}
			}
		}
		stNodeId="NOD_0000000130";
		List<LegislationProcessTask> onlineEditList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(onlineEditList.size()>0){
			if("TODO".equals(onlineEditList.get(0).getStTaskStatus())){
				if("U_3_7".equals(teamId)){
					onlineEditClass="cell row_items row_item2 bcg_green border_width border_style border_radius border_color_t";
					onlineEditDisabled=false;
				}
			}else{
				onlineEditClass="cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t";
				if("U_3_7".equals(teamId)||"U_3_1".equals(teamId)){
					onlineEditDisabled=false;
				}
			}
		}else {
			if("U_3_7".equals(teamId)){
				onlineEditDisabled=false;
			}
		}
		stNodeId="NOD_0000000131";
		List<LegislationProcessTask> onlineSealList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(onlineSealList.size()>0){
			String stTaskStatus=onlineSealList.get(0).getStTaskStatus();
			if("TODO".equals(stTaskStatus)){
				if("U_3_1".equals(teamId)){
					onlineSealClass="cell row_items row_item3 bcg_green border_width border_style border_radius border_color_red";
					onlineSealDisabled=false;
				}
			}else{
				onlineSealClass="cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_red";
				if("SEND".equals(stTaskStatus)){
					if("U_3_1".equals(teamId)){
						onlineCensorshipClass="cell row_items row_item4 bcg_green border_width border_style border_radius border_color_yellow";
						onlineCensorshipDisabled=false;
					}
				}else{
					onlineCensorshipClass="cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_yellow";
					if ("PUBLISH".equals(stTaskStatus)) {
						if("U_3_1".equals(teamId)){
							onlineReleaseClass="cell row_items row_item5 bcg_green border_width border_style border_radius border_color_purple";
							onlineReleaseDisabled=false;
							onlineCensorshipDisabled=false;
						}
					} else {
						onlineReleaseClass="cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_purple";
						if ("GATHER".equals(stTaskStatus)) {
							if ("U_3_1".equals(teamId)) {
								onlineSummaryClass="cell row_items row_item6 bcg_green border_width border_style border_radius border_color_purple";
								onlineSummaryDisabled=false;
								onlineCensorshipDisabled=false;
								onlineReleaseDisabled=false;
							}
						} else {
							onlineSummaryClass="cell row_items row_item6 bcg_blue border_width border_style border_radius border_color_purple";
							if ("RESULT".equals(stTaskStatus)) {
								if ("U_3_1".equals(teamId)) {
									onlinePublishClass="cell row_items row_item7 bcg_green border_width border_style border_radius border_color_purple";
									onlinePublishDisabled=false;
									onlineSummaryDisabled=false;
									onlineCensorshipDisabled=false;
									onlineReleaseDisabled=false;
								}
							} else {
								onlinePublishClass="cell row_items row_item7 bcg_blue border_width border_style border_radius border_color_purple";
								if ("U_3_1".equals(teamId)) {
									onlinePublishDisabled=false;
									onlineSummaryDisabled=false;
									onlineCensorshipDisabled=false;
									onlineReleaseDisabled=false;
								}
								if("U_3_7".equals(teamId)){
									onlinePublishDisabled=false;
								}
							}
						}
					}
				}
			}
		}
		request.setAttribute("stDocName",stDocName);
		request.setAttribute("unitEditClass",unitEditClass);
		request.setAttribute("unitEditDisabled",unitEditDisabled);
		request.setAttribute("unitSealClass",unitSealClass);
		request.setAttribute("unitSealDisabled",unitSealDisabled);
		request.setAttribute("unitSendClass",unitSendClass);
		request.setAttribute("unitSendDisabled",unitSendDisabled);
		request.setAttribute("unitReceiveClass",unitReceiveClass);
		request.setAttribute("unitReceiveDisabled",unitReceiveDisabled);

		request.setAttribute("expertBeforeClass",expertBeforeClass);
		request.setAttribute("expertBeforeDisabled",expertBeforeDisabled);
		request.setAttribute("expertAfterClass",expertAfterClass);
		request.setAttribute("expertAfterDisabled",expertAfterDisabled);

		request.setAttribute("legislationEditClass",legislationEditClass);
		request.setAttribute("legislationEditDisabled",legislationEditDisabled);
		request.setAttribute("legislationReceiveClass",legislationReceiveClass);
		request.setAttribute("legislationReceiveDisabled",legislationReceiveDisabled);
		request.setAttribute("legislationCensorshipClass",legislationCensorshipClass);
		request.setAttribute("legislationCensorshipDisabled",legislationCensorshipDisabled);
		request.setAttribute("legislationReleaseClass",legislationReleaseClass);
		request.setAttribute("legislationReleaseDisabled",legislationReleaseDisabled);
		request.setAttribute("legislationRegistrationClass",legislationRegistrationClass);
		request.setAttribute("legislationRegistrationDisabled",legislationRegistrationDisabled);
		request.setAttribute("legislationFileClass",legislationFileClass);
		request.setAttribute("legislationFileDisabled",legislationFileDisabled);

        request.setAttribute("onlineEditClass",onlineEditClass);
        request.setAttribute("onlineEditDisabled",onlineEditDisabled);
        request.setAttribute("onlineSealClass",onlineSealClass);
        request.setAttribute("onlineSealDisabled",onlineSealDisabled);
        request.setAttribute("onlineCensorshipClass",onlineCensorshipClass);
        request.setAttribute("onlineCensorshipDisabled",onlineCensorshipDisabled);
        request.setAttribute("onlineReleaseClass",onlineReleaseClass);
        request.setAttribute("onlineReleaseDisabled",onlineReleaseDisabled);
        request.setAttribute("onlineSummaryClass",onlineSummaryClass);
        request.setAttribute("onlineSummaryDisabled",onlineSummaryDisabled);
        request.setAttribute("onlinePublishClass",onlinePublishClass);
        request.setAttribute("onlinePublishDisabled",onlinePublishDisabled);
		return pageController();
	}
	private String openAddPage(){
		request.setAttribute("LegislationExampleList",queryLegislationExampleFiles());
		return pageController();
	}
	/**
	 * 查询范本
	 * @return
	 */
	private List<Map> queryLegislationExampleFiles(){
		String stNodeId = request.getParameter("stNodeId");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", stNodeId);
		sortMap.put("stExampleId", "ASC");

		List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFiles(condMap,sortMap);

		return legislationExampleFilesList;
	}
	private String openInfoPage(){
		String stDocId=request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);

		List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+stDocId+"' and t.stNodeId='NOD_0000000101' and t.stSampleId !='null' order by t.stSampleId ");
		List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+stDocId+"' and t.stNodeId='NOD_0000000101' and t.stSampleId='null' order by t.stFileId ");
		for(LegislationFiles legislationFiles:otherDocList){
			docList.add(legislationFiles);
		}
		request.setAttribute("legislationProcessDoc",legislationProcessDoc);
		request.setAttribute("docList",docList);
		return pageController();
	}

	private String openHeartMeetingCheckInfoPage(){
		String stDocId=request.getParameter("stDocId");

		request.setAttribute("checkDetails",queryDetails(stDocId,"NOD_0000000141"));
		return pageController();
	}

	private String openProMeetCheckInfoPage(){
		String stDocId=request.getParameter("stDocId");

		request.setAttribute("checkDetails",queryDetails(stDocId,"NOD_0000000104"));
		return pageController();
	}

	private List<LegislationProcessTaskdetail> queryDetails(String stDocId,String stNodeId){
		List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"'");
		if(list!=null && list.size()>0){
			String stTaskId = list.get(0).getStTaskId();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='"+stTaskId+"' order by t.stTaskdetailId");
			for(LegislationProcessTaskdetail legislationProcessTaskdetail:taskDetails){
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='"+legislationProcessTaskdetail.getStTaskdetailId()+"'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
		}
		return checkDetails;
	}
	private String openProMeetPage(){
		String stTaskStatus=request.getParameter("stTaskStatus");
		String stDocId=request.getParameter("stDocId");
        String stNodeId = request.getParameter("stNodeId");

		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"'");
		LegislationProcessTask legislationProcessTask = list.get(0);
		request.setAttribute("stTaskStatus",stTaskStatus);
		request.setAttribute("nodeId",legislationProcessTask.getStNodeId());
		request.setAttribute("stDocId",stDocId);
		request.setAttribute("stTaskId",legislationProcessTask.getStTaskId());
		request.setAttribute("requestUrl", request.getRequestURI());
		return "openCheckExplainPage";
	}
	private String openCheckExplainPage(){
		String stTaskStatus=request.getParameter("stTaskStatus");
		request.setAttribute("stTaskStatus",stTaskStatus);
		return pageController();
	}
	private String openEditPage(){
        String stNodeId = request.getParameter("stNodeId");
        Map<String, Object> condMap = new HashMap<>();
        Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", stNodeId);
        sortMap.put("stExampleId", "ASC");
        List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
        String stDocId=request.getParameter("stDocId");
        condMap.clear();
        condMap.put("stParentId",stDocId);
		condMap.put("stNodeId",stNodeId);
        sortMap.clear();
        sortMap.put("dtPubDate","ASC");
        List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
        List<Map> legislationExampleFilesList=new ArrayList<>();
        legislationExampleList.forEach((LegislationExample legislationExample)->{
            Map map=new HashMap();
            map.put("stExampleId",legislationExample.getStExampleId());
            map.put("stExampleName",legislationExample.getStExampleName());
            map.put("stNeed",legislationExample.getStNeed());
            legislationFilesList.forEach((LegislationFiles legislationFiles)->{
                if(null!=legislationFiles.getStSampleId()&&
                        legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
                    map.put("fileId",legislationFiles.getStFileId());
                    map.put("fileName",legislationFiles.getStTitle());
                    map.put("fileUrl",legislationFiles.getStFileUrl());
                }
            });
            legislationExampleFilesList.add(map);
        });
        LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
        request.setAttribute("LegislationExampleList",legislationExampleFilesList);
        request.setAttribute("legislationFilesList",legislationFilesList);
        request.setAttribute("legislationProcessDoc",legislationProcessDoc);
		return pageController();
	}
	private String openDraftHistoryPage(){
		String stDocId=request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stDocId",stDocId );
		sortMap.put("stDealId", "ASC");
		List<LegislationProcessDeal> dealList = legislationProcessDealService.findByList(condMap, sortMap);
		List<Map> legislationDeal=new ArrayList<Map>();
		for (int i = 0; i < dealList.size(); i++) {
			Map<String, Object> filesMap = new HashMap<>();
			filesMap.put("stActionName", dealList.get(i).getStActionName());
			filesMap.put("stBakOne", dealList.get(i).getStBakOne());
			filesMap.put("stUserName", dealList.get(i).getStUserName());
			filesMap.put("dtDealDate", dealList.get(i).getDtDealDate());
			List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+stDocId+"' and t.stNodeId='"+dealList.get(i).getStActionId()+"' and t.stSampleId !='null' order by t.stSampleId ");
			List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+stDocId+"' and t.stNodeId='"+dealList.get(i).getStActionId()+"' and t.stSampleId='null' order by t.stFileId ");
			for(LegislationFiles legislationFiles:otherDocList){
				docList.add(legislationFiles);
			}
			filesMap.put("docList", docList);
			legislationDeal.add(filesMap);
		}

		request.setAttribute("legislationDeal", legislationDeal);
		request.setAttribute("legislationProcessDoc",legislationProcessDoc);
		request.setAttribute("dealList", dealList);
		return pageController();
	}

	private String openSeparatePage(){
		String stDocId=request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='NOD_0000000102' and t.stEnable is null");
		for (LegislationProcessTask legislationProcessTask : list) {
			request.setAttribute("legislationProcessTask", legislationProcessTask);
		}
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		//查询分办处
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("idLike","U_3_" );
		condMap.put("unitNameLike","法规处" );
		sortMap.put("id", "ASC");
		List<TeamInfo> teamInfoList=teamInfoService.findTeamInfoList(condMap, sortMap);
		request.setAttribute("teamList", teamInfoList);
		return pageController();
	}

	private String openLeaderIdeaPage(){
		return pageController();
	}
	private String openEditMeetingPage(){
		return pageController();
	}

	private String openDemonstrationPage(){
		return pageController();
	}

	/**
	 * 办理页面--跳转网上征求意见发起页面
	 * @return
	 */
	private String openOnlineDemonstrationPage(){
        String method;
        String stDocId=request.getParameter("stDocId");
        String stNodeId=request.getParameter("stNodeId");
        LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
        List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
        Map<String, Object> condMap = new HashMap<>();
        Map<String, String> sortMap = new HashMap<>();
        condMap.put("stNode", stNodeId);
        sortMap.put("stExampleId", "ASC");
        if(legislationProcessTaskList.size()>0){
            List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
            condMap.clear();
            sortMap.clear();
            condMap.put("stParentId",stDocId);
            condMap.put("stNodeId",stNodeId);
            sortMap.put("dtPubDate","ASC");
            List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
            if("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
                List<Map> legislationExampleFilesList =new ArrayList<>();
                legislationExampleList.forEach((LegislationExample legislationExample)->{
                    Map map=new HashMap();
                    map.put("stExampleId",legislationExample.getStExampleId());
                    map.put("stExampleName",legislationExample.getStExampleName());
                    map.put("stNeed",legislationExample.getStNeed());
                    legislationFilesList.forEach((LegislationFiles legislationFiles)->{
                        if(null!=legislationFiles.getStSampleId()&&
                                legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
                            map.put("fileId",legislationFiles.getStFileId());
                            map.put("fileName",legislationFiles.getStTitle());
                            map.put("fileUrl",legislationFiles.getStFileUrl());
                        }
                    });
                    legislationExampleFilesList.add(map);
                });
                request.setAttribute("LegislationExampleList",legislationExampleFilesList);
                method="openOnlineDemonstrationPage";
            }else{
                method="openOnlineInfoPage";
            }
            request.setAttribute("legislationFilesList",legislationFilesList);
            request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
        }else{
            List<Map> legislationExampleFilesList=legislationExampleService.queryLegislationExampleFiles(condMap,sortMap);
            request.setAttribute("legislationProcessTask",new LegislationProcessTask());
            request.setAttribute("LegislationExampleList",legislationExampleFilesList);
            method="openOnlineDemonstrationPage";
        }
        request.setAttribute("nodeId",stNodeId);
        request.setAttribute("stDocId",stDocId);
        request.setAttribute("buttonId",request.getParameter("buttonId"));
        request.setAttribute("requestUrl", request.getRequestURI());
        request.setAttribute("legislationProcessDoc", legislationProcessDoc);
        return method;
    }

	/**
	 * 办理页面--跳转网上征求意见盖章页面
	 * @return
	 */
	private String openOnlineSealPage(){
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId",stDocId);
		condMap.put("stNodeId","NOD_0000000130");
		sortMap.put("dtPubDate","ASC");
		List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
		request.setAttribute("buttonId",request.getParameter("buttonId"));
		return pageController();
	}

	/**
	 * 办理页面--跳转网上征求意见送审页面
	 * @return
	 */
	private String openOnlineCensorshipPage(){
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(!"SEND".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus in ('SEND','SEND-RETURN') order by t.stTaskdetailId");
			for(LegislationProcessTaskdetail legislationProcessTaskdetail:taskDetails){
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='"+legislationProcessTaskdetail.getStTaskdetailId()+"'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails",checkDetails);
			return "openProMeetCheckInfoPage";
		}else{
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId",stDocId);
			condMap.put("stNodeId","NOD_0000000130");
			sortMap.put("dtPubDate","ASC");
			List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
			Boolean sendDisabled=false;
			Boolean sendReturnDisabled=false;
			Integer disabledNumber=0;
			String stTaskStatus="SEND";
			List<LegislationProcessTaskdetail> sendList=legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus='"+stTaskStatus+"' ");
			if(sendList.size()>0){
				sendDisabled=true;
				disabledNumber++;
			}
			stTaskStatus="SEND-RETURN";
			List<LegislationProcessTaskdetail> sendReturnList=legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus='"+stTaskStatus+"' ");
			if(sendReturnList.size()>0){
				sendReturnDisabled=true;
				disabledNumber++;
			}
			request.setAttribute("disabledNumber",disabledNumber);
			request.setAttribute("sendDisabled",sendDisabled);
			request.setAttribute("sendReturnDisabled",sendReturnDisabled);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
			request.setAttribute("buttonId",request.getParameter("buttonId"));
			return pageController();
		}
	}

	/**
	 * 办理页面--跳转网上征求意见上网发布页面
	 * @return
	 */
	private String openOnlineReleasePage(){
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(!"PUBLISH".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus in ('SEND','SEND-RETURN','PUBLISH') order by t.stTaskdetailId");
			for(LegislationProcessTaskdetail legislationProcessTaskdetail:taskDetails){
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='"+legislationProcessTaskdetail.getStTaskdetailId()+"'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails",checkDetails);
			return "openProMeetCheckInfoPage";
		}else{
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId",stDocId);
			condMap.put("stNodeId","NOD_0000000130");
			sortMap.put("dtPubDate","ASC");
			List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
			Boolean publishListDisabled=false;
			List<LegislationProcessTaskdetail> publishList=legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus='PUBLISH' ");
			if(publishList.size()>0){
				publishListDisabled=true;
			}
			request.setAttribute("publishListDisabled",publishListDisabled);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
			request.setAttribute("buttonId",request.getParameter("buttonId"));
			return pageController();
		}
	}

	/**
	 * 办理页面--跳转网上意见汇总页面
	 * @return
	 */
	private String openOnlineSummaryPage(){
		String method;
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", stNodeId);
		sortMap.put("stExampleId", "ASC");
		List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
		condMap.clear();
		sortMap.clear();
		condMap.put("stParentId",stDocId);
		condMap.put("stNodeId",stNodeId);
		sortMap.put("dtPubDate","ASC");
		List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
		if("GATHER".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
			List<Map> legislationExampleFilesList =new ArrayList<>();
			legislationExampleList.forEach((LegislationExample legislationExample)->{
				Map map=new HashMap();
				map.put("stExampleId",legislationExample.getStExampleId());
				map.put("stExampleName",legislationExample.getStExampleName());
				map.put("stNeed",legislationExample.getStNeed());
				if(legislationFilesList.size()>0){
					legislationFilesList.forEach((LegislationFiles legislationFiles)->{
						if(null!=legislationFiles.getStSampleId()&&
								legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
							map.put("fileId",legislationFiles.getStFileId());
							map.put("fileName",legislationFiles.getStTitle());
							map.put("fileUrl",legislationFiles.getStFileUrl());
						}
					});
				}else{
					map.put("fileId",null);
					map.put("fileName",null);
					map.put("fileUrl",null);
				}
				legislationExampleFilesList.add(map);
			});
			Boolean summaryDisabled=false;
			if(null==legislationProcessTaskList.get(0).getStBakTwo()){
				summaryDisabled=true;
			}
			request.setAttribute("summaryDisabled",summaryDisabled);
			request.setAttribute("LegislationExampleList",legislationExampleFilesList);
			method="openOnlineSummaryPage";
		}else{
			request.setAttribute("legislationFilesList",legislationFilesList);
			method="openOnlineSummaryInfoPage";
		}
		request.setAttribute("nodeId",stNodeId);
		request.setAttribute("buttonId",request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
		return method;
	}

	/**
	 * 网上意见汇总保存
	 * @return
	 */
	private String saveOnlineSummary() throws IOException {
		legislationProcessTaskService.saveOnlineSummary(request);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("success",true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}
	/**
	 * 办理页面--跳转专家论证会发起页面和专家论证会结果归档页面
	 * @return
	 */
	private String openExpertDemonstrationPage(){
		String method;
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", "NOD_0000000150");
		sortMap.put("stExampleId", "ASC");
		if(legislationProcessTaskList.size()>0){
			List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
			condMap.clear();
			sortMap.clear();
			condMap.put("stParentId",stDocId);
			condMap.put("stNodeId",stNodeId);
			sortMap.put("dtPubDate","ASC");
			List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
			if("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
				List<Map> legislationExampleFilesList =new ArrayList<>();
				legislationExampleList.forEach((LegislationExample legislationExample)->{
					Map map=new HashMap();
					map.put("stExampleId",legislationExample.getStExampleId());
					map.put("stExampleName",legislationExample.getStExampleName());
					map.put("stNeed",legislationExample.getStNeed());
					legislationFilesList.forEach((LegislationFiles legislationFiles)->{
						if(null!=legislationFiles.getStSampleId()&&
								legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
							map.put("fileId",legislationFiles.getStFileId());
							map.put("fileName",legislationFiles.getStTitle());
							map.put("fileUrl",legislationFiles.getStFileUrl());
						}
					});
					legislationExampleFilesList.add(map);
				});
				request.setAttribute("LegislationExampleList",legislationExampleFilesList);
				method="openExpertDemonstrationPage";
			}else{
				method="openExpertInfoPage";
			}
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
		}else{
			List<Map> legislationExampleFilesList=legislationExampleService.queryLegislationExampleFiles(condMap,sortMap);
			request.setAttribute("legislationProcessTask",new LegislationProcessTask());
			request.setAttribute("LegislationExampleList",legislationExampleFilesList);
			method="openExpertDemonstrationPage";
		}
		request.setAttribute("nodeId",stNodeId);
		request.setAttribute("stDocId",stDocId);
		request.setAttribute("buttonId",request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 办理页面--跳转立法听证会发起页面和立法听证会结果归档页面
	 * @return
	 */
	private String openLegislationDemonstrationPage(){
		String method;
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", "NOD_0000000140");
		sortMap.put("stExampleId", "ASC");
		if(legislationProcessTaskList.size()>0){
			List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
			condMap.clear();
			sortMap.clear();
			condMap.put("stParentId",stDocId);
			condMap.put("stNodeId",stNodeId);
			sortMap.put("dtPubDate","ASC");
			List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
			if("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())||"RESULT".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
				List<Map> legislationExampleFilesList =new ArrayList<>();
				legislationExampleList.forEach((LegislationExample legislationExample)->{
					Map map=new HashMap();
					map.put("stExampleId",legislationExample.getStExampleId());
					map.put("stExampleName",legislationExample.getStExampleName());
					map.put("stNeed",legislationExample.getStNeed());
					legislationFilesList.forEach((LegislationFiles legislationFiles)->{
						if(null!=legislationFiles.getStSampleId()&&
								legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
							map.put("fileId",legislationFiles.getStFileId());
							map.put("fileName",legislationFiles.getStTitle());
							map.put("fileUrl",legislationFiles.getStFileUrl());
						}
					});
					legislationExampleFilesList.add(map);
				});
				request.setAttribute("LegislationExampleList",legislationExampleFilesList);
				method="openLegislationDemonstrationPage";
			}else{
				method="openLegislationInfoPage";
			}
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
		}else{
			List<Map> legislationExampleFilesList=legislationExampleService.queryLegislationExampleFiles(condMap,sortMap);
			request.setAttribute("legislationProcessTask",new LegislationProcessTask());
			request.setAttribute("LegislationExampleList",legislationExampleFilesList);
			method="openLegislationDemonstrationPage";
		}
		request.setAttribute("nodeId",request.getParameter("stNodeId"));
		request.setAttribute("stDocId",stDocId);
		request.setAttribute("buttonId",request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	/**
	 * 办理页面--跳转立法听证会接收页面
	 * @return
	 */
	private String openLegislationReceivePage(){
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId",stDocId);
		condMap.put("stNodeId","NOD_0000000140");
		sortMap.put("dtPubDate","ASC");
		List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
		request.setAttribute("buttonId",request.getParameter("buttonId"));
		return pageController();
	}

	/**
	 * 办理页面--跳转立法听证会送审页面
	 * @return
	 */
	private String openLegislationCensorshipPage(){
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(!"SEND".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus in ('SEND','SEND-RETURN') order by t.stTaskdetailId");
			for(LegislationProcessTaskdetail legislationProcessTaskdetail:taskDetails){
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='"+legislationProcessTaskdetail.getStTaskdetailId()+"'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails",checkDetails);
			return "openProMeetCheckInfoPage";
		}else{
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId",stDocId);
			condMap.put("stNodeId","NOD_0000000140");
			sortMap.put("dtPubDate","ASC");
			List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
			Boolean sendDisabled=false;
			Boolean sendReturnDisabled=false;
			Integer disabledNumber=0;
			String stTaskStatus="SEND";
			List<LegislationProcessTaskdetail> sendList=legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus='"+stTaskStatus+"' ");
			if(sendList.size()>0){
				sendDisabled=true;
				disabledNumber++;
			}
			stTaskStatus="SEND-RETURN";
			List<LegislationProcessTaskdetail> sendReturnList=legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus='"+stTaskStatus+"' ");
			if(sendReturnList.size()>0){
				sendReturnDisabled=true;
				disabledNumber++;
			}
			request.setAttribute("disabledNumber",disabledNumber);
			request.setAttribute("sendDisabled",sendDisabled);
			request.setAttribute("sendReturnDisabled",sendReturnDisabled);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
			request.setAttribute("buttonId",request.getParameter("buttonId"));
			return pageController();
		}
	}

	/**
	 * 办理页面--跳转立法听证会上网发布页面
	 * @return
	 */
	private String openLegislationReleasePage(){
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(!"PUBLISH".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus in ('SEND','SEND-RETURN','PUBLISH') order by t.stTaskdetailId");
			for(LegislationProcessTaskdetail legislationProcessTaskdetail:taskDetails){
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='"+legislationProcessTaskdetail.getStTaskdetailId()+"'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails",checkDetails);
			return "openProMeetCheckInfoPage";
		}else{
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId",stDocId);
			condMap.put("stNodeId","NOD_0000000140");
			sortMap.put("dtPubDate","ASC");
			List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
			Boolean publishListDisabled=false;
			List<LegislationProcessTaskdetail> publishList=legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus='PUBLISH' ");
			if(publishList.size()>0){
				publishListDisabled=true;
			}
			request.setAttribute("publishListDisabled",publishListDisabled);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
			request.setAttribute("buttonId",request.getParameter("buttonId"));
			return pageController();
		}
	}

    /**
     * 办理页面--跳转立法听证会网上报名页面
     * @return
     */
	private String openLegislationRegistrationPage(){
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		if(!"GATHER".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
			List<LegislationProcessTaskdetail> checkDetails = new ArrayList<>();
			List<LegislationProcessTaskdetail> taskDetails = legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus in ('SEND','SEND-RETURN','PUBLISH','GATHER-RETURN') order by t.stTaskdetailId");
			for(LegislationProcessTaskdetail legislationProcessTaskdetail:taskDetails){
				List<LegislationFiles> files = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId='"+legislationProcessTaskdetail.getStTaskdetailId()+"'");
				legislationProcessTaskdetail.setFilesList(files);
				checkDetails.add(legislationProcessTaskdetail);
			}
			request.setAttribute("checkDetails",checkDetails);
			return "openProMeetCheckInfoPage";
		}else{
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId",stDocId);
			condMap.put("stNodeId","NOD_0000000140");
			sortMap.put("dtPubDate","ASC");
			List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
			Boolean gatherReturnDisabled=false;
			List<LegislationProcessTaskdetail> gatherReturnList=legislationProcessTaskdetailService.findByHQL("from LegislationProcessTaskdetail t where 1=1 and t.stTaskId='"+legislationProcessTaskList.get(0).getStTaskId()+"' and t.stTaskStatus='GATHER-RETURN' ");
			if(gatherReturnList.size()>0){
				gatherReturnDisabled=true;
			}
			request.setAttribute("gatherReturnDisabled",gatherReturnDisabled);
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
			request.setAttribute("buttonId",request.getParameter("buttonId"));
			return pageController();
		}
	}
	private String openHandleDemonstrationPage(){
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stTaskStatus='DOING' and t.stEnable is null");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId",stDocId);
		condMap.put("stNodeId",stNodeId);
		sortMap.put("dtPubDate","ASC");
		List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
		request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
		request.setAttribute("legislationFilesList",legislationFilesList);
		return pageController();
	}

	private String saveHandleDemonstration() throws IOException {
		String stTaskId=request.getParameter("stTaskId");
		String stComment2=request.getParameter("stComment2");
		LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(stTaskId);
		legislationProcessTask.setStComment2(stComment2);
		legislationProcessTaskService.update(legislationProcessTask);
		Enumeration keys=request.getParameterNames();
		while(keys.hasMoreElements()){
			String key=(String)keys.nextElement();
			String value=request.getParameter(key);
			if(value.startsWith("FIL_")){
				legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='"+legislationProcessTask.getStDocId()+"' where s.stFileId='"+value+"'");
			}
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("stTaskId",stTaskId);
		jsonObject.put("success",true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}
	private String openExpertInfoPage(){
		return pageController();
	}

	private String openLegislationInfoPage(){
		return pageController();
	}

    /**
     * 跳转新增审核会议信息页面
     * @return
     */
	private String openAddAuditMeetingPage(){
		request.setAttribute("LegislationExampleList",queryLegislationExampleFiles());
		List<LegislationProcessDoc> legislationProcessDocList=legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stEnable is null");
		request.setAttribute("legislationProcessDocList",legislationProcessDocList);
		request.setAttribute("legislationProcessDoc",new LegislationProcessDoc());
		request.setAttribute("stTaskStatus","TODO");
		return pageController();
	}

    /**
     *跳转编辑审核会议信息页面
     * @return
     */
	private String openEditAuditMeetingPage(){
		String stDocId=request.getParameter("stDocId");
		String stTaskStatus=request.getParameter("stTaskStatus");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", "NOD_0000000170");
		sortMap.put("stExampleId", "ASC");
		List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		String[] stDocIdArray=legislationProcessDoc.getStDocSource().split("#");
		List<Map> legislationProcessDocList=new ArrayList<>();
		for (int i = 0; i <stDocIdArray.length ; i++) {
			LegislationProcessDoc checkedDoc=legislationProcessDocService.findById(stDocIdArray[i]);
			Map map=new HashMap();
			map.put("stDocId",checkedDoc.getStDocId());
            map.put("stDocName",checkedDoc.getStDocName());
            List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocIdArray[i]+"' and stNodeId='NOD_0000000105' and t.stEnable is null");
            map.put("stActive",legislationProcessTaskList.get(0).getStActive()==null?"true":legislationProcessTaskList.get(0).getStActive());
			legislationProcessDocList.add(map);
		}
		condMap.clear();
		sortMap.clear();
		condMap.put("stNodeId","NOD_0000000170");
		sortMap.put("dtPubDate","ASC");
		if("INPUT".equals(stTaskStatus)){
			LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='NOD_0000000170' and t.stTaskStatus='INPUT' and t.stEnable is null").get(0);
			Map map=new HashMap();
			map.put("stDocId",legislationProcessTask.getStTaskId());
			if(legislationProcessTask.getStComment1()!=null&&"after".equals(legislationProcessTask.getStComment1())){
				map.put("stActive",legislationProcessTask.getStActive());
				map.put("stDocName",legislationProcessTask.getStBakOne());
				map.put("stDocNo",legislationProcessTask.getStBakTwo());
				map.put("stNodeName",legislationProcessTask.getStNodeName());
				map.put("stComent",legislationProcessTask.getStComment2());
				map.put("dtCreateDate",legislationProcessTask.getDtBakDate());
				condMap.put("stParentId",legislationProcessTask.getStTaskId());
			}else{
				map.put("stActive","true");
				map.put("stDocName",legislationProcessDoc.getStDocName());
				map.put("stDocNo",legislationProcessDoc.getStDocNo());
				map.put("stNodeName",legislationProcessDoc.getStNodeName());
				map.put("stComent",legislationProcessDoc.getStComent());
				map.put("dtCreateDate",legislationProcessDoc.getDtCreateDate());
				condMap.put("stParentId","-1");
			}
			request.setAttribute("legislationProcessDoc",map);
		}else{
			request.setAttribute("legislationProcessDoc",legislationProcessDoc);
			condMap.put("stParentId",legislationProcessDoc.getStDocId());
		}
		List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
		List<Map> legislationExampleFilesList=new ArrayList<>();
		legislationExampleList.forEach((LegislationExample legislationExample)->{
			Map map=new HashMap();
			map.put("stExampleId",legislationExample.getStExampleId());
			map.put("stExampleName",legislationExample.getStExampleName());
			map.put("stNeed",legislationExample.getStNeed());
			legislationFilesList.forEach((LegislationFiles legislationFiles)->{
				if(null!=legislationFiles.getStSampleId()&&
						legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
					map.put("fileId",legislationFiles.getStFileId());
					map.put("fileName",legislationFiles.getStTitle());
					map.put("fileUrl",legislationFiles.getStFileUrl());
				}
			});
			legislationExampleFilesList.add(map);
		});
		request.setAttribute("LegislationExampleList",legislationExampleFilesList);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationProcessDocList",legislationProcessDocList);
		request.setAttribute("stTaskStatus",stTaskStatus);
		return pageController();
	}

    /**
     * 保存审核会议信息
     * @return
     * @throws Exception
     */
	private String saveAuditMeeting() throws Exception{
		String stDocId = request.getParameter("stDocId");
		String stDocName = request.getParameter("stDocName");
		String stDocNo = request.getParameter("stDocNo");
		String stDocSource = request.getParameter("stDocSource");
		String stNodeName = request.getParameter("stNodeName");
		String dtCreateDate = request.getParameter("dtCreateDate");
		String stComent = request.getParameter("stComent");
		String stTaskStatus=request.getParameter("stTaskStatus");
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		String unitId = currentPerson.getTeamInfos().get(0).getId();
		String unitName = currentPerson.getTeamInfos().get(0).getUnitName();

		String[] stDocIdArray=stDocSource.split("#");
		for (int i = 0; i < stDocIdArray.length; i++) {
			String newDocName=request.getParameter(stDocIdArray[i]);
			if(StringUtil.isNotEmpty(newDocName)){
				LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocIdArray[i]);
				legislationProcessDoc.setStDocName(newDocName);
				legislationProcessDocService.update(legislationProcessDoc);
				List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocIdArray[i]+"'");
				legislationProcessTaskList.forEach((LegislationProcessTask newLegislationProcessTask)->{
					newLegislationProcessTask.setStFlowId(newDocName);
					legislationProcessTaskService.update(newLegislationProcessTask);
				});
				List<LegislationProcessDeal> legislationProcessDealList=legislationProcessDealService.findByHQL("from LegislationProcessDeal t where 1=1 and t.stDocId='"+stDocIdArray[i]+"'");
				legislationProcessDealList.forEach((LegislationProcessDeal newLegislationProcessDeal)->{
					newLegislationProcessDeal.setStBakOne(newDocName);
					legislationProcessDealService.update(newLegislationProcessDeal);
				});
			}
			if("INPUT".equals(stTaskStatus)){
                String stActive=request.getParameter("stActive"+stDocIdArray[i]);
                List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId='"+stDocIdArray[i]+"' and stNodeId='NOD_0000000105' and t.stEnable is null");
                legislationProcessTaskList.forEach((LegislationProcessTask legislationProcessTask)->{
                     legislationProcessTask.setStActive(stActive);
                     legislationProcessTaskService.update(legislationProcessTask);
                });
			}
		}
		if(StringUtil.isEmpty(stDocId)){
			LegislationProcessDoc auditMeeting=new LegislationProcessDoc();
			auditMeeting.setStDocName(stDocName);
			auditMeeting.setStDocNo(stDocNo);
			auditMeeting.setStNodeId("NOD_0000000170");
			auditMeeting.setStNodeName(stNodeName);
			auditMeeting.setStComent(stComent);
			auditMeeting.setDtCreateDate(DateUtils.parseDate(dtCreateDate,"yyyy-MM-dd"));
			auditMeeting.setStDocSource(stDocSource);
			auditMeeting.setStUserId(userId);
			auditMeeting.setStUserName(userName);
			auditMeeting.setStUnitId(unitId);
			auditMeeting.setStUnitName(unitName);
			stDocId=legislationProcessDocService.addObj(auditMeeting);

			LegislationProcessTask legislationProcessTask=new LegislationProcessTask();
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
				LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocIdArray[i]);
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

		}else{
			if("TODO".equals(stTaskStatus)){
				LegislationProcessDoc auditMeeting=legislationProcessDocService.findById(stDocId);
				auditMeeting.setStDocName(stDocName);
				auditMeeting.setStDocNo(stDocNo);
				auditMeeting.setStNodeName(stNodeName);
				auditMeeting.setStComent(stComent);
				auditMeeting.setDtCreateDate(DateUtils.parseDate(dtCreateDate,"yyyy-MM-dd"));
				legislationProcessDocService.update(auditMeeting);
			}else{
				LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(stDocId);
				legislationProcessTask.setStBakOne(stDocName);
				legislationProcessTask.setStBakTwo(stDocNo);
				legislationProcessTask.setStNodeName(stNodeName);
				legislationProcessTask.setStComment2(stComent);
				legislationProcessTask.setStComment1("after");
				legislationProcessTask.setDtBakDate(DateUtils.parseDate(dtCreateDate,"yyyy-MM-dd"));
				legislationProcessTaskService.update(legislationProcessTask);
			}
		}
		Enumeration keys=request.getParameterNames();
		while(keys.hasMoreElements()){
			String key=(String)keys.nextElement();
			String value=request.getParameter(key);
			if(value.startsWith("FIL_")){
				legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='"+stDocId+"' where s.stFileId='"+value+"'");
			}
		}
		return null;
	}

    /**
     * 跳转审核会议详情页
     * @return
     */
	private String openAuditMeetingInfoPage(){
		String stDocId=request.getParameter("stDocId");
        String stTaskStatus=request.getParameter("stTaskStatus");
        LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
        String[] stDocIdArray=legislationProcessDoc.getStDocSource().split("#");
        List<LegislationProcessDoc> legislationProcessDocList=new ArrayList<>();
        for (int i = 0; i <stDocIdArray.length ; i++) {
            LegislationProcessDoc checkedDoc=legislationProcessDocService.findById(stDocIdArray[i]);
            legislationProcessDocList.add(checkedDoc);
        }
        Map<String, Object> condMap = new HashMap<>();
        Map<String, String> sortMap = new HashMap<>();
        condMap.put("stNodeId","NOD_0000000170");
        sortMap.put("dtPubDate","ASC");
        if("before".equals(stTaskStatus)){
            request.setAttribute("legislationProcessDoc",legislationProcessDoc);
            condMap.put("stParentId",legislationProcessDoc.getStDocId());
        }else{
            LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='NOD_0000000170' and t.stEnable is null").get(0);
            Map map=new HashMap();
            map.put("stDocId",legislationProcessTask.getStTaskId());
            map.put("stActive",legislationProcessTask.getStActive());
            map.put("stDocName",legislationProcessTask.getStBakOne());
            map.put("stDocNo",legislationProcessTask.getStBakTwo());
            map.put("stNodeName",legislationProcessTask.getStNodeName());
            map.put("stComent",legislationProcessTask.getStComment2());
            map.put("dtCreateDate",legislationProcessTask.getDtBakDate());
            request.setAttribute("legislationProcessDoc",map);
            condMap.put("stParentId",legislationProcessTask.getStTaskId());
        }
        List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
        request.setAttribute("legislationFilesList",legislationFilesList);
        request.setAttribute("legislationProcessDocList",legislationProcessDocList);
        request.setAttribute("stTaskStatus",stTaskStatus);
		return pageController();
	}

	private String openInfoTabPage(){
		return openInfoPage();
	}

	private String openSeparateTabPage(){
		String stDocId=request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='NOD_0000000102' and t.stEnable is null");
		if(list!=null && list.size()>0){
			request.setAttribute("legislationProcessTask", list.get(0));
		}
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return pageController();
	}

	private String openUnitTabPage(){
		return pageController();
	}

	private String openAuditMeetingTabPage(){
		return pageController();
	}

	private String openOnlineTabPage(){
		return pageController();
	}

	private String openHeartMeetingAddPage(){
		return addDemonstrationPage();
	}

	private String openHeartMeetingEditPage(){
		return editDemonstrationPage();
	}

	private String openExpertAddPage(){
		return addDemonstrationPage();
	}

	private String openExpertEditPage(){
		return editDemonstrationPage();
	}

	/**
	 * 办理页面--跳转部门征求意见编辑页面
	 * @return
	 */
	private String openUnitDemonstrationPage(){
		String method;
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stEnable is null");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", stNodeId);
		sortMap.put("stExampleId", "ASC");
		if(legislationProcessTaskList.size()>0){
			List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
			condMap.clear();
			sortMap.clear();
			condMap.put("stParentId",stDocId);
			condMap.put("stNodeId",stNodeId);
			sortMap.put("dtPubDate","ASC");
			List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
			if("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())){
				List<Map> legislationExampleFilesList =new ArrayList<>();
				legislationExampleList.forEach((LegislationExample legislationExample)->{
					Map map=new HashMap();
					map.put("stExampleId",legislationExample.getStExampleId());
					map.put("stExampleName",legislationExample.getStExampleName());
					map.put("stNeed",legislationExample.getStNeed());
					legislationFilesList.forEach((LegislationFiles legislationFiles)->{
						if(null!=legislationFiles.getStSampleId()&&
								legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
							map.put("fileId",legislationFiles.getStFileId());
							map.put("fileName",legislationFiles.getStTitle());
							map.put("fileUrl",legislationFiles.getStFileUrl());
						}
					});
					legislationExampleFilesList.add(map);
				});
				request.setAttribute("LegislationExampleList",legislationExampleFilesList);
				method="openUnitDemonstrationPage";
			}else{
				method="openUnitInfoPage";
			}
			request.setAttribute("legislationFilesList",legislationFilesList);
			request.setAttribute("legislationProcessTask",legislationProcessTaskList.get(0));
		}else{
			List<Map> legislationExampleFilesList=legislationExampleService.queryLegislationExampleFiles(condMap,sortMap);
			request.setAttribute("legislationProcessTask",new LegislationProcessTask());
			request.setAttribute("LegislationExampleList",legislationExampleFilesList);
			method="openUnitDemonstrationPage";
		}
		request.setAttribute("nodeId",stNodeId);
		request.setAttribute("stDocId",stDocId);
		request.setAttribute("buttonId",request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		return method;
	}

	private String openUnitAddPage(){
		return addDemonstrationPage();
	}

	private String openUnitEditPage(){
		return editDemonstrationPage();
	}

	/**
	 * 跳转发送部门页面
	 * @return
	 */
	private String openUnitSeekPage(){
		String method=request.getParameter("method");
		String stDocId=request.getParameter("stDocId");
		String stNodeId=request.getParameter("stNodeId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='"+stNodeId+"' and t.stTaskStatus='TODO' and t.stEnable is null");
		List<TeamInfo> teamInfoList=teamInfoService.findTeamInfoInModuleByType("MODULE_LEGISLATE","委办局");
		request.setAttribute("teamInfoList",teamInfoList);
		return demonstrationPageController(method,legislationProcessTaskList.get(0).getStTaskId());
	}

	/**
	 * 发送部门
	 * @return
	 */
	private String sendUnit(){
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userRoleId = session.getAttribute("userRoleId").toString();
        String userRole = session.getAttribute("userRole").toString();
        legislationProcessTaskService.sendUnit(request,currentPerson,userRoleId,userRole);
		return null;
	}

	/**
	 * 填写意见
	 * @return
	 */
	public String addOpinion(){
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        legislationProcessTaskService.addOpinion(request,currentPerson);
		return null;
	}

	/**
	 * 办理页面--跳转部门征求意见接收反馈
	 * @return
	 */
	private String openUnitReceivePage(){
		String stDocId=request.getParameter("stDocId");
		List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + stDocId + "' and t.stNodeId='NOD_0000000122' and t.stEnable is null");
		List<Map> opinionList=new ArrayList<>();
		legislationProcessTaskList.forEach((LegislationProcessTask legislationProcessTask)->{
			Map map=new HashMap();
			map.put("stTaskId",legislationProcessTask.getStTaskId());
			map.put("stTeamName",legislationProcessTask.getStComment1());
			map.put("stTaskStatus",legislationProcessTask.getStTaskStatus());
			map.put("dtBakDate",legislationProcessTask.getDtBakDate());
			if(StringUtil.isNotEmpty(legislationProcessTask.getStComment2())){
				LegislationFiles legislationFiles=legislationFilesService.findById(legislationProcessTask.getStComment2());
				map.put("fileName",legislationFiles.getStTitle());
				map.put("fileUrl",legislationFiles.getStFileUrl());
			}else{
				map.put("fileName",null);
				map.put("fileUrl",null);
			}
			opinionList.add(map);
		});
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String teamId=currentPerson.getTeamInfos().get(0).getId();
		request.setAttribute("teamId",teamId);
		request.setAttribute("buttonId",request.getParameter("buttonId"));
		request.setAttribute("opinionList",opinionList);
		return pageController();
	}

	private String openUnitInfoPage(){
		String stTaskId=request.getParameter("stTaskId");
		LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(stTaskId);
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stParentId",legislationProcessTask.getStDocId());
		condMap.put("stNodeId","NOD_0000000120");
		sortMap.put("dtPubDate","ASC");
		List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
		if(StringUtil.isNotEmpty(legislationProcessTask.getStComment2())){
			LegislationFiles legislationFiles=legislationFilesService.findById(legislationProcessTask.getStComment2());
			request.setAttribute("legislationFiles",legislationFiles);
		}
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationProcessTask",legislationProcessTask);
		return pageController();
	}

	private String addDemonstrationPage(){
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList",queryLegislationExampleFiles());
		List<LegislationProcessDoc> legislationProcessDocList=legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000103' and t.stTaskStatus='DOING' and t.stEnable is null and d.stDocId not in (select a.stDocId from LegislationProcessDeal a where a.stActionId='"+stNodeId+"')");
		request.setAttribute("legislationProcessDocList",legislationProcessDocList);
		request.setAttribute("legislationProcessTask",new LegislationProcessTask());
		return pageController();
	}
	private String openMeetingBeforeInfoPage(){
		LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(request.getParameter("stTaskId"));
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		String stDocId = legislationProcessTask.getStDocId();
		String stNodeId = legislationProcessTask.getStNodeId();
		if(stNodeId.equals("NOD_0000000141")){
			stNodeId = "NOD_0000000140";
		}else if(stNodeId.equals("NOD_0000000151")){
			stNodeId = "NOD_0000000150";
		}
		condMap.put("stDocId",stDocId);
		condMap.put("stNodeId",stNodeId);

		legislationProcessTask= legislationProcessTaskService.findByList(condMap,sortMap).get(0);
		List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+legislationProcessTask.getStDocId()+"' and t.stNodeId ='"+stNodeId+"' and t.stSampleId !='null' order by t.stSampleId ");
		List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+legislationProcessTask.getStDocId()+"' and t.stNodeId ='"+stNodeId+"' and t.stSampleId='null' order by t.stFileId ");

		request.setAttribute("docList",docList);
		request.setAttribute("otherDocList",otherDocList);
		request.setAttribute("legislationProcessTask",legislationProcessTask);
		return pageController();
	}
	private String openMeetingAfterInfoPage(){
		LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(request.getParameter("stTaskId"));
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stDocId",legislationProcessTask.getStDocId());
		condMap.put("stTaskStatus",legislationProcessTask.getStTaskStatus());

		String stNodeId = legislationProcessTask.getStNodeId();

		if(stNodeId.equals("NOD_0000000140")){
			stNodeId = "NOD_0000000141";
		}else if(stNodeId.equals("NOD_0000000150")){
			stNodeId = "NOD_0000000151";
		}

		condMap.put("stNodeId",stNodeId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findByList(condMap,sortMap);
		if(list!=null && list.size()>0){
			legislationProcessTask= legislationProcessTaskService.findByList(condMap,sortMap).get(0);
			List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+legislationProcessTask.getStDocId()+"' and t.stNodeId ='"+stNodeId+"' and t.stSampleId !='null' order by t.stSampleId ");
			List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+legislationProcessTask.getStDocId()+"' and t.stNodeId ='"+stNodeId+"' and t.stSampleId='null' order by t.stFileId ");

			request.setAttribute("docList",docList);
			request.setAttribute("otherDocList",otherDocList);
			request.setAttribute("legislationProcessTask",legislationProcessTask);
		}

		return pageController();
	}

	private String editDemonstrationPage(){
		String stTaskId=request.getParameter("stTaskId");
		String stNodeId = request.getParameter("stNodeId");
        LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(stTaskId);
        LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(legislationProcessTask.getStDocId());
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		if("NOD_0000000141".equals(stNodeId)){
			condMap.put("stNode", "NOD_0000000140");
		}else if("NOD_0000000151".equals(stNodeId)){
			condMap.put("stNode", "NOD_0000000150");
		}
		else{
			condMap.put("stNode", stNodeId);
		}
		sortMap.put("stExampleId", "ASC");
		List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
		condMap.clear();
		condMap.put("stParentId",legislationProcessTask.getStDocId());
        condMap.put("stNodeId",stNodeId);
		sortMap.clear();
		sortMap.put("dtPubDate","ASC");
		List<LegislationFiles> legislationFilesList=legislationFilesService.findByList(condMap,sortMap);
		List<Map> legislationExampleFilesList=new ArrayList<>();
		legislationExampleList.forEach((LegislationExample legislationExample)->{
			Map map=new HashMap();
			map.put("stExampleId",legislationExample.getStExampleId());
			map.put("stExampleName",legislationExample.getStExampleName());
			map.put("stNeed",legislationExample.getStNeed());
			legislationFilesList.forEach((LegislationFiles legislationFiles)->{
				if(null!=legislationFiles.getStSampleId()&&
						legislationExample.getStExampleId().equals(legislationFiles.getStSampleId())){
					map.put("fileId",legislationFiles.getStFileId());
					map.put("fileName",legislationFiles.getStTitle());
					map.put("fileUrl",legislationFiles.getStFileUrl());
				}
			});
			legislationExampleFilesList.add(map);
		});
		request.setAttribute("legislationProcessDoc",legislationProcessDoc);
		request.setAttribute("LegislationExampleList",legislationExampleFilesList);
		request.setAttribute("legislationFilesList",legislationFilesList);
		request.setAttribute("legislationProcessTask",legislationProcessTask);
		return pageController();
	}
	/**
	 * 页面控制
	 * @return
	 */
	private String pageController(){
		String stDocId=request.getParameter("stDocId");

		String methodStr = request.getParameter("method");

		String stNodeId = request.getParameter("stNodeId");

		String stTaskId=request.getParameter("stTaskId");

		request.setAttribute("nodeId",stNodeId);
		request.setAttribute("stDocId",stDocId);
		request.setAttribute("stTaskId",stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return methodStr;
	}

	/**
	 * 办理页面控制
	 * @return
	 */
	private String demonstrationPageController(String method,String stTaskId){
		String stDocId=request.getParameter("stDocId");
		String buttonId=request.getParameter("buttonId");
		String stNodeId=request.getParameter("stNodeId");

		request.setAttribute("nodeId",stNodeId);
		request.setAttribute("buttonId",buttonId);
		request.setAttribute("stDocId",stDocId);
		request.setAttribute("stTaskId",stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return method;
	}
	/**
	 * 新增修改草案
	 * @return
	 * @throws FzbDaoException
	 */
	private String editLegislationProcessDoc() throws FzbDaoException {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		legislationProcessDocService.editLegislationProcessDoc(request,currentPerson,session.getAttribute("userRoleId").toString(),session.getAttribute("userRole").toString());
		return null;
	}

	private String saveLegislationDemonstration() throws Exception {
		String stNodeId=request.getParameter("stNodeId");
		String stTaskId;
		if("NOD_0000000140".equals(stNodeId)||"NOD_0000000141".equals(stNodeId)){
			stTaskId=saveLegislation(stNodeId,"立法听证会发起");
		}else if("NOD_0000000150".equals(stNodeId)||"NOD_0000000151".equals(stNodeId)){
			stTaskId=saveLegislation(stNodeId,"专家论证会发起");
		}else if("NOD_0000000130".equals(stNodeId)||"NOD_0000000131".equals(stNodeId)){
			stTaskId=saveLegislation(stNodeId,"网上征求意见发起");
		}else{
			stTaskId=saveLegislation(stNodeId,"部门征求意见发起");
		}
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("stTaskId",stTaskId);
		jsonObject.put("success",true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}

	private String saveLegislation(String stNodeId,String stNodeName) throws Exception{
        UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userRoleId= session.getAttribute("userRoleId").toString();
        String userRole= session.getAttribute("userRole").toString();
       return legislationProcessDocService.saveLegislation(request,currentPerson,userRoleId,userRole,stNodeId,stNodeName);
	}


	/**
	 * 提交分办
	 * @return
	 * @throws Exception
	 */
	@Action(value = "draft_dist_info", results = {@Result(name = SUCCESS, location = "/legislation/legislationProcessManager_list.jsp"), @Result(name = "QueryTable", location = "/legislation/legislationProcessManager_table.jsp")})
	public String draft_dist_info() throws Exception {

		legislationProcessDocService.draft_dist_info(request);
		return SUCCESS;
	}

	/**
	 * 审核操作
	 * @return
	 */
	private String saveTaskCheck() throws ParseException {
        UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userRoleId= session.getAttribute("userRoleId").toString();
        String userRole= session.getAttribute("userRole").toString();
        legislationProcessTaskService.saveTaskCheck(request,currentPerson,userRoleId,userRole);

		return null;
	}

	private String dealFinish(){
		legislationProcessTaskService.dealFinish(request,session);

		return null;
	}
}
