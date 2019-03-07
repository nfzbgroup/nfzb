<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>添加</title>
<link rel="stylesheet" type="text/css" href="../resources/components/tree/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="../resources/styles/bootstrap.min.css" />
<script type="text/javascript" src="../resources/scripts/jquery-1.11.3.js"></script>
<script type="text/javascript" src="../resources/scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/components/tree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="../resources/components/tree/jquery.ztree.excheck-3.5.min.js"></script>
<!--[if lt IE 9]>
     <script src="../resources/scripts/html5shiv.min.js"></script>
     <script src="../resources/scripts/respond.min.js"></script>
<![endif]-->
</head>
<body>
<div class="container">
	<div class="panel panel-default">
    	<div class="panel-body">
    		<form action="/user/simple_add.do"  method="post" class="form-horizontal" role="form">
  				<div class="form-group">
    				<label class="col-sm-2 control-label">帐号</label>
    				<div class="col-sm-10">
    					<s:textfield name="userInfo.account"  placeholder="帐号" class="form-control" />
    				</div>
  				</div>
  				<div class="form-group">
    				<label class="col-sm-2 control-label">密码</label>
    				<div class="col-sm-10">
    					<s:textfield name="userInfo.password"  placeholder="密码" class="form-control" />
    				</div>
  				</div>
  				<div class="form-group">
    				<label class="col-sm-2 control-label">名字</label>
    				<div class="col-sm-10">
    					<s:textfield name="userInfo.name"  placeholder="名字" class="form-control" />
    				</div>
  				</div>
  				<div class="form-group">
  						<label class="col-sm-2 control-label">单位</label>
						<div class="col-sm-10">
							<div class="input-group">
								<input type="text"  id="teamName" readonly class="form-control" onClick="showMenu()">
								<input type="hidden"  id="teamId" name="teamId" >
								<span class="input-group-btn">
									<button class="btn btn-default" type="button" onClick="showMenu()">选择</button>
								</span>
							</div>
						</div>
    			</div>
    			<div class="form-group">
    				<label class="col-sm-2 control-label">联系人</label>
    				<div class="col-sm-10">
    					<s:textfield name="userInfo.abbrName"  placeholder="联系人" class="form-control" />
    				</div>
  				</div>
  				<div class="form-group">
    				<label class="col-sm-2 control-label">联系电话</label>
    				<div class="col-sm-10">
    					<s:textfield name="userInfo.phone"  placeholder="联系电话" class="form-control" />
    				</div>
  				</div>
  				<div class="form-group">
    				<div class="col-sm-offset-2 col-sm-10">
    					<div class="btn-group">
      						<button type="submit" class="btn btn-primary">提交</button>
      						<button type="reset" class="btn btn-warning">重置</button>
      					</div>
    				</div>
  				</div>
			</form>
    	</div>
	</div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position: absolute; background-color: #fff;">
	<ul id="teamTree" class="ztree" style="margin-top:0; height: 550px;"></ul>
</div>
</body>
<script language="javascript">
			function ajaxGetNodes(treeNode, reloadType) {
				var zTree = $.fn.zTree.getZTreeObj("teamTree");
				if (reloadType == "refresh") {
					treeNode.icon = "tree/img/loading.gif";
					zTree.updateNode(treeNode);
				}
				zTree.reAsyncChildNodes(treeNode, reloadType, true);
			}

			function beforeExpand(treeId, treeNode) {
				if (!treeNode.isAjaxing) {
					startTime = new Date();
					treeNode.times = 1;
					ajaxGetNodes(treeNode, "refresh");
					return true;
				} else {
					alert("数据加载中。。。");
					return false;
				}
			}
			function onAsyncSuccess(event, treeId, treeNode, msg) {
				if (!msg || msg.length == 0) {
					return;
				}
				var zTree = $.fn.zTree.getZTreeObj("teamTree"),
				totalCount = treeNode.count;
				if (treeNode.children.length < totalCount) {
					setTimeout(function() {ajaxGetNodes(treeNode);}, perTime);
				} else {
					treeNode.icon = "";
					zTree.updateNode(treeNode);
					zTree.selectNode(treeNode.children[0]);
				}
			}
			function beforeClick(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("teamTree");
				zTree.checkNode(treeNode, !treeNode.checked, null, true);
				return false;
			}
			
			function onCheck(e, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("teamTree"),
				nodes = zTree.getCheckedNodes(true),
				displayName = "";
				valueId = "";
				for (var i=0, l=nodes.length; i<l; i++) {
					displayName += nodes[i].name + ",";
					valueId += nodes[i].id + ",";
				}
				if (displayName.length > 0 ) displayName = displayName.substring(0, displayName.length-1);
				if (valueId.length > 0 ) valueId = valueId.substring(0, valueId.length-1);
				var display = $("#teamName");
				var value = $("#teamId");
				value.attr("value",valueId);
				display.attr("value", displayName);
			}

			function showMenu() {
				var cityObj = $("#teamName");
				var cityOffset = $("#teamName").offset();
				$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
				$("#teamTree").width($("#teamName").width()+25);
				
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
				var setting = {
						async: {
							enable: true,
							url:"../team/async.do",
							autoParam:["id"]
						},
						check: {
							enable: true,
							chkboxType: {"Y":"", "N":""}
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
					{id:"U0", pid:0, name:"市法制办", isParent:true, nocheck:true}
				];
				
				$.fn.zTree.init($("#teamTree"), setting, InitNode);
			});
</script>
</html>