package com.wonders.fzb.legislate.beans;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 立法计划表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_PLAN_TASK")
public class PlanTask {
	public PlanTask() {
		this.taskId = UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 任务编号
	 */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id") 
	@Column(name = "TASK_ID", unique = true, nullable = false, length = 32)
	private String taskId;

	/**
	 *所属用户
	 */
	@Column(name = "USER_ID")
	private String userId;
	/**
	 * 所属部门
	 */
	@Column(name = "TEAM_ID")
	private String teamId;
	/**
	 * 创建时间
	 */
	@Column(name = "TASK_TIME")
	private Date taskTime;
	/**
	 * 任务类别 1.报送  2.接收 3.分办 4.认领 5.办理
	 */
	@Column(name = "TASK_TYPE")
	private String taskType;
	/**
	 * 状态 0.待办 1.已办
	 */
	@Column(name = "STATUS")
	private String status;
	/**
	 * 处理时间
	 */
	@Column(name = "PROCESS_TIME")
	private Date processTime;
	/**
	 * 处理说明
	 */
	@Column(name = "INSTRUCTIONS")
	private String instructions;
	/**
	 * 立法计划编号
	 */
	@ManyToOne(fetch =FetchType.LAZY )
	@JoinColumn(name = "PLAN_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private Plan plan;
	/**
	 * 所属用户姓名
	 */
	@Column(name = "USER_NAME")
	private String userName;
	/**
	 * 所属部门名称
	 */
	@Column(name = "TEAM_NAME")
	private String teamName;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	

	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getTaskTime() {
		return taskTime;
	}
	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}
	public Date getProcessTime() {
		return processTime;
	}
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
	
}
