package com.wonders.fzb.legislate.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 规章草案
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_DRAFT_DEAL")
public class DraftDeal {
	/**
	 * 编号
	 */
	@Id
	@Column(name = "deal_id", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String dealId;
	
	/**
	 * 草案编号
	 */
	@Column(name = "draft_id")
	private String draftId;
	
	/**
	 * 经办人
	 */
	@Column(name = "user_id")
	private String userId;
	/**
	 * 经办人姓名
	 */
	@Column(name = "user_name")
	private String userName;
	
	/**
	 * 经办部门
	 */
	@Column(name = "team_id")
	private String teamId;
	
	/**
	 * 经办时间
	 */
	@Column(name = "create_time")
	private String createTime;
	
	/**
	 * 办理说明
	 */
	@Column(name = "instructions")
	private String instructions;

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getDraftId() {
		return draftId;
	}

	public void setDraftId(String draftId) {
		this.draftId = draftId;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	
}
