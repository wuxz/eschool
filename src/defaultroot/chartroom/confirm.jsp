<html>
<head>
<title>╣гб╪╪Л╡И</title>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=gb2312" %>
</head>
<body>

<%@ page language="java"  %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.dc.eschool.controller.web.UsersWebImpl,com.dc.eschool.util.WebKeys" %>

<%
	UsersWebImpl uw = (UsersWebImpl)session.getAttribute(WebKeys.UsersWebKey);
	String regName = null;
	String Name=null;

	if (uw != null)
	{
		regName = uw.getLoginName();
		Name = regName;
	}

	if(regName == null || regName.length() == 0)
	{
		session.setAttribute("confirm_message","<center><font color=red><b>╢МнСпео╒ё╨</b></font><br><font color=blue><b>[дЦипн╢╣гб╪ё║]</b></font></center>");
%>
		<jsp:forward page="error.jsp"/>
<%
	}

	synchronized (application)
	{
		Vector UserName=null;
		UserName= (Vector)application.getAttribute("UserName");
		if(UserName==null)
		{
				UserName= new Vector(30,10);
		}

		if (!UserName.contains(Name))
		{
			UserName.addElement(Name);
			Name=new String(Name.getBytes("iso-8859-1"),"GBK");
			session.setAttribute("Name", Name);
		}

		application.setAttribute("UserName",UserName);
%>
		<script language="JavaScript">
		window.location="netchat1.jsp"
		</script>
<%
	}
%>

</body>
</html>