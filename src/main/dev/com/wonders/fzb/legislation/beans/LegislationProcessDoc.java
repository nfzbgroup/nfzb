package com.wonders.fzb.legislation.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * LEGISLATION_PROCESS_DOC Bean (操作业务实体) 
 * autoCreated by lj
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_PROCESS_DOC")
public class LegislationProcessDoc implements Serializable {

  public static final String LegislationProcessDoc = "LEGISLATION_PROCESS_DOC";
	
  public LegislationProcessDoc()
  {
  }

	/**
	 * ST_DOC_NO
	 */
	@Column(name = "ST_DOC_NO")
	private String stDocNo;

	/**
	 * ST_DOC_NO
	 */
	public String getStDocNo(){
		return stDocNo;
	}

	/**
	 * ST_DOC_NO
	 */
	public void setStDocNo (String stDocNo){
		this.stDocNo = stDocNo;
	}

	/**
	 * ST_DOC_NAME
	 */
	@Column(name = "ST_DOC_NAME")
	private String stDocName;

	/**
	 * ST_DOC_NAME
	 */
	public String getStDocName(){
		return stDocName;
	}

	/**
	 * ST_DOC_NAME
	 */
	public void setStDocName (String stDocName){
		this.stDocName = stDocName;
	}

	/**
	 * ST_DOC_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
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
	 * ST_COMENT
	 */
	@Column(name = "ST_COMENT")
	private String stComent;

	/**
	 * ST_COMENT
	 */
	public String getStComent(){
		return stComent;
	}

	/**
	 * ST_COMENT
	 */
	public void setStComent (String stComent){
		this.stComent = stComent;
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
	@Transient
	private boolean hasReturn=false;

	public boolean isHasReturn() {
		return hasReturn;
	}

	public void setHasReturn(boolean hasReturn) {
		this.hasReturn = hasReturn;
	}
}