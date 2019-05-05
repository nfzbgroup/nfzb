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
 * LEGISLATION_SEND_NOTICE Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_SEND_NOTICE")
public class LegislationSendNotice implements Serializable {

  public static final String LegislationSendNotice = "LEGISLATION_SEND_NOTICE";
	
  public LegislationSendNotice()
  {
  }

	/**
	 * ST_BAK
	 */
	@Column(name = "ST_BAK")
	private String stBak;

	/**
	 * ST_BAK
	 */
	public String getStBak(){
		return stBak;
	}

	/**
	 * ST_BAK
	 */
	public void setStBak (String stBak){
		this.stBak = stBak;
	}

	/**
	 * DT_FEEKBACK_DATE
	 */
	@Column(name = "DT_FEEKBACK_DATE")
	private Date dtFeekbackDate;

	/**
	 * DT_FEEKBACK_DATE
	 */
	public Date getDtFeekbackDate(){
		return dtFeekbackDate;
	}

	/**
	 * DT_FEEKBACK_DATE
	 */
	public void setDtFeekbackDate (Date dtFeekbackDate){
		this.dtFeekbackDate = dtFeekbackDate;
	}

	/**
	 * ST_SEND_TYPE
	 */
	@Column(name = "ST_SEND_TYPE")
	private String stSendType;

	/**
	 * ST_SEND_TYPE
	 */
	public String getStSendType(){
		return stSendType;
	}

	/**
	 * ST_SEND_TYPE
	 */
	public void setStSendType (String stSendType){
		this.stSendType = stSendType;
	}

	/**
	 * ST_DOC_ID
	 */
	@Column(name = "ST_DOC_ID")
	private String stDocId;

	/**
	 * ST_DOC_ID
	 */
	public String getStDocId(){
		return stDocId;
	}

	/**
	 * ST_DOC_ID
	 */
	public void setStDocId (String stDocId){
		this.stDocId = stDocId;
	}

	/**
	 * ST_NOTICE_STATUS
	 */
	@Column(name = "ST_NOTICE_STATUS")
	private String stNoticeStatus;

	/**
	 * ST_NOTICE_STATUS
	 */
	public String getStNoticeStatus(){
		return stNoticeStatus;
	}

	/**
	 * ST_NOTICE_STATUS
	 */
	public void setStNoticeStatus (String stNoticeStatus){
		this.stNoticeStatus = stNoticeStatus;
	}

	/**
	 * ST_TEAM_ID
	 */
	@Column(name = "ST_TEAM_ID")
	private String stTeamId;

	/**
	 * ST_TEAM_ID
	 */
	public String getStTeamId(){
		return stTeamId;
	}

	/**
	 * ST_TEAM_ID
	 */
	public void setStTeamId (String stTeamId){
		this.stTeamId = stTeamId;
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
	 * ST_FEEDBACK_CONTENT
	 */
	@Column(name = "ST_FEEDBACK_CONTENT")
	private String stFeedbackContent;

	/**
	 * ST_FEEDBACK_CONTENT
	 */
	public String getStFeedbackContent(){
		return stFeedbackContent;
	}

	/**
	 * ST_FEEDBACK_CONTENT
	 */
	public void setStFeedbackContent (String stFeedbackContent){
		this.stFeedbackContent = stFeedbackContent;
	}

	/**
	 * ST_TEAM_NAME
	 */
	@Column(name = "ST_TEAM_NAME")
	private String stTeamName;

	/**
	 * ST_TEAM_NAME
	 */
	public String getStTeamName(){
		return stTeamName;
	}

	/**
	 * ST_TEAM_NAME
	 */
	public void setStTeamName (String stTeamName){
		this.stTeamName = stTeamName;
	}

	/**
	 * ST_NOTICE_CONTENT
	 */
	@Column(name = "ST_NOTICE_CONTENT")
	private String stNoticeContent;

	/**
	 * ST_NOTICE_CONTENT
	 */
	public String getStNoticeContent(){
		return stNoticeContent;
	}

	/**
	 * ST_NOTICE_CONTENT
	 */
	public void setStNoticeContent (String stNoticeContent){
		this.stNoticeContent = stNoticeContent;
	}

	/**
	 * ST_NOTICE_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
	@Column(name = "ST_NOTICE_ID")
	private String stNoticeId;

	/**
	 * ST_NOTICE_ID
	 */
	public String getStNoticeId(){
		return stNoticeId;
	}

	/**
	 * ST_NOTICE_ID
	 */
	public void setStNoticeId (String stNoticeId){
		this.stNoticeId = stNoticeId;
	}

	/**
	 * DT_RECV_DATE
	 */
	@Column(name = "DT_RECV_DATE")
	private Date dtRecvDate;

	/**
	 * DT_RECV_DATE
	 */
	public Date getDtRecvDate(){
		return dtRecvDate;
	}

	/**
	 * DT_RECV_DATE
	 */
	public void setDtRecvDate (Date dtRecvDate){
		this.dtRecvDate = dtRecvDate;
	}

	/**
	 * DT_OPEN_DATE
	 */
	@Column(name = "DT_OPEN_DATE")
	private Date dtOpenDate;

	/**
	 * DT_OPEN_DATE
	 */
	public Date getDtOpenDate(){
		return dtOpenDate;
	}

	/**
	 * DT_OPEN_DATE
	 */
	public void setDtOpenDate (Date dtOpenDate){
		this.dtOpenDate = dtOpenDate;
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
	 * ST_MODEL_NAME
	 */
	@Column(name = "ST_MODEL_NAME")
	private String stModelName;

	/**
	 * ST_MODEL_NAME
	 */
	public String getStModelName(){
		return stModelName;
	}

	/**
	 * ST_MODEL_NAME
	 */
	public void setStModelName (String stModelName){
		this.stModelName = stModelName;
	}

}