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
 * LEGISLATION_CHECKMEETING_DEAL Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_CHECKMEETING_DEAL")
public class LegislationCheckmeetingDeal implements Serializable {

  public static final String LegislationCheckmeetingDeal = "LEGISLATION_CHECKMEETING_DEAL";
	
  public LegislationCheckmeetingDeal()
  {
  }

	/**
	 * ST_DEAL_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
	@Column(name = "ST_DEAL_ID")
	private String stDealId;

	/**
	 * ST_DEAL_ID
	 */
	public String getStDealId(){
		return stDealId;
	}

	/**
	 * ST_DEAL_ID
	 */
	public void setStDealId (String stDealId){
		this.stDealId = stDealId;
	}

	/**
	 * ST_BAK_ONE
	 */
	@Column(name = "ST_BAK_ONE")
	private String stBakOne;

	/**
	 * ST_BAK_ONE
	 */
	public String getStBakOne(){
		return stBakOne;
	}

	/**
	 * ST_BAK_ONE
	 */
	public void setStBakOne (String stBakOne){
		this.stBakOne = stBakOne;
	}

	/**
	 * DT_BAK_DATE
	 */
	@Column(name = "DT_BAK_DATE")
	private Date dtBakDate;

	/**
	 * DT_BAK_DATE
	 */
	public Date getDtBakDate(){
		return dtBakDate;
	}

	/**
	 * DT_BAK_DATE
	 */
	public void setDtBakDate (Date dtBakDate){
		this.dtBakDate = dtBakDate;
	}

	/**
	 * ST_ACTION_ID
	 */
	@Column(name = "ST_ACTION_ID")
	private String stActionId;

	/**
	 * ST_ACTION_ID
	 */
	public String getStActionId(){
		return stActionId;
	}

	/**
	 * ST_ACTION_ID
	 */
	public void setStActionId (String stActionId){
		this.stActionId = stActionId;
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
	 * DT_DEAL_DATE
	 */
	@Column(name = "DT_DEAL_DATE")
	private Date dtDealDate;

	/**
	 * DT_DEAL_DATE
	 */
	public Date getDtDealDate(){
		return dtDealDate;
	}

	/**
	 * DT_DEAL_DATE
	 */
	public void setDtDealDate (Date dtDealDate){
		this.dtDealDate = dtDealDate;
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
	 * ST_ACTION_NAME
	 */
	@Column(name = "ST_ACTION_NAME")
	private String stActionName;

	/**
	 * ST_ACTION_NAME
	 */
	public String getStActionName(){
		return stActionName;
	}

	/**
	 * ST_ACTION_NAME
	 */
	public void setStActionName (String stActionName){
		this.stActionName = stActionName;
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
	 * ST_BAK_TWO
	 */
	@Column(name = "ST_BAK_TWO")
	private String stBakTwo;

	/**
	 * ST_BAK_TWO
	 */
	public String getStBakTwo(){
		return stBakTwo;
	}

	/**
	 * ST_BAK_TWO
	 */
	public void setStBakTwo (String stBakTwo){
		this.stBakTwo = stBakTwo;
	}

}