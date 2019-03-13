<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >单位意见征求 > </span>
		</li>
		<li>
			<span>查看</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">征求意见单</h2>
	<table class="table table-border table-bordered">
		<tr>
			<td class="text-right">
				<label style="white-space: nowrap">论证会议题:</label>
			</td>
			<td class="text-center">
				<label>接口未绑定</label>
			</td>
		</tr>
		<tr>
			<td class="text-right">
				<label style="white-space: nowrap">对应草案:</label>
			</td>
			<td class="text-center">
				<label >接口未绑定</label>
			</td>
		</tr>
		<tr>
			<td class="text-right">
				<label style="white-space: nowrap">论证会地点:</label>
			</td>
			<td class="text-center">
				<label >接口未绑定</label>
			</td>
		</tr>
		<tr>
			<td class="text-right">
				<label style="white-space: nowrap">论证会时间:</label>
			</td>
			<td class="text-center">
				<label >接口未绑定</label>
			</td>
		</tr>
		<tr>
			<td class="text-right">
				<label style="white-space: nowrap">论证会人员:</label>
			</td>
			<td class="text-center">
				<label >接口未绑定</label>
			</td>
		</tr>
		<tr>
			<td class="text-right">
				<label style="white-space: nowrap">专家论证会前相关材料:</label>
			</td>
			<td class="text-center">
				<label class="control-label col-md-12 text-center"><a target="_blank" href="javaScript:void(0)">未绑定接口</a></label>
				<label class="control-label col-md-12 text-center"><a target="_blank" href="javaScript:void(0)">未绑定接口</a></label>
				<label class="control-label col-md-12 text-center"><a target="_blank" href="javaScript:void(0)">未绑定接口</a></label>
			</td>
		</tr>
		<tr>
			<td class="text-right">
				<label style="white-space: nowrap">专家论证会前其他材料:</label>
			</td>
			<td class="text-center">
				<label class="control-label col-md-12 text-center"><a target="_blank" href="javaScript:void(0)">未绑定接口</a></label>
				<label class="control-label col-md-12 text-center"><a target="_blank" href="javaScript:void(0)">未绑定接口</a></label>
				<label class="control-label col-md-12 text-center"><a target="_blank" href="javaScript:void(0)">未绑定接口</a></label>
			</td>
		</tr>
		<tr>
			<td class="text-right">
				<label style="white-space: nowrap">专家论证会前领导批示:</label>
			</td>
			<td class="text-center">
				<label >接口未绑定</label>
			</td>
		</tr>
	</table>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
	</div>
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    })
</script>