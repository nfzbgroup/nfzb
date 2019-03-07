$(function(){
	$("[id^='dp_']").datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	
	$("[id^='cascade_']").change(function(){
		var cascade_type = $(this).attr("cascade-type");
		var cascade_element = $(this).attr("cascade-element");
		var cascade_controller = $(this).attr("cascade-controller");
		var cascade_label = $(this).val();
		var cascade_uuid = $(this).find("option:selected").attr("pid");
		$.ajax({
			url:cascade_controller,
			type:"POST",
			data:"label="+cascade_label+"&type="+cascade_type+"&uuid="+cascade_uuid,
			success:function(msg){
				var obj = eval('('+msg+')');
				if(cascade_type == "SELECT"){
					var tempHTML = "";
					$.each(obj,function(index){
						tempHTML+="<option pid='"+obj[index].UUID+"' value='"+obj[index].LABEL+"'>"+obj[index].LABEL+"</option>";
					});
					$("."+cascade_element).html(tempHTML);
					$("."+cascade_element).change();
				}else{
					$("#label_"+cascade_element).text(obj.MEASUREMENT);
					$("#"+cascade_element).val(obj.MEASUREMENT);
				}
			}
		});
	});
	
	/*var targets = $("[id^='target_']");
	$.each(targets,function(key){
		var target = targets[key];
		$(target).dragsort({
			dragSelector:"span",
			dragBetween:true,
			placeHolderTemplate:"<span class='chk_item placeHolder'></span>",
			scrollSpeed:10
		});
	});*/
	
	$("[id^='target_']").dragsort({
		dragSelector:"span",
		dragBetween:true,
		placeHolderTemplate:"<span class='chk_item placeHolder'></span>",
		scrollSpeed:5
	});
	
	$("#section_save").click(function(){
		var targets = $("[id^='target_']");
		var arrayList = new Array();
		$.each(targets,function(index){
			var code = $(targets[index]).attr("code");
			if(code != "pool"){
				var obj = new Object();
				obj.uuid = $(targets[index]).attr("uuid");
				obj.children = new Array();
				var children = $(targets[index]).children(":not(.placeHolder)");
				$.each(children,function(key){
					var item = children[key];
					obj.children.push({"code":$(item).attr("tableName"),"section":$(item).text()});
				});
				arrayList.push(obj);
			}
		});
		
		$.ajax({
			url:"/admin/classify",
			type:"POST",
			dataType:"json",
			data:"ret="+JSON.stringify(arrayList),
			success:function(){
				location.reload();
			}
		});
	});
});