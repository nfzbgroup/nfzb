<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/header.jsp" />
<title>图表展示维度选择</title>
</head>
<body>
	<div class="container-fluid">
		<div class="col-xs-4"></div>
		<div class="col-xs-4">
			<form id="mainFrm" class="form-horizontal" role="form"  action="charts.do" method="post">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">图表展示维度设置</h3>						
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">时间</label>
							<div class="col-sm-8 input-group">
								<input type="text" class="form-control"  id="txtDatepicker" readonly>
								<span class="input-group-btn">
									<button id="btnSelect" class="btn btn-default"  type="button"><span class="glyphicon glyphicon-calendar"></span>&nbsp;</button>
								</span>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">单位</label>
							<div class="col-sm-8 input-group">
								 <select class="form-control"  multiple>
      								<option>黄浦区</option>
      								<option>普陀区</option>
     								<option>卫计委</option>
     								<option>食药监</option>
    							</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">类目</label>
							<div class="col-sm-8 input-group">
								 <div class="input-group">
									<input type="text"  id="className" readonly class="form-control class-tree" >
									<input type="hidden"  id="classId" name="classId" >
									<span class="input-group-btn">
										<button class="btn btn-default class-tree"  type="button" >选择</button>
									</span>
								</div>
							</div>
						</div>
						
						<div class="form-group">
    						<div class="col-sm-offset-2 col-sm-10">
    							<div class="btn-group">
      								<button type="submit" class="btn btn-primary">统计</button>
      								<button type="reset" class="btn btn-warning">重置</button>
      							</div>
    						</div>
  						</div>
					</div>
				</div>
				</div>
			</form>
		</div>
		<div class="col-xs-4"></div>
	</div>
	<div id="menuContent" class="menuContent" style="display:none; position: absolute; background-color: #fff;height: 400px;border:1px solid black; overflow-x:hidden; overflow-y:auto; ">
	<!--  <form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-2 control-label">快捷搜索</label>
			<div class="input-group col-sm-9">
				<input type="text" class="form-control" id="txtFastSearch">
				<span class="input-group-btn">
					<button id="btnTreeNodeSearch" class="btn btn-default"  type="button"><span class="glyphicon glyphicon-search"></span>&nbsp;</button>
				</span>
			</div>
		</div>
	</form>-->
	<ul id="classTree" class="ztree" style="margin-top:0; height: 350px;width:100%;"></ul>
</div>
</body>
<jsp:include page="/common/scripts.jsp" />

<script language="javascript">
	$.include([ 'tree', 'treeEX', 'datepicker' ]);
</script>

<script language="javascript">
			function onCheck(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("classTree"),
				nodes = zTree.getCheckedNodes(true),
				displayName = "";
				valueId = "";
				for (var i=0, l=nodes.length; i<l; i++) {
					displayName += nodes[i].name + ",";
					valueId += nodes[i].id + ",";
				}
				if (displayName.length > 0 ) displayName = displayName.substring(0, displayName.length-1);
				if (valueId.length > 0 ) valueId = valueId.substring(0, valueId.length-1);
				var display = $("#className");
				var value = $("#classId");
				
				value.attr("value",valueId);
				display.attr("value", displayName);
			}
			
			function beforeClick(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("teamTree");
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
			}

			function showMenu() {
				var teamObj = $("#className");
				var teamOffset = $("#className").offset();
				$("#menuContent").css({left:teamOffset.left + "px", top:teamOffset.top + teamObj.outerHeight() + "px"}).slideDown("fast");
				$("#menuContent").width($("#className").width() + 24);
				
				$("body").bind("mousedown", onBodyDown);
			}
			function hideMenu() {
				$("#menuContent").fadeOut("fast");
				$("body").unbind("mousedown", onBodyDown);
			}
			function onBodyDown(event) {
				if (!(event.target.id == "menuBtn" || event.target.id == "teamName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
					hideMenu();
				}
			}

	$(function(){
		$("#btnSelect").datetimepicker({
			startView:4,
			minView:4,
			maxView:4,
			viewSelect:"decade",
			linkField: "txtDatepicker",
        	linkFormat: "yyyy"
		}).on("changeYear",function(){
			$('#btnSelect').datetimepicker('hide');
		});
		
		var setting = {
			check: {
				enable: true,
				chkboxType: {"Y":"ps", "N":"ps"}
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}
		};

		var InitNode =[
			{id:1, pId:0, name:"行政处罚", isParent:true, nocheck:false},
			{id:2, pId:1, name:"行政强制措施", isParent:true, nocheck:false},
			{id:3, pId:0, name:"行政强制执行", isParent:true, nocheck:false},
			{id:4, pId:0, name:"行政许可", isParent:true, nocheck:false},
			{id:5, pId:0, name:"行政给付", isParent:true, nocheck:false},
			{id:6, pId:0, name:"行政征收", isParent:true, nocheck:false},
			{id:7, pId:2, name:"案件总数", isParent:false, nocheck:false},
			{id:8, pId:2, name:"警告", isParent:false, nocheck:false},
			{id:9, pId:2, name:"罚款", isParent:true, nocheck:false},
			{id:10, pId:9, name:"次数", isParent:false, nocheck:false},
			{id:11, pId:9, name:"金额", isParent:false, nocheck:false},
			{id:12, pId:1, name:"没收违法所得", isParent:true, nocheck:false},
			{id:13, pId:12, name:"次数", isParent:false, nocheck:false},
			{id:14, pId:12, name:"折合金额", isParent:false, nocheck:false},
			{id:15, pId:1, name:"听证程序", isParent:true, nocheck:false}
		];
				
		$.fn.zTree.init($("#classTree"), setting, InitNode);
		
		$(".class-tree").click(function(){
			showMenu();
		});
	});
</script>
</html>