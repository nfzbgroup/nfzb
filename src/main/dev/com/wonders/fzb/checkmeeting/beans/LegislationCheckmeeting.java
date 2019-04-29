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
 * LEGISLATION_CHECKMEETING Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_CHECKMEETING")
public class LegislationCheckmeeting implements Serializable {

  public static final String LegislationCheckmeeting = "LEGISLATION_CHECKMEETING";
	
  public LegislationCheckmeeting()
  {
  }

	/**
	 * ST_ADDRESS
	 */
	@Column(name = "ST_ADDRESS")
	private String stAddress;

	/**
	 * ST_ADDRESS
	 */
	public String getStAddress(){
		return stAddress;
	}

	/**
	 * ST_ADDRESS
	 */
	public void setStAddress (String stAddress){
		this.stAddress = stAddress;
	}

	/**
	 * ST_TYPE
	 */
	@Column(name = "ST_TYPE")
	private String stType;

	/**
	 * ST_TYPE
	 */
	public String getStType(){
		return stType;
	}

	/**
	 * ST_TYPE
	 */
	public void setStType (String stType){
		this.stType = stType;
	}

	/**
	 * ST_DOC_SOURCE
	 */
	@Column(name = "ST_DOC_SOURCE")
	private String stDocSource;

	/**
	 * ST_DOC_SOURCE
	 */
	public String getStDocSource(){
		return stDocSource;
	}

	/**
	 * ST_DOC_SOURCE
	 */
	public void setStDocSource (String stDocSource){
		this.stDocSource = stDocSource;
	}

	/**
	 * ST_CLEAN_SOURCE
	 */
	@Column(name = "ST_CLEAN_SOURCE")
	private String stCleanSource;

	/**
	 * ST_CLEAN_SOURCE
	 */
	public String getStCleanSource(){
		return stCleanSource;
	}

	/**
	 * ST_CLEAN_SOURCE
	 */
	public void setStCleanSource (String stCleanSource){
		this.stCleanSource = stCleanSource;
	}

	/**
	 * ST_ASSES_SOURCE
	 */
	@Column(name = "ST_ASSES_SOURCE")
	private String stAssesSource;

	/**
	 * ST_ASSES_SOURCE
	 */
	public String getStAssesSource(){
		return stAssesSource;
	}

	/**
	 * ST_ASSES_SOURCE
	 */
	public void setStAssesSource (String stAssesSource){
		this.stAssesSource = stAssesSource;
	}

	/**
	 * ST_NODE_NAME
	 */
	@Column(name = "ST_NODE_NAME")
	private String stNodeName;

	/**
	 * ST_NODE_NAME
	 */
	public String getStNodeName(){
		return stNodeName;
	}

	/**
	 * ST_NODE_NAME
	 */
	public void setStNodeName (String stNodeName){
		this.stNodeName = stNodeName;
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
	 * ST_PERSONS
	 */
	@Column(name = "ST_PERSONS")
	private String stPersons;

	/**
	 * ST_PERSONS
	 */
	public String getStPersons(){
		return stPersons;
	}

	/**
	 * ST_PERSONS
	 */
	public void setStPersons (String stPersons){
		this.stPersons = stPersons;
	}

	/**
	 * ST_MEETING_NAME
	 */
	@Column(name = "ST_MEETING_NAME")
	private String stMeetingName;

	/**
	 * ST_MEETING_NAME
	 */
	public String getStMeetingName(){
		return stMeetingName;
	}

	/**
	 * ST_MEETING_NAME
	 */
	public void setStMeetingName (String stMeetingName){
		this.stMeetingName = stMeetingName;
	}

	/**
	 * ST_UNIT_ID
	 */
	@Column(name = "ST_UNIT_ID")
	private String stUnitId;

	/**
	 * ST_UNIT_ID
	 */
	public String getStUnitId(){
		return stUnitId;
	}

	/**
	 * ST_UNIT_ID
	 */
	public void setStUnitId (String stUnitId){
		this.stUnitId = stUnitId;
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
	 * ST_PLAN_SOURCE
	 */
	@Column(name = "ST_PLAN_SOURCE")
	private String stPlanSource;

	/**
	 * ST_PLAN_SOURCE
	 */
	public String getStPlanSource(){
		return stPlanSource;
	}

	/**
	 * ST_PLAN_SOURCE
	 */
	public void setStPlanSource (String stPlanSource){
		this.stPlanSource = stPlanSource;
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
	 * DT_BEGIN_DATE
	 */
	@Column(name = "DT_BEGIN_DATE")
	private Date dtBeginDate;

	/**
	 * DT_BEGIN_DATE
	 */
	public Date getDtBeginDate(){
		return dtBeginDate;
	}

	/**
	 * DT_BEGIN_DATE
	 */
	public void setDtBeginDate (Date dtBeginDate){
		this.dtBeginDate = dtBeginDate;
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
	 * DT_END_DATE
	 */
	@Column(name = "DT_END_DATE")
	private Date dtEndDate;

	/**
	 * DT_END_DATE
	 */
	public Date getDtEndDate(){
		return dtEndDate;
	}

	/**
	 * DT_END_DATE
	 */
	public void setDtEndDate (Date dtEndDate){
		this.dtEndDate = dtEndDate;
	}

	/**
	 * ST_MEETING_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
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
	 * ST_UNIT_NAME
	 */
	@Column(name = "ST_UNIT_NAME")
	private String stUnitName;

	/**
	 * ST_UNIT_NAME
	 */
	public String getStUnitName(){
		return stUnitName;
	}

	/**
	 * ST_UNIT_NAME
	 */
	public void setStUnitName (String stUnitName){
		this.stUnitName = stUnitName;
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

	/**
	 * ST_TOPIC
	 */
	@Column(name = "ST_TOPIC")
	private String stTopic;

	/**
	 * ST_TOPIC
	 */
	public String getStTopic(){
		return stTopic;
	}

	/**
	 * ST_TOPIC
	 */
	public void setStTopic (String stTopic){
		this.stTopic = stTopic;
	}

}