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
	JwmaMessage message=(JwmaMessage) session.getValue("jwma.message");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="Pragma" content="no-cache">
    <title>WebMail</title>
</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">

<%-- Display message header --%>
<pre>
<%= message.getFullHeader() %>
</pre>
</body>
</html>
