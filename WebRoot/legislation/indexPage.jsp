<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.wonders.fzb.framework.beans.*"%>
<%@ page import="com.wonders.fzb.legislation.beans.*"%>
<%@ page import="com.wonders.fzb.plan.beans.*"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${basePath}/legislation/assets/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
		<link href="${basePath}/legislation/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
		<link href="${basePath}/legislation/assets/css/animate.min.css" rel="stylesheet">
		<link href="${basePath}/legislation/assets/css/style.min.css?v=4.0.0" rel="stylesheet">
		<link href="${basePath}/legislation/assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">
		<link href="${basePath}/legislation/assets/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	</head>
   <%
   List legislationProcessDocList = (List)request.getAttribute("legislationProcessDocList"); 
   List legislationPlanList = (List)request.getAttribute("legislationPlanList");
   %>              
	<style type="text/css">
		#echartsPie,
		#echartsPie {
			width: 400px;
			height: 300px;
		}
		
		#echartsBar {
			width: 450px;
			height: 300px;
		}
		
		#echartsPie {
			float: left;
		}
		
		#echartsBar {
			float: left;
		}
		/*#echartsBar {
				position: relative;
				left: 450px;
				bottom: 300px;
			}*/
		
		#irow {
			width: 100%;
			margin-left: 100px;
			margin-top: 10px;
		}
		
		#clear {
			clear: both;
		}
		
		#t-title {
			width: 100%;
			text-align: center;
			padding-bottom: 15px;
			font-weight: bold;
			font-size: 18px;
			font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
		}
	</style>

	<body class="gray-bg">

		<div class="ibox-title">
			<div class="row">
				<div id="irow">
					<div id="echartsPie"></div>
					<div id="echartsBar"></div>
				</div>
			</div>
		</div>
   <form name="form1"  action="${basePath}/${requestUrl}"  method="post">
   <input type="hidden" id="requestUrl" value="${requestUrl}">
		<div class="ibox-title">
			<div id="t-title">立法办理中的草案</div>
			<table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
				<thead>
					<tr class="text-center">
						<th class="text-center" data-field="id" hidden="hidden">草案编号</th>
						<th class="text-center" data-field="district_name">法规规章草案</th>
						<th class="text-center" data-field="district_name">处理环节</th>
						<th class="text-center" data-field="district_name">发起人</th>
						<th class="text-center" data-field="created_at">发起时间</th>
						<th class="text-center" data-field="set">操作</th>
					</tr>
				</thead>
				
				<tbody>
			<%-- 	<c:if test="${retPage.totalSize > 0}">
					<c:forEach items="${retPage.result}" var="doc">
						<tr class="text-center">
							<td>${doc.stDocId}</td>
							<td>${doc.stDocName}</td>
							<td>${doc.stNodeName}</td>
							<td>${doc.stUserName}</td>
							<td>
							    <fmt:formatDate type="date" value="${doc.dtCreateDate}" />
							</td>
							<td><input type="button" onclick="openProcessIndex('${doc.stDocId}','${doc.stDocName}')"  class="btn btn-w-m btn-success" value='查看'></td>
              
						</tr>
					</c:forEach>
				</c:if> --%>
              <%  
                  for(int i=0;i<legislationProcessDocList.size();i++){
                    LegislationProcessDoc legislationProcessDoc=(LegislationProcessDoc)legislationProcessDocList.get(i);
               %>
               <tr class="text-center">
                <td nowrap="" width="20%" hidden="hidden"><%=legislationProcessDoc.getStDocId()%></td>
                <td nowrap="" width="20%"><%=legislationProcessDoc.getStDocName()%></td>
                <td nowrap="" width="20%"><%=legislationProcessDoc.getStNodeName()%></td>
                <td nowrap="" width="20%"><%=legislationProcessDoc.getStUserName()%></td>
                <td nowrap="" width="20%"><fmt:formatDate value="<%=legislationProcessDoc.getDtCreateDate()%>"  pattern="yyyy-MM-dd"/></td>
                <td><input type="button" onclick="openProcessIndex('<%=legislationProcessDoc.getStDocId()%>','<%=legislationProcessDoc.getStDocName()%>')"  class="btn btn-w-m btn-success" value='查看'></td>
               </tr>
             
             <%}%>  
				</tbody>
				</table>
				<div class="clearfix">
				   <div class="list-page" id="listPage"></div>
			    </div>
				<div id="t-title">立法计划管理</div>
			   <table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
				<thead>
					<tr class="text-center">
						<th class="text-center" data-field="id" hidden="hidden">计划编号</th>
						<th class="text-center" data-field="district_name">计划名称</th>
						<th class="text-center" data-field="district_name">处理环节</th>
						<th class="text-center" data-field="district_name">发起人</th>
						<th class="text-center" data-field="created_at">发起时间</th>
						<th class="text-center" data-field="set">操作</th>
					</tr>
				</thead>
				<tbody>
				 <%  
                  for(int i=0;i<legislationPlanList.size();i++){
                	  LegislationPlan legislationPlan=(LegislationPlan)legislationPlanList.get(i);
               %>
               <tr class="text-center">
                <td nowrap="" width="20%" hidden="hidden"><%=legislationPlan.getStPlanId()%></td>
                <td nowrap="" width="20%"><%=legislationPlan.getStPlanName()%></td>
                <td nowrap="" width="20%"><%=legislationPlan.getStNodeName()%></td>
                <td nowrap="" width="20%"><%=legislationPlan.getStCreatorName()%></td>
                <td nowrap="" width="20%"><fmt:formatDate value="<%=legislationPlan.getDtCreateDate()%>"  pattern="yyyy-MM-dd"/></td>
                <td><input type="button" onclick="openLegislationPlanList('<%=legislationPlan.getStPlanId()%>','<%=legislationPlan.getStPlanName()%>')"  class="btn btn-w-m btn-success" value='查看'></td>
               </tr>
             
             <%}%> 
				</tbody>
				</table>
				
			<div class="ibox-content">
				<div class="row" id="legislationProcessTaskTable">

				</div>
			</div>
			<div class="modal inmodal fade" id="legislationProcessForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
			</div>
			<div class="modal inmodal fade" id="processIndexRootForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog" style="margin-top: 0px">
					<div class="modal-content">
					</div>
				</div>
			</div>
			<div class="modal inmodal fade" id="processIndexForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
			</div>
			<div class="modal inmodal fade" id="processIndexChildForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
			</div>
			<!-- <div align="center">
				<ul class="pagination">
					<li>
						<a href="#">&laquo;</a>
					</li>
					<li>
						<a href="#">1</a>
					</li>
					<li>
						<a href="#">2</a>
					</li>
					<li>
						<a href="#">3</a>
					</li>
					<li>
						<a href="#">4</a>
					</li>
					<li>
						<a href="#">5</a>
					</li>
					<li>
						<a href="#">&raquo;</a>
					</li>
				</ul>
			</div> -->
		</div>
</form>
		<script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
		<script src="${basePath}/legislation/assets/js/demo/echarts.js"></script>
		<script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
		<script src="${basePath}/legislation/assets/js/plugins/echarts/echarts-all.js"></script>
		<script src="${basePath}/legislation/assets/js/content.min.js?v=1.0.0"></script>
		<script src="${basePath}/legislation/assets/js/demo/echarts-demo.min.js"></script>
		
	<link href="${basePath}/legislation/assets/page/common.css" rel="stylesheet" type="text/css" />
	<script src="${basePath}/legislation/assets/page/page.js" type="text/javascript"></script>
	<script src="${basePath}/legislation/assets/page/common.js" type="text/javascript"></script>
		<script type="text/javascript">
		 
		window.onload=function(){  
			 $('body').on('hidden.bs.modal', '.modal', function () {
		            $(this).removeData('bs.modal');
		        });
		        $('#processIndexForm').on('hidden.bs.modal', function () {
		            $(document.body).addClass("modal-open");
		        });
		        $('#legislationProcessForm').on('show.bs.modal', function () {
		            $('#legislationProcessForm .modal-body').css('overflow', 'auto');
		            $('#legislationProcessForm .modal-body').css('height', $(window).height());
					$('#legislationProcessForm .modal-dialog').css('width', $(window).width()*0.96);
		        });
		        $('#processIndexRootForm').on('show.bs.modal', function () {
		            $('#processIndexRootForm .wrapper').css('overflow', 'auto');
		            $('#processIndexRootForm .wrapper').css('height', $(window).height());
		            $('#processIndexRootForm .modal-dialog').css('width', $(window).width());
		        });
		        $('#processIndexChildForm').on('show.bs.modal', function () {
		            $('#processIndexChildForm .modal-body').css('overflow', 'auto');
		            $('#processIndexChildForm .modal-body').css('height', $(window).height());
		            $('#processIndexChildForm .modal-dialog').css('width', $(window).width()*0.9);
		        });
		        $('#processIndexForm').on('show.bs.modal', function () {
		            $('#processIndexForm .modal-body').css('overflow', 'auto');
		            $('#processIndexForm .modal-body').css('height', $(window).height());
		            $('#processIndexForm .modal-dialog').css('width', $(window).width()*0.9);
		        }); 
		}  
		
		 function openProcessIndex(stDocId,stDocName) {
			 //alert(1);
				$("#processIndexRootForm").modal({
					remote:  '${basePath}/legislationProcessDoc/draft_doc_info.do?stNodeId=${nodeId}&method=openProcessIndexPage&stDocId='+stDocId+'&stDocName='+stDocName
				});
		    }
		 
		 function openLegislationPlanList(stPlanId,stPlanName) {
			 //alert(2);
				$("#processIndexRootForm").modal({
					remote:  '${basePath}/legislationPlan/draft_plan_info.do?stNodeId=${nodeId}&method=flowDealPage&stPlanId='+stPlanId+'&stPlanName='+stPlanName
				});
		    }
			
	        // 每次隐藏时，清除数据，确保不会和主页dom元素冲突。确保点击时，重新加载。
	         $("#processIndexRootForm").on("hidden.bs.modal", function() {
	             // 这个#processIndexRootForm是模态框的id
	             $(this).removeData("bs.modal");
	             $(this).find(".modal-content").children().remove(); 
	         });
		 
		 
		 
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('echartsBar'));
		
			//柱状图
			var option = {
				title: {
					text: "立法环节数量统计",
					x: "center",
				},
				tooltip: {
					trigger: "axis"
				},
				legend: {
					data: ["案件数量"],
					x: 'right'
				},
				grid: {
					x: 80,
					y: 60,
					x2: 80,
					y2: 60
				},
				calculable: !0,
				xAxis: [{
					type: "category",
					data : (function(){
			            var arr=[];
			            $.ajax({
			                 type : "post",
			                 async : false, //同步执行
			                 url:"${basePath}/legislationProcessDoc/query_doc_num.do",
			                 data :{},
			                 dataType : "json", //返回数据形式为json
			                 success : function(data) {
			                	 $.each(data, function(i, n) {
			                	 arr.push(n.stNodeName);
			 					});                      
			                },
			                    error : function(errorMsg) {
			                        alert("不好意思，图表请求数据失败啦!");
			                         myChart.hideLoading();
			                       }
			               })
			                 return arr;
			              })() ,
			           }],
				yAxis: [{
					type: "value"
				}],
				series: [{
					name: "案件数量",
					type: "bar",
					data: (function(){
			            var arr=[];
			            $.ajax({
			                 type : "post",
			                 async : false, //同步执行
			                 url:"${basePath}/legislationProcessDoc/query_doc_num.do",
			                 data :{},
			                 dataType : "json", //返回数据形式为json
			                 success : function(data) {
			                	 $.each(data, function(i, n) {
			                	 arr.push(n.count);
			 					});                      
			                },         
			                    error : function(errorMsg) {
			                        alert("不好意思，图表请求数据失败啦!");
			                         myChart.hideLoading();
			                       }
			               })
			                 return arr;
			              })() ,
					markPoint: {
						data: [{
							type: "max",
							name: "最大值"
						}, {
							type: "min",
							name: "最小值"
						}]
					},
					markLine: {
						data: [{
							type: "average",
							name: "平均值"
						}]
					},
					itemStyle: {
							normal: {
								label: {
									show: true, //开启显示
									position: 'top', //在上方显示
									textStyle: { //数值样式
										color: 'black',
										fontSize: 16
									}
								}
							}
					},
				}]
			};
			// 饼图
			echarts.init(document.getElementById('echartsPie')).setOption({
				title: {
					text: "工作人员处理草案数统计",
					subtext: "",
					x: "center"
				},
				tooltip: {
					trigger: "item",
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient: "vertical",
					x: "left",  
					data: (function(){
			            var res=[];
			            var len=0;
			            $.ajax({
			                 type : "post",
			                 async : false, //同步执行
			                 url:"${basePath}/legislationProcessDoc/query_user_num.do",
			                 data :{},
			                 dataType : "json", //返回数据形式为json
			                 success : function(data) {
			                	 $.each(data, function(i, n) {
			                		 res.push(n.stUserName);
			 					});                      
			                }
			               })
			                 return res;
			              })()
				},
				calculable: !0,
				series: [{
					name: "工作人员姓名",
					type: "pie",
					radius: "55%",
					center: ["50%", "60%"],
					data: (function(){
			            var res=[];
			            var len=0;
			            $.ajax({
			                 type : "post",
			                 async : false, //同步执行
			                 url:"${basePath}/legislationProcessDoc/query_user_num.do",
			                 data :{},
			                 dataType : "json", //返回数据形式为json
			                 success : function(data) {
			                	 $.each(data, function(i, n) {
			                		 res.push({
			                             name: n.stUserName,
			                             value: n.count
			                             });
			 					});                      
			                }
			               })
			                 return res;
			              })()
				}]
			});
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		</script>
	</body>

</html>