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
 * 操作业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WEGOV_SERVICE_BULLETIN")
public class BulletinInfo implements Serializable {

	/**
	 * 构造一个空的函数，每一项赋空的值
	 */
	public BulletinInfo() {
	}
	
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
	@Column(name = "ST_BULLETIN_ID")
	public String BulletinId;

	@Column(name = "DT_BULLETIN_DATE")
	public Date BulletinDate;
	
	@Column(name = "DT_BULLETIN_END_DATE")
	public Date BulletinEndDate;
	
	@Column(name = "ST_POPUP")
	public String Popup;
	
	@Column(name = "ST_BULLETIN_DEPART")
	public String BulletinDepart;
	
	@Column(name = "ST_BULLETIN_TITLE")
	public String BulletinTitle;
	
	@Column(name = "BL_BULLETIN_CONTENT")
	public byte[] BulletinContent;

	@Column(name = "ST_BULLETIN_FLAG")
	public String BulletinFlag;

	@Column(name = "ST_BULLETIN_PID")
	public String BulletinPid;

	@Column(name = "ST_BULLETIN_PNAME")
	public String BulletinPname;

	@Column(name = "ST_BULLETIN_WIDTH")
	public String BulletinWidth;

	@Column(name = "ST_BULLETIN_HEIGHT")
	public String BulletinHeight;

	@Column(name = "ST_ARRANGE_ID")
	public String ArrangeId;

	@Column(name = "ST_BAK")
	public String BAK;

	@Column(name = "ST_BAK2")
	public String BAK2;

	@Column(name = "ST_BAK3")
	public String BAK3;
	
	@Column(name = "DT_BAK_DATE")
	public Date BAKDate;

	public String getBulletinId() {
		return BulletinId;
	}

	public void setBulletinId(String bulletinId) {
		BulletinId = bulletinId;
	}

	public Date getBulletinDate() {
		return BulletinDate;
	}

	public void setBulletinDate(Date bulletinDate) {
		BulletinDate = bulletinDate;
	}

	public Date getBulletinEndDate() {
		return BulletinEndDate;
	}

	public void setBulletinEndDate(Date bulletinEndDate) {
		BulletinEndDate = bulletinEndDate;
	}

	public String getPopup() {
		return Popup;
	}

	public void setPopup(String popup) {
		Popup = popup;
	}

	public String getBulletinDepart() {
		return BulletinDepart;
	}

	public void setBulletinDepart(String bulletinDepart) {
		BulletinDepart = bulletinDepart;
	}

	public String getBulletinTitle() {
		return BulletinTitle;
	}

	public void setBulletinTitle(String bulletinTitle) {
		BulletinTitle = bulletinTitle;
	}

	public byte[] getBulletinContent() {
		return BulletinContent;
	}

	public void setBulletinContent(byte[] bulletinContent) {
		BulletinContent = bulletinContent;
	}

	public String getBulletinFlag() {
		return BulletinFlag;
	}

	public void setBulletinFlag(String bulletinFlag) {
		BulletinFlag = bulletinFlag;
	}

	public String getBulletinPid() {
		return BulletinPid;
	}

	public void setBulletinPid(String bulletinPid) {
		BulletinPid = bulletinPid;
	}

	public String getBulletinPname() {
		return BulletinPname;
	}

	public void setBulletinPname(String bulletinPname) {
		BulletinPname = bulletinPname;
	}

	public String getStBulletinWidth() {
		return BulletinWidth;
	}

	public void setStBulletinWidth(String stBulletinWidth) {
		this.BulletinWidth = stBulletinWidth;
	}

	public String getBulletinHeight() {
		return BulletinHeight;
	}

	public void setBulletinHeight(String bulletinHeight) {
		BulletinHeight = bulletinHeight;
	}

	public String getArrangeId() {
		return ArrangeId;
	}

	public void setArrangeId(String arrangeId) {
		ArrangeId = arrangeId;
	}

	public String getBAK() {
		return BAK;
	}

	public void setBAK(String bAK) {
		BAK = bAK;
	}

	public String getBAK2() {
		return BAK2;
	}

	public void setBAK2(String bAK2) {
		BAK2 = bAK2;
	}

	public String getBAK3() {
		return BAK3;
	}

	public void setBAK3(String bAK3) {
		BAK3 = bAK3;
	}

	public Date getBAKDate() {
		return BAKDate;
	}

	public void setBAKDate(Date bAKDate) {
		BAKDate = bAKDate;
	}

	
}