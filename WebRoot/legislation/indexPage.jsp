<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.wonders.fzb.framework.beans.*"%>
<%@ page import="com.wonders.fzb.legislation.beans.*"%>
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
   <form name="form1"  action="${basePath}/legislationProcessDoc/query_doc_info.do" method="post">
		<div class="ibox-title">
			<div id="t-title">立法办理中的草案</div>
			<table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
				<thead>
					<tr class="text-center">
						<th class="text-center" data-field="id">草案编号</th>
						<th class="text-center" data-field="district_name">法规规章草案</th>
						<th class="text-center" data-field="district_name">处理环节</th>
						<th class="text-center" data-field="district_name">发起人</th>
						<th class="text-center" data-field="created_at">发起时间</th>
						<th class="text-center" data-field="set">操作</th>
					</tr>
				</thead>
				
				<tbody id="div1">
		
             <%--   <%  
                  for(int i=0;i<legislationProcessDocList.size();i++){
                    LegislationProcessDoc legislationProcessDoc=(LegislationProcessDoc)legislationProcessDocList.get(i);
               %>
               <tr>
                <td nowrap="" width="20%"><%=legislationProcessDoc.getStNodeName()%></td>
            </tr>
             
             <%}%> --%>
					<tr class="text-center">
						<td>20190003</td>
						<td>环境治理规章法</td>
						<td>草案查看环节</td>
						<td>张延新</td>
						<td>2019-3-12</td>
						<td>
							<!-- <input type="button" class="btn btn-w-m btn-success" onclick="openPagexiugai()" value="查看">
									</br> -->
							<!-- <input type="button" class="btn btn-w-m btn-success" onclick="openPagebanli()" value="查看"> -->
							<a class="J_menuItem" href="map.html">
								<input type="button" class="btn btn-w-m btn-success" value="查看">
							</a>
						</td>
					</tr>
					<tr class="text-center">
						<td>20190004</td>
						<td>环境治理规章法</td>
						<td>草案查看环节</td>
						<td>张延新</td>
						<td>2019-3-12</td>
						<td>
							<!-- <input type="button" class="btn btn-w-m btn-success" onclick="openPagexiugai()" value="查看">
									</br> -->
							<!-- <input type="button" class="btn btn-w-m btn-success" onclick="openPagebanli()" value="查看"> -->
							<a class="J_menuItem" href="map.html">
								<input type="button" class="btn btn-w-m btn-success" value="查看">
							</a>
						</td>
					</tr>
					<tr class="text-center">
						<td>20190005</td>
						<td>环境治理规章法</td>
						<td>草案查看环节</td>
						<td>张延新</td>
						<td>2019-3-12</td>
						<td>
							<!-- <input type="button" class="btn btn-w-m btn-success" onclick="openPagexiugai()" value="查看">
									</br> -->
							<!-- <input type="button" class="btn btn-w-m btn-success" onclick="openPagebanli()" value="查看"> -->
							<a class="J_menuItem" href="map.html">
								<input type="button" class="btn btn-w-m btn-success" value="查看">
							</a>
						</td>
					</tr>
					<tr class="text-center">
						<td>20190007</td>
						<td>环境治理规章法</td>
						<td>草案查看环节</td>
						<td>张延新</td>
						<td>2019-3-12</td>
						<td>
							<!-- <input type="button" class="btn btn-w-m btn-success" onclick="openPagexiugai()" value="查看">
									</br> -->
							<!-- <input type="button" class="btn btn-w-m btn-success" onclick="openPagebanli()" value="查看"> -->
							<a class="J_menuItem" href="map.html">
								<input type="button" class="btn btn-w-m btn-success" value="查看">
							</a>
						</td>
					</tr>
				</tbody>
				</table>
				<div id="t-title">立法办理中的计划</div>
			   <table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
				<thead>
					<tr class="text-center">
						<th class="text-center" data-field="id">计划编号</th>
						<th class="text-center" data-field="district_name">计划名称</th>
						<th class="text-center" data-field="district_name">处理环节</th>
						<th class="text-center" data-field="district_name">发起人</th>
						<th class="text-center" data-field="created_at">发起时间</th>
						<th class="text-center" data-field="set">操作</th>
					</tr>
				</thead>
				<tbody id="div2">
				</tbody>
				</table>
				
			
			<div class="modal inmodal fade" id="processIndexRootForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog" style="margin-top: 0px">
					<div class="modal-content">
					</div>
				</div>
			</div>
			<div align="center">
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
			</div>
		</div>
</form>
		<script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
		<script src="${basePath}/legislation/assets/js/demo/echarts.js"></script>
		<script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
		<script src="${basePath}/legislation/assets/js/plugins/echarts/echarts-all.js"></script>
		<script src="${basePath}/legislation/assets/js/content.min.js?v=1.0.0"></script>
		<script src="${basePath}/legislation/assets/js/demo/echarts-demo.min.js"></script>
		<script type="text/javascript">
	 	
		window.onload=function(){
			 $.ajax({
                 type : "post",
                 async : false, //同步执行
                 url:"${basePath}/legislationProcessDoc/query_doc_info1.do",
                 data :{},
                 dataType : "json", //返回数据形式为json
                 success : function(data) {
                	 var option; 
                	 $.each(data, function(i, n) {
                		 option += "<tr class=text-center>" +
                		 "<td>"+n.stDocId+"</td>"+
                		 "<td>"+n.stDocName+"</td>"+
                		 "<td>"+n.stNodeName+"</td>"+
                		 "<td>"+n.stUserName+"</td>"+
                		 "<td>2019-08-02</td>"+
                		 "<td><input type='button' onclick='openProcessIndex(\""+n.stDocId+"\",\""+n.stDocName+"\")' class='btn btn-w-m btn-success' value='查看'></td>"
							+ "</tr>"
 					});  
                	 $("#div1").html(option);
                }
               })
               
                $.ajax({
                 type : "post",
                 async : false, //同步执行
                 url:"${basePath}/legislationPlanTask/query_doc_info1.do",
                 data :{},
                 dataType : "json", //返回数据形式为json
                 success : function(data) {
                	 var option;
                	 $.each(data, function(i, n) {
                		 option += "<tr class=text-center>" +
                		 "<td>"+n.stPlanId+"</td>"+
                		 "<td>"+n.stPlanName+"</td>"+
                		 "<td>"+n.stNodeName+"</td>"+
                		 "<td>"+n.stCreatorName+"</td>"+
                		 "<td>2010-09-07</td>"+
                		 "<td><a class='J_menuItem' href='/plan/flowDealPage.jsp'><input type='button' class='btn btn-w-m btn-success' value='查看'></a></td>"
							+ "</tr>"
 					});  
                	 $("#div2").html(option);
                }})
               
               
               $('#processIndexRootForm').on('show.bs.modal', function () {
                   $('#processIndexRootForm .wrapper').css('overflow', 'auto');
                   $('#processIndexRootForm .wrapper').css('height', $(window).height());
                   $('#processIndexRootForm .modal-dialog').css('width', $(window).width());
               });
		}  
		
		 function openProcessIndex(stDocId,stDocName) {
			 alert(stDocId);
			 alert(stDocName);
				$("#processIndexRootForm").modal({
					remote:  '${basePath}/legislationProcessDoc/draft_doc_info.do?stNodeId=${nodeId}&method=openProcessIndexPage&stDocId='+stDocId+'&stDocName='+stDocName
				});
		    }
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
			                 url:"${basePath}/legislationProcessDoc/query_doc_num1.do",
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
			                 url:"${basePath}/legislationProcessDoc/query_doc_num1.do",
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