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
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	JwmaAddress add=prefs.getAddressBook().getAddress(request.getParameter("nick").trim());
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
		function submitSave(form){
			form.todo.value="add";
   		 	form.submit();
		}//submitFastAdd
	// -->
	</script>
</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	    <td height="36"> <img src="../images/inbox.png" alt="收件箱" width="36" height="36" align="middle">
            <font face="Arial,Helvetica" size="-1"><b><a href="<%= htmlhelper.getFolderDisplayAction(inbox) %>">收件箱</a></b>
            (<em><%= inbox.getNewMessageCount() %> new </em>/<%= inbox.getMessageCount()%>)
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
        <td width="10%" align="center" bgcolor="#dadada" nowrap> <font face="Arial, Helvetica, sans-serif" size="-1"><b><a href="addressbook.jsp">地址薄</a></b></font>
        </td>
        <td width="7%" align="center" nowrap> <a href="preferences.jsp"> <font face="Arial, Helvetica, sans-serif" size="-1"><b>设置</b></font>
            </a> </td>
        <td width="6%" align="center" nowrap> <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b>登出</b></font>
            </a> </td>
        <td width="65%" nowrap><font color="#00000"></font></td>
    </tr>
</table>
<%-- Display inlined errors --%>
<% if (error!=null && !error.isDisplayed()) { %>
	<font size="+1" color="#ff0000"><b><%= error.getDescription(true) %></b></font><br>
<% } %>
<%-- Display Addressbook --%>
<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
	<input type="hidden" name="acton" value="addressbook">
	<input type="hidden" name="todo" value="">
    <table width="90%" cellpadding="1" cellspacing="1" border="0">
        <tr bgcolor="#000000">
            <td height="20" nowrap colspan="2"> <img src="../images/address_small.png" width="29" height="20" alt="地址"><font face="Arial, Helvetica, sans-serif" color="#FFFFFF" size="+1">
                <i><b><%= add.getNickname() %>
                <input type="hidden" name="nickname" value="<%= add.getNickname() %>"></b></i>&nbsp;
				</font>
			</td>
		</tr>
				<tr>
				  <td bgcolor="#dddddd" nowrap>
					<b>名</b>
				  </td>
				  <td bgcolor="#eeeeee">
				  	<input type="text" name="lastname" value="<%= add.getLastname() %>" size="50" maxlength="50">
				  </td>
				</tr>
				<tr>
				  <td bgcolor="#dddddd" nowrap>
					<b>姓</b>
				  </td>
				  <td bgcolor="#eeeeee">
				  	<input type="text" name="firstname" value="<%= add.getFirstname() %>" size="50">
				  </td>
				</tr>
				<tr>
				  <td bgcolor="#dddddd" nowrap>
					<b>Email</b>
				  </td>
				  <td bgcolor="#eeeeee">
				  	<input type="text" name="email" value="<%= add.getEmail() %>" size="50">
				  </td>
				</tr>
				<tr>
				  <td bgcolor="#dddddd" nowrap valign="top"> <b>备注</b> </td>
				  <td bgcolor="#eeeeee">
				  	<textarea name="comment" rows="4" cols="60"><%= add.getComment() %></textarea>
				  </td>
				</tr>
				<tr>
				  <td bgcolor="#dddddd" nowrap>
					<b>电邮直投</b>
				  </td>
				  <td bgcolor="#eeeeee">
				    <input type="checkbox" name="frequent"  value="true" <%= ((add.isFrequentRecipient())? "checked":"") %>>
                    <font face="Arial, Helvetica, sans-serif"> 是<br>
                    </font>*<font size="-2" face="Arial, Helvetica, sans-serif">if
                     将直投信息发送到您的信箱
				 	</font>
				  </td>
				</tr>
				<tr>
				  <td bgcolor="#dddddd" nowrap>
					<b>公开</b>
				  </td>
				  <td bgcolor="#eeeeee">
				    <input type="checkbox" name="shared"  value="true" <%= ((add.isShared())? "checked":"") %>>
                    <font face="Arial, Helvetica, sans-serif"> Yes.<br>
                    </font>*<font size="-2" face="Arial, Helvetica, sans-serif">if
                     其他用户能看到您
				 	</font>
				  </td>
				</tr>


                <tr align="right">
                  <td bgcolor="#000000" height="20" colspan="2">
					<input type="reset" name="reset" value=" 取 消 ">
				 	<input type="button" name="update" value=" 保 存 " onClick="submitSave(this.form);">
				  </td>
                </tr>

              </table>
</form>


<p>&nbsp;</p>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom">
        <td> <a href="#top"><img src="../images/up_small.png" width="15" height="14" align="right" border="0" alt="回页首"></a>
            <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy;
            2001</font> </td>
  </tr>
</table>
</body>
</html>
