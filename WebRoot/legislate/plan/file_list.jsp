<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.wonders.fzb.legislate.LegislateConst"%>
<%@page import="com.wonders.fzb.legislate.beans.ModelFileRecord"%>
<%@page import="java.util.List"%>
<table class=" table-height">
	<thead>
		<tr>
			<th width="40%">文件类型</th>
			<th width="40%">文件名称</th>
			<!-- <th width="15%">环节名称</th> -->
			<th>操作</th>
		</tr>
	</thead>
	<caption style="font-weight:bold;color:#000"><%//=activityType%>材料上传</caption>
	<tbody>
		<%
		List planFiles = (List)request.getAttribute("planFiles");
		List otherPlanFiles = (List)request.getAttribute("otherPlanFiles");
		%>
		
		<%
		if(planFiles!=null&&planFiles.size()>0){
			for(int i=0;i<planFiles.size();i++){
				ModelFileRecord modelFileRecord = (ModelFileRecord)planFiles.get(i);
		%>
			<tr>
				<td>
					<%=modelFileRecord.getBizType() %>
					<%if(modelFileRecord.getUnique()==1){ %>
					<font style="color: #B90000;font-size: 16px;"> (必须提交)</font>
					<%}else{ %>
					(可选提交)
					<%} %>
					<%if(modelFileRecord.getHasFile()==null||"".equals(modelFileRecord.getHasFile())){ }else{%>
					<a href="${basePath}/legislate/modelFileLoad.do?modelId=<%=modelFileRecord.getModelId()%>">
				  		(范本)
					 </a>
					 <font style="color: #B9404D;font-size: 12px;"> 
					 <%if(modelFileRecord.getInstructions()==null||"".equals(modelFileRecord.getInstructions())){ }else{%>
					 	<br>使用说明 : <%=modelFileRecord.getInstructions() %>
					 <%} %>
					<%} %>
				</td>
				<td>
					<%if(modelFileRecord.getFileName()==null||"".equals(modelFileRecord.getFileName())){ %>
						<font style="color: #B9404D;font-size: 12px;">暂未上传</font>
					<%}else{ %>
						<%=modelFileRecord.getFileName() %>
					<%} %>
				</td>
				<td>
					<%if(modelFileRecord.getFileName()==null||"".equals(modelFileRecord.getFileName())){ %>
						<input type="button" class="chakan caozuo-btn btn-70" value="点击上传" onclick="uploadFile('<%=modelFileRecord.getBizType() %>');">
					<%}else{ %>
						<a href="${basePath}/legislate/fileRecordLoad.do?fileRecordId=<%=modelFileRecord.getFileRecordId() %>">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
	                    			<a
								onclick="deleteFile('<%=modelFileRecord.getFileRecordId() %>')"
								href="javascript:void (0)"><font
								style="color: #B9404D;font-size: 12px;">删除</font>
							</a>
					<%} %>
				</td>
			</tr>
			<%} %>
		<%} %>
		<!-- 其它材料 -->
		<%
		if(otherPlanFiles!=null&&otherPlanFiles.size()>0){
			for(int i=0;i<otherPlanFiles.size();i++){
				ModelFileRecord modelFileRecord = (ModelFileRecord)otherPlanFiles.get(i);
		%>
			<tr>
				<td><%=modelFileRecord.getBizType()==null?"":modelFileRecord.getBizType() %></td>
				<td><%=modelFileRecord.getFileName()==null?"":modelFileRecord.getFileName() %></td>
				<td>
					<a
					href="${basePath}/legislate/fileRecordLoad.do?fileRecordId=<%=modelFileRecord.getFileRecordId() %>">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="deleteFile('<%=modelFileRecord.getFileRecordId() %>')"
					href="javascript:void (0)"><font
						style="color: #B9404D;font-size: 12px;">删除</font>
					</a>
				</td>
			</tr>
			<%} %>
		<%} %>
		<tr>
			<td>需要报送的其他材料</td>
			<td></td>
			<td> <input type="button" class="chakan caozuo-btn btn-70" value="点击上传" onclick="uploadFile('其它需要报送的材料');"></td>
		</tr>
		<!-- 其它材料 -->
		
	</tbody>
</table>
<table class=" table-height">
	<tbody>
		<font color="red">
			<b>格式要求</b><br>
           1、上边距：4cm，下边距：3.5cm，左、右边距：各3.4cm，行距：最小值28。<br>
           2、标题：小二号宋体加粗，居中。<br>
           3、主送单位：小三号仿宋。<br>
           4、正文：一级标题小三号黑体，二级标题小三号楷体加粗，三级标题小三号仿宋加粗，其他内容均为小三号仿宋。
       </font>
	</tbody>
</table>
<table class=" table-height">
	<caption style="font-weight:bold;color:red"></caption>
	<tbody>
		<font color="red">
		<b>政府规章制定计划项目申报表注意事项</b><br>
           	申请列入“正式项目”计划的项目，请同时上传调研论证报告和规章草案，并在“备注”栏中注明拟向市政府法制办报送规章草案送审稿的时间。<br>
       </font>
	</tbody>
</table>

<script type="text/javascript">
	function uploadFile(bizType){
		layer.open({
	        type: 2,
	        title: '',
	        shade: false,
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area: ['640px', '480px'],
	        content: '${basePath}/legislate/plan/upload_file.jsp?activityType='+encodeURIComponent("<%=activityType%>")+'&planId=<%=outId%>&bizType='+encodeURIComponent(bizType),
	    });
	    
	}
</script>

