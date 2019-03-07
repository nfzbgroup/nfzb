package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 范本表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_MODEL")
public class Model {
	/**
	 * 范本ID
	 */
	@Id
	@Column(name = "MODEL_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
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
	private String modelType;

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
	 * 修改人
	 */
	@Column(name = "UPDATE_ID")
	private String updateId;

	/**
	 * 创建人姓名
	 */
	@Column(name = "CREATOR_NAME")
	private String creatorName;
	/**
	 * 修改人姓名
	 */
	@Column(name = "UPDATE_NAME")
	private String updateName;
	
	@Column(name = "is_unique")
	private Integer unique;
	
	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	
	/**
	 * 修改时间
	 */
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	/**
	 * 范本文件
	 */
	@ManyToOne(fetch =FetchType.LAZY )
	@JoinColumn(name = "FILE_RECORD_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private ModelFile modelFile;

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

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
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

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public ModelFile getModelFile() {
		return modelFile;
	}

	public void setModelFile(ModelFile modelFile) {
		this.modelFile = modelFile;
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

	public Integer getUnique() {
		return unique;
	}

	public void setUnique(Integer unique) {
		this.unique = unique;
	}
	
}
