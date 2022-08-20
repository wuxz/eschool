<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312"> 
	<meta http-equiv="Pragma" content="no-cache">
    <title>WebMail</title>
</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">
<%-- Header & Menu --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="36"><img src="../images/logo.png" width="195" height="36" align="right" alt="WebMail"></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
    <tr bgcolor="#000000"> 
        <td width="7%" align="center"><a href="login.jsp"><img src="../images/login_small.png" width="21" height="20" border="0" alt="登录"></a></td>
        <td width="93%" bgcolor="#000000">&nbsp;</td>
    </tr>
    <tr bgcolor="#dadada"> 
        <td width="7%" align="center" nowrap> <a href="../index.jsp"> <font face="Arial, Helvetica, sans-serif" size="-1"><b>重新登录</b></font> 
            </a> </td>
        <td width="93%" nowrap><font color="#00000"></font></td>
    </tr>
</table>
<%-- End Header & Menu --%>


<%-- Display Message --%>
<p>成功的登出。</p>
<p><em>谢谢您使用 WebMail!</em></p>
<% session.invalidate(); %>


<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom"> 
        <td> <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy; 
            2001</font> </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
