package com.wonders.fzb.platform.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.hibernate.annotations.GenericGenerator;

/**
 * 操作业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_OP_INFO")
public class OpInfo implements Serializable {
	 /**
     * WFC_F_OP_INFO
     */
    public static final String WFC_F_OP_INFO = "WFC_F_OP_INFO";
    
    /**
     * OP_ID
     */
    public static final String OP_ID = "OP_ID";
    
    /**
     * OP_NAME
     */
    public static final String OP_NAME = "OP_NAME";
    
    /**
     * RIGHT_TYPE
     */
    public static final String RIGHT_TYPE = "RIGHT_TYPE";
    
    /**
     * DESCRIPTION
     */
    public static final String DESCRIPTION = "DESCRIPTION";
    
    /**
     * OP_GROUP
     */
    public static final String OP_GROUP = "OP_GROUP";
    
	public OpInfo() {
	}
	/**
     * 操作标识
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
    @Column(name = "OP_ID")
    private String opId;
	
    /**
     * 操作名称
     */
    @Column(name = "OP_NAME")
    private String opName;
    
    /**
     * 类别，"F"无权限控制,"R"有权限控制,一般为R
     */
    @Column(name = "RIGHT_TYPE")
    private String rightType;
    
    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;
    
    /**
     * 操作组

     */
    @Column(name = "OP_GROUP")
    private String opGroup;

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getRightType() {
		return rightType;
	}

	public void setRightType(String rightType) {
		this.rightType = rightType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpGroup() {
		return opGroup;
	}

	public void setOpGroup(String opGroup) {
		this.opGroup = opGroup;
	}

}