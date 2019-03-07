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
 * 操作权限关系业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_OP_RIGHT")
public class OpRightInfo implements Serializable {
	/**
     * WFC_F_OP_RIGHT
     */
    public static final String WFC_F_OP_RIGHT = "WFC_F_OP_RIGHT";
    
    /**
     * OP_RIGHT_ID
     */
    public static final String OP_RIGHT_ID = "OP_RIGHT_ID";
    
    /**
     * USER_ID
     */
    public static final String USER_ID = "USER_ID";
    
    /**
     * TEAM_ID
     */
    public static final String TEAM_ID = "TEAM_ID";
    
    /**
     * TP_TYPE
     */
    public static final String TP_TYPE = "TP_TYPE";
    
    /**
     * ROLE_ID
     */
    public static final String ROLE_ID = "ROLE_ID";
    
    /**
     * OP_ID
     */
    public static final String OP_ID = "OP_ID";
    
    /**
     * RO_TYPE
     */
    public static final String RO_TYPE = "RO_TYPE";
    
	public OpRightInfo() {
	}
	/**
     * 标识
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
    @Column(name = "OP_RIGHT_ID")
    public String opRightId;
    
    /**
     * 用户标识
     */
    @Column(name = "USER_ID")
    public String userId;
    
    /**
     * 组织标识
     */
    @Column(name = "TEAM_ID")
    public String teamId;
    
    /**
     * 主体类别，用户：P；组织：T
     */
    @Column(name = "TP_TYPE")
    public String tpType;
    
    /**
     * 角色标识
     */
    @Column(name = "ROLE_ID")
    public String roleId;
    
    /**
     * 操作标识
     */
    @Column(name = "OP_ID")
    public String opId;
    
    /**
     * 客体类别，角色：R，操作：O
     */
    @Column(name = "RO_TYPE")
    public String roType;

	public String getOpRightId() {
		return opRightId;
	}

	public void setOpRightId(String opRightId) {
		this.opRightId = opRightId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTpType() {
		return tpType;
	}

	public void setTpType(String tpType) {
		this.tpType = tpType;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getRoType() {
		return roType;
	}

	public void setRoType(String roType) {
		this.roType = roType;
	}
    
}