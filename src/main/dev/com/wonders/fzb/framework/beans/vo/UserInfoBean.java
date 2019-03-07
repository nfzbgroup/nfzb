package com.wonders.fzb.framework.beans.vo;

import java.util.HashMap;

/**
 * 监管平台用户综合VO类
 */
public class UserInfoBean {
	/**
	 * UserInfo ID
	 */
	private String userId;
	
	/**
	 * 名字
	 */
	private String name;
	
	/**
	 * 帐号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 加密key
	 */
	private String salt;
	
	/**
	 * 联系人
	 */
	private String abbr;
	
	/**
	 * 联系电话
	 */
	private String phone;
	
	private String accountStatus;
	
	private String moduleId;
	
	private String moduleName;
	
	private String teamId;
	
	private String teamName;
	
	private String morId;
	
	private String ourId;
	
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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

	public String getMorId() {
		return morId;
	}

	public void setMorId(String morId) {
		this.morId = morId;
	}

	public String getOurId() {
		return ourId;
	}

	public void setOurId(String ourId) {
		this.ourId = ourId;
	}
	
	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	//key = id,value = name
	private HashMap<String,String> modules = new HashMap<String, String>();
	
	//key = id,value = name
	private HashMap<String,String> teams = new HashMap<String, String>();
	
	//key = morId,value = teamId
	private HashMap<String,String> mors = new HashMap<String, String>();
	
	//key = oursId,value = morsId
	private HashMap<String,String> ours = new HashMap<String, String>();
}
