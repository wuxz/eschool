<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<jsp:useBean id="bbs" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>
<%
	java.sql.ResultSet sqlRst = bbs.listBoard(null, "BoardID");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>首页</title>
<base target="rtop">

<link rel="stylesheet" type="text/css" href="bbs03.css">

</head>
<body>
<div align="center">
  <center>
  <table border="0" width="500">
<%
	while(sqlRst.next())
	{
%>
	<tr>
	  <td width="50%" align="center"><a href="bbslist.jsp?boardid=<%=sqlRst.getString("BoardID")%>"><%=sqlRst.getString("BoardName")%></a></td>
<%
		if (!sqlRst.next())
			break;
%>
	 <td width="50%" align="center"><a href="bbslist.jsp?boardid=<%=sqlRst.getString("BoardID")%>"><%=sqlRst.getString("BoardName")%></a></td>

  </tr>
<%
	}

	bbs.close();//关闭结果集
%>
  </table>
  </center>
</div>
<p>
<p>
<div align="center">
  <center>
  <table border="1" width="600" bordercolorlight="#000000" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF">
	<tr>
	  <td width="100%">&nbsp;&nbsp;&nbsp;&nbsp;
		大家可以随便“灌水”，但您在本坛注册发言便表示您将遵守以下条款：<br>
		<ol>
		  <li>遵守中华人民共和国的一切法律法规；网友发表的观点与看法仅代表其个人，与本站无关。</li>
		  <li>本站有权对注册网友的账号进行管理。</li>
		</ol>
	  </td>
	</tr>
  </table>
  </center>
</div>
</body>
</html>