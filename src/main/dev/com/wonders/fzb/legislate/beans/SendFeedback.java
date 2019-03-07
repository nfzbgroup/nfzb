package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 发送反馈
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_SEND_FEEDBACK")
public class SendFeedback {
	/**
	 * 发送反馈ID
	 */
	@Id
	@Column(name = "SEND_FEEDBACK_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String sendFeedbackId;
	/**
	 * 业务ID
	 */
	@Column(name = "BIZ_ID")
	private String bizId;
	/**
	 * 业务类型
	 */
	@Column(name = "BIZ_TYPE")
	private String bizType;
	/**
	 * 发送名单
	 */
	@Column(name = "SEND_LIST")
	private String sendList;
	/**
	 * 反馈内容
	 */
	@Column(name = "FEEDBACK_CONTENT")
	private String feedbackContent;
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

	public String getSendFeedbackId() {
		return sendFeedbackId;
	}

	public void setSendFeedbackId(String sendFeedbackId) {
		this.sendFeedbackId = sendFeedbackId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getSendList() {
		return sendList;
	}

	public void setSendList(String sendList) {
		this.sendList = sendList;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
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
