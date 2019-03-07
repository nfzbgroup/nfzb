package com.wonders.fzb.legislate.web;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.wonders.fzb.base.actions.FileManageAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislate.LegislateConst;
import com.wonders.fzb.legislate.beans.Draft;
import com.wonders.fzb.legislate.beans.DraftTask;
import com.wonders.fzb.legislate.beans.Fbd;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.beans.Model;
import com.wonders.fzb.legislate.beans.ModelFileRecord;
import com.wonders.fzb.legislate.beans.Plan;
import com.wonders.fzb.legislate.beans.PlanTask;
import com.wonders.fzb.legislate.services.OrgUtils;
import com.wonders.fzb.legislate.services.FbdService;
import com.wonders.fzb.legislate.services.FileRecordService;
import com.wonders.fzb.legislate.services.ModelService;
import com.wonders.fzb.legislate.services.PlanService;
import com.wonders.fzb.legislate.services.PlanSummaryService;
import com.wonders.fzb.legislate.services.PlanTaskService;
import com.wonders.fzb.framework.services.TeamInfoService;

import net.sf.json.JSONObject;

@Namespace("/legislate")
@Controller
@Scope("prototype")
public class PlanAction extends FileManageAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	PlanTaskService planTaskService;
	@Autowired
	PlanService planService;
	@Autowired
	FbdService fbdService;
	@Autowired
	PlanSummaryService planSummaryService;
	@Autowired
	FileRecordService fileRecordService;
	@Autowired
	TeamInfoService teamInfoService;
	@Autowired
	ModelService modelService;
	
	private int pageSize = 10;
	
	private int pageNo = 1;
	
	private String state;
	
	private String planId;
	
	private Page pageModel;
	
	private String op="add";
	private Plan plan;
	
	private String planName;
	private String planType;
	private String projectType;
	private String reason;
	private String progress;
	private String remark;
	private String[] planIds;
	private String instructions;
	private PlanTask planTask;
	private String taskId;
	private String creatorName;
	private Date startTime;
	private Date endTime;
	private String isdise;
	private PlanTask planTaskBylfjhsh;
	//判断已完成  后页面的显示值
	private String finishstate;
	//立法计划汇总查询条件参数
	private String search;
	private String year;
	private String teamId;
	
	private Date processStartTime;
	private Date processEndTime;
	private String status;
	private String teamName;
	
	protected List<Model> modellist;
	protected List<ModelFileRecord> planFiles;
	protected List<ModelFileRecord> otherPlanFiles;
	
	protected TeamInfo getTeamInfo(UserInfo user){
		if(user.getTeamInfos() != null && !user.getTeamInfos().isEmpty()){
			return user.getTeamInfos().get(0);
		}
		return null;
	}
	
	
	/**
	 * 查询
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_serach_lfjhsh",results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhsh.jsp")})
	public String plan_serach_lfjhsh() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		if("summary".equals(state)){
			sortMap.put("createTime", CommonConst.ORDER_DESC);
			condMap.put("status", "summary");
			condMap.put("planNameLike", planName);
			condMap.put("teamNameLike", teamName);
			if(!"请选择".equals(planType)){
				condMap.put("planTypeLike", planType);
			}
			condMap.put("teamId", teamId);
			pageModel = planService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if("audit".equals(state)){
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("taskType", "process");
			condMap.put("plan.planNameLike", planName);
			if(!"请选择".equals(planType)){
				condMap.put("plan.planTypeLike", planType);
			}	
			condMap.put("status", "0");
			condMap.put("plan.creatorNameLike", creatorName);
			condMap.put("plan.teamId", teamId);
			//大于等于
			if(startTime!=null){
				condMap.put("plan.createTimeGe",startTime);
			}
			//小于等于
			if(endTime!=null){
				condMap.put("plan.createTimeLe",endTime);
			}
			pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if("unaudit".equals(state)){
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("taskType", "claim");
			condMap.put("status", "0");
			condMap.put("plan.planNameLike", planName);
			if(!"请选择".equals(planType)){
				condMap.put("plan.planTypeLike", planType);
			}
			condMap.put("plan.creatorNameLike", creatorName);
			condMap.put("teamId", teamId);
			//大于等于
			if(startTime!=null){
				condMap.put("plan.createTimeGe",startTime);
			}
			//小于等于
			if(endTime!=null){
				condMap.put("plan.createTimeLe",endTime);
			}
			pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if("done".equals(state)){
			sortMap.put("createTime", CommonConst.ORDER_DESC);
			condMap.put("status", "process");
			condMap.put("planNameLike", planName);
			condMap.put("teamNameLike", teamName);
			if(!"请选择".equals(planType)){
				condMap.put("planTypeLike", planType);
			}
			if(!"请选择".equals(projectType)){
				condMap.put("projectType", projectType);
			}
			condMap.put("teamId", teamId);
			finishstate="finish";
			pageModel = planService.findByPage(condMap, sortMap, pageNo, pageSize);
		}
		return SUCCESS;
	}
	
	/**
	 * 立法汇总
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "dosummary")
	public String dosummary() throws FzbDaoException {
		int code = 200;
		String message = "汇总成功";
		try {
			UserInfo user = super.getLoginUser();
			if(user == null){
				return null;
			}
			TeamInfo team = getTeamInfo(user);
			String str = request.getParameter("taskIds");
			planIds = str.split(",");
			for (int i = 0; i < planIds.length; i++) {
				planTask  = planTaskService.findById(planIds[i]);
				plan = planTask.getPlan();
				plan.setUpdateName(user.getName());
				plan.setUpdateId(user.getUserId());
				plan.setUpdateTime(new Date());
				plan.setTeamId(team.getId());
				plan.setTeamName(team.getTeamName());
				plan.setStatus("summary");
				planService.updateBySummary(planTask);
				
			}
		} catch (Exception e) {
			code = 400;
			message = "汇总失败";
			e.printStackTrace();
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("message", message);
		try {
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(result).toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 审核立法
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "shenhe_gxd", results = {@Result(name = SUCCESS, location = "/legislate/plan/shenhe_gxd.jsp")})
	public String shenhe_gxd() throws FzbDaoException {
		if("toaudit".equals(op)){
			planTask = planTaskService.findById(taskId);
			return SUCCESS;
		}else if("doaudit".equals(op)){
		int code = 200;
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String message = "审核成功";
		try {
			PlanTask planTask = planTaskService.findById(taskId);
			planTask.setTeamName(team.getTeamName());
			planTask.setTeamId(team.getId());
			planTask.setUserId(user.getUserId());
			planTask.setUserName(user.getName());
			planTask.setStatus(LegislateConst.TASK_STATUS_DONE);
			planTask.setProcessTime(new Date());
			planTask.setInstructions("已审核");
			plan = planTask.getPlan();
			plan.setRemark(remark);
			plan.setStatus(LegislateConst.STATUS_PROCESS);
			plan.setUpdateId(user.getUserId());
			plan.setUpdateName(user.getName());
			plan.setUpdateTime(new Date());
			plan.setTeamId(team.getId());
			plan.setTeamName(team.getTeamName());
			planTask.setPlan(plan);
			planTask.setTeamId(team.getId());
			planService.updateByaudit(planTask);
		} catch (Exception e) {
			code = 400;
			message = "审核失败";
			e.printStackTrace();
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("message", message);
		try {
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(result).toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		return null;
		
	}
	
	/**
	 * 未审核
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_unaudit_list", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhsh.jsp")})
	public String plan_unaudit_list() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		sortMap.put("taskTime", CommonConst.ORDER_DESC);
		condMap.put("taskType", "claim");
		condMap.put("status", "0");
		condMap.put("teamId", team.getId());
		pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		state = "unaudit";
		return SUCCESS;
	}
	
	/**
	 * 以汇总
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_summary_list", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhsh.jsp")})
	public String plan_summary_list() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		sortMap.put("createTime", CommonConst.ORDER_DESC);
		condMap.put("status", "summary");
		condMap.put("teamId", team.getId());
		pageModel = planService.findByPage(condMap, sortMap, pageNo, pageSize);
		state = "summary";
		return SUCCESS;
	}
	
	@Action(value = "planSummary_list", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhsh.jsp"),
			@Result(name = "list", location = "/legislate/plan/summary_list.jsp")})
	public String planSummary_list() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		sortMap.put("year", CommonConst.ORDER_DESC);
		if("query".equals(search)){
			condMap.put("year", year);
			if(!"请选择".equals(teamName)){
				condMap.put("teamName", teamName);
			}
			condMap.put("year", year);
			//大于等于
			if(startTime!=null){
				condMap.put("lastTimeTimeGe",processStartTime);
			}
			//小于等于
			if(endTime!=null){
				condMap.put("lastTimeTimeLe",processEndTime);
			}
		}
		Boolean isZhc = (Boolean) session.getAttribute("isZhc");
		if(!isZhc){
			condMap.put("teamId", team.getId());
		}
		pageModel = planSummaryService.findByPage(condMap, sortMap, pageNo, pageSize);
		state = "summary_list";
		if(!isZhc){
			return SUCCESS;
		}else{
			return "list";
		}
	}
	
	@Action(value = "planSummary_items", results = {@Result(name = SUCCESS, location = "/legislate/plan/summary_items.jsp")})
	public String planSummary_items() throws FzbDaoException {
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		String summaryId = request.getParameter("summaryId");
		condMap.put("planSummary.summaryId", summaryId);
		pageModel = planService.findByPage(condMap, sortMap, pageNo, pageSize);
		return SUCCESS;
	}

	
	
	/**
	 * 审核中
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_audit_list", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhsh.jsp")})
	public String plan_audit_list() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		sortMap.put("taskTime", CommonConst.ORDER_DESC);
		condMap.put("taskType", "process");
		condMap.put("status", "0");
		condMap.put("plan.teamId", team.getId());
		pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		state = "audit";
		return SUCCESS;
	}
	/**
	 * 已完成
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_done_list", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhsh.jsp")})
	public String plan_done_list() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
//		Map<String, Object> condMap = new HashMap<String, Object>();
//		Map<String, String> sortMap = new HashMap<String, String>();
//		sortMap.put("createTime", CommonConst.ORDER_DESC);
//		List<String> list = new ArrayList<String>();
//		list.add("summary");
//		list.add("process");
//		condMap.put("statusList", list);
//		condMap.put("teamId", team.getId());
//		pageModel = planService.findByPage(condMap, sortMap, pageNo, pageSize);
		
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		sortMap.put("taskTime", CommonConst.ORDER_DESC);
		condMap.put("taskType", "process");
		condMap.put("status", "1");
		condMap.put("plan.teamId", team.getId());
		condMap.put("plan.status","process");
		pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		
		state = "done";
		return SUCCESS;
	}
	
	/**
	 * 立法计划认领
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_claim")
	public String plan_claim() throws FzbDaoException{
		
		int code = 200;
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String message = "认领成功";
		try {
			PlanTask planTask = planTaskService.findById(taskId);
			planTask.setTeamName(team.getTeamName());
			planTask.setTeamId(team.getId());
			planTask.setUserId(user.getUserId());
			planTask.setUserName(user.getName());
			planTask.setStatus(LegislateConst.TASK_STATUS_DONE);
			planTask.setProcessTime(new Date());
			planTask.setInstructions("已认领");
			plan = planTask.getPlan();
			plan.setStatus(LegislateConst.STATUS_CLAIM);
			plan.setUpdateId(user.getUserId());
			plan.setUpdateName(user.getName());
			plan.setUpdateTime(new Date());
			plan.setTeamId(team.getId());
			plan.setTeamName(team.getTeamName());
			planTask.setPlan(plan);
			planTask.setTeamId(team.getId());
			planService.updateByClaim(planTask);
		} catch (Exception e) {
			code = 400;
			message = "认领失败";
			e.printStackTrace();
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("message", message);
		try {
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(result).toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 立法计划汇总
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "lfjhhz", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhhz.jsp")})
	public String lfjhhz() throws FzbDaoException {
		return SUCCESS;
	}
	
	
	/**
	 * 立法计划上报查看
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "lfjhlb", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhlb_view.jsp")})
	public String lfjhlb() throws FzbDaoException {
		plan = planService.findById(planId);
		if(plan.getFbd() != null){
			isdise = "yes";
		}else{
			isdise = "no";
		}
		Map<String, Object> condMap =  new HashMap<String, Object>();
		Map<String, String> sortMap =  new HashMap<String, String>();
		sortMap.put("taskTime",  CommonConst.ORDER_ASC);
		condMap.put("plan.planId", plan.getPlanId());
		condMap.put("status", "1");
		pageModel = planTaskService.findByPage(condMap, sortMap , pageNo, pageSize);
		
		condMap.clear();
		condMap.put("plan.planId", plan.getPlanId());
		System.out.println(plan.getPlanId());
		condMap.put("taskType", "claim");
		if( planTaskService.findByList(condMap, sortMap).size()!=0){
			planTaskBylfjhsh =planTaskService.findByList(condMap, sortMap).get(0);
		}
		sortMap.clear();
		condMap.clear();
		condMap.put("outId", plan.getPlanId());
		List pageFileRecord=fileRecordService.findByList(condMap, sortMap);
		request.setAttribute("pageFileRecord", pageFileRecord);
		return SUCCESS;
	}
	
	/**
	 * 立法计划上报修改和新增
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "savePlan", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhlb_add.jsp")})
	public String savePlan() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		
		String op=request.getParameter("op");
		if(StringUtils.hasText(planId)){
			this.plan=this.planService.findById(planId);
			this.op="update";
		}
		String lexSuperior = request.getParameter("lexSuperior"); 
		String contacts = request.getParameter("contacts"); 
	    String telephone = request.getParameter("telephone"); 
	    String sendYear = request.getParameter("sendYear"); 
	    String sendMonth = request.getParameter("sendMonth"); 
		if("add".equals(op)){
			String userId = user.getUserId();
			plan = new Plan();
			plan.setPlanName(planName);
			plan.setPlanType(planType);
			plan.setProjectType(projectType);
			plan.setCreateTime(new Date());
			plan.setCreatorId(userId);
			plan.setUpdateId(userId);
			
			plan.setCreatorName(user.getName());
			plan.setUpdateTime(new Date());
			plan.setUpdateName(userId);
			plan.setStatus(LegislateConst.STATUS_INIT);
			plan.setTeamId(team == null ? "" : team.getId());
			plan.setTeamName(team == null ? "" :  team.getTeamName());
			
			plan.setReason(reason);
			plan.setProgress(progress);
			plan.setRemark(remark);
			plan.setContacts(contacts);
			plan.setTelephone(telephone);
			plan.setLexSuperior(lexSuperior);
			plan.setSendYear(sendYear);
			plan.setSendMonth(sendMonth);
			planService.add(plan);
			planId = plan.getPlanId();
			this.op = "update";
		}else if("update".equals(op)){
			String userId = user.getUserId();
	        plan.setPlanName(planName);
	        plan.setPlanType(planType);
			plan.setProjectType(projectType);
			plan.setUpdateId(userId);
			plan.setUpdateTime(new Date());
			plan.setUpdateName(user.getName());
			plan.setReason(reason);
			plan.setProgress(progress);
			plan.setRemark(remark);
			plan.setContacts(contacts);
			plan.setTelephone(telephone);
			plan.setLexSuperior(lexSuperior);
			plan.setSendYear(sendYear);
			plan.setSendMonth(sendMonth);
			planService.saveOrUpdate(plan);
		}
		if(this.plan!=null){
			this.planType = this.plan.getPlanType();
			this.projectType = this.plan.getProjectType();
			sendYear = this.plan.getSendYear();
			sendMonth = this.plan.getSendMonth();
		}
		String fromTemplate = "Y";
		if(plan!=null&&!"".equals(plan.getPlanId())&&!"null".equals(plan.getPlanId())){
			Map<String, Object> condMap = new LinkedHashMap<String, Object>();
			Map<String, String> sortMap = new LinkedHashMap<String, String>();
			condMap.put("outId", plan.getPlanId());
			condMap.put("bizType", "立法计划");
			List<FileRecord> fileRecordList = fileRecordService.findByList(condMap, sortMap);
			if(fileRecordList!=null&&fileRecordList.size()>0){
				fromTemplate = "N";
			}
		}
		request.setAttribute("planType", planType);
		request.setAttribute("projectType", projectType);
		request.setAttribute("sendYear", sendYear);
		request.setAttribute("sendMonth", sendMonth);
		request.setAttribute("fromTemplate",fromTemplate);
		
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		condMap.put("activityType", LegislateConst.ACTIVITY_TYPE_Plan_ADD);
		sortMap.put("createTime",  CommonConst.ORDER_ASC);
		modellist = modelService.findByList(condMap , sortMap );
		
		if(StringUtils.hasText(planId)){
			condMap.clear();
			sortMap.clear();
			loadDraftFile();
			if(OrgUtils.isFzb(teamInfoService, team.getId())){
				for (ModelFileRecord fileRecord : this.planFiles){
					if(!LegislateConst.Plan_Add_Model_key_01.equals(fileRecord.getBizType())){
						fileRecord.setUnique(0);
					}
				}
			}
			for (ModelFileRecord fileRecord : this.planFiles){
				if("正式项目".equals(projectType))
					fileRecord.setUnique(1);
			}
		}
		request.setAttribute("planFiles", this.planFiles);
		request.setAttribute("otherPlanFiles", this.otherPlanFiles);
		return SUCCESS;
	}
	
	private void loadDraftFile() {
		this.planFiles = this.fileRecordService.findByList(this.planId, LegislateConst.ACTIVITY_TYPE_Plan_ADD, null);
		this.otherPlanFiles = this.fileRecordService.findByList(this.planId, LegislateConst.ACTIVITY_TYPE_Plan_ADD, LegislateConst.Plan_Add_Model_key_09);
	}
	
	@Action(value = "uploadPlanFile",results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhlb_add.jsp"),
			@Result(name = INPUT, location = "/legislate/plan/upload_file.jsp")})
	public String uploadPlanFile() throws FzbDaoException{
		byte[] content = upload();
		String bizType = request.getParameter("bizType");
		String activityType = request.getParameter("activityType");
		String savePlan = request.getParameter("savePlan");
		planId = request.getParameter("planId");
		if(content != null){
			UserInfo user = super.getLoginUser();
			if(user == null){
				return null;
			}
			FileRecord info = new FileRecord();
			Map<String, Object> condMap = new HashMap<String, Object>();
			condMap.put("outId", planId);
			condMap.put("activityType",activityType);
			condMap.put("bizType", bizType);
			
			List<FileRecord> fileRecord = fileRecordService.findByList(condMap , null);
			if(!fileRecord.isEmpty() && !LegislateConst.Plan_Add_Model_key_09.equals(bizType)){
				info = fileRecord.get(0);
				info = fileRecordService.findById(info.getFileRecordId());
			}
			
			String userId = user.getUserId();
			
			info.setCreatorId(userId);
			info.setCreatorName(user.getName());
			info.setFileContent(content);
			info.setFileName(getUploadFileFileName());
			info.setBizType(bizType);
			info.setOutId(planId);
			info.setCreateTime(new Date());
			info.setActivityType(activityType);
			
						
			Plan plan = this.planService.findById(planId);
			if(plan != null){
				info.setRemarks(plan.getPlanName());
			}
			
			fileRecordService.add(info);
		}
		if("0".equals(savePlan)){
			if(content != null){
				request.setAttribute("message", "上传成功！");
			}
			return INPUT;
		}
		return savePlan();
	}
	
	@Action(value = "planAttach", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhlb_attach.jsp")})
	public String planAttach() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		if(StringUtils.hasText(planId)){
			this.plan=this.planService.findById(planId);
		}
		op = request.getParameter("op");
		if("upload".equals(op)){
			byte[] content = upload();
			String bizType = request.getParameter("bizType");
			String activityType = request.getParameter("activityType");
			String remarks = request.getParameter("remarks");
			if(content != null){
				FileRecord info = new FileRecord();
				String userId = user.getUserId();
				info.setCreatorId(userId);
				info.setFileContent(content);
				info.setFileName(getUploadFileFileName());
				info.setBizType(bizType);
				info.setOutId(plan.getPlanId());
				info.setCreateTime(new Date());
				info.setActivityType(activityType);
				info.setRemarks(remarks);
				fileRecordService.add(info);
				op = "";
			}
		}
		if("del".equals(op)){
			String fileRecordId = request.getParameter("fileRecordId");
			FileRecord info = fileRecordService.findById(fileRecordId);
			if(info!=null){
				fileRecordService.delete(info);
			}
			op = "";
		}
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		condMap.put("outId", plan.getPlanId());
		sortMap.put("createTime", "ASC");
		pageModel = fileRecordService.findByPage(condMap, sortMap, pageNo, pageSize);
		return SUCCESS;
	}
	
	@Action(value = "planAttachDownlad", results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhlb_attach.jsp")})
	public String planAttachDownlad(HttpServletRequest req, HttpServletResponse res) throws FzbDaoException {
		String fileRecordId = request.getParameter("fileRecordId");
		FileRecord info = fileRecordService.findById(fileRecordId);
		if(info!=null && info.getFileContent()!=null){
			byte[] file = info.getFileContent();
			long fileLength = file.length;
			String fileName = info.getFileName();
			String contentType = "application/octet-stream;charset=utf-8";
			OutputStream os = null;
			try {
				res.reset();
				res.setContentType(contentType);
				res.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));
				res.setHeader("Content-Length", String.valueOf(fileLength));
				os = new BufferedOutputStream(res.getOutputStream());
				os.write(file);
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (os != null)
					try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 未上报
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_init_list", results = {
			@Result(name = SUCCESS, location = "/legislate/plan/lfjhlb.jsp")
		})
	public String plan_init_list() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		String op = request.getParameter("op");
		String planId = request.getParameter("planId");
		if("del".equals(op)){
			if(planId!=null&&!"".equals(planId)&&!"null".equals(planId)){
				Plan delPlan = planService.findById(planId);
				if(delPlan!=null)
					planService.delete(delPlan);
			}
		}
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("creatorId", user.getUserId());
		condMap.put("status", LegislateConst.STATUS_INIT);
		pageModel = planService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = LegislateConst.STATUS_INIT;
 		return SUCCESS;
	}
	
	/**
	 * 已上报
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_send_list", results = {
			@Result(name = SUCCESS, location = "/legislate/plan/lfjhlb.jsp")
		})
	public String plan_send_list() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("taskTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("status", "1");
		condMap.put("taskType", LegislateConst.STATUS_SEND);
		condMap.put("plan.creatorId", user.getUserId());
		pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = LegislateConst.STATUS_SEND;
 		return SUCCESS;
	}
	
	/**
	 * 查询立法计划上报列表
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_serach_lfjhlb",results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhlb.jsp")})
	public String plan_serach_lfjhlb() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		if(LegislateConst.STATUS_INIT.equals(state)){
			//未上报查询
			sortMap.put("createTime", CommonConst.ORDER_DESC);
			condMap.put("creatorId", user.getUserId());
			condMap.put("status", LegislateConst.STATUS_INIT);
			if(planName!=null)
				condMap.put("planNameLike", planName);
			if(creatorName!=null)
				condMap.put("creatorNameLike", creatorName);
			//发起时间
			//大于等于
			if(startTime!=null){
				condMap.put("createTimeGe",startTime);
			}
			//小于等于
			if(endTime!=null){
				condMap.put("createTimeLe",endTime);
			}
			pageModel = planService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if(LegislateConst.STATUS_SEND.equals(state)){
			//已上报  查询
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("status", "1");
			condMap.put("taskType", LegislateConst.STATUS_SEND);
			condMap.put("plan.creatorId", user.getUserId());
			if(planName!=null)
				condMap.put("plan.planNameLike", planName);
			if(status!=null&&!"请选择".equals(status)){
				condMap.put("plan.status", status);
			}
			if(creatorName!=null)
				condMap.put("plan.creatorNameLike", creatorName);
			//大于等于
			if(startTime!=null){
				condMap.put("plan.createTimeGe",startTime);
			}
			//小于等于
			if(endTime!=null){
				condMap.put("plan.createTimeLe",endTime);
			}
			
			//处理时间
			//大于等于
			if(processStartTime!=null){
				condMap.put("processTimeGe",processStartTime);
			}
			//小于等于
			if(processEndTime!=null){
				condMap.put("processTimeLe",processEndTime);
			}
			pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}
		request.setAttribute("tp", "search");
		return SUCCESS;
	}
	
	/**
	 * 上报检查是否有重复
	 * @return
	 * @throws FzbDaoException
	 * @throws IOException 
	 */
	@Action(value = "plan_check")
	public void plan_check() throws FzbDaoException, IOException{
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		UserInfo user = super.getLoginUser();
		TeamInfo team = getTeamInfo(user);
		if(StringUtils.hasText(planId)){
			this.plan=this.planService.findById(planId);
		}
		condMap.put("planName", this.plan.getPlanName());
		condMap.put("creatorId", user.getUserId());
		condMap.put("statusNotEq", "init");
		List<Plan> list = planService.findByList(condMap, sortMap);
		Map<String,Object> result = new HashMap<String, Object>();
		if(list!=null&&list.size()>0) {
			result.put("duplicate", 1);
		}else {
			result.put("duplicate", 0);
		}
		PrintWriter out = response.getWriter();
		out.print(JSONObject.fromObject(result).toString());
		out.flush();
	}
	
	/**
	 * 上报操作
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_send")
	public String plan_send() throws FzbDaoException{
		int code = 200;
		String message = "请上传相关资料";
		String needAttach = "";
		if(StringUtils.hasText(planId)){
			this.plan=this.planService.findById(planId);
		}
		if("正式项目".equals(this.plan.getProjectType())){
			needAttach = LegislateConst.Plan_Add_Model_key_01+" "+LegislateConst.Plan_Add_Model_key_02+" "+LegislateConst.Plan_Add_Model_key_03;
		}else {
			needAttach = LegislateConst.Plan_Add_Model_key_01;
		}
		try {
			UserInfo user = super.getLoginUser();
			TeamInfo team = getTeamInfo(user);
			if(user == null){
				message = "登录已超时！";
				code = 400;
			}else{
				/*planService.updateBySend(planId);*/
				loadDraftFile();
				//上传文件的类型
				Set<String> bizTypes = new HashSet<String>();
				for (ModelFileRecord fileRecord : planFiles) {
					if(fileRecord.getFileName() != null){
						bizTypes.add(fileRecord.getBizType());
						needAttach = ""+needAttach.replace(fileRecord.getBizType(), "");
					}
				}
				message = message+":"+needAttach;
				//排除其他材料
				bizTypes.remove(LegislateConst.Plan_Add_Model_key_09);
				String content = request.getParameter("content");
				//对于该单位填报的相类似的规章、计划系统予以提示，并需要填写说明理由后方可上报
				if(content!=null&&!"".equals(content)&&!"null".equals(content))
					plan.setReasonDesc(content);
				if(!"正式项目".equals(this.plan.getProjectType())){
					bizTypes.remove(LegislateConst.Plan_Add_Model_key_02);
					bizTypes.remove(LegislateConst.Plan_Add_Model_key_03);
					if(bizTypes.size() >=1){
						String teamId = team.getId();
						plan.setStatus(LegislateConst.STATUS_SEND);
						plan.setUpdateId(user.getUserId());
						plan.setUpdateName(user.getName());
						plan.setTeamName(team.getTeamName());
						plan.setTeamId(teamId);
						planService.updateBySend(plan);
						message = "报送成功";
						code = 200;
					}
				}else{
					if(bizTypes.size() >=3){
						String teamId = team.getId();
						plan.setStatus(LegislateConst.STATUS_SEND);
						plan.setUpdateId(user.getUserId());
						plan.setUpdateName(user.getName());
						plan.setTeamName(team.getTeamName());
						plan.setTeamId(teamId);
						if(plan.getRemark()!=null)
							plan.setRemark("拟于"+plan.getSendYear()+"年"+plan.getSendMonth()+"月前报送规章草案\r\n"+plan.getRemark());
						else
							plan.setRemark("拟于"+plan.getSendYear()+"年"+plan.getSendMonth()+"月前报送规章草案");
						planService.updateBySend(plan);
						message = "报送成功";
						code = 200;
					}
				}
			}
		} catch (Exception e) {
			code = 400;
			message = "报送失败";
			e.printStackTrace();
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("message", message);
		try {
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(result).toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 立法计划分办列表
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_serach_lfjhfb",results = {@Result(name = SUCCESS, location = "/legislate/plan/lfjhfb.jsp")})
	public String draft_serach_gzcafblb() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		if("unreceive".equals(state)){
			//未上报查询
			sortMap.put("taskTime",  CommonConst.ORDER_DESC);
			condMap.put("status", "0");
			condMap.put("taskType", "receive");
			condMap.put("teamId", teamId);
			condMap.put("plan.planNameLike", planName);
			condMap.put("plan.creatorNameLike", creatorName);
			//大于等于
			if(startTime!=null){
				condMap.put("plan.createTimeGe",startTime);
			}
			//小于等于
			if(endTime!=null){
				condMap.put("plan.createTimeLe",endTime);
			}
			pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if("undise".equals(state)){
			//已上报  查询
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("status", "0");
			condMap.put("taskType", "dise");
			condMap.put("teamId", teamId);
			condMap.put("plan.planNameLike", planName);
			condMap.put("plan.creatorNameLike", creatorName);
			//大于等于
			if(startTime!=null){
				condMap.put("plan.createTimeGe",startTime);
			}
			//小于等于
			if(endTime!=null){
				condMap.put("plan.createTimeLe",endTime);
			}
			pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if("dise".equals(state)){
			//已上报  查询
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("status", "1");
			condMap.put("taskType", "dise");
			condMap.put("teamId", teamId);
			condMap.put("plan.planNameLike", planName);
			condMap.put("plan.creatorNameLike", creatorName);
			//大于等于
			if(startTime!=null){
				condMap.put("plan.createTimeGe",startTime);
			}
			//小于等于
			if(endTime!=null){
				condMap.put("plan.createTimeLe",endTime);
			}
			if(!"请选择".equals(status)){
				condMap.put("plan.status", status);
			}
			//处理时间
			//大于等于
			if(processStartTime!=null){
				condMap.put("processTimeGe",processStartTime);
			}
			//小于等于
			if(processEndTime!=null){
				condMap.put("processTimeLe",processEndTime);
			}
			pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 已分办
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_dise_list", results = {
			@Result(name = SUCCESS, location = "/legislate/plan/lfjhfb.jsp"),
		})
	public String plan_dise_list() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("taskTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("status", "1");
		condMap.put("taskType", "dise");
		condMap.put("teamId", teamId);
 		pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "dise";
		return SUCCESS;
	}
	
	/**
	 * 未接收
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_unreceive_list", results = {
		@Result(name = SUCCESS, location = "/legislate/plan/lfjhfb.jsp"),
	})
	public String plan_unreceive_list() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("taskTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("status", "0");
		condMap.put("taskType", "receive");
		condMap.put("teamId", teamId);
		pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "unreceive";
		return SUCCESS;
	}
	/**
	 * 已接收
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_receive_list", results = {
			@Result(name = SUCCESS, location = "/legislate/plan/lfjhfb.jsp"),
		})
	public String plan_receive_list() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("taskTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("status", "1");
		condMap.put("taskType", "receive");
		condMap.put("teamId", teamId);
		pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "receive";
		return SUCCESS;
	}
	/**
	 * 待分办
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_undise_list", results = {
			@Result(name = SUCCESS, location = "/legislate/plan/lfjhfb.jsp"),
		})
	public String plan_undise_list() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("taskTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("status", "0");
		condMap.put("taskType", "dise");
		condMap.put("teamId", teamId);
		pageModel = planTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "undise";
		return SUCCESS;
	}
	
	
	/**
	 * 立法计划接收
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_receive")
	public String plan_receive() throws FzbDaoException{
		int code = 200;
		String message = "接收成功";
		try {
			UserInfo user = super.getLoginUser();
			if(user == null){
				message = "登录已超时！";
				code = 400;
			}else{
				TeamInfo team = getTeamInfo(user);
				String taskId = request.getParameter("taskId");
				PlanTask planTask = planTaskService.findById(taskId);
				planTask.setTeamName(team.getTeamName());
				planTask.setUserId(user.getUserId());
				planTask.setUserName(user.getName());
				planTask.setStatus(LegislateConst.TASK_STATUS_DONE);
				planTask.setProcessTime(new Date());
				planTask.setInstructions("已接收");
				planTask.setTeamName(team.getTeamName());
				plan = planTask.getPlan();
				plan.setStatus(LegislateConst.STATUS_RECEIVE);
				plan.setUpdateId(user.getUserId());
				plan.setUpdateName(user.getName());
				plan.setUpdateTime(new Date());
				plan.setTeamId(team.getId());
				plan.setTeamName(team.getTeamName());
				planTask.setPlan(plan);
				planService.updateByReceive(planTask);
			}
		} catch (Exception e) {
			code = 400;
			message = "接收失败";
			e.printStackTrace();
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("message", message);
		try {
			PrintWriter out = response.getWriter();
			out.print(JSONObject.fromObject(result).toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 立法计划分办
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "plan_dise",results={@Result(name = SUCCESS, location = "/legislate/plan/fbd.jsp")})
	public String plan_dise() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		if("doDise".equals(op)){
			int code = 200;
			String message = "分办成功";
			try {
				Fbd fbd= fbdService.findById(request.getParameter("fbdId"));
 				String userId = user.getUserId();
				fbd.setInstructions(instructions);
				String year=request.getParameter("year");
				String month=request.getParameter("month");
				if(month.length()!=2){
					month = "0" + month;
				}
				String day=request.getParameter("day");
				if(day.length()!=2){
					day = "0" + day;
				}
				String processTime=year+month+day;
				fbd.setProcessTime(new SimpleDateFormat("yyyyMMdd").parse(processTime));
				fbd.setReceiveTime(new Date());
				fbd.setUserId(userId);
				fbd.setUserName(user.getName());
				fbd.setTeamId(teamId);
				fbd.setTeamName(team.getTeamName());
				String taskId = request.getParameter("taskId");
				String pteamId = request.getParameter("teamId");
				
				PlanTask planTask = planTaskService.findById(taskId);
				planTask.setTeamName(team.getTeamName());
				planTask.setTeamId(teamId);
				planTask.setUserId(user.getUserId());
				planTask.setUserName(user.getName());
				planTask.setStatus(LegislateConst.TASK_STATUS_DONE);
				planTask.setProcessTime(new Date());
				planTask.setInstructions("已分办");
				planTask.setTeamName(team.getTeamName());
				plan = planTask.getPlan();
				plan.setStatus(LegislateConst.STATUS_DISE);
				plan.setUpdateId(user.getUserId());
				plan.setUpdateName(user.getName());
				plan.setUpdateTime(new Date());
				plan.setTeamId(team.getId());
				plan.setTeamName(team.getTeamName());
				plan.setFbd(fbd);
				planTask.setPlan(plan);
				planService.updateByDise(planTask,fbd,pteamId);
			} catch (Exception e) {
				code = 400;
				message = "分办失败";
				e.printStackTrace();
			}
			Map<String,Object> result = new HashMap<String, Object>();
			result.put("code", code);
			result.put("message", message);
			try {
				PrintWriter out = response.getWriter();
				out.print(JSONObject.fromObject(result).toString());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			this.planTask = planTaskService.findById(taskId);
			if(planTask.getPlan().getFbd() == null){
				Map<String, String> sortMap = new LinkedHashMap<String, String>();
				sortMap.put("seq",  CommonConst.ORDER_DESC);
				Map<String, Object> condMap = new LinkedHashMap<String, Object>();
				int year =  Calendar.getInstance().get(Calendar.YEAR);
				condMap.put("year", String.valueOf(year));
				Page pages=fbdService.findByPage(condMap, sortMap, pageNo, pageSize);
				int seq = 1;
				if(pages.getTotalSize() != 0){
				   Fbd fbd=(Fbd)pages.getResult().get(0);
				   seq = Integer.parseInt(fbd.getSeq()) + 1;
				}
				Fbd fbd = new Fbd();
				fbd.setFbdId("DF"+year+"规"+String.format("%05d",seq));
				fbd.setSeq(String.format("%05d",seq));
				fbd.setYear(String.valueOf(year));
				fbd.setTeamId(planTask.getPlan().getTeamId());
				fbdService.add(fbd);
				planTask.getPlan().setFbd(fbd);
				planService.update(plan); 
			}
			return SUCCESS;
		}
	}
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public Page getPageModel() {
		return pageModel;
	}
	public void setPageModel(Page pageModel) {
		this.pageModel = pageModel;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String[] getPlanIds() {
		return planIds;
	}

	public void setPlanIds(String[] planIds) {
		this.planIds = planIds;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public PlanTask getPlanTask() {
		return planTask;
	}
	public void setPlanTask(PlanTask planTask) {
		this.planTask = planTask;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getIsdise() {
		return isdise;
	}
	public void setIsdise(String isdise) {
		this.isdise = isdise;
	}
	public PlanTask getPlanTaskBylfjhsh() {
		return planTaskBylfjhsh;
	}
	public void setPlanTaskBylfjhsh(PlanTask planTaskBylfjhsh) {
		this.planTaskBylfjhsh = planTaskBylfjhsh;
	}
	public String getFinishstate() {
		return finishstate;
	}
	public void setFinishstate(String finishstate) {
		this.finishstate = finishstate;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public Date getProcessStartTime() {
		return processStartTime;
	}
	public void setProcessStartTime(Date processStartTime) {
		this.processStartTime = processStartTime;
	}
	public Date getProcessEndTime() {
		return processEndTime;
	}
	public void setProcessEndTime(Date processEndTime) {
		this.processEndTime = processEndTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
