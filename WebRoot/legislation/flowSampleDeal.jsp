<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="modal-body" >
	<div class="pull-right">
		<label class="btn btn-w-m btn-success"  onclick="openDemonstrationChildPage('openAddExamplePage',null,'${nodeId}')">添加</label>
	</div>
	<div id="sampleTable">

	</div>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
	$(function () {
		querySampleTable('${nodeId}');
    });
	function querySampleTable(stNodeId) {
        $.post("../${requestUrl}?stNodeId="+stNodeId+"&method=sampleTable",function(data){
            $('#sampleTable').html(data);
        });
    }
</script>