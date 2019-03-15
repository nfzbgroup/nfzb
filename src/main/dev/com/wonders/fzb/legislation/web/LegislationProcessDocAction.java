package com.wonders.fzb.legislation.web;

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
			@Result(name = "openLegislationInfoPage",location = "/legislation/legislationProcessManager_legislationBeforeInfo.jsp"),
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
			@Result(name = "openExpertEditPage",location = "/legislation/legislationProcessManager_expertForm.jsp"),
			@Result(name = "openUnitAddPage",location = "/legislation/legislationProcessManager_unitForm.jsp"),
			@Result(name = "openUnitEditPage",location = "/legislation/legislationProcessManager_unitForm.jsp"),
			@Result(name = "openUnitSeekPage",location = "/legislation/legislationProcessManager_unitSeek.jsp"),
			@Result(name = "openUnitReceivePage",location = "/legislation/legislationProcessManager_unitReceive.jsp"),
			@Result(name = "openEditMeetingPage",location = "/legislation/legislationProcessManager_auditMeeting.jsp"),
			@Result(name = "openUnitInfoPage",location = "/legislation/legislationProcessManager_unitInfo.jsp"),
			@Result(name = "openUnitInfoPage",location = "/legislation/legislationProcessManager_unitInfo.jsp"),
			@Result(name = "openUnitAddOpinionPage",location = "/legislation/legislationProcessManager_unitAddOpinion.jsp")})
	public String legislationProcessDoc_form() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
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

	private String openEditMeetingPage(){
		return pageController();
	}

	private String openDemonstrationPage(){
		return pageController();
	}

	private String openExpertDemonstrationPage(){
		request.setAttribute("LegislationExampleList",queryLegislationExampleFiles());
		return pageController();
	}

	private String openLegislationDemonstrationPage(){
		request.setAttribute("LegislationExampleList",queryLegislationExampleFiles());
		return pageController();
	}

	private String openUnitDemonstrationPage(){
		request.setAttribute("LegislationExampleList",queryLegislationExampleFiles());
		return pageController();
	}

	private String openExpertInfoPage(){
		return pageController();
	}

	private String openLegislationInfoPage(){
		return pageController();
	}

	private String openAddAuditMeetingPage(){
		request.setAttribute("LegislationExampleList",queryLegislationExampleFiles());
		List<LegislationProcessDoc> legislationProcessDocList=legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stEnable is null");
		request.setAttribute("legislationProcessDocList",legislationProcessDocList);
		request.setAttribute("legislationProcessDoc",new LegislationProcessDoc());
		request.setAttribute("stTaskStatus","TODO");
		return pageController();
	}

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
		List<LegislationProcessDoc> legislationProcessDocList=new ArrayList<>();
		for (int i = 0; i <stDocIdArray.length ; i++) {
			LegislationProcessDoc checkedDoc=legislationProcessDocService.findById(stDocIdArray[i]);
			legislationProcessDocList.add(checkedDoc);
		}
		condMap.clear();
		sortMap.clear();
		condMap.put("stNodeId","NOD_0000000170");
		sortMap.put("dtPubDate","ASC");
		if("INPUT".equals(stTaskStatus)){
			List<LegislationProcessTask> legislationProcessTaskList=legislationProcessTaskService.findByHQL("from LegislationProcessTask t where t.stDocId='"+stDocId+"' and t.stNodeId='NOD_0000000170' and t.stTaskStatus='INPUT' and t.stEnable is null");
			if(legislationProcessTaskList.size()>0){
				LegislationProcessTask legislationProcessTask=legislationProcessTaskList.get(0);
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
			}else{
				request.setAttribute("legislationProcessDoc",legislationProcessDoc);
				condMap.put("stParentId","-1");
			}
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

	private String saveAuditMeeting() throws Exception{
		String stDocId = request.getParameter("stDocId");
		String stDocName = request.getParameter("stDocName");
		String stDocNo = request.getParameter("stDocNo");
		String stDocSource = request.getParameter("stDocSource");
		String stNodeName = request.getParameter("stNodeName");
		String dtCreateDate = request.getParameter("dtCreateDate");
		String stComent = request.getParameter("stComent");
		String stActive = request.getParameter("stActive");
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
			if(StringUtil.isEmpty(stActive)){
				LegislationProcessDoc auditMeeting=legislationProcessDocService.findById(stDocId);
				auditMeeting.setStDocName(stDocName);
				auditMeeting.setStDocNo(stDocNo);
				auditMeeting.setStNodeName(stNodeName);
				auditMeeting.setStComent(stComent);
				auditMeeting.setDtCreateDate(DateUtils.parseDate(dtCreateDate,"yyyy-MM-dd"));
				legislationProcessDocService.update(auditMeeting);
			}else{
				LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(stDocId);
				legislationProcessTask.setStActive(stActive);
				legislationProcessTask.setStBakOne(stDocName);
				legislationProcessTask.setStBakTwo(stDocNo);
				legislationProcessTask.setStNodeName(stNodeName);
				legislationProcessTask.setStComment2(stComent);
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

	private String openAuditMeetingInfoPage(){
		return pageController();
	}

	private String openInfoTabPage(){
		return openInfoPage();
	}

	private String openSeparateTabPage(){
		String stDocId=request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> list = legislationProcessTaskService.findByHQL("from LegislationProcessTask t where 1=1 and t.stDocId ='" + legislationProcessDoc.getStDocId() + "' and t.stNodeId='NOD_0000000102' and t.stEnable is null");
		for (LegislationProcessTask legislationProcessTask : list) {
			request.setAttribute("legislationProcessTask", legislationProcessTask);
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

	private String openUnitAddPage(){
		return addDemonstrationPage();
	}

	private String openUnitEditPage(){
		return editDemonstrationPage();
	}

	private String openUnitSeekPage(){
		List<TeamInfo> teamInfoList=teamInfoService.findTeamInfoInModuleByType("MODULE_LEGISLATE","委办局");
		request.setAttribute("teamInfoList",teamInfoList);
		return pageController();
	}

	private String sendUnit(){
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userRoleId = session.getAttribute("userRoleId").toString();
        String userRole = session.getAttribute("userRole").toString();
        legislationProcessTaskService.sendUnit(request,currentPerson,userRoleId,userRole);
		return null;
	}

	public String addOpinion(){
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        legislationProcessTaskService.addOpinion(request,currentPerson);
		return null;
	}

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
		if("NOD_0000000140".equals(stNodeId)||"NOD_0000000141".equals(stNodeId)){
			saveLegislation(stNodeId,"立法听证会发起");
		}else if("NOD_0000000150".equals(stNodeId)||"NOD_0000000151".equals(stNodeId)){
			saveLegislation(stNodeId,"专家论证会发起");
		}else if("NOD_0000000120".equals(stNodeId)){
			saveLegislation(stNodeId,"部门征求意见发起");
		}
		return null;
	}

	private void saveLegislation(String stNodeId,String stNodeName) throws Exception{
        UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userRoleId= session.getAttribute("userRoleId").toString();
        String userRole= session.getAttribute("userRole").toString();
        legislationProcessDocService.saveLegislation(request,currentPerson,userRoleId,userRole,stNodeId,stNodeName);
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
	private String saveTaskCheck(){
        UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
        String userRoleId= session.getAttribute("userRoleId").toString();
        String userRole= session.getAttribute("userRole").toString();
        legislationProcessTaskService.saveTaskCheck(request,currentPerson,userRoleId,userRole);


		return null;
	}
}
