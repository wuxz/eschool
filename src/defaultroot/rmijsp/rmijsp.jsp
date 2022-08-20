<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>
Jsp1
</title>
</head>
<jsp:useBean id="bean0" scope="session" class="com.dc.eschool.rmiclient.RMIIntrfaceManagerTestClient" />
<jsp:setProperty name="bean0" property="*" />
<body>
<h1>
JBuilder Generated JSP
</h1>
<%
  bean0.getUserInfo("1");
  bean0.getUserInfoByName("admin");
  bean0.getCourseAllInfo("10").size();
  bean0.getExamContent("12");
  bean0.getExamContent("26","3");
  bean0.getExamInfo("10");
  bean0.getQuestionInfo("26","3");
  bean0.getContentInfo("32");
  bean0.getContentList("3");
  bean0.verifyPassword("qe","qe");
  bean0.login("3","202.198.0.26");
  bean0.verifyPassword("e","");
  bean0.logout("3");
%>
</body>
</html>