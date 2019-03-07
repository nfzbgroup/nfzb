package com.wonders.fzb.demo.beans;

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
@Table(name = "FZB_DEMO_COMPOSER")
public class ComposerDemo {
	/**
	 * 主键
	 */
	@Id
	@Column(name = "COMPOSER_ID")
	private String composerId;
	
	/**
	 * 类型
	 */
	@Column(name = "COMPOSER_TYPE")
	private String composerType;
	
	/**
	 * 内容
	 */
	@Column(name = "COMPOSER_BLOB")
	private byte[] composerBlob;
	
	/**
	 * 时间
	 */
	@Column(name = "COMPOSER_DATE")
	private Date composerDate;

	public String getComposerId() {
		return composerId;
	}

	public void setComposerId(String composerId) {
		this.composerId = composerId;
	}

	public String getComposerType() {
		return composerType;
	}

	public void setComposerType(String composerType) {
		this.composerType = composerType;
	}

	public byte[] getComposerBlob() {
		return composerBlob;
	}

	public void setComposerBlob(byte[] composerBlob) {
		this.composerBlob = composerBlob;
	}

	public Date getComposerDate() {
		return composerDate;
	}

	public void setComposerDate(Date composerDate) {
		this.composerDate = composerDate;
	}
	
}
