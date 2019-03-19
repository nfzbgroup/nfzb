<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${basePath}/legislation/assets/css/demo.css" rel="stylesheet">
<div class="modal-body">
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
					<div class="row_title">草案名称：${stDocName}
						<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					</div>
					<div class="flowPic">
						<!--<img src="flowPicNew.png" alt="">-->
						<img src="${basePath}/legislation/assets/demo/arrow_block_03.png" alt="" class="arrow_block">
						<!--图例-->
						<div class="legend_box">
							<p class="title">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;例</p>
							<p><span class="color_red">红色、红框</span>流程涉及非秘单向导出</p>
							<p><span class="color_yellow">黄色</span>司法局OA</p>
							<p><span class="color_purple">紫色</span>网站</p>
							<p><span class="color_brown">棕色</span>市政府系统</p>
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
										<span class="img_style border_radius_circle"><img src="${basePath}/legislation/assets/demo/flow10.png"></span>
									</a>
								</div>
								<div class="${unitEditClass}">
									<a href="javaScript:void(0)" id="unitEdit" <c:if test="${unitEditDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openUnitDemonstrationPage','${stDocId}','NOD_0000000120')">
										<p class="font_color_black">部门征求意见</br>编辑</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<%--<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_yellow">--%>
									<%--<a href="javaScript:void(0)">--%>
										<%--<p class="font_color_black">部门征求意见</br>盖章</p>--%>
										<%--<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>--%>
									<%--</a>--%>
								<%--</div>--%>
								<div class="${unitSendClass}" >
									<a href="javaScript:void(0)" id="unitSend" <c:if test="${unitSendDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openUnitSeekPage','${stDocId}','NOD_0000000121')">
										<p class="font_color_black">部门征求意见</br>发送部门</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">法制调研处</span>
									</a>
								</div>
								<div class="${unitReceiveClass}">
									<a href="javaScript:void(0)" id="unitReceive" <c:if test="${unitReceiveDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openUnitReceivePage','${stDocId}','NOD_0000000120')">
										<p class="font_color_black">部门征求意见</br>接受发聩</p>
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
										<p class="font_color_white">网上征求意见</p>
										<span class="img_style border_radius_circle"><img src="${basePath}/legislation/assets/demo/flow11.png"></span>
									</a>
								</div>
								<div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)">
										<p class="font_color_black">网上征求意见</br>发起</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red">
									<a href="javaScript:void(0)">
										<p class="font_color_black">网上征求意见</br>盖章</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow">
									<a href="javaScript:void(0)">
										<p class="font_color_black">网上征求意见</br>送审</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)">
										<p class="font_color_black">网上征求意见</br>上网发布</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)">
										<p class="font_color_black">网上意见汇总</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)">
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
										<span class="img_style border_radius_circle"><img src="${basePath}/legislation/assets/demo/flow12.png"></span>
									</a>
								</div>
								<div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)">
										<p class="font_color_black">专家论证会发</br>起</p>
										<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)">
										<p class="font_color_black">专家论证会结</br>果归档</p>
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
										<span class="img_style border_radius_circle"><img src="${basePath}/legislation/assets/demo/flow13.png"></span>
									</a>
								</div>
								<div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)">
										<p class="font_color_black">立法听证会发</br>起</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red">
									<a href="javaScript:void(0)">
										<p class="font_color_black">立法听证会发</br>接收</p>
										<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow">
									<a href="javaScript:void(0)">
										<p class="font_color_black">立法听证会</br>送审</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)">
										<p class="font_color_black">立法听证会上</br>网发布</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple">
									<a href="javaScript:void(0)">
										<p class="font_color_black">立法听证会网</br>上报名</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)">
										<p class="font_color_black">立法听证会结</br>果归档</p>
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
										<span class="img_style border_radius_circle"><img src="${basePath}/legislation/assets/demo/flow9.png"></span>
									</a>
								</div>
								<div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<a href="javaScript:void(0)">
										<p class="font_color_black">部门会签编辑</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_yellow">
									<a href="javaScript:void(0)">
										<p class="font_color_black">部门会签盖章</p>
										<span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_red">
									<a href="javaScript:void(0)">
										<p class="font_color_black">部门会签发起</br>发送部门</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red">
									<a href="javaScript:void(0)">
										<p class="font_color_black">部门会签结果</br>接收反馈</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<script>
    function openDemonstrationPage(buttonId,method,stDocId,stNodeId) {
		if($('#'+buttonId).attr("handStatus")==undefined){
            $("#processIndexForm").modal({
                remote: "${basePath}/legislationProcessDoc/draft_doc_info.do?buttonId="+buttonId+"&method="+method+"&stDocId="+stDocId+"&stNodeId="+stNodeId
            });
		}
    };
    function uploadDemonstrationReport(stDocId,stNodeId,buttonId) {
        $.post("../legislationProcessTask/uploadReport.do?stDocId="+stDocId+"&stNode="+stNodeId,
            function (data) {
                if(data.success) {
                    nextDemonstrationProcess(stDocId,stNodeId,"nextProcess",buttonId);
                }else{
                    Duang.error("提示", "请补全必填材料！");
                }
            },
            "json")
    };
    function nextDemonstrationProcess(stDocId,stNodeId,method,buttonId) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
            $.post("../"+$('#requestUrl').val()+"?stDocId="+stDocId+"&stNodeId="+stNodeId+"&method="+method+"&buttonId="+buttonId,function(data){
                if(data.success){
                    $('#processIndexForm').modal('hide');
					if ("unitEdit"==buttonId) {
						$('#'+buttonId).parent().attr("class","cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t");
					}
                    if(data.addDisabled){
                        $('#'+buttonId).attr("class","removeHand");
						$('#'+buttonId).attr("handStatus","1");
                    }
                    if(data.removeDisabled){
						if ("unitEdit"==buttonId) {
							$('#'+buttonId).parent().next().attr("class","cell row_items row_item4 bcg_green border_width border_style border_radius border_color_red");
						}
                        $('#'+buttonId).parent().next().children().removeAttr('handStatus');
						$('#'+buttonId).parent().next().children().removeAttr('class');
                    }
                    Duang.success("提示","操作成功");
                }else{
                    Duang.error("提示","操作失败");
                }
            });
        });
    };
    function nextChildDemonstrationProcess(stDocId,stNodeId,method,buttonId) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
            $.post("../"+$('#requestUrl').val()+"?stDocId="+stDocId+"&stNodeId="+stNodeId+"&method="+method+"&buttonId="+buttonId,function(data){
                if(data.success){
                    $('#processIndexForm').modal('hide');
                    $('#'+buttonId).attr("class","btn btn-primary btn-rounded process-btn");
                    if(data.removeDisabled){
                        $('#'+buttonId).parent().next().children().attr("class","btn btn-warning btn-rounded process-btn");
                        $('#'+buttonId).parent().next().children().removeAttr('disabled');
                    }
                    Duang.success("提示","操作成功");
                }else{
                    Duang.error("提示","操作失败");
                }
            });
        });
    };
    function toUploadFile(obj) {
        $(obj).next().click();
    };
    function deleteAttach(attachObj,type,id,fileId,stSampleId) {
        var obj=$(attachObj);
        if(type==1){
            obj.parent().prev().html('<span style="color: red">暂未上传</span>');
            var html= '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>'
                +'<input id="'+id+'" name="upload" type="file" style="display:none"  onchange="uploadFile(\''+id+'\',1,\''+stSampleId+'\')">';
            obj.parent().html(html);
        }else{
            obj.parent().parent().remove();
        }
    };
    function openDemonstrationTaskPageWithStatus(method,buttonId,buttonStatus,stDocId,stNodeId) {
        if($('#'+buttonId).attr("handStatus")==undefined){
            $("#processIndexForm").modal({
                remote: "${basePath}/legislationProcessDoc/draft_doc_info.do?buttonId="+buttonId+"&method="+method+"&stDocId="+stDocId+"&stNodeId="+stNodeId+"&stTaskStatus="+buttonStatus
            });
        }
    };
</script>