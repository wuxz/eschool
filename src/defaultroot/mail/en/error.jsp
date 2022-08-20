<%@ page session="true" import="dtw.webmail.model.*" %>

<%-- Ensure authenticated & valid session --%>
<%
	if(session.isNew()
       || session.getValue("jwma.session")==null
       || session.getValue("jwma.session.authenticated")==null) {
    		response.sendRedirect(response.encodeRedirectUrl("login.jsp"));
			return;
    }
%>
<%-- Prepare references for use in the page --%>
<%
	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
    Object o=session.getValue("jwma.error");
	JwmaError error=null;
	if(o!=null) {
		error=(JwmaError)o;
	} else {
		//try redirect to actual view (FIXME:this could loop)
		response.sendRedirect(response.encodeRedirectUrl(
			htmlhelper.getControllerUrl()+
			"?acton=session&amp;todo=redirect&amp;view=actual")
		);
	}
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="Pragma" content="no-cache">
    <title>WebMail</title>
</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">

<%-- Header & Menu --%>
<table width="100%" border="0" cellspacing="1" cellpadding="1">
  <tr>
    <td><img src="../images/logo.png" width="195" height="36" align="right"><a name="top"></a></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
    <tr bgcolor="#000000">
        <td width="9%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=redirect&amp;view=main"><img src="../images/main_small.png" width="20" height="20" border="0"></a></td>
        <td width="3%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose"><img src="../images/compose_small.png" width="20" height="20" border="0"></a></td>
        <td width="10%" align="center"><a href="addressbook.jsp"><img src="../images/addresses_small.png" width="20" height="20" border="0"></a></td>
        <td width="7%" align="center"><a href="preferences.jsp"><img src="../images/settings_small.png" width="20" height="20" border="0"></a></td>
        <td width="6%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout"><img src="../images/logout_small.png" width="21" height="20" border="0"></a></td>
        <td width="65%" bgcolor="#000000">&nbsp;</td>
    </tr>
    <tr bgcolor="#dadada">
        <td width="9%" align="center" nowrap> <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=redirect&amp;view=main">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b>电邮首页</b></font>
            </a> </td>
        <td width="3%" align="center" nowrap> <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b>写信</b></font>
            </a> </td>
        <td width="10%" align="center" nowrap> <a href="addressbook.jsp"> <font face="Arial, Helvetica, sans-serif" size="-1"><b>地址薄</b></font>
            </a> </td>
        <td width="7%" align="center" nowrap> <a href="preferences.jsp"> <font face="Arial, Helvetica, sans-serif" size="-1"><b>设置</b></font>
            </a> </td>
        <td width="6%" align="center" nowrap> <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b>登出</b></font>
            </a> </td>
        <td width="65%" nowrap><font color="#00000"></font></td>
    </tr>
</table>
<%-- End Header & Menu --%>

<p>
<% if (error!=null) { %>
	<font size="+1" color="#ff0000"><b><%= error.getDescription(true) %></b></font>
<% } else { %>
	<font size="+1" color="#ff0000"><b>没有错误！</b></font>
<% } %>
</p>

<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom">
    <td> <a href="#top"><img src="../images/up_small.png" width="15" height="15" align="right" border="0"></a>
      <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy;
      2001 </font> </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
