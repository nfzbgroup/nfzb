package com.wonders.fzb.framework.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "WFC_C_SYS_MODULE")
public class SystemModule implements Serializable {
	private static final long serialVersionUID = 1640442955602911172L;
	
	/**
	 * 主键ID
	 * 尽量避免使用Seq来生成主键，使用UUID
	 */
	@Id
	@GenericGenerator(name = "uuid", strategy = "com.wonders.fzb.base.dao.generator.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "UUID")
	private String id;
	
	/**
	 * 系统名，例如：执法人员管理系统
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 系统模块名，例如：execlaw
	 */
	@Column(name="module_name")
	private String moduleName;
	
	/**
	 * 系统排序字段
	 */
	@Column(name="sort")
	private int sort;
	
	/**
	 * -1=禁用，0=不可见，1=启用，默认启用
	 */
	@Column(name="status")
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
