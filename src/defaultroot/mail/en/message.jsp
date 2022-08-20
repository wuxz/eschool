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
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	JwmaMessage message=(JwmaMessage) session.getValue("jwma.message");
	JwmaFolder folder=(JwmaFolder) session.getValue("jwma.folder");
	String inserthandler="onChange=\"this.form.to.value=this.value;\"";
	JwmaError error=null;
    Object o=session.getValue("jwma.error");
	if (o!=null) {
		error=(JwmaError) o;
    }
	//Prepare datestring
	String date=htmlhelper.formatDate(message.getDate());
	if (message.isSent()) {
		date="<i>"+date+"</i>";
	}
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="Pragma" content="no-cache">
    <title>WebMail</title>
	<script type="text/javascript">
	<!--
		var headerwindow=null;

		function submitDelete(aform) {
			//enhance: check for selected folders or mailboxes
			aform.todo.value="delete";
			aform.numbers.value="actual";
			aform.submit();
		}
		function submitMove(aform){
			aform.todo.value="move";
			aform.numbers.value="actual";
    		aform.submit();
		}
		function submitReply(aform){
			aform.todo.value="compose";
			aform.reply.value="true";
			aform.submit();
		}
		function submitForward(aform){
			if(aform.to.value!=null && aform.to.value!="") {
				aform.todo.value="compose";
				aform.forward.value="true";
				aform.submit();
			}
		}

	// -->
	</script>

</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">

<%-- Header & Menu --%>
<table width="100%" border="0" cellspacing="1" cellpadding="1">
    <tr>
	    <td width="88%" height="36"><img src="../images/inbox.png" alt="收件箱" width="36" height="36" align="middle">
            <font face="Arial,Helvetica" size="-1"><b><a href="<%= htmlhelper.getFolderDisplayAction(inbox) %>">收件箱</a></b>
            (<em><%= inbox.getNewMessageCount() %> new </em>/<%= inbox.getMessageCount()%>)
            </font> </td>
        <td width="12%" height="36"><img src="../images/logo.png" width="195" height="36" align="right" alt="WebMail"><a name="top"></a></td>
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


<%-- Display message --%>
<table width="100%">
    <tr bgcolor="#FFFFFF" valign="bottom">
        <td> <font color="#000000"><font face="Arial,Helvetica"><b><a href="folder.jsp"><img src="../images/folder.png" width="36" height="36" border="0" alt="到文件夹" align="middle"></a>
            <%= htmlhelper.getPathHierarchyNavigator(store,folder)%> </b></font></font>
        </td>
    </tr>
</table>
<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
	<input type="hidden" name="acton" value="message">
	<input type="hidden" name="todo" value="">
	<input type="hidden" name="numbers" value="">
	<input type="hidden" name="reply" value="">
	<input type="hidden" name="forward" value="">
	<table border="0" width="90%" cellspacing="1" cellpadding="1">
        <tr>

            <td colspan="3" align="left" bgcolor="#000000"> <font color="#FFFFFF" face="Arial, Helvetica, sans-serif">
                <b><a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=display&amp;number=next">
                <img src="../images/next_small.png" width="20" height="15" align="right" alt="下一条" border="0" vspace="3"></a>
                <a href="printmessage.jsp"><img src="../images/printer_small.png" width="20" height="20" align="right" hspace="5" alt="友好打印" border="0"></a>
                <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=display&amp;number=prev"><img src="../images/previous_small.png" width="20" height="15" align="right" border="0" alt="上一条" vspace="3"></a>
                <img src="../images/message_small.png" width="20" height="20" alt="Message">
                #<%= message.getMessageNumber() %> 日期:<%= date %></b> </font>
            </td>
                </tr>
                <tr>

            <td align="left" valign="top" width="143" bgcolor="#dddddd"><b>来自:</b></td>
                  <td colspan="2" align="left" valign="top" bgcolor="#eeeeee"><%= message.getFrom() %></td>
                </tr>
                <tr>

            <td align="left" valign=top width="143" bgcolor="#dddddd"><b>到达:</b></td>
                  <td colspan="2" align="left" valign=top bgcolor="#eeeeee"><%= message.getTo() %></td>
                </tr>
                <tr>

            <td  align=left valign=top width="143" bgcolor="#dddddd" height="22"><b>主题:</b></td>

            <td  align=left valign=top width="587" bgcolor="#eeeeee" height="22"><em><%= message.getSubject() %></em>
            </td>

            <td  align=left valign=top width="21" bgcolor="#eeeeee" height="22"><a href="message_header.jsp"><img src="../images/headers_small.png" width="16" height="16" alt="Headers" border="0"></a></td>
                </tr>
                <tr>
                  <td  align="left" valign="top" colspan="3" bgcolor="#eeeeee">
                    <% if (message.isSinglepart()) { %>
                    <pre><%= message.getBody() %></pre>
                    <% } else {
							JwmaMessagePart[] parts=message.getMessageParts();
							for (int i=0;i<parts.length;i++) {
					%> <%= htmlhelper.getPartDescription(parts[i]) %> <%
					   		}
					   }
					%> </td>
                </tr>
                <tr>

            <td colspan="3" align="left" bgcolor="#000000" height="24"> <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=display&amp;number=next"><img src="../images/next_small.png" width="20" height="15" align="right" alt="下一条" border="0" vspace="3"></a>
                <a href="printmessage.jsp"><img src="../images/printer_small.png" width="20" height="20" align="right" hspace="5" alt="友好打印" border="0"></a>
                <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=display&amp;number=prev"><img src="../images/previous_small.png" width="20" height="15" align="right" alt="前一条" border="0" vspace="3"></a>
                <input type="button" name="delete" value="删除" onClick="submitDelete(this.form);">
                    <input type="button" name="move" value="转移到:" onClick="submitMove(this.form);">
                    <%= htmlhelper.getDestinationsSelect(store.listMessageMoveTargets()) %>
                  </td>
                </tr>
                <tr>
                  <td colspan="3" align="left" bgcolor="#000000" height="24">
                    <input type="button" name="replybutton" value="Reply" onClick="submitReply(this.form);">
                    <input type="checkbox" name="toall" value="true">
                    <font face="Arial, Helvetica, sans-serif" color="#ffffff" size="-1">全部回复</font>
                    <input type="checkbox" name="togglequote" value="true">
                    <font face="Arial, Helvetica, sans-serif" color="#ffffff" size="-1">回复作者</font> </td>
                </tr>
                <tr>
                  <td colspan="3" align="left" bgcolor="#000000" height="24" nowrap>
                    <input type="button" name="forwardbutton" value="转发给:" onClick="submitForward(this.form);">
                    <input type="text" name="to" size="30">
                    <%= htmlhelper.getFrequentSelect(prefs.getAddressBook(),inserthandler) %>
                  </td>
                </tr>
              </table>
            </form>

<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom">
        <td> <a href="#top"><img src="../images/up_small.png" width="15" height="15" align="right" border="0" alt="回页首"></a>
            <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy;
            2001</font> </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
