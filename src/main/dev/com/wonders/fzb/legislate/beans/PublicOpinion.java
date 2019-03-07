package com.wonders.fzb.legislate.beans;

import java.util.Date;

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
 * 意见表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_PUBLIC_OPINION")
public class PublicOpinion {

	/**
	 * 文件记录ID
	 */
	@Id
	@Column(name = "OPINION_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String opinionId;
	/**
	 * 草案id
	 */
	@ManyToOne
	@JoinColumn(name = "draft_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Draft draft;
	
	/**
	 * 经办部门
	 */
	@Column(name = "team_id")
	private String teamId;
	/**
	 * 经办部门
	 */
	@Column(name = "team_name")
	private String teamName;
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
	 * 意见征集开始时间
	 */
	@Column(name = "start_time")
	private Date startTime;
	/**
	 * 意见征集结束时间
	 */
	@Column(name = "end_time")
	private Date endTime;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 邮件数
	 */
	@Column(name = "email_count")
	private Integer emailCount;

	/**
	 * 网上民意数
	 */
	@Column(name = "web_count")
	private Integer webCount;
	/**
	 * 电话数
	 */
	@Column(name = "call_count")
	private Integer callCount;
	/**
	 * 信件数
	 */
	@Column(name = "letter_count")
	private Integer letterCount;
	/**
	 * 电话意见数
	 */
	@Column(name = "call_opinion_count")
	private Integer callOpinionCount;
	/**
	 * 邮件意见数
	 */
	@Column(name = "email_opinion_count")
	private Integer emailOpinionCount;
	/**
	 * 网上意见数
	 */
	@Column(name = "web_opinion_count")
	private Integer letterOpinionCount;
	/**
	 * 信件意见数
	 */
	@Column(name = "letter_opinion_count")
	private Integer webOpinionCount;
	/**
	 * 具体意见数
	 */
	@Column(name = "opinion_count")
	private Integer opinionCount;
	public String getOpinionId() {
		return opinionId;
	}
	public void setOpinionId(String opinionId) {
		this.opinionId = opinionId;
	}
	
	public Draft getDraft() {
		return draft;
	}
	public void setDraft(Draft draft) {
		this.draft = draft;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getEmailCount() {
		return emailCount;
	}
	public void setEmailCount(Integer emailCount) {
		this.emailCount = emailCount;
	}
	public Integer getWebCount() {
		return webCount;
	}
	public void setWebCount(Integer webCount) {
		this.webCount = webCount;
	}
	public Integer getCallCount() {
		return callCount;
	}
	public void setCallCount(Integer callCount) {
		this.callCount = callCount;
	}
	public Integer getLetterCount() {
		return letterCount;
	}
	public void setLetterCount(Integer letterCount) {
		this.letterCount = letterCount;
	}
	public Integer getCallOpinionCount() {
		return callOpinionCount;
	}
	public void setCallOpinionCount(Integer callOpinionCount) {
		this.callOpinionCount = callOpinionCount;
	}
	public Integer getEmailOpinionCount() {
		return emailOpinionCount;
	}
	public void setEmailOpinionCount(Integer emailOpinionCount) {
		this.emailOpinionCount = emailOpinionCount;
	}
	public Integer getLetterOpinionCount() {
		return letterOpinionCount;
	}
	public void setLetterOpinionCount(Integer letterOpinionCount) {
		this.letterOpinionCount = letterOpinionCount;
	}
	public Integer getWebOpinionCount() {
		return webOpinionCount;
	}
	public void setWebOpinionCount(Integer webOpinionCount) {
		this.webOpinionCount = webOpinionCount;
	}
	public Integer getOpinionCount() {
		return opinionCount;
	}
	public void setOpinionCount(Integer opinionCount) {
		this.opinionCount = opinionCount;
	}
	
}
