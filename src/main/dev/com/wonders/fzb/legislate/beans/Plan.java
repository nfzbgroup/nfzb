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
 * 立法计划表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_PLAN")
public class Plan {
	public Plan() {
		this.planId = UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 立法计划ID
	 */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id") 
	@Column(name = "PLAN_ID", unique = true, nullable = false, length = 32)
	private String planId;

	/**
	 * 计划名称
	 */
	@Column(name = "PLAN_NAME")
	private String planName;
	/**
	 * 计划类型
	 */
	@Column(name = "PLAN_TYPE")
	private String planType;
	/**
	 * 项目类型
	 */
	@Column(name = "PROJECT_TYPE")
	private String projectType;
	
	/**
	 * 创建人
	 */
	@Column(name = "CREATOR_ID")
	private String creatorId;	
	/**
	 * 所属部门
	 */
	@Column(name = "TEAM_ID")
	private String teamId;
	/**
	 * 部门名称
	 */
	@Column(name = "TEAM_NAME")
	private String teamName;
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
	/**
	 * 状态
	 */
	@Column(name = "STATUS")
	private String status;
	/**
	 * 立项理由
	 */
	@Column(name = "REASON")
	private String reason;
	/**
	 * 目前进展情况
	 */
	@Column(name = "PROGRESS")
	private String progress;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "REASON_DESC")
	private String reasonDesc;
	
	/**
	 * 分办单
	 */
	@ManyToOne()
	@JoinColumn(name = "fbd_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Fbd fbd;
	
	
	@ManyToOne()
	@JoinColumn(name = "SUMMARY_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private PlanSummary planSummary;
	
	@Column(name = "advice")
	private String advice;
	
	@Column(name = "LEX_SUPERIOR")
	private String lexSuperior;
	
	@Column(name = "CONTACTS")
	private String contacts;
	
	@Column(name = "TELEPHONE")
	private String telephone;
	
	@Column(name = "SEND_YEAR")
	private String sendYear;
	
	@Column(name = "SEND_MONTH")
	private String sendMonth;
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Fbd getFbd() {
		return fbd;
	}
	public void setFbd(Fbd fbd) {
		this.fbd = fbd;
	}
	public PlanSummary getPlanSummary() {
		return planSummary;
	}
	public void setPlanSummary(PlanSummary planSummary) {
		this.planSummary = planSummary;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getLexSuperior() {
		return lexSuperior;
	}
	public void setLexSuperior(String lexSuperior) {
		this.lexSuperior = lexSuperior;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSendYear() {
		return sendYear;
	}
	public void setSendYear(String sendYear) {
		this.sendYear = sendYear;
	}
	public String getSendMonth() {
		return sendMonth;
	}
	public void setSendMonth(String sendMonth) {
		this.sendMonth = sendMonth;
	}
	public String getReasonDesc() {
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
	
}
