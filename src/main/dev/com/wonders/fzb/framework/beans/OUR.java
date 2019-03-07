package com.wonders.fzb.framework.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ORG_USER_RELATE，只做中间表关联PO，没有直接的数据操作此类不需要直接使用
 * @author ZSW
 */
@Entity
@Table(name = "WFC_C_OU_RELATE")
public class OUR implements Serializable {
	private static final long serialVersionUID = 2056748814544802318L;
	
	/**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "uuid", strategy = "com.wonders.fzb.base.dao.generator.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
    @Column(name = "ID")
    private String id;
	
    @Column(name="ORG_RELA_ID")
    private String morId;
    
    @Column(name="USER_ID")
    private String userId;
    
    @Column(name="TYPE")
    private String type;
    
    @Column(name="SORT")
    private Integer sort;
    
    @Column(name="INSERTDATE")
    private Date insertDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMorId() {
		return morId;
	}

	public void setMorId(String morId) {
		this.morId = morId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}
