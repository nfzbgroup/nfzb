package com.wonders.fzb.legislate.web;

import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.wonders.fzb.legislate.LegislateConst;
import com.wonders.fzb.legislate.beans.Model;
import com.wonders.fzb.legislate.beans.ModelFile;
import com.wonders.fzb.legislate.services.ModelFileService;
import com.wonders.fzb.legislate.services.ModelService;

import net.sf.json.JSONObject;

/**
 * ReviewGenFile action接口
 * @author scalffold created by lj
 */
 
@Namespace("/legislate")
@Controller
@Scope("prototype")
public class ModelAction extends FileManageAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3039340455800015952L;
	
	@Autowired
	private  ModelService modelService;
	@Autowired
	private ModelFileService modelFileService;

	private int pageNo = 1;
	private int pageSize = 10;
    
	private String modelName;
	private String modelType;
	private String instructions;
	private String activityType;
    private Page pageModel;
    private String modelId;
    private int unique;
    private Model model;
    //判断是否是检索
    private String search;
    String op = "add";

	private String fileName;

	private byte[] fileContent;
    
	private Date startTime;
	private Date endTime;
    
    //仅仅是访问一下首页
	@Action(value = "sylist", results = {@Result(name = SUCCESS, location = "/legislate/plan/list.jsp")})
	public String sylist() throws FzbDaoException {
		return SUCCESS;
	}
    
    //范本查看
	@Action(value = "viewModel", results = {@Result(name = SUCCESS, location = "/legislate/regulations/model_view.jsp")})
	public String view() throws FzbDaoException {
		this.model = this.modelService.findById(modelId);
		return SUCCESS;
	}
    
	
	@Action(value = "modelFileLoad",  results = {@Result(name=SUCCESS,type="stream",params={
			"contentType","application/octet-stream;charset=utf-8",
			"inputName","inputStream",
			"contentDisposition","attachment;filename=\"${fileName}\"",
			"bufferSize","4096"
	})})
	public String modelFileLoad(){
		this.model = this.modelService.findById(modelId);
		ModelFile modelFile = model.getModelFile();
		fileContent = modelFile.getFileContent();
		fileName = modelFile.getFileName();
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
	
	//ReviewGenFile的查看列表
	@Action(value = "listModel", results = {@Result(name = SUCCESS, location = "/legislate/regulations/model_list.jsp")})
	public String list() throws FzbDaoException {
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		sortMap.put("createTime",  CommonConst.ORDER_DESC);
		Map<String, Object> condMap = new LinkedHashMap<String, Object>();
		System.out.println(modelType);
		if("query".equals(search)){
			if(!"".equals(modelName)||modelName!=null){
				condMap.put("modelNameLike", modelName);
			}
			if(!"请选择".equals(modelType)){
				condMap.put("modelType", modelType);
			}
			condMap.put("activityTypeLike", activityType);
			//大于等于
			if(startTime!=null){
				condMap.put("createTimeGe",startTime);
			}
			//小于等于
			if(endTime!=null){
				condMap.put("createTimeLe",endTime);
			}
		}
		pageModel=modelService.findByPage(condMap, sortMap, pageNo, pageSize);
		return SUCCESS;
	}
	
	
	//ReviewGenFile的新增
	@Action(value = "saveModel", results = {@Result(name = SUCCESS, location = "/legislate/regulations/model_add.jsp")})
	public String save() throws FzbDaoException {
		String op = (String)request.getParameter("op");
		
		if(StringUtils.hasText(modelId)){
			this.model = this.modelService.findById(modelId);
			this.op = "update";
		}
		byte[] content = upload();
		String unique1 = request.getParameter("unique");
		
		if(unique1!=null&&!"".equals(unique1)&&!"".equals(unique1)){
			this.unique = Integer.parseInt(unique1);
		}
		if("add".equals(op)){
			String creatorId = "1";
			
			model = new Model();
			model.setActivityType(activityType);
			model.setCreateTime(new Date());
			model.setCreatorId(creatorId);
			model.setInstructions(instructions);
			model.setModelName(getUploadFileFileName() == null ? "未上传文件" :getUploadFileFileName());
			model.setModelType(modelType);
			model.setStatus(1);
			model.setUpdateId(creatorId);
			model.setUnique(unique);
			model.setUpdateTime(new Date());
			ModelFile modelFile = null;
			if(content != null){
				modelFile = new ModelFile();
				modelFile.setCreateTime(new Date());
				modelFile.setFileContent(content);
				modelFile.setFileName(getUploadFileFileName());
				modelFile.setCreatorId(creatorId);
				model.setModelFile(modelFile);
			}
			
			modelService.add(model,modelFile);
			request.setAttribute("message", "保存成功！");
			this.op = "update";
		}else if("update".equals(op)){
			String creatorId = "1";
			
			
			model.setActivityType(activityType);
			model.setInstructions(instructions);
			if(content != null){
				model.setModelName(getUploadFileFileName());
			}
			model.setModelType(modelType);
			model.setUpdateId(creatorId);
			model.setUpdateTime(new Date());
			model.setUnique(unique);
			ModelFile modelFile = null;
			
			
			if(content != null){
				modelFile = new ModelFile();
				modelFile.setCreateTime(new Date());
				modelFile.setFileContent(content);
				modelFile.setFileName(getUploadFileFileName());
				modelFile.setCreatorId(creatorId);
			}
			request.setAttribute("message", "保存成功！");
			modelService.update(model,modelFile);
		}
		
		List<String> activityTypeList = Arrays.asList(
				
				//起草
				LegislateConst.ACTIVITY_TYPE_Draft_ADD,
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
				LegislateConst.ACTIVITY_TYPE_Draft_DEMONSTRATION_end,
				//立法计划
				LegislateConst.ACTIVITY_TYPE_Plan_ADD);
		
		request.setAttribute("activityTypeList", activityTypeList);
		
		JSONObject modelType = new JSONObject();
		modelType.put(LegislateConst.ACTIVITY_TYPE_Plan_ADD, 
				Arrays.asList(
						LegislateConst.Plan_Add_Model_key_01,
						LegislateConst.Plan_Add_Model_key_02,
						LegislateConst.Plan_Add_Model_key_03));
		
		modelType.put(LegislateConst.ACTIVITY_TYPE_Draft_ADD, 
				Arrays.asList(
						LegislateConst.Draft_Add_Model_key_02,
						LegislateConst.Draft_Add_Model_key_01,
						LegislateConst.Draft_Add_Model_key_03,
						LegislateConst.Draft_Add_Model_key_04,
						LegislateConst.Draft_Add_Model_key_05,
						LegislateConst.Draft_Add_Model_key_06,
						LegislateConst.Draft_Add_Model_key_07));
		
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
		if(this.model!=null){
			unique1 = "" + model.getUnique();
		}
		request.setAttribute("unique1", unique1);
		request.setAttribute("modelType", modelType);
		if(this.activityType == null){
			this.activityType = LegislateConst.ACTIVITY_TYPE_Draft_ADD;
		}
		if("add".equals(this.op)){
			request.setAttribute("modelTypeList", new ArrayList(modelType.optJSONArray(this.activityType)));
		}else{
			request.setAttribute("modelTypeList", new ArrayList(modelType.optJSONArray(model.getActivityType())));
		}
		return SUCCESS;
	}
		
	/**
	 * 文件删除
	 * @return
	 * @throws FzbDaoException
	 */
	@Action(value = "deleteModelFile")
	public String deleteModelFile() throws FzbDaoException{
		int code = 200;
		String message = "删除成功";
		try {
			String fileRecordId=request.getParameter("fileRecordId");
			if(StringUtils.hasText(fileRecordId)){
				modelFileService.delete(modelFileService.findById(fileRecordId));
			}
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
	
	//ReviewGenFile的禁用
	@Action(value = "disabled")
	public void disabled() throws FzbDaoException, IOException {
		int code = 200;
		String message = "禁用成功";
		try {
			if(StringUtils.hasText(modelId)){
				this.model = this.modelService.findById(modelId);
			}
			String creatorId = "1";
			model.setStatus(0);
			model.setUpdateId(creatorId);
			model.setUpdateTime(new Date());
			modelService.saveOrUpdate(model);
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
	
	//ReviewGenFile的启用
	@Action(value = "enabled")
	public void enabled() throws FzbDaoException, IOException {
		int code = 200;
		String message = "启用成功";
		try {
			if(StringUtils.hasText(modelId)){
				this.model = this.modelService.findById(modelId);
			}
			String creatorId = "1";
			model.setStatus(1);
			model.setUpdateId(creatorId);
			model.setUpdateTime(new Date());
			modelService.saveOrUpdate(model);
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
	
	//ReviewGenFile的删除
	@Action(value = "deleteModel")
	public void delete() throws FzbDaoException, IOException {
		int code = 200;
		String message = "删除成功";
		try {
			if(StringUtils.hasText(modelId)){
				this.model = this.modelService.findById(modelId);
				modelService.delete(model);
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

	public ModelService getModelService() {
		return modelService;
	}


	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
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



	public String getModelType() {
		return modelType;
	}


	public void setModelType(String modelType) {
		this.modelType = modelType;
	}


	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getActivityType() {
		return activityType;
	}
	
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public Page getPageModel() {
		return pageModel;
	}

	public void setPageModel(Page pageModel) {
		this.pageModel = pageModel;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	public String getOp() {
		return op;
	}


	public void setOp(String op) {
		this.op = op;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
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

}
