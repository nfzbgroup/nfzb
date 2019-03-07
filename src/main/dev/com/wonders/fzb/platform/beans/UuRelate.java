package com.wonders.fzb.platform.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.hibernate.annotations.GenericGenerator;

/**
 * 平台用户与 个模块系统用户之间关联表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_C_UU_RELATE")
public class UuRelate implements Serializable {

	public UuRelate() {
	}

	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
	@Column(name = "UU_ID")
	public String uuId;
	/**
	 * 平台用户ID
	 */
	@Column(name = "PLANTFORM_ID")
	private String plantFormId;

	/**
	 * 模块系统ID
	 */
	@Column(name = "MODULE_ID")
	private String moduleId;

	/**
	 * 对应系统用户ID
	 */
	@Column(name = "MODULE_USER_ID")
	private String moduleUserId;
	/**
	 * 对应系统url
	 */
	@Column(name = "MODULE_URL")
	private String moduleUrl;

	public String getPlantFormId() {
		return plantFormId;
	}

	public void setPlantFormId(String plantFormId) {
		this.plantFormId = plantFormId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleUserId() {
		return moduleUserId;
	}

	public void setModuleUserId(String moduleUserId) {
		this.moduleUserId = moduleUserId;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

}