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
 * 单位人员业务实体
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WFC_F_TEAM_MEMBER")
public class TeamMemberInfo implements Serializable {
	
	public TeamMemberInfo() {
	}
	/**
     * 单位人员标识
     */
    @Id
    @GenericGenerator(name = "id", strategy = "assigned") 
    @GeneratedValue(generator = "id")  
    @Column(name = "MEMBER_ID")
    public String memberId;
	
    /**
     * 单位ID
     */
    @Column(name = "TEAM_ID")
    public String teamId;
    
    /**
     * 单位人员标识单位
     */
    @Column(name = "MEMBER_ID_TEAM")
    public String memberIdTeam;
    
    /**
     * 单位人员标识人员
     */
    @Column(name = "MEMBER_ID_USER")
    public String memberIdUser;
    
    /**
     * 名称
     */
    @Column(name = "NAME")
    public String name;

    /**
     * 类型
     */
    @Column(name = "TYPE")
    public String type;

    /**
     * 类型
     */
    @Column(name = "IN_ORDER")
    public int inOrder;
    
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getMemberIdTeam() {
		return memberIdTeam;
	}

	public void setMemberIdTeam(String memberIdTeam) {
		this.memberIdTeam = memberIdTeam;
	}

	public String getMemberIdUser() {
		return memberIdUser;
	}

	public void setMemberIdUser(String memberIdUser) {
		this.memberIdUser = memberIdUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getInOrder() {
		return inOrder;
	}

	public void setInOrder(int inOrder) {
		this.inOrder = inOrder;
	}
    
}