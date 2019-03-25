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
									<div class="${unitSealClass}">
									<a href="javaScript:void(0)" id="unitSeal" <c:if test="${unitSealDisabled}">handStatus="1" class="removeHand" </c:if> onclick="changeUnitSeal(this.id)">
									<p class="font_color_black">部门征求意见</br>盖章</p>
									<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
									</div>
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
									<div class="${onlineEditClass}">
										<a href="javaScript:void(0)" id="onlineEdit" <c:if test="${onlineEditDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openOnlineDemonstrationPage','${stDocId}','NOD_0000000130')">
											<p class="font_color_black">网上征求意见</br>发起</p>
											<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
										</a>
									</div>
									<div class="${onlineSealClass}">
										<a href="javaScript:void(0)" id="onlineSeal" <c:if test="${onlineSealDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openOnlineSealPage','${stDocId}','NOD_0000000131')">
											<p class="font_color_black">网上征求意见</br>盖章</p>
											<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
										</a>
									</div>
									<div class="${onlineCensorshipClass}">
										<a href="javaScript:void(0)" id="onlineCensorship" <c:if test="${onlineCensorshipDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openOnlineCensorshipPage','${stDocId}','NOD_0000000131')">
											<p class="font_color_black">网上征求意见</br>送审</p>
											<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
										</a>
									</div>
									<div class="${onlineReleaseClass}">
										<a href="javaScript:void(0)" id="onlineRelease" <c:if test="${onlineReleaseDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openOnlineReleasePage','${stDocId}','NOD_0000000131')">
											<p class="font_color_black">网上征求意见</br>上网发布</p>
											<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
										</a>
									</div>
									<div class="${onlineSummaryClass}">
										<a href="javaScript:void(0)" id="onlineSummary" <c:if test="${onlineSummaryDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openOnlineSummaryPage','${stDocId}','NOD_0000000131')">
											<p class="font_color_black">网上意见汇总</p>
											<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
										</a>
									</div>
									<div class="${onlinePublishClass}">
										<a href="javaScript:void(0)" id="onlinePublish" <c:if test="${onlinePublishDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openOnlineDemonstrationPage','${stDocId}','NOD_0000000131')">
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
									<div class="${expertBeforeClass}">
										<a href="javaScript:void(0)" id="expertBefore" <c:if test="${expertBeforeDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openExpertDemonstrationPage','${stDocId}','NOD_0000000150')">
											<p class="font_color_black">专家论证会发</br>起</p>
											<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
										</a>
									</div>
									<div class="${expertAfterClass}">
										<a href="javaScript:void(0)" id="expertAfter" <c:if test="${expertAfterDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openExpertDemonstrationPage','${stDocId}','NOD_0000000151')">
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
									<div class="${legislationEditClass}">
										<a href="javaScript:void(0)" id="legislationEdit" <c:if test="${legislationEditDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openLegislationDemonstrationPage','${stDocId}','NOD_0000000140')">
											<p class="font_color_black">立法听证会发</br>起</p>
											<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
										</a>
									</div>
									<div class="${legislationReceiveClass}">
										<a href="javaScript:void(0)" id="legislationReceive" <c:if test="${legislationReceiveDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openLegislationReceivePage','${stDocId}','NOD_0000000141')">
											<p class="font_color_black">立法听证会发</br>接收</p>
											<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
										</a>
									</div>
									<div class="${legislationCensorshipClass}">
										<a href="javaScript:void(0)" id="legislationCensorship" <c:if test="${legislationCensorshipDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openLegislationCensorshipPage','${stDocId}','NOD_0000000141')">
											<p class="font_color_black">立法听证会</br>送审</p>
											<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
										</a>
									</div>
									<div class="${legislationReleaseClass}">
										<a href="javaScript:void(0)" id="legislationRelease" <c:if test="${legislationReleaseDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openLegislationReleasePage','${stDocId}','NOD_0000000141')">
											<p class="font_color_black">立法听证会上</br>网发布</p>
											<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
										</a>
									</div>
									<div class="${legislationRegistrationClass}">
										<a href="javaScript:void(0)" id="legislationRegistration" <c:if test="${legislationRegistrationDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openLegislationRegistrationPage','${stDocId}','NOD_0000000141')">
											<p class="font_color_black">立法听证会网</br>上报名</p>
											<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
										</a>
									</div>
									<div class="${legislationFileClass}">
										<a href="javaScript:void(0)" id="legislationFile" <c:if test="${legislationFileDisabled}">handStatus="1" class="removeHand" </c:if> onclick="openDemonstrationPage(this.id,'openLegislationDemonstrationPage','${stDocId}','NOD_0000000141')">
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
                    if("expertBefore"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t");
                    }
                    if("legislationEdit"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t");
                    }
                    if("onlineEdit"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item2 bcg_blue border_width border_style border_radius border_color_t");
                    }
                    if(data.addDisabled){
                        $('#'+buttonId).attr("class","removeHand");
                        $('#'+buttonId).attr("handStatus","1");
                    }
                    if(data.removeDisabled){
                        if ("unitEdit"==buttonId) {
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item3 bcg_green border_width border_style border_radius border_color_yellow");
                        }
                        if("expertBefore"==buttonId){
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item3 bcg_green border_width border_style border_radius border_color_t");
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
                    if("expertAfter"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_t");
                    }
                    if("legislationReceive"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_red");
                    }
                    if("legislationCensorship"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_yellow");
                    }
                    if("legislationRelease"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_purple");
                    }
                    if("legislationRegistration"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item6 bcg_blue border_width border_style border_radius border_color_purple");
                    }
                    if("legislationFile"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item7 bcg_blue border_width border_style border_radius border_color_t");
                    }
                    if("onlineSeal"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_red");
                    }
                    if("onlineCensorship"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_yellow");
                    }
                    if("onlineRelease"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item5 bcg_blue border_width border_style border_radius border_color_purple");
                    }
                    if("onlineSummary"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item6 bcg_blue border_width border_style border_radius border_color_purple");
                    }
                    if("onlinePublish"==buttonId){
                        $('#'+buttonId).parent().attr("class","cell row_items row_item7 bcg_blue border_width border_style border_radius border_color_purple");
                    }
                    if(data.addDisabled){
                        $('#'+buttonId).attr("class","removeHand");
                        $('#'+buttonId).attr("handStatus","1");
                    }
                    if(data.removeDisabled){
                        if("legislationReceive"==buttonId){
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item4 bcg_green border_width border_style border_radius border_color_yellow");
                        }
                        if("legislationCensorship"==buttonId){
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item5 bcg_green border_width border_style border_radius border_color_purple");
                        }
                        if("legislationRelease"==buttonId){
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item6 bcg_green border_width border_style border_radius border_color_purple");
                        }
                        if("legislationRegistration"==buttonId){
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item7 bcg_green border_width border_style border_radius border_color_t");
                        }
                        if("onlineSeal"==buttonId){
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item4 bcg_green border_width border_style border_radius border_color_yellow");
                        }
                        if("onlineCensorship"==buttonId){
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item5 bcg_green border_width border_style border_radius border_color_purple");
                        }
                        if("onlineRelease"==buttonId){
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item6 bcg_green border_width border_style border_radius border_color_purple");
                        }
                        if("onlineSummary"==buttonId){
                            $('#'+buttonId).parent().next().attr("class","cell row_items row_item7 bcg_green border_width border_style border_radius border_color_purple");
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
    function openDemonstrationTaskPageWithStatus(method,stTaskId,buttonStatus,stDocId,stNodeId) {
		$("#processIndexChildForm").modal({
			remote: "${basePath}/legislationProcessDoc/draft_doc_info.do?method="+method+"&stDocId="+stDocId+"&stNodeId="+stNodeId+"&stTaskStatus="+buttonStatus+"&stTaskId="+stTaskId
		});
    };
    function changeUnitSeal(buttonId) {
        if($('#'+buttonId).attr("handStatus")==undefined){
            $('#'+buttonId).parent().attr("class","cell row_items row_item3 bcg_blue border_width border_style border_radius border_color_yellow");
            $('#'+buttonId).attr("class","removeHand");
            $('#'+buttonId).attr("handStatus","1");
        }
    };
</script>