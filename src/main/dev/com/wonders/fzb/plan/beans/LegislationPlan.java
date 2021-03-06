package com.wonders.fzb.plan.beans;

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
 * LEGISLATION_PLAN Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_PLAN")
public class LegislationPlan implements Serializable {

  public static final String LegislationPlan = "LEGISLATION_PLAN";
	
  public LegislationPlan()
  {
  }

	/**
	 * ST_PLAN_TYPE
	 */
	@Column(name = "ST_PLAN_TYPE")
	private String stPlanType;

	/**
	 * ST_PLAN_TYPE
	 */
	public String getStPlanType(){
		return stPlanType;
	}

	/**
	 * ST_PLAN_TYPE
	 */
	public void setStPlanType (String stPlanType){
		this.stPlanType = stPlanType;
	}

	/**
	 * DT_GATHER_DATE
	 */
	@Column(name = "DT_GATHER_DATE")
	private Date dtGatherDate;

	/**
	 * DT_GATHER_DATE
	 */
	public Date getDtGatherDate(){
		return dtGatherDate;
	}

	/**
	 * DT_GATHER_DATE
	 */
	public void setDtGatherDate (Date dtGatherDate){
		this.dtGatherDate = dtGatherDate;
	}

	/**
	 * ST_CREATOR_ID
	 */
	@Column(name = "ST_CREATOR_ID")
	private String stCreatorId;

	/**
	 * ST_CREATOR_ID
	 */
	public String getStCreatorId(){
		return stCreatorId;
	}

	/**
	 * ST_CREATOR_ID
	 */
	public void setStCreatorId (String stCreatorId){
		this.stCreatorId = stCreatorId;
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
	 * ST_PLAN_NAME
	 */
	@Column(name = "ST_PLAN_NAME")
	private String stPlanName;

	/**
	 * ST_PLAN_NAME
	 */
	public String getStPlanName(){
		return stPlanName;
	}

	/**
	 * ST_PLAN_NAME
	 */
	public void setStPlanName (String stPlanName){
		this.stPlanName = stPlanName;
	}

	/**
	 * ST_CREATOR_NAME
	 */
	@Column(name = "ST_CREATOR_NAME")
	private String stCreatorName;

	/**
	 * ST_CREATOR_NAME
	 */
	public String getStCreatorName(){
		return stCreatorName;
	}

	/**
	 * ST_CREATOR_NAME
	 */
	public void setStCreatorName (String stCreatorName){
		this.stCreatorName = stCreatorName;
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
	 * ST_PROJECT_TYPE
	 */
	@Column(name = "ST_PROJECT_TYPE")
	private String stProjectType;

	/**
	 * ST_PROJECT_TYPE
	 */
	public String getStProjectType(){
		return stProjectType;
	}

	/**
	 * ST_PROJECT_TYPE
	 */
	public void setStProjectType (String stProjectType){
		this.stProjectType = stProjectType;
	}

	/**
	 * ST_PROGRESS
	 */
	@Column(name = "ST_PROGRESS")
	private String stProgress;

	/**
	 * ST_PROGRESS
	 */
	public String getStProgress(){
		return stProgress;
	}

	/**
	 * ST_PROGRESS
	 */
	public void setStProgress (String stProgress){
		this.stProgress = stProgress;
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
	 * ST_REASON
	 */
	@Column(name = "ST_REASON")
	private String stReason;

	/**
	 * ST_REASON
	 */
	public String getStReason(){
		return stReason;
	}

	/**
	 * ST_REASON
	 */
	public void setStReason (String stReason){
		this.stReason = stReason;
	}

	/**
	 * ST_PLAN_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
	@Column(name = "ST_PLAN_ID")
	private String stPlanId;

	/**
	 * ST_PLAN_ID
	 */
	public String getStPlanId(){
		return stPlanId;
	}

	/**
	 * ST_PLAN_ID
	 */
	public void setStPlanId (String stPlanId){
		this.stPlanId = stPlanId;
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
	 * ST_REMARK
	 */
	@Column(name = "ST_REMARK")
	private String stRemark;

	/**
	 * ST_REMARK
	 */
	public String getStRemark(){
		return stRemark;
	}

	/**
	 * ST_REMARK
	 */
	public void setStRemark (String stRemark){
		this.stRemark = stRemark;
	}

	/**
	 * DT_PUBLISH_DATE
	 */
	@Column(name = "DT_PUBLISH_DATE")
	private Date dtPublishDate;

	/**
	 * DT_PUBLISH_DATE
	 */
	public Date getDtPublishDate(){
		return dtPublishDate;
	}

	/**
	 * DT_PUBLISH_DATE
	 */
	public void setDtPublishDate (Date dtPublishDate){
		this.dtPublishDate = dtPublishDate;
	}

}