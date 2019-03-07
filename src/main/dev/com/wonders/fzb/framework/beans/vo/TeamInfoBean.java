package com.wonders.fzb.framework.beans.vo;

/**
 * 监管平台用户综合VO类
 */
public class TeamInfoBean {
	/**
	 * teamInfo ID
	 */
	private String teamId;
	
	/**
	 * 单位名字
	 */
	private String unitName;
	
	/**
	 * 组织帐号
	 */
	private String teamName;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 单位类型
	 */
	private String unitType;

	private String moduleId;
	
	private String moduleName;
	
	private String morId;

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
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

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
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

	public String getMorId() {
		return morId;
	}

	public void setMorId(String morId) {
		this.morId = morId;
	}
}
