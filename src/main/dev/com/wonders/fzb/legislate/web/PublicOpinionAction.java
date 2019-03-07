package com.wonders.fzb.legislate.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.wonders.fzb.legislate.LegislateConst;
import com.wonders.fzb.legislate.beans.Model;
import com.wonders.fzb.legislate.beans.OpinionItem;
import com.wonders.fzb.legislate.beans.PublicOpinion;
import com.wonders.fzb.legislate.services.OpinionItemService;
import com.wonders.fzb.legislate.services.PublicOpinionService;

@Namespace("/legislate")
@Controller
@Scope("prototype")
public class PublicOpinionAction extends DraftAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	PublicOpinionService publicOpinionService;
	@Autowired
	OpinionItemService opinionItemService;
	
	PublicOpinion publicOpinion;
	
	Date startTime;
	Date endTime;
	
	Date startTimeS;
	Date startTimeE;
	
	Date endTimeS;
	Date endTimeE;
	
	String opinionId;
	String teamName;
	
	public void loadOpinionFile(){
		loadFiles(draftId, LegislateConst.Draft_PUBLIC_OPINION_key_01, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION,this.currFiles);
		loadFiles(draftId, LegislateConst.Draft_PUBLIC_OPINION_key_02, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION,this.currFiles);
		loadFiles(draftId, LegislateConst.Draft_PUBLIC_OPINION_key_03, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION,this.currFiles);
		
		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_02, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION,this.currFiles);
//		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_01, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
//		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_03, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
//		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_04, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
//		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_05, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
//		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_06, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
		loadFiles(draftId, LegislateConst.Draft_Add_Model_key_07, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION,this.otherFiles);
		allowFileType.put(LegislateConst.Draft_Add_Model_key_07, LegislateConst.Draft_Add_Model_key_07);
	}
	
	public void loadModelFile(){
		

		if(modellist == null){
			modellist = new ArrayList<Model>();
		}
		
		Model model1 =  new Model();
		model1.setActivityType(LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
		model1.setModelType(LegislateConst.Draft_PUBLIC_OPINION_key_01);
		modellist.add(model1);
		
		Model model2 =  new Model();
		model2.setActivityType(LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
		model2.setModelType(LegislateConst.Draft_PUBLIC_OPINION_key_02);
		modellist.add(model2);	

		
		Model model3 =  new Model();
		model3.setActivityType(LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
		model3.setModelType(LegislateConst.Draft_PUBLIC_OPINION_key_03);
		modellist.add(model3);	
		
		//起草
		Model qc2 =  new Model();
		qc2.setActivityType(LegislateConst.ACTIVITY_TYPE_Draft_ADD);
		qc2.setModelType(LegislateConst.Draft_Add_Model_key_02);
		modellist.add(qc2);
		
		
		Model qc7 =  new Model();
		qc7.setActivityType(LegislateConst.ACTIVITY_TYPE_Draft_ADD);
		qc7.setModelType(LegislateConst.Draft_Add_Model_key_07);
		modellist.add(qc7);
	}
	
	@Action(value = "savePublicOption", results = {@Result(name = SUCCESS, location = "/legislate/draft/public_option_add.jsp")})
	public String savePublicOption() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		
		draft = draftService.findById(draftId);
		
		Map<String, String> sortMap = new HashMap<String, String>();
		Map<String, Object> condMap = new HashMap<String, Object>();
		
		condMap.put("draft.draftId", draftId);
		List<PublicOpinion> list = publicOpinionService.findByList(condMap, sortMap );
		if(!list.isEmpty()){
			publicOpinion = list.get(0);
		}else{
			publicOpinion = new PublicOpinion();
			publicOpinion.setCreateTime(new Date());
		}
		
		publicOpinion.setUserId(user.getUserId());
		publicOpinion.setUserName(user.getName());
		publicOpinion.setTeamId(team.getId());
		publicOpinion.setTeamName(team.getTeamName());
		publicOpinion.setDraft(draft);
		OpinionItem item = new OpinionItem();
		condMap.clear();
		condMap.put("outId", publicOpinion.getOpinionId());
		condMap.put("itemType","经办处意见");
		List<OpinionItem> itemList = opinionItemService.findByList(condMap, sortMap);
		if(!itemList.isEmpty()){
			item = itemList.get(0);
		}
		
		if("update".equals(op)){
			uploadDraftFile();
/*			publicOpinion.setStartTime(startTime);
			publicOpinion.setEndTime(endTime);*/
			
			
			item.setUserId(user.getUserId());
			item.setUserName(user.getName());
			item.setTeamId(team.getId());
			item.setTeamName(team.getTeamName());
			item.setCreanTime(new Date());
			item.setOutId(publicOpinion.getOpinionId());
			item.setItemType("经办处意见");
			item.setInstuctions(instructions);
			item.setActivityType("公开社会意见");
			opinionItemService.saveOrUpdate(item );
		}
		
		publicOpinionService.saveOrUpdate(publicOpinion);
		this.instructions = item.getInstuctions();
/*		this.endTime = publicOpinion.getEndTime();
		this.startTime = publicOpinion.getStartTime();*/
		
		loadModelFile();
		loadOpinionFile();
		
		return SUCCESS;
	}
	
	
	@Action(value = "modifyPublicOption", results = {@Result(name = SUCCESS, location = "/legislate/draft/public_option_modify.jsp")})
	public String modifyPublicOption() throws FzbDaoException {
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		TeamInfo team = getTeamInfo(user);
		
		publicOpinion = publicOpinionService.findById(opinionId);
		this.draft = publicOpinion.getDraft();
		this.draftId = draft.getDraftId();
		String zhcInstructions = request.getParameter("zhcInstructions");
		String bldInstructions = request.getParameter("bldInstructions");
		
		Map<String, String> sortMap = new HashMap<String, String>();
		Map<String, Object> condMap = new HashMap<String, Object>();
		OpinionItem zhcItem = new OpinionItem();
		OpinionItem bldItem = new OpinionItem();
		condMap.clear();
		condMap.put("outId", publicOpinion.getOpinionId());
		condMap.put("itemType","综合处意见");
		List<OpinionItem> itemList = opinionItemService.findByList(condMap, sortMap);
		if(!itemList.isEmpty()){
			zhcItem = itemList.get(0);
		}
		
		condMap.put("itemType","办领导意见");
		itemList = opinionItemService.findByList(condMap, sortMap);
		if(!itemList.isEmpty()){
			bldItem = itemList.get(0);
		}
		
		if("update".equals(op)){
			
		//uploadDraftFile();
			
			publicOpinion.setCallCount(request.getParameter("callCount") == null? 0 : Integer.parseInt(request.getParameter("callCount")));
			publicOpinion.setCallOpinionCount(request.getParameter("callOpinionCount") == null? 0 : Integer.parseInt(request.getParameter("callOpinionCount")));
			publicOpinion.setEmailCount(request.getParameter("emailCount") == null? 0 : Integer.parseInt(request.getParameter("emailCount")));
			publicOpinion.setEmailOpinionCount(request.getParameter("emailOpinionCount") == null? 0 : Integer.parseInt(request.getParameter("emailOpinionCount")));
			publicOpinion.setLetterCount(request.getParameter("letterCount") == null? 0 : Integer.parseInt(request.getParameter("letterCount")));
			publicOpinion.setLetterOpinionCount(request.getParameter("letterOpinionCount") == null? 0 : Integer.parseInt(request.getParameter("letterOpinionCount")));
			publicOpinion.setOpinionCount(request.getParameter("opinionCount") == null? 0 : Integer.parseInt(request.getParameter("opinionCount")));
			publicOpinion.setWebCount(request.getParameter("webCount") == null? 0 : Integer.parseInt(request.getParameter("webCount")));
			publicOpinion.setWebOpinionCount(request.getParameter("webOpinionCount") == null? 0 : Integer.parseInt(request.getParameter("webOpinionCount")));
			
			publicOpinion.setStartTime(startTime);
			publicOpinion.setEndTime(endTime);
			
			zhcItem.setUserId(user.getUserId());
			zhcItem.setUserName(user.getName());
			zhcItem.setTeamId(team.getId());
			zhcItem.setCreanTime(new Date());
			zhcItem.setTeamName(team.getTeamName());
			zhcItem.setOutId(publicOpinion.getOpinionId());
			zhcItem.setItemType("综合处意见");
			zhcItem.setInstuctions(zhcInstructions);
			opinionItemService.saveOrUpdate(zhcItem );
			
			
			bldItem.setUserId(user.getUserId());
			bldItem.setUserName(user.getName());
			bldItem.setTeamId(team.getId());
			bldItem.setTeamName(team.getTeamName());
			bldItem.setOutId(publicOpinion.getOpinionId());
			bldItem.setItemType("办领导意见");
			bldItem.setInstuctions(bldInstructions);
			opinionItemService.saveOrUpdate(bldItem );
			
			publicOpinionService.saveOrUpdate(publicOpinion);
		}
	
		Map instructionsMap = new HashMap();
		condMap.clear();
		condMap.put("outId", publicOpinion.getOpinionId());
		itemList = opinionItemService.findByList(condMap, sortMap);
		for (OpinionItem opinionItem : itemList) {
			instructionsMap.put(opinionItem.getItemType(), opinionItem.getInstuctions());
		}
		request.setAttribute("instructionsMap", instructionsMap);
		
		loadFiles(draftId, LegislateConst.Draft_PUBLIC_OPINION_key_04, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION,this.currFiles);
		loadFiles(draftId, LegislateConst.Draft_PUBLIC_OPINION_key_05, LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION,this.currFiles);
		
		loadModelFile();
		Model model4 =  new Model();
		model4.setActivityType(LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
		model4.setModelType(LegislateConst.Draft_PUBLIC_OPINION_key_04);
		modellist.add(model4);	

		
		Model model5 =  new Model();
		model5.setActivityType(LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION);
		model5.setModelType(LegislateConst.Draft_PUBLIC_OPINION_key_05);
		modellist.add(model5);	
		
		loadOpinionFile();
		return SUCCESS;
	}
	
	@Action(value = "public_opinion_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/public_opinion_list.jsp")
		})
	public String public_opinion_list() throws FzbDaoException{
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		String draftName = request.getParameter("draftName");
		if(StringUtils.hasText(draftName)){
			condMap.put("draft.draftNameLike", draftName);
		}
		if(teamName==null||"请选择".equals(teamName)){
			System.out.println("我都不敢相信，居然不可以");
		}else{
			condMap.put("teamName", teamName);
		}
		//发起时间
		if(!("".equals(startTime)||startTime == null)){  
			condMap.put("createTimeGe", startTime);
		}
		if(!("".equals(endTime)||endTime == null)){
			Calendar ca = new GregorianCalendar();
			ca.setTime(endTime);
			ca.add(ca.DATE, 1);
			condMap.put("createTimeLe", ca.getTime());
		}
		//征求开始时间
		if(!("".equals(startTimeS)||startTimeS == null)){  
			condMap.put("startTimeGe", startTimeS);
		}
		if(!("".equals(startTimeE)||startTimeE == null)){
			condMap.put("startTimeLe", startTimeE);
		}
		//反馈截止时间
		if(!("".equals(endTimeS)||endTimeS == null)){  
			condMap.put("endTimeGe", endTimeS);
		}
		if(!("".equals(endTimeE)||endTimeE == null)){
			condMap.put("endTimeLe", endTimeE);
		}
 		pageModel = publicOpinionService.findByPage(condMap, sortMap, pageNo, pageSize);
 		state = LegislateConst.STATUS_INIT;
 		return SUCCESS;
	}

	public PublicOpinion getPublicOpinion() {
		return publicOpinion;
	}

	public void setPublicOpinion(PublicOpinion publicOpinion) {
		this.publicOpinion = publicOpinion;
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

	public String getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(String opinionId) {
		this.opinionId = opinionId;
	}

	public Date getStartTimeS() {
		return startTimeS;
	}

	public void setStartTimeS(Date startTimeS) {
		this.startTimeS = startTimeS;
	}

	public Date getStartTimeE() {
		return startTimeE;
	}

	public void setStartTimeE(Date startTimeE) {
		this.startTimeE = startTimeE;
	}
	public Date getEndTimeS() {
		return endTimeS;
	}
	public void setEndTimeS(Date endTimeS) {
		this.endTimeS = endTimeS;
	}
	public Date getEndTimeE() {
		return endTimeE;
	}
	public void setEndTimeE(Date endTimeE) {
		this.endTimeE = endTimeE;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
