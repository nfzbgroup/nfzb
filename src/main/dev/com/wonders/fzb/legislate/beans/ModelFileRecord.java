/**
 * 
 */
package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author shawn
 * 
 */
@Entity
@Table(name = "WEGOV_LF_MODEL")
public class ModelFileRecord {

	@Id
	@Column(name = "MODEL_ID")
	private String modelId;

	/**
	 * 范本名称
	 */
	@Column(name = "MODEL_NAME")
	private String modelName;

	/**
	 * 范本类型
	 */
	@Column(name = "MODEL_TYPE")
	private String bizType;

	/**
	 * 环节类型
	 */
	@Column(name = "ACTIVITY_TYPE")
	private String activityType;

	/**
	 * 使用說明
	 */
	@Column(name = "INSTRUCTIONS")
	private String instructions;
	/**
	 * 状态
	 */
	@Column(name = "STATUS")
	private int status;

	/**
	 * 文件名称
	 */
	@Column(name = "file_name")
	private String fileName;

	/**
	 * 创建人
	 */
	@Column(name = "CREATOR_ID")
	private String creatorId;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;

	/**
	 * 创建人姓名
	 */
	@Column(name = "CREATOR_NAME")
	private String creatorName;

	/**
	 * 外键
	 */
	@Column(name = "out_id")
	private String outId;
	
	@Column(name = "file_record_id")
	private String fileRecordId;
	
	@Column(name = "is_unique")
	private Integer unique;
	
	@Column(name = "has_file")
	private String hasFile;
	

	public String getHasFile() {
		return hasFile;
	}

	public void setHasFile(String hasFile) {
		this.hasFile = hasFile;
	}

	public Integer getUnique() {
		return unique;
	}

	public void setUnique(Integer unique) {
		this.unique = unique;
	}

	public String getFileRecordId() {
		return fileRecordId;
	}

	public void setFileRecordId(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

}
