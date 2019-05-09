package com.wonders.fzb.assess.beans;

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
 * LEGISLATION_ASSESS_ITEM Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_ASSESS_ITEM")
public class LegislationAssessItem implements Serializable {

  public static final String LegislationAssessItem = "LEGISLATION_ASSESS_ITEM";
	
  public LegislationAssessItem()
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
	 * ST_SUGGEST
	 */
	@Column(name = "ST_SUGGEST")
	private String stSuggest;

	/**
	 * ST_SUGGEST
	 */
	public String getStSuggest(){
		return stSuggest;
	}

	/**
	 * ST_SUGGEST
	 */
	public void setStSuggest (String stSuggest){
		this.stSuggest = stSuggest;
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

	/**
	 * ST_IS_DELETE
	 */
	@Column(name = "ST_IS_DELETE")
	private String stIsDelete;

	/**
	 * ST_IS_DELETE
	 */
	public String getStIsDelete(){
		return stIsDelete;
	}

	/**
	 * ST_IS_DELETE
	 */
	public void setStIsDelete (String stIsDelete){
		this.stIsDelete = stIsDelete;
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
	 * ST_ASSESS_ID
	 */
	@Column(name = "ST_ASSESS_ID")
	private String stAssessId;

	/**
	 * ST_ASSESS_ID
	 */
	public String getStAssessId(){
		return stAssessId;
	}

	/**
	 * ST_ASSESS_ID
	 */
	public void setStAssessId (String stAssessId){
		this.stAssessId = stAssessId;
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

}