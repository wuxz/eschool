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
	if (ew == null || !"管理员".equals(ew.getUserType()))
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
//'method=4 显示增加论坛表格
//'method=5 显示删除论坛表格
//'method=6 退出论坛管理
//'
//'method=1 为增加论坛
//'method=2 为修改论坛
//'method=3 为删除论坛

	if (method==6)
	{
		//'method=6 退出论坛管理                                     
%>
<jsp:forward page='about.jsp'/>
<%
	}  //'end if method==6;

	if (method==1)
	{
		//'method=1 为增加论坛                                                               
		boardname=request.getParameter("boardname");

		boardname=boardname.trim();

		tmpbyte=boardname.getBytes("ISO8859_1");
		boardname=new String(tmpbyte);											   

		boardhits=0;
		boardtopics=0;

		if (bbs.checkBoardName(boardname))
		{
			//'重名

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
		//'method=3 为删除论坛                                       
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
//是否为空格字符串;true为空，FALSE为非空
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
//检查首字母是否是空格，TRUE首字母为空格；FALSE首字母不为空格
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
	if (atpos==-1) //email中没有@符号；不正确的EMAIL
		temp=false;

	return temp;
}


function check_input(theForm)
{
	if (theForm=="frmAdd")
	{
		if ((frmAdd.boardname.value == "")|(isspacestring(frmAdd.boardname.value)))
		{
			alert("请输入版块名.");
			frmAdd.boardname.focus();
		
			return (false);
		}
		
		if (frmAdd.boardname.value.length > 20)
		{
			alert("太长了,请重新输入.");
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
    <td width="25%" align="center"><a href="boardmanager.jsp" target="_self">版面管理</a></td>
    <td width="25%" align="center"><a href="boardmanager.jsp?method=6" target="_self">退出</a></td>
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
      <p align="center"><b>论坛版面管理</b></p>
      <p align="center">操作请点击版面&nbsp;&nbsp;&nbsp;&nbsp; <a href="boardmanager.jsp?method=4">增加版面</a></p>                                                                              
    </td>                                                                     
  </tr>                                                                     
</table>                                                                     
      <table border="1" width="100%" bordercolorlight="#000000" bordercolordark="#FFFFFF" cellspacing="0" cellpadding="0">                                                                         
        <tr>                                                                              
          <td width="10%" align="center" bgcolor="#000080"><font color="#FFFFFF">ID</font></td>                                                                             
          <td width="50%" align="center" bgcolor="#000080"><font color="#FFFFFF">版面</font></td>                                                                             
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
		//'method=4 显示增加论坛表格                                                               
%>                                   
<div style="width: 214; height: 235; position: absolute; left: 432; top: 73">                                                                         
<table border="0" width="100%">                                                                      
  <tr>                                                                      
    <td width="100%">                                                                      
      <p align="center"><b>版面增加</b></p>                                                                     
    </td>                                                                     
  </tr>                                                                     
  <tr>                                                                     
    <td width="100%">                                                                     
      <form method="POST" action="boardmanager.jsp?method=1" onSubmit="return check_input(this)" name="frmAdd">                                                                     
        <p align="left" style="margin-top: 0; margin-bottom: 0">版名：<input type="text" name="boardname" size="20"></p>                                                                     
        <p align="center" style="margin-top: 0; margin-bottom: 0"><input class="buttonface"  type="submit" value="增加" name="B1">&nbsp;&nbsp;&nbsp;&nbsp;                                                                           
        <input class="buttonface" type="reset" value="重写" name="B2"></p>                                                                          
      </form>                                                                          
    </td>                                                                          
  </tr>                                                                          
</table>                                                                          
</div>                                                                  
<%
	}  //'End if method==4

	if (method==5)
	{
		//'method=5 显示删除论坛表格                                                              
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
      <p align="center"><b>版面删除</b></p>                                                                        
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
        <p align="left" style="margin-top: 0; margin-bottom: 0">版名：<%=boardname%></p>                                                                       
        <p align="center" style="margin-top: 0; margin-bottom: 0"><input class="buttonface" type="submit" value="删除" name="B1">
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