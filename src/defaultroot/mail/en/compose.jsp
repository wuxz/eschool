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
	JwmaMessage message=(JwmaMessage) session.getValue("jwma.composemessage");
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	String inserthandler="onChange=\"this.form.to.value=this.value;\"";
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
    <title>jwma WebMail</title>
</head>
<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">

<%-- Header & Menu --%>
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
        <td width="10%" align="center"><a href="addressbook.jsp"><img src="../images/addresses_small.png" width="20" height="20" border="0" alt="地址薄"></a></td>
        <td width="7%" align="center"><a href="preferences.jsp"><img src="../images/settings_small.png" width="20" height="20" border="0" alt="设置"></a></td>
        <td width="6%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout"><img src="../images/logout_small.png" width="21" height="20" border="0" alt="登出"></a></td>
        <td width="65%" bgcolor="#000000">&nbsp;</td>
    </tr>
    <tr bgcolor="#dadada">
        <td width="9%" align="center" nowrap> <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=redirect&amp;view=main">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b>电邮首页</b></font>
            </a> </td>
        <td width="3%" align="center" nowrap> <font face="Arial, Helvetica, sans-serif" size="-1"><b>写信</b></font>
        </td>
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
          	<form method="post" enctype="multipart/form-data" action="<%= htmlhelper.getSendMailControllerUrl() %>">

    <table width="90%" border="0" cellspacing="0" cellpadding="0">
        <tr>


            <td colspan=3 bgcolor="#000000"> <b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF">
                <img src="../images/compose_small.png" width="20" height="20" alt="写信">
                写新邮件</font></b> </td>
				</tr>
				<tr>
      				<td width="17%" bgcolor="#DDDDDD"><b>收件人</b></td>
      				<td width="1%" bgcolor="#DDDDDD"><b>:</b></td>
      				<td width="82%" bgcolor="#EEEEEE">
					<p>
                      <input type="text" name="to" size="50" value="<%= message.getTo() %>">
                      <%= htmlhelper.getFrequentSelect(prefs.getAddressBook(),inserthandler) %></p>
                    </td>
    			</tr>
    			<tr>
      				<td width="17%" bgcolor="#DDDDDD"><b>抄送</b></td>
      				<td width="1%" bgcolor="#DDDDDD"><b>:</b></td>
      				<td width="82%" bgcolor="#EEEEEE">
        				<input type="text" name="ccto" size="50" value="<%= message.getCCTo() %>">
                  	</td>
    			</tr>
    			<tr>
      				<td width="17%" bgcolor="#DDDDDD"><b>密件抄送</b></td>
      				<td width="1%" bgcolor="#DDDDDD"><b>:</b></td>
      				<td width="82%" bgcolor="#EEEEEE">
        				<input type="text" name="bccto" size="50" value="<%= message.getBCCTo() %>">
                  	</td>
    			</tr>
				<tr>
      				<td width="17%" bgcolor="#DDDDDD"><b>附件</b></td>
      				<td width="1%" bgcolor="#DDDDDD"><b>:</b></td>
      				<td width="82%" bgcolor="#EEEEEE">
        				<input type="file" name="attachment" size="36">
     				</td>
    			</tr>
    			<tr>
					<td width="17%" bgcolor="#DDDDDD"><b>主题</b></td>
      				<td width="1%" bgcolor="#DDDDDD"><b>:</b></td>
      				<td width="82%" bgcolor="#EEEEEE">
        				<input type="text" name="subject" size="50" value="<%= message.getSubject() %>">
     				</td>
   				</tr>
    			<tr>
      				<td width="17%" bgcolor="#DDDDDD"><b>内容</b></td>
      				<td width="1%" bgcolor="#DDDDDD"><b>:</b></td>
      				<td width="82%" bgcolor="#EEEEEE">&nbsp; </td>
    			</tr>
    			<tr>
      				<td colspan=3 bgcolor="#EEEEEE">


                <textarea name="body" cols="80" rows="25"><%= message.getBody() %></textarea>
      				</td>
    			</tr>
     			<tr>
      				<td width="17%" bgcolor="#DDDDDD"><b>签名</b></td>
     			 	<td width="1%" bgcolor="#DDDDDD"><b>:</b></td>
      				<td width="82%" bgcolor="#EEEEEE">
						<Select name="signature">
                      <option value="std" <%= ((prefs.isAutoSigning())? "selected" :"") %>>默认签名档</option>
                      <option value="alt">其他签名档</option>
                      <option value="none" <%= ((!prefs.isAutoSigning())? "selected" :"") %>>不使用签名</option>
                    </select>
	   				</td>
				</tr>
  			</table>
			<p>
			<table align=right>
  	 			<tr>
  	   				<td>
						<input type="reset" value=" 取 消 ">
  	  				</td>
  	   				<td width=10>&nbsp;</td>
  	   				<td>
  						<input type="submit" value=" 发 送 ">
  	   				</td>
  	 			</tr>
  			</table>
		</form>
<p>&nbsp;</p>
<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom">
        <td> <a href="#top"><img src="../images/up_small.png" width="15" height="15" align="right" border="0" alt="回到页首"></a>
            <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy;
            2001</font> </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
