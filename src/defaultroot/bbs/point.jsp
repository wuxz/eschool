<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<jsp:useBean id="bbs" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>
<%
	String tempSTR,strSQL;//                       'ִ�е�SQL���
	java.sql.ResultSet sqlRst;
	java.sql.ResultSet sqlRst2;
	int i,pages;

	String errmsg=request.getParameter("errmsg");

	tempSTR=request.getParameter("pages");
	if (tempSTR==null)
		pages=0;
	else
		pages=java.lang.Integer.parseInt(tempSTR);

//'
//'
//'pages=1  ���˻������а�
//'pages=2  ��̳�����
//'pages=3  ��̳������
//'
//'
%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>New Page 4</title>
<link rel="stylesheet" type="text/css" href="bbs03.css">
</head>

<body>
<%
	if ((pages==2)||(pages==0))
	{
		//'pages=2  ��̳�����
%>
<div align="center">
  <center>
        <table border="1" width="90%" bordercolorlight="#000000" bordercolordark="#FFFFFF" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%" bgcolor="#000080" bordercolorlight="#000000" bordercolordark="#FFFFFF" colspan="6">
              <p align="center"><b><font color="#FFFFFF">��̳��������а�</font></b></td>
          </tr>
          <tr>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>����</b></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>���</b></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>�����</b></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>����</b></td>
            <td width="16%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>���</b></td>
            <td width="16%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>�����</b></td>
          </tr>
<%
		i=1;
		sqlRst = bbs.listBoard(null, "BoardHits");
		while ((i<=10)&&(sqlRst.next()))
		{
%>
          <tr>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <%=i%></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <a href="bbslist.jsp?boardid=<%=sqlRst.getString("BoardID")%>" target="rtop"><%=sqlRst.getString("BoardName")%></a></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <%=sqlRst.getString("BoardHits")%></td>
<!---------------------->
<%
			if (!sqlRst.next())
				break;

			i++;
%>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <%=i%></td>
            <td width="16%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
               <a href="bbslist.jsp?boardid=<%=sqlRst.getString("BoardID")%>" target="rtop"><%=sqlRst.getString("BoardName")%></a></td>
            <td width="16%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
             <%=sqlRst.getString("BoardHits")%></td>
          </tr>
<%
			i++;
		}   //end while

		bbs.close();
%>
        </table>
  </center>
</div>
<p></p>
<%
	}  //'end if
%>
<!------------------------------------------------------------------>
<%
	if ((pages==3)||(pages==0))
	{
		//'pages=3  ��̳������
%>
<div align="center">
  <center>
        <table border="1" width="90%" bordercolorlight="#000000" bordercolordark="#FFFFFF" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%" bgcolor="#000080" bordercolorlight="#000000" bordercolordark="#FFFFFF" colspan="6">
              <p align="center"><b><font color="#FFFFFF">������̳���а�</font></b></td>
          </tr>
          <tr>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>����</b></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>���</b></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>������</b></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>����</b></td>
            <td width="16%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>���</b></td>
            <td width="16%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <b>������</b></td>
          </tr>
<%
	i=1;
	sqlRst = bbs.listBoard (null, "BoardTopics");
	while ((i<=10)&&(sqlRst.next()))
	{
%>
          <tr>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <%=i%></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
               <a href="bbslist.jsp?boardid=<%=sqlRst.getString("BoardID")%>" target="rtop"><%=sqlRst.getString("BoardName")%></a></td>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <%=sqlRst.getString("BoardTopics")%></td>
<%//''''''''''''''
			if (!sqlRst.next())
				break;

			i++;
%>
            <td width="17%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
              <%=i%></td>
            <td width="16%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
               <a href="bbslist.jsp?boardid=<%=sqlRst.getString("BoardID")%>" target="rtop"><%=sqlRst.getString("BoardName")%></a></td>
            <td width="16%" bordercolorlight="#000000" bordercolordark="#FFFFFF" align="center">
             <%=sqlRst.getString("BoardTopics")%></td>
          </tr>

<%
			i++;
		}  //'end while

		bbs.close();
%>
        </table>
  </center>
</div>
<p></p>
<%
	}  //'end if
%>

</body>

</html>
