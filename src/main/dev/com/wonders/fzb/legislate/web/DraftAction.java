package com.wonders.fzb.legislate.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

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
import com.wonders.fzb.legislate.services.DraftService;
import com.wonders.fzb.legislate.services.DraftTaskService;
import com.wonders.fzb.legislate.services.FbdService;
import com.wonders.fzb.legislate.services.FileRecordService;
import com.wonders.fzb.legislate.services.ModelService;

@Namespace("/legislate")
@Controller
@Scope("prototype")
public class DraftAction extends FileManageAction {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;
	
	@Autowired
	DraftTaskService draftTaskService;
	@Autowired
	DraftService draftService;
	@Autowired
	ModelService modelService;
	@Autowired
	FileRecordService fileRecordService;
	@Autowired
	FbdService fbdService;
	
	protected int pageSize = 10;

	
	/**
	 * 也面路径
	 */
	protected String uuu;
	
	protected int pageNo = 1;

	protected Page pageModel;
	
	protected List<Model> modellist;
	
	protected List<FileRecord> draftFiles = new ArrayList<FileRecord>();
	protected List<FileRecord> otherFiles = new ArrayList<FileRecord>();
	protected List<FileRecord> currFiles = new ArrayList<FileRecord>();
	
	protected String fileRecordId;
	
	protected Draft draft;

	protected String draftId;
	
	protected String draftName;

	protected String instructions;
	
	protected String op = "add";
	
	protected String fileName;
	private List<FileRecord> FileRecordlist;
	protected byte[] fileContent;
	
	protected String taskId;
	
	protected String teamId;
	private String bizType;
	protected DraftTask draftTask;
	protected DraftTask draftTaskBygzcaqcbl;
	
	protected String state;
	
	protected String creatorName;
	
	protected String isdise;
	
	protected Date createstartTime;
	
	protected Date createendTime;
	
	private Date processStartTime;
	private Date processEndTime;
	private String status;
	
	protected TeamInfo getTeamInfo(UserInfo user){
		if(user.getTeamInfos() != null && !user.getTeamInfos().isEmpty()){
			return user.getTeamInfos().get(0);
		}
		return null;
	}
	
	protected Map<String,String> allowFileType = new LinkedHashMap<String, String>();
	
	public void loadFiles(String outId ,String bizType,String activityType,List<FileRecord> files){
		Map<String, String> sortMap = new HashMap<String, String>();
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		files = new ArrayList<FileRecord>();
		condMap.put("outId",  outId);
		//condMap.put("bizType",  bizType);
		condMap.put("activityType",  activityType);
		draftFiles = fileRecordService.findByList(condMap, sortMap);
		if (draftFiles.isEmpty()){
			if(!LegislateConst.ACTIVITY_TYPE_Draft_ADD.equals(activityType)) {
				//lisu  修改为  草案附件
				//原来 -loadFiles(outId,bizType,LegislateConst.ACTIVITY_TYPE_Draft_ADD,files);
				//loadFiles(outId,bizType,LegislateConst.ACTIVITY_TYPE_Draft_ADD,this.draftFiles);
			}
			allowFileType.put(bizType,bizType);
		}else{
			files.addAll(draftFiles);
		}
	}
	
	public void loadDraftFile(){
		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_01, LegislateConst.ACTIVITY_TYPE_Draft_ADD,null);
		allowFileType.put(LegislateConst.Draft_Add_Model_key_07, LegislateConst.Draft_Add_Model_key_07);
	}
	
	
	/**
	 * 查询规章草案起草列表
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_serach_gzcaqclb",results = {@Result(name = SUCCESS, location = "/legislate/draft/gzcaqclb.jsp")})
	public String draft_serach_gzcaqclb() throws FzbDaoException {
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
			condMap.put("draftNameLike", draftName);
			condMap.put("creatorNameLike", creatorName);
			if(!("".equals(createstartTime)||createstartTime == null)){
				condMap.put("createTimeGe", createstartTime);
			}
			if(!("".equals(createendTime)||createendTime == null)){
				condMap.put("createTimeLe", createendTime);
			}
			pageModel = draftService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if(LegislateConst.STATUS_SEND.equals(state)){
			//已上报  查询
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("status", "1");
			condMap.put("taskType", LegislateConst.STATUS_SEND);
			condMap.put("draft.creatorId", user.getUserId());
			condMap.put("draft.draftNameLike", draftName);
			condMap.put("draft.creatorNameLike", creatorName);
			if(!"请选择".equals(status)){
				condMap.put("draft.status", status);
			}
			//发起时间
			if(!("".equals(createstartTime)||createstartTime == null)){
				condMap.put("draft.createTimeGe", createstartTime);
			}
			if(!("".equals(createendTime)||createendTime == null)){
				condMap.put("draft.createTimeLe", createendTime);
			}
			//处理时间
			if(!("".equals(processStartTime)||processStartTime == null)){
				condMap.put("processTimeGe", processStartTime);
			}
			if(!("".equals(processEndTime)||processEndTime == null)){
				condMap.put("processTimeLe", processEndTime);
			}
			pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}
		return SUCCESS;
	}

	/**
	 * 查询规章草案分办列表
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_serach_gzcafblb",results = {@Result(name = SUCCESS, location = "/legislate/draft/gzcafblb.jsp")})
	public String draft_serach_gzcafblb() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		if(!("".equals(createstartTime)||createstartTime == null)){
			condMap.put("draft.createTimeGe", createstartTime);
		}
		if(!("".equals(createendTime)||createendTime == null)){
			condMap.put("draft.createTimeLe", createendTime);
		}
		if("unreceive".equals(state)){
			//未上报查询
			sortMap.put("taskTime",  CommonConst.ORDER_DESC);
			condMap.put("status", "0");
			condMap.put("taskType", "receive");
			condMap.put("teamId", teamId);
			condMap.put("draft.draftNameLike", draftName);
			condMap.put("draft.creatorNameLike", creatorName);
			pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if("undise".equals(state)){
			//已上报  查询
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("status", "0");
			condMap.put("taskType", "dise");
			condMap.put("teamId", teamId);
			condMap.put("draft.draftNameLike", draftName);
			condMap.put("draft.creatorNameLike", creatorName);
			pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if("dise".equals(state)){
			//已上报  查询
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("status", "1");
			condMap.put("taskType", "dise");
			condMap.put("teamId", teamId);
			condMap.put("draft.draftNameLike", draftName);
			condMap.put("draft.creatorNameLike", creatorName);
			if(!"请选择".equals(status)){
				condMap.put("draft.status", status);
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
			pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}
		return SUCCESS;
	}
	

	/**
	 * 查询规章草案办理列表
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_serach_gzcabllb",results = {@Result(name = SUCCESS, location = "/legislate/draft/gzcabllb.jsp")})
	public String draft_serach_gzcabllb() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		//发起时间
		if(!("".equals(createstartTime)||createstartTime == null)){
			condMap.put("draft.createTimeGe", createstartTime);
		}
		if(!("".equals(createendTime)||createendTime == null)){
			condMap.put("draft.createTimeLe", createendTime);
		}
		
		//处理时间
		if(!("".equals(processStartTime)||processStartTime == null)){
			condMap.put("draft.processTimeGe", processStartTime);
		}
		if(!("".equals(processEndTime)||processEndTime == null)){
			condMap.put("draft.processTimeLe", processEndTime);
		}
		if("unprocess".equals(state)){
			//未上报查询
			sortMap.put("taskTime",  CommonConst.ORDER_DESC);
			condMap.put("status", "0");
			condMap.put("taskType", "claim");
			condMap.put("teamId", teamId);
			condMap.put("draft.draftNameLike", draftName);
			condMap.put("draft.creatorNameLike", creatorName);
			pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if("process".equals(state)){
			//已上报  查询
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("status", "0");
			condMap.put("taskType", "process");
			condMap.put("teamId", teamId);
			condMap.put("draft.draftNameLike", draftName);
			condMap.put("draft.creatorNameLike", creatorName);
			pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}else if("done".equals(state)){
			//已上报  查询
			sortMap.put("taskTime", CommonConst.ORDER_DESC);
			condMap.put("status", "1");
			condMap.put("taskType", "process");
			condMap.put("teamId", teamId);
			condMap.put("draft.draftNameLike", draftName);
			condMap.put("draft.creatorNameLike", creatorName);
			pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
		}
		return SUCCESS;
	}
	@Action(value = "gzcaqcbl", results = {@Result(name = SUCCESS, location = "/legislate/draft/gzcaqcbl.jsp")})
	public String gzcaqcbl() throws FzbDaoException {
		draftTask = draftTaskService.findById(taskId);
		if(draftTask.getDraft().getFbd() != null){
			isdise = "yes";
		}else{
			isdise = "no";
		}
		this.draftId = draftTask.getDraft().getDraftId();
		Map<String, Object> condMap =  new HashMap<String, Object>();
		Map<String, String> sortMap =  new HashMap<String, String>();
		sortMap.put("taskTime",  CommonConst.ORDER_ASC);
		condMap.put("draft.draftId", draftTask.getDraft().getDraftId());
		condMap.put("status", "1");
		pageModel = draftTaskService.findByPage(condMap, sortMap , pageNo, pageSize);
		condMap.clear();
		condMap.put("draft.draftId", draftTask.getDraft().getDraftId());
		condMap.put("taskType", "claim");
		if( draftTaskService.findByList(condMap, sortMap).size()!=0){
			draftTaskBygzcaqcbl =draftTaskService.findByList(condMap, sortMap).get(0);
		}
		
		loadDraftFile();
		return SUCCESS;
	}
	
	@Action(value = "saveDraft", results = {@Result(name = SUCCESS, location = "/legislate/draft/draft_add.jsp")})
	public String save() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		condMap.put("activityType", LegislateConst.ACTIVITY_TYPE_Draft_ADD);
		sortMap.put("createTime",  CommonConst.ORDER_ASC);
		modellist = modelService.findByList(condMap , sortMap );
		if("add".equals(op)){
			if(!StringUtils.hasText(draftId)){
				op = "save";
			}
		}else if("save".equals(op)){
			String userId = user.getUserId();
			draft = new Draft();
			draft.setCreateTime(new Date());
			draft.setCreatorId(userId);
			draft.setCreatorName(user.getName());
			draft.setDraftName(draftName);
			draft.setInstructions(instructions);
			draft.setStatus(LegislateConst.STATUS_INIT);
			draft.setTeamId(team == null ? "" : team.getId());
			draft.setTeamName(team == null ? "" :  team.getTeamName());
			draft.setUpdateId(userId);
			draft.setUpdateTime(new Date());
			draftService.add(draft);
			draftId = draft.getDraftId();
			op = "add";
		}else if("update".equals(op)){
			String userId = user.getUserId();
			draft = draftService.findById(draftId);
			draft.setDraftName(draftName);
			draft.setInstructions(instructions);
			draft.setUpdateId(userId);
			draft.setUpdateName(user.getName());
			draft.setUpdateTime(new Date());
			draftService.saveOrUpdate(draft);
			op = "add";
		}
		
		if(StringUtils.hasText(draftId)){
			condMap.clear();
			sortMap.clear();
			condMap.put("draftId", draftId);
			//loadDraftFile();
			Map<String, Object> attcondMap = new LinkedHashMap<String, Object>();
			Map<String, String> attsortMap = new HashMap<String, String>();
			attcondMap.put("outId",  draftId);
			attcondMap.put("activityType",  "起草");
			draftFiles = fileRecordService.findByList(attcondMap, attsortMap);
			draft = draftService.findById(draftId);
			draftName = draft.getDraftName();
			instructions = draft.getInstructions();
		}		
		return SUCCESS;
	}
	
	@Action(value = "uploadDraftFile",results = {@Result(name = SUCCESS, location = "/legislate/draft/draft_add.jsp"),
			@Result(name = INPUT, location = "/legislate/draft/upload_file.jsp")})
	public String uploadDraftFile() throws FzbDaoException{
		byte[] content = upload();
		String bizType = request.getParameter("bizType");
		String activityType = request.getParameter("activityType");
		String saveDraft = request.getParameter("saveDraft");
		draftId = request.getParameter("draftId");
		if(content != null){
			UserInfo user = super.getLoginUser();
			if(user == null){
				return null;
			}
			FileRecord info = new FileRecord();
			Map<String, Object> condMap = new HashMap<String, Object>();
			condMap.put("outId", draftId);
			condMap.put("activityType",activityType);
			condMap.put("bizType", bizType);
			
			List<FileRecord> fileRecord = fileRecordService.findByList(condMap , null);
			if(!fileRecord.isEmpty() && !LegislateConst.Draft_Add_Model_key_07.equals(bizType)){
				info = fileRecord.get(0);
				info = fileRecordService.findById(info.getFileRecordId());
				info.setFileContent(content);
				info.setFileName(getUploadFileFileName());
				fileRecordService.saveOrUpdate(info);
			}else{
				String userId = user.getUserId();
				info.setCreatorId(userId);
				info.setFileContent(content);
				info.setFileName(getUploadFileFileName());
				info.setBizType(bizType);
				info.setOutId(draftId);
				info.setCreateTime(new Date());
				info.setActivityType(activityType);
				fileRecordService.add(info);
			}			
		}
		if("0".equals(saveDraft)){
			if(content != null){
				request.setAttribute("message", "上传成功！");
			}
			return INPUT;
		}
		return save();
	}
	
	/**
	 * 未上报
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_init_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/gzcaqclb.jsp")
		})
	public String draft_init_list() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		String op = request.getParameter("op");
		String draftId = request.getParameter("draftId");
		if("del".equals(op)){
			if(draftId!=null&&!"".equals(draftId)&&!"null".equals(draftId)){
				Draft delDraft = draftService.findById(draftId);
				if(delDraft!=null)
					draftService.delete(delDraft);
			}
		}
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("creatorId", user.getUserId());
		condMap.put("status", LegislateConst.STATUS_INIT);
 		pageModel = draftService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = LegislateConst.STATUS_INIT;
 		return SUCCESS;
	}
	
	/**
	 * 已上报
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_send_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/gzcaqclb.jsp")
		})
	public String draft_send_list() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("taskTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("status", "1");
		condMap.put("taskType", LegislateConst.STATUS_SEND);
		condMap.put("draft.creatorId", user.getUserId());
 		pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = LegislateConst.STATUS_SEND;
 		return SUCCESS;
	}
	
	/**
	 * 未接收
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_unreceive_list", results = {
		@Result(name = SUCCESS, location = "/legislate/draft/gzcafblb.jsp"),
	})
	public String draft_unreceive_list() throws FzbDaoException{
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
 		pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "unreceive";
		return SUCCESS;
	}
	/**
	 * 已接收
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_receive_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/gzcafblb.jsp"),
		})
	public String draft_receive_list() throws FzbDaoException{
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
 		pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "receive";
		return SUCCESS;
	}
	/**
	 * 待分办
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_undise_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/gzcafblb.jsp"),
		})
	public String draft_undise_list() throws FzbDaoException{
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
 		pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "undise";
		return SUCCESS;
	}
	
	/**
	 * 已分办
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_dise_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/gzcafblb.jsp"),
		})
	public String draft_dise_list() throws FzbDaoException{
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
 		pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "dise";
		return SUCCESS;
	}
	
	/**
	 * 立法处待处理
	 * 
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_unprocess_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/gzcabllb.jsp"),
		})
	public String draft_unprocess_list() throws FzbDaoException{
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
		condMap.put("taskType", "claim");
		condMap.put("teamId", teamId);
 		pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "unprocess";
		return SUCCESS;
	}
	
	/**
	 * 立法处处理中
	 * 
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_process_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/gzcabllb.jsp"),
		})
	public String draft_process_list() throws FzbDaoException{
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
		condMap.put("taskType", "process");
		condMap.put("teamId", teamId);
 		pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "process";
		return SUCCESS;
	}
	
	/**
	 * 办理完毕
	 * 
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_done_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/gzcabllb.jsp"),
		})
	public String draft_done_list() throws FzbDaoException{
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
		condMap.put("taskType", "process");
		condMap.put("teamId", teamId);
 		pageModel = draftTaskService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = "done";
		return SUCCESS;
	}
	
	/**
	 * 上报检查是否有重复
	 * @return
	 * @throws FzbDaoException
	 * @throws IOException 
	 */
	@Action(value = "draft_check")
	public void draft_check() throws FzbDaoException, IOException{
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		UserInfo user = super.getLoginUser();
		TeamInfo team = getTeamInfo(user);
		draft = draftService.findById(draftId);
		condMap.put("draftName", draft.getDraftName());
		condMap.put("creatorId", user.getUserId());
		condMap.put("statusNotEq", "init");
		List<Draft> list = draftService.findByList(condMap, sortMap);
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

	@Action(value = "draft_send")
	public String draft_send() throws FzbDaoException{
		int code = 400;
		String message = "请上传完相关资料";
		try {
			UserInfo user = super.getLoginUser();
			if(user == null){
				message = "登录已超时！";
				code = 400;
			}else{
				loadDraftFile();
				//上传文件的类型
				Set<String> bizTypes = new HashSet<String>();
				for (FileRecord fileRecord : draftFiles) {
					bizTypes.add(fileRecord.getBizType());
				}
				//排除其他材料
				String content = request.getParameter("content");
				//对于该单位填报的相类似的规章、计划系统予以提示，并需要填写说明理由后方可上报
				if(content!=null&&!"".equals(content)&&!"null".equals(content))
					draft.setReasonDesc(content);
				bizTypes.remove(LegislateConst.Draft_Add_Model_key_07);
				if(bizTypes.size() >=6){
					TeamInfo team = getTeamInfo(user);
					String teamId = team.getId();
					draft = draftService.findById(draftId);
					draft.setStatus(LegislateConst.STATUS_SEND);
					draft.setUpdateId(user.getUserId());
					draft.setUpdateName(user.getName());
					draft.setTeamName(team.getTeamName());
					draft.setTeamId(teamId);
					draftService.updateBySend(draft);
					message = "报送成功";
					code = 200;
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
	 * 草案接收
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_receive")
	public String draft_receive() throws FzbDaoException{
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
				DraftTask draftTask = draftTaskService.findById(taskId);
				draftTask.setTeamName(team.getTeamName());
				draftTask.setUserId(user.getUserId());
				draftTask.setUserName(user.getName());
				draftTask.setStatus(LegislateConst.TASK_STATUS_DONE);
				draftTask.setProcessTime(new Date());
				draftTask.setInstructions("已接收");
				draftTask.setTeamName(team.getTeamName());
				draft = draftTask.getDraft();
				draft.setStatus(LegislateConst.STATUS_RECEIVE);
				draft.setUpdateId(user.getUserId());
				draft.setUpdateName(user.getName());
				draft.setUpdateTime(new Date());
				draft.setTeamId(team.getId());
				draft.setTeamName(team.getTeamName());
				draftTask.setDraft(draft);
				draftService.updateByReceive(draftTask);
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
	 * 草案分办
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_dise",results={@Result(name = SUCCESS, location = "/legislate/draft/fbd.jsp")})
	public String draft_dise() throws FzbDaoException{
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
				DraftTask draftTask = draftTaskService.findById(taskId);
				draftTask.setTeamId(teamId);
				draftTask.setUserId(user.getUserId());
				draftTask.setUserName(user.getName());
				draftTask.setStatus(LegislateConst.TASK_STATUS_DONE);
				draftTask.setProcessTime(new Date());
				draftTask.setInstructions("已分办");
				draftTask.setTeamName(team.getTeamName());
				draft = draftTask.getDraft();
				draft.setStatus(LegislateConst.STATUS_DISE);
				draft.setUpdateId(user.getUserId());
				draft.setUpdateName(user.getName());
				draft.setUpdateTime(new Date());
				draft.setTeamId(team.getId());
				draft.setTeamName(team.getTeamName());
				draft.setFbd(fbd);
				draftTask.setDraft(draft);
				draftService.updateByDise(draftTask,fbd,pteamId);
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
			this.draftTask = draftTaskService.findById(taskId);
			if(draftTask.getDraft().getFbd() == null){
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
				fbd.setTeamId(draftTask.getDraft().getTeamId());
				fbdService.add(fbd);
				draftTask.getDraft().setFbd(fbd);
				draftService.update(draft); 
			}
			return SUCCESS;
		}
	}
	/**
	 * 草案认领
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "draft_claim")
	public String draft_claim() throws FzbDaoException{
		int code = 200;
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String message = "认领成功";
		try {
			String taskId = request.getParameter("taskId");
			DraftTask draftTask = draftTaskService.findById(taskId);
			draftTask.setTeamName(team.getTeamName());
			draftTask.setUserId(user.getUserId());
			draftTask.setUserName(user.getName());
			draftTask.setStatus(LegislateConst.TASK_STATUS_DONE);
			draftTask.setProcessTime(new Date());
			draftTask.setInstructions("已认领");
			draftTask.setTeamName(team.getTeamName());
			draft = draftTask.getDraft();
			draft.setStatus(LegislateConst.STATUS_CLAIM);
			draft.setUpdateId(user.getUserId());
			draft.setUpdateName(user.getName());
			draft.setUpdateTime(new Date());
			draft.setTeamId(team.getId());
			draft.setTeamName(team.getTeamName());
			draftTask.setDraft(draft);
			draftTask.setTeamId(team.getId());
			draftService.updateByClaim(draftTask);
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
	 * 文件删除
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "deleteFile")
	public String deleteFile() throws FzbDaoException{
		int code = 200;
		String message = "删除成功";
		try {
			fileRecordService.delete(fileRecordService.findById(fileRecordId));
		} catch (Exception e) {
			code = 400;
			message = "删除失败";
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
	
	@Action(value = "fileRecordLoad",  results = {@Result(name=SUCCESS,type="stream",params={
			"contentType","application/octet-stream;charset=utf-8",
			"inputName","inputStream",
			"contentDisposition","attachment;filename=\"${fileName}\"",
			"bufferSize","4096"
	})})
	public String fileRecordLoad(){
		String fileRecordId = (String) request.getParameter("fileRecordId");
		FileRecord model = this.fileRecordService.findById(fileRecordId);
		fileContent = model.getFileContent();
		fileName = model.getFileName();
		try {
			fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("文件下载的名称："+fileName);
		return SUCCESS;
	}
	
	public InputStream getInputStream(){
		if(null != fileContent){
			ByteArrayInputStream bais = null;
			try {
				bais = new ByteArrayInputStream(fileContent);
				return bais;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 草案的历史记录查看   传参  草案ID
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "lsjlck" , results = {
			@Result(name = SUCCESS, location = "/legislate/draft/ca-lsjl.jsp")
		})
	public String lsjlck() throws FzbDaoException {
		Map<String, Object> condMap = new HashMap<String, Object>();
		Map<String, String> sortMap = new HashMap<String, String>();
		condMap.put("outId", draftId);
		condMap.put("bizType", bizType);
		 FileRecordlist = fileRecordService.findByList(condMap, sortMap);
		return SUCCESS;
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

	public String getFileRecordId() {
		return fileRecordId;
	}
	public void setFileRecordId(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Page getPageModel() {
		return pageModel;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public Date getCreatestartTime() {
		return createstartTime;
	}

	public void setCreatestartTime(Date createstartTime) {
		this.createstartTime = createstartTime;
	}

	public Date getCreateendTime() {
		return createendTime;
	}

	public void setCreateendTime(Date createendTime) {
		this.createendTime = createendTime;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setPageModel(Page pageModel) {
		this.pageModel = pageModel;
	}

	public List<Model> getModellist() {
		return modellist;
	}

	public void setModellist(List<Model> modellist) {
		this.modellist = modellist;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public List<FileRecord> getFileRecordlist() {
		return FileRecordlist;
	}

	public void setFileRecordlist(List<FileRecord> fileRecordlist) {
		FileRecordlist = fileRecordlist;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getIsdise() {
		return isdise;
	}

	public void setIsdise(String isdise) {
		this.isdise = isdise;
	}

	public Draft getDraft() {
		return draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}
	
	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}


	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public DraftTask getDraftTask() {
		return draftTask;
	}

	public void setDraftTask(DraftTask draftTask) {
		this.draftTask = draftTask;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDraftName() {
		return draftName;
	}

	public void setDraftName(String draftName) {
		this.draftName = draftName;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getDraftId() {
		return draftId;
	}

	public void setDraftId(String draftId) {
		this.draftId = draftId;
	}

	public List<FileRecord> getDraftFiles() {
		return draftFiles;
	}

	public DraftTask getDraftTaskBygzcaqcbl() {
		return draftTaskBygzcaqcbl;
	}

	public void setDraftTaskBygzcaqcbl(DraftTask draftTaskBygzcaqcbl) {
		this.draftTaskBygzcaqcbl = draftTaskBygzcaqcbl;
	}

	public void setDraftFiles(List<FileRecord> draftFiles) {
		this.draftFiles = draftFiles;
	}

	public Map<String, String> getAllowFileType() {
		return allowFileType;
	}

	public void setAllowFileType(Map<String, String> allowFileType) {
		this.allowFileType = allowFileType;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public List<FileRecord> getOtherFiles() {
		return otherFiles;
	}

	public void setOtherFiles(List<FileRecord> otherFiles) {
		this.otherFiles = otherFiles;
	}

	public List<FileRecord> getCurrFiles() {
		return currFiles;
	}

	public void setCurrFiles(List<FileRecord> currFiles) {
		this.currFiles = currFiles;
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

	public String getUuu() {
		return uuu;
	}

	public void setUuu(String uuu) {
		this.uuu = uuu;
	}
	
}
