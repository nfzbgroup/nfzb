package com.wonders.fzb.legislate.beans;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 文件记录表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_FILE_RECORD")
public class FileRecord {
	
	public FileRecord() {
		this.fileRecordId = UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 文件记录ID
	 */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id") 
	@Column(name = "file_record_id", unique = true, nullable = false, length = 32)
	private String fileRecordId;
	
	/**
	 * 业务类型
	 */
	@Column(name="biz_type")
	private String bizType;
	
	/**
	 * 文件名称
	 */
	@Column(name="file_name")
	private String fileName;
	
	/**
	 * 文件内容
	 */
	@Column(name="file_content")
	private byte[] fileContent;
	
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
	 * 创建人姓名
	 */
	@Column(name = "CREATOR_NAME")
	private String creatorName;
	
	/**
	 * 外键
	 */
	@Column(name = "out_id")
	private String outId;

	/**
	 * 环节类型
	 */
	@Column(name = "ACTIVITY_TYPE")
	private String activityType;
	
	@Column(name = "REMARKS")
	private String remarks;

	
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getFileRecordId() {
		return fileRecordId;
	}
	public void setFileRecordId(String fileRecordId) {
		this.fileRecordId = fileRecordId;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
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

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
