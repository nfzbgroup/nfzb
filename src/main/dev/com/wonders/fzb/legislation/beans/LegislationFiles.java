package com.wonders.fzb.legislation.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * LEGISLATION_FILES Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_FILES")
public class LegislationFiles implements Serializable {

  public static final String LegislationFiles = "LEGISLATION_FILES";
	
  public LegislationFiles()
  {
  }

	/**
	 * ST_FORMAT
	 */
	@Column(name = "ST_FORMAT")
	private String stFormat;

	/**
	 * ST_FORMAT
	 */
	public String getStFormat(){
		return stFormat;
	}

	/**
	 * ST_FORMAT
	 */
	public void setStFormat (String stFormat){
		this.stFormat = stFormat;
	}

	/**
	 * ST_SAMPLE_ID
	 */
	@Column(name = "ST_SAMPLE_ID")
	private String stSampleId;

	/**
	 * ST_SAMPLE_ID
	 */
	public String getStSampleId(){
		return stSampleId;
	}

	/**
	 * ST_SAMPLE_ID
	 */
	public void setStSampleId (String stSampleId){
		this.stSampleId = stSampleId;
	}

	/**
	 * ST_FILE_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
	@Column(name = "ST_FILE_ID")
	private String stFileId;

	/**
	 * ST_FILE_ID
	 */
	public String getStFileId(){
		return stFileId;
	}

	/**
	 * ST_FILE_ID
	 */
	public void setStFileId (String stFileId){
		this.stFileId = stFileId;
	}

	/**
	 * ST_OWNER_ID
	 */
	@Column(name = "ST_OWNER_ID")
	private String stOwnerId;

	/**
	 * ST_OWNER_ID
	 */
	public String getStOwnerId(){
		return stOwnerId;
	}

	/**
	 * ST_OWNER_ID
	 */
	public void setStOwnerId (String stOwnerId){
		this.stOwnerId = stOwnerId;
	}

	/**
	 * ST_NODE_STATUS
	 */
	@Column(name = "ST_NODE_STATUS")
	private String stNodeStatus;

	/**
	 * ST_NODE_STATUS
	 */
	public String getStNodeStatus(){
		return stNodeStatus;
	}

	/**
	 * ST_NODE_STATUS
	 */
	public void setStNodeStatus (String stNodeStatus){
		this.stNodeStatus = stNodeStatus;
	}

	/**
	 * ST_FILE_URL
	 */
	@Column(name = "ST_FILE_URL")
	private String stFileUrl;

	/**
	 * ST_FILE_URL
	 */
	public String getStFileUrl(){
		return stFileUrl;
	}

	/**
	 * ST_FILE_URL
	 */
	public void setStFileUrl (String stFileUrl){
		this.stFileUrl = stFileUrl;
	}

	/**
	 * ST_OWNER_NAME
	 */
	@Column(name = "ST_OWNER_NAME")
	private String stOwnerName;

	/**
	 * ST_OWNER_NAME
	 */
	public String getStOwnerName(){
		return stOwnerName;
	}

	/**
	 * ST_OWNER_NAME
	 */
	public void setStOwnerName (String stOwnerName){
		this.stOwnerName = stOwnerName;
	}

	/**
	 * ST_PARENT_ID
	 */
	@Column(name = "ST_PARENT_ID")
	private String stParentId;

	/**
	 * ST_PARENT_ID
	 */
	public String getStParentId(){
		return stParentId;
	}

	/**
	 * ST_PARENT_ID
	 */
	public void setStParentId (String stParentId){
		this.stParentId = stParentId;
	}

	/**
	 * ST_TITLE
	 */
	@Column(name = "ST_TITLE")
	private String stTitle;

	/**
	 * ST_TITLE
	 */
	public String getStTitle(){
		return stTitle;
	}

	/**
	 * ST_TITLE
	 */
	public void setStTitle (String stTitle){
		this.stTitle = stTitle;
	}

	/**
	 * ST_FILE_TYPE
	 */
	@Column(name = "ST_FILE_TYPE")
	private String stFileType;

	/**
	 * ST_FILE_TYPE
	 */
	public String getStFileType(){
		return stFileType;
	}

	/**
	 * ST_FILE_TYPE
	 */
	public void setStFileType (String stFileType){
		this.stFileType = stFileType;
	}

	/**
	 * BL_CONTENT
	 */
	@Column(name = "BL_CONTENT")
	private byte[] blContent;

	/**
	 * BL_CONTENT
	 */
	public byte[] getBlContent(){
		return blContent;
	}

	/**
	 * BL_CONTENT
	 */
	public void setBlContent (byte[] blContent){
		this.blContent = blContent;
	}

	/**
	 * DT_PUB_DATE
	 */
	@Column(name = "DT_PUB_DATE")
	private Date dtPubDate;

	/**
	 * DT_PUB_DATE
	 */
	public Date getDtPubDate(){
		return dtPubDate;
	}

	/**
	 * DT_PUB_DATE
	 */
	public void setDtPubDate (Date dtPubDate){
		this.dtPubDate = dtPubDate;
	}

	/**
	 * ST_NODE_ID
	 */
	@Column(name = "ST_NODE_ID")
	private String stNodeId;

	/**
	 * ST_NODE_ID
	 */
	public String getStNodeId(){
		return stNodeId;
	}

	/**
	 * ST_NODE_ID
	 */
	public void setStNodeId (String stNodeId){
		this.stNodeId = stNodeId;
	}

}