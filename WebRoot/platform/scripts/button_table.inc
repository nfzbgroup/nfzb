<%
switch(button_text.length())
{
	case 2:
		button_width = 45;
		break;	
	case 3:
		button_width = 52;
		break;
	case 4:
		button_width = 65;
		break;
	case 5:
		button_width = 77;
		break;
	case 6:
		button_width = 90;
		break;
	case 7:
		button_width = 100;
		break;
	case 8:
		button_width = 115;
		break;
}
%>

<a href="#" onclick="<%=button_url%>"
	onmouseout="button_blur('<%=button_name%>');"
	onmouseover="button_focus('<%=button_name%>');" 
	onfocus="button_focus('<%=button_name%>');" 
	onblur="button_blur('<%=button_name%>');" >
<table style="cursor:hand;display:block;" border="1" bordercolor="#82AEDF" id="<%=button_name%>" background="/share/images/bg_button.gif" 
	cellpadding="0" cellspacing="1" width="<%=button_width%>">
<tr align="center"><td height="18" bordercolor="White" style="font-size:9pt;padding-top:2;"><%=button_text%></td></tr>
</table>
</a>
