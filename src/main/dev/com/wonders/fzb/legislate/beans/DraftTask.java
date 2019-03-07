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
 * 立法任务表
 * 
 * @author FZB
 * 
 */
@Entity
@Table(name = "WEGOV_LF_DRAFT_TASK")
public class DraftTask {
	public DraftTask() {
		this.taskId = UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 * 任务编号
	 */
	@Id
	@Column(name = "task_id", unique = true, nullable = false, length = 32)
	@GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id") 
	private String taskId;
	
	/**
	 * 所属用户
	 */
	@Column(name = "user_id")
	private String userId;
	
	/**
	 * 所属部门
	 */
	@Column(name = "team_id")
	private String teamId;
	
	/**
	 * 所属用户姓名
	 */
	@Column(name = "user_name")
	private String userName;
	
	/**
	 * 所属部门姓名
	 */
	@Column(name = "team_name")
	private String teamName;
	
	/**
	 * 创建时间
	 */
	@Column(name = "task_time")
	private Date taskTime;
	
	/**
	 * 任务类别 1.报送  2.接收 3.分办 4.认领 5.办理
	 */
	@Column(name = "task_type")
	private String taskType;
	
	/**
	 * 状态 0.待办 1.已办
	 */
	@Column(name = "status")
	private String status;
	
	/**
	 * 处理时间
	 */
	@Column(name = "process_time")
	private Date processTime;
	
	/**
	 * 处理说明
	 */
	@Column(name = "instructions")
	private String instructions;
	
	/**
	 * 草案编号
	 */
	@ManyToOne(fetch =FetchType.LAZY )
	@JoinColumn(name = "draft_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Draft draft;

	
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

	public Date getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
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

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Draft getDraft() {
		return draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}

}
