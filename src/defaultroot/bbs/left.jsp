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

	<div><a href="index.jsp" target="_top">返回首页</a></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	</td>
   </tr>
   <tr>
	<td width="100%" align="center">
	<div><a href="about.jsp" target="rtop">关于论坛</a></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	</td>
   </tr>
	<tr>
	<td width="100%" align="center">
	<div><a href="query.jsp">帖子查询</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	</td>
   </tr>
<!---------------------------------->
  <tr>
	<td width="100%" align="center">
<DIV align=center onClick="menuclick(submenu1, bar1 );" style="CURSOR: hand">
<img src="images/folder_01.gif" id=bar1  width="16" height="16">
<A HREF="#" target="_self">论坛版面</a><BR></DIV>
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
<A HREF="#" target="_self">排 行 榜</a><BR></DIV>
</td>
</tr>
  <tr>
	<td width="100%" align="center">
<DIV align=center class=parent><hr width="100" size="1"></div>

<DIV align=center  id=submenu2 style="DISPLAY: none; ">

	 <a href="point.jsp">-总排行榜</a><br>
	<a href="point.jsp?pages=2">-版面点击排行</a><br>
	<a href="point.jsp?pages=3">-版面积分排行</a><br>
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
	<div><a href="about_website.htm">了解网站</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	<div><a href="about_dialup.htm">模拟拨号上网</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	<div><a href="about_compose.htm">学习制作主页</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	<div><a href="about_domainname.htm">域名申请</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	<div><a href="about_download.htm">资源下载</a><br></div>
	<DIV align=center class=parent><hr width="100" size="1"></div>
	</td>
   </tr>
</table>
  </center>
</div>

</body>

</html>
