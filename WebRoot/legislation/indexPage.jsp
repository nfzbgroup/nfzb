<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.fzb.framework.beans.*"%>
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
				<tbody>
					<tr class="text-center">
						<td>20190001</td>
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
						<td>20190002</td>
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

		<script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
		<script src="${basePath}/legislation/assets/js/demo/echarts.js"></script>
		<script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
		<script src="${basePath}/legislation/assets/js/plugins/echarts/echarts-all.js"></script>
		<script src="${basePath}/legislation/assets/js/content.min.js?v=1.0.0"></script>
		<script src="${basePath}/legislation/assets/js/demo/echarts-demo.min.js"></script>
		<script type="text/javascript">
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
					data: ["草案起草", "接收分办", "立法办理", "审核会议", "草案报审", "常务会议(常委会)", "报市长审签", "归档"],
				}],
				yAxis: [{
					type: "value"
				}],
				series: [{
					name: "案件数量",
					type: "bar",
					data: [150, 23, 17, 20, 50, 30, 20, 60],
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
					data: ["张延新", "李英", "朱振生", "李萍", "李垚曜"]
				},
				calculable: !0,
				series: [{
					name: "工作人员姓名",
					type: "pie",
					radius: "55%",
					center: ["50%", "60%"],
					data: [{
						value: 335,
						name: "张延新"
					}, {
						value: 310,
						name: "李英"
					}, {
						value: 234,
						name: "朱振生"
					}, {
						value: 135,
						name: "李萍"
					}, {
						value: 1548,
						name: "李垚曜"
					}]
				}]
			});
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		</script>
	</body>

</html>