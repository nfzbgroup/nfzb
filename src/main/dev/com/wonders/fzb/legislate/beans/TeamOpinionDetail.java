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
 * 立法计划表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_TEAM_OPINION_DETAIL")
public class TeamOpinionDetail {
	/**
	 * 单位意见详细表
	 */
	@Id
	@Column(name = "TEAM_OPINION_DETAIL_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String teamOpinionDetailId;

	
	
	@ManyToOne(fetch =FetchType.LAZY )
	@JoinColumn(name = "TEAM_OPINION_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private TeamOpinion teamOpinion;
	
	/**
	 * 部门id
	 */
	@Column(name = "TEAM_ID")
	private String teamId;
	/**
	 * 部门名称
	 */
	@Column(name = "TEAM_NAME")
	private String teamName;
	
	/**
	 * 意见信息
	 */
	@Column(name = "OPINION_INFO")
	private String opinionInfo;	
	
	/**
	 * 内容
	 */
	@Column(name = "OPINION_BLOB")
	private byte[] opinionBlob;
	/**
	 * 意见状态
	 */
	@Column(name = "OPINION_STATUS")
	private String opinionStatus;
	
	/**
	 * 接收时间
	 */
	@Column(name = "RECEIVE_TIME")
	private Date receiveTime;
	
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
	public String getTeamOpinionDetailId() {
		return teamOpinionDetailId;
	}
	public void setTeamOpinionDetailId(String teamOpinionDetailId) {
		this.teamOpinionDetailId = teamOpinionDetailId;
	}


	public TeamOpinion getTeamOpinion() {
		return teamOpinion;
	}
	public void setTeamOpinion(TeamOpinion teamOpinion) {
		this.teamOpinion = teamOpinion;
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
	public String getOpinionInfo() {
		return opinionInfo;
	}
	public void setOpinionInfo(String opinionInfo) {
		this.opinionInfo = opinionInfo;
	}
	public String getOpinionStatus() {
		return opinionStatus;
	}
	public void setOpinionStatus(String opinionStatus) {
		this.opinionStatus = opinionStatus;
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
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public byte[] getOpinionBlob() {
		return opinionBlob;
	}
	public void setOpinionBlob(byte[] opinionBlob) {
		this.opinionBlob = opinionBlob;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	
}
