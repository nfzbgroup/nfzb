package com.wonders.fzb.base.logging.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.wonders.fzb.base.consts.LogConst;

/**
 * 
 * @author ZSW
 * 2016-06-01
 */
@Entity
@Table(name = "WEGOV_LOG_INFO")
public class LogInfo implements Serializable {
	
	private static final long serialVersionUID = 4856093710664628513L;

	public LogInfo(){
	}
	
	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "uuid", strategy = "com.wonders.fzb.base.dao.generator.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "UUID")
	private String uuid;

	/**
	 * 日志信息
	 */
	@Column(name="ctx")
	private String ctx;
	
	/**
	 * 日志类别（参照常量类LogConst）
	 */
	@Column(name="category")
	private String category;
	
	/**
	 * 日志级别（参照常量类LogConst）
	 */
	@Column(name="rank")
	private String rank;
	
	/**
	 * 	日志记录时间
	 */
	@Column(name="time")
	private Date time;
	
	/**
	 * 触发类名
	 */
	@Column(name="class")
	private String className;
	
	/**
	 * 触发方法名
	 */
	@Column(name="method")
	private String methodName;
	
	/**
	 * 执行参数（JSON格式）
	 */
	@Column(name="params")
	private String params;
	
	/**
	 * 返回值(JSON格式)
	 */
	@Column(name="result")
	private String result;
	
	/**
	 * 是否是父级 0不是 1是
	 */
	@Column(name="isParent")
	private int isParent = 0;
	
	/**
	 * 父级UUID
	 */
	@Column(name="previous")
	private String previous;
	
	/**
	 * 操作IP/计算机名
	 */
	@Column(name="op_host")
	private String host;
	
	/**
	 * 操作人ID
	 */
	@Column(name="op_usr")
	private String usr;
	
	/**
	 * 资源ID
	 */
	@Column(name="op_res_id")
	private String resId;
	
	/**
	 * 资源类型
	 */
	@Column(name="op_res_type")
	private String resType;
	
	/**
	 * 日志模式 
	 * 0 手动记录 1 自动记录
	 */
	@Column(name="log_mode")
	private int mode = 1;
	
	/**
	 * 日志记录委托类（包括包名）
	 * 一般是LOG_MODE = 1 自动记录的委托类
	 */
	@Column(name="delegation")
	private String delegation;
	
	/**
	 * 执行用时
	 */
	@Column(name="cost_ms")
	private int cost;
	
	/**
	 * 可见标记 
	 * 0 可见 1 不可见
	 */
	@Column(name = "visible")
	private int visible = 0;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCtx() {
		return ctx;
	}

	public void setCtx(String ctx) {
		this.ctx = ctx;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setCategory(LogConst.Category category) {
		this.category = category.getValue();
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public void setRank(LogConst.Rank rank) {
		this.rank = rank.getValue();
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getIsParent() {
		return isParent;
	}

	public void setIsParent(int isParent) {
		this.isParent = isParent > 1 ? 1 : isParent;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode > 1 ? 1 : mode;
	}

	public String getDelegation() {
		return delegation;
	}

	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible > 1 ? 1 : visible;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
