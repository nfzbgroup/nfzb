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
 * LEGISLATION_CHECKMEETING_TASKD Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_CHECKMEETING_TASKD")
public class LegislationCheckmeetingTaskd implements Serializable {

  public static final String LegislationCheckmeetingTaskd = "LEGISLATION_CHECKMEETING_TASKD";
	
  public LegislationCheckmeetingTaskd()
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
	 * ST_TASK_ID
	 */
	@Column(name = "ST_TASK_ID")
	private String stTaskId;

	/**
	 * ST_TASK_ID
	 */
	public String getStTaskId(){
		return stTaskId;
	}

	/**
	 * ST_TASK_ID
	 */
	public void setStTaskId (String stTaskId){
		this.stTaskId = stTaskId;
	}

	/**
	 * DT_DEAD_DATE
	 */
	@Column(name = "DT_DEAD_DATE")
	private Date dtDeadDate;

	/**
	 * DT_DEAD_DATE
	 */
	public Date getDtDeadDate(){
		return dtDeadDate;
	}

	/**
	 * DT_DEAD_DATE
	 */
	public void setDtDeadDate (Date dtDeadDate){
		this.dtDeadDate = dtDeadDate;
	}

	/**
	 * DT_BAK1
	 */
	@Column(name = "DT_BAK1")
	private Date dtBak1;

	/**
	 * DT_BAK1
	 */
	public Date getDtBak1(){
		return dtBak1;
	}

	/**
	 * DT_BAK1
	 */
	public void setDtBak1 (Date dtBak1){
		this.dtBak1 = dtBak1;
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

	/**
	 * DT_BAK2
	 */
	@Column(name = "DT_BAK2")
	private Date dtBak2;

	/**
	 * DT_BAK2
	 */
	public Date getDtBak2(){
		return dtBak2;
	}

	/**
	 * DT_BAK2
	 */
	public void setDtBak2 (Date dtBak2){
		this.dtBak2 = dtBak2;
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
	 * ST_PERSON_ID
	 */
	@Column(name = "ST_PERSON_ID")
	private String stPersonId;

	/**
	 * ST_PERSON_ID
	 */
	public String getStPersonId(){
		return stPersonId;
	}

	/**
	 * ST_PERSON_ID
	 */
	public void setStPersonId (String stPersonId){
		this.stPersonId = stPersonId;
	}

	/**
	 * ST_BAK4
	 */
	@Column(name = "ST_BAK4")
	private String stBak4;

	/**
	 * ST_BAK4
	 */
	public String getStBak4(){
		return stBak4;
	}

	/**
	 * ST_BAK4
	 */
	public void setStBak4 (String stBak4){
		this.stBak4 = stBak4;
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
	 * ST_BAK5
	 */
	@Column(name = "ST_BAK5")
	private String stBak5;

	/**
	 * ST_BAK5
	 */
	public String getStBak5(){
		return stBak5;
	}

	/**
	 * ST_BAK5
	 */
	public void setStBak5 (String stBak5){
		this.stBak5 = stBak5;
	}

	/**
	 * ST_TASK_STATUS
	 */
	@Column(name = "ST_TASK_STATUS")
	private String stTaskStatus;

	/**
	 * ST_TASK_STATUS
	 */
	public String getStTaskStatus(){
		return stTaskStatus;
	}

	/**
	 * ST_TASK_STATUS
	 */
	public void setStTaskStatus (String stTaskStatus){
		this.stTaskStatus = stTaskStatus;
	}

	/**
	 * ST_BAK2
	 */
	@Column(name = "ST_BAK2")
	private String stBak2;

	/**
	 * ST_BAK2
	 */
	public String getStBak2(){
		return stBak2;
	}

	/**
	 * ST_BAK2
	 */
	public void setStBak2 (String stBak2){
		this.stBak2 = stBak2;
	}

	/**
	 * ST_BAK3
	 */
	@Column(name = "ST_BAK3")
	private String stBak3;

	/**
	 * ST_BAK3
	 */
	public String getStBak3(){
		return stBak3;
	}

	/**
	 * ST_BAK3
	 */
	public void setStBak3 (String stBak3){
		this.stBak3 = stBak3;
	}

	/**
	 * ST_BAK1
	 */
	@Column(name = "ST_BAK1")
	private String stBak1;

	/**
	 * ST_BAK1
	 */
	public String getStBak1(){
		return stBak1;
	}

	/**
	 * ST_BAK1
	 */
	public void setStBak1 (String stBak1){
		this.stBak1 = stBak1;
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
	 * ST_TASKDETAIL_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
	@Column(name = "ST_TASKDETAIL_ID")
	private String stTaskdetailId;

	/**
	 * ST_TASKDETAIL_ID
	 */
	public String getStTaskdetailId(){
		return stTaskdetailId;
	}

	/**
	 * ST_TASKDETAIL_ID
	 */
	public void setStTaskdetailId (String stTaskdetailId){
		this.stTaskdetailId = stTaskdetailId;
	}

	/**
	 * DT_CLOSE_DATE
	 */
	@Column(name = "DT_CLOSE_DATE")
	private Date dtCloseDate;

	/**
	 * DT_CLOSE_DATE
	 */
	public Date getDtCloseDate(){
		return dtCloseDate;
	}

	/**
	 * DT_CLOSE_DATE
	 */
	public void setDtCloseDate (Date dtCloseDate){
		this.dtCloseDate = dtCloseDate;
	}

	/**
	 * ST_BAK6
	 */
	@Column(name = "ST_BAK6")
	private String stBak6;

	/**
	 * ST_BAK6
	 */
	public String getStBak6(){
		return stBak6;
	}

	/**
	 * ST_BAK6
	 */
	public void setStBak6 (String stBak6){
		this.stBak6 = stBak6;
	}

	/**
	 * ST_PERSON_NAME
	 */
	@Column(name = "ST_PERSON_NAME")
	private String stPersonName;

	/**
	 * ST_PERSON_NAME
	 */
	public String getStPersonName(){
		return stPersonName;
	}

	/**
	 * ST_PERSON_NAME
	 */
	public void setStPersonName (String stPersonName){
		this.stPersonName = stPersonName;
	}

}