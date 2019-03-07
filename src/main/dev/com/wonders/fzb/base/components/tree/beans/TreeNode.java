package com.wonders.fzb.base.components.tree.beans;

public class TreeNode {
	/*
	 * 节点主键
	 */
	private String id;
	
	/*
	 * 父ID
	 */
	private String pid;
	
	/*
	 * 显示标签 
	 */
	private String label;
	private String alt;
	private boolean isParent;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
