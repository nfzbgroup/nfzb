package com.wonders.fzb.legislation.beans;

import java.util.Date;


/**
 * 通用发送表查询出的统一列表页面展示的VO对象。
 * @author ACER
 *
 */
public class SendNoticeVO {
	
	private String stNoticeId;//发送的ID
	private String stDocId;//主信息ID，FK关联。
	private String stTile;
	private String stUser;
	private String stUnitName;
	
	private String stBak;
	private String stBak1;
	private String stBak2;
	
	private Date dtSendDate;
	private Date dtBakDate;
	public String getStNoticeId() {
		return stNoticeId;
	}
	public void setStNoticeId(String stNoticeId) {
		this.stNoticeId = stNoticeId;
	}
	public String getStDocId() {
		return stDocId;
	}
	public void setStDocId(String stDocId) {
		this.stDocId = stDocId;
	}
	public String getStTile() {
		return stTile;
	}
	public void setStTile(String stTile) {
		this.stTile = stTile;
	}
	public String getStUser() {
		return stUser;
	}
	public void setStUser(String stUser) {
		this.stUser = stUser;
	}
	public String getStUnitName() {
		return stUnitName;
	}
	public void setStUnitName(String stUnitName) {
		this.stUnitName = stUnitName;
	}
	public String getStBak() {
		return stBak;
	}
	public void setStBak(String stBak) {
		this.stBak = stBak;
	}
	public String getStBak1() {
		return stBak1;
	}
	public void setStBak1(String stBak1) {
		this.stBak1 = stBak1;
	}
	public String getStBak2() {
		return stBak2;
	}
	public void setStBak2(String stBak2) {
		this.stBak2 = stBak2;
	}
	public Date getDtSendDate() {
		return dtSendDate;
	}
	public void setDtSendDate(Date dtSendDate) {
		this.dtSendDate = dtSendDate;
	}
	public Date getDtBakDate() {
		return dtBakDate;
	}
	public void setDtBakDate(Date dtBakDate) {
		this.dtBakDate = dtBakDate;
	}
	
	
}
