package com.wonders.fzb.legislate.web;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.TeamInfo;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.framework.services.TeamInfoService;
import com.wonders.fzb.legislate.LegislateConst;
import com.wonders.fzb.legislate.beans.Draft;
import com.wonders.fzb.legislate.beans.DraftTask;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.beans.Model;
import com.wonders.fzb.legislate.beans.TeamOpinion;
import com.wonders.fzb.legislate.beans.TeamOpinionDetail;
import com.wonders.fzb.legislate.services.DraftService;
import com.wonders.fzb.legislate.services.TeamOpinionDetailService;
import com.wonders.fzb.legislate.services.TeamOpinionService;

import net.sf.json.JSONObject;

@Namespace("/legislate")
@Controller
@Scope("prototype")
public class TeamOpinionAction extends DraftAction {
	
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	@Autowired
	TeamOpinionService teamOpinionService;
	@Autowired
	TeamOpinionDetailService teamOpinionDetailService;
	@Autowired
	TeamInfoService teamInfoService;
	@Autowired
	DraftService draftService;
	
	protected TeamOpinion teamOpinion;
	
	private TeamOpinionDetail teamOpinionDetail;
	
	protected String teamOpinionId;

	private String teamOpinionDetailId;

	private String opinionType;

	private String opinionChannel;

	private String opinionInfo;

	private String opinionName;
	
	private String opinionTeamList;

	private String opinionTeamListId;
	
	private DraftTask draftTask;
	
	private String st;
	
	private String detailStatus;

	private String fromTemplate = "Y";
	
	List<TeamOpinionDetail> details;
	
	private Date startTime;
	private Date endTime;
    private String teamName;
	
	protected TeamInfo getTeamInfo(UserInfo user){
		if(user.getTeamInfos() != null && !user.getTeamInfos().isEmpty()){
			return user.getTeamInfos().get(0);
		}
		return null;
	}
	
	protected Map<String,String> getAllowUploadFileType(String draftId){
		Map<String,String> fileType = new LinkedHashMap<String, String>();
		return fileType;
	}
	public void loadModellist(){
		
		if(modellist == null){
			modellist = new ArrayList<Model>();
		}
			
		
		//起草
		Model qc1 =  new Model();
		qc1.setActivityType(LegislateConst.ACTIVITY_TYPE_Draft_ADD);
		qc1.setModelType(LegislateConst.Draft_Add_Model_key_02);
		modellist.add(qc1);
		
		
		Model qc7 =  new Model();
		qc7.setActivityType(LegislateConst.ACTIVITY_TYPE_Draft_ADD);
		qc7.setModelType(LegislateConst.Draft_Add_Model_key_07);
		modellist.add(qc7);
		
	}

	public void loadTeamOpinionFile(){
		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_02, LegislateConst.ACTIVITY_TYPE_Draft_TeamOpinion,this.currFiles);
		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_07, LegislateConst.ACTIVITY_TYPE_Draft_TeamOpinion,this.otherFiles);
		allowFileType.put(LegislateConst.Draft_Add_Model_key_07, LegislateConst.Draft_Add_Model_key_07);
	}

	@Action(value = "saveTeamOpinion", results = {@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_add.jsp")})
	public String saveTeamOpinion() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		
 		List<Draft> draftList = draftService.findByList(condMap, sortMap);
 		for (Draft draft : draftList) {
			if(draft.getDraftId().equals(draftId)){
				draftName = draft.getDraftName();
			}
		}
 		request.setAttribute("draftList", draftList);
 		
 		if("zhengxun".equals(op)){

			int code = 200;
			String message = "征询成功";
			try {
	 			if(draftService.findById(draftId).getFbd()!=null){
					condMap.clear();
					sortMap.clear();
					condMap.put("draft.draftId", draftId);
					condMap.put("taskType", "claim");
					sortMap.put("taskTime", CommonConst.ORDER_DESC);
					if(draftTaskService.findByList(condMap, sortMap).size()!=0){
						draftTask =draftTaskService.findByList(condMap, sortMap).get(0);
						teamOpinion = new TeamOpinion();
						teamOpinion.setTeamName(draftTask.getTeamName());
						teamOpinion.setTeamId(draftTask.getTeamId());
						teamOpinion.setDraft(draftService.findById(draftId));
						teamOpinion.setCreateTime(new Date());
						teamOpinion.setCreatorId(user.getUserId());
						teamOpinion.setCreatorName(user.getName());
						teamOpinion.setUpdateId(user.getUserId());
						teamOpinion.setUpdateName(user.getName());
						teamOpinion.setUpdateTime(new Date());
						teamOpinion.setOpinionStatus("init");
						teamOpinionService.saveOrUpdate(teamOpinion);
					}else{
						message="征询失败:该草案未认领";
					}
				}else{
					message="征询失败:该草案未分办";
				}
	 			
			} catch (Exception e) {
				code = 400;
				message = "征询失败";
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
		
 			
		}else if("add".equals(op)){
			if(!StringUtils.hasText(teamOpinionId)){
				op = "save";
			}
		}else if("save".equals(op)){
			teamOpinion = teamOpinionService.findById(teamOpinionId);
			if(teamOpinion != null && teamOpinion.getOpinionBlob()!=null){
				fromTemplate = "N";
			}
			String userId = user.getUserId();
			teamOpinion = new TeamOpinion();
			teamOpinion.setCreateTime(new Date());
			teamOpinion.setCreatorId(userId);
			teamOpinion.setCreatorName(user.getName());
			teamOpinion.setUpdateId(userId);
			teamOpinion.setUpdateTime(new Date());
			teamOpinion.setUpdateName(user.getName());
			teamOpinion.setOpinionChannel(opinionChannel);
			teamOpinion.setOpinionInfo(opinionInfo);
			teamOpinion.setOpinionTeamList(opinionTeamList);
			teamOpinion.setOpinionType(opinionType);
//			teamOpinion.setOpinionName(draftService.findById(draftId).getTeamName());
			
			teamOpinion.setDraft(draftService.findById(draftId));


			
			teamOpinionService.add(teamOpinion);
			teamOpinionId = teamOpinion.getTeamOpinionId();
			teamOpinionService.saveOrUpdate(teamOpinion);
			System.out.println(opinionTeamListId);
			op = "update";
		}else if("update".equals(op)){
			String userId = user.getUserId();
			
			teamOpinion = teamOpinionService.findById(teamOpinionId);

			teamOpinion.setDraft(draftService.findById(draftId));
			
			teamOpinion.setOpinionChannel(opinionChannel);
			teamOpinion.setOpinionInfo(opinionInfo);
			teamOpinion.setOpinionTeamList(opinionTeamList);
			teamOpinion.setOpinionType(opinionType);
			teamOpinion.setOpinionName(opinionName);
			teamOpinion.setOpinionStatus("send");
			teamOpinion.setUpdateId(userId);
			teamOpinion.setUpdateName(user.getName());
			teamOpinion.setUpdateTime(new Date());
			teamOpinionService.saveOrUpdate(teamOpinion);
			op = "update";
			
			System.out.println(opinionTeamListId);
			//回填  草案
			request.setAttribute("selectDraftId", teamOpinion.getDraft().getDraftId());
		

		}
		if(StringUtils.hasText(teamOpinionId)){
			condMap.clear();
			sortMap.clear();
			teamOpinion = teamOpinionService.findById(teamOpinionId);
			//回填  草案
			request.setAttribute("selectDraftId", teamOpinion.getDraft().getDraftId());
			draftId = teamOpinion.getDraft().getDraftId();
			draftName = teamOpinion.getDraft().getDraftName();
			op = "update";
		}
		
		
		//在線編輯  -判斷是否需要模板
		teamOpinion = teamOpinionService.findById(teamOpinionId);
		if(teamOpinion != null && teamOpinion.getOpinionBlob()!=null){
			fromTemplate = "N";
		}
		
		loadModellist();
		loadTeamOpinionFile();
		return SUCCESS;
	}
	
	/**
	 * 审核会议列表
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "deleteTeamOpinion", results = {
			@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_list.jsp")
		})
	public String deleteteamOpinion() throws FzbDaoException{
		
		teamOpinion = new TeamOpinion();
		teamOpinion.setTeamOpinionId(teamOpinionId);	
		teamOpinionService.delete(teamOpinion);
		
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
	
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		

 		pageModel = teamOpinionService.findByPage(condMap, sortMap, pageNo, pageSize);
 		
 		
 		return SUCCESS;
	}
	
	/**
	 * 單位意見列表
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "teamOpinion_list", results = {
			@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_list.jsp")
		})
	public String teamOpinion_list() throws FzbDaoException{
		
		
		
		
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
	
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		
		if("".equals(state)){
			state="unsummary";
		}
		if("summary".equals(state)){
			condMap.put("opinionStatus", "recevie");
		}else{
			List<String> list = new ArrayList<String>();
			list.add("init");
			list.add("send");
			
			condMap.put("opinionStatusList", list);
			state="unsummary";
		}
		
		if(!StringUtils.isEmpty(opinionType)){
			condMap.put("opinionTypeLike",opinionType);
		}
		if(!StringUtils.isEmpty(draftName)){
			condMap.put("draft.draftNameLike",draftName);
		}
		if(!StringUtils.isEmpty(teamName)){
			condMap.put("teamName",teamName);
		}
		
		if(!("".equals(startTime)||startTime == null)){
			condMap.put("createTimeGe", startTime);
		}
		if(!("".equals(endTime)||endTime == null)){
			condMap.put("createTimeLe", endTime);
		}
		
 		pageModel = teamOpinionService.findByPage(condMap, sortMap, pageNo, pageSize);
 		
 		return SUCCESS;
	}
	
	/**
	 * 單位意見列表
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "teamOpinion_fk_list", results = {
			@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_fk_list.jsp")
		})
	public String teamOpinionfk_list() throws FzbDaoException{
		
		
		
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
	
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		if("".equals(detailStatus) || detailStatus == null){
			detailStatus = LegislateConst.STATUS_TEAMOPINION_SEND;
		}
		condMap.put("opinionStatus", detailStatus);
		condMap.put("teamId", teamId);
		
		if(!StringUtils.isEmpty(opinionType)){
			condMap.put("opinionTypeLike",opinionType);
		}
		if(!StringUtils.isEmpty(draftName)){
			condMap.put("teamOpinion.draft.draftNameLike",draftName);
		}
		
 		pageModel = teamOpinionDetailService.findByPage(condMap, sortMap, pageNo, pageSize);
 		
 		return SUCCESS;
	}
	
	/**
	 * 單位意見-修改状态
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "teamOpinion_fk_update_status", results = {
			@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_fk_list.jsp")
		})
	public String teamOpinion_fk_update_status() throws FzbDaoException{
		
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
		TeamOpinionDetail  teamOpinionDetail = teamOpinionDetailService.findById(teamOpinionDetailId);
		teamOpinionDetail.setOpinionStatus(LegislateConst.STATUS_TEAMOPINION_RECEIVE);
		teamOpinionDetail.setReceiveTime(new Date());
		teamOpinionDetailService.update(teamOpinionDetail);


		request.setAttribute("teamOpinionDetail", teamOpinionDetail);
		
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		if("".equals(detailStatus)){
			detailStatus = LegislateConst.STATUS_TEAMOPINION_SEND;
		}
		condMap.put("opinionStatus", detailStatus);
		condMap.put("teamId", teamId);
 		pageModel = teamOpinionDetailService.findByPage(condMap, sortMap, pageNo, pageSize);
 		
 		return SUCCESS;
	}
	
	/**
	 * 單位意見-反馈操作
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "teamOpinion_fk_save", results = {
			@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_fk_save.jsp"),
			@Result(name = "jsqk", location = "/legislate/teamOpinion/teamOpinion_fk_save2.jsp")
		})
	public String teamOpinion_fk_save() throws FzbDaoException{
		
		
		teamOpinionDetail = teamOpinionDetailService.findById(teamOpinionDetailId);
		
		if("jsqk".equals(st)){
			return "jsqk";
		}
 		
 		return SUCCESS;
	}
	
	/**
	 * 單位意見-反馈操作
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "teamOpinion_fk_uploadFile", results = {
			@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_fk_save.jsp"),
			@Result(name = "jsqk", location = "/legislate/teamOpinion/teamOpinion_fk_save2.jsp")
		})
	public String teamOpinion_fk_uploadFile() throws FzbDaoException{
		
		byte[] content = upload();
		if(content != null){
			teamOpinionDetail = teamOpinionDetailService.findById(teamOpinionDetailId);
			teamOpinionDetail.setOpinionInfo(getUploadFileFileName());
			teamOpinionDetail.setOpinionBlob(content);
			teamOpinionDetail.setOpinionStatus(LegislateConst.STATUS_TEAMOPINION_FEEDBACK);
			teamOpinionDetail.setUpdateTime(new Date());
			teamOpinionDetail.setReceiveTime(new Date());
			teamOpinionDetailService.saveOrUpdate(teamOpinionDetail);
		}
			
 		return teamOpinion_fk_save();
	}
	@Action(value = "saveTeamOpinionfk", results = {@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_fk_add.jsp")})
	public String saveTeamOpinionfk() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		
 		List<Draft> draftList = draftService.findByList(condMap, sortMap);
 		for (Draft draft : draftList) {
			if(draft.getDraftId().equals(draftId)){
				draftName = draft.getDraftName();
			}
		}
 		request.setAttribute("draftList", draftList);
 		if("add".equals(op)){
			if(!StringUtils.hasText(teamOpinionId)){
				op = "save";
			}
		}else if("save".equals(op)){
			teamOpinion = teamOpinionService.findById(teamOpinionId);
			if(teamOpinion != null && teamOpinion.getOpinionBlob()!=null){
				fromTemplate = "N";
			}
			String userId = user.getUserId();
			teamOpinion = new TeamOpinion();
			teamOpinion.setCreateTime(new Date());
			teamOpinion.setCreatorId(userId);
			teamOpinion.setCreatorName(user.getName());
			teamOpinion.setUpdateId(userId);
			teamOpinion.setUpdateTime(new Date());
			teamOpinion.setUpdateName(user.getName());
			
			teamOpinion.setOpinionChannel(opinionChannel);
			teamOpinion.setOpinionInfo(opinionInfo);
			teamOpinion.setOpinionTeamList(opinionTeamList);
			teamOpinion.setOpinionType(opinionType);
//			teamOpinion.setOpinionName(draftService.findById(draftId).getTeamName());
			
			teamOpinion.setDraft(draftService.findById(draftId));


			
			teamOpinionService.add(teamOpinion);
			teamOpinionId = teamOpinion.getTeamOpinionId();
			teamOpinionService.saveOrUpdate(teamOpinion);
			System.out.println(opinionTeamListId);
			op = "update";
		}else if("update".equals(op)){
			String userId = user.getUserId();

			teamOpinion.setDraft(draftService.findById(draftId));
			
			teamOpinion.setOpinionChannel(opinionChannel);
			teamOpinion.setOpinionInfo(opinionInfo);
			teamOpinion.setOpinionTeamList(opinionTeamList);
			teamOpinion.setOpinionType(opinionType);
			teamOpinion.setOpinionName(opinionName);
			
			teamOpinion.setUpdateId(userId);
			teamOpinion.setUpdateName(user.getName());
			teamOpinion.setUpdateTime(new Date());
			teamOpinionService.saveOrUpdate(teamOpinion);
			op = "update";
			
			System.out.println(opinionTeamListId);
			//回填  草案
			request.setAttribute("selectDraftId", teamOpinion.getDraft().getDraftId());
		

		}
		if(StringUtils.hasText(teamOpinionId)){
			condMap.clear();
			sortMap.clear();
			teamOpinion = teamOpinionService.findById(teamOpinionId);
			//回填  草案
			request.setAttribute("selectDraftId", teamOpinion.getDraft().getDraftId());
			draftId = teamOpinion.getDraft().getDraftId();
			draftName = teamOpinion.getDraft().getDraftName();
			op = "update";
		}
		
		
		//在線編輯  -判斷是否需要模板
		teamOpinion = teamOpinionService.findById(teamOpinionId);
		if(teamOpinion != null && teamOpinion.getOpinionBlob()!=null){
			fromTemplate = "N";
		}
		
		loadModellist();
		loadTeamOpinionFile();
		return SUCCESS;
	}
	/**
	 * 单位意见发送
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "teamOpinionDetailSava" )
	public void teamOpinionDetailSava() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
		}
		TeamInfo team = getTeamInfo(user);
		String teamId = team.getId();
			int code = 200;
			String message = "发送成功";
			try {
				teamOpinion = teamOpinionService.findById(teamOpinionId);
				teamOpinion.setOpinionStatus("recevie");
				String check= request.getParameter("checkval");
 				String userId = user.getUserId();
				String[] arr = check.split(",");
				TeamOpinionDetail detail;
				for (int i = 0; i < arr.length; i++) {
					detail = new TeamOpinionDetail();
					detail.setCreateTime(new Date());
					detail.setTeamOpinion(teamOpinion);
					detail.setTeamId(arr[i]);
					detail.setTeamName(teamInfoService.findTeamInfoByTeamId(LegislateConst.SYSTEM_MODULE, arr[i]).getTeamName());
					detail.setOpinionStatus(LegislateConst.STATUS_TEAMOPINION_SEND);
					detail.setUpdateId(userId);
					detail.setCreatorId(userId);
					detail.setUpdateName(user.getName());
					detail.setCreatorName(user.getName());
					detail.setUpdateTime(new Date());
					teamOpinionDetailService.saveOrUpdate(detail);
				}
				teamOpinionService.saveOrUpdate(teamOpinion);
			} catch (Exception e) {
				code = 400;
				message = "发送失败";
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
	
	@Action(value = "teamOpinionFileRecordLoad",  results = {@Result(name=SUCCESS,type="stream",params={
			"contentType","application/octet-stream;charset=utf-8",
			"inputName","inputStream",
			"contentDisposition","attachment;filename=\"${fileName}\"",
			"bufferSize","4096"
	})})
	public String teamOpinionFileRecordLoad(){
		String teamOpinionId = (String) request.getParameter("teamOpinionId");
		TeamOpinion teamOpinion = teamOpinionService.findById(teamOpinionId);
		fileContent = teamOpinion.getOpinionBlob();
		fileName = "单位意见内容信息.doc";
		try {
			fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "teamOpinionDetailFileRecordLoad",  results = {@Result(name=SUCCESS,type="stream",params={
			"contentType","application/octet-stream;charset=utf-8",
			"inputName","inputStream",
			"contentDisposition","attachment;filename=\"${fileName}\"",
			"bufferSize","4096"
	})})
	public String teamOpinionDetailFileRecordLoad(){
		String teamOpinionDetailId = (String) request.getParameter("teamOpinionDetailId");
		TeamOpinionDetail teamOpinionDetail =teamOpinionDetailService.findById(teamOpinionDetailId);
		fileContent = teamOpinionDetail.getOpinionBlob();
		fileName = teamOpinionDetail.getOpinionInfo();
		try {
			fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("文件下载的名称："+fileName);
		return SUCCESS;
	}
	
	
		@Action(value = "to_Teamadd" , results = {
				@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_add3.jsp")
			})
		public String to_Teamadd() throws FzbDaoException {
			Map<String, Object> condMap = new LinkedHashMap<String, Object>();
			Map<String, String> sortMap = new LinkedHashMap<String, String>();
			condMap.put("fbd.fbdIdIsNotNull","");
			List<Draft> draftList = draftService.findByList(condMap, sortMap);
			request.setAttribute("draftList", draftList);
			return SUCCESS;
		}
		
		
	@Action(value = "detaillist" , results = {
			@Result(name = SUCCESS, location = "/legislate/teamOpinion/dwyj-jsqk.jsp")
		})
	public String detaillist() throws FzbDaoException {
		teamOpinion = teamOpinionService.findById(teamOpinionId);
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("teamOpinion.teamOpinionId", teamOpinionId);
		details = teamOpinionDetailService.findByList(condMap, sortMap);
		return SUCCESS;
	}
	
	@Action(value = "detailTeamOpinion" , results = {
			@Result(name = SUCCESS, location = "/legislate/teamOpinion/teamOpinion_add2.jsp")
		})
	public String detail() throws FzbDaoException {
		teamOpinion = teamOpinionService.findById(teamOpinionId);
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("teamOpinion.teamOpinionId", teamOpinionId);
		loadModellist();
		loadTeamOpinionFile();
		return SUCCESS;
	}

	public TeamOpinion getTeamOpinion() {
		return teamOpinion;
	}

	public void setTeamOpinion(TeamOpinion teamOpinion) {
		this.teamOpinion = teamOpinion;
	}

	public String getTeamOpinionId() {
		return teamOpinionId;
	}

	public void setTeamOpinionId(String teamOpinionId) {
		this.teamOpinionId = teamOpinionId;
	}

	public String getOpinionType() {
		return opinionType;
	}



	public void setOpinionType(String opinionType) {
		this.opinionType = opinionType;
	}

	public String getOpinionChannel() {
		return opinionChannel;
	}

	public void setOpinionChannel(String opinionChannel) {
		this.opinionChannel = opinionChannel;
	}


	public String getOpinionInfo() {
		return opinionInfo;
	}

	public void setOpinionInfo(String opinionInfo) {
		this.opinionInfo = opinionInfo;
	}

	public String getOpinionName() {
		return opinionName;
	}

	public void setOpinionName(String opinionName) {
		this.opinionName = opinionName;
	}

	public String getOpinionTeamList() {
		return opinionTeamList;
	}

	public void setOpinionTeamList(String opinionTeamList) {
		this.opinionTeamList = opinionTeamList;
	}

	public String getOpinionTeamListId() {
		return opinionTeamListId;
	}

	public void setOpinionTeamListId(String opinionTeamListId) {
		this.opinionTeamListId = opinionTeamListId;
	}

	public DraftTask getDraftTask() {
		return draftTask;
	}

	public void setDraftTask(DraftTask draftTask) {
		this.draftTask = draftTask;
	}

	public Draft getDraft() {
		return draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}

	public String getDraftId() {
		return draftId;
	}

	public void setDraftId(String draftId) {
		this.draftId = draftId;
	}

	public String getDraftName() {
		return draftName;
	}

	public void setDraftName(String draftName) {
		this.draftName = draftName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFromTemplate() {
		return fromTemplate;
	}

	public void setFromTemplate(String fromTemplate) {
		this.fromTemplate = fromTemplate;
	}

	public List<TeamOpinionDetail> getDetails() {
		return details;
	}

	public void setDetails(List<TeamOpinionDetail> details) {
		this.details = details;
	}

	public String getDetailStatus() {
		return detailStatus;
	}

	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}

	public String getTeamOpinionDetailId() {
		return teamOpinionDetailId;
	}

	public void setTeamOpinionDetailId(String teamOpinionDetailId) {
		this.teamOpinionDetailId = teamOpinionDetailId;
	}

	public TeamOpinionDetail getTeamOpinionDetail() {
		return teamOpinionDetail;
	}

	public void setTeamOpinionDetail(TeamOpinionDetail teamOpinionDetail) {
		this.teamOpinionDetail = teamOpinionDetail;
	}

	public TeamOpinionService getTeamOpinionService() {
		return teamOpinionService;
	}

	public void setTeamOpinionService(TeamOpinionService teamOpinionService) {
		this.teamOpinionService = teamOpinionService;
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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}
	
}
