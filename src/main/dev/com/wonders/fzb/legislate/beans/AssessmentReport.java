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
@Table(name = "ASSESSMENT_REPORT")
public class AssessmentReport implements Serializable{
	/**
     * 主键
     */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
    @Column(name = "ASSESSMENT_ID")
    private String assessmentId;
    
	/**
     * 规章立法后评估报告名称
     */
    @Column(name = "ASSESSMENT_TITLE")
    private String assessmentTitle;
    
	/**
     * 公开等级：PUBLIC(完全公开)、PROTECTED(部分公开)、PRIVATE(不公开)
     */
    @Column(name = "SECRET_LEVEL")
    private String secretLevel;
    
	/**
     * 规章立法后评估报告年度
     */
    @Column(name = "YEAR")
    private String year;
    
	/**
     * 后评估报告创建人ID
     */
    @Column(name = "AGENT_ID")
    private String agentId;
    
	/**
     * 后评估报告创建人姓名
     */
    @Column(name = "AGENT_NAME")
    private String agentName;
    
	/**
     * 后评估报告单位
     */
    @Column(name = "AGENT_UNIT")
    private String agentUnit;
    
	/**
     * 后评估报告单位名称
     */
    @Column(name = "AGENT_UNIT_NAME")
    private String agentUnitName;
    
	/**
     * 创建后评估报告时间
     */
    @Column(name = "INSERT_DATE")
    private Date insertDate;
    
	/**
     * 后评估接收单位ID，现在是U0即监督处接收，可能存在扩展
     */
    @Column(name = "RECEIVE_UNIT")
    private String receiveUnit;
    
	/**
     * 后评估报告接收单位名称，单位ID对应的中文名称
     */
    @Column(name = "RECEIVE_UNIT_NAME")
    private String receiveUnitName;
    
	/**
     * 是否接收（监督处有一个人接收都算接收完成）Y/N
     */
    @Column(name = "RECEIVE")
    private String receive;
    
	/**
     * 后评估接收时间，记录第一个人接收的时间
     */
    @Column(name = "RECEIVE_DATE")
    private Date receiveDate;
    
	/**
     * 后评估上传附件名称，下载链接不需要查附件表就能提供附件名称
     */
    @Column(name = "ABSTRACT_FILE_NAME")
    private String abstractFileName;
    
	/**
     * 后评估上传附件名称，下载链接不需要查附件表就能提供附件名称
     */
    @Column(name = "REPORT_FILE_NAME")
    private String reportFileName;
    
	/**
     * 后评估上传附件名称，下载链接不需要查附件表就能提供附件名称
     */
    @Column(name = "PRIVATE_FILE_NAME")
    private String privateFileName;
    
	/**
     * 后评估上传附件名称，下载链接不需要查附件表就能提供附件名称
     */
    @Column(name = "PROTECT_FILE_NAME")
    private String protectFileName;
    
	/**
     * 后评估上传附件名称，下载链接不需要查附件表就能提供附件名称
     */
    @Column(name = "PART_FILE_NAME")
    private String partFileName;

	/**
     * 后评估报告也可以富文本形式展示内容
     */
    @Column(name = "ASSESSMENT_CONTENT")
    private String assessmentContent;
    
	/**
     * 外键，关联后评估申报项目
     */
    @Column(name = "ASSESSMENT_PRO_ID")
    private String assessmentProId;
    
	/**
     * 备用
     */
    @Column(name = "MEMO")
    private String memo;

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getAssessmentTitle() {
		return assessmentTitle;
	}

	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getReceiveUnit() {
		return receiveUnit;
	}

	public void setReceiveUnit(String receiveUnit) {
		this.receiveUnit = receiveUnit;
	}

	public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getAbstractFileName() {
		return abstractFileName;
	}

	public void setAbstractFileName(String abstractFileName) {
		this.abstractFileName = abstractFileName;
	}

	public String getAssessmentContent() {
		return assessmentContent;
	}

	public void setAssessmentContent(String assessmentContent) {
		this.assessmentContent = assessmentContent;
	}

	public String getAssessmentProId() {
		return assessmentProId;
	}

	public void setAssessmentProId(String assessmentProId) {
		this.assessmentProId = assessmentProId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAgentUnit() {
		return agentUnit;
	}

	public void setAgentUnit(String agentUnit) {
		this.agentUnit = agentUnit;
	}

	public String getAgentUnitName() {
		return agentUnitName;
	}

	public void setAgentUnitName(String agentUnitName) {
		this.agentUnitName = agentUnitName;
	}

	public String getSecretLevel() {
		return secretLevel;
	}

	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	public String getPrivateFileName() {
		return privateFileName;
	}

	public void setPrivateFileName(String excuseFileName) {
		this.privateFileName = excuseFileName;
	}

	public String getProtectFileName() {
		return protectFileName;
	}

	public void setProtectFileName(String protectFileName) {
		this.protectFileName = protectFileName;
	}
    
	public String getPartFileName() {
		return partFileName;
	}

	public void setPartFileName(String partFileName) {
		this.partFileName = partFileName;
	}
}
