<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
String button_text = "按 钮";
String button_name = "default_button";
String button_url = "#";
String button_type = "default";
int button_width = button_name.length() + 50;
%>
<script language="JavaScript">
function button_focus(button_name){
	var obj = eval("document.all." + button_name);
 	obj.style.backgroundImage = "url(/share/images/bg_focus.gif)";
	obj.style.color = "white";
}
function button_blur(button_name){
	var obj = eval("document.all." + button_name);
 	obj.style.backgroundImage = "url(/share/images/bg_button.gif)";
	obj.style.color = "black";
}
function bill_focus(button_name){
	var obj = eval("document.all." + button_name);
 	obj.style.backgroundImage = "url(/share/images/bill_focus.gif)";
	obj.style.fontWeight = "bold";
}
function bill_blur(button_name){
	var obj = eval("document.all." + button_name);
	obj.style.backgroundImage = "url(/share/images/bill_button.gif)";
	obj.style.fontWeight = "";
}
function bill_blur_blank(button_name){
	var obj = eval("document.all." + button_name);
 	obj.style.backgroundImage = "url(/share/images/bill_blank.gif)";
	obj.style.fontWeight = "";
}

var timer;
function button_focus1(button_name){
	var obj = eval("document.all." + button_name);
 	obj.style.backgroundImage = "url(/share/images/bg_focus.gif)";
	obj.style.color = "white";
	window.setTimeout("button_blur('" + button_name + "')", 500);
}
function button_flash(button_name){
	try{
		window.clearInterval(timer);
	}catch(e){
	}
	var obj = eval("document.all." + button_name);
	timer = window.setInterval("button_focus1('" + button_name + "')", 1000);
}

</script>
