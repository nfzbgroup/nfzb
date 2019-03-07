package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 立法计划表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_PLAN_SUMMARY")
public class PlanSummary {
	/**
	 * 汇总编号
	 */
	@Id
	@Column(name = "SUMMARY_ID", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String summaryId;

	/**
	 * 汇总年份
	 */
	@Column(name = "YEAR")
	private String year;
	/**
	 * 汇总处室
	 */
	@Column(name = "TEAM_ID")
	private String teamId;
	/**
	 *汇总处室名称
	 */
	@Column(name = "TEAM_NAME")
	private String teamName;
	
	/**
	 * 最后汇总时间
	 */
	@Column(name = "LAST_TIME")
	private Date lastTime;	
	/**
	 * 首次汇总时间
	 */
	@Column(name = "FIRST_TIME")
	private Date firstTime;
	/**
	 * 最后汇总人姓名
	 */
	@Column(name = "USER_NAME")
	private String userName;
	/**
	 *最后汇总人
	 */
	@Column(name = "USER_ID")
	private String userId;
	public String getSummaryId() {
		return summaryId;
	}
	public void setSummaryId(String summaryId) {
		this.summaryId = summaryId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Date getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

}
