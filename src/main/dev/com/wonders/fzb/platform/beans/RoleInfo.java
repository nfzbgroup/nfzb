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
 * 角色 业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_ROLE_INFO")
public class RoleInfo implements Serializable {
	 /**
     * WFC_F_ROLE_INFO
     */
    public static final String WFC_F_ROLE_INFO = "WFC_F_ROLE_INFO";
    
    /**
     * ROLE_ID
     */
    public static final String ROLE_ID = "ROLE_ID";
    
    /**
     * ROLE_NAME
     */
    public static final String ROLE_NAME = "ROLE_NAME";
    
    /**
     * DESCRIPTION
     */
    public static final String DESCRIPTION = "DESCRIPTION";
    
    /**
     * ROLE_GROUP
     */
    public static final String ROLE_GROUP = "ROLE_GROUP";
    
	public RoleInfo() {
		
	}
	/**
     * 角色标识
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
    @Column(name = "ROLE_ID")
    private String roleId;
    
    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;
    
    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;
    
    /**
     * 角色组
     */
    @Column(name = "ROLE_GROUP")
    private String roleGroup;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}
    
}