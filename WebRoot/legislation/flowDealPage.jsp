<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${basePath}/legislation/assets/css/demo.css" rel="stylesheet">


<style>
.addHand {
	cursor: pointer;
}
</style>



<%--<div class="modal-body">--%>
<div class="wrapper">
	<div class="row">
		<div class="ibox float-e-margins">
			<button style="padding-right: 5px" type="button" class="close" onclick="closeProcessIndex()">
				<span aria-hidden="true">&times;</span>
				<span class="sr-only">Close</span>
			</button>
			<div class="ibox-title">
				<div class="row">
					<div class="row_title">草案名称：${request.docInfo.stDocName}</div>
					<div class="flowPic">
						<!--<img src="flowPicNew.png" alt="">-->
						<img src="${basePath}/legislation/assets/demo/arrow_block_03.png" alt="" class="arrow_block">
						<!--图例-->
						<div class="legend_box">
							<p class="title">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;例</p>
							<p>
								<span class="color_red">红色、红框</span>
								流程涉及非秘单向导出
							</p>
							<p>
								<span class="color_yellow">黄色</span>
								司法局OA
							</p>
							<p>
								<span class="color_purple">紫色</span>
								网站
							</p>
							<p>
								<span class="color_brown">棕色</span>
								市政府系统
							</p>
						</div>
						<div class="left_menu">
							<ul>
								<li class="flow3_li">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">立法办理</p>
										<span class="border_radius flow3_span"></span>
									</a>
								</li>
							</ul>
						</div>
						<div class="row1 two_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">部门意见征求</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow10.png">
										</span>
									</a>
								</div>
								<div nodeHref="" class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)" id="NOD_0000000120" class="removeHand" handStatus="1" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											部门征求意见
											</br>
											编辑
										</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_yellow">
									<a href="javaScript:void(0)" id="NOD_0000000121" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											部门征求意见
											</br>
											盖章
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_red">
									<a href="javaScript:void(0)" id="NOD_0000000123" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											部门征求意见
											</br>
											发送部门
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
									</a>
								</div>

								<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red">
									<a href="javaScript:void(0)" id="NOD_0000000122" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											部门征求意见
											</br>
											接受发聩
										</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>

							</div>
						</div>
						<div class="row1 three_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow5" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow6" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">公开征求意见</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow11.png">
										</span>
									</a>
								</div>
								<div nodeHref="" class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)" id="NOD_0000000130" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											公开征求意见
											</br>
											发起
										</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red">
									<a href="javaScript:void(0)" id="NOD_0000000131__TODO" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											公开征求意见
											</br>
											接收
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow">
									<a href="javaScript:void(0)" id="NOD_0000000131__SEND" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											公开征求意见
											</br>
											送审
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)" id="NOD_0000000131__PUBLISH" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											网上征求意见
											</br>
											上网发布
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)" id="NOD_0000000131__GATHER" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">汇总整理分析</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)" id="NOD_0000000131__RESULT" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">采纳意见发布</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
							</div>
						</div>
						<div class="row1 four_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">专家论证会</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow12.png">
										</span>
									</a>
								</div>
								<div nodeHref="" class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)" id="NOD_0000000150" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											专家论证会发
											</br>
											起
										</p>
										<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)" id="NOD_0000000151" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											专家论证会结
											</br>
											果归档
										</p>
										<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
							</div>
						</div>
						<div class="row1 five_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow5" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow6" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">立法听证会</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow13.png">
										</span>
									</a>
								</div>
								<div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)" id="legislationEdit" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											立法听证会
											</br>
											发起
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red">
									<a href="javaScript:void(0)" id="legislationReceive" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											立法听证会发
											</br>
											接收
										</p>
										<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow">
									<a href="javaScript:void(0)" id="legislationCensorship" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											立法听证会
											</br>
											送审
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)" id="legislationRelease" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											立法听证会上
											</br>
											网发布
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)" id="legislationRegistration" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											立法听证会网
											</br>
											上报名
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)" id="legislationFile" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											立法听证会结
											</br>
											果归档
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
							</div>
						</div>
						<div class="row1 six_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">部门会签</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow9.png">
										</span>
									</a>
								</div>
								<div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)" id="departmentEdit" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">部门会签编辑</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_yellow">
									<a href="javaScript:void(0)" id="departmentSeal" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">部门会签盖章</p>
										<span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_red">
									<a href="javaScript:void(0)" id="departmentSend" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
										<p class="font_color_black">
											部门会签发起
											</br>
											发送部门
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
									</a>
								</div>
								<c:choose>
									<c:when test="${nodeId=='NOD_0000000162'||nodeId=='NOD_0000000122'}">
										<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red">
											<a href="javaScript:void(0)" id="departmentOpinion" handStatus="1" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
												<p class="font_color_black">
													部门会签结果
													</br>
													接收反馈
												</p>
												<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
											</a>
										</div>
									</c:when>
									<c:otherwise>
										<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red">
											<a href="javaScript:void(0)" id="departmentReceive" class="removeHand" onclick="openDemonstrationPage(this.id,'','${request.docInfo.stDocId}')">
												<p class="font_color_black">
													部门会签结果
													</br>
													接收反馈
												</p>
												<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
											</a>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%--</div>--%>
<script>
	function openDemonstrationPage(buttonId, method, stDocId) {
		var nodeHref = $('#' + buttonId).parent().attr('nodeHref');
		//console.info(method);
		var index = method.indexOf("__");
		if (method == '' || index == 0)
			method = nodeHref;
		if (method == '' || typeof (method) == "undefined") {
			alert('当前节点不可以操作！');
			return false;
		}
		//alert("method=" + method);
		console.log("method=" + method);
		$("#processIndexForm").modal({
			remote : "${basePath}/legislationProcessDoc/draft_doc_info.do?stNodeId=" + buttonId + "&method=" + method + "&stDocId=" + stDocId
		});
	};
	function uploadDemonstrationReport(stDocId, stNodeId, buttonId) {
		$.post("../legislationProcessTask/uploadReport.do?stDocId=" + stDocId + "&stNode=" + stNodeId, function(data) {
			if (data.success) {
				//提交操作，到达下一个节点
				nextDemonstrationProcess(stDocId, stNodeId, "nextProcess", buttonId);
			} else {
				Duang.error("提示", "请补全必填材料！");
			}
		}, "json")
	};
	function uploadChildDemonstrationReport(stDocId, stNodeId, nodeStatus) {
		$.post("../legislationProcessTask/uploadReport.do?stDocId=" + stDocId + "&stNode=" + stNodeId, function(data) {
			//alert("附件数校验"+JSON.stringify(data));
			if (data.success) {
				nextChildDemonstrationProcess(stDocId, stNodeId, "nextChildProcess", nodeStatus);
			} else {
				Duang.error("提示", "请补全必填材料！");
			}
		}, "json")
	};

	function nextDemonstrationProcess(stDocId, stNodeId, method, buttonId) {
		layer.confirm('请确认操作！', function(index) {
			layer.close(layer.index);
			//console.info(" $('#requestUrl').val() --------"+ $('#requestUrl').val() )
			$.post("../" + $('#requestUrl').val() + "?stDocId=" + stDocId + "&stNodeId=" + stNodeId + "&method=" + method + "&buttonId=" + buttonId, function(data) {
				if (data.success) {
					$('#processIndexForm').modal('hide');
					//alert(JSON.stringify(data));
					$.each(data.nodeChangeArray, function(index, item) {
						//改变当前按钮的背景颜色
						$('#' + item.node).parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green');
						$('#' + item.node).parent().addClass(item.colorSet);
						console.info("item.nodeHref------"+item.nodeHref);
						if (item.nodeHref != undefined && item.nodeHref != null && item.nodeHref != '') {
							$('#' + item.node).parent().attr('nodeHref', item.nodeHref);
						}
					});
					Duang.success("提示", "操作成功");
				} else {
					Duang.error("提示", "操作失败");
				}
			});
		});
	};
	function nextChildDemonstrationProcess(stDocId, stNodeId, method, nodeStatus) {
		layer.confirm('请确认操作！', function(index) {
			layer.close(layer.index);
			$.post("../" + $('#requestUrl').val() + "?stDocId=" + stDocId + "&stNodeId=" + stNodeId + "&method=" + method + "&nodeStatus=" + nodeStatus, function(data) {
				//alert(JSON.stringify(data));
				if (data.success) {
					$('#processIndexForm').modal('hide');
					$.each(data.nodeChangeArray, function(index, item) {
						// 改变当前按钮的背景颜色
						$('#' + item.node).parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green');
						$('#' + item.node).parent().addClass(item.colorSet);
						if (item.nodeHref != undefined && item.nodeHref != null && item.nodeHref != '')
							$('#' + item.node).parent().attr('nodeHref', item.nodeHref);
					});
					Duang.success("提示", "操作成功");
				} else {
					Duang.error("提示", "操作失败");
				}
			});
		});
	};
	function toUploadFile(obj) {
		$(obj).next().click();
	};
	function deleteAttach(attachObj, type, id, fileId, stSampleId) {
		$.post('${basePath}/file/deleteAttach.do?fileId=' + fileId);
		var obj = $(attachObj);
		if (type == 1) {
			obj.parent().prev().html('<span style="color: red">暂未上传</span>');
			var html = '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>' + '<input id="' + id + '" name="upload" type="file" style="display:none"  onchange="uploadFile(\'' + id + '\',1,\'' + stSampleId + '\')">';
			obj.parent().html(html);
		} else {
			obj.parent().parent().remove();
		}
	};
	function openDemonstrationTaskPageWithStatus(method, stTaskId, buttonStatus, stDocId, stNodeId) {
		$("#processIndexChildForm").modal({
			remote : "${basePath}/legislationProcessDoc/draft_doc_info.do?method=" + method + "&stDocId=" + stDocId + "&stNodeId=" + stNodeId + "&stTaskStatus=" + buttonStatus + "&stTaskId=" + stTaskId
		});
	};
	function changeUnitSeal(buttonId) {
		if ($('#' + buttonId).attr("handStatus") == undefined) {
			$('#' + buttonId).parent().attr("class", "cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_yellow");
			$('#' + buttonId).attr("class", "removeHand");
			$('#' + buttonId).attr("handStatus", "1");
		}
	};

	function closeProcessIndex() {
		//$('#processIndexRootForm').modal('hide');
		//if(${nodeId=='NOD_0000000103'}){
		//changeType('DOING');
		//}else{
		//submitForm(1);
		//}
	}

	//页面加载后，去后台获取所有节点的状态，连接等信息
	$(document).ready(function() {
		//  $('#processIndexRootForm').on('shown.bs.modal',function () {
		<!--Ajax请求-->
		$.post("../legislationProcessDoc/draft_doc_info.do?stDocId=${request.docInfo.stDocId}&method=openProcessIndexPage_ajax", function(data) {
			//alert(JSON.stringify(data))
			if (data.success) {
				//alert(JSON.stringify(data));
				$.each(data.nodeInfoArray, function(index, item) {
					//改变当前按钮的背景颜色
					$('#' + item.node).parent().removeClass('bcg_gray');//.removeClass('bcg_blue').removeClass('bcg_green');
					//如果不是绿的，可以覆盖。部门多个接收任务，可以绿盖蓝。
					if (!$('#' + item.node).parent().hasClass("bcg_green")) {
						$('#' + item.node).parent().addClass(item.colorSet);
					}
					$('#' + item.node).parent().attr('nodeHref', item.nodeHref);
					$('#' + item.node).addClass('addHand');
				});
				$.each(data.nodeStartInfoArray, function(index, item) {
					// 改变当前按钮的背景颜色
					//$('#'+item.node).parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green');
					//$('#'+item.node).parent().addClass(item.colorSet);
					$('#' + item.node).parent().attr('nodeHref', item.nodeHref);
					//$('#'+item.node).parent().removeClass('removeHand');
					$('#' + item.node).addClass('addHand');
				});
			}
			//   });
		});

	});

	
</script>