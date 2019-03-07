package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 意见表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_OPINION")
public class Opinion {
	/**
	 * 文件记录ID
	 */
	@Id
	@Column(name = "OPINION_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String opinionId;
	/**
	 * 实例id
	 */
	@Column(name = "PROCESS_INST_ID")
	private String processInstId;
	/**
	 * 工作项id
	 */
	@Column(name = "WORKITEM_ID")
	private String workitemId;
	/**
	 * 业务类型
	 */
	@Column(name = "BIZ_TYPE")
	private String bizType;
	/**
	 * 意见发表人
	 */
	@Column(name = "OPINION_PEOPLE")
	private String opinionPeople;
	/**
	 * 意见发表内容
	 */
	@Column(name = "OPINION_REMARK")
	private String opinionRemark;
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

	public String getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(String opinionId) {
		this.opinionId = opinionId;
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

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getOpinionPeople() {
		return opinionPeople;
	}

	public void setOpinionPeople(String opinionPeople) {
		this.opinionPeople = opinionPeople;
	}

	public String getOpinionRemark() {
		return opinionRemark;
	}

	public void setOpinionRemark(String opinionRemark) {
		this.opinionRemark = opinionRemark;
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
