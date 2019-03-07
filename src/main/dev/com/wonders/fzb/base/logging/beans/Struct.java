package com.wonders.fzb.base.logging.beans;

public class Struct {
	
	public Struct(String packageName,String typeName,Object value){
		this.packageName = packageName;
		this.typeName = typeName;
		this.value = value;
	}
	
	private String packageName;
	private String typeName;
	private Object value;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
