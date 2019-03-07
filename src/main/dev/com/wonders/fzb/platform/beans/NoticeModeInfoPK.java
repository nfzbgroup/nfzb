package com.wonders.fzb.platform.beans;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class NoticeModeInfoPK implements java.io.Serializable {
    
    /**
     * TYPE_ID
     */
    @Column(name = "TYPE_ID")
    private String typeId;
    
    /**
     * NOTICE_MODE
     */
    @Column(name = "NOTICE_MODE")
    private String noticeMode;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getNoticeMode() {
		return noticeMode;
	}

	public void setNoticeMode(String noticeMode) {
		this.noticeMode = noticeMode;
	}

	public NoticeModeInfoPK() {
	}

	@Override
	public boolean equals(Object o) {
	   if(o instanceof AppConfigPK) {
		   NoticeModeInfoPK pk = (NoticeModeInfoPK)o;
		  if(this.typeId == pk.getTypeId() && this.noticeMode.equals(pk.getNoticeMode())) {
			  return true;
		  }
	   }
	   return false;
	}
	 
	@Override
	public int hashCode() {
	   return 29 * this.typeId.hashCode() + this.noticeMode.hashCode();
	}

}
