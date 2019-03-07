package com.wonders.fzb.platform.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;

/**
 * WEGOV_P_NOTICE_MODE 业务实体
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WEGOV_P_NOTICE_MODE")
@IdClass(NoticeModeInfoPK.class)
public class NoticeModeInfo implements Serializable {

    /**
     * WEGOV_P_NOTICE_MODE
     */
    public static final String WEGOV_P_NOTICE_MODE = "WEGOV_P_NOTICE_MODE";

    /**
     * TYPE_ID
     */
    public static final String TYPE_ID = "TYPE_ID";

    /**
     * INTERVAL
     */
    public static final String INTERVAL = "INTERVAL";

    /**
     * NOTICE_MODE
     */
    public static final String NOTICE_MODE = "NOTICE_MODE";

    public NoticeModeInfo() {
    }

    /**
     * TYPE_ID
     */
    @Id
    private String typeId;

    /**
     * INTERVAL
     */
    @Column(name = "INTERVAL")
    private Long interval = new Long(0);

    /**
     * NOTICE_MODE
     */
    @Id
    private String noticeMode;

    //	@ManyToOne(targetEntity = NoticeTypeInfo.class, fetch = FetchType.LAZY)
//	@JoinColumn(name = "TYPE_ID")
    @Transient
    private NoticeTypeInfo noticeTypeInfo;

    /**
     * TYPE_ID
     */
    public String getTypeId() {
        return this.typeId;
    }

    /**
     * TYPE_ID
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * INTERVAL
     */
    public Long getInterval() {
        return this.interval;
    }

    /**
     * INTERVAL
     */
    public void setInterval(Long interval) {
        this.interval = interval;
    }

    /**
     * NOTICE_MODE
     */
    public String getNoticeMode() {
        return this.noticeMode;
    }

    /**
     * NOTICE_MODE
     */
    public void setNoticeMode(String noticeMode) {
        this.noticeMode = noticeMode;
    }

    public NoticeTypeInfo getNoticeTypeInfo() {
        return noticeTypeInfo;
    }

    public void setNoticeTypeInfo(NoticeTypeInfo noticeTypeInfo) {
        this.noticeTypeInfo = noticeTypeInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}