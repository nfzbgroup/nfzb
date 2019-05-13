package com.wonders.fzb.legislation.web;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislation.beans.*;
import com.wonders.fzb.legislation.services.*;
import com.wonders.fzb.report.beans.LegislationReport;
import com.wonders.fzb.report.beans.LegislationReportTask;
import com.wonders.fzb.report.services.LegislationReportService;
import com.wonders.fzb.report.services.LegislationReportTaskService;
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

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Namespace("/legislationReport")
@Controller
@Scope("prototype")
public class LegislationReportAction extends BaseAction {

	private static final long serialVersionUID = -5052398774978475642L;

	@Autowired
	@Qualifier("legislationProcessTaskService")
	private LegislationProcessTaskService legislationProcessTaskService;

	@Autowired
	@Qualifier("wegovSimpleNodeService")
	private WegovSimpleNodeService wegovSimpleNodeService;

	@Autowired
	@Qualifier("legislationProcessDocService")
	private LegislationProcessDocService legislationProcessDocService;
	@Autowired
	@Qualifier("legislationExampleService")
	private LegislationExampleService legislationExampleService;
	@Autowired
	@Qualifier("legislationFilesService")
	private LegislationFilesService legislationFilesService;
	@Autowired
	@Qualifier("legislationProcessTaskdetailService")
	private LegislationProcessTaskdetailService legislationProcessTaskdetailService;
	
	
	@Autowired
	@Qualifier("legislationReportService")
	private LegislationReportService legislationReportService;
	
	@Autowired
	@Qualifier("legislationReportTaskService")
	private LegislationReportTaskService legislationReportTaskService;
	

	String stDocId = "";
	String stNodeId = "";
	String op = "";

	public String getStDocId() {
		return stDocId;
	}

	public void setStDocId(String stDocId) {
		this.stDocId = stDocId;
	}

	public String getStNodeId() {
		return stNodeId;
	}

	public void setStNodeId(String stNodeId) {
		this.stNodeId = stNodeId;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	/**
	 * 打开报审审签页面
	 * 
	 * @return
	 * @throws Exception
	 * @author sy
	 */
	@Action(value = "openDraftReportPage", results = { @Result(name = SUCCESS, location = "/report/openDraftReportPage.jsp") })
	public String openDraftReportPage() throws Exception {

		stDocId = request.getParameter("stDocId") == null ? "" : request.getParameter("stDocId");
		stNodeId = request.getParameter("stNodeId") == null ? "" : request.getParameter("stNodeId");
		String state = request.getParameter("state") == null ? "" : request.getParameter("state");
		LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
		List<LegislationProcessTask> legislationProcessTaskList = legislationProcessTaskService.findTaskByDocIdAndNodeId(stDocId, stNodeId);
/*		if (legislationProcessTaskList.size() > 0) {
			Map<String, Object> condMap = new HashMap<>();
			Map<String, String> sortMap = new HashMap<>();
			condMap.put("stParentId", stDocId);
			condMap.put("stNodeId", stNodeId);
			sortMap.put("dtPubDate", "ASC");
			List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
			if ("TODO".equals(legislationProcessTaskList.get(0).getStTaskStatus())) {
				List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, legislationFilesList);
				request.setAttribute("LegislationExampleList", legislationExampleFilesList);
			} else {
				String stStyle = "style ='display: none;'";
				request.setAttribute("strDisplay", stStyle);
			}
			if("banli".equals(state)) {
				//办理页面打开正在处理中的任务
				String stStyle = "style ='display: none;'";
				request.setAttribute("strDisplay", stStyle);
				request.setAttribute("isbanli", "banli");//提示用户在对应模块中进行操作
			}
			request.setAttribute("legislationFilesList", legislationFilesList);
			request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
		} else {
			List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesList(stNodeId, null);
			request.setAttribute("legislationProcessTask", new LegislationProcessTask());
			request.setAttribute("LegislationExampleList", legislationExampleFilesList);
		}*/

		
		//回显上传材料
		String nodeStatus = request.getParameter("stTaskStatus");
		if(!"null".equals(nodeStatus)){
			nodeStatus="TODO";
		}
		String method="";
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
						condMap.put("stParentId",stDocId);
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

				String buttonId = request.getParameter("buttonId");
		
		request.setAttribute("nodeId", stNodeId);
		request.setAttribute("stDocId", stDocId);
		request.setAttribute("buttonId", request.getParameter("buttonId"));
		request.setAttribute("requestUrl", request.getRequestURI());
		request.setAttribute("legislationProcessDoc", legislationProcessDoc);
		
		return SUCCESS;
	}

	/**
	 * 打开报审审签按钮
	 * 
	 * @throws Exception
	 * @author sy
	 */
	@Action(value = "draftReportBt")
	public void draftReportBt() throws Exception {
		JSONObject jsonObject = new JSONObject();
		stDocId = request.getParameter("stDocId") == null ? "" : request.getParameter("stDocId");
		op = request.getParameter("stDocId") == null ? "" : request.getParameter("stDocId");
		if ("save".equals(op)) {
			// legislationProcessTaskService.
			// 在legislation_process_task表中添加一条 todo的信息

		}

		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}
	
	
	
	//保存签报任务，可能提交
	@Action(value = "saveSendMayorDatum")
	public void saveSendMayorDatum() throws Exception {
		JSONObject jsonObject = new JSONObject();
		stDocId = request.getParameter("stDocId") == null ? "" : request.getParameter("stDocId");
		stNodeId = request.getParameter("stNodeId") == null ? "" : request.getParameter("stNodeId");
		String op = request.getParameter("op") == null ? "" : request.getParameter("op");
		
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String userRoleId = session.getAttribute("userRoleId").toString();
		String userRole = session.getAttribute("userRole").toString();
		WegovSimpleNode node = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='"+stNodeId+"'").get(0);
		String stTaskId = legislationProcessDocService.saveLegislation(request, currentPerson, userRoleId, userRole, stNodeId, node.getStNodeName());

		//提交
		if ("submit".equals(op)) {
			WegovSimpleNode nextNode=legislationProcessTaskService.nextProcess(stDocId,stNodeId,session);
			//产生190报签的主表与任务表
			Date nowDate=new Date();
			LegislationProcessDoc legislationProcessDoc = legislationProcessDocService.findById(stDocId);
			WegovSimpleNode nodeReport = wegovSimpleNodeService.findByHQL("from WegovSimpleNode t where t.stNodeId='NOD_0000000190'").get(0);
			LegislationReport legislationReport=new LegislationReport();
			legislationReport.setStReportName(legislationProcessDoc.getStDocName());
			legislationReport.setStSourceDoc(legislationProcessDoc.getStDocId());
			legislationReport.setStNodeId("NOD_0000000190");
			legislationReport.setStNodeName(nodeReport.getStNodeName());
			legislationReport.setDtCreateDate(nowDate);
			legislationReport.setStType(stNodeId);//来源于草案的节点，一般一个草案两次签报
			String reportId=legislationReportService.addObj(legislationReport);
			
			LegislationReportTask reportTask=new LegislationReportTask();
			reportTask.setStReportId(reportId);
			reportTask.setStNodeId(nodeReport.getStNodeId());
			reportTask.setStNodeName(nodeReport.getStNodeName());
			reportTask.setStRoleId(nodeReport.getStSubmitRole());
			reportTask.setDtOpenDate(nowDate);
			reportTask.setStTaskStatus("TODO");
			legislationReportTaskService.addObj(reportTask);
		}

		
		//上传的材料添加st_parent_id字段数据
		legislationFilesService.updateParentIdById(request,stDocId);

		jsonObject.put("stTaskId", stTaskId);
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
	}
	
	
	


}
