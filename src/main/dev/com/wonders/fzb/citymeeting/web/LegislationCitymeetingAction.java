package com.wonders.fzb.citymeeting.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeetingTask;
import com.wonders.fzb.citymeeting.beans.*;
import com.wonders.fzb.citymeeting.services.*;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.plan.beans.LegislationPlan;
import com.wonders.fzb.plan.beans.LegislationPlanTask;
import com.wonders.fzb.plan.services.LegislationPlanService;
import com.wonders.fzb.plan.services.LegislationPlanTaskService;
import com.wonders.fzb.report.beans.LegislationReport;
import com.wonders.fzb.report.beans.LegislationReportTask;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;

/**
 * LegislationCitymeeting action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislationCitymeeting")
@Controller
@Scope("prototype")
public class LegislationCitymeetingAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationCitymeetingService")
	private LegislationCitymeetingService legislationCitymeetingService;
	
	@Autowired
	@Qualifier("legislationCitymeetingTaskService")
	private LegislationCitymeetingTaskService legislationCitymeetingTaskService;
	
	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamInfoService;
	
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
	@Qualifier("legislationPlanTaskService")
	private LegislationPlanTaskService legislationPlanTaskService;
	
	@Autowired
	@Qualifier("legislationPlanService")
	private LegislationPlanService legislationPlanService;
	
	@Autowired
	@Qualifier("legislationSendNoticeService")
	private LegislationSendNoticeService legislationSendNoticeService;
	
	@Autowired
	@Qualifier("legislationCitymeetingTaskdService")
	private LegislationCitymeetingTaskdService legislationCitymeetingTaskdService;

	private int pageNo = 1;
	private int pageSize = 10;


	//LegislationCitymeeting的修改
	@Action(value = "legislationCitymeeting_add", results = {@Result(name = SUCCESS, location = "/LegislationCitymeeting.jsp"), @Result(name = "List", location = "/legislationCitymeeting_list.jsp")})
	public String legislationCitymeeting_add() throws FzbDaoException {
//		System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationCitymeeting> legislationCitymeetingList = new ArrayList<LegislationCitymeeting>();
		LegislationCitymeeting legislationCitymeeting = new LegislationCitymeeting();
		legislationCitymeetingService.add(legislationCitymeeting);
		return SUCCESS;
	}
	
	
	
	@Action(value = "city_meeting_info", results = {
			@Result(name = "city_meeting__TODO", location = "/citymeeting/city_meeting__TODO.jsp"), 
			@Result(name = "city_meeting__RESULT", location = "/citymeeting/city_meeting__RESULT.jsp"), 
			@Result(name = "city_meeting__AFFIRM", location = "/citymeeting/city_meeting__AFFIRM.jsp"),
			@Result(name = "city_meeting__INPUT", location = "/citymeeting/city_meeting__INPUT.jsp"),
			@Result(name = "city_meeting__DONE", location = "/citymeeting/city_meeting__DONE.jsp")
	})
	public String city_meeting_info() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}

	/**
	 * 打开常务会议议题添加页面（发起时，已经生成了todo180）
	 * 
	 * @return
	 */
	private String city_meeting__TODO() {
		String stNodeId = request.getParameter("stNodeId");
		//String stTopicId = request.getParameter("stTopicId");
		String stNoticeId = request.getParameter("stNoticeId");
		// 可选择的草案
		//List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stEnable is null order by d.dtCreateDate desc");
		//request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		String stTopicId = request.getParameter("stTopicId")==null?"":request.getParameter("stTopicId");
		if(stTopicId.equals("TODO")){

			//回显上传材料
			String method = "citymeeting_TODO";
			String nodeIdStatus[] = method.split("_");
			String nodeStatus = nodeIdStatus[1];
					if (true) {
						Map<String, Object> condMap = new HashMap<>();
						Map<String, String> sortMap = new HashMap<>();
						condMap.put("stParentId",null);
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
		}else{
		//回显上传材料
		String method = "citymeeting_TODO";
		String nodeIdStatus[] = method.split("_");
		String nodeStatus = nodeIdStatus[1];
				if (true) {
					Map<String, Object> condMap = new HashMap<>();
					Map<String, String> sortMap = new HashMap<>();
					condMap.put("stParentId",stTopicId);
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
				}}
		
		if(stNoticeId!=null&&!stNoticeId.isEmpty()) {
			//常务会议议题接收 --查看
			LegislationSendNotice legislationSendNotice = legislationSendNoticeService.findById(stNoticeId);
			LegislationCitymeeting legislationCitymeeting = legislationCitymeetingService.findById(legislationSendNotice.getStDocId());
			request.setAttribute("legislationCitymeeting", legislationCitymeeting);
			LegislationCitymeetingTask legislationCitymeetingTask = legislationCitymeetingTaskService.findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + legislationSendNotice.getStDocId() + "' and t.stNodeId='NOD_0000000180'").get(0);
			request.setAttribute("legislationCitymeetingTask", legislationCitymeetingTask);
			request.setAttribute("stNoticeId", stNoticeId);
			String itemId=legislationSendNotice.getStBak();
			
			
			
			
			if(null!=itemId&&!itemId.isEmpty()) {
				String itemName=null;
				String itemType=null;
				//常务会议对应事项
				if("DFT".equals(itemId.substring(0,3))) {
					LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(itemId);
					itemName=legislationProcessDoc.getStDocName();
					itemType="立法草案";
				}else if("PLA".equals(itemId.substring(0,3))) {
					LegislationPlan legislationPlan = legislationPlanService.findById(itemId);
					itemName = legislationPlan.getStPlanName();
					itemType="立法计划";
				}
				request.setAttribute("itemName", itemName);
				request.setAttribute("itemType", itemType);
			}
		
			
		
		}else {
			if (StringUtil.isEmpty(stTopicId)) {
				request.setAttribute("legislationCitymeeting", new LegislationCitymeeting());
			} else {
				LegislationCitymeeting auditMeeting = legislationCitymeetingService.findById(stTopicId);
				request.setAttribute("legislationCitymeeting", auditMeeting);
			}
			if("TODO".equals(stTopicId)){
				request.setAttribute("legislationCitymeetingTask", new LegislationCitymeetingTask());
			}else{
				LegislationCitymeetingTask legislationCitymeetingTask = legislationCitymeetingTaskService.findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
				LegislationCitymeetingTaskd legislationCitymeetingTaskd = legislationCitymeetingTaskdService.findByHQL("from LegislationCitymeetingTaskd t where t.stTaskId='" + legislationCitymeetingTask.getStTaskId() + "' and t.stTaskStatus='TODO'").get(0);
				request.setAttribute("legislationCitymeetingTask", legislationCitymeetingTask);
				request.setAttribute("legislationCitymeetingTaskd", legislationCitymeetingTaskd);
			}
			request.setAttribute("stTaskStatus", "TODO");
		}
		
		
				
		
		
		return pageController();
	}
	/**
	 * 打开常务会议议题结果（发起时，已经生成了todo180）
	 * 
	 * @return
	 */
	private String city_meeting__RESULT() {
		String stNodeId = request.getParameter("stNodeId");
		//request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		// 可选择的草案
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stEnable is null order by d.dtCreateDate desc");
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		
		String stTopicId = request.getParameter("stTopicId");
		if (StringUtil.isEmpty(stTopicId)) {
			request.setAttribute("legislationCitymeeting", new LegislationCitymeeting());
		} else {
			LegislationCitymeeting auditMeeting = legislationCitymeetingService.findById(stTopicId);
			request.setAttribute("legislationCitymeeting", auditMeeting);
		}
		LegislationCitymeetingTask legislationCitymeetingTask = legislationCitymeetingTaskService.findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
		// 查询处室
		List<TeamInfo> teamInfoListRaw = teamInfoService.findTeamInfoInModuleByType("MODULE_LEGISLATE","市司法局处室");
		List<TeamInfo> teamInfoList=new ArrayList<TeamInfo>();
		for(TeamInfo each:teamInfoListRaw){
			if(each.getTeamName().indexOf("立法")>=0||each.getTeamName().indexOf("调研")>=0)  teamInfoList.add(each);
		}
		request.setAttribute("stTopicId", stTopicId);
		request.setAttribute("teamList", teamInfoList);
		request.setAttribute("legislationCitymeetingTask", legislationCitymeetingTask);
		request.setAttribute("stTaskStatus", legislationCitymeetingTask.getStTaskStatus());
		
		
		//回显上传材料
		String method = "citymeeting_RESULT";
		String nodeIdStatus[] = method.split("_");
		String nodeStatus = nodeIdStatus[1];
				if (true) {
					Map<String, Object> condMap = new HashMap<>();
					Map<String, String> sortMap = new HashMap<>();
					condMap.put("stParentId",stTopicId);
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
	/**
	 * 打开常务会议材料确认
	 * 
	 * @return
	 */
	private String city_meeting__AFFIRM() {
		String stNodeId = request.getParameter("stNodeId");
		String stTopicId = request.getParameter("stTopicId");
		// 议题对应的草案
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000110' and t.stTaskStatus='DONE' and t.stComment2='"+stTopicId+"' and t.stEnable is null order by d.dtCreateDate desc");
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		// 议题对应的计划
		List<LegislationPlanTask> legislationPlanTaskList = legislationPlanTaskService.findByHQL("from LegislationPlanTask t where t.stNodeId='NOD_0000000214' and t.stTaskStatus='TODO' and t.stTopicId='"+stTopicId+"' order by t.dtOpenDate desc");
		if(legislationPlanTaskList.size()>0){
			request.setAttribute("legislationPlanTaskList", legislationPlanTaskList);
		}
		if (StringUtil.isEmpty(stTopicId)) {
			request.setAttribute("legislationCitymeeting", new LegislationCitymeeting());
		} else {
			LegislationCitymeeting auditMeeting = legislationCitymeetingService.findById(stTopicId);
			request.setAttribute("legislationCitymeeting", auditMeeting);
		}
		LegislationCitymeetingTask legislationCitymeetingTask = legislationCitymeetingTaskService.findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
		request.setAttribute("legislationCitymeetingTask", legislationCitymeetingTask);
		request.setAttribute("stTaskStatus", legislationCitymeetingTask.getStTaskStatus());
		
		//回显上传材料
				String method = "citymeeting_AFFIRM";
				String nodeIdStatus[] = method.split("_");
				String nodeStatus = nodeIdStatus[1];
						if (true) {
							Map<String, Object> condMap = new HashMap<>();
							Map<String, String> sortMap = new HashMap<>();
							condMap.put("stParentId",stTopicId);
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
	/**
	 * 打开常务会议会议会议记要
	 * 
	 * @return
	 */
	private String city_meeting__INPUT() {
		String stNodeId = request.getParameter("stNodeId");
		// 可选择的草案
		//List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stEnable is null order by d.dtCreateDate desc");
		//request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		String stTopicId = request.getParameter("stTopicId");	
		//待处理计划
		List<LegislationPlanTask> legislationPlanTaskList = legislationPlanTaskService.findByHQL("from LegislationPlanTask t where t.stNodeId='NOD_0000000214' and t.stTaskStatus='DOING' and t.stTopicId='"+stTopicId+"' order by t.dtOpenDate desc");
		request.setAttribute("legislationPlanTaskList", legislationPlanTaskList);
		//待处理草案
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000110' and t.stTaskStatus='DONE' and t.stComment2='"+stTopicId+"' and t.stEnable is null order by d.dtCreateDate desc");
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		if (StringUtil.isEmpty(stTopicId)) {
			request.setAttribute("legislationCitymeeting", new LegislationCitymeeting());
		} else {
			LegislationCitymeeting auditMeeting = legislationCitymeetingService.findById(stTopicId);
			request.setAttribute("legislationCitymeeting", auditMeeting);
		}
		LegislationCitymeetingTask legislationCitymeetingTask = legislationCitymeetingTaskService.findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
		request.setAttribute("legislationCitymeetingTask", legislationCitymeetingTask);
		request.setAttribute("stTaskStatus", legislationCitymeetingTask.getStTaskStatus());
		
		//回显上传材料
		String method = "citymeeting_INPUT";
		String nodeIdStatus[] = method.split("_");
		String nodeStatus = nodeIdStatus[1];
				if (true) {
					Map<String, Object> condMap = new HashMap<>();
					Map<String, String> sortMap = new HashMap<>();
					condMap.put("stParentId", stTopicId);
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
	/**
	 * 打开常务会完成查看
	 * 
	 * @return
	 */
	private String city_meeting__DONE() {
		String stNodeId = request.getParameter("stNodeId");
		// 可选择的草案
		//List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000105' and t.stTaskStatus='TODO' and t.stEnable is null order by d.dtCreateDate desc");
		//request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		
		String stTopicId = request.getParameter("stTopicId");
		//待处理草案
		List<LegislationProcessDoc> legislationProcessDocList = legislationProcessDocService.findByHQL("select d from LegislationProcessDoc d inner join LegislationProcessTask t on d.stDocId=t.stDocId where 1=1 and t.stNodeId='NOD_0000000110' and t.stTaskStatus='DONE' and t.stComment2='"+stTopicId+"' and t.stEnable is null order by d.dtCreateDate desc");
		request.setAttribute("legislationProcessDocList", legislationProcessDocList);
		//待处理计划
		List<LegislationPlanTask> legislationPlanTaskList = legislationPlanTaskService.findByHQL("from LegislationPlanTask t where t.stNodeId='NOD_0000000214' and t.stTaskStatus='DOING' and t.stTopicId='"+stTopicId+"' order by t.dtOpenDate desc");
		request.setAttribute("legislationPlanTaskList", legislationPlanTaskList);
		if (StringUtil.isEmpty(stTopicId)) {
			request.setAttribute("legislationCitymeeting", new LegislationCitymeeting());
		} else {
			LegislationCitymeeting auditMeeting = legislationCitymeetingService.findById(stTopicId);
			request.setAttribute("legislationCitymeeting", auditMeeting);
		}
		LegislationCitymeetingTask legislationCitymeetingTask = legislationCitymeetingTaskService.findByHQL("from LegislationCitymeetingTask t where t.stTopicId='" + stTopicId + "' and t.stNodeId='NOD_0000000180'").get(0);
		request.setAttribute("legislationCitymeetingTask", legislationCitymeetingTask);
		request.setAttribute("stTaskStatus", legislationCitymeetingTask.getStTaskStatus());
		
		//回显上传材料
		String nodeStatus = "INPUT";
		String method="city_meeting__DONE";
				if(true){
					//String nodeIdStatus[] = method.split("__");	
					//String nodeStatus = nodeIdStatus[1];		
					String stItemId = request.getParameter("stItemIds");
					String stItemIds =  request.getParameter("stItemId");
					if(stItemIds!=null){
						String[] strs = stItemIds.split(",");
						StringBuilder builder = new StringBuilder();
						for (int i = 0; i < strs.length; i++) {
						builder.append("'"+ strs[i] + (i!=strs.length-1?"',":"'"));
						}
					}
					//回显上传材料
					if (true) {
						Map<String, Object> condMap = new HashMap<>();
						Map<String, String> sortMap = new HashMap<>();
						condMap.put("stParentId",stTopicId);
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
				}

		
		
		return pageController();
	}
	
	
	private String saveLegislation(String stNodeId, String string) {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		String stNodeName = request.getParameter("stNodeName");  
		String stComment1 = request.getParameter("stComment1");
		LegislationCitymeetingTask legislationCitymeetingTask = new LegislationCitymeetingTask();
		legislationCitymeetingTask.setStNodeName(stNodeName);
		legislationCitymeetingTask.setStComment1(stComment1);
		
		legislationCitymeetingTaskService.add(legislationCitymeetingTask);
		return null;
	}



	/**
	 * 保存常务会议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	private String saveCitymeeting() throws Exception {
		legislationCitymeetingTaskService.saveCitymeeting(request, session);
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
		String stReportId = request.getParameter("stReportId");

		String methodStr = request.getParameter("method");

		String stNodeId = request.getParameter("stNodeId");

		String stTaskId = request.getParameter("stTaskId");

		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stReportId", stReportId);
		request.setAttribute("stTaskId", stTaskId);
		request.setAttribute("requestUrl", request.getRequestURI());
		return methodStr;
	}
}
