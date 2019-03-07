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
 * 听证会
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_HEARING")
public class Hearing {
	/**
	 * 听证会ID
	 */
	@Id
	@Column(name = "HEARING_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String hearingId;
	
	@ManyToOne(fetch =FetchType.LAZY )
	@JoinColumn(name = "draft_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Draft draft;
	/**
	 * 标题
	 */
	@Column(name = "HEARING_TITLE")
	private String hearingTitle;
	/**
	 * 地点
	 */
	@Column(name = "HEARING_PLACE")
	private String hearingPlace;

	/**
	 * 时间
	 */
	@Column(name = "HEARING_TIME")
	private Date hearingTime;
	
	/**
	 * 状态
	 */
	@Column(name = "HEARING_STATUS")
	private String hearingStatus;
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
	public String getHearingId() {
		return hearingId;
	}
	public void setHearingId(String hearingId) {
		this.hearingId = hearingId;
	}
	public Draft getDraft() {
		return draft;
	}
	public void setDraft(Draft draft) {
		this.draft = draft;
	}
	public String getHearingTitle() {
		return hearingTitle;
	}
	public void setHearingTitle(String hearingTitle) {
		this.hearingTitle = hearingTitle;
	}
	public String getHearingPlace() {
		return hearingPlace;
	}
	public void setHearingPlace(String hearingPlace) {
		this.hearingPlace = hearingPlace;
	}
	public Date getHearingTime() {
		return hearingTime;
	}
	public void setHearingTime(Date hearingTime) {
		this.hearingTime = hearingTime;
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
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getHearingStatus() {
		return hearingStatus;
	}
	public void setHearingStatus(String hearingStatus) {
		this.hearingStatus = hearingStatus;
	}
	
	


}
