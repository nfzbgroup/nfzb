package com.wonders.fzb.platform.beans;

import java.io.Serializable;
import javax.persistence.Column;

@SuppressWarnings("serial")
public class RoleOpRelationPK implements Serializable {
	 @Column(name = "ROLE_ID")
	private String roleId;
	
	@Column(name = "OP_ID")
	private String opId;
	
	@Column(name = "REL_ID")
	private String relId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}

	@Override
	public boolean equals(Object o) {
	   if(o instanceof RoleOpRelationPK) {
		  RoleOpRelationPK pk = (RoleOpRelationPK)o;
		  if(this.roleId == pk.getRoleId() && this.opId.equals(pk.getOpId()) && this.relId == pk.getRelId()) {
			  return true;
		  }
	   }
	   return false;
	}
	 
	@Override
	public int hashCode() {
	   return 29 * this.roleId.hashCode() + 17 * this.opId.hashCode() + this.relId.hashCode();
	}
}