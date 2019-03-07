<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>测试</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div id="webcam"></div>
	<jsp:include page="/common/scripts.jsp" />
	<script language="javascript">
		$.include([ 'webcam' ]);
	</script>
	
	<script language="javascript">
		$("#webcam").webcam({
			width : 320,
			height : 240,
			mode : "callback",
			swffile : "${basePath}/resources/flash/jscam_canvas_only.swf", // canvas only doesn't implement a jpeg encoder, so the file is much smaller
			onTick : function(remain) {
				if (0 == remain) {
					jQuery("#status").text("Cheese!");
				} else {
					jQuery("#status").text(remain + " seconds remaining...");
				}
			},

			onSave : function(data) {

				var col = data.split(";");
				// Work with the picture. Picture-data is encoded as an array of arrays... Not really nice, though =/
			},

			onCapture : function() {
				webcam.save();

				// Show a flash for example
			},

			debug : function(type, string) {
				// Write debug information to console.log() or a div, ...
			},

			onLoad : function() {
				// Page load
				var cams = webcam.getCameraList();
				for ( var i in cams) {
					jQuery("#cams").append("<li>" + cams[i] + "</li>");
				}
			}
		});
	</script>
</body>
</html>