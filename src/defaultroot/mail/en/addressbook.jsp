<%@ page session="true" import="dtw.webmail.model.*" %>
<%
	if(session.isNew()
       || session.getValue("jwma.session")==null
       || session.getValue("jwma.session.authenticated")==null) {
    		response.sendRedirect(response.encodeRedirectUrl("login.jsp"));
			return;
    }

	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaAddressBook addbook=prefs.getAddressBook();
	JwmaError error=null;
    Object o=session.getValue("jwma.error");
	if (o!=null) {
		error=(JwmaError) o;
    }
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="Pragma" content="no-cache">
	<title>WebMail</title>
	<script type="text/javascript">
	<!--
		function submitRemove(form){
			form.todo.value="remove";
			form.submit();
		}//submitRemove

		function submitFastAdd(form){
			form.todo.value="add";
   		 	form.submit();
		}//submitFastAdd
	// -->
	</script>
</head>
<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="36"> <img src="../images/inbox.png" alt="首件箱" width="36" height="36" align="middle">
            <font face="Arial,Helvetica" size="-1"><b><a href="<%= htmlhelper.getFolderDisplayAction(inbox) %>">
            收件箱</a></b> (<em><%= inbox.getNewMessageCount() %> new </em>/<%= inbox.getMessageCount()%>)
            </font> </td>
        <td height="36"><img src="../images/logo.png" width="195" height="36" align="right" alt="WebMail"><a name="top"></a></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
    <tr bgcolor="#000000">
        <td width="9%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=redirect&amp;view=main"><img src="../images/main_small.png" width="20" height="20" border="0" alt="电邮首页"></a></td>
        <td width="3%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose"><img src="../images/compose_small.png" width="20" height="20" border="0" alt="写信"></a></td>
        <td width="10%" align="center"><img src="../images/addresses_small.png" width="20" height="20" border="0" alt="地址薄"></td>
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
        <td width="10%" align="center" bgcolor="#dadada" nowrap> <font face="Arial, Helvetica, sans-serif" size="-1"><b>地址薄</b></font>
        </td>
        <td width="7%" align="center" nowrap> <a href="preferences.jsp"> <font face="Arial, Helvetica, sans-serif" size="-1"><b>设置</b></font>
            </a> </td>
        <td width="6%" align="center" nowrap> <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b>登出</b></font>
            </a> </td>
        <td width="65%" nowrap><font color="#00000"></font></td>
    </tr>
</table>
<% if (error!=null && !error.isDisplayed()) { %>
	<font size="+1" color="#ff0000"><b><%= error.getDescription(true) %></b></font><br>
<% } %>
<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
	<input type="hidden" name="acton" value="addressbook">
	<input type="hidden" name="todo" value="">
    <table width="95%" cellpadding="1" cellspacing="1" border="0">
        <tr valign="bottom">
            <td bgcolor="#000000" colspan="5"><img src="../images/addresses_small.png" width="20" height="20" alt="地址薄"><font face="Arial,Helvetica" color="#FFFFFF"><b>
                地址薄</b></font></td>
        </tr>
        <tr bgcolor="#dadada">
            <td nowrap width="10%"> <font color="#000000" face="Arial,Helvetica"><b>#</b></font>
            </td>
            <td nowrap width="11%"> <font color="#000000" face="Arial,Helvetica"><b>呢称</b></font>
            </td>
            <td nowrap width="33%"> <font color="#000000" face="Arial,Helvetica"><b>姓名
                (名, 姓)</b></font> </td>
            <td nowrap width="33%"> <font color="#000000" face="Arial,Helvetica"><b>地址</b></font>
            </td>
            <td nowrap width="12%"> <font color="#000000" face="Arial,Helvetica"><b>频度</b></font>
            </td>
        </tr>
        <%
			String[] nicknames=addbook.listNicknames();
			if(nicknames==null || nicknames.length==0) {
				//empty message
		%>
        <tr>
            <td bgcolor="#eeeeee" colspan="5"><i>您的地址薄为空.</i></td>
        </tr>
        <%  } else {
				//loop over entries
				for (int index=0;index<nicknames.length;index++) {
						JwmaAddress add=addbook.getAddress(nicknames[index]);
		%>
        <tr>
            <td bgcolor="#eeeeee" width="10%">
                <input type="checkbox" name="nicknames" value="<%= add.getNickname() %>">
            </td>
            <td bgcolor="#eeeeee" nowrap width="11%"> <font face="Arial,Helvetica" size="-1">
                <a href="addressbook_entry.jsp?nick=<%= add.getNickname() %>"><em><%= add.getNickname() %></em></a>
                </font> </td>
            <td bgcolor="#eeeeee" nowrap width="33%"> <font face="Arial,Helvetica" size="-1"><%= add.getLastname() %>,
                <%= add.getFirstname() %></font> </td>
            <td bgcolor="#eeeeee" nowrap width="33%"> <font face="Arial,Helvetica" size="-1">
                <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose&amp;to=<%= add.getEmail() %>">
                <%= add.getEmail() %> </a> </font> </td>
            <td nowrap bgcolor="#dddddd" width="13%"> <font face="Arial,Helvetica" size="-1"><%= ((add.isFrequentRecipient())? "y":"n") %></font>
            </td>
        </tr>
        <%
				}//for end
			}//else end
		 %>
        <tr>
            <td bgcolor="#000000" height="3" width="10%">&nbsp;</td>
            <td bgcolor="#000000" height="3" width="11%">&nbsp;</td>
            <td bgcolor="#000000" height="3" width="33%">&nbsp;</td>
            <td bgcolor="#000000" height="3" width="33%">&nbsp;</td>
            <td bgcolor="#000000" height="3" width="13%">&nbsp;</td>
        </tr>
        <tr>
            <td bgcolor="#eeeeee" width="10%"> <i>添加:</i></td>
            <td bgcolor="#eeeeee" nowrap width="11%">
                <input type="text" name="nickname" size="10">
            </td>
            <td bgcolor="#eeeeee" nowrap width="33%">
                <input type="text" name="lastname" size="20">
                ,
                <input type="text" name="firstname" size="20">
            </td>
            <td bgcolor="#eeeeee" nowrap width="33%">
                <input type="text" name="email" size="35">
            </td>
            <td nowrap bgcolor="#dddddd" width="13%">
                <input type="checkbox" name="frequent" value="true">
            </td>
        </tr>
        <tr align="right">
            <td colspan="5" bgcolor="#000000">
                <input type="button" name="remove" value="删除选择" onClick="submitRemove(this.form);">
                <input type="button" name="add" value="添加" onclick="submitFastAdd(this.form);">
            </td>
        </tr>
    </table>
</form>
<p>&nbsp;</p>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom">
        <td> <a href="#top"><img src="../images/up_small.png" width="15" height="15" align="right" border="0" alt="To Top"></a>
            <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy;
            2001</font> </td>
  </tr>
</table>
</body>
</html>