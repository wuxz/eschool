<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<jsp:useBean id="bbs" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>
<%@ page import="com.dc.eschool.controller.web.UsersWebImpl,com.dc.eschool.util.WebKeys" %>

<%
	String strSQL;//                       'ִ�е�SQL���
	java.sql.ResultSet sqlRst;				   //'ADODB RECORDSE ����

	int boardid;
	String tempSTR,whereTo;
	byte[] tmpbyte;
	String tt;       // '�������
	boardid=0;

	tempSTR=request.getParameter("boardid");
	boardid=java.lang.Integer.parseInt(tempSTR);
	tt=request.getParameter("tt");
	if (tt==null)
	 tt="";

%>
<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>����</title>
<link rel="stylesheet" type="text/css" href="bbs03.css">
</head>
<body>
<script Language="JavaScript">
<!--
function isspacestring(mystring)
//�Ƿ�Ϊ�ո��ַ���;trueΪ�գ�FALSEΪ�ǿ�
{
var istring=mystring;
	var temp,i,strlen;
	temp=true;
	strlen=istring.length;
	for (i=0;i<strlen;i++)
	{
		if ((istring.substring(i,i+1)!=" ")&(temp))
			temp=false;
	}

	return temp;
}

function firstisspace(mystring)
//�������ĸ�Ƿ��ǿո�TRUE����ĸΪ�ո�FALSE����ĸ��Ϊ�ո�
{
	var istring=mystring;
	var temp,i;
	temp=true;
	if (istring.substring(0,1)!=" ")
		temp=false;

	return temp;
}

function check_input(theForm)
{
	if ((theForm.bbstopic.value == "")|(isspacestring(theForm.bbstopic.value)))
	{
		alert("��ûд����.");
		theForm.bbstopic.focus();

		return (false);
	}

	return (true);
}
//-->
</script>
<!--�״ν��뱾ҳ-->
<div align="center">
  <center>
<table border="0" width="100%">
  <tr>
	<td width="100%">
	  <p align="center"><%=tt%></p>
	</td>
  </tr>
</table>
  </center>
</div>
<div align="center">
  <center>
  <table border="1" width="500" bordercolorlight="#000000" cellspacing="0" cellpadding="0" bordercolordark="#FFFFFF">
	<tr>
	  <td width="100%" bgcolor="#000080">
		<p align="center"><font color="#FFFFFF">�� Ҫ �� ��</font></td>
	</tr>
	<tr>
	  <td width="100%" bgcolor="#FFFFFF" valign="top">��


<div align="center">
  <center>
<table border="0" width="440">
  <tr>
	<td width="100%" valign="top">
<%
	String boardname="";

	sqlRst = bbs.listBoard(" where \"BoardID\" = " + boardid, "BoardID");
	if (sqlRst.next())
		boardname = sqlRst.getString("BoardName");

	bbs.close();
%>
	  <form method="POST" action="bbsadd.jsp" onsubmit="return check_input(this)" name="bbs_add_form">
		<p style="margin-top: 0; margin-bottom: 0"><b><font color="#008000">����:</font></b><a href="bbslist.jsp?boardid=<%=boardid%>"><%=boardname%></a></p>
		<p align="left" style="margin-top: 0; margin-bottom: 0"><b><font color="#008000">����:</font></b><input type="text" name="bbstopic" size="50"></p>
		<p align="left" style="margin-top: 0; margin-bottom: 0"><font color="#008000"><b>����:</b></font><input type="radio" value="001.gif" name="expression" checked><img border="0" src="images/001.gif"><input type="radio" value="002.gif" name="expression"><img border="0" src="images/002.gif"><input type="radio" value="003.gif" name="expression"><img border="0" src="images/003.gif" width="20" height="20"><input type="radio" value="004.gif" name="expression"><img border="0" src="images/004.gif" ><input type="radio" value="005.gif" name="expression"><img border="0" src="images/005.gif" ><input type="radio" value="006.gif" name="expression"><img border="0" src="images/006.gif" ><input type="radio" value="007.gif" name="expression"><img border="0" src="images/007.gif" ><input type="radio" value="008.gif" name="expression"><img border="0" src="images/008.gif" ><input type="radio" value="009.gif" name="expression"><img border="0" src="images/009.gif" ></p>
		<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" value="010.gif" name="expression"><img border="0" src="images/010.gif" ><input type="radio" value="011.gif" name="expression"><img border="0" src="images/011.gif" ><input type="radio" value="012.gif" name="expression"><img border="0" src="images/012.gif" ><input type="radio" value="013.gif" name="expression"><img border="0" src="images/013.gif" ><input type="radio" value="014.gif" name="expression"><img border="0" src="images/014.gif" ></p>
		<p align="left" style="margin-top: 0; margin-bottom: 0"><b><font color="#008000">����:</font></b><b><font color="#008000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</font></b><input class="buttonface" type="submit" value=" �� �� " name="B1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="buttonface" type="reset" value=" ȡ �� " name="B2"></p>
		<p align="left" style="margin-top: 0; margin-bottom: 0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<textarea rows="4" name="bbscontent" cols="50"></textarea>&nbsp;&nbsp;
		&nbsp;&nbsp;</p>
		<input type="hidden" name="boardid" value="<%=boardid%>"><input type="hidden" name="parentid" value="<%=0%>">
	  </form>
	</td>
  </tr>
</table>
  </center>
</div>
</td>
	</tr>
  </table>
  </center>
</div>

<%
	int parentid = 0, child = 0, bbshits = 0, length = 0;
	String username = null,useremail,userip,expression,usersign = "",bbstopic,bbscontent,dateandtime,userpassword;

	tempSTR=request.getParameter("boardid");
	boardid=java.lang.Integer.parseInt(tempSTR);

	tt=request.getParameter("tt");
	if (tt==null)
		tt="";

	String B1=request.getParameter("B1");
	if (B1!=null)
	{
		//��ʼ��������
		child=0;

		UsersWebImpl uw = (UsersWebImpl)session.getAttribute(WebKeys.UsersWebKey);
		if (uw != null)
				username = uw.getLoginName();

		tmpbyte=username.getBytes("ISO8859_1");
		username=new String(tmpbyte);

		expression=request.getParameter("expression");
		bbstopic=request.getParameter("bbstopic");
		bbstopic=bbstopic.trim();
		tmpbyte=bbstopic.getBytes("ISO8859_1");
		bbstopic=new String(tmpbyte);

		bbscontent=request.getParameter("bbscontent");
		tmpbyte=bbscontent.getBytes("ISO8859_1");
		bbscontent=new String(tmpbyte);
		if (bbscontent.length() > 2000)
			bbscontent = bbscontent.substring(0, 2000);

		bbshits=0;
		length=bbscontent.length();

//'========================================================================
		if (bbstopic==null)
		{
			tt="����û����ȫ ��������д";
%>
<script language="JavaScript">
	 window.location="bbsadd.jsp?boardid="+boardid+"&tt="+tt;
</script>
<%
		}

		bbs.addNewBbs(parentid, boardid, child, username, expression, usersign, bbstopic, bbscontent, bbshits, length);
		bbs.changeBbsChild (parentid,1);    //'BBS�����ظ�����1
		bbs.updateBoardTopics(boardid,1);//'������������1

		whereTo="bbslist.jsp?boardid="+boardid;
%>
<jsp:forward page='<%=whereTo%>'/>
<%
	}//if (B1!=null)
%>
</body>
</html>