package com.wonders.fzb.base.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

public class BaseBean {
	
	
	@Column(name = "REMOVED")
	private int removed;

	public int getRemoved() {
		return removed;
	}

	public void setRemoved(int removed) {
		this.removed = removed;
	}
}
