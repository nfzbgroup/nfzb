package com.wonders.fzb.legislate.beans;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 规章草案
 * 
 * @author FZB
 * 
 */

@Entity
@Table(name = "WEGOV_LF_DRAFT")
public class Draft {
	public Draft() {
		this.draftId = UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 草案ID
	 */
	@Id
	@Column(name = "draft_id", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id") 
	private String draftId;
	
	/**
	 * 草案名称
	 */
	@Column(name = "draft_name")
	private String draftName;
	
	/**
	 * 发起人
	 */
	@Column(name = "creator_id")
	private String creatorId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 最后操作人
	 */
	@Column(name = "update_id")
	private String updateId;
	
	/**
	 * 处理时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	
	/**
	 * 上报单位
	 */
	@Column(name = "team_id")
	private String teamId;
	
	/**
	 * 上报单位
	 */
	@Column(name = "team_name")
	private String teamName;
	
	/**
	 * 填写说明
	 */
	@Column(name = "instructions")
	private String instructions;
	
	/**
	 * 状态
	 */
	@Column(name = "status")
	private String status;
	
	/**
	 * 分办单
	 */
	@ManyToOne()
	@JoinColumn(name = "fbd_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Fbd fbd;

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
	
	@Column(name = "REASON_DESC")
	private String reasonDesc;
	
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

	
	
	
	public String getDraftId() {
		return draftId;
	}

	public void setDraftId(String draftId) {
		this.draftId = draftId;
	}

	public String getDraftName() {
		return draftName;
	}

	public void setDraftName(String draftName) {
		this.draftName = draftName;
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

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Fbd getFbd() {
		return fbd;
	}

	public void setFbd(Fbd fbd) {
		this.fbd = fbd;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

}
