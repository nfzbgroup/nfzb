<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table table-border table-bordered">
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">会议名称:</label>
		</td>
		<td class="text-center">
			<label>接口未绑定</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">会议地点:</label>
		</td>
		<td class="text-center">
			<label >接口未绑定</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">会议时间:</label>
		</td>
		<td class="text-center">
			<label >接口未绑定</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">会议人员:</label>
		</td>
		<td class="text-center">
			<label >接口未绑定</label>
		</td>
	</tr>
</table>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    })
</script>