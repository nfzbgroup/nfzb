<%@page language="java" contentType="text/html;charset=utf-8"  pageEncoding="utf-8"%>
			<div class="form-group">
				<table class="table table-striped table-bordered table-hover" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
					<thead>
						<tr class="text-center">
						<%-- <c:if test="${fn:length(LegislationExampleList)>0}"> --%>
							<th class="text-center" data-field="id">文件类型</th>
							<th class="text-center" data-field="district_name">文件名称</th>
							<th class="text-center" data-field="set">操作</th>
					   <%--  </c:if>   --%>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.LegislationExampleList" var="example">
							   <tr class="text-center">
                                <td class="text-left">${example.stExampleName}
                                    <c:if test="${example.stNeed=='NEED'}">
                                        <span style="color: red">(必须上传)</span>
                                    </c:if>
                                    <span style="color: dodgerblue">(范本)</span>
                                </td>
                                <td >
                                    <c:choose>
                                        <c:when test="${example.fileId !=null}">
                                            <span >${example.fileName}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red">暂未上传</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td >
                                    <c:choose>
                                        <c:when test="${example.fileId !=null}">
                                        <c:if test="${example.stNodeStatus==nodeStatus&&example.stNodeStatus!=null}">
                                            <a target="_blank" href="${basePath}/file/downloadAttach.do?fileId=${example.fileId}&name=${example.fileName}&url=${example.fileUrl}">下载</a>&nbsp;&nbsp;
                                            <input type="hidden" id="${example.fileId}"  name="${example.fileId}" value="${example.fileId}">
                                            <label style="color: red" name="fileBtn" onclick="deleteAttach(this,1,'${example.stExampleId}','${example.fileId}','${example.stExampleId}')" >删除</label>                                        
                                        </c:if>
                                         <c:if test="${example.stNodeStatus==null}">
                                            <a target="_blank" href="${basePath}/file/downloadAttach.do?fileId=${example.fileId}&name=${example.fileName}&url=${example.fileUrl}">下载</a>&nbsp;&nbsp;
                                            <input type="hidden" id="${example.fileId}"  name="${example.fileId}" value="${example.fileId}">
                                            <label style="color: red" name="fileBtn" onclick="deleteAttach(this,1,'${example.stExampleId}','${example.fileId}','${example.stExampleId}')" >删除</label>                                        
                                        </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <label class="btn btn-w-m btn-success" name="fileBtn" onclick="toUploadFile(this)">点击上传</label>
                                            <input id="${example.stExampleId}" name="upload" type="file" style="display:none"  onchange="uploadFile(this.id,1,'${example.stExampleId}')">
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="form-group">
				<label class="control-label">其他材料 </label>
				<label name="fileBtn" class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传 </label>
				<input type="file" id="7" name="upload" style="display: none" onchange="uploadFile(this.id,2,null)">
			</div>
			<div class="form-group">
				<table class="table table-striped table-hover" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
					<thead>
						<tr class="text-center">
							<th class="text-center" data-field="id">文件类型</th>
							<th class="text-center" data-field="district_name">文件名称</th>
							<th class="text-center" data-field="set">操作</th>
						</tr>
					</thead>
					<tbody id="otherMaterial">
						<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
							<c:forEach var="file" items="${legislationFilesList}">
								<c:if test="${file.stSampleId==null||file.stSampleId=='null'}">
									<tr class="text-center">
										<td class="text-center">其他材料</td>
										<td>${file.stTitle}</td>
										<td>
											<a target="_blank" href="${basePath}/file/downloadAttach.do?fileId=${file.stFileId}&name=${file.stTitle}&url=${file.stFileUrl}">下载</a>
											&nbsp;&nbsp;
											<label  name="fileBtn" style="color: red" onclick="deleteAttach(this,2,null,'${file.stFileId}',null)">删除</label>
											<input type="hidden" id="${file.stFileId}" name="${file.stFileId}" value="${file.stFileId}">
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<script>
			   $(function(){
				   //alert();
				   var displayStyle=$("#btnSave").css("display");
				   //alert(displayStyle);
				     if(($("#btnSave")[0]||$("#btnSubmit")[0])&&displayStyle!='none') {
				    	// alert('2');
				     }else{
				    	 //alert($("[name='fileBtn']")[0]);
				    	 $("[name='fileBtn']").hide();
				     }
			   })
			    
			</script>