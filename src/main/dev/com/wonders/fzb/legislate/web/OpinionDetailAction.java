package com.wonders.fzb.legislate.web;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.wonders.fzb.base.actions.FileManageAction;
import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.legislate.beans.FileRecord;
import com.wonders.fzb.legislate.beans.OpinionDetail;
import com.wonders.fzb.legislate.beans.PublicOpinion;
import com.wonders.fzb.legislate.services.FileRecordService;
import com.wonders.fzb.legislate.services.OpinionDetailService;
import com.wonders.fzb.legislate.services.PublicOpinionService;

import net.sf.json.JSONObject;


@Namespace("/legislate")
@Controller
@Scope("prototype")
public class OpinionDetailAction extends FileManageAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7120012037637950470L;
	@Autowired
	FileRecordService fileRecordService;
	@Autowired
	OpinionDetailService opinionDetailService;
	@Autowired
	PublicOpinionService opinionService;
	
	String detailId;
	String outId;
	OpinionDetail opinionDetail;
	String source;
	String name;
	String phone;
	String content;
	String op = "add";
	String draftId;
	private int pageNo = 1;
	private int pageSize = 10;
	private Page pageModel;
	private String updateop;
	
	@Action(value = "opinion_detail_save",results = {@Result(name = SUCCESS, location = "/legislate/draft/opinion_detail_add.jsp")})
	public String save() throws FzbDaoException{
		UserInfo user = super.getLoginUser();
		if(user == null){
			return null;
		}
		if(StringUtils.hasText(detailId)){
			opinionDetail = this.opinionDetailService.findById(detailId);
			op="save";
		}else{
			opinionDetail = new OpinionDetail();
		}
		if("save".equals(updateop)&&!StringUtils.isEmpty(detailId)){
			opinionDetail = this.opinionDetailService.findById(detailId);
		}
		opinionDetail.setContent(content);
		opinionDetail.setName(name);
		opinionDetail.setPhone(phone);
		opinionDetail.setSource(source);
 		opinionDetail.setOutId(outId);
		opinionDetail.setCreateTime(new Date());
		
		if("add".equals(op)){
			op = "save";
			updateop="save";
		}else if("save".equals(op)||"save".equals(updateop)){
			opinionDetail.setUserId(user.getUserId());
			opinionDetail.setUserName(user.getName());
			opinionDetailService.saveOrUpdate(opinionDetail);
			
			//查询数据判断
			Map<String, Object> condMapOne = new LinkedHashMap<String, Object>();
			condMapOne.put("detailId", opinionDetail.getDetailId());
			opinionDetail=opinionDetailService.findByList(condMapOne, null).get(0);
			
			byte[] content = upload();
			String bizType = request.getParameter("bizType");
 			String activityType = request.getParameter("activityType");
			if(content != null){  
				FileRecord info = new FileRecord();
				Map<String, Object> condMap = new HashMap<String, Object>();
				condMap.put("outId", opinionDetail.getDetailId());
				condMap.put("activityType",activityType);
				condMap.put("bizType", bizType);
				List<FileRecord> fileRecord = fileRecordService.findByList(condMap , null);
				if(!fileRecord.isEmpty()){
					info = fileRecord.get(0);
					info = fileRecordService. findById(info.getFileRecordId());
				}
				String userId = user.getUserId();
				
				info.setCreatorId(userId);
				info.setFileContent(content);
				info.setFileName(getUploadFileFileName());
				info.setBizType(bizType);
				info.setOutId(opinionDetail.getDetailId());
				info.setCreateTime(new Date());
				info.setActivityType(activityType);
				fileRecordService.saveOrUpdate(info);
			}
		}
		return SUCCESS;
	}
	
	@Action(value = "opinion_detail_list", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/opinion_detail_list.jsp")
		})
	public String public_opinion_list() throws FzbDaoException{
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("draft.draftId",draftId);
		PublicOpinion opinion = opinionService.findByList(condMap, null).get(0);
		condMap.clear();
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime",  CommonConst.ORDER_DESC);
		
		String draftName = request.getParameter("draftName");
		if(StringUtils.hasText(draftName)){
			condMap.put("draft.draftNameLike", draftName);
		}
		outId =  opinion.getOpinionId();
		condMap.put("outId",outId);
		if(source == "" || source == null){
			source="电子邮件";
		}
		if(!("".equals(name)||name==null)){
			condMap.put("nameLike",name);
		}
		condMap.put("source",source);
 		pageModel = opinionDetailService.findByPage(condMap, sortMap, pageNo, pageSize);
 		return SUCCESS;
	}
	
	@Action(value = "opinion_detail_view", results = {
			@Result(name = SUCCESS, location = "/legislate/draft/opinion_detail_view.jsp")
		})
	public String opinion_detail_view() throws FzbDaoException{
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		condMap.put("detailId", detailId);
		opinionDetail=opinionDetailService.findByList(condMap, null).get(0);
 		return SUCCESS;
	}

	//ReviewGenFile的删除
	@Action(value = "deleteopinion")
	public void deleteopinion() throws FzbDaoException, IOException {
		int code = 200;
		String message = "删除成功";
		try {
			if(StringUtils.hasText(detailId)){
				this.opinionDetail = this.opinionDetailService.findById(detailId);
				opinionDetailService.delete(opinionDetail);
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
	}
	
	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public OpinionDetail getOpinionDetail() {
		return opinionDetail;
	}

	public void setOpinionDetail(OpinionDetail opinionDetail) {
		this.opinionDetail = opinionDetail;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page getPageModel() {
		return pageModel;
	}

	public void setPageModel(Page pageModel) {
		this.pageModel = pageModel;
	}

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

	public String getDraftId() {
		return draftId;
	}

	public void setDraftId(String draftId) {
		this.draftId = draftId;
	}

	public String getUpdateop() {
		return updateop;
	}
	public void setUpdateop(String updateop) {
		this.updateop = updateop;
	}
	

}
