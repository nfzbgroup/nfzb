package com.wonders.fzb.base.components.charts.beans;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Series {
	private String name;
	private String type="bar";
	private String stack;
	private JSONObject label;
	private JSONArray data;
	
	public Series(){
		JSONObject normal = new JSONObject();
		normal.put("show", true);
		normal.put("position", "insideRight");
		
		label = new JSONObject();
		label.put("normal", normal);
	}
	
	public Series(String name,String stack,JSONArray ja){
		JSONObject normal = new JSONObject();
		normal.put("show", true);
		normal.put("position", "top");
		
		label = new JSONObject();
		label.put("normal", normal);
		setName(name);
		setStack(stack);
		setData(ja);
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

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public JSONObject getLabel() {
		return label;
	}

	public void setLabel(JSONObject label) {
		this.label = label;
	}

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}
	
}
