package com.wonders.fzb.report.web;

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
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.checkmeeting.beans.LegislationCheckmeeting;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislation.beans.LegislationFiles;
import com.wonders.fzb.legislation.beans.LegislationProcessDoc;
import com.wonders.fzb.legislation.beans.LegislationProcessTask;
import com.wonders.fzb.legislation.beans.LegislationSendNotice;
import com.wonders.fzb.legislation.services.LegislationExampleService;
import com.wonders.fzb.legislation.services.LegislationFilesService;
import com.wonders.fzb.legislation.services.LegislationProcessDocService;
import com.wonders.fzb.legislation.services.LegislationSendNoticeService;
import com.wonders.fzb.report.beans.*;
import com.wonders.fzb.report.services.*;
import com.wonders.fzb.simpleflow.beans.WegovSimpleNode;
import com.wonders.fzb.simpleflow.services.WegovSimpleNodeService;

import dm.jdbc.util.StringUtil;

/**
 * LegislationReport action接口
 * 
 * @author scalffold created by lj
 */

@Namespace("/legislationReport")
@Controller
@Scope("prototype")
public class LegislationReportAction extends BaseAction {

	private static final long serialVersionUID = -5236871814191219582L;
	@Autowired
	@Qualifier("legislationReportService")
	private LegislationReportService legislationReportService;

	@Autowired
	@Qualifier("legislationReportTaskService")
	private LegislationReportTaskService legislationReportTaskService;

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
	@Qualifier("legislationSendNoticeService")
	private LegislationSendNoticeService legislationSendNoticeService;
	
	@Autowired
	@Qualifier("legislationReportTaskdetailService")
	private LegislationReportTaskdetailService legislationReportTaskdetailService;

	private int pageNo = 1;
	private int pageSize = 10;

	// LegislationReport的修改
	@Action(value = "legislationReport_add", results = { 
			@Result(name = SUCCESS, location = "/LegislationReport.jsp"), 
			@Result(name = "List", location = "/legislationReport_list.jsp") })
	public String legislationReport_add() throws FzbDaoException {
		// System.out.println("Begin....");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<LegislationReport> legislationReportList = new ArrayList<LegislationReport>();
		LegislationReport legislationReport = new LegislationReport();
		legislationReportService.add(legislationReport);
		return SUCCESS;
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

	@Action(value = "report_doc_info", results = { 
			@Result(name = "checkMeetingStart_old", location = "/checkmeeting/ckeckMeeting_start.jsp"),
			@Result(name = "report_info_add", location = "/report/report_info_add.jsp"),
			@Result(name = "report_info_input", location = "/report/report_info_input.jsp"),
			@Result(name = "report_info_doing", location = "/report/report_info_doing.jsp"), 
			@Result(name = "report_info_result", location = "/report/report_info_result.jsp"),
			@Result(name = "report_info_done", location = "/report/report_info_done.jsp") })
	public String report_doc_info() throws Exception {
		String methodStr = request.getParameter("method");
		java.lang.reflect.Method method = this.getClass().getDeclaredMethod(methodStr);
		Object object = method.invoke(this);
		return object == null ? null : object.toString();
	}

	/**
	 * 抽取签报弹窗页面方法共同代码
	 * 
	 * @return
	 */
	private void reportPage() {
		String stNodeId = request.getParameter("stNodeId");
		request.setAttribute("LegislationExampleList", legislationExampleService.queryLegislationExampleFilesList(stNodeId, null));
		String nodeStatus = request.getParameter("stTaskStatus");
		String stReportId = request.getParameter("stReportId");
		LegislationReport legislationReport = legislationReportService.findById(stReportId);
		List<LegislationReportTask> taskList = legislationReportTaskService.findByHQL("from LegislationReportTask t where t.stReportId='" + stReportId + "' and t.stEnable is null");
		LegislationReportTask legislationReportTask = taskList.get(0);
		List<LegislationReportTaskdetail> taskdetailList=new ArrayList<LegislationReportTaskdetail>();
		if(!"DOING".equals(nodeStatus)) {
			//送审领导信息
			taskdetailList = legislationReportTaskdetailService.findByHQL("from LegislationReportTaskdetail t where t.stTaskId='" + legislationReportTask.getStTaskId() + "' and t.stTaskStatus='TODO'");
		}else {
			//报审相关信息
			taskdetailList = legislationReportTaskdetailService.findByHQL("from LegislationReportTaskdetail t where t.stTaskId='" + legislationReportTask.getStTaskId() + "' and t.stTaskStatus='DOING'");
		}
		LegislationReportTaskdetail legislationReportTaskdetail=null;
		if(taskdetailList.size()>0) {
        	legislationReportTaskdetail = taskdetailList.get(0);
        }
		
		Map<String, Object> condMap = new HashMap<>();
		Map<String, String> sortMap = new HashMap<>();
		condMap.put("stDocId", stReportId);
		condMap.put("stNodeName","签报件报OA审核");
		List<LegislationSendNotice> legislationSendNoticeList = legislationSendNoticeService.findByList(condMap, sortMap);
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
				condMap.clear();
				condMap.put("stParentId", stReportId);
				condMap.put("stNodeId", "NOD_0000000190");
				condMap.put("stNodeStatus", nodeStatus);
				sortMap.put("dtPubDate", "ASC");
				List<LegislationFiles> legislationFilesList = legislationFilesService.findByList(condMap, sortMap);
					List<Map> legislationExampleFilesList = legislationExampleService.queryLegislationExampleFilesListByNodeStatus("NOD_0000000190", nodeStatus, legislationFilesList);
					request.setAttribute("LegislationExampleList", legislationExampleFilesList);
				
					String stStyle = "style ='display: none;'";
					request.setAttribute("stStyle", stStyle);
				request.setAttribute("nodeStatus", nodeStatus);
				request.setAttribute("legislationFilesList", legislationFilesList);
				//request.setAttribute("legislationProcessTask", legislationProcessTaskList.get(0));
			}
		}

		request.setAttribute("legislationReport", legislationReport);
		request.setAttribute("legislationReportTask", legislationReportTask);
		request.setAttribute("stTaskStatus", legislationReportTask.getStTaskStatus());
		request.setAttribute("legislationReportTaskdetail", legislationReportTaskdetail);
		request.setAttribute("legislationSendNoticeList", legislationSendNoticeList);
	}

	/**
	 * 打开报签修改页面（发起时，已经生成了todo190）
	 * 
	 * @return
	 */
	private String report_info_add() {
		reportPage();
		return pageController();
	}

	/**
	 * 打开报签审批录入页面
	 * 
	 * @return
	 */
	private String report_info_input() {
		reportPage();
		return pageController();
	}

	/**
	 * 打开报签待报审页面
	 * 
	 * @return
	 */
	private String report_info_doing() {
		UserInfo currentPerson = (UserInfo) session.getAttribute("currentPerson");
		String stUserName = currentPerson.getName();
		String stTeamName = currentPerson.getTeamInfos().get(0).getTeamName();
		request.setAttribute("stUserName", stUserName);
		request.setAttribute("stTeamName", stTeamName);
		reportPage();
		return pageController();
	}

	/**
	 * 打开报审结果编辑页面
	 * 
	 * @return
	 */
	private String report_info_result() {
		reportPage();
		return pageController();
	}

	/**
	 * 报签审批结果查看页面
	 * 
	 * @return
	 */
	private String report_info_done() {
		String stNodeId = request.getParameter("stNodeId");
		String stReportId = request.getParameter("stReportId");
		reportPage();
		List<LegislationFiles> reportList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + stReportId + "' and t.stNodeId='" + stNodeId + "' and t.stSampleId !='null' order by t.stSampleId ");
		List<LegislationFiles> otherDocList = legislationFilesService.findByHQL("from LegislationFiles t where 1=1 and t.stParentId ='" + stReportId + "' and t.stNodeId='" + stNodeId + "' and t.stSampleId='null' order by t.stFileId ");
		for (LegislationFiles legislationFiles : otherDocList) {
			reportList.add(legislationFiles);
		}
		request.setAttribute("reportList", reportList);
		return pageController();
	}

	/**
	 * 保存审核会议信息
	 * 
	 * @return
	 * @throws Exception
	 */
	private String saveReport() throws Exception {
		legislationReportTaskService.saveReport(request, session);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObject);
		return null;
	}

}
