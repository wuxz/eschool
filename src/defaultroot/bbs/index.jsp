<%@ page import="com.dc.eschool.controller.web.UsersWebImpl,com.dc.eschool.util.WebKeys" %>
<html>

<head>
  <meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
  <meta name="GENERATOR" content="Microsoft FrontPage 4.0">
  <meta name="ProgId" content="FrontPage.Editor.Document">
  <jsp:useBean id="bbs" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>

  <title>====��̳=====</title>
    <%
            UsersWebImpl uw = (UsersWebImpl)session.getAttribute(WebKeys.UsersWebKey);
            String username = null;
            if (uw != null)
                    username = uw.getLoginName();

            if (username == null || username.equals(""))
            {
    %>
    �Բ�������δ��¼��
    <%
            }
            else
            {
    %>
  <LINK REL="SHORTCUT ICON"  href="images/an.ico">
  <link rel="stylesheet" type="text/css" href="bbs03.css">
</head>

<frameset rows="100,*" cols="*" frameborder="NO" border="0" framespacing="0"> 
  <frame name="topFrame" scrolling="NO" noresize src="top.jsp" >
	<frameset cols="150,*" framespacing="1" border="1" frameborder="0">
	  <frame name="left" scrolling="no" noresize target="rtop" src="left.jsp">
	  <frame name="rtop" src="about.jsp" scrolling="auto" target="rtop">
	  <noframes>
	  <body>

	  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

	  </body>
	  </noframes>
	</frameset>
</frameset>


<%
	}
%>
</html>
