<%@page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/platform/include/import.jsp"%>
<%@ include file="/platform/include/usebean.jsp"%>
<%@ include file="/platform/include/authcheck.jsp"%>
<%@ include file="/platform/include/html_head.jsp"%>
<%@ include file="/platform/scripts/doing_alert.inc"%>
<%@ include file="/platform/scripts/button_init.inc"%>
<%
String docId = (String)request.getParameter("docId");
String docType = request.getParameter("docType");
String versionId = (String)request.getParameter("versionId");
String fromTemplate = (String)request.getParameter("fromTemplate");
String op = (String)request.getParameter("op");
String pageTitle = (String)request.getParameter("pageTitle");
if(pageTitle==null){
	pageTitle = "在线编辑";
}
DocProperties docProperties = (DocProperties)request.getAttribute("docProperties");
ComposeServer composeServer = (ComposeServer)ctx.getANTBean("composeServer");
try{
	composeServer.getComposer(docType);
}catch(Exception e){
}
Composer composer = composeServer.getComposer(docType);
if(composer==null){
	docType = new String(docType.getBytes("ISO8859-1"),"UTF-8");
	composer = composeServer.getComposer(docType);
}
docProperties = composer.getDocProperties(docId, new DocProperties());
%>
<html lang="zh">
	<head>
		<title><%=pageTitle%></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="generator" content="Notepad ++" />
		<!--[if IE 8 ]>	<body class="ie8"> <![endif]-->
		<!--[if IE 9 ]>	<body class="ie9"> <![endif]-->
		<script type="text/javascript">
			var openWindowW = 1100;
			var openWindowH = 1030;
			if(screen.width<=1024){
				openWindowW = 1000;
				openWindowH = 680;
			}

			var normalsize = 100;
			var bigsize = 145;
			var smallsize = 80;
			
			if(screen.width<1920){
			     normalsize = 100;
			     bigsize = 130;
			     smallsize = 50;
			}
			 
			function window_onload() {
				oc.OpenFromURL('compose_doc_download.jsp?<%if(versionId!=null){%>versionId=<%=versionId%>&<%}%>docId=<%=docId%>&docType=<%=java.net.URLEncoder.encode(docType,"UTF-8")%>&fromTemplate=<%=fromTemplate%>');
				
				<%if (CommonConst.YES.equals(fromTemplate)){
					for(String key:docProperties.keySet()){
				%>
						copyInputToBookMark(oc,"<%=key%>","<%=key%>");
				<%  }
				  }%>
				
				try{
					oc.ActiveDocument.CustomDocumentProperties("USER_ID").Delete();
				}catch(e){}
				oc.ActiveDocument.CustomDocumentProperties.Add("USER_ID",false,4,"<%=currentPerson.getUserId()%>");
				try{
					oc.ActiveDocument.CustomDocumentProperties("USER_NAME").Delete();
				}catch(e){}
				oc.ActiveDocument.CustomDocumentProperties.Add("USER_NAME",false,4,"<%=currentPerson.getName()%>");
				try{
					oc.ActiveDocument.CustomDocumentProperties("DOC_ID").Delete();
				}catch(e){}
				oc.ActiveDocument.CustomDocumentProperties.Add("DOC_ID",false,4,"<%=docId%>");
				try{
					oc.ActiveDocument.CustomDocumentProperties("SGZ_TEXT").Delete();
				}catch(e){}
				oc.ActiveDocument.CustomDocumentProperties.Add("SGZ_TEXT",false,4,"KGZ");
				
				//oc.OpenFromURL('../template/bj.doc');
				//oc.Menubar = false;
				//oc.Statusbar = false;
				change_onclick("normal");
				
				<%if(false){%>
				
				//燕托电子签章
				var w ;
				var menuBar;
				w=oc.ActiveDocument.Application;
				menuBar=w.CommandBars("签章系统");
				if (menuBar !=null)
				{
					for (i=1;i<menuBar.Controls.Count+1;i++)
					{
						if(menuBar.Controls.item(i).Caption=="显示签章(&W)...")
						{
							menuBar.Controls.item(i).Execute();
							break;
						}
					}
				}
				
				<%}%>
				
				oc.ActiveDocument.AcceptAllRevisions();
				
				<%if("protect".equals(op)){%>
				//oc.ActiveDocument.Protect(3,false,'password',false,true);
				oc.SetReadOnly(true,"password");//文档保护
				<%}else{%>
				oc.ActiveDocument.ShowRevisions = false;//修订模式关闭
				<%}%>
				
				//oc.ActiveDocument.TrackRevissions = true;
				//if(oc.docType==1){
				//	oc.ActiveDocument.ShowRevisions = false;	//修订模式关闭

					//var doc = oc.ActiveDocument;
					//var app = doc.application;
					//doc.PageSetup.PageWidth = app.CentimetersToPoints(21);
					//doc.PageSetup.PageHeight = app.CentimetersToPoints(23.5);
					//doc.PageSetup.LeftMargin = app.CentimetersToPoints(5);
					//doc.PageSetup.RightMargin = app.CentimetersToPoints(5);
					//doc.PageSetup.TopMargin = app.InchesToPoints(1.5);
					//doc.PageSetup.BottomMargin = app.InchesToPoints(1);
				//}
			}
			
			function save_onclick()
			{
				<%  for(String key:docProperties.keySet()){
					%>
								copyBookMarkToInput(oc,"<%=key%>","<%=key%>");
					<%  }
					%>
					//alert(versionId);
					var result = oc.saveToURL("compose_doc_upload.jsp", "file", "", "senddoc.doc", 0);
					//alert(result);
					if(result.indexOf("SUCCESS")>=0){
						docForm.fromTemplate.value = "<%=CommonConst.NO%>";
						alert("保存成功！");
						if(window.opener!=null && window.opener.form1!=null ){
							window.opener.form1.submit();
						}
					}else{
						alert("保存失败！");
					}
			}		
			
			function change_onclick(mode) {
				//oc.ActiveDocument.BuiltInDocumentProperties(2)="BBBBB";
				if(mode=="normal")
					oc.ActiveDocument.ActiveWindow.ActivePane.View.Zoom.Percentage = normalsize;
				if(mode=="small")
					oc.ActiveDocument.ActiveWindow.ActivePane.View.Zoom.Percentage = smallsize;
				if(mode=="big")
					oc.ActiveDocument.ActiveWindow.ActivePane.View.Zoom.Percentage = bigsize;
				//var doc = oc.ActiveDocument;
				//var app = doc.application;
				//doc.PageSetup.LeftMargin = app.CentimetersToPoints(1);
				//doc.PageSetup.RightMargin = app.CentimetersToPoints(1);
			}
			
			function print_onclick()
			{
				copyInputToBookMark(oc,"hongtouNone","hongtou");
				copyInputToBookMark(oc,"hongtouNone","hongxian");
				var oldOption;	
				try
				{
					var objOptions =  oc.ActiveDocument.Application.Options;
					oldOption = objOptions.PrintBackground;
					objOptions.PrintBackground = true;
				}
				catch(err){};
				oc.printout(true);
				//$.get("../../WeGovPlatform/seal/print_log_info_add.do",{docInfoId:"<%=docId%>",docId:"<%=docId%>",procNo:"<%%>",docTitle:"<%%>"},function(data,textStatus){
					//alert(textStatus);
					//alert(data);
				//});
				try
				{
					var objOptions =  oc.ActiveDocument.Application.Options;
					objOptions.PrintBackground = oldOption;
				}
				catch(err){};
				copyInputToBookMark(oc,"hongtouBlock","hongtou");	
			}
			
			function printpreview_onclick()
			{
				oc.printpreview();
			}
			
			function showFile(){
				fileShow1.style.display = "none";
				fileShow2.style.display = "block";
			}

			window.setTimeout(showFile,2000);
		</script>
		<script type="text/javascript" src="ntko.js"></script>
	</head>
	<body onload="window_onload()">
		<div class="popup_width_1180">
			<div class="popup-body">
				<div class="fileShow2">
					<div id="fileShow1" class="fileShow2 heightCtrlNO2" style="display:block">
						<table width="100%" height="100%" border="0">
							<tr><td align="center" valign="middle">文档正在加载中……</td></tr>
						</table>
					</div>
					<%if(!"read".equals(op)&&!"protect".equals(op)){%>
					<ul class="rightCtrl">
					  <table align="center">
					          <tr align="center">
					               <%if(!"read".equals(op)&&!"protect".equals(op)){
					                button_text = "修改保存";
					                button_name = "save_button_top";
					                button_url ="save_onclick();";
					                %><td><%@ include file="../../platform/scripts/button_table.inc"%></td>
					                <td width="30">　　　　</td>
					                <%}%>
					                <%if("法制办签报件".equals(docType)||"预览无抄送".equals(docType)||"预览有抄送".equals(docType)){%>
					               <%
					                button_text = "套红头打印";
					                button_name = "print_button_top";
					                button_url ="print_onclick();";
					                %><td align="center"><%@ include file="../../platform/scripts/button_table.inc"%></td>
					                <td width="30">　　　　</td>
					                <%}%>
					               <%
					                button_text = "关  闭";
					                button_name = "close_button_top";
					                button_url ="window.close();";
					                %><td><%@ include file="../../platform/scripts/button_table.inc"%></td>
					          </tr>
					          <tr align="center">
					                <td colspan="5">（注意：文档编辑后请点击【修改保存】按钮完成保存操作！）</td>
					          </tr>
					  </table>
					</ul>
					<%}%>
					<div id="fileShow2" class="fileShow2 heightCtrlNO3">
							<object id="oc" classid="clsid:C9BC4DFF-4248-4a3c-8A49-63A7D317F404" width="100%" height="90%"
									codebase="../../general/cab/OfficeControl.cab#version=5,0,2,1">
								<param name="MakerCaption" value="万达信息股份有限公司">
								<param name="MakerKey" value="F5DBF1933EF1CB6E3EAC07768706D17AF202FCE5">
								<param name="ProductCaption" value="核心办公系统">
								<param name="ProductKey" value="7A78153EEA2141080E089B95BF69714A38D6E823">
								<param name="isuseutf8url" value="1" />
								<param name="IsUseUTF8Data" value="1" />
								<param name="BorderStyle" value="1" />
								<param name="BorderColor" value="14402205" />
								<param name="TitlebarColor" value="15658734" />
								<param name="TitlebarTextColor" value="0" />
								<param name="MenubarColor" value="14402205" />
								<param name="MenuButtonColor" value="16180947" />
								<param name="MenuBarStyle" value="3" />
								<param name="MenuButtonStyle" value="7" />
								<param name="WebUserName" value="ntko" />
								<param name="TitleBar" value="false" />
								<param name="MenuBar" value="<%="protect".equals(op)?"false":"true"%>" />
								<param name="ToolBars" value="<%="protect".equals(op)?"false":"true"%>" />
								<span style="color: red">请加载并运行文档控件。如果无法加载，请在检查浏览器的选项中检查浏览器的安全设置（请使用IE浏览器，并确保安装了Microsoft Word）。</span>
							</object>
					</div>
				</div>
				<ul>
				  <table align="center">
				          <tr align="center">
				               <%if(!"read".equals(op)&&!"protect".equals(op)){
				                button_text = "修改保存";
				                button_name = "save_button";
				                button_url ="save_onclick();";
				                %><td><%@ include file="../../platform/scripts/button_table.inc"%></td>
					              <td width="30">　　　　</td>
					            <%}%>
					            <%if("法制办签报件".equals(docType)||"预览无抄送".equals(docType)||"预览有抄送".equals(docType)){%>
					               <%
					                button_text = "套红头打印";
					                button_name = "print_button";
					                button_url ="print_onclick();";
					                %><td align="center"><%@ include file="../../platform/scripts/button_table.inc"%></td>
					                <td width="30">　　　　</td>
					            <%}%>
				                <%
				                button_text = "关  闭";
				                button_name = "close_button";
				                button_url ="window.close();";
				                %><td><%@ include file="../../platform/scripts/button_table.inc"%></td><td height="10"></td>
				          </tr>
						<%if(!"read".equals(op)&&!"protect".equals(op)){%>
					          <tr align="center">
					                <td colspan="5">（注意：文档编辑后请点击【修改保存】按钮完成保存操作！）</td>
					          </tr>
						<%}%>
				  </table>
				</ul>
			</div>
		</div>
		<form name="docForm" id="docForm" method="post">
			<input type="hidden" name="hongtouNone" value=""/>
			<input type="hidden" name="hongtouBlock" value="<%if("法制办签报件".equals(docType)){%>市政府法制办工作签报<%}else{%>上海市人民政府法制办公室<%}%>"/>
			<input type="hidden" name="docId" value="<%=docId%>"/>
			<input type="hidden" name="docType" value="<%=java.net.URLEncoder.encode(docType,"UTF-8")%>"/>
			<input type="hidden" name="fromTemplate" value="<%=fromTemplate%>"/>
			<%if(versionId!=null){%>
			<input type="hidden" name="versionId" value="<%=versionId%>"/>
			<%}%>
			<%for(String key:docProperties.keySet()){
				String value = docProperties.get(key);
				if(value!=null)
					value = value.replace('\"','“').replace('\r','$').replace('\n','$');
				if("".equals(value))
					value = " ";
			%>
			<input type="hidden" name="<%=key%>" value="<%=value%>"/>
			<%}%>
		</form>
    </body>
<%@ include file="/platform/include/scripts.jsp"%>
</html>
<script>
var oc = document.getElementById("oc");
oc.style.height='700px';
function OpenerReload(){
	   if(window.opener && !window.opener.closed){
		if(window.opener.document.condForm!=null)
			window.opener.document.condForm.submit();
		else if(window.opener.document.form1!=null)
			window.opener.document.form1.submit();
		else if(window.opener.document.conditionForm!=null)
			window.opener.document.conditionForm.submit();
		else if(window.opener.document.title!=null)
			window.opener.document.title.submit();
		else
			window.opener.location.reload();
	    }
	}
</script>
<%@ include file="/platform/include/html_foot.jsp"%>
