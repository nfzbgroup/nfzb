package com.wonders.fzb.platform.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户 业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_USER_INFO")
public class UserInfoOld implements Serializable, HttpSessionBindingListener {
	
	public static final String ACCOUNT_STATE_USABLE="y";
    public static final String ACCOUNT_STATE_NOT_USABLE="n";
    public static final String ACCOUNT_STATE_DELETED="d";
    public static final String LOGIN_STATUS_ON="1";
    public static final String LOGIN_STATUS_OFF="0";
	
	public UserInfoOld() {
	}
	
	/**
	 * 用户标识
	 */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
	@Column(name = "USER_ID")
	public String userId;
	
	/**
	 * 用户姓名
	 */
	@Column(name = "NAME")
	public String name;
	
	/**
	 * 性别
	 */
	@Column(name = "SEX")
	public String sex;

	/**
	 * 电子邮件
	 */
	@Column(name = "EMAIL")
	public String email;

	/**
	 * 用户账号
	 */
	@Column(name = "ACCOUNT")
	public String account;

	/**
	 * 用户密码
	 */
	@Column(name = "PASSWORD")
	public String password;

	/**
	 * 用户状态
	 */
	@Column(name = "ACCOUNTSTATE")
	public String accountState;

	/**
	 * 用户登录状态
	 * 
	 */
	@Column(name = "LOGINSTATUS")
	public String loginStatus;

	/**
	 * 用户最后登录时间
	 * 
	 */
	@Column(name = "LASTLOGINTIME")
	public Date lastLoginTime;

	/**
	 * 用户最后登录IP
	 */
	@Column(name = "LASTLOGINIP")
	public String lastLoginIp;

	/**
	 * 用户最后登录主机名
	 */
	@Column(name = "LASTLOGINHOST")
	public String lastLoginHost;

	/**
	 * 用户最后登出时间
	 * 
	 */
	@Column(name = "LASTLOGOFFTIME")
	public Date lastLogoffTime;

	/**
	 * 用户最后登录失败时间
	 * 
	 */
	@Column(name = "LASTLOGINFAILEDTIME")
	public Date lastLoginFailedTime;

	/**
	 * 用户最后登录失败IP
	 */
	@Column(name = "LASTLOGINFAILEDIP")
	public String lastLoginFailedIp;

	/**
	 * 用户最后登录失败主机名
	 */
	@Column(name = "LASTLOGINFAILEDHOST")
	public String lastLoginFailedHost;
	/**
	 * 姓名缩写
	 */
	@Column(name = "ABBR")
	public String abbrName;

	/**
	 * 用户描述
	 */
	@Column(name = "DESCRIPTION")
	public String description;

	/**
	 * 电话
	 */
	@Column(name = "PHONE")
	public String phone;

	/**
	 * 地址
	 */
	@Column(name = "ADDRESS")
	public String address;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATETIME")
	public Date createTime;

	/**
	 * 删除时间
	 */
	@Column(name = "DELETETIME")
	public Date deleteTime;

	/**
	 * 用户当前使用IP
	 */
	@Column(name = "USERIP")
	public String userIp;

	@Transient
	public List<TeamInfoOld> teamInfos;
	
	/**
	 * ST_USER_ID
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * ST_USER_ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ST_ACCOUNT
	 */
	public String getAccount() {
		return this.account;
	}

	/**
	 * ST_ACCOUNT
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * ST_NAME
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * ST_NAME
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ST_ABBR_NAME
	 */
	public String getAbbrName() {
		return this.abbrName;
	}

	/**
	 * ST_ABBR_NAME
	 */
	public void setAbbrName(String abbrName) {
		this.abbrName = abbrName;
	}

	/**
	 * ST_SEX
	 */
	public String getSex() {
		return this.sex;
	}

	/**
	 * ST_SEX
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * ST_PASSWORD
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * ST_PASSWORD
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ST_EMAIL
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * ST_EMAIL
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * ST_PHONE
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * ST_PHONE
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * ST_ADDRESS
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * ST_ADDRESS
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * DT_CREAT
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * DT_CREAT
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * DT_DELETE
	 */
	public Date getDeleteTime() {
		return this.deleteTime;
	}

	/**
	 * DT_DELETE
	 */
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	/**
	 * ST_DESCRIPTION
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * ST_DESCRIPTION
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ST_LOGIN_STATUS
	 */
	public String getLoginStatus() {
		return this.loginStatus;
	}

	/**
	 * ST_LOGIN_STATUS
	 */
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	/**
	 * DT_LAST_LOGIN
	 */
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 * DT_LAST_LOGIN
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * ST_LAST_IP
	 */
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	/**
	 * ST_LAST_IP
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * ST_LAST_HOST
	 */
	public String getLastLoginHost() {
		return this.lastLoginHost;
	}

	/**
	 * ST_LAST_HOST
	 */
	public void setLastLoginHost(String lastLoginHost) {
		this.lastLoginHost = lastLoginHost;
	}

	/**
	 * DT_LAST_LOGOUT
	 */
	public Date getLastLogoffTime() {
		return this.lastLogoffTime;
	}

	/**
	 * DT_LAST_LOGOUT
	 */
	public void setLastLogoffTime(Date lastLogoffTime) {
		this.lastLogoffTime = lastLogoffTime;
	}

	/**
	 * DT_LAST_FAIL_LOGIN
	 */
	public Date getLastLoginFailedTime() {
		return this.lastLoginFailedTime;
	}

	/**
	 * DT_LAST_FAIL_LOGIN
	 */
	public void setLastLoginFailedTime(Date lastLoginFailedTime) {
		this.lastLoginFailedTime = lastLoginFailedTime;
	}

	/**
	 * ST_LAST_FAIL_IP
	 */
	public String getLastLoginFailedIp() {
		return this.lastLoginFailedIp;
	}

	/**
	 * ST_LAST_FAIL_IP
	 */
	public void setLastLoginFailedIp(String lastLoginFailedIp) {
		this.lastLoginFailedIp = lastLoginFailedIp;
	}

	/**
	 * ST_LAST_FAIL_HOST
	 */
	public String getLastLoginFailedHost() {
		return this.lastLoginFailedHost;
	}

	/**
	 * ST_LAST_FAIL_HOST
	 */
	public void setLastLoginFailedHost(String lastLoginFailedHost) {
		this.lastLoginFailedHost = lastLoginFailedHost;
	}

	/**
	 * ST_IP
	 */
	public String getUserIp() {
		return this.userIp;
	}
	
	public List<TeamInfoOld> getTeamInfos() {
		return teamInfos;
	}

	public void setTeamInfos(List<TeamInfoOld> teamInfos) {
		this.teamInfos = teamInfos;
	}

	/**
	 * ST_IP
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	
	public String getAccountState() {
		return accountState;
	}

	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		//UserInfoService userInfoService = (UserInfoService) AppContext.getSingleBean(UserInfoService.class);
		//userInfoService.logout(userId, false);
		this.account = null;
		this.userId = null;
	}
	
}