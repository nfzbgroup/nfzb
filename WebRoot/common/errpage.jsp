<%--
平台公共的报错页面<br>
\u2014\u2014系统发生错误时显示的页面<br>

@author Moyunzhe

--%>

<%@ page isErrorPage="true" contentType="text/html;charset=GBK"%>
<html>
<head>
<title>错误提示</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>


<body bgcolor="f7fcff" text="#000000">
<table width="500" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table cellspacing=0 cellpadding=0 width=100% align=center border=0 height="22">
        <tr>
          <td align=middle width=22 ><img height=22 src="/image/err_1.jpg" width=22></td>
          <td valign=middle align="center" background="/image/err_2.jpg" width="474"><b><font color="#FFFFFF" size="2">错
            误 提 示</font></b></td>
          <td width="4" align="left"><img height=22 src="/image/err_3.jpg" width=4></td>
        </tr>
      </table>
      <table cellspacing=0 cellpadding=0 width=100% align=center border=0>
        <tr>
          <td width=5 background=/image/err_4.jpg><img height=3 src="/image/err_4.jpg" width=5></td>
          <td align=center valign="bottom" width=490 height=22>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" height="200" bgcolor="EDEDED">
              <tr>
                <td rowspan="2" align="center" valign="middle" width="30%"><img src="/image/err_9.jpg" width="134" height="149"></td>
                <td width="70%" height="80%" align="center" valign="middle"><%=exception/*.getMessage()*/%><%exception.printStackTrace();%><img src="images/err_10.jpg" width="32" height="33"></td>
              </tr>
              <tr>
                <td width="70%" align="center" valign="middle" height="20%"><a href="javascript:history.back()">【
                  返 回 】</a>&nbsp;&nbsp;<a href="javascript:window.close()">【
                  关 闭 】</a></td>
              </tr>
            </table>
          </td>
          <td width=5 background=/image/err_5.jpg><img height=1 src="/image/err_5.jpg" width=4></td>
        </tr>
      </table>

      <table cellspacing=0 cellpadding=0 width=100% align=center border=0>
        <tr>
          <td width=5 height="5"><img src="/image/err_6.jpg" width="5" height="5"></td>
          <td width=490  background=/image/err_8.jpg></td>
          <td width=5><img height=4 src="/image/err_7.jpg" width=4></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
