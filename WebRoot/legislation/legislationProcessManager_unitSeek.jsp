<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >单位意见征求</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table table-border table-bordered">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="all" onchange="changeType(this)">
				</th>
				<th style="text-align: center">
					单位名称
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<input type="checkbox" name="unit">
				</td>
				<td style="text-align: center">
					上海市黄浦区人民振幅法制办公室
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" name="unit">
				</td>
				<td style="text-align: center">
					上海市黄浦区人民振幅法制办公室2
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" name="unit">
				</td>
				<td style="text-align: center">
					上海市黄浦区人民振幅法制办公室3
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" name="unit">
				</td>
				<td style="text-align: center">
					上海市黄浦区人民振幅法制办公室4
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" name="unit">
				</td>
				<td style="text-align: center">
					上海市黄浦区人民振幅法制办公室5
				</td>
			</tr>
		</tbody>
	</table>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.1);
        $('[name="unit"]').change(function () {
            var num=$('[name="unit"]').size();
            var checkedNum=0;
            $('[name="unit"]').each(function () {
				if($(this).prop("checked")){
					checkedNum++;
				}
            })
			if(num==checkedNum){
				$("#all").prop("checked",true);
			}else{
                $("#all").prop("checked",false);
			}
        });
    });
	function changeType(obj) {
		if($(obj).prop("checked")){
            $('[name="unit"]').prop("checked",true);
		}else{
            $('[name="unit"]').prop("checked",false);
		}
    }
</script>
