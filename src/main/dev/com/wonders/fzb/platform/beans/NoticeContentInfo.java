package com.wonders.fzb.platform.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * WEGOV_P_NOTICE_CONTENT 业务实体
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WEGOV_P_NOTICE_CONTENT")
public class NoticeContentInfo implements Serializable {

    /**
     * WEGOV_P_NOTICE_CONTENT
     */
    public static final String WEGOV_P_NOTICE_CONTENT = "WEGOV_P_NOTICE_CONTENT";

    /**
     * SEQ_ID
     */
    public static final String SEQ_ID = "SEQ_ID";

    /**
     * USER_ID
     */
    public static final String USER_ID = "USER_ID";

    /**
     * TYPE_ID
     */
    public static final String TYPE_ID = "TYPE_ID";

    /**
     * CONTENT_ID
     */
    public static final String CONTENT_ID = "CONTENT_ID";

    /**
     * CONTENT
     */
    public static final String CONTENT = "CONTENT";

    /**
     * LIMIT_TIME
     */
    public static final String LIMIT_TIME = "LIMIT_TIME";

    /**
     * NOTICE_TIME
     */
    public static final String NOTICE_TIME = "NOTICE_TIME";

    /**
     * IMPORTANCE
     */
    public static final String IMPORTANCE = "IMPORTANCE";

    public NoticeContentInfo() {
    }

    /**
     * SEQ_ID
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned")
    @GeneratedValue(generator = "id")
    @Column(name = "SEQ_ID")
    private String seqId;

    /**
     * USER_ID
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * TYPE_ID
     */
    @Column(name = "TYPE_ID")
    private String typeId;

    /**
     * CONTENT_ID
     */
    @Column(name = "CONTENT_ID")
    private String contentId;

    /**
     * CONTENT
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * LIMIT_TIME
     */
    @Column(name = "LIMIT_TIME")
    private Date limitTime;

    /**
     * NOTICE_TIME
     */
    @Column(name = "NOTICE_TIME")
    private Date noticeTime;

    /**
     * IMPORTANCE
     */
    @Column(name = "IMPORTANCE")
    private Integer importance;

    /**
     * SEQ_ID
     */
    public String getSeqId() {
        return this.seqId;
    }

    /**
     * SEQ_ID
     */
    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    /**
     * USER_ID
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

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
     * CONTENT_ID
     */
    public String getContentId() {
        return this.contentId;
    }

    /**
     * CONTENT_ID
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * CONTENT
     */
    public String getContent() {
        return this.content;
    }

    /**
     * CONTENT
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * LIMIT_TIME
     */
    public Date getLimitTime() {
        return this.limitTime;
    }

    /**
     * LIMIT_TIME
     */
    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
    }

    /**
     * NOTICE_TIME
     */
    public Date getNoticeTime() {
        return this.noticeTime;
    }

    /**
     * NOTICE_TIME
     */
    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    /**
     * IMPORTANCE
     */
    public Integer getImportance() {
        return this.importance;
    }

    /**
     * IMPORTANCE
     */
    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}