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
@Table(name = "ASSESSMENT_REPORT_DIS")
public class AssessmentReportDis implements Serializable{
	/**
     * 主键
     */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
    @Column(name = "DISTRIBUTE_ID")
    private String distributeId;
    
	/**
     * 分送名称
     */
    @Column(name = "DISTRIBUTE_TITLE")
    private String distributeTitle;
    
    /**
     * 分送单位、部门Id
     */
    @Column(name = "DISTRIBUTE_UNIT")
    private String distributeUnit;
    
    /**
     * 分送单位、部门名称
     */
    @Column(name = "DISTRIBUTE_UNIT_NAME")
    private String distributeUnitName;
    
    /**
     * 接收单位、部门ID
     */
    @Column(name = "RECEIVE_UNIT")
    private String receiveUnit;
    
    /**
     * 接收单位、部门名称
     */
    @Column(name = "RECEIVE_UNIT_NAME")
    private String receiveUnitName;
    
    /**
     * 分送开始时间
     */
    @Column(name = "INSERT_DATE")
    private Date insertDate;
    
    /**
     * 接收时间
     */
    @Column(name = "RECEIVE_DATE")
    private Date receiveDate;
    
    /**
     * 反馈信息
     */
    @Column(name = "DISTRIBUTE_FEEDBACK")
    private String distributeFeedback;
    
    /**
     * 报告Id
     */
    @Column(name = "ASSESSMENT_ID")
    private String assessmentId;
    
    /**
     * 备注
     */
    @Column(name = "MEMO")
    private String memo;

	public String getDistributeId() {
		return distributeId;
	}

	public void setDistributeId(String distributeId) {
		this.distributeId = distributeId;
	}

	public String getDistributeTitle() {
		return distributeTitle;
	}

	public void setDistributeTitle(String distributeTitle) {
		this.distributeTitle = distributeTitle;
	}

	public String getDistributeUnit() {
		return distributeUnit;
	}

	public void setDistributeUnit(String distributeUnit) {
		this.distributeUnit = distributeUnit;
	}

	public String getDistributeUnitName() {
		return distributeUnitName;
	}

	public void setDistributeUnitName(String distributeUnitName) {
		this.distributeUnitName = distributeUnitName;
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

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getDistributeFeedback() {
		return distributeFeedback;
	}

	public void setDistributeFeedback(String distributeFeedback) {
		this.distributeFeedback = distributeFeedback;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
