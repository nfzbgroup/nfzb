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
@Table(name = "ASSESSMENT_DIS")
public class AssessmentDis implements Serializable{
	/**
     * 主键
     */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
    @Column(name = "ASSESSMENT_DIS_ID")
    private String assessmentDisId;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_TITLE")
    private String assessmentTitle;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_DIS_UNIT")
    private String assessmentDisUnit;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_DIS_UNIT_NAME")
    private String assessmentDisUnitName;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_RECEIVE_UNIT")
    private String assessmentReceiveUnit;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_RECEIVE_UNIT_NAME")
    private String assessmentReceiveUnitName;
    
	/**
     * 
     */
    @Column(name = "INSERT_DATE")
    private Date insertDate;
    
	/**
     * 
     */
    @Column(name = "RECEIVE_DATE")
    private Date receiveDate;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_ID")
    private String assessmentId;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_MEMO")
    private String assessmentMemo;

	public String getAssessmentDisId() {
		return assessmentDisId;
	}

	public void setAssessmentDisId(String assessmentDisId) {
		this.assessmentDisId = assessmentDisId;
	}

	public String getAssessmentTitle() {
		return assessmentTitle;
	}

	public void setAssessmentTitle(String assessmentTitle) {
		this.assessmentTitle = assessmentTitle;
	}

	public String getAssessmentDisUnit() {
		return assessmentDisUnit;
	}

	public void setAssessmentDisUnit(String assessmentDisUnit) {
		this.assessmentDisUnit = assessmentDisUnit;
	}

	public String getAssessmentDisUnitName() {
		return assessmentDisUnitName;
	}

	public void setAssessmentDisUnitName(String assessmentDisUnitName) {
		this.assessmentDisUnitName = assessmentDisUnitName;
	}

	public String getAssessmentReceiveUnit() {
		return assessmentReceiveUnit;
	}

	public void setAssessmentReceiveUnit(String assessmentReceiveUnit) {
		this.assessmentReceiveUnit = assessmentReceiveUnit;
	}

	public String getAssessmentReceiveUnitName() {
		return assessmentReceiveUnitName;
	}

	public void setAssessmentReceiveUnitName(String assessmentReceiveUnitName) {
		this.assessmentReceiveUnitName = assessmentReceiveUnitName;
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

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getAssessmentMemo() {
		return assessmentMemo;
	}

	public void setAssessmentMemo(String assessmentMemo) {
		this.assessmentMemo = assessmentMemo;
	}

}
