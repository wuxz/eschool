<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<jsp:useBean id="bbs" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>
<%
	java.sql.ResultSet sqlRst = bbs.listBoard(null, "BoardID");
%>

<html>

<head>
<title>New Page 2</title>
<base target="rtop">

<link rel="stylesheet" type="text/css" href="bbs03.css">

<SCRIPT language=JavaScript>
<!--
var old_menu = '';
var old_cell = '';
function menuclick( submenu ,cellbar)
{
	if( old_menu != submenu )
	{
		if( old_menu !='' )
		{
			old_menu.style.display = 'none';
			old_cell.src= 'images/folder_01.gif';
		}

		submenu.style.display = 'block';
		cellbar.src = 'images/folder_02.gif';
		old_menu = submenu;
		old_cell = cellbar;
	}
	else
	{
		submenu.style.display = 'none';
		cellbar.src= 'images/folder_01.gif';
		old_menu = '';
		old_cell = '';
	}
}
-->
</SCRIPT>
</head>

<body>

<div align="center">
  <center>

<table border="0" width="130">
  <tr>
	<td width="100%" align="center">

	<div><a href="index.jsp" target="_top">������ҳ</a></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	</td>
   </tr>
   <tr>
	<td width="100%" align="center">
	<div><a href="about.jsp" target="rtop">������̳</a></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	</td>
   </tr>
	<tr>
	<td width="100%" align="center">
	<div><a href="query.jsp">���Ӳ�ѯ</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	</td>
   </tr>
<!---------------------------------->
  <tr>
	<td width="100%" align="center">
<DIV align=center onClick="menuclick(submenu1, bar1 );" style="CURSOR: hand">
<img src="images/folder_01.gif" id=bar1  width="16" height="16">
<A HREF="#" target="_self">��̳����</a><BR></DIV>
</td>
</tr>
  <tr>
	<td width="100%" align="center">
<DIV align=center class=parent><hr width="100" size="1"></div>

<DIV align=center  id=submenu1 style="DISPLAY: none; ">


<%
	while(sqlRst.next())
	{
%>
<a href="bbslist.jsp?boardid=<%=sqlRst.getString("BoardID")%>"><%=sqlRst.getString("BoardName")%></a><br>
<%
	}

	bbs.close();
%>
	<hr width="100" size="1">

</div>
	</td>
	</tr>
  <tr>
	<td width="100%" align="center">
<!---------------------------------->
<DIV align=center onClick="menuclick(submenu2, bar2 );" style="CURSOR: hand">
<img src="images/folder_01.gif" id=bar2  width="16" height="16">
<A HREF="#" target="_self">�� �� ��</a><BR></DIV>
</td>
</tr>
  <tr>
	<td width="100%" align="center">
<DIV align=center class=parent><hr width="100" size="1"></div>

<DIV align=center  id=submenu2 style="DISPLAY: none; ">

	 <a href="point.jsp">-�����а�</a><br>
	<a href="point.jsp?pages=2">-����������</a><br>
	<a href="point.jsp?pages=3">-�����������</a><br>
	<hr width="100" size="1">
</div>
</td>
</tr>
  <tr>
	<td width="100%" align="center">
<!---------------------------------->
</td>
</tr>
  <tr>
	<td width="100%" align="center">
<DIV align=center class=parent><hr width="100" size="1"></div>

</td>
</tr>
  <tr>
	<td width="100%" align="center">
<!---------------------------------->
</td>
</tr>
  <tr>
	<td width="100%" align="center">
<DIV align=center class=parent><hr width="100" size="1"></div>

</td>
</tr>
  <tr>
	<td width="100%" align="center">
<!---------------------------------->
	</td>
<DIV align=center class=parent><hr width="100" size="1"></div>
  </tr>
	<tr>
	<td width="100%" align="center">
	<div><a href="about_website.htm">�˽���վ</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	<div><a href="about_dialup.htm">ģ�Ⲧ������</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	<div><a href="about_compose.htm">ѧϰ������ҳ</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	<div><a href="about_domainname.htm">��������</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	<div><a href="about_download.htm">��Դ����</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	</td>
   </tr>
</table>
  </center>
</div>

</body>

</html>
