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
 * 审核会议
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_AUDIT_MEETING")
public class AuditMeeting {
	/**
	 * 审核会议ID
	 */
	@Id
	@Column(name = "AUDIT_MEETING_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String auditMeetingId;
	@ManyToOne(fetch =FetchType.LAZY )
	@JoinColumn(name = "draft_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Draft draft;
	/**
	 * 会议标题
	 */
	@Column(name = "MEETING_TITLE")
	private String meetingTitle;
	/**
	 * 会议地点
	 */
	@Column(name = "MEETING_PLACE")
	private String meetingPlace;
	/**
	 * 会议人员
	 */
	@Column(name = "MEETING_PEOPLE")
	private String meetingPeople;
	/**
	 * 会议时间
	 */
	@Column(name = "MEETING_TIME")
	private Date meetingTime;
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

	@Column(name = "UPDATE_NAME")
	private String updateName;
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
	
	
	public String getAuditMeetingId() {
		return auditMeetingId;
	}

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

	public void setAuditMeetingId(String auditMeetingId) {
		this.auditMeetingId = auditMeetingId;
	}

	public Draft getDraft() {
		return draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public String getMeetingPlace() {
		return meetingPlace;
	}

	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}

	public String getMeetingPeople() {
		return meetingPeople;
	}

	public void setMeetingPeople(String meetingPeople) {
		this.meetingPeople = meetingPeople;
	}

	public Date getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
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
