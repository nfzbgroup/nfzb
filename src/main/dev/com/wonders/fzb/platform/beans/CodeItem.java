package com.wonders.fzb.platform.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 操作业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_CODE_INFO")
public class CodeItem implements Serializable {

	/**
	 * 构造一个空的函数，每一项赋空的值
	 */
	public CodeItem() {
		this.stTid = null;
		this.stPid = null;
		this.stName = null;
		this.stAppName = null;
		this.stDesc = null;
		this.stFlag = null;
	}

	/**
	 * 字典代码标识
	 */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
	@Column(name = "ST_TID")
	public String stTid;

	/**
	 * 父代码标识
	 */
	@Column(name = "ST_PID")
	public String stPid;

	/**
	 * 字典代码名称
	 */
	@Column(name = "ST_NAME")
	public String stName;

	/**
	 * 字典代码值
	 */
	@Column(name = "ST_APP_NAME")
	public String stAppName;

	/**
	 * 描述
	 */
	@Column(name = "ST_DESC")
	public String stDesc;

	/**
	 * 代码属性标志
	 */
	@Column(name = "ST_FLAG")
	public String stFlag;

	/**
	 * 当等于000001时常量为VISIBLE,表示代码是可见的
	 */
	public static final String VISIBLE = "000000";
	/**
	 * 当等于0000000时表示代码是不可删除的
	 */
	public static final String UNDELETABLE = "0000000";
	/**
	 * 当等于0000001时表示代码是不可修改不可删除的
	 */
	public static final String UNMODIFYABLE = "0000001";
	/**
	 * 当等于000000时常量为UNVISIBLE,表示代码是不可见的
	 */
	public static final String UNVISIBLE = "000001";

	/**
	 * 构造函数－－对应于数据库中的每一个域
	 */
	public CodeItem(String stTid, String stPid, String stName, String stAppName, String stDesc, String stFlag) {
		this.stTid = stTid;
		this.stPid = stPid;
		this.stName = stName;
		this.stAppName = stAppName;
		this.stDesc = stDesc;
		this.stFlag = stFlag;
	}

	/**
	 * 构造函数－－对应于数据库中的每一个域，除了ST_APP_NAME和ST_DESC两个为空的域
	 */
	public CodeItem(String stTid, String stPid, String stName, String stFlag) {
		this.stTid = stTid;
		this.stPid = stPid;
		this.stName = stName;
		this.stFlag = stFlag;
	}

	public String getStTid() {
		return stTid;
	}

	public void setStTid(String stTid) {
		this.stTid = stTid;
	}

	public String getStPid() {
		return stPid;
	}

	public void setStPid(String stPid) {
		this.stPid = stPid;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getStAppName() {
		return stAppName;
	}

	public void setStAppName(String stAppName) {
		this.stAppName = stAppName;
	}

	public String getStDesc() {
		return stDesc;
	}

	public void setStDesc(String stDesc) {
		this.stDesc = stDesc;
	}

	public String getStFlag() {
		return stFlag;
	}

	public void setStFlag(String stFlag) {
		this.stFlag = stFlag;
	}

}