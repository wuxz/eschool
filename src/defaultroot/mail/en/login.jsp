<%@ page session="true" import="dtw.webmail.model.*" %>

<%-- Ensure valid session. --%>
<%
    if(session.isNew() || session.getValue("jwma.session")==null) {
    	response.sendRedirect(response.encodeRedirectUrl("index.jsp"));
		return;
    }
%>
<%-- Prepare references for use in the page --%>
<%
	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
%>
<%
	Object o=session.getValue("jwma.error");
	JwmaError error=null;
    if (o!=null) {
		error=(JwmaError) o;
    }
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="Pragma" content="no-cache">
	<title>WebMail</title>
</head>
<body bgcolor="#FFFFFF" link="#666666" vlink="#666666" alink="#FFFFFF">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr align="left" valign="top">
        <td height="19"><img src="../images/logo.png" width="195" height="36" alt="WebMail" align="right"></td>
    </tr>
    <tr align="left" valign="top" bgcolor="#000000">
        <td height="19">&nbsp;</td>
    </tr>
</table>
<%-- Display inlined error --%>
<% if (error!=null && !error.isDisplayed()) { %>
	<font size="+1" color="#ff0000"><b><%= error.getDescription(true) %></b></font><br>
<% } %>
<form action="<%= htmlhelper.getControllerUrl() %>" method="post" enctype="application/x-www-form-urlencoded">
	<input type="hidden" name="acton" value="session">
	<input type="hidden" name="todo" value="login">
	<table border="0" align="center" cellpadding="1" cellspacing="1">
        <tr valign="bottom">
			<td colspan="2" bgcolor="#000000" height="20"> <img src="../images/login_small.png" width="21" height="20" align="right" alt="登录">
                <font face="Arial, Helvetica, sans-serif" size="+1" color="FFFFFF"><b>登录</b></font>
            </td>
		</tr>
		<tr>
			<td width="30%" bgcolor="#dddddd">
				<font face="Arial, Helvetica">登录名:</font>
			</td>
			<td width="70%" bgcolor="#eeeeee">
				<input type="text" name="username" size="25">
			</td>
		</tr>
		<tr>
			<td width="30%" bgcolor="#dddddd">
				<font face="Arial, Helvetica">密码:</font>
			</td>
			<td width="70%" bgcolor="#eeeeee">
				<input type="password" name="password" size="25">
			</td>
		</tr>
		<tr>
			<td bgcolor="#000000" colspan="2" align="right" height="20">
				<input type="reset" name="reset" value="取消">
				&nbsp;&nbsp;&nbsp;
				<input type="submit" value="登录" name="submit">
			</td>
		</tr>
	</table>
</form>
<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr bgcolor="#000000" valign="bottom">
        <td> <a href="#top"><img src="../images/up_small.png" width="15" height="15" align="right" border="0" alt="To Top."></a>
            <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy;
            2001</font> </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
