package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 流程实例表 ( 例如:每发起 计划、起草 都会实例一条记录 )
 * 
 * @author FZB
 * 
 */

@Entity
@Table(name = "WEGOV_LF_PROCESS_INSTANCE")
public class ProcessInstance {
	/**
	 * 
	 * 实例ID
	 */
	@Id
	@Column(name = "PROCESS_INST_ID")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String processiInstId;

	/**
	 * 实例code
	 */
	@Column(name = "PROCESS_INST_CODE")
	private String processiInstCode;

	/**
	 * 实例状态
	 */
	@Column(name = "INSTANCE_STATUS")
	private String instanceStatus;

	/**
	 * 实例主题
	 */
	@Column(name = "INSTANCE_TOPIC")
	private String instanceTopic;

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

	public String getProcessiInstId() {
		return processiInstId;
	}

	public void setProcessiInstId(String processiInstId) {
		this.processiInstId = processiInstId;
	}

	public String getProcessiInstCode() {
		return processiInstCode;
	}

	public void setProcessiInstCode(String processiInstCode) {
		this.processiInstCode = processiInstCode;
	}

	public String getInstanceStatus() {
		return instanceStatus;
	}

	public void setInstanceStatus(String instanceStatus) {
		this.instanceStatus = instanceStatus;
	}

	public String getInstanceTopic() {
		return instanceTopic;
	}

	public void setInstanceTopic(String instanceTopic) {
		this.instanceTopic = instanceTopic;
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
