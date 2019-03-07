package com.wonders.fzb.framework.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 组织架构
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_C_TEAM_INFO")
public class TeamInfo implements Serializable {
    
	public TeamInfo() {
	}
	
	/**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "uuid", strategy = "com.wonders.fzb.base.dao.generator.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
    @Column(name = "ID")
    private String id;
	
    /**
     * 单位名称
     */
    @Column(name = "UNITNAME")
    private String unitName;
    
    /**
     * 单位名称，多个#分割，与事业单位一起组成单位列表
     */
    @Column(name = "TEAMNAME")
    private String teamName;

	/**
     * 办公地址
     */
    @Column(name = "ADDRESS")
    private String address;
    
    /**
     * 邮编
     */
    @Column(name = "ZIP")
    private String zip;
    
    /**
     * 电话
     */
    @Column(name = "PHONE")
    private String phone;
    
    /**
     * 所属区县
     */
    @Column(name = "UNIT_AREA")
    private String unitArea;
    
    /**
     * 执法机构类别
     */
    @Column(name = "UNIT_TYPE")
    private String unitType;
    
    /**
     * 是否需要执法委托书
     */
    @Column(name = "IF_ENTRUST")
    private String ifEntust;
    
    /**
     * 职责简述
     */
    @Column(name = "INTRO")
    private String intro;
    
    /**
     * 录入日期
     */
    @Column(name = "INSERT_DATE")
    private Date insertDate;
    
    /**
     * Module-Org-Relate关联对象
     */
    @Transient
    private List<MOR> mor;
    
    @Transient
    private List<UserInfo> users;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUnitArea() {
		return unitArea;
	}

	public void setUnitArea(String unitArea) {
		this.unitArea = unitArea;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getIfEntust() {
		return ifEntust;
	}

	public void setIfEntust(String ifEntust) {
		this.ifEntust = ifEntust;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public List<MOR> getMor() {
		return mor;
	}

	public void setMor(List<MOR> mor) {
		this.mor = mor;
	}

	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}
}