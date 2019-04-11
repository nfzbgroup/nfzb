package com.wonders.fzb.checkmeeting.beans;

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
 * LEGISLATION_CHECKMEETING_ITEM Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_CHECKMEETING_ITEM")
public class LegislationCheckmeetingItem implements Serializable {

  public static final String LegislationCheckmeetingItem = "LEGISLATION_CHECKMEETING_ITEM";
	
  public LegislationCheckmeetingItem()
  {
  }

	/**
	 * ST_SOURCE_ID
	 */
	@Column(name = "ST_SOURCE_ID")
	private String stSourceId;

	/**
	 * ST_SOURCE_ID
	 */
	public String getStSourceId(){
		return stSourceId;
	}

	/**
	 * ST_SOURCE_ID
	 */
	public void setStSourceId (String stSourceId){
		this.stSourceId = stSourceId;
	}

	/**
	 * ST_TYPE_NAME
	 */
	@Column(name = "ST_TYPE_NAME")
	private String stTypeName;

	/**
	 * ST_TYPE_NAME
	 */
	public String getStTypeName(){
		return stTypeName;
	}

	/**
	 * ST_TYPE_NAME
	 */
	public void setStTypeName (String stTypeName){
		this.stTypeName = stTypeName;
	}

	/**
	 * ST_STATUS
	 */
	@Column(name = "ST_STATUS")
	private String stStatus;

	/**
	 * ST_STATUS
	 */
	public String getStStatus(){
		return stStatus;
	}

	/**
	 * ST_STATUS
	 */
	public void setStStatus (String stStatus){
		this.stStatus = stStatus;
	}

	/**
	 * ST_TYPE_ID
	 */
	@Column(name = "ST_TYPE_ID")
	private String stTypeId;

	/**
	 * ST_TYPE_ID
	 */
	public String getStTypeId(){
		return stTypeId;
	}

	/**
	 * ST_TYPE_ID
	 */
	public void setStTypeId (String stTypeId){
		this.stTypeId = stTypeId;
	}

	/**
	 * ST_ITEM_NAME
	 */
	@Column(name = "ST_ITEM_NAME")
	private String stItemName;

	/**
	 * ST_ITEM_NAME
	 */
	public String getStItemName(){
		return stItemName;
	}

	/**
	 * ST_ITEM_NAME
	 */
	public void setStItemName (String stItemName){
		this.stItemName = stItemName;
	}

	/**
	 * DT_CREATE_DATE
	 */
	@Column(name = "DT_CREATE_DATE")
	private Date dtCreateDate;

	/**
	 * DT_CREATE_DATE
	 */
	public Date getDtCreateDate(){
		return dtCreateDate;
	}

	/**
	 * DT_CREATE_DATE
	 */
	public void setDtCreateDate (Date dtCreateDate){
		this.dtCreateDate = dtCreateDate;
	}

	/**
	 * ST_USER_NAME
	 */
	@Column(name = "ST_USER_NAME")
	private String stUserName;

	/**
	 * ST_USER_NAME
	 */
	public String getStUserName(){
		return stUserName;
	}

	/**
	 * ST_USER_NAME
	 */
	public void setStUserName (String stUserName){
		this.stUserName = stUserName;
	}

	/**
	 * ST_MEETING_ID
	 */
	@Column(name = "ST_MEETING_ID")
	private String stMeetingId;

	/**
	 * ST_MEETING_ID
	 */
	public String getStMeetingId(){
		return stMeetingId;
	}

	/**
	 * ST_MEETING_ID
	 */
	public void setStMeetingId (String stMeetingId){
		this.stMeetingId = stMeetingId;
	}

	/**
	 * ST_ITEM_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
	@Column(name = "ST_ITEM_ID")
	private String stItemId;

	/**
	 * ST_ITEM_ID
	 */
	public String getStItemId(){
		return stItemId;
	}

	/**
	 * ST_ITEM_ID
	 */
	public void setStItemId (String stItemId){
		this.stItemId = stItemId;
	}

	/**
	 * ST_USER_ID
	 */
	@Column(name = "ST_USER_ID")
	private String stUserId;

	/**
	 * ST_USER_ID
	 */
	public String getStUserId(){
		return stUserId;
	}

	/**
	 * ST_USER_ID
	 */
	public void setStUserId (String stUserId){
		this.stUserId = stUserId;
	}

	/**
	 * ST_CONTENT
	 */
	@Column(name = "ST_CONTENT")
	private String stContent;

	/**
	 * ST_CONTENT
	 */
	public String getStContent(){
		return stContent;
	}

	/**
	 * ST_CONTENT
	 */
	public void setStContent (String stContent){
		this.stContent = stContent;
	}

}