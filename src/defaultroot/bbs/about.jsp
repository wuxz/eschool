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
<title>��ҳ</title>
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

	bbs.close();//�رս����
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
		��ҿ�����㡰��ˮ���������ڱ�̳ע�ᷢ�Ա��ʾ���������������<br>
		<ol>
		  <li>�����л����񹲺͹���һ�з��ɷ��棻���ѷ���Ĺ۵��뿴������������ˣ��뱾վ�޹ء�</li>
		  <li>��վ��Ȩ��ע�����ѵ��˺Ž��й���</li>
		</ol>
	  </td>
	</tr>
  </table>
  </center>
</div>
</body>
</html>