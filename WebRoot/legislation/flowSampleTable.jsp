<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="table table-border table-bordered table-bg table-hover">
	<thead>
	<tr class="text-center">
		<th class="text-center">样本名称</th>
		<th class="text-center">是否必须</th>
		<th class="text-center">操作</th>
	</tr>
	</thead>
	<tbody>
	<c:if test="${legislationExampleFilesList !=null&&fn:length(legislationExampleFilesList)>0}">
		<c:forEach var="exampleFile" items="${legislationExampleFilesList}">
			<tr class="text-center">
				<td>${exampleFile.stExampleName}</td>
				<td>
					<c:if test="${exampleFile.stNeed=='NEED'}">
						<span style="color: green">必须</span>
					</c:if>
					<c:if test="${exampleFile.stNeed=='NONEED'}">
						<span style="color: red">非必须</span>
					</c:if>
				</td>
				<td>
					<a href="javaScript:void(0)" data-title="编辑" onclick="openDemonstrationChildPage('openEditExamplePage','${exampleFile.stExampleId}','${nodeId}')" class="layer_full_link">编辑</a>
					<a href="javaScript:void(0)" data-title="删除" onclick="deleteSimple('${exampleFile.stExampleId}','${nodeId}')" class="layer_full_link">删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	</tbody>
</table>
<script>
	function deleteSimple(stExampleId,stNodeId) {
        layer.close(layer.index);
        layer.confirm('是否删除！', function(index) {
            layer.close(layer.index);
            $.post("../${requestUrl}?stExampleId="+stExampleId+"&method=deleteSimple",function(data){
                querySampleTable(stNodeId);
            });
        });
    }
</script>