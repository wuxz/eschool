<HTML>
<HEAD>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=gb2312" %>
<script language="JavaScript">
function NameGotFocus() {
//		document.LoginForm.username.focus();
	}
</script>
<TITLE></TITLE>
<link rel="stylesheet" href="a.css">
</HEAD>
<BODY onload="NameGotFocus()" bgcolor="#CEFFCE">
<%
	String getMessage=(String)session.getAttribute("confirm_message");
	if (getMessage==null)
		getMessage="";
	
	out.println(getMessage);%>
</BODY>
</HTML>
