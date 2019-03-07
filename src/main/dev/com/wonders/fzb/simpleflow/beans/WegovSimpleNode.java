package com.wonders.fzb.simpleflow.beans;

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
 * WEGOV_SIMPLE_NODE Bean (操作业务实体) 
 * autoCreated by lj
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WEGOV_SIMPLE_NODE")
public class WegovSimpleNode implements Serializable {

  public static final String WegovSimpleNode = "WEGOV_SIMPLE_NODE";
	
  public WegovSimpleNode()
  {
  }

	/**
	 * DT_EXTEND_DATE
	 */
	@Column(name = "DT_EXTEND_DATE")
	private Date dtExtendDate;

	/**
	 * DT_EXTEND_DATE
	 */
	public Date getDtExtendDate(){
		return dtExtendDate;
	}

	/**
	 * DT_EXTEND_DATE
	 */
	public void setDtExtendDate (Date dtExtendDate){
		this.dtExtendDate = dtExtendDate;
	}

	/**
	 * ST_INFO_URL
	 */
	@Column(name = "ST_INFO_URL")
	private String stInfoUrl;

	/**
	 * ST_INFO_URL
	 */
	public String getStInfoUrl(){
		return stInfoUrl;
	}

	/**
	 * ST_INFO_URL
	 */
	public void setStInfoUrl (String stInfoUrl){
		this.stInfoUrl = stInfoUrl;
	}

	/**
	 * ST_SUBMIT_ROLE
	 */
	@Column(name = "ST_SUBMIT_ROLE")
	private String stSubmitRole;

	/**
	 * ST_SUBMIT_ROLE
	 */
	public String getStSubmitRole(){
		return stSubmitRole;
	}

	/**
	 * ST_SUBMIT_ROLE
	 */
	public void setStSubmitRole (String stSubmitRole){
		this.stSubmitRole = stSubmitRole;
	}

	/**
	 * ST_MENU
	 */
	@Column(name = "ST_MENU")
	private String stMenu;

	/**
	 * ST_MENU
	 */
	public String getStMenu(){
		return stMenu;
	}

	/**
	 * ST_MENU
	 */
	public void setStMenu (String stMenu){
		this.stMenu = stMenu;
	}

	/**
	 * ST_START
	 */
	@Column(name = "ST_START")
	private String stStart;

	/**
	 * ST_START
	 */
	public String getStStart(){
		return stStart;
	}

	/**
	 * ST_START
	 */
	public void setStStart (String stStart){
		this.stStart = stStart;
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
	 * ST_FLOW_NAME
	 */
	@Column(name = "ST_FLOW_NAME")
	private String stFlowName;

	/**
	 * ST_FLOW_NAME
	 */
	public String getStFlowName(){
		return stFlowName;
	}

	/**
	 * ST_FLOW_NAME
	 */
	public void setStFlowName (String stFlowName){
		this.stFlowName = stFlowName;
	}

	/**
	 * ST_TODO_NAME
	 */
	@Column(name = "ST_TODO_NAME")
	private String stTodoName;

	/**
	 * ST_TODO_NAME
	 */
	public String getStTodoName(){
		return stTodoName;
	}

	/**
	 * ST_TODO_NAME
	 */
	public void setStTodoName (String stTodoName){
		this.stTodoName = stTodoName;
	}

	/**
	 * ST_EXTEND_TWO
	 */
	@Column(name = "ST_EXTEND_TWO")
	private String stExtendTwo;

	/**
	 * ST_EXTEND_TWO
	 */
	public String getStExtendTwo(){
		return stExtendTwo;
	}

	/**
	 * ST_EXTEND_TWO
	 */
	public void setStExtendTwo (String stExtendTwo){
		this.stExtendTwo = stExtendTwo;
	}

	/**
	 * ST_PARENT_NODE
	 */
	@Column(name = "ST_PARENT_NODE")
	private String stParentNode;

	/**
	 * ST_PARENT_NODE
	 */
	public String getStParentNode(){
		return stParentNode;
	}

	/**
	 * ST_PARENT_NODE
	 */
	public void setStParentNode (String stParentNode){
		this.stParentNode = stParentNode;
	}

	/**
	 * NM_ID
	 */
	@Column(name = "NM_ID")
	private Integer id;

	/**
	 * NM_ID
	 */
	public Integer getId(){
		return id;
	}

	/**
	 * NM_ID
	 */
	public void setId (Integer id){
		this.id = id;
	}

	/**
	 * ST_EXTEND_ONE
	 */
	@Column(name = "ST_EXTEND_ONE")
	private String stExtendOne;

	/**
	 * ST_EXTEND_ONE
	 */
	public String getStExtendOne(){
		return stExtendOne;
	}

	/**
	 * ST_EXTEND_ONE
	 */
	public void setStExtendOne (String stExtendOne){
		this.stExtendOne = stExtendOne;
	}

	/**
	 * ST_DONE_NAME
	 */
	@Column(name = "ST_DONE_NAME")
	private String stDoneName;

	/**
	 * ST_DONE_NAME
	 */
	public String getStDoneName(){
		return stDoneName;
	}

	/**
	 * ST_DONE_NAME
	 */
	public void setStDoneName (String stDoneName){
		this.stDoneName = stDoneName;
	}

	/**
	 * ST_NEXT_NODE
	 */
	@Column(name = "ST_NEXT_NODE")
	private String stNextNode;

	/**
	 * ST_NEXT_NODE
	 */
	public String getStNextNode(){
		return stNextNode;
	}

	/**
	 * ST_NEXT_NODE
	 */
	public void setStNextNode (String stNextNode){
		this.stNextNode = stNextNode;
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
	 * ST_NODE_ID
	 */
	@Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
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