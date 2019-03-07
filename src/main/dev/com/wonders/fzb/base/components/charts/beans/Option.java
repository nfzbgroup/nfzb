package com.wonders.fzb.base.components.charts.beans;

import net.sf.json.JSONObject;

public class Option {
	private JSONObject title;
	
	private JSONObject tooltip;
	
	private JSONObject xAxis;
	
	private JSONObject yAxis;
	
	private JSONObject legend;
	
	private Series series;
	
	public Option(){
		yAxis = new JSONObject();
		yAxis.put("type", "value");
		
		tooltip = new JSONObject();
		tooltip.put("trigger", "axis");
	}

	public JSONObject getTitle() {
		return title;
	}

	public void setTitle(JSONObject title) {
		this.title = title;
	}
	/**
	 * 设置图表的Title项
	 * @param titleText
	 * @param subText
	 */
	public void setTitle(String  titleText,String subText) {
		title = new JSONObject();
		title.put("text", titleText);
		title.put("subtext", subText);
	}
	
	public JSONObject getTooltip() {
		return tooltip;
	}

	/**
	 * 鼠标悬浮在指标上显示
	 * @param formatter 显示的字
	 * @param axisPointer 显示方式  line , cross , shadow,折线图推荐line or cross ，柱状图推荐shadow
	 */
	public void setTooltip(String formatter,String axisPointer) {
		tooltip = new JSONObject();
		tooltip.put("trigger", "axis");
		tooltip.put("formatter", formatter);
		JSONObject axisPointerObj = new JSONObject();
		axisPointerObj.put("type", axisPointer);
		tooltip.put("axisPointer",axisPointerObj);
	}
	
	public void setTooltip(JSONObject tooltip) {
		this.tooltip = tooltip;
	}
	
	public JSONObject getxAxis() {
		return xAxis;
	}

	public void setxAxis(JSONObject xAxis) {
		this.xAxis = xAxis;
	}
	
	public void setxAxis(String[] datas) {
		xAxis = new JSONObject();
		xAxis.put("data", datas);
		xAxis.put("type", "category");
		JSONObject axisLabel = new JSONObject();
		axisLabel.put("interval", 0);
		xAxis.put("axisLabel", axisLabel);
	}
	
	public JSONObject getyAxis() {
		return yAxis;
	}

	public void setyAxis(JSONObject yAxis) {
		this.yAxis = yAxis;
	}

	public JSONObject getLegend() {
		return legend;
	}

	public void setLegend(JSONObject legend) {
		this.legend = legend;
	}
	
	public void setLegend(String[] data) {
		this.legend = new JSONObject();
		legend.put("padding", 10);
		legend.put("borderWidth", 1);
		legend.put("data", data);
	}
	
	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}