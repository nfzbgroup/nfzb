package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 工作项表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_WORKITEM")
public class Workitem {
	/**
	 * 工作项ID
	 */
	@Id
	@Column(name = "WORKITEM_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String workitemId;
	/**
	 * 工作项当前状态
	 */
	@Column(name = "CURRENTSTATE")
	private int currentstate;
	/**
	 * 实例id
	 */
	@Column(name = "PROCESS_INST_ID")
	private String processInstId;
	/**
	 * 实例code
	 */
	@Column(name = "PROCESS_INST_CODE")
	private String processInstCode;
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
	 * 参与者
	 */
	@Column(name = "PARTICIPANT")
	private String participant;
	/**
	 * 参与者名称
	 */
	@Column(name = "PARTINAME")
	private String partiname;
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

	public String getWorkitemId() {
		return workitemId;
	}

	public void setWorkitemId(String workitemId) {
		this.workitemId = workitemId;
	}

	public int getCurrentstate() {
		return currentstate;
	}

	public void setCurrentstate(int currentstate) {
		this.currentstate = currentstate;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getProcessInstCode() {
		return processInstCode;
	}

	public void setProcessInstCode(String processInstCode) {
		this.processInstCode = processInstCode;
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

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getPartiname() {
		return partiname;
	}

	public void setPartiname(String partiname) {
		this.partiname = partiname;
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
