package com.wonders.fzb.framework.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * MODULE_ORG_RELATE，只做中间表关联PO，没有直接的数据操作此类不需要直接使用
 * @author ZSW
 */
@Entity
@Table(name = "WFC_C_MO_RELATE")
public class MOR implements Serializable {	
	private static final long serialVersionUID = 3268089846302089042L;
	
	/**
     * 主键ID
     */
	@Id
	@GenericGenerator(name = "id", strategy = "assigned")
	@GeneratedValue(generator = "id")
	@Column(name = "ID")
    private String id;
  
    /**
     * 系统模块ID
     */
    @Column(name = "module_id")
    private String moduleId;
    
    /**
     * 父节点ID
     */
    @Column(name = "org_pid")
    private String teamPid;
    
    /**
     * 本级ID
     */
    @Column(name = "org_cid")
    private String teamCid;
    
    /**
     * 单位在本module中的别称
     */
    @Column(name = "show_name")
    private String showName;
    
    @Column(name="sort")
    private Integer sort;
    
    @Column(name="org_type")
    private String orgType;
    
    @Column(name="insertDate")
    private Date insertDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getTeamPid() {
		return teamPid;
	}

	public void setTeamPid(String teamPid) {
		this.teamPid = teamPid;
	}

	public String getTeamCid() {
		return teamCid;
	}

	public void setTeamCid(String teamCid) {
		this.teamCid = teamCid;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}
	
}
