<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<jsp:useBean id="bbs" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>

<%
	java.sql.ResultSet sqlRst;
	int i,method;
	String tempSTR,strSQL,whereTo;

	int boardid;
	String boardname,username,sel;
%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>New Page 1</title>
<link rel="stylesheet" type="text/css" href="bbs03.css">
</head>

<body>

<div align="center">
  <center>
  <table border="0" width="500">
	<tr>
	  <td width="100%" align="center"><b>���Ӳ�ѯ</b></td>
	</tr>

	<tr>
	  <td width="100%" align="left">
		<form method="POST" action="bbslist.jsp" target="rtop">
		  <p>��&nbsp; �⣺<input type="text" name="par" size="20">&nbsp;
		  <select size="1" name="boardid">
<%
	sqlRst = bbs.listBoard(null, "BoardID");
	i=1;

	while (sqlRst.next())
	{
		if (i==1)
			sel="selected";
		else
			sel="";

		boardid = sqlRst.getInt("BoardID");
		boardname = sqlRst.getString("BoardName");
%>

		  <option value="<%=boardid%>" <%=sel%>><%=boardname%></option>
<%
		i++;
	}  //end while

	bbs.close();
%> &nbsp; </select>&nbsp; <input class="buttonface" type="submit" value="��ѯ" name="B1"></p>
		  <input type="hidden" name="method" value="1">
		</form>
	  </td>
	</tr>
	<tr>
	  <td width="100%" align="left">
		<form method="POST" action="bbslist.jsp" target="rtop">
		  <p>�����ˣ�<select size="1" name="par">
<%
	sqlRst = bbs.listBbsUser(null, "UserName");
	i=1;
	while (sqlRst.next())
	{
		if (i==1)
			sel="selected";
		else
			sel="";

		username = sqlRst.getString("username");
%>
				<option value="<%=username%>" <%=sel%>><%=username%></option>
<%
		i++;
	}  //end while

	bbs.close();
//'=================
%>
		  </select>
		   <select size="1" name="boardid">
<%

	i=1;
	sqlRst = bbs.listBoard(null, "BoardID");

	while (sqlRst.next())
	{
		if (i==1)
			sel="selected";
		else
			sel="";

		tempSTR = sqlRst.getString("BoardID");
		boardid = java.lang.Integer.parseInt(tempSTR);
		boardname = sqlRst.getString("BoardName");
%>
		  <option value="<%=boardid%>" <%=sel%>><%=boardname%></option>
<%
		i++;
	}  //'end while

	bbs.close();

%>
		  </select>&nbsp; <input class="buttonface" type="submit" value="��ѯ" name="B1"></p>
		  <input type="hidden" name="method" value="2">
		</form>
	  </td>
	</tr>
	<tr>
	  <td width="100%" align="left">
		<form method="POST" action="bbslist.jsp" target="rtop">
		  <p>ʱ&nbsp; �䣺<select size="1" name="par">
			<option value="1" selected>��ʮ��Сʱ�ڵ�����</option>
			<option value="3">�����ڵ�����</option>
			<option value="5">�����ڵ�����</option>
			<option value="7">һ���ڵ�����</option>
			<option value="30">һ���ڵ�����</option>
			<option value="9>">�������ڵ�����</option>
		  </select>&nbsp; <select size="1" name="boardid">
<%

	sqlRst = bbs.listBoard(null, "BoardID");
	while (sqlRst.next())
	{
		if (i==1)
			sel="selected";
		else
			sel="";

		tempSTR = sqlRst.getString("BoardID");
		boardid=java.lang.Integer.parseInt(tempSTR);
		boardname = sqlRst.getString("BoardName");
%>
		  <option value="<%=boardid%>" <%=sel%>><%=boardname%></option>
<%
		i++;
	} //end while

	bbs.close();

%>
		  </select>&nbsp; <input class="buttonface" type="submit" value="��ѯ" name="B1"></p>
		  <input type="hidden" name="method" value="3">
		</form>
	  </td>
	</tr>
	<tr>
	  <td width="100%" align="left">
		<form method="POST" action="bbslist.jsp" target="rtop">
		<p>������&nbsp; <select size="1" name="boardid">
<%

	sqlRst = bbs.listBoard(null, "BoardID");

	while (sqlRst.next())
	{
		if (i==1)
			sel="selected";
		else
			sel="";

		tempSTR = sqlRst.getString("BoardID");
		boardid = java.lang.Integer.parseInt(tempSTR);
		boardname = sqlRst.getString("BoardName");
%>

		  <option value="<%=boardid%>" <%=sel%>><%=boardname%></option>
<%

		i++;
	} //end while

	bbs.close();
%>
		  </select><input class="buttonface" type="submit" value="��ѯ" name="B1"></p>
		  <input type="hidden" name="method" value="4">
		</form>
	  </td>
	</tr>
  </table>
  </center>
</div>
</body>
</html>
