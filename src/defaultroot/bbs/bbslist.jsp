<%@ page session="true" %>
<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ page import="com.dc.eschool.controller.web.ESchoolWebImpl,com.dc.eschool.util.WebKeys" %>
<jsp:useBean id="bbs" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>
<jsp:useBean id="bbs2" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>
<%
	int boardid,method;
	String tempSTR;
	boardid=0;
	method=0;
	boolean delon = false;
	byte[] tmpbyte;

	tempSTR = request.getParameter("boardid");
	boardid=java.lang.Integer.parseInt(tempSTR);

	tempSTR=request.getParameter("method");
	if (tempSTR==null)
		method=0;
	else
		method = java.lang.Integer.parseInt(tempSTR);

//'method=0������ʾ����
//'method=1����=����
//'method=2����=������
//'method=3����=ʱ��
//'method=4����=������(���а���)
//'method=8��������DEL��
%>
<%
	int iPageSize;                  //                    'ÿҳ��ʾ�ļ�¼��
	int iPageCount;                 //                   'ҳ������
	int iPageCurrent=1;               //                 '��ʾ�ĵ�ǰҳ��
	int iPageLast=0;                  //                    '��һҳ
	int iPageNext=0;                  //                    '��һҳ
	int iRecordsShown;
	int iRecordsCount; //��¼����
	int iRecordsStart; //��ʼ��¼
	int i;//                            'ѭ������
	String par="";//                          '��ѯ����

	java.sql.ResultSet rs;
	java.sql.ResultSet sqlRst;

	int bbsid,parentid,child,bbshits,length;
	String username,useremail,userip,expression,usersign,bbstopic,bbscontent,bbshot;
	String boardname,boardmaster,masterword,masteremail;
	java.sql.Timestamp dateandtime = null;

	username="";
	boardname="";
	boardmaster="";
	masterword="";
	masteremail="";

	bbs.updateBoardHits(boardid, 1);   //'����������1

	rs = bbs.listBoard(" where \"BoardID\" = " + boardid, "BoardID");
	if (rs.next())
		boardname = rs.getString("BoardName");
		//'�ҳ����������,����,����,�����Ļ�

	bbs.close();
	rs = null;

	iRecordsCount=0;
	iPageCount=0;
	iPageSize =10;			//   'ÿҳ��ʾ10����¼,�ɸ��Ĳ���.

	//'������״���ʾ,��ǰҳΪ1,������������ҳ����ʾ

	tempSTR = request.getParameter("pages");
	if (tempSTR==null)
		iPageCurrent=1;
	else
		iPageCurrent = java.lang.Integer.parseInt(tempSTR);

	par=request.getParameter("par");
	if (par==null)
		par="";

	tmpbyte = par.getBytes("ISO8859_1");
	par = new String(tmpbyte);

	iRecordsCount = bbs.getBbsCount(method, boardid, par);
	iPageCount=0;
	if (iRecordsCount!=0)
	{
		iPageCount=iRecordsCount/iPageSize;
		if ((iRecordsCount%iPageSize)!=0)
			iPageCount++;					   //ҳ��������
	}

	iPageLast = iPageCount - 1;
	iPageNext = iPageCurrent + 1;
	if (iPageLast <= 0)
		iPageLast = 0;
	if (iPageNext > iPageCount)
		iPageNext = 0;

	if (iPageCurrent > iPageCount)
	   iPageCurrent = iPageCount;

	if (iPageCurrent < 0)
	   iPageCurrent = 0;

	{
		//  '����Ա����
		//'method=8��������DEL��
	
		delon = false;
		ESchoolWebImpl ew = (ESchoolWebImpl)session.getAttribute(WebKeys.ESchoolWebKey);
		if (ew != null && "����Ա".equals(ew.getUserType()))
			delon = true;
			
	}  //'End if
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>New Page 3</title>
<base target="rtop">
<link rel="stylesheet" type="text/css" href="bbs03.css">
</head>
<body link="#000080" vlink="#000080" alink="#000080">
<!--��ͷ���� -->
 <div align="center">
  <center>
 <table border="1" width="600" bordercolorlight="#000000" bordercolordark="#FFFFFF" bgcolor="#FAD185" cellspacing="0" cellpadding="3">
  <tr>
	<td width="30" bgcolor="#000080">
	  <p align="center"><a href="bbsadd.jsp?boardid=<%=boardid%>" target="_self"><font color="#FFFFFF">����</font></a></p>
	</td>
	<td width="30" bgcolor="#000080">
	 <p align="center"><a href="bbslist.jsp?boardid=<%=boardid%>" target="_self"><font color="#FFFFFF">ˢ��</font></a></p>
	</td>
	<td width="30" bgcolor="#000080">
	 <p align="center"><a href="query.jsp"><font color="#FFFFFF">��ѯ</font></a></p>
	</td>
	<td width="100"  bgcolor="#000080">
	  <p align="center"><a href="bbslist.jsp?boardid=<%=boardid%>&method=4"><font color="#FFFFFF">������</font></a></p>
	</td>
	<td width="200" bgcolor="#000080">
	  <p align="center">
	  <%if (iPageCount>1) {%><A HREF="bbslist.jsp?boardid=<%=boardid%>&pages=1&method=<%=method%>&par=<%=par%>">
	  <font color="#FFFFFF">��ҳ</font></A><%}%>

	  <%if (iPageLast!=0) {%>
	  <A HREF="bbslist.jsp?boardid=<%=boardid%>&pages=<%=iPageLast%>&method=<%=method%>&par=<%=par%>"><font color="#FFFFFF">��ҳ</font></A><%}%>

	  <%if (iPageNext!=0) {%>
	  <A HREF="bbslist.jsp?boardid=<%=boardid%>&pages=<%=iPageNext%>&method=<%=method%>&par=<%=par%>"><font color="#FFFFFF">��ҳ</font></A><%}%>

	  <%if (iPageCount>1) {%>
	   <A HREF="bbslist.jsp?boardid=<%=boardid%>&pages=<%=iPageCount%>&method=<%=method%>&par=<%=par%>"><font color="#FFFFFF">βҳ</font></A><%}%>

	   <font color="#FFFFFF"><%=iPageCurrent%>/<%=iPageCount%></font></p>
	</td>
	<td width="110" valign="middle" bgcolor="#000080">
	  <form method="GET" action="bbslist.jsp" style="margin-top: 0; margin-bottom: 0">
		<p align="center" style="margin-top: 0; margin-bottom: 0"><font color="#FFFFFF">ת��:<input type="text" name="pages" size="3" value="1">ҳ<input type="submit" value="GO" name="GO"></font></p>
		<input type="hidden" name="boardid" value="<%=boardid%>">
		<input type="hidden" name="method" value="<%=method%>">
		<input type="hidden" name="par" value="<%=par%>">
		</form>
	</td>
  </tr>
</table>
  </center>
 </div>
<!--��ͷ���ֽ��� -->
<P></P>
<%

	if (iPageCurrent>0)
	{
%>
<div align="left">
  <table border="0" width="600">
	<tr>
	  <td width="100%">
	  <ul>
<%

		sqlRst = bbs.listBbs(method,boardid,par);

		boolean bContinue = true;

		i = 1;
		while (i < iPageSize * (iPageCurrent  -1))
		{
			i ++;

			if (i >= iPageSize * (iPageCurrent  -1))
				break;

			if (!sqlRst.next())
			{
				bContinue = false;
				break;
			}
		}

		i = 0;
		while(bContinue && sqlRst.next() && i < iPageSize)
		{
			i ++;

			bbsid = sqlRst.getInt("BbsID");
			parentid = sqlRst.getInt("parentid");
			child = sqlRst.getInt("Child");

			username = sqlRst.getString("UserName");
			expression = sqlRst.getString("Expression");
			usersign = sqlRst.getString("Usersign");
			bbstopic = sqlRst.getString("BbsTopic");
			bbscontent = sqlRst.getString("BbsContent");
			dateandtime = sqlRst.getTimestamp("DateAndTime");

			bbshits = sqlRst.getInt("BbsHits");
			length = sqlRst.getInt("length");

%>

		  <li>
		   <p align="left"><img border="0" src="images/<%=expression%>"><a href="bbsaddre.jsp?boardid=<%=boardid%>&bbsid=<%=bbsid%>" ><%=bbstopic%></a>
		   &lt;<font color="#FF0000"><%if (length==0){%>������<%}else{%><%=length%>Bytes<%}%></font> <i><%=dateandtime%></i> [ID:<%=bbsid%>���:<%=bbshits%>�ظ�:<%=child%>]
           <%if (delon){%><a href="delete.jsp?bbsid=<%=bbsid%>&boardid=<%=boardid%>">ɾ��</a> <%}%>           

<!--������ʼ----------------------->
<%
			if ((method==0)||(method==8)||(method==4)||(method==1))
			{
				rs = bbs2.listBbsRe(bbsid);              //���г�ĳ�������и���

				while(rs.next())
				{
					bbsid=rs.getInt("BbsID");
					parentid=rs.getInt("ParentID");

					username = rs.getString("username");
					expression = rs.getString("Expression");
					usersign = rs.getString("UserSign");
					bbstopic = rs.getString("BbsTopic");
					bbscontent = rs.getString("BbsContent");
					dateandtime=rs.getTimestamp("DateAndTime");
					bbshits=rs.getInt("BbsHits");
					length=rs.getInt("Length");
%>
<ul>
<li type="circle">
		   <p align="left"><img border="0" src="images/<%=expression%>"><a href="bbsaddre.jsp?boardid=<%=boardid%>&bbsid=<%=parentid%>" ><%=bbstopic%></a>
		   &lt;<font color="#FF0000"><%if (length==0){%>������<%}else{%><%=length%>Bytes<%}%></font> <i><%=dateandtime%></i> </li>[ID:<%=bbsid%>���:<%=bbshits%>]
           <%if (delon){%><a href="delete.jsp?bbsid=<%=bbsid%>&boardid=<%=boardid%>">ɾ��</a> <%}%>           
</ul>

<%
				}  //end while

				bbs2.close();
			}	 //end if

%>
<!--��������------------------------->
<hr size="0" color="#808080">
<%
		}  //ȫ����¼����

		bbs.close();
%>

		  </ul>

	  </td>
	</tr>
  </table>
</div>
<!--��ͷ���� -->
 <div align="center">
  <center>
 <table border="1" width="600" bordercolorlight="#000000" bordercolordark="#FFFFFF" bgcolor="#FAD185" cellspacing="0" cellpadding="3">
  <tr>
	<td width="30" bgcolor="#000080">
	  <p align="center"><a href="bbsadd.jsp?boardid=<%=boardid%>" target="_self"><font color="#FFFFFF">����</font></a></p>
	</td>
	<td width="30" bgcolor="#000080">
	 <p align="center"><a href="bbslist.jsp?boardid=<%=boardid%>" target="_self"><font color="#FFFFFF">ˢ��</font></a></p>
	</td>
	<td width="30" bgcolor="#000080">
	 <p align="center"><a href="query.jsp"><font color="#FFFFFF">��ѯ</font></a></p>
	</td>
	<td width="100"  bgcolor="#000080">
	  <p align="center"><a href="bbslist.jsp?boardid=<%=boardid%>&method=4"><font color="#FFFFFF">������</font></a></p>
	</td>
	<td width="200" bgcolor="#000080">
	  <p align="center">
	  <%if (iPageCount>1) {%><A HREF="bbslist.jsp?boardid=<%=boardid%>&pages=1&method=<%=method%>&par=<%=par%>">
	  <font color="#FFFFFF">��ҳ</font></A><%}%>

	  <%if (iPageLast!=0) {%>
	  <A HREF="bbslist.jsp?boardid=<%=boardid%>&pages=<%=iPageLast%>&method=<%=method%>&par=<%=par%>"><font color="#FFFFFF">��ҳ</font></A><%}%>

	  <%if (iPageNext!=0) {%>
	  <A HREF="bbslist.jsp?boardid=<%=boardid%>&pages=<%=iPageNext%>&method=<%=method%>&par=<%=par%>"><font color="#FFFFFF">��ҳ</font></A><%}%>

	  <%if (iPageCount>1) {%>
	   <A HREF="bbslist.jsp?boardid=<%=boardid%>&pages=<%=iPageCount%>&method=<%=method%>&par=<%=par%>"><font color="#FFFFFF">βҳ</font></A><%}%>

	   <font color="#FFFFFF"><%=iPageCurrent%>/<%=iPageCount%></font></p>
	</td>
	<td width="110" valign="middle" bgcolor="#000080">
	  <form method="GET" action="bbslist.jsp" style="margin-top: 0; margin-bottom: 0">
		<p align="center" style="margin-top: 0; margin-bottom: 0"><font color="#FFFFFF">ת��:<input type="text" name="pages" size="3" value="1">ҳ<input type="submit" value="GO" name="GO"></font></p>
		<input type="hidden" name="boardid" value="<%=boardid%>">
		<input type="hidden" name="method" value="<%=method%>">
		<input type="hidden" name="par" value="<%=par%>">
		</form>
	</td>
  </tr>
</table>
  </center>
 </div>
<!--��ͷ���ֽ��� -->
<%
	}
	else
	{
		//'���δ������ҳ����Ϊ0����ʾ������Ϣ
%>
�ܱ�Ǹ��û���ҵ�������Ҫ�ļ�¼�������²�ѯ��
<%
	}
%>
</body>
</html>