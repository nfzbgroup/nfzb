package com.wonders.fzb.legislate.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.FileManageAction;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislate.LegislateConst;
import com.wonders.fzb.legislate.beans.ExchangePivot;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.services.ExchangePivotService;

/**
 * ReviewGenFile action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislate")
@Controller
@Scope("prototype")
public class FileRecordAction extends FileManageAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3039340455800015952L;
	private String draftNumber;
	private String activityType;
	private String bizType;
	private String op;
	@Autowired
	ExchangePivotService exchangePivotService;
	
	
	@Action(value = "draft_upload_file", results = {@Result(name = SUCCESS, location = "/legislate/draft/draft_upload_file.jsp")})
	public String sylist() throws FzbDaoException {
		List<String> activityTypeList = Arrays.asList(
				//公开意见
				LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION,
				//公开意见采纳情况
				LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION_DONE,
				//审核会议前
				LegislateConst.ACTIVITY_TYPE_Draft_AuditMeeting_start,
				//审核会议后
				LegislateConst.ACTIVITY_TYPE_Draft_AuditMeeting_end,
				//立法听证会前
				LegislateConst.ACTIVITY_TYPE_Draft_HEARING_start,
				//立法听证会后
				LegislateConst.ACTIVITY_TYPE_Draft_HEARING_end,
				//单位意见
				LegislateConst.ACTIVITY_TYPE_Draft_TeamOpinion,
				//专家论证会前
				LegislateConst.ACTIVITY_TYPE_Draft_DEMONSTRATION_start,
				//专家论证会后
				LegislateConst.ACTIVITY_TYPE_Draft_DEMONSTRATION_end);
		
		request.setAttribute("activityTypeList", activityTypeList);
		
		JSONObject modelType = new JSONObject();
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION, 
				Arrays.asList(
						LegislateConst.Draft_Add_Model_key_02,
						LegislateConst.Draft_PUBLIC_OPINION_key_01,
						LegislateConst.Draft_PUBLIC_OPINION_key_02,
						LegislateConst.Draft_PUBLIC_OPINION_key_03));
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION_DONE, 
				Arrays.asList(
						LegislateConst.Draft_PUBLIC_OPINION_key_04));
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_AuditMeeting_start, 
				Arrays.asList(
						LegislateConst.Draft_AuditMeeting_key_01,
						LegislateConst.Draft_AuditMeeting_key_02));
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_AuditMeeting_end, 
				Arrays.asList(
						LegislateConst.Draft_AuditMeeting_key_04,
						LegislateConst.Draft_AuditMeeting_key_05));
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_HEARING_start, 
				Arrays.asList(
						LegislateConst.Draft_HEARING_key_01,
						LegislateConst.Draft_HEARING_key_02,
						LegislateConst.Draft_HEARING_key_03,
						LegislateConst.Draft_HEARING_key_04));
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_HEARING_end, 
				Arrays.asList(
						LegislateConst.Draft_HEARING_key_06));
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_TeamOpinion, 
				Arrays.asList(
						LegislateConst.Draft_Add_Model_key_02));
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_DEMONSTRATION_start, 
				Arrays.asList(
						LegislateConst.Draft_AuditMeeting_key_02,
						LegislateConst.Draft_AuditMeeting_key_01));
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_DEMONSTRATION_end, 
				Arrays.asList(
						LegislateConst.Draft_AuditMeeting_key_04,
						LegislateConst.Draft_AuditMeeting_key_05));
		
		request.setAttribute("modelType", modelType);
		if(this.activityType == null){
			this.activityType = LegislateConst.ACTIVITY_TYPE_Draft_ADD;
		}
		request.setAttribute("modelTypeList", new ArrayList(modelType.optJSONArray(LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION)));
		if(op == null){
			this.op = "upload";
			return SUCCESS;
		}
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		FileRecord fileRecord = new FileRecord();
		fileRecord.setActivityType(activityType);
		fileRecord.setBizType(bizType);
		fileRecord.setCreateTime(new Date());
		fileRecord.setCreatorName(user.getName());
		fileRecord.setCreatorId(user.getUserId());
		fileRecord.setFileContent(upload());
		fileRecord.setFileName(getUploadFileFileName());
		fileRecord.setOutId(draftNumber);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("fileRecord", fileRecord);
		ExchangePivot exchangePivot = new ExchangePivot();
		try {
			exchangePivot.setFileContent(jsonObject.toString().getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		exchangePivot.setInsertDate(new Date());
		exchangePivot.setSendUserId(user.getUserId());
		exchangePivot.setSendUserName(user.getName());
		exchangePivot.setModule("材料上报");
		exchangePivot.setType("上传");
		exchangePivot.setInfoId(fileRecord.getFileRecordId());
		exchangePivot.setParser_class_name("fileRecordService#save");
		exchangePivot.setRecvStatus("init");
		exchangePivotService.saveOrUpdate(exchangePivot );
		request.setAttribute("msg", "上传成功");
		return SUCCESS;
	}

	public String getDraftNumber() {
		return draftNumber;
	}

	public void setDraftNumber(String draftNumber) {
		this.draftNumber = draftNumber;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
    

}
