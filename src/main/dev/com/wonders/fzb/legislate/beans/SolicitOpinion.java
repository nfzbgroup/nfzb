package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 征求意见
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_SOLICIT_OPINION")
public class SolicitOpinion {
	/**
	 * 征求意见ID
	 */
	@Id
	@Column(name = "SOLICIT_OPINION_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String solicitOpinionId;
	/**
	 * 流程实例ID
	 */
	@Column(name = "PROCESS_INST_ID")
	private String processInstId;
	/**
	 * 工作项ID
	 */
	@Column(name = "WORKITEM_ID")
	private String workitemId;
	/**
	 * 意见类型
	 */
	@Column(name = "OPINION_TYPE")
	private String opinionType;
	/**
	 * 意见条数
	 */
	@Column(name = "OPINION_COUNT")
	private String opinionCount;
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
	 * 修改时间
	 */
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

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

	public String getSolicitOpinionId() {
		return solicitOpinionId;
	}

	public void setSolicitOpinionId(String solicitOpinionId) {
		this.solicitOpinionId = solicitOpinionId;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getWorkitemId() {
		return workitemId;
	}

	public void setWorkitemId(String workitemId) {
		this.workitemId = workitemId;
	}

	public String getOpinionType() {
		return opinionType;
	}

	public void setOpinionType(String opinionType) {
		this.opinionType = opinionType;
	}

	public String getOpinionCount() {
		return opinionCount;
	}

	public void setOpinionCount(String opinionCount) {
		this.opinionCount = opinionCount;
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

}
