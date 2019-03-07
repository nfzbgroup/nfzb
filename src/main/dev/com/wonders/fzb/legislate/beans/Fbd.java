package com.wonders.fzb.legislate.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 分办单
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_FBD")
public class Fbd {
	/**
	 * 分办单编号
	 */
	@Id
	@Column(name = "fbd_id")
	private String fbdId;
	
	/**
	 * 序号
	 */
	@Column(name = "seq")
	private String seq;
	
	
	/**
	 * 办理人姓名
	 */
	@Column(name = "user_name")
	private String userName;
	
	/**
	 * 来文单位名称
	 */
	@Column(name = "team_name")
	private String teamName;
	
	/**
	 * 来文单位
	 */
	@Column(name = "team_id")
	private String teamId;
	
	/**
	 * 接收时间
	 */
	@Column(name = "receive_time")
	private Date receiveTime;
	
	/**
	 * 办理说明
	 */
	@Column(name = "instructions")
	private String instructions;
	
	/**
	 * 处理时间
	 */
	@Column(name = "process_time")
	private Date processTime;
	
	/**
	 * 办理人
	 */
	@Column(name = "user_id")
	private String userId;
	
	/**
	 * 年份
	 */
	@Column(name = "year")
	private String year;

	public String getFbdId() {
		return fbdId;
	}

	public void setFbdId(String fbdId) {
		this.fbdId = fbdId;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
}
