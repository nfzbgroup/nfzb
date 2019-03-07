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
 * 操作业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_ROLE_OP_RANGE")
@IdClass(RoleOpRangePK.class)
public class RoleOpRangeInfo implements Serializable {

	public RoleOpRangeInfo() {
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
    
    /**
     * 客体类型  UNIT：组织； GROUP：组织类型； ROLE：角色；RIGHT：操作；
     */
    @Id
    private String type;
    
    /**
     * 客体标识
     */
    @Id
    private String objectId;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
    
}