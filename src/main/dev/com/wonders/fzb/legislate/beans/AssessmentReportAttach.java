package com.wonders.fzb.legislate.beans;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "ASSESSMENT_REPORT_ATTACH")
public class AssessmentReportAttach implements Serializable{
	/**
     * 主键
     */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
    @Column(name = "ASSESSMENT_ATTACH_ID")
    private String assessmentAttachId;
    
	/**
     * 附件名称
     */
    @Column(name = "ASSESSMENT_ATTACH_NAME")
    private String assessmentAttachName;
    
	/**
     * 附件
     */
    @Column(name = "ASSESSMENT_ATTACH")
    private byte[] assessmentAttach;
    
	/**
     * 上传用户ID
     */
    @Column(name = "AGENT_ID")
    private String agentId;
    
	/**
     * 上传用户名称
     */
    @Column(name = "AGENT_NAME")
    private String agentName;
    
	/**
     * 附件类型
     */
    @Column(name = "ASSESSMENT_ATTACH_TYPE")
    private String attachType;
    
	/**
     * 报告Id
     */
    @Column(name = "ASSESSMENT_ID")
    private String assessmentId;
    

	/**
     * 附件名称
     */
    @Column(name = "INSERT_DATE")
    private Date insertDate;
    
	/**
     * 附件名称
     */
    @Column(name = "ASSESSMENT_ATTACH_INSTRACTION")
    private String assessmentAttachInstraction;
    
	/**
     * 附件名称
     */
    @Column(name = "MEMO")
    private String memo;
    
	public String getAssessmentAttachId() {
		return assessmentAttachId;
	}

	public void setAssessmentAttachId(String assessmentAttachId) {
		this.assessmentAttachId = assessmentAttachId;
	}

	public String getAssessmentAttachName() {
		return assessmentAttachName;
	}

	public void setAssessmentAttachName(String assessmentAttachName) {
		this.assessmentAttachName = assessmentAttachName;
	}

	public byte[] getAssessmentAttach() {
		return assessmentAttach;
	}

	public void setAssessmentAttach(byte[] assessmentAttach) {
		this.assessmentAttach = assessmentAttach;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAttachType() {
		return attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAssessmentAttachInstraction() {
		return assessmentAttachInstraction;
	}

	public void setAssessmentAttachInstraction(String assessmentAttachInstraction) {
		this.assessmentAttachInstraction = assessmentAttachInstraction;
	}
}
