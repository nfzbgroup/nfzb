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
 * LEGISLATION_PROCESS_TASK Bean (操作业务实体) 
 * autoCreated by lj
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_PROCESS_TASK")
public class LegislationProcessTask implements Serializable {

  public static final String LegislationProcessTask = "LEGISLATION_PROCESS_TASK";
	
  public LegislationProcessTask()
  {
  }

	/**
	 * ST_DEAL_ID
	 */
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
	 * ST_TASK_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
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
	 * ST_ENABLE
	 */
	@Column(name = "ST_ENABLE")
	private String stEnable;

	/**
	 * ST_ENABLE
	 */
	public String getStEnable(){
		return stEnable;
	}

	/**
	 * ST_ENABLE
	 */
	public void setStEnable (String stEnable){
		this.stEnable = stEnable;
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
	 * ST_COMMENT1
	 */
	@Column(name = "ST_COMMENT1")
	private String stComment1;

	/**
	 * ST_COMMENT1
	 */
	public String getStComment1(){
		return stComment1;
	}

	/**
	 * ST_COMMENT1
	 */
	public void setStComment1 (String stComment1){
		this.stComment1 = stComment1;
	}

	/**
	 * ST_COMMENT2
	 */
	@Column(name = "ST_COMMENT2")
	private String stComment2;

	/**
	 * ST_COMMENT2
	 */
	public String getStComment2(){
		return stComment2;
	}

	/**
	 * ST_COMMENT2
	 */
	public void setStComment2 (String stComment2){
		this.stComment2 = stComment2;
	}

	/**
	 * ST_FLOW_ID
	 */
	@Column(name = "ST_FLOW_ID")
	private String stFlowId;

	/**
	 * ST_FLOW_ID
	 */
	public String getStFlowId(){
		return stFlowId;
	}

	/**
	 * ST_FLOW_ID
	 */
	public void setStFlowId (String stFlowId){
		this.stFlowId = stFlowId;
	}

	/**
	 * ST_ROLE_NAME
	 */
	@Column(name = "ST_ROLE_NAME")
	private String stRoleName;

	/**
	 * ST_ROLE_NAME
	 */
	public String getStRoleName(){
		return stRoleName;
	}

	/**
	 * ST_ROLE_NAME
	 */
	public void setStRoleName (String stRoleName){
		this.stRoleName = stRoleName;
	}

	/**
	 * ST_ROLE_ID
	 */
	@Column(name = "ST_ROLE_ID")
	private String stRoleId;

	/**
	 * ST_ROLE_ID
	 */
	public String getStRoleId(){
		return stRoleId;
	}

	/**
	 * ST_ROLE_ID
	 */
	public void setStRoleId (String stRoleId){
		this.stRoleId = stRoleId;
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

	/**
	 * ST_ACTIVE
	 */
	@Column(name = "ST_ACTIVE")
	private String stActive;

	/**
	 * ST_ACTIVE
	 */
	public String getStActive(){
		return stActive;
	}

	/**
	 * ST_ACTIVE
	 */
	public void setStActive (String stActive){
		this.stActive = stActive;
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
	 * ST_DEAL_NAME
	 */
	@Column(name = "ST_DEAL_NAME")
	private String stDealName;

	/**
	 * ST_DEAL_NAME
	 */
	public String getStDealName(){
		return stDealName;
	}

	/**
	 * ST_DEAL_NAME
	 */
	public void setStDealName (String stDealName){
		this.stDealName = stDealName;
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

}