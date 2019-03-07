package com.wonders.fzb.base.logging.beans;

import com.wonders.fzb.base.beans.FastQueryCondition;
import com.wonders.fzb.base.beans.FastQueryConditionParser;

/**
 * 日志查询条件
 * @author ZSW
 */
public class LoggingQueryBean extends FastQueryConditionParser {
	
	public LoggingQueryBean(@SuppressWarnings("rawtypes") Class beanClass) {
		super(beanClass);
	}

	private FastQueryCondition ctx;
	
	private FastQueryCondition category;
	
	private FastQueryCondition rank;
	
	private FastQueryCondition time;
	
	private FastQueryCondition method;
	
	private FastQueryCondition className;
	
	private FastQueryCondition params;
	
	private FastQueryCondition result;
	
	private FastQueryCondition host;
	
	private FastQueryCondition usr;
	
	private FastQueryCondition mode;

	public FastQueryCondition getCtx() {
		return ctx;
	}

	public void setCtx(FastQueryCondition ctx) {
		this.ctx = ctx;
	}

	public FastQueryCondition getCategory() {
		return category;
	}

	public void setCategory(FastQueryCondition category) {
		this.category = category;
	}

	public FastQueryCondition getRank() {
		return rank;
	}

	public void setRank(FastQueryCondition rank) {
		this.rank = rank;
	}

	public FastQueryCondition getTime() {
		return time;
	}

	public void setTime(FastQueryCondition time) {
		this.time = time;
	}

	public FastQueryCondition getMethod() {
		return method;
	}

	public void setMethod(FastQueryCondition method) {
		this.method = method;
	}

	public FastQueryCondition getClassName() {
		return className;
	}

	public void setClassName(FastQueryCondition className) {
		this.className = className;
	}

	public FastQueryCondition getParams() {
		return params;
	}

	public void setParams(FastQueryCondition params) {
		this.params = params;
	}

	public FastQueryCondition getResult() {
		return result;
	}

	public void setResult(FastQueryCondition result) {
		this.result = result;
	}

	public FastQueryCondition getHost() {
		return host;
	}

	public void setHost(FastQueryCondition host) {
		this.host = host;
	}

	public FastQueryCondition getUsr() {
		return usr;
	}

	public void setUsr(FastQueryCondition usr) {
		this.usr = usr;
	}

	public FastQueryCondition getMode() {
		return mode;
	}

	public void setMode(FastQueryCondition mode) {
		this.mode = mode;
	}
}
