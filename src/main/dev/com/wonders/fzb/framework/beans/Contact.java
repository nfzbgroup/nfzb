package com.wonders.fzb.framework.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * MODULE_ORG_RELATE，只做中间表关联PO，没有直接的数据操作此类不需要直接使用
 * @author ZSW
 */
@Entity
@Table(name = "EXECLAW_CONTACTS")
public class Contact implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "uuid", strategy = "com.wonders.fzb.base.dao.generator.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
    @Column(name = "CONTACT_ID")
    private String contactId;
  
    /**
     * 用户Id
     */
    @Column(name = "USER_ID")
    private String userId;
    
    /**
     * 姓名
     */
    @Column(name = "NAME")
    private String name;
    
    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;
    
    /**
     * 地址
     */
    @Column(name = "ADDRESS")
    private String address;
    
    /**
     * 手机
     */
    @Column(name="MOBILE_PHONE")
    private String mobilePhone;
    
    /**
     * 座机、分机
     */
    @Column(name="LANDLINE")
    private String landLine;
    
    /**
     * MOR_ID 
     */
    @Column(name="MORELATE_ID")
    private String morelateId;
    
    /**
     * 单位ID
     */
    @Column(name="TEAM_ID")
    private String teamId;
    
    /**
     * 单位名称
     */
    @Column(name="TEAM_NAME")
    private String teamName;
    
    /**
     * 系统名称
     */
    @Column(name="MODULE_ID")
    private String moduleId;
    
    /**
     * 头像
     */
    @Column(name="AVATAR")
    private String avatar;
    
    /**
     * 账号类型
     */
    @Column(name="CONTACT_TYPE")
    private String contactType;
    
    /**
     * 职级：排序
     */
    @Column(name="POSITION")
    private long position;
    
    /**
     * 状态
     */
    @Column(name="USER_STATUS")
    private String userStatus;
    
    /**
     * 职务
     */
    @Column(name="MEMO")
    private String memo;
    
    @Column(name="PARENT_ID")
    private String parentId;

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getLandLine() {
		return landLine;
	}

	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	public String getMorelateId() {
		return morelateId;
	}

	public void setMorelateId(String morelateId) {
		this.morelateId = morelateId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
