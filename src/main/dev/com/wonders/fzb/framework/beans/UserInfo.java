package com.wonders.fzb.framework.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户 业务实体
 */
@Entity
@Table(name = "WFC_C_USER_INFO")
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = -5794391912555922668L;

	/**
	 * 用户标识
	 */
	@Id
	@GenericGenerator(name = "uuid", strategy = "com.wonders.fzb.base.dao.generator.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "USER_ID")
	private String userId;
	
	/**
	 * 用户姓名
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * 性别
	 * 0女 1男
	 */
	@Column(name = "SEX")
	private int sex;

	/**
	 * 电子邮件
	 */
	@Column(name = "EMAIL")
	private String email;

	/**
	 * 用户账号
	 */
	@Column(name = "ACCOUNT")
	private String account;

	/**
	 * 用户密码
	 * 以fzb_user_加salt字段作为加密的key以AES方式加密
	 */
	@Column(name = "PASSWORD")
	private String password;
	
	/**
	 * 加密字段
	 */
	@Column(name = "salt")
	private String salt;

	/**
	 * 用户状态
	 */
	@Column(name = "ACCOUNTSTATUS")
	private String accountStatus;

	/**
	 * 姓名缩写
	 */
	@Column(name = "ABBR")
	private String abbrName;

	/**
	 * 电话
	 */
	@Column(name = "PHONE")
	private String phone;

	/**
	 * 地址
	 */
	@Column(name = "ADDRESS")
	private String address;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATETIME")
	private Date createTime;

	/**
	 * 用户当前使用IP
	 */
	@Column(name = "USERIP")
	private String userIp;
	
//	/**
//	 * 用户当前使用IP
//	 */
//	@Column(name = "OFFICEPHONE")
//	private String officePhone;

	@Transient
	private List<TeamInfo> teamInfos;

	@Transient
	private Boolean checked;
	
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrName() {
		return this.abbrName;
	}

	public void setAbbrName(String abbrName) {
		this.abbrName = abbrName;
	}

	public int getSex() {
		return this.sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserIp() {
		return this.userIp;
	}
	
	public List<TeamInfo> getTeamInfos() {
		return teamInfos;
	}

	public void setTeamInfos(List<TeamInfo> teamInfos) {
		this.teamInfos = teamInfos;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

//	public String getOfficePhone() {
//		return officePhone;
//	}
//
//	public void setOfficePhone(String officePhone) {
//		this.officePhone = officePhone;
//	}
	
}