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
	JwmaStoreInfo store=(JwmaStoreInfo) session.getValue("jwma.storeinfo");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaTrashInfo trash=(JwmaTrashInfo) session.getValue("jwma.trashinfo");
	JwmaFolder folder=(JwmaFolder) session.getValue("jwma.folder");
	JwmaError error=null;
    Object o=session.getValue("jwma.error");
	if (o!=null) {
		error=(JwmaError) o;
    }
	String sorthandler="onChange=\"submitSort(this.form)\"";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="Pragma" content="no-cache">
    <title>WebMail</title>
	<script type="text/javascript">
	<!--
		function submitDelete(aform,onwhat) {
			aform.acton.value=onwhat;
			aform.todo.value="delete";
			aform.submit();
		}
		function submitMove(aform,onwhat){
			aform.acton.value=onwhat;
			aform.todo.value="move";
    		aform.submit();
		}
		function submitCreate(aform) {
			aform.acton.value="folder";
			aform.todo.value="create";
			aform.submit();
		}
		function submitSort(aform) {
			aform.acton.value="folder";
			aform.todo.value="sortmessages";
			aform.submit();
		}

	// -->
	</script>
</head>
<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#ffffff">

<%-- Header & Menu --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	    <td height="36" width="80%"> <img src="../images/inbox.png" alt="收件箱" width="36" height="36" align="middle">
            <font face="Arial,Helvetica" size="-1"><b><a href="<%= htmlhelper.getFolderDisplayAction(inbox) %>">收件箱</a></b>
            (<em><%= inbox.getNewMessageCount() %> new </em>/<%= inbox.getMessageCount()%>)
            </font> </td>
        <td height="36" width="20%"><img src="../images/logo.png" width="195" height="36" align="right" alt="WebMail"><a name="top"></a></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
    <tr bgcolor="#000000">
        <td width="9%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=redirect&amp;view=main"><img src="../images/main_small.png" width="20" height="20" border="0" alt="电邮首页"></a></td>
        <td width="3%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose"><img src="../images/compose_small.png" width="20" height="20" border="0" alt="写信"></a></td>
        <td width="10%" align="center"><a href="addressbook.jsp"><img src="../images/addresses_small.png" width="20" height="20" border="0" alt="地址薄"></a></td>
        <td width="7%" align="center"><a href="preferences.jsp"><img src="../images/settings_small.png" width="20" height="20" border="0" alt="设置"></a></td>
        <td width="6%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout"><img src="../images/logout_small.png" width="21" height="20" border="0" alt="登出"></a></td>
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
<%-- Display inlined error --%>
<% if (error!=null && !error.isDisplayed()) { %>
	<font size="+1" color="#ff0000"><b><%= error.getDescription(true) %></b></font><br>
<% } %>
<%-- End inlined error --%>
<%-- Display Inbox information --%>

<%-- Include main view part respecting the store mode --%>
<% if (store.isMixedMode()) { %>
	<%@ include file="mixed_view.jsp" %>
<% } else { %>
	<%@ include file="distinct_view.jsp" %>
<% } %>

<%-- Insert information about trash here. --%>
<p>&nbsp;</p>
<%	if (trash.isEmpty()){ %> <img border="0" src="../images/trash_empty.png" alt="清空回收站" align="right">
  <% } else { %> <a href="<%= htmlhelper.getFolderDisplayAction(trash) %>">
  <img border="0" src="../images/trash_full.png" alt="清空回收站" align="right">
  </a> <% } %>
<br>
<p>&nbsp;</p>
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
