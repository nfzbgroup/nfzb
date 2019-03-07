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
 * 资源业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_RES_INFO")
public class ResInfo implements Serializable {
	/**
     * WFC_F_RES_INFO
     */
    public static final String WFC_F_RES_INFO = "WFC_F_RES_INFO";
    
    /**
     * RES_ID
     */
    public static final String RES_ID = "RES_ID";
    
    /**
     * RES_NAME
     */
    public static final String RES_NAME = "RES_NAME";
    
    /**
     * RIGHT_TYPE
     */
    public static final String RIGHT_TYPE = "RIGHT_TYPE";
    
    /**
     * DESCRIPTION
     */
    public static final String DESCRIPTION = "DESCRIPTION";
    
	public ResInfo() {
	}
	/**
     * 资源标识
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
    @Column(name = "RES_ID")
    private String resId;
    
    /**
     * 资源名称
     */
    @Column(name = "RES_NAME")
    private String resName;
    
    /**
     * 类别
     */
    @Column(name = "RIGHT_TYPE")
    private String rightType;
    
    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
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
    
}