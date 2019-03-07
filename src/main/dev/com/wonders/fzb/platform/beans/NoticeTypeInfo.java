package com.wonders.fzb.platform.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * WEGOV_P_NOTICE_TYPE 业务实体
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WEGOV_P_NOTICE_TYPE")
public class NoticeTypeInfo implements Serializable {

    /**
     * WEGOV_P_NOTICE_TYPE
     */
    public static final String WEGOV_P_NOTICE_TYPE = "WEGOV_P_NOTICE_TYPE";

    /**
     * TYPE_ID
     */
    public static final String TYPE_ID = "TYPE_ID";

    /**
     * DESCRIPT
     */
    public static final String DESCRIPT = "DESCRIPT";

    /**
     * ADVANCE_TIME
     */
    public static final String ADVANCE_TIME = "ADVANCE_TIME";

    /**
     * DELETE_TIME
     */
    public static final String DELETE_TIME = "DELETE_TIME";

    public NoticeTypeInfo() {
    }

    /**
     * TYPE_ID
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned")
    @GeneratedValue(generator = "id")
    @Column(name = "TYPE_ID")
    private String typeId;

    /**
     * DESCRIPT
     */
    @Column(name = "DESCRIPT")
    private String descript;

    /**
     * ADVANCE_TIME
     */
    @Column(name = "ADVANCE_TIME")
    private Long advanceTime;

    /**
     * DELETE_TIME
     */
    @Column(name = "DELETE_TIME")
    private Long deleteTime;

    //    @OneToMany(mappedBy="noticeTypeInfo",cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
    @Transient
    private List<NoticeModeInfo> noticeModes;

    public List<NoticeModeInfo> getNoticeModes() {
        return noticeModes;
    }

    public void setNoticeModes(List<NoticeModeInfo> noticeModes) {
        this.noticeModes = noticeModes;
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
//        if(noticeModes!=null){
//        	for(NoticeModeInfo mode:noticeModes){
//        		mode.setTypeId(typeId);
//        	}
//        }
    }

    /**
     * DESCRIPT
     */
    public String getDescript() {
        return this.descript;
    }

    /**
     * DESCRIPT
     */
    public void setDescript(String descript) {
        this.descript = descript;
    }

    /**
     * ADVANCE_TIME
     */
    public Long getAdvanceTime() {
        return this.advanceTime;
    }

    /**
     * ADVANCE_TIME
     */
    public void setAdvanceTime(Long advanceTime) {
        this.advanceTime = advanceTime;
    }

    /**
     * DELETE_TIME
     */
    public Long getDeleteTime() {
        return this.deleteTime;
    }

    /**
     * DELETE_TIME
     */
    public void setDeleteTime(Long deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public int compareTo(Object o){
        NoticeTypeInfo r = (NoticeTypeInfo)o;
        int ret = typeId.compareTo(r.typeId);
        return ret;
    }
}