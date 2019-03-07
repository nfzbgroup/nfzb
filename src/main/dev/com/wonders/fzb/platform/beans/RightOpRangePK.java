package com.wonders.fzb.platform.beans;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class RightOpRangePK implements java.io.Serializable {
	@Column(name = "OP_RIGHT_ID")
	private String opRightId;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "OBJECT_ID")
	private String objectId;

	public String getOpRightId() {
		return opRightId;
	}

	public void setOpRightId(String opRightId) {
		this.opRightId = opRightId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
}
