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
@Table(name = "ASSESSMENT_ATTACH")
public class AssessmentAttach implements Serializable{
	/**
     * 主键
     */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
    @Column(name = "ASSESSMENT_ATTACH_ID")
    private String assessmentAttachId;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_ATTACH_NAME")
    private String assessmentAttachName;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_ATTACH_FILE")
    private byte[] assessmentAttachFile;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_ID")
    private String assessmentId;
    
	/**
     * 
     */
    @Column(name = "ASSESSMENT_ATTACH_MEMO")
    private String assessmentAttachMemo;
    
	/**
     * 
     */
    @Column(name = "INSERT_DATE")
    private Date insertDate;

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

	public byte[] getAssessmentAttachFile() {
		return assessmentAttachFile;
	}

	public void setAssessmentAttachFile(byte[] assessmentAttachFile) {
		this.assessmentAttachFile = assessmentAttachFile;
	}

	public String getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getAssessmentAttachMemo() {
		return assessmentAttachMemo;
	}

	public void setAssessmentAttachMemo(String assessmentAttachMemo) {
		this.assessmentAttachMemo = assessmentAttachMemo;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

}
