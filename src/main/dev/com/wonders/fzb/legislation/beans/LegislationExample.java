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
 * LEGISLATION_EXAMPLE Bean (操作业务实体) 
 * autoCreated by liujun
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LEGISLATION_EXAMPLE")
public class LegislationExample implements Serializable {

  public static final String LegislationExample = "LEGISLATION_EXAMPLE";
	
  public LegislationExample()
  {
  }

	/**
	 * ST_MEMO
	 */
	@Column(name = "ST_MEMO")
	private String stMemo;

	/**
	 * ST_MEMO
	 */
	public String getStMemo(){
		return stMemo;
	}

	/**
	 * ST_MEMO
	 */
	public void setStMemo (String stMemo){
		this.stMemo = stMemo;
	}

	/**
	 * ST_EXAMPLE_NAME
	 */
	@Column(name = "ST_EXAMPLE_NAME")
	private String stExampleName;

	/**
	 * ST_EXAMPLE_NAME
	 */
	public String getStExampleName(){
		return stExampleName;
	}

	/**
	 * ST_EXAMPLE_NAME
	 */
	public void setStExampleName (String stExampleName){
		this.stExampleName = stExampleName;
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
	 * ST_CREATE_ID
	 */
	@Column(name = "ST_CREATE_ID")
	private String stCreateId;

	/**
	 * ST_CREATE_ID
	 */
	public String getStCreateId(){
		return stCreateId;
	}

	/**
	 * ST_CREATE_ID
	 */
	public void setStCreateId (String stCreateId){
		this.stCreateId = stCreateId;
	}

	/**
	 * ST_CREATE_NAME
	 */
	@Column(name = "ST_CREATE_NAME")
	private String stCreateName;

	/**
	 * ST_CREATE_NAME
	 */
	public String getStCreateName(){
		return stCreateName;
	}

	/**
	 * ST_CREATE_NAME
	 */
	public void setStCreateName (String stCreateName){
		this.stCreateName = stCreateName;
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
	 * ST_NEED
	 */
	@Column(name = "ST_NEED")
	private String stNeed;

	/**
	 * ST_NEED
	 */
	public String getStNeed(){
		return stNeed;
	}

	/**
	 * ST_NEED
	 */
	public void setStNeed (String stNeed){
		this.stNeed = stNeed;
	}

	/**
	 * ST_FILE_NO
	 */
	@Column(name = "ST_FILE_NO")
	private String stFileNo;

	/**
	 * ST_FILE_NO
	 */
	public String getStFileNo(){
		return stFileNo;
	}

	/**
	 * ST_FILE_NO
	 */
	public void setStFileNo (String stFileNo){
		this.stFileNo = stFileNo;
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
	 * ST_EXAMPLE_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
	@Column(name = "ST_EXAMPLE_ID")
	private String stExampleId;

	/**
	 * ST_EXAMPLE_ID
	 */
	public String getStExampleId(){
		return stExampleId;
	}

	/**
	 * ST_EXAMPLE_ID
	 */
	public void setStExampleId (String stExampleId){
		this.stExampleId = stExampleId;
	}

	/**
	 * ST_NODE
	 */
	@Column(name = "ST_NODE")
	private String stNode;

	/**
	 * ST_NODE
	 */
	public String getStNode(){
		return stNode;
	}

	/**
	 * ST_NODE
	 */
	public void setStNode (String stNode){
		this.stNode = stNode;
	}

}