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
 * 资源权限关系业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_RES_RIGHT")
public class ResRightInfo implements Serializable {
	/**
     * WFC_F_RES_RIGHT
     */
    public static final String WFC_F_RES_RIGHT = "WFC_F_RES_RIGHT";
    
    /**
     * RES_RIGHT_ID
     */
    public static final String RES_RIGHT_ID = "RES_RIGHT_ID";
    
    /**
     * RES_ID
     */
    public static final String RES_ID = "RES_ID";
    
    /**
     * TEAM_ID
     */
    public static final String TEAM_ID = "TEAM_ID";
    
    /**
     * USER_ID
     */
    public static final String USER_ID = "USER_ID";
    
    /**
     * TP_TYPE
     */
    public static final String TP_TYPE = "TP_TYPE";
    
    /**
     * RES_ROLE_ID
     */
    public static final String RES_ROLE_ID = "RES_ROLE_ID";
    
    /**
     * RES_OP_ID
     */
    public static final String RES_OP_ID = "RES_OP_ID";
    
    /**
     * RO_TYPE
     */
    public static final String RO_TYPE = "RO_TYPE";
    
	public ResRightInfo() {
	}
	/**
     * 标识
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
    @Column(name = "RES_RIGHT_ID")
    private String resRightId;
    
    /**
     * 资源标识
     */
    @Column(name = "RES_ID")
    private String resId;
    
    /**
     * 组织标识
     */
    @Column(name = "TEAM_ID")
    private String teamId;
    
    /**
     * 用户标识
     */
    @Column(name = "USER_ID")
    private String userId;
    
    /**
     * 主体类别，资源：R，组织：T，用户：P
     */
    @Column(name = "TP_TYPE")
    private String tpType;
    
    /**
     * 角色标识
     */
    @Column(name = "RES_ROLE_ID")
    private String resRoleId;
    
    /**
     * 操作标识
     */
    @Column(name = "RES_OP_ID")
    private String resOpId;
    
    /**
     * 客体类别，角色：R，操作：O
     */
    @Column(name = "RO_TYPE")
    private String roType;

	public String getResRightId() {
		return resRightId;
	}

	public void setResRightId(String resRightId) {
		this.resRightId = resRightId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTpType() {
		return tpType;
	}

	public void setTpType(String tpType) {
		this.tpType = tpType;
	}

	public String getResRoleId() {
		return resRoleId;
	}

	public void setResRoleId(String resRoleId) {
		this.resRoleId = resRoleId;
	}

	public String getResOpId() {
		return resOpId;
	}

	public void setResOpId(String resOpId) {
		this.resOpId = resOpId;
	}

	public String getRoType() {
		return roType;
	}

	public void setRoType(String roType) {
		this.roType = roType;
	}
    
}