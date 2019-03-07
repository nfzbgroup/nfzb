package com.wonders.fzb.platform.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 单位业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_TEAM_INFO")
public class TeamInfoOld implements Serializable {
	
	public TeamInfoOld() {
	}
	/**
	 * 单位标识
	 */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
	@Column(name = "TEAM_ID")
	public String teamId;
	
	/**
	 * 单位姓名
	 */
	@Column(name = "TEAM_NAME")
	public String teamName;

	/**
	 * 单位简称
	 */
	@Column(name = "TEAM_ABBR")
	public String teamAbbr;

	/**
	 * 组织文号
	 */
	@Column(name = "TEAM_DOCNO")
	public String teamDocno;
	
	/**
	 * 组织类型
	 */
	@Column(name = "TEAM_GROUP")
	public String teamGroup;
	
	/**
	 * 单位排序
	 */
	@Column(name = "TEAM_ORDER")
	public int teamOrder;
	
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

	public String getTeamAbbr() {
		return teamAbbr;
	}

	public void setTeamAbbr(String teamAbbr) {
		this.teamAbbr = teamAbbr;
	}

	public String getTeamDocno() {
		return teamDocno;
	}

	public void setTeamDocno(String teamDocno) {
		this.teamDocno = teamDocno;
	}

	public String getTeamGroup() {
		return teamGroup;
	}

	public void setTeamGroup(String teamGroup) {
		this.teamGroup = teamGroup;
	}

	public int getTeamOrder() {
		return teamOrder;
	}

	public void setTeamOrder(int teamOrder) {
		this.teamOrder = teamOrder;
	}

}