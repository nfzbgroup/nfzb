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
@Table(name = "ASSESSMENT")
public class Assessment implements Serializable{
	
	/**
     * 主键
     */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
    @Column(name = "ASSESSMENT_ID")
    private String assessmentId;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_TITLE")
    private String assessmentTitle;
    
	/**
     * 
     */
    @Column(name = "YEAR")
    private String year;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_CONTENT")
    private String assessmentContent;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_FILE_NAME")
    private String assessmentFileName;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_AGENT_NAME")
    private String assessmentAgentName;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_AGENT_PHONE")
    private String assessmentAgentPhone;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_UNIT")
    private String assessmentUnit;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_UNIT_NAME")
    private String assessmentUnitName;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_OTHERS")
    private String assessmentOthers;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_MEMO")
    private String assessmentMemo;
    
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
    @Column(name = "RECEIVE")
    private String receive;
    
	/**
     * 
     */
    @Column(name = "RECEIVE_UNIT")
    private String receiveUnit;
    
	/**
     * 
     */
    @Column(name = "RECEIVE_UNIT_NAME")
    private String receiveUnitName;

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

	public String getAssessmentContent() {
		return assessmentContent;
	}

	public void setAssessmentContent(String assessmentContent) {
		this.assessmentContent = assessmentContent;
	}

	public String getAssessmentFileName() {
		return assessmentFileName;
	}

	public void setAssessmentFileName(String assessmentFileName) {
		this.assessmentFileName = assessmentFileName;
	}

	public String getAssessmentAgentName() {
		return assessmentAgentName;
	}

	public void setAssessmentAgentName(String assessmentAgentName) {
		this.assessmentAgentName = assessmentAgentName;
	}

	public String getAssessmentAgentPhone() {
		return assessmentAgentPhone;
	}

	public void setAssessmentAgentPhone(String assessmentAgentPhone) {
		this.assessmentAgentPhone = assessmentAgentPhone;
	}

	public String getAssessmentUnit() {
		return assessmentUnit;
	}

	public void setAssessmentUnit(String assessmentUnit) {
		this.assessmentUnit = assessmentUnit;
	}

	public String getAssessmentUnitName() {
		return assessmentUnitName;
	}

	public void setAssessmentUnitName(String assessmentUnitName) {
		this.assessmentUnitName = assessmentUnitName;
	}

	public String getAssessmentOthers() {
		return assessmentOthers;
	}

	public void setAssessmentOthers(String assessmentOthers) {
		this.assessmentOthers = assessmentOthers;
	}

	public String getAssessmentMemo() {
		return assessmentMemo;
	}

	public void setAssessmentMemo(String assessmentMemo) {
		this.assessmentMemo = assessmentMemo;
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

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
