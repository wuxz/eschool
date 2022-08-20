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
	JwmaMessage message=(JwmaMessage) session.getValue("jwma.message");
	JwmaFolder folder=(JwmaFolder) session.getValue("jwma.folder");
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
    <title>jwma WebMail</title>
</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">

<%-- Display message --%>
<table width="100%">
    <tr bgcolor="#FFFFFF" valign="bottom">
        <td> <font color="#000000"><font face="Arial,Helvetica"><b><img src="../images/folder.png" width="36" height="36" alt="到文件夹">
            <%= htmlhelper.getPathHierarchyNavigator(store,folder)%> </b></font></font>
        </td>
    </tr>
</table>
<table border="0" width="100%" cellspacing="1" cellpadding="1">
    <tr>
    	<td align="left" bgcolor="#000000" height="20"> <img src="../images/message_small.png" width="20" height="20" alt="邮件">
            <font color="#FFFFFF" face="Arial, Helvetica, sans-serif"><b> #<%= message.getMessageNumber() %>
            </b></font> </td>
		<td align="right" bgcolor="#000000" height="20"> <font color="#FFFFFF" face="Arial, Helvetica, sans-serif"><b>
            日期:<%= date %></b></font> </td>
  	</tr>
  <tr>
    <td  align=left valign=top width="20%" bgcolor="#dddddd"><b>来自于:</b></td>
    <td  align=left valign=top width="80%" bgcolor="#eeeeee"> <%= message.getFrom() %>
    </td>
  </tr>
  <tr>
    <td  align=left valign=top width="20%" bgcolor="#dddddd"><b>到:</b></td>
    <td  align=left valign=top width="80%" bgcolor="#eeeeee"> <%= message.getTo() %>
    </td>
  </tr>
  <tr>
    <td  align=left valign=top width="20%" bgcolor="#dddddd"><b>主题:</b></td>
    <td  align=left valign=top width="80%" bgcolor="#eeeeee"> <em><%= message.getSubject() %></em>
    </td>
  </tr>
    <tr>
        <td  align="left" valign="top" colspan="2" bordercolor="#eeeeee"> <% if (message.isSinglepart()) { %>
            <pre><%= message.getBody() %></pre>
      <% } else {
							JwmaMessagePart[] parts=message.getMessageParts();
							for (int i=0;i<parts.length;i++) {
					%> <%= htmlhelper.getPartDescription(parts[i]) %> <%
					   		}
					   }
					%> </td>
  </tr>
</table>
<br>
<p>
<table>
  <tr>
    <td><font size="-2" face="arial,helvetica"><a href="message.jsp">邮件列印 </a></font></td>
  </tr>
</table>
<p>
</body>
</html>
