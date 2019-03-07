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
 * 单位意见
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_TEAM_OPINION")
public class TeamOpinion {
	/**
	 * 单位意见ID
	 */
	@Id
	@Column(name = "TEAM_OPINION_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String teamOpinionId;
	@ManyToOne(fetch =FetchType.LAZY )
	@JoinColumn(name = "draft_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Draft draft;
	
	@Column(name = "OPINION_STATUS")
	private String opinionStatus;
	/**
	 * 单位意见意见类型
	 */
	@Column(name = "OPINION_TYPE")
	private String opinionType;
	/**
	 * 单位意见意见渠道
	 */
	@Column(name = "OPINION_CHANNEL")
	private String opinionChannel;
	/**
	 * 单位意见意见信息
	 */
	@Column(name = "OPINION_INFO")
	private String opinionInfo;
	
	/**
	 * 内容
	 */
	@Column(name = "OPINION_BLOB")
	private byte[] opinionBlob;
	
	/**
	 * 单位意见意见
	 */
	@Column(name = "OPINION_NAME")
	private String opinionName;
	/**
	 * 单位意见意见部门名单
	 */
	@Column(name = "OPINION_TEAM_LIST")
	private String opinionTeamList;
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
	 * 
	 */
	@Column(name = "TEAM_ID")
	private String teamId;
	/**
	 *
	 */
	@Column(name = "TEAM_NAME")
	private String teamName;
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
	public String getTeamOpinionId() {
		return teamOpinionId;
	}
	public void setTeamOpinionId(String teamOpinionId) {
		this.teamOpinionId = teamOpinionId;
	}
	public Draft getDraft() {
		return draft;
	}
	public void setDraft(Draft draft) {
		this.draft = draft;
	}
	public String getOpinionType() {
		return opinionType;
	}
	public void setOpinionType(String opinionType) {
		this.opinionType = opinionType;
	}
	public String getOpinionChannel() {
		return opinionChannel;
	}
	public void setOpinionChannel(String opinionChannel) {
		this.opinionChannel = opinionChannel;
	}
	public String getOpinionInfo() {
		return opinionInfo;
	}
	public void setOpinionInfo(String opinionInfo) {
		this.opinionInfo = opinionInfo;
	}
	public String getOpinionTeamList() {
		return opinionTeamList;
	}
	public void setOpinionTeamList(String opinionTeamList) {
		this.opinionTeamList = opinionTeamList;
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
	public String getOpinionName() {
		return opinionName;
	}
	public void setOpinionName(String opinionName) {
		this.opinionName = opinionName;
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
	public byte[] getOpinionBlob() {
		return opinionBlob;
	}
	public void setOpinionBlob(byte[] opinionBlob) {
		this.opinionBlob = opinionBlob;
	}
	public String getOpinionStatus() {
		return opinionStatus;
	}
	public void setOpinionStatus(String opinionStatus) {
		this.opinionStatus = opinionStatus;
	}
	
}
