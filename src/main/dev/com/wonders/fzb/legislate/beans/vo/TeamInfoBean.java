package com.wonders.fzb.legislate.beans.vo;

import java.util.List;

import com.wonders.fzb.framework.beans.vo.UserInfoBean;

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
	 * 组织id
	 */
	private String orgId;
	
	/**
	 * 组织父id
	 */
	private String orgPid;
	
	/**
	 * 
	 */
	private String morId;
	
	private List<UserInfoBean>  list;

	
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgPid() {
		return orgPid;
	}

	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}

	public String getMorId() {
		return morId;
	}

	public void setMorId(String morId) {
		this.morId = morId;
	}

	public List<UserInfoBean> getList() {
		return list;
	}

	public void setList(List<UserInfoBean> list) {
		this.list = list;
	}
	
	

}
