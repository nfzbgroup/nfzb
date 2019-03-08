<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="modal-body">
	<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">上海市人民政府法制办公室文件分办单</h2>
	<form id="user_form" class="form-horizontal"
		  novalidate="novalidate">
		<div class="form-body">
			<div class="form-group">
				<label class="control-label pull-right">EF2017规00001
				</label>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">来文单位：
				</label>
				<div class="col-md-4">
					<input class="control-label underline" readonly>
				</div>
				<label class="control-label col-md-5">收文日期：<span id="receiveYear" class="underline">2019</span>年&nbsp;<span id="receiveMonth" class="underline">03</span>月&nbsp;<span id="receiveDay" class="underline">08</span>日
				</label>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">草案名称：
				</label>
				<div class="col-md-4">
					<input class="control-label underline" readonly value="<c:if test="${stDocName!=null}">${stDocName}</c:if>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">分办：
				</label>
				<div class="col-md-6">
					<textarea  class="form-control" cols="30" rows="4"></textarea>
				</div>
			</div>
			<div style="width: 100%;height: 2px;background-color: #E4243D;margin-bottom: 20px"></div>
			<%--<div class="form-group">--%>
				<%--<label class="control-label pull-right">办理时限：--%>
					<%--<input style="width: 40px" type="text" name="year" class="underline">年<input style="width: 40px" type="text" name="month" class="underline">月<input style="width: 40px" type="text" name="day" class="underline">日--%>
				<%--</label>--%>
			<%--</div>--%>
			<div class="form-group">
				<label class="control-label col-md-2">选择办理处：
				</label>
				<div class="col-md-5">
					<select class="control-label" >
						<option>未绑定接口</option>
					</select>
				</div>
				<div class="col-md-5">
					<label class="control-label pull-right">办理时限：
						<input style="width: 40px" type="text" name="year" class="underline">年<input style="width: 40px" type="text" name="month" class="underline">月<input style="width: 40px" type="text" name="day" class="underline">日
					</label>
				</div>
			</div>
		</div>
	</form>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="确认分办">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="编辑分办单">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>

</script>