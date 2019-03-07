package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "WEGOV_LF_OPINION_ITEM")
public class OpinionItem {
	@Id
	@Column(name = "ITEM_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String itemId;
	
	@Column(name = "ITEM_TYPE")
	private String itemType;
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "TEAM_ID")
	private String teamId;
	@Column(name = "TEAM_NAME")
	private String teamName;
	@Column(name = "INSTRUCTIONS")
	private String instuctions;
	@Column(name = "OUT_ID")
	private String outId;
	@Column(name = "CREATE_TIME")
	private Date creanTime;
	@Column(name = "ACTIVITY_TYPE")
	private String activityType;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getInstuctions() {
		return instuctions;
	}
	public void setInstuctions(String instuctions) {
		this.instuctions = instuctions;
	}
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	public Date getCreanTime() {
		return creanTime;
	}
	public void setCreanTime(Date creanTime) {
		this.creanTime = creanTime;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	
}
