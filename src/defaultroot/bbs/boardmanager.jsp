<%@ page session="true" %>
<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ page import="com.dc.eschool.controller.web.ESchoolWebImpl,com.dc.eschool.util.WebKeys" %>
<jsp:useBean id="bbs" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>
<%
	String tempSTR,whereTo;
	byte[] tmpbyte;
	int method;
	String strSQL;
	java.sql.ResultSet sqlRst;
	int boardid,boardhits,boardtopics;
	String boardname = "";

	ESchoolWebImpl ew = (ESchoolWebImpl)session.getAttribute(WebKeys.ESchoolWebKey);
	if (ew == null || !"����Ա".equals(ew.getUserType()))
	{
%>
<jsp:forward page='about.jsp'/>
<%
	}

	tempSTR=null;
	tempSTR=request.getParameter("method");
	if (tempSTR==null)
		method=0;
	else
		method=java.lang.Integer.parseInt(tempSTR);

//'
//'method=4 ��ʾ������̳���
//'method=5 ��ʾɾ����̳���
//'method=6 �˳���̳����
//'
//'method=1 Ϊ������̳
//'method=2 Ϊ�޸���̳
//'method=3 Ϊɾ����̳

	if (method==6)
	{
		//'method=6 �˳���̳����                                     
%>
<jsp:forward page='about.jsp'/>
<%
	}  //'end if method==6;

	if (method==1)
	{
		//'method=1 Ϊ������̳                                                               
		boardname=request.getParameter("boardname");

		boardname=boardname.trim();

		tmpbyte=boardname.getBytes("ISO8859_1");
		boardname=new String(tmpbyte);											   

		boardhits=0;
		boardtopics=0;

		if (bbs.checkBoardName(boardname))
		{
			//'����

			whereTo="boardmanager.jsp?method=4";
%>
<jsp:forward page='<%=whereTo%>'/>
<%
		}else
		{
			//'end if                                                     

			bbs.addNewBoard(boardname);
%>
<jsp:forward page='boardmanager.jsp?method=0'/>
<%
		}// end else
	}  //'End if (method==1) {

	if (method==3)
	{
		//'method=3 Ϊɾ����̳                                       
		tempSTR=request.getParameter("boardid");
		boardid=java.lang.Integer.parseInt(tempSTR);

		bbs.delBoard(boardid);
%>
<jsp:forward page='boardmanager.jsp?method=0'/>
<%
	}   //'End if METHOD==3
%>

<html>

<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Board Management</title>
<link rel="stylesheet" type="text/css" href="bbs03.css">
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

function isemail(mystring)
{
	var istring=mystring;
	var atpos=mystring.indexOf("@");
	var temp=true;
	if (atpos==-1) //email��û��@���ţ�����ȷ��EMAIL
		temp=false;

	return temp;
}


function check_input(theForm)
{
	if (theForm=="frmAdd")
	{
		if ((frmAdd.boardname.value == "")|(isspacestring(frmAdd.boardname.value)))
		{
			alert("����������.");
			frmAdd.boardname.focus();
		
			return (false);
		}
		
		if (frmAdd.boardname.value.length > 20)
		{
			alert("̫����,����������.");
			frmAdd.boardname.focus();
			return (false);
		}
	}

	return (true);
}
-->
</script>

</head>
<body>
<table border="0" width="100%">
  <tr>
    <td width="25%" align="center"><a href="boardmanager.jsp" target="_self">�������</a></td>
    <td width="25%" align="center"><a href="boardmanager.jsp?method=6" target="_self">�˳�</a></td>
  </tr>
  <tr>
    <td width="100%" colspan="4">
      <hr>
    </td>
  </tr>
</table>
    <table border="0" width="400">
  <tr>
    <td width="100%">
    <table border="0" width="100%">
  <tr>
    <td width="100%">
      <p align="center"><b>��̳�������</b></p>
      <p align="center">������������&nbsp;&nbsp;&nbsp;&nbsp; <a href="boardmanager.jsp?method=4">���Ӱ���</a></p>                                                                              
    </td>                                                                     
  </tr>                                                                     
</table>                                                                     
      <table border="1" width="100%" bordercolorlight="#000000" bordercolordark="#FFFFFF" cellspacing="0" cellpadding="0">                                                                         
        <tr>                                                                              
          <td width="10%" align="center" bgcolor="#000080"><font color="#FFFFFF">ID</font></td>                                                                             
          <td width="50%" align="center" bgcolor="#000080"><font color="#FFFFFF">����</font></td>                                                                             
        </tr>                                                                             
<%
	sqlRst = bbs.listBoard(null, "BoardID");

	while (sqlRst.next())
	{
		boardid = sqlRst.getInt("BoardID");
		boardname = sqlRst.getString("BoardName");
%>                                                                          
                                                                           
        <tr>                                                                             
          <td width="10%" align="center"><%=boardid%></td>                                                                             
          <td width="50%" align="center"><a href="boardmanager.jsp?method=5&boardid=<%=boardid%>"><%=boardname%></a></td>                                                                            
        </tr>                                                                           
<%
	}
	
	bbs.close();                                                                    
%>                                                                          
      </table>                                                                      
  </td>                                                                     
  </tr>                                                                     
  </table>                                                               
                                                              
<%
	if (method==4)
	{
		//'method=4 ��ʾ������̳���                                                               
%>                                   
<div style="width: 214; height: 235; position: absolute; left: 432; top: 73">                                                                         
<table border="0" width="100%">                                                                      
  <tr>                                                                      
    <td width="100%">                                                                      
      <p align="center"><b>��������</b></p>                                                                     
    </td>                                                                     
  </tr>                                                                     
  <tr>                                                                     
    <td width="100%">                                                                     
      <form method="POST" action="boardmanager.jsp?method=1" onSubmit="return check_input(this)" name="frmAdd">                                                                     
        <p align="left" style="margin-top: 0; margin-bottom: 0">������<input type="text" name="boardname" size="20"></p>                                                                     
        <p align="center" style="margin-top: 0; margin-bottom: 0"><input class="buttonface"  type="submit" value="����" name="B1">&nbsp;&nbsp;&nbsp;&nbsp;                                                                           
        <input class="buttonface" type="reset" value="��д" name="B2"></p>                                                                          
      </form>                                                                          
    </td>                                                                          
  </tr>                                                                          
</table>                                                                          
</div>                                                                  
<%
	}  //'End if method==4

	if (method==5)
	{
		//'method=5 ��ʾɾ����̳���                                                              
		tempSTR=request.getParameter("boardid");
		boardid=java.lang.Integer.parseInt(tempSTR);

		boardname="asfasdf";

		sqlRst = bbs.listBoard(" where \"BoardID\" = " + boardid, "BoardID");
		if (sqlRst.next())
			boardname = sqlRst.getString("BoardName");
			
		bbs.close();
%>                                                                                   
<div style="width: 208; height: 242; position: absolute; left: 433; top: 93">                                                                            
<table border="0" width="89%">                                                                         
  <tr>                                                                         
    <td width="100%">                                                                         
      <p align="center"><b>����ɾ��</b></p>                                                                        
    </td>                                                                        
  </tr>                                                                        
  <tr>                                                                        
    <td width="100%">                                                                        
      <form method="POST" action="boardmanager.jsp" onSubmit="return check_input(this)" name="frmUpdate">                                                                        
        <input type="hidden" name="boardid" value="<%=boardid%>">                                                                         
        <input type="hidden" name="method" value=3>                                                                
        <table border="0" width="87%">                                                   
          <tr>                                                   
            <td width="50%">                                                               
        <p align="left" style="margin-top: 0; margin-bottom: 0">������<%=boardname%></p>                                                                       
        <p align="center" style="margin-top: 0; margin-bottom: 0"><input class="buttonface" type="submit" value="ɾ��" name="B1">
        </p>                                                                   
            </td>                                               
          </tr>                                               
        </table>                                               
      </form>                                                    
    </td>                                               
   </tr>                                                                   
</table>                                                                   
</div>                                                           
<%}  //'End if method==5%>                                                                         
</body>                                                                      
</html> 