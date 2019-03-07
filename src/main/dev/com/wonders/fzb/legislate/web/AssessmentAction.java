package com.wonders.fzb.legislate.web;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import com.wonders.fzb.base.actions.FileManageAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.MOR;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.framework.services.UserInfoService;
import com.wonders.fzb.legislate.beans.Assessment;
import com.wonders.fzb.legislate.beans.AssessmentAttach;
import com.wonders.fzb.legislate.beans.AssessmentDis;
import com.wonders.fzb.legislate.beans.AssessmentReport;
import com.wonders.fzb.legislate.beans.AssessmentReportAttach;
import com.wonders.fzb.legislate.beans.AssessmentReportDis;
import com.wonders.fzb.legislate.services.AssessmentAttachService;
import com.wonders.fzb.legislate.services.AssessmentDisService;
import com.wonders.fzb.legislate.services.AssessmentReportAttachService;
import com.wonders.fzb.legislate.services.AssessmentReportDisService;
import com.wonders.fzb.legislate.services.AssessmentReportService;
import com.wonders.fzb.legislate.services.AssessmentService;

@Namespace("/legislate")
@Controller
@Scope("prototype")
public class AssessmentAction extends FileManageAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3336862312608527142L;
	
	@Autowired
	@Qualifier("assessmentReportAttachService")
	private AssessmentReportAttachService assessmentReportAttachService;
	
	@Autowired
	@Qualifier("assessmentReportDisService")
	private AssessmentReportDisService assessmentReportDisService;
	
	@Autowired
	@Qualifier("assessmentReportService")
	private AssessmentReportService assessmentReportService;
	
	@Autowired
	@Qualifier("assessmentService")
	private AssessmentService assessmentService;
	
	@Autowired
	@Qualifier("assessmentDisService")
	private AssessmentDisService assessmentDisService;
	
	@Autowired
	@Qualifier("assessmentAttachService")
	private AssessmentAttachService assessmentAttachService;
	
	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userInfoService;
	
	@Autowired
	@Qualifier("teamInfoService")
	private TeamInfoService teamInfoService;
	
	protected String assessmentTitle;
	
	protected String assessmentContent;
	
	protected String memo;
	
	protected String secretLevel;
	
	protected String queryType;
	
	public InputStream inputStream;
	// 附件下载
	String fileName = "";
	byte[] fileContent = null;
	
	String assessmentId = "";
	String flag = "";
	
	// 导出 word
	public String docName;
	public InputStream inputStreamSus;

	//市法制办接收后评估报告
	@Action(value = "assessment_report_list", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_report_list.jsp"),
			@Result(name = "upReport", location = "assessment_reportUp_list.do?flag=${flag}",type="redirectAction"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})  //
	public String assessmentList() throws NumberFormatException, FzbDaoException {
		String receive = request.getParameter("receive")==null?"":request.getParameter("receive");//是否接收
		String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
		if("EDIT".equals(flag)) {
			return "upReport";
		}
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}
		UserInfo currentUser = getLoginUser();
		if(currentUser==null) {//登录信息为空，重新登录
			return LOGIN;
		}else {//已接收、未接收列表
			queryType = request.getParameter("queryType")==null?"":request.getParameter("queryType");
			String queryValue = request.getParameter("queryValue")==null?"":request.getParameter("queryValue");
			TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
			Map<String,Object> condMap = new HashMap<String,Object>();
			Map<String,String> sortMap = new LinkedHashMap<String,String>();
			if("assessmentTitle".equals(queryType)) {
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}else if("agentUnitName".equals(queryType)){
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}
			condMap.put("receive", receive);//Y/N
			condMap.put("receiveUnit", currentTeam.getId());//U0
			sortMap.put("insertDate", "DESC");
			Page<AssessmentReport> assessmentReports = assessmentReportService.findByPage(condMap, sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			request.setAttribute("queryType", queryType);
			request.setAttribute("queryValue", queryValue);
			request.setAttribute("retPage", assessmentReports);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("receive", receive);
			request.setAttribute("currentTeam", currentTeam);
			return SUCCESS;
		}
		
	}
	
	//单位编辑上报后评估报告
	@Action(value = "assessment_reportUp_list", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_reportUp_list.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentReportUpList() throws NumberFormatException, FzbDaoException {
		String flag = request.getParameter("flag")==null?"":request.getParameter("flag");//编辑、上报
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}
		UserInfo currentUser = getLoginUser();
		if(currentUser==null) {//登录信息为空，重新登录
			return LOGIN;
		}else {//已接收、未接收列表
			queryType = request.getParameter("queryType")==null?"":request.getParameter("queryType");
			String queryValue = request.getParameter("queryValue")==null?"":request.getParameter("queryValue");
			TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
			Map<String,Object> condMap = new HashMap<String,Object>();
			Map<String,String> sortMap = new LinkedHashMap<String,String>();
			if("assessmentTitle".equals(queryType)) {
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}else if("agentUnitName".equals(queryType)){
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}
			if("EDIT".equals(flag)) {
				condMap.put("receive"+"IsNull", "");//NULL、not NULL
			}else {
				condMap.put("receive"+"IsNotNull", "");//NULL、not NULL
			}
			condMap.put("agentUnit", currentTeam.getId());//U0
			sortMap.put("insertDate", "DESC");
			Page<AssessmentReport> assessmentReports = assessmentReportService.findByPage(condMap, sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			request.setAttribute("queryType", queryType);
			request.setAttribute("queryValue", queryValue);
			request.setAttribute("retPage", assessmentReports);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("flag", flag);
			request.setAttribute("currentTeam", currentTeam);
			return SUCCESS;
		}
		
	}
	
	//单位编辑上报后评估报告
	@Action(value = "assessment_report_dis_list", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_report_dis_list.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentDisList() throws NumberFormatException, FzbDaoException {
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}
		UserInfo currentUser = getLoginUser();
		if(currentUser==null) {//登录信息为空，重新登录
			return LOGIN;
		}else {//已接收、未接收列表
			queryType = request.getParameter("queryType")==null?"":request.getParameter("queryType");
			String queryValue = request.getParameter("queryValue")==null?"":request.getParameter("queryValue");
			TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
			Map<String,Object> condMap = new HashMap<String,Object>();
			Map<String,String> sortMap = new LinkedHashMap<String,String>();
			List<String> assessmentIdList = new ArrayList<String>(); 
			//
			condMap.put("receiveUnit", currentTeam.getId());
			sortMap.put("receiveDate", "DESC");
			List<AssessmentReportDis> assessmentReportDises = assessmentReportDisService.findByList(condMap, sortMap);
			for(int i=0;i<assessmentReportDises.size();i++) {
				AssessmentReportDis assessmentReportDis = assessmentReportDises.get(i);
				String assessmentId = assessmentReportDis.getAssessmentId();
				assessmentIdList.add(assessmentId);
			}
			condMap.clear();sortMap.clear();
			if("assessmentTitle".equals(queryType)) {
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}else if("agentUnitName".equals(queryType)){
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}
			condMap.put("assessmentId"+"List", assessmentIdList);
			sortMap.put("receiveDate", "DESC");
			Page<AssessmentReport> assessmentReports = assessmentReportService.findByPage(condMap, sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			request.setAttribute("queryType", queryType);
			request.setAttribute("queryValue", queryValue);
			request.setAttribute("retPage", assessmentReports);
			request.setAttribute("pageNo", pageNo);
			return SUCCESS;
		}
		
	}
	
	//单位编辑上报后评估报告
	@Action(value = "assessment_report_dis_unit", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_report_dis_unit.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentDisUnit() throws NumberFormatException, FzbDaoException {
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		UserInfo currentUser = getLoginUser();
		if(currentUser==null) {//登录信息为空，重新登录
			return LOGIN;
		}else {//已接收、未接收列表
			TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
			if(!"U_3_3".equals(currentTeam.getId())) {
				return LOGIN;
			}
			List<String> listUnit = new ArrayList<String>();
			List<AssessmentReportDis> assessmentReporDiss = assessmentReportDisService.findByAssessmentId(assessmentId, null);
			for(int i=0;i<assessmentReporDiss.size();i++) {
				AssessmentReportDis assessmentReportDis = (AssessmentReportDis)assessmentReporDiss.get(i);
				String unitId = assessmentReportDis.getReceiveUnit();
				listUnit.add(unitId);
			}
			List<MOR> mors = teamInfoService.findMorTypeList("MODULE_LEGISLATE","市法制办处室");
			for(int i=0;i<mors.size();i++) {
				MOR mor = mors.get(i);
				String orgCid = mor.getTeamCid();
				if("U_3_3".equals(orgCid)) {
					mors.remove(mor);
				}
			}
			List<AssessmentReportDis> assessmentReportDises = assessmentReportDisService.findByAssessmentId(assessmentId, null);
			List<String> receives = new ArrayList<String>();
			for(int i=0;i<assessmentReportDises.size();i++) {
				AssessmentReportDis assessmentReportDis = assessmentReportDises.get(i);
				if(assessmentReportDis.getReceiveDate()!=null) {
					receives.add(assessmentReportDis.getReceiveUnit());
				}
			}
			request.setAttribute("receives", receives);
			request.setAttribute("mors", mors);
			request.setAttribute("listUnit", listUnit);
			request.setAttribute("assessmentId", assessmentId);
			return SUCCESS;
		}
		
	}
	
	@Action("assessment_report_dis")//分办
	public void assessmentReportDis() throws IOException
	{
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		String[] unitIds = request.getParameterValues("unitIds")==null?new String[100]:request.getParameterValues("unitIds");
		UserInfo currentUser = getLoginUser();
		TeamInfo currentTeam = new TeamInfo();
		if(currentUser.getTeamInfos().size()>0) {
			currentTeam = currentUser.getTeamInfos().get(0);
		}
		PrintWriter out = response.getWriter();
		if("U_3_3".equals(currentTeam.getId())) {
			AssessmentReport assessmentReport = assessmentReportService.findById(assessmentId); 
			boolean flag = true;
			String retMsg = "";
			for(int i=0;i<unitIds.length;i++) {
				String unitId = unitIds[i];
				TeamInfo receiveTeam = teamInfoService.findTeamInfoByTeamId("MODULE_LEGISLATE", unitId);
				List<AssessmentReportDis> assessmentReportDiss = assessmentReportDisService.findByAssessmentId(assessmentId, unitId);
				if(assessmentReportDiss.size()==0) {
					AssessmentReportDis assessmentReportDis = new AssessmentReportDis();
					assessmentReportDis.setAssessmentId(assessmentId);
					assessmentReportDis.setDistributeTitle(assessmentReport.getAssessmentTitle());
					assessmentReportDis.setDistributeUnit(currentTeam.getId());
					assessmentReportDis.setDistributeUnitName(currentTeam.getTeamName());
					assessmentReportDis.setInsertDate(new Date());
					assessmentReportDis.setReceiveUnit(unitId);
					assessmentReportDis.setReceiveUnitName(receiveTeam.getTeamName());
					assessmentReportDisService.add(assessmentReportDis);	
				}else {
					retMsg = receiveTeam.getTeamName()+" ";
					flag = false;
				}
			}
			if(flag) {
				out.write("分送成功。");
			}else {
				out.write(retMsg+"分送失败。请确认是否已经分送。");
			}
		}else {
			out.write("分送失败。");
		}
		out.flush();
	}
	
	@Action(value = "assessment_report_add", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_report_add.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentReportAdd() {
		assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");	
		
		//理由
		
		String op = request.getParameter("op")==null?"":request.getParameter("op");
//		List<String> fileTypes = new ArrayList<String>();
		UserInfo currentUser = getLoginUser();
		if(currentUser.getTeamInfos().size()==0) {
			return LOGIN;
		}
		TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Date nowDate = new Date();
		AssessmentReport assessmentReport = new AssessmentReport();
		if("save".equals(op)) {//
			//添加后评估报告基本信息
			assessmentReport.setAgentId(currentUser.getUserId());
			assessmentReport.setAgentName(currentUser.getName());
			assessmentReport.setAgentUnit(currentTeam.getId());
			assessmentReport.setAgentUnitName(currentTeam.getTeamName());
			assessmentReport.setAssessmentContent(assessmentContent);//概要
			assessmentReport.setAssessmentTitle(assessmentTitle);
			assessmentReport.setInsertDate(nowDate);
			assessmentReport.setMemo(memo);
			assessmentReport.setSecretLevel(secretLevel);
			assessmentReport.setReceiveUnit("U_3_3");//写死
			assessmentReport.setReceiveUnitName("上海市人民政府法制办公室");
			//将后评估报告关联申报项目
			//后续添加关联
			assessmentReport.setYear(df.format(nowDate));
			assessmentReportService.add(assessmentReport);
		}else if("modify".equals(op)){//
			assessmentReport = assessmentReportService.findById(assessmentId);
			assessmentReport.setAgentId(currentUser.getUserId());
			assessmentReport.setAgentName(currentUser.getName());
			assessmentReport.setAgentUnit(currentTeam.getId());
			assessmentReport.setAgentUnitName(currentTeam.getTeamName());
			assessmentReport.setAssessmentContent(assessmentContent);//概要
			assessmentReport.setAssessmentTitle(assessmentTitle);
			assessmentReport.setMemo(memo);
			assessmentReport.setSecretLevel(secretLevel);
			assessmentReport.setInsertDate(nowDate);
			assessmentReport.setReceiveUnit("U_3_3");//写死
			assessmentReport.setReceiveUnitName("上海市人民政府法制办公室");
			//将后评估报告关联申报项目
			//后续添加关联
			assessmentReport.setYear(df.format(nowDate));
			assessmentReportService.saveOrUpdate(assessmentReport);
		}else {
			assessmentReport = assessmentReportService.findById(assessmentId);
			if(assessmentReport==null) {
				assessmentReport = new AssessmentReport();
			}
		}
		memo = assessmentReport.getMemo();
		assessmentContent = assessmentReport.getAssessmentContent();
		assessmentTitle = assessmentReport.getAssessmentTitle();
		secretLevel = assessmentReport.getSecretLevel();
		request.setAttribute("assessmentReport", assessmentReport);
		return SUCCESS;
	}
	
	@Action(value = "assessment_report_attach_add", results = {
			@Result(name = "assessmentReportAdd", location = "assessment_report_add.do?assessmentId=${assessmentId}",type="redirectAction"),//
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String attachAdd() {
		String reportType = request.getParameter("reportType")==null?"":request.getParameter("reportType");
		assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		//String assessmentTitle = request.getParameter("assessmentTitle")==null?"":request.getParameter("assessmentTitle");
	//	String assessmentContent = request.getParameter("assessmentContent")==null?"":request.getParameter("assessmentContent");
		//String secretLevel = request.getParameter("secretLevel")==null?"":request.getParameter("secretLevel");//公开
		fileContent = upload();
		fileName = getUploadFileFileName();
		UserInfo currentUser = getLoginUser();
		if(currentUser.getTeamInfos().size()==0) {//未登录重新登录
			return LOGIN;
		}
		TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Date nowDate = new Date();
		Map<String,Object> condMap = new HashMap<String,Object>();
		Map<String,String> sortMap = new LinkedHashMap<String,String>();
		AssessmentReport assessmentReport = new AssessmentReport();
		//可以提取公共部分，但是会影响逻辑清晰度
		if("ABSTRACT".equals(reportType)) {//摘要      
			if("".equals(assessmentId)) {//后评估报告未添加
				//添加后评估报告基本信息
				assessmentReport.setAgentId(currentUser.getUserId());
				assessmentReport.setAgentName(currentUser.getName());
				assessmentReport.setAgentUnit(currentTeam.getId());
				assessmentReport.setAgentUnitName(currentTeam.getTeamName());
				assessmentReport.setAssessmentContent(assessmentContent);//概要
				assessmentReport.setAbstractFileName(fileName);
				assessmentReport.setAssessmentTitle(assessmentTitle);
				assessmentReport.setSecretLevel(secretLevel);//是否公开
				assessmentReport.setInsertDate(nowDate);
				assessmentReport.setReceiveUnit("U_3_3");//写死
				assessmentReport.setReceiveUnitName("上海市人民政府法制办公室");
				//将后评估报告关联申报项目
				//后续添加关联
				assessmentReport.setYear(df.format(nowDate));
				assessmentReportService.add(assessmentReport);
				assessmentId = assessmentReport.getAssessmentId();
				//添加后评估管理附件
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				assessmentId = assessmentReport.getAssessmentId();
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReport.setSecretLevel(secretLevel);
				assessmentReportAttach.setAttachType("ABSTRACT");
				assessmentReportAttachService.add(assessmentReportAttach);
			}else {//后评估报告已添加
				assessmentReport = assessmentReportService.findById(assessmentId);
				assessmentReport.setSecretLevel(secretLevel);
				assessmentReport.setAbstractFileName(fileName);
				assessmentReportService.saveOrUpdate(assessmentReport);
				condMap.clear();sortMap.clear();
				condMap.put("assessmentId", assessmentId);
				condMap.put("attachType", "ABSTRACT");
				List<AssessmentReportAttach> assessmentReportAttachs = assessmentReportAttachService.findByList(condMap, sortMap);
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttach = assessmentReportAttachs.get(0);
				}
				//添加后评估管理附件
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReportAttach.setAttachType("ABSTRACT");	
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttachService.saveOrUpdate(assessmentReportAttach);
				}else {
					assessmentReportAttachService.add(assessmentReportAttach);
				}
			}
		}else if("REPORT".equals(reportType)) {//报告
			if("".equals(assessmentId)) {//后评估报告未添加
				//添加后评估报告基本信息
				assessmentReport.setAgentId(currentUser.getUserId());
				assessmentReport.setAgentName(currentUser.getName());
				assessmentReport.setAgentUnit(currentTeam.getId());
				assessmentReport.setAgentUnitName(currentTeam.getTeamName());
				assessmentReport.setAssessmentContent(assessmentContent);//概要
				assessmentReport.setReportFileName(fileName);
				assessmentReport.setAssessmentTitle(assessmentTitle);
				assessmentReport.setSecretLevel(secretLevel);//是否公开
				assessmentReport.setInsertDate(nowDate);
				assessmentReport.setReceiveUnit("U_3_3");//写死
				assessmentReport.setReceiveUnitName("上海市人民政府法制办公室");
				//将后评估报告关联申报项目
				//后续添加关联
				assessmentReport.setYear(df.format(nowDate));
				assessmentReportService.add(assessmentReport);
				assessmentId = assessmentReport.getAssessmentId();
				//添加后评估管理附件
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				assessmentId = assessmentReport.getAssessmentId();
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReportAttach.setAttachType("REPORT");	
				assessmentReportAttachService.add(assessmentReportAttach);
			}else {//后评估报告已添加
				assessmentReport = assessmentReportService.findById(assessmentId);
				assessmentReport.setSecretLevel(secretLevel);
				assessmentReport.setReportFileName(fileName);
				assessmentReportService.saveOrUpdate(assessmentReport);
				condMap.clear();sortMap.clear();
				condMap.put("assessmentId", assessmentId);
				condMap.put("attachType", "REPORT");
				List<AssessmentReportAttach> assessmentReportAttachs = assessmentReportAttachService.findByList(condMap, sortMap);
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttach = assessmentReportAttachs.get(0);
				}
				//添加后评估管理附件
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReportAttach.setAttachType("REPORT");	
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttachService.saveOrUpdate(assessmentReportAttach);
				}else {
					assessmentReportAttachService.add(assessmentReportAttach);
				}
			}
		}else if("PRIVATE".equals(reportType)) {//不公开说明
			String privateInstructions = request.getParameter("privateInstructions")==null?"":request.getParameter("privateInstructions");
			if("".equals(assessmentId)) {//后评估报告未添加
				//添加后评估报告基本信息
				assessmentReport.setAgentId(currentUser.getUserId());
				assessmentReport.setAgentName(currentUser.getName());
				assessmentReport.setAgentUnit(currentTeam.getId());
				assessmentReport.setAgentUnitName(currentTeam.getTeamName());
				assessmentReport.setAssessmentContent(assessmentContent);//概要
				assessmentReport.setPrivateFileName(fileName);
				assessmentReport.setAssessmentTitle(assessmentTitle);
				assessmentReport.setSecretLevel(secretLevel);//是否公开
				assessmentReport.setMemo(memo);
				assessmentReport.setInsertDate(nowDate);
				assessmentReport.setReceiveUnit("U_3_3");//写死
				assessmentReport.setReceiveUnitName("上海市人民政府法制办公室");
				//将后评估报告关联申报项目
				//后续添加关联
				assessmentReport.setYear(df.format(nowDate));
				assessmentReportService.add(assessmentReport);
				assessmentId = assessmentReport.getAssessmentId();
				//添加后评估管理附件
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				assessmentId = assessmentReport.getAssessmentId();
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setAssessmentAttachInstraction(privateInstructions);//部分公开理由
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReportAttach.setAttachType("PRIVATE");	
				assessmentReportAttach.setAssessmentAttachInstraction(memo);
				assessmentReportAttachService.add(assessmentReportAttach);
			}else {//后评估报告已添加
				assessmentReport = assessmentReportService.findById(assessmentId);
				assessmentReport.setSecretLevel(secretLevel);
				assessmentReport.setMemo(memo);
				assessmentReport.setPrivateFileName(fileName);
				assessmentReportService.saveOrUpdate(assessmentReport);
				condMap.clear();sortMap.clear();
				condMap.put("assessmentId", assessmentId);
				condMap.put("attachType", "PRIVATE");
				List<AssessmentReportAttach> assessmentReportAttachs = assessmentReportAttachService.findByList(condMap, sortMap);
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttach = assessmentReportAttachs.get(0);
				}
				//添加后评估管理附件
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setAssessmentAttachInstraction(privateInstructions);//部分公开理由
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReportAttach.setAttachType("PRIVATE");	
				assessmentReportAttach.setAssessmentAttachInstraction(memo);
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttachService.saveOrUpdate(assessmentReportAttach);
				}else {
					assessmentReportAttachService.add(assessmentReportAttach);
				}
			}
		}else if("PROTECTED".equals(reportType)) {//部分公开
			if("".equals(assessmentId)) {//后评估报告未添加
				//添加后评估报告基本信息
				assessmentReport.setAgentId(currentUser.getUserId());
				assessmentReport.setAgentName(currentUser.getName());
				assessmentReport.setAgentUnit(currentTeam.getId());
				assessmentReport.setAgentUnitName(currentTeam.getTeamName());
				assessmentReport.setAssessmentContent(assessmentContent);//概要
				assessmentReport.setProtectFileName(fileName);
				assessmentReport.setAssessmentTitle(assessmentTitle);
				assessmentReport.setSecretLevel(secretLevel);//是否公开
				assessmentReport.setInsertDate(nowDate);
				assessmentReport.setReceiveUnit("U_3_3");//写死
				assessmentReport.setReceiveUnitName("上海市人民政府法制办公室");
				//将后评估报告关联申报项目
				//后续添加关联
				assessmentReport.setYear(df.format(nowDate));
				assessmentReportService.add(assessmentReport);
				assessmentId = assessmentReport.getAssessmentId();
				//添加后评估管理附件
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				assessmentId = assessmentReport.getAssessmentId();
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReportAttach.setAttachType("PROTECTED");	
				assessmentReportAttachService.add(assessmentReportAttach);
			}else {//后评估报告已添加
				assessmentReport = assessmentReportService.findById(assessmentId);
				assessmentReport.setSecretLevel(secretLevel);
				assessmentReport.setProtectFileName(fileName);
				assessmentReportService.saveOrUpdate(assessmentReport);
				condMap.clear();sortMap.clear();
				condMap.put("assessmentId", assessmentId);
				condMap.put("attachType", "PROTECTED");
				List<AssessmentReportAttach> assessmentReportAttachs = assessmentReportAttachService.findByList(condMap, sortMap);
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttach = assessmentReportAttachs.get(0);
				}
				//添加后评估管理附件
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReportAttach.setAttachType("PROTECTED");	
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttachService.saveOrUpdate(assessmentReportAttach);
				}else {
					assessmentReportAttachService.add(assessmentReportAttach);
				}
			}
		}else if("PART".equals(reportType)) {//部分公开理由
			if("".equals(assessmentId)) {//后评估报告未添加
				//添加后评估报告基本信息
				assessmentReport.setAgentId(currentUser.getUserId());
				assessmentReport.setAgentName(currentUser.getName());
				assessmentReport.setAgentUnit(currentTeam.getId());
				assessmentReport.setAgentUnitName(currentTeam.getTeamName());
				assessmentReport.setAssessmentContent(assessmentContent);//概要
				assessmentReport.setPartFileName(fileName);
				assessmentReport.setAssessmentTitle(assessmentTitle);
				assessmentReport.setSecretLevel(secretLevel);//是否公开
				assessmentReport.setInsertDate(nowDate);
				assessmentReport.setReceiveUnit("U_3_3");//写死
				assessmentReport.setReceiveUnitName("上海市人民政府法制办公室");
				//将后评估报告关联申报项目
				//后续添加关联
				assessmentReport.setYear(df.format(nowDate));
				assessmentReportService.add(assessmentReport);
				assessmentId = assessmentReport.getAssessmentId();
				//添加后评估管理附件
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				assessmentId = assessmentReport.getAssessmentId();
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReportAttach.setAttachType("PART");	
				assessmentReportAttachService.add(assessmentReportAttach);
			}else {//后评估报告已添加
				assessmentReport = assessmentReportService.findById(assessmentId);
				assessmentReport.setSecretLevel(secretLevel);
				assessmentReport.setPartFileName(fileName);
				assessmentReportService.saveOrUpdate(assessmentReport);
				condMap.clear();sortMap.clear();
				condMap.put("assessmentId", assessmentId);
				condMap.put("attachType", "PART");
				List<AssessmentReportAttach> assessmentReportAttachs = assessmentReportAttachService.findByList(condMap, sortMap);
				AssessmentReportAttach assessmentReportAttach = new AssessmentReportAttach();
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttach = assessmentReportAttachs.get(0);
				}
				//添加后评估管理附件
				assessmentReportAttach.setAgentId(currentUser.getUserId());
				assessmentReportAttach.setAgentName(currentUser.getName());
				assessmentReportAttach.setAssessmentAttach(fileContent);
				assessmentReportAttach.setAssessmentAttachName(fileName);
				assessmentReportAttach.setAssessmentId(assessmentId);
				assessmentReportAttach.setInsertDate(nowDate);
				assessmentReportAttach.setAttachType("PART");	
				if(assessmentReportAttachs.size()>0) {//修改
					assessmentReportAttachService.saveOrUpdate(assessmentReportAttach);
				}else {
					assessmentReportAttachService.add(assessmentReportAttach);
				}
			}
		}
		assessmentId = assessmentReport.getAssessmentId();
		return "assessmentReportAdd";
	}
	
	@Action(value = "assessment_report_detail", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_report_detail.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentReportDetail() {
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		UserInfo currentUser = getLoginUser();
		if(currentUser.getTeamInfos().size()==0) {
			return LOGIN;
		}
		TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
		List<String> disUnit = new ArrayList<String>();
		disUnit.add("U161");disUnit.add("U160");
		disUnit.add("U100");disUnit.add("U_3_1");
		disUnit.add("U_3_2");disUnit.add("U_3_4");
		disUnit.add("U_3_5");disUnit.add("U_3_6");
		disUnit.add("U_3_7");disUnit.add("U_1_0");
		AssessmentReport assessmentReport = assessmentReportService.findById(assessmentId);
		if(assessmentReport==null) {
			assessmentReport = new AssessmentReport();
		}else {
			if("U_3_3".equals(currentTeam.getId())) {
				assessmentReport.setReceive("Y");
				assessmentReport.setReceiveDate(new Date());
				assessmentReportService.saveOrUpdate(assessmentReport);
			}else if(disUnit.contains(currentTeam.getId())) {
				List<AssessmentReportDis> assessmentReportDis = assessmentReportDisService.findByAssessmentId(assessmentId, currentTeam.getId());
				if(assessmentReportDis.size()>0) {
					AssessmentReportDis assessmentReportDis2 = assessmentReportDis.get(0);
					if(assessmentReportDis2.getReceiveDate()==null) {
						assessmentReportDis2.setReceiveDate(new Date());
					}
					assessmentReportDisService.saveOrUpdate(assessmentReportDis2);
				}
			}
		}
		memo = assessmentReport.getMemo();
		assessmentContent = assessmentReport.getAssessmentContent();
		assessmentTitle = assessmentReport.getAssessmentTitle();
		secretLevel = assessmentReport.getSecretLevel();
		Boolean isLfc = (Boolean)session.getAttribute("isLfc");
		if(!isLfc&&!"U_3_3".equals(currentTeam.getId())) {
			request.setAttribute("others", true);
		}else {
			request.setAttribute("others", false);
		}
		request.setAttribute("assessmentReport", assessmentReport);
		request.setAttribute("currentTeam", currentTeam);
		return SUCCESS;
	}
	
	@Action(value="assessmentUpReport")
	public void assessmentUpReport() throws IOException {
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		PrintWriter out = response.getWriter();
		if(!"".equals(assessmentId)) {
			AssessmentReport assessmentReport = assessmentReportService.findById(assessmentId);
			if(assessmentReport==null) {
				out.write("false");
			}else {
				assessmentReport.setReceive("N");
				assessmentReportService.saveOrUpdate(assessmentReport);
				out.write("true");
			}
		}else {
			out.write("false");
		}
		out.flush();
	}
	
	@Action(value="assessmentReportBack")
	public void assessmentBack() throws IOException {
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		PrintWriter out = response.getWriter();
		if(!"".equals(assessmentId)) {
			AssessmentReport assessmentReport = assessmentReportService.findById(assessmentId);
			if(assessmentReport==null) {
				out.write("false");
			}else {
				assessmentReport.setReceive(null);
				assessmentReportService.saveOrUpdate(assessmentReport);
				out.write("true");
			}
		}else {
			out.write("false");
		}
		out.flush();
	}
	
	@Action(value = "assessmentAttachLoad", results = { @Result(name = SUCCESS, type = "stream", params = { "contentType", "application/octet-stream;charset=utf-8", "inputName",
			"inputStream", "contentDisposition", "attachment;filename=\"${fileName}\"", "bufferSize", "4096" }) })
	public String attachFileLoad() throws UnsupportedEncodingException {
		String assessmentId = request.getParameter("assessmentId") == null ? "" : request.getParameter("assessmentId");
		String reportType = request.getParameter("reportType")==null?"":request.getParameter("reportType");
		Map<String,Object> condMap = new HashMap<String,Object>();
		condMap.put("assessmentId", assessmentId);
		condMap.put("attachType", reportType);
		List<AssessmentReportAttach> assessmentReportAttachs = assessmentReportAttachService.findByList(condMap, null);
		if(assessmentReportAttachs.size()>0) {
			fileContent = assessmentReportAttachs.get(0).getAssessmentAttach();
			if(fileContent==null) {
				String err = "看到此文字，说明附件未上传。";
				fileContent = err.getBytes();
			}
			fileName = assessmentReportAttachs.get(0).getAssessmentAttachName();
			fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		}
		System.out.println("文件下载的名称：" + fileName);
		return SUCCESS;
	}
	
	//ASSESSMENT START================================================================================
	//市法制办接收后评估报告
	@Action(value = "assessment_list", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_list.jsp"),
			@Result(name = "upReport", location = "assessment_up_list.do?flag=${flag}",type="redirectAction"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})  //
	public String assessment_list() throws NumberFormatException, FzbDaoException {
		String receive = request.getParameter("receive")==null?"":request.getParameter("receive");//是否接收
		String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
		if("EDIT".equals(flag)) {
			return "upReport";
		}
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}
		UserInfo currentUser = getLoginUser();
		if(currentUser==null) {//登录信息为空，重新登录
			return LOGIN;
		}else {//已接收、未接收列表
			queryType = request.getParameter("queryType")==null?"":request.getParameter("queryType");
			String queryValue = request.getParameter("queryValue")==null?"":request.getParameter("queryValue");
			TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
			Map<String,Object> condMap = new HashMap<String,Object>();
			Map<String,String> sortMap = new LinkedHashMap<String,String>();
			if("assessmentTitle".equals(queryType)) {
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}else if("agentUnitName".equals(queryType)){
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}
			condMap.put("receive", receive);//Y/N
			condMap.put("receiveUnit", currentTeam.getId());//U0
			sortMap.put("insertDate", "DESC");
			Page<Assessment> assessments = assessmentService.findByPage(condMap, sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			request.setAttribute("queryType", queryType);
			request.setAttribute("queryValue", queryValue);
			request.setAttribute("retPage", assessments);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("receive", receive);
			request.setAttribute("currentTeam", currentTeam);
			return SUCCESS;
		}
		
	}
	//单位编辑上报后评估报告
	@Action(value = "assessment_up_list", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_up_list.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentUpList() throws NumberFormatException, FzbDaoException {
		String flag = request.getParameter("flag")==null?"":request.getParameter("flag");//编辑、上报
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}
		UserInfo currentUser = getLoginUser();
		if(currentUser==null) {//登录信息为空，重新登录
			return LOGIN;
		}else {//已接收、未接收列表
			queryType = request.getParameter("queryType")==null?"":request.getParameter("queryType");
			String queryValue = request.getParameter("queryValue")==null?"":request.getParameter("queryValue");
			TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
			Map<String,Object> condMap = new HashMap<String,Object>();
			Map<String,String> sortMap = new LinkedHashMap<String,String>();
			if("assessmentTitle".equals(queryType)) {
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}else if("assessmentUnitName".equals(queryType)){
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}
			if("EDIT".equals(flag)) {
				condMap.put("receive"+"IsNull", "");//NULL、not NULL
			}else {
				condMap.put("receive"+"IsNotNull", "");//NULL、not NULL
			}
			condMap.put("assessmentUnit", currentTeam.getId());//U0
			sortMap.put("insertDate", "DESC");
			Page<Assessment> assessments = assessmentService.findByPage(condMap, sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			request.setAttribute("queryType", queryType);
			request.setAttribute("queryValue", queryValue);
			request.setAttribute("retPage", assessments);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("flag", flag);
			request.setAttribute("currentTeam", currentTeam);
			return SUCCESS;
		}
		
	}
	
	@Action(value = "assessment_detail", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_detail.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentDetail() {
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		UserInfo currentUser = getLoginUser();
		if(currentUser.getTeamInfos().size()==0) {
			return LOGIN;
		}
		TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
		List<String> disUnit = new ArrayList<String>();
		disUnit.add("U161");disUnit.add("U160");
		disUnit.add("U100");disUnit.add("U_3_1");
		disUnit.add("U_3_2");disUnit.add("U_3_4");
		disUnit.add("U_3_5");disUnit.add("U_3_6");
		disUnit.add("U_3_7");disUnit.add("U_1_0");
		Assessment assessment = assessmentService.findById(assessmentId);
		if(assessment==null) {
			assessment = new Assessment();
		}else {
			if("U_3_3".equals(currentTeam.getId())) {
				assessment.setReceive("Y");
				assessment.setReceiveDate(new Date());
				assessmentService.saveOrUpdate(assessment);
			}else if(disUnit.contains(currentTeam.getId())) {
				List<AssessmentDis> assessmentDis = assessmentDisService.findByAssessmentId(assessmentId, currentTeam.getId());
				if(assessmentDis.size()>0) {
					AssessmentDis assessmentDis2 = assessmentDis.get(0);
					if(assessmentDis2.getReceiveDate()==null) {
						assessmentDis2.setReceiveDate(new Date());
					}
					assessmentDisService.saveOrUpdate(assessmentDis2);
				}
			}
		}
		memo = assessment.getAssessmentMemo();
		assessmentContent = assessment.getAssessmentContent();
		assessmentTitle = assessment.getAssessmentTitle();
		Boolean isLfc = (Boolean)session.getAttribute("isLfc");
		if(!isLfc&&!"U_3_3".equals(currentTeam.getId())) {
			request.setAttribute("others", true);
		}else {
			request.setAttribute("others", false);
		}
		request.setAttribute("assessment", assessment);
		request.setAttribute("currentTeam", currentTeam);
		return SUCCESS;
	}
	
	@Action(value = "assessment_add", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_add.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentAdd() {
		assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");	
		String op = request.getParameter("op")==null?"":request.getParameter("op");
		UserInfo currentUser = getLoginUser();
		if(currentUser.getTeamInfos().size()==0) {
			return LOGIN;
		}
		TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Date nowDate = new Date();
		Assessment assessment = new Assessment();
		if("save".equals(op)) {//
			//添加后评估报告基本信息
			assessment.setAssessmentAgentName(currentUser.getName());
			assessment.setAssessmentUnit(currentTeam.getId());
			assessment.setAssessmentUnitName(currentTeam.getTeamName());
			assessment.setAssessmentContent(assessmentContent);//概要
			assessment.setAssessmentTitle(assessmentTitle);
			assessment.setInsertDate(nowDate);
			assessment.setAssessmentMemo(memo);
			assessment.setReceiveUnit("U_3_3");//写死
			assessment.setReceiveUnitName("上海市人民政府法制办公室");
			//将后评估报告关联申报项目
			//后续添加关联
			assessment.setYear(df.format(nowDate));
			assessmentService.add(assessment);
		}else if("modify".equals(op)){//
			assessment = assessmentService.findById(assessmentId);
			assessment.setAssessmentAgentName(currentUser.getName());
			assessment.setAssessmentUnit(currentTeam.getId());
			assessment.setAssessmentUnitName(currentTeam.getTeamName());
			assessment.setAssessmentContent(assessmentContent);//概要
			assessment.setAssessmentTitle(assessmentTitle);
			assessment.setAssessmentMemo(memo);
			assessment.setInsertDate(nowDate);
			assessment.setReceiveUnit("U_3_3");//写死
			assessment.setReceiveUnitName("上海市人民政府法制办公室");
			//将后评估报告关联申报项目
			//后续添加关联
			assessment.setYear(df.format(nowDate));
			assessmentService.saveOrUpdate(assessment);
		}else {
			assessment = assessmentService.findById(assessmentId);
			if(assessment==null) {
				assessment = new Assessment();
			}
		}
		memo = assessment.getAssessmentMemo();
		assessmentContent = assessment.getAssessmentContent();
		assessmentTitle = assessment.getAssessmentTitle();
		request.setAttribute("assessment", assessment);
		return SUCCESS;
	}
	@Action(value="assessmentUp")
	public void assessmentUp() throws IOException {
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		PrintWriter out = response.getWriter();
		if(!"".equals(assessmentId)) {
			Assessment assessment = assessmentService.findById(assessmentId);
			if(assessment==null) {
				out.write("false");
			}else {
				assessment.setReceive("N");
				assessmentService.saveOrUpdate(assessment);
				TeamInfo receiveTeam = teamInfoService.findTeamInfoByTeamId("MODULE_LEGISLATE", "U_3_1");
				List<AssessmentDis> assessmentDiss = assessmentDisService.findByAssessmentId(assessmentId, "U_3_1");
				if(assessmentDiss.size()==0) {
					AssessmentDis assessmentDis = new AssessmentDis();
					assessmentDis.setAssessmentId(assessmentId);
					assessmentDis.setAssessmentTitle(assessment.getAssessmentTitle());
					assessmentDis.setAssessmentDisUnit("U_3_3");
					assessmentDis.setAssessmentDisUnitName("上海市人民政府法制办公室-监督处");
					assessmentDis.setInsertDate(new Date());
					assessmentDis.setAssessmentReceiveUnit("U_3_1");
					assessmentDis.setAssessmentReceiveUnitName(receiveTeam.getTeamName());
					assessmentDisService.add(assessmentDis);	
				}
				out.write("true");
			}
		}else {
			out.write("false");
		}
		out.flush();
	}
	
	//单位编辑上报后评估报告
	@Action(value = "assessment_dis_list", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_dis_list.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentDistributeList() throws NumberFormatException, FzbDaoException {
		String pageSize = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		if (null == pageSize || "".equals(pageSize)) {
			pageSize = "10";
		}
		if (null == pageNo || "".equals(pageNo)) {
			pageNo = "1";
		}
		UserInfo currentUser = getLoginUser();
		if(currentUser==null) {//登录信息为空，重新登录
			return LOGIN;
		}else {//已接收、未接收列表
			queryType = request.getParameter("queryType")==null?"":request.getParameter("queryType");
			String queryValue = request.getParameter("queryValue")==null?"":request.getParameter("queryValue");
			TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
			Map<String,Object> condMap = new HashMap<String,Object>();
			Map<String,String> sortMap = new LinkedHashMap<String,String>();
			List<String> assessmentIdList = new ArrayList<String>(); 
			condMap.put("assessmentReceiveUnit", currentTeam.getId());
			sortMap.put("receiveDate", "DESC");
			List<AssessmentDis> assessmentDises = assessmentDisService.findByList(condMap, sortMap);
			for(int i=0;i<assessmentDises.size();i++) {
				AssessmentDis assessmentDis = assessmentDises.get(i);
				String assessmentId = assessmentDis.getAssessmentId();
				assessmentIdList.add(assessmentId);
			}
			condMap.clear();sortMap.clear();
			if("assessmentTitle".equals(queryType)) {
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}else if("assessmentUnitName".equals(queryType)){
				if(!"".equals(queryValue)&&queryValue!=null) {
					condMap.put(queryType+"Like", queryValue);
				}
			}
			condMap.put("assessmentId"+"List", assessmentIdList);
			condMap.put("receive" + "IsNotNull", "");
			sortMap.put("receiveDate", "DESC");
			Page<Assessment> assessmentReports = assessmentService.findByPage(condMap, sortMap, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			request.setAttribute("queryType", queryType);
			request.setAttribute("queryValue", queryValue);
			request.setAttribute("retPage", assessmentReports);
			request.setAttribute("pageNo", pageNo);
			return SUCCESS;
		}
		
	}
	@Action(value="assessmentBack")
	public void assessmentNoPass() throws IOException {
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		PrintWriter out = response.getWriter();
		if(!"".equals(assessmentId)) {
			Assessment assessment = assessmentService.findById(assessmentId);
			if(assessment==null) {
				out.write("false");
			}else {
				assessment.setReceive(null);
				assessmentService.saveOrUpdate(assessment);
				out.write("true");
			}
		}else {
			out.write("false");
		}
		out.flush();
	}
	
	@Action(value = "assessmentAttachDownload", results = { @Result(name = SUCCESS, type = "stream", params = { "contentType", "application/octet-stream;charset=utf-8", "inputName",
			"inputStream", "contentDisposition", "attachment;filename=\"${fileName}\"", "bufferSize", "4096" }) })
	public String assessmentAttachDownload() throws UnsupportedEncodingException {
		String assessmentId = request.getParameter("assessmentId") == null ? "" : request.getParameter("assessmentId");
		//String reportType = request.getParameter("reportType")==null?"":request.getParameter("reportType");
		if(!"".equals(assessmentId)) {//!"".equals(reportType)&&
			Map<String,Object> condMap = new HashMap<String,Object>();
			condMap.put("assessmentId", assessmentId);
			List<AssessmentAttach> assessmentAttachs = assessmentAttachService.findByList(condMap, null);
			if(assessmentAttachs.size()>0) {
				fileContent = assessmentAttachs.get(0).getAssessmentAttachFile();
				if(fileContent==null) {
					String err = "看到此文字，说明附件未上传。";
					fileContent = err.getBytes();
				}
				fileName = assessmentAttachs.get(0).getAssessmentAttachName();
				fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			}
		}
		System.out.println("文件下载的名称：" + fileName);
		return SUCCESS;
	}
	@SuppressWarnings({ "finally", "resource" })
	public InputStream getInputStream() throws UnsupportedEncodingException{
		ByteArrayInputStream bais = null;
		if(null != fileContent||inputStream!=null){
			try {
				if(fileContent!=null) {
					bais = new ByteArrayInputStream(fileContent);
					return bais;
				}else {
					return inputStream;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			FileInputStream fis = null;
			fileName = "2019年后评估项目申报表18-10-15.doc";
			fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			String filePath = "D:/fzb_file/download/2019年后评估项目申报表18-10-15.doc";
			try {
				fis = new FileInputStream(filePath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				return fis;
			}
		}
		return null;
	}
	
	@Action(value = "assessment_attach_add", results = {
			@Result(name = "assessmentAdd", location = "assessment_add.do?assessmentId=${assessmentId}",type="redirectAction"),//
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentAttachAdd() {
		//String reportType = request.getParameter("reportType")==null?"":request.getParameter("reportType");
		assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		//String assessmentTitle = request.getParameter("assessmentTitle")==null?"":request.getParameter("assessmentTitle");
	//	String assessmentContent = request.getParameter("assessmentContent")==null?"":request.getParameter("assessmentContent");
		//String secretLevel = request.getParameter("secretLevel")==null?"":request.getParameter("secretLevel");//公开
		fileContent = upload();
		fileName = getUploadFileFileName();
		UserInfo currentUser = getLoginUser();
		if(currentUser.getTeamInfos().size()==0) {//未登录重新登录
			return LOGIN;
		}
		TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Date nowDate = new Date();
		Map<String,Object> condMap = new HashMap<String,Object>();
		Map<String,String> sortMap = new LinkedHashMap<String,String>();
		Assessment assessment = new Assessment();
		if(!"".equals(assessmentId)) {
			assessment = assessmentService.findById(assessmentId);		
		}
		assessment.setAssessmentTitle(assessmentTitle);
		assessment.setAssessmentAgentName(currentUser.getName());
		assessment.setAssessmentAgentPhone(currentUser.getPhone());
		assessment.setAssessmentFileName(fileName);
		assessment.setAssessmentUnit(currentTeam.getId());
		assessment.setAssessmentUnitName(currentTeam.getTeamName());
		assessment.setInsertDate(new Date());
		if("".equals(assessmentId)) {
			assessment.setYear(df.format(new Date()));
			assessment.setReceiveUnit("U_3_3");
			assessment.setReceiveUnitName("上海市人民政府法制办公室");
			assessmentService.add(assessment);
			assessmentId = assessment.getAssessmentId();
			condMap.put("assessmentId", assessmentId);
			List<AssessmentAttach> assessmentAttachs = assessmentAttachService.findByList(condMap, sortMap);
			AssessmentAttach assessmentAttach = new AssessmentAttach();
			if(assessmentAttachs.size()>0) {
				assessmentAttach = assessmentAttachs.get(0);
			}
			assessmentAttach.setAssessmentAttachFile(fileContent);
			assessmentAttach.setAssessmentAttachName(fileName);
			assessmentAttach.setAssessmentId(assessmentId);
			assessmentAttach.setInsertDate(new Date());
			if(assessmentAttachs.size()>0) {
				assessmentAttachService.saveOrUpdate(assessmentAttach);
			}else {
				assessmentAttachService.add(assessmentAttach);
			}
		}else {
			assessmentService.saveOrUpdate(assessment);
			assessmentId = assessment.getAssessmentId();
			condMap.put("assessmentId", assessmentId);
			List<AssessmentAttach> assessmentAttachs = assessmentAttachService.findByList(condMap, sortMap);
			AssessmentAttach assessmentAttach = new AssessmentAttach();
			if(assessmentAttachs.size()>0) {
				assessmentAttach = assessmentAttachs.get(0);
			}
			assessmentAttach.setAssessmentAttachFile(fileContent);
			assessmentAttach.setAssessmentAttachName(fileName);
			assessmentAttach.setAssessmentId(assessmentId);
			assessmentAttach.setInsertDate(new Date());
			if(assessmentAttachs.size()>0) {
				assessmentAttachService.saveOrUpdate(assessmentAttach);
			}else {
				assessmentAttachService.add(assessmentAttach);
			}
		}
		return "assessmentAdd";
	}
	
	//单位编辑上报后评估报告
	@Action(value = "assessment_dis_unit", results = {
			@Result(name = SUCCESS, location = "/legislate/assessment/assessment_dis_unit.jsp"),
			@Result(name = LOGIN, location = "/execlaw/logout.jsp")})
	public String assessmentDisUnitList() throws NumberFormatException, FzbDaoException {
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		UserInfo currentUser = getLoginUser();
		if(currentUser==null) {//登录信息为空，重新登录
			return LOGIN;
		}else {//已接收、未接收列表
			TeamInfo currentTeam = currentUser.getTeamInfos().get(0);
			if(!"U_3_3".equals(currentTeam.getId())) {
				return LOGIN;
			}
			List<String> listUnit = new ArrayList<String>();
			List<AssessmentDis> assessmentDiss = assessmentDisService.findByAssessmentId(assessmentId, null);
			for(int i=0;i<assessmentDiss.size();i++) {
				AssessmentDis assessmentDis = (AssessmentDis)assessmentDiss.get(i);
				String unitId = assessmentDis.getAssessmentReceiveUnit();
				listUnit.add(unitId);
			}
			List<MOR> mors = teamInfoService.findMorTypeList("MODULE_LEGISLATE","市法制办处室");
			for(int i=0;i<mors.size();i++) {
				MOR mor = mors.get(i);
				String orgCid = mor.getTeamCid();
				if("U_3_3".equals(orgCid)) {
					mors.remove(mor);
				}
			}
			List<AssessmentDis> assessmentDises = assessmentDisService.findByAssessmentId(assessmentId, null);
			List<String> receives = new ArrayList<String>();
			for(int i=0;i<assessmentDises.size();i++) {
				AssessmentDis assessmentDis = assessmentDises.get(i);
				if(assessmentDis.getReceiveDate()!=null) {
					receives.add(assessmentDis.getAssessmentReceiveUnit());
				}
			}
			request.setAttribute("receives", receives);
			request.setAttribute("mors", mors);
			request.setAttribute("listUnit", listUnit);
			request.setAttribute("assessmentId", assessmentId);
			return SUCCESS;
		}
		
	}
	
	@Action("assessment_dis")//分办
	public void assessmentDis() throws IOException
	{
		String assessmentId = request.getParameter("assessmentId")==null?"":request.getParameter("assessmentId");
		String[] unitIds = request.getParameterValues("unitIds")==null?new String[100]:request.getParameterValues("unitIds");
		UserInfo currentUser = getLoginUser();
		TeamInfo currentTeam = new TeamInfo();
		if(currentUser.getTeamInfos().size()>0) {
			currentTeam = currentUser.getTeamInfos().get(0);
		}
		PrintWriter out = response.getWriter();
		if("U_3_3".equals(currentTeam.getId())) {
			Assessment assessment = assessmentService.findById(assessmentId); 
			boolean flag = true;
			String retMsg = "";
			for(int i=0;i<unitIds.length;i++) {
				String unitId = unitIds[i];
				TeamInfo receiveTeam = teamInfoService.findTeamInfoByTeamId("MODULE_LEGISLATE", unitId);
				List<AssessmentDis> assessmentDiss = assessmentDisService.findByAssessmentId(assessmentId, unitId);
				if(assessmentDiss.size()==0) {
					AssessmentDis assessmentDis = new AssessmentDis();
					assessmentDis.setAssessmentId(assessmentId);
					assessmentDis.setAssessmentTitle(assessment.getAssessmentTitle());
					assessmentDis.setAssessmentDisUnit(currentTeam.getId());
					assessmentDis.setAssessmentDisUnitName(currentTeam.getTeamName());
					assessmentDis.setInsertDate(new Date());
					assessmentDis.setAssessmentReceiveUnit(unitId);
					assessmentDis.setAssessmentReceiveUnitName(receiveTeam.getTeamName());
					assessmentDisService.add(assessmentDis);	
				}else {
					retMsg = receiveTeam.getTeamName()+" ";
					flag = false;
				}
			}
			if(flag) {
				out.write("分送成功。");
			}else {
				out.write(retMsg+"分送失败。请确认是否已经分送。");
			}
		}else {
			out.write("分送失败。");
		}
		out.flush();
	}

	/*
	 * 导出Word
	 */
	@Action(value = "exportWord", results = { @Result(name = SUCCESS, type = "stream", params = {"contentType", "application/octet-stream;charset=utf-8","inputName", "inputStream",
			"contentDisposition", "attachment;filename=\"${docName}\"", "bufferSize", "4096" }) })
	public String exportWord() throws Exception {
		String assessmentTitle1 = request.getParameter("assessmentTitle1")==null?"":request.getParameter("assessmentTitle1");
		String assessmentTitle2 = request.getParameter("assessmentTitle2")==null?"":request.getParameter("assessmentTitle2");
		String assessmentTitle3 = request.getParameter("assessmentTitle3")==null?"":request.getParameter("assessmentTitle3");
		String assessmentTitle4 = request.getParameter("assessmentTitle4")==null?"":request.getParameter("assessmentTitle4");
		String assessmentTitle5 = request.getParameter("assessmentTitle5")==null?"":request.getParameter("assessmentTitle5");
		String select1 = request.getParameter("select1")==null?"":request.getParameter("select1");
		String select2 = request.getParameter("select2")==null?"":request.getParameter("select2");
		String select3 = request.getParameter("select3")==null?"":request.getParameter("select3");
		String select4 = request.getParameter("select4")==null?"":request.getParameter("select4");
		String select5 = request.getParameter("select5")==null?"":request.getParameter("select5");
		String others1 = request.getParameter("others1")==null?"":request.getParameter("others1");
		String others2 = request.getParameter("others2")==null?"":request.getParameter("others2");
		String others3 = request.getParameter("others3")==null?"":request.getParameter("others3");
		String others4 = request.getParameter("others4")==null?"":request.getParameter("others4");
		String others5 = request.getParameter("others5")==null?"":request.getParameter("others5");
		UserInfo currentUser = getLoginUser();
		TeamInfo currentTeam = new TeamInfo();
		if(currentUser.getTeamInfos()!=null) {
			if(currentUser.getTeamInfos().size()>0) {
				currentTeam = currentUser.getTeamInfos().get(0);
			}
		}
		String agentName = currentUser.getName()==null?"":currentUser.getName();
		String agentPhone = currentUser.getPhone()==null?"":currentUser.getPhone();
		String unitName = currentTeam.getTeamName()==null?"":currentTeam.getTeamName();
		String newRealPath=this.servletContext.getRealPath("/legislate/template/assessment.doc");
		Map<String, String> paramMap = new HashMap<String, String>();
		String year = new SimpleDateFormat("yyyy").format(new Date());
		year = (Integer.parseInt(year) + 1 ) + "";
		paramMap.put("${agentName}", agentName);
		paramMap.put("${agentPhone}", agentPhone);
		paramMap.put("${unitName}", unitName);
		paramMap.put("${year}", year);
		paramMap.put("${assessmentTitle1}", assessmentTitle1);
		paramMap.put("${select1}", select1);
		paramMap.put("${others1}", others1);
		paramMap.put("${assessmentTitle2}", assessmentTitle2);
		paramMap.put("${assessmentTitle3}", assessmentTitle3);
		paramMap.put("${assessmentTitle4}", assessmentTitle4);
		paramMap.put("${assessmentTitle5}", assessmentTitle5);
		paramMap.put("${select2}", select2);
		paramMap.put("${select3}", select3);
		paramMap.put("${select4}", select4);
		paramMap.put("${select5}", select5);
		paramMap.put("${others2}", others2);
		paramMap.put("${others3}", others3);
		paramMap.put("${others4}", others4);
		paramMap.put("${others5}", others5);
		docName = java.net.URLEncoder.encode(new SimpleDateFormat("yyyy").format(new Date())+"年后评估项目申报表18-10-15.doc", "UTF-8");
		inputStream = assessmentService.exportWord(paramMap,null, newRealPath);	
		return SUCCESS;
	}
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAssessmentTitle() {
		return assessmentTitle;
	}

	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}

	public String getAssessmentContent() {
		return assessmentContent;
	}

	public void setAssessmentContent(String assessmentContent) {
		this.assessmentContent = assessmentContent;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSecretLevel() {
		return secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}
}
