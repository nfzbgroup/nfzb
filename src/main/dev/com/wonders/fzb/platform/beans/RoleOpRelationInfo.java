package com.wonders.fzb.platform.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色操作关系 业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_ROLE_OP_RELATION")
@IdClass(RoleOpRelationPK.class)
public class RoleOpRelationInfo implements Serializable {
	 /**
     * WFC_F_ROLE_OP_RELATION
     */
    public static final String WFC_F_ROLE_OP_RELATION = "WFC_F_ROLE_OP_RELATION";
    
    /**
     * ROLE_ID
     */
    public static final String ROLE_ID = "ROLE_ID";
    
    /**
     * OP_ID
     */
    public static final String OP_ID = "OP_ID";
    
    /**
     * REL_ID
     */
    public static final String REL_ID = "REL_ID";
    
	public RoleOpRelationInfo() {
	}
	/**
     * 角色标识
     */
    @Id
    private String roleId;
    
    /**
     * 操作标识
     */
    @Id
    private String opId;
    
    /**
     * 关系标识，一般为01
     */
    @Id
    private String relId;

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

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}
    
}