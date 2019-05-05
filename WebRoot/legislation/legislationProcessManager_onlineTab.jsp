<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table table-border table-bordered">
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">法规规章草案:</label>
		</td>
		<td class="text-center">
			<label>接口未绑定</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">经办处室:</label>
		</td>
		<td class="text-center">
			<label >接口未绑定</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">经办处意见:</label>
		</td>
		<td class="text-center">
			<label >接口未绑定</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">法治调研处意见:</label>
		</td>
		<td class="text-center">
			<label >接口未绑定</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">办领导意见:</label>
		</td>
		<td class="text-center">
			<label >接口未绑定</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">征求意见开始时间:</label>
		</td>
		<td class="text-center">
			<label >接口未绑定</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">征求意见结束时间:</label>
		</td>
		<td class="text-center">
			<label >接口未绑定</label>
		</td>
	</tr>
</table>
<label >征求意见汇总情况: 门户网站征询平台(反馈意见数)xxx条</label>
<div class="form-group text-center">
	<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
</div>
<script>
	$(function () {
		$(".tab-left").css('width', $(window).width() * 0.2)
    })
</script>