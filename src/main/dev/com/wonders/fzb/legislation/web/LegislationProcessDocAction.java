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
			@Result(name = "openHeartMeetingBeforeInfoPage",location = "/legislation/legislationProcessManager_legislationBeforeInfo.jsp"),
			@Result(name = "openHeartMeetingAfterInfoPage",location = "/legislation/legislationProcessManager_legislationAfterInfo.jsp"),
			@Result(name = "openExpertAddPage",location = "/legislation/legislationProcessManager_expertForm.jsp"),
			@Result(name = "openExpertEditPage",location = "/legislation/legislationProcessManager_expertForm.jsp")})
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

		List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+stDocId+"' and t.stSampleId !='null' order by t.stSampleId ");
		List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+stDocId+"' and t.stSampleId='null' order by t.stFileId ");
		for(LegislationFiles legislationFiles:otherDocList){
			docList.add(legislationFiles);
		}
		request.setAttribute("legislationProcessDoc",legislationProcessDoc);
		request.setAttribute("docList",docList);
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
		return pageController();
	}

	private String openEditAuditMeetingPage(){
		return pageController();
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
		request.setAttribute("LegislationExampleList",queryLegislationExampleFiles());
		List<LegislationProcessDoc> legislationProcessDocList=legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000103' and t.stTaskStatus='DOING' and t.stEnable is null and d.stDocId not in (select a.stDocId from LegislationProcessDeal a where a.stActionId='NOD_0000000140')");
		request.setAttribute("legislationProcessDocList",legislationProcessDocList);
		request.setAttribute("legislationProcessTask",new LegislationProcessTask());
		return pageController();
	}
	private String openHeartMeetingBeforeInfoPage(){
		LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(request.getParameter("stTaskId"));
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stDocId",legislationProcessTask.getStDocId());
		condMap.put("stNodeId","NOD_0000000140");

		legislationProcessTask= legislationProcessTaskService.findByList(condMap,sortMap).get(0);
		List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+legislationProcessTask.getStTaskId()+"' and t.stSampleId !='null' order by t.stSampleId ");
		List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+legislationProcessTask.getStTaskId()+"' and t.stSampleId='null' order by t.stFileId ");

		request.setAttribute("docList",docList);
		request.setAttribute("otherDocList",otherDocList);
		request.setAttribute("legislationProcessTask",legislationProcessTask);
		return pageController();
	}
	private String openHeartMeetingAfterInfoPage(){
		LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(request.getParameter("stTaskId"));
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stDocId",legislationProcessTask.getStDocId());
		condMap.put("stTaskStatus",legislationProcessTask.getStTaskStatus());
		condMap.put("stNodeId","NOD_0000000141");
		List<LegislationProcessTask> list = legislationProcessTaskService.findByList(condMap,sortMap);
		if(list!=null && list.size()>0){
			legislationProcessTask= legislationProcessTaskService.findByList(condMap,sortMap).get(0);
			List<LegislationFiles> docList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+legislationProcessTask.getStTaskId()+"' and t.stSampleId !='null' order by t.stSampleId ");
			List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='"+legislationProcessTask.getStTaskId()+"' and t.stSampleId='null' order by t.stFileId ");

			request.setAttribute("docList",docList);
			request.setAttribute("otherDocList",otherDocList);
			request.setAttribute("legislationProcessTask",legislationProcessTask);
		}

		return pageController();
	}
	private String openHeartMeetingEditPage(){
		LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(request.getParameter("stTaskId"));
		String stNodeId = request.getParameter("stNodeId");
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stNode", "NOD_0000000140");
		sortMap.put("stExampleId", "ASC");
		List<LegislationExample> legislationExampleList = legislationExampleService.findByList(condMap, sortMap);
		String stTaskId=request.getParameter("stTaskId");
		condMap.clear();
		condMap.put("stParentId",stTaskId);
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
		List<LegislationProcessDoc> legislationProcessDocList=legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000103' and t.stTaskStatus='DOING' and t.stEnable is null ");
		request.setAttribute("legislationProcessDocList",legislationProcessDocList);
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
		String stTaskId = request.getParameter("stTaskId");
		String stBakOne = request.getParameter("stBakOne");
		String stDocId = request.getParameter("stDocId");
		String stBakTwo = request.getParameter("stBakTwo");
		String dtBakDate = request.getParameter("dtBakDate");
		String stComment2 = request.getParameter("stComment2");
		String stNodeId=request.getParameter("stNodeId");

		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userId = currentPerson.getUserId();
		String userName = currentPerson.getName();
		LegislationProcessDoc legislationProcessDoc=legislationProcessDocService.findById(stDocId);
		if(StringUtil.isEmpty(stTaskId) || "null".equals(stTaskId)){
			LegislationProcessTask newTask= new LegislationProcessTask();
			newTask.setStDocId(stDocId);
			newTask.setStFlowId(legislationProcessDoc.getStDocName());
			newTask.setStBakOne(stBakOne);
			newTask.setStBakTwo(stBakTwo);
			newTask.setDtBakDate(DateUtils.parseDate(dtBakDate,"yyyy-MM-dd"));
			newTask.setStComment2(stComment2);
			newTask.setStNodeId(stNodeId);
			newTask.setStNodeName("立法听证会发起");
			newTask.setStTaskStatus("TODO");
			newTask.setDtOpenDate(new Date());
			newTask.setStUserId(userId);
			newTask.setStUserName(userName);
			newTask.setStRoleId(session.getAttribute("userRoleId").toString());
			newTask.setStRoleName(session.getAttribute("userRole").toString());
			newTask.setStTeamId((currentPerson.getTeamInfos().get(0)).getId());
			newTask.setStTeamName((currentPerson.getTeamInfos().get(0)).getTeamName());
			stTaskId=legislationProcessTaskService.addObj(newTask);

			LegislationProcessDeal legislationProcessDeal = new LegislationProcessDeal();
			legislationProcessDeal.setStDocId(stDocId);
			legislationProcessDeal.setStActionId(stNodeId);
			legislationProcessDeal.setStActionName("立法听证会会前");
			legislationProcessDeal.setStUserId(userId);
			legislationProcessDeal.setStUserName(userName);
			legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
			legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
			legislationProcessDeal.setDtDealDate(new Date());
			legislationProcessDealService.add(legislationProcessDeal);

		}else {
			LegislationProcessTask legislationProcessTask=legislationProcessTaskService.findById(stTaskId);
			legislationProcessTask.setStBakOne(stBakOne);
			legislationProcessTask.setStBakTwo(stBakTwo);
			legislationProcessTask.setDtBakDate(DateUtils.parseDate(dtBakDate,"yyyy-MM-dd"));
			legislationProcessTask.setStComment2(stComment2);
			legislationProcessTaskService.update(legislationProcessTask);

			List<LegislationProcessDeal> list = legislationProcessDealService.findByHQL("from LegislationProcessDeal t where 1=1 and t.stDocId='"+stDocId+"' and t.stActionId='"+stNodeId+"'");
			if(list!=null && list.size()>0){
				LegislationProcessDeal legislationProcessDeal = list.get(0);
				legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
				legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
				legislationProcessDeal.setStUserId(userId);
				legislationProcessDeal.setStUserName(userName);
				legislationProcessDeal.setDtDealDate(new Date());
				legislationProcessDealService.update(legislationProcessDeal);
			}else{
				LegislationProcessDeal legislationProcessDeal = new LegislationProcessDeal();
				legislationProcessDeal.setStDocId(stDocId);
				legislationProcessDeal.setStActionId(stNodeId);
				legislationProcessDeal.setStActionName("立法听证会会后");
				legislationProcessDeal.setStUserId(userId);
				legislationProcessDeal.setStUserName(userName);
				legislationProcessDeal.setStBakOne(legislationProcessDoc.getStDocName());
				legislationProcessDeal.setStBakTwo(legislationProcessDoc.getStComent());
				legislationProcessDeal.setDtDealDate(new Date());
				legislationProcessDealService.add(legislationProcessDeal);
			}


		}
		Enumeration keys=request.getParameterNames();
		while(keys.hasMoreElements()){
			String key=(String)keys.nextElement();
			String value=request.getParameter(key);
			if(value.startsWith("FIL_")){
				legislationFilesService.executeSqlUpdate("update LegislationFiles s set s.stParentId='"+stTaskId+"' where s.stFileId='"+value+"'");
			}
		}
		return null;
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

}
