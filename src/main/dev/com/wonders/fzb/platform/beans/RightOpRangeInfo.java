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
 * 权限操作范围 业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_RIGHT_OP_RANGE")
@IdClass(RightOpRangePK.class)
public class RightOpRangeInfo implements Serializable {
	 /**
     * WFC_F_RIGHT_OP_RANGE
     */
    public static final String WFC_F_RIGHT_OP_RANGE = "WFC_F_RIGHT_OP_RANGE";
    
    /**
     * OP_RIGHT_ID
     */
    public static final String OP_RIGHT_ID = "OP_RIGHT_ID";
    
    /**
     * TYPE
     */
    public static final String TYPE = "TYPE";
    
    /**
     * OBJECT_ID
     */
    public static final String OBJECT_ID = "OBJECT_ID";
    
	public RightOpRangeInfo() {
	}
	 /**
     * 权限标识
     */
    @Id
    public String opRightId;
    
    /**
     * 客体类型  UNIT：组织； GROUP：组织类型； ROLE：角色；RIGHT：操作；
     */
    @Id
    public String type;
    
    /**
     * 客体标识
     */
    @Id
    public String objectId;

	public String getOpRightId() {
		return opRightId;
	}

	public void setOpRightId(String opRightId) {
		this.opRightId = opRightId;
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