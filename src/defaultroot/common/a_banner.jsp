<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<table width="779" border="0" cellspacing="1" cellpadding="1" bgcolor="#FFFFFF">
  <tr valign="bottom">
    <td width="130" align="center" height="80"><img src="<%= request.getContextPath() %>/image/logo.gif" width="112" height="76"></td>
    <td width="640"><img src="<%= request.getContextPath() %>/image/AD.gif" width="632" height="65"></td>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="779" border="0" cellspacing="0" cellpadding="0" bgcolor="#32009f">
  <tr>
    <td width="51"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="5"></td>
    <td background="<%= request.getContextPath() %>/image/navigator_shadow.gif" valign="top" height="5"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="5"></td>
  </tr>
</table>


<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
<!--
	NS4 = (document.layers);
	IE4 = (document.all);
	ver4 = (NS4 || IE4);
	IE5 = (IE4 && navigator.appVersion.indexOf("5.")!=-1);
	isMac = (navigator.appVersion.indexOf("Mac") != -1);
	isMenu = (NS4 || (IE4 && !isMac) || (IE5 && isMac));

	function popUp(){
		return;
	}

	function popDown(){
		return
	}

	if (!ver4) event=null;

//-->
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript1.2" TYPE="text/javascript">
<!--
	if (isMenu) {
		menuVersion = 3;
		menuWidth = 120;
		childOverlap = 20;
		childOffset = 5;
		perCentOver = null;
		secondsVisible = .5;
		fntCol = "blue";
		fntSiz = "9";
		fntBold = false;
		fntItal = false;
		fntFam = "Arial,sans-serif,宋体";
		backCol = "#DDDDDD";
		overCol = "#FFCCCC";
		overFnt = "purple";
		borWid = 2;
		borCol = "black";
		borSty = "solid";
		itemPad = 3;
		imgSrc = "/images/other/tri.gif";
		imgSiz = 10;
		separator = 1;
		separatorCol = "black";

		isFrames = false;      // <-- IMPORTANT
    	        navFrLoc = "top";    // <-- frameset-specific
    	        mainFrName = "main";  // <-- variables

		keepHilite = true;
		clickStart = false;
		clickKill = true;
	}
//-->
</SCRIPT>

<SCRIPT LANGUAGE="JavaScript1.2" TYPE="text/javascript" SRC='<%= request.getContextPath() %>/common/hierArrays.js'>
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" TYPE="text/javascript" SRC='<%= request.getContextPath() %>/common/hierMenus.js'>
</SCRIPT>
  <table width="779" border="1"  cellpadding="2" cellspacing="0" bgcolor="#efefef" bordercolorlight="#000000" bordercolordark="#FFFFFF">
    <tr align="center">
      <td width=100>&nbsp;</td>
      <td><A HREF="admininfo" >个人信息</A></td>
      <td><A HREF="javascript:void(0)" onMouseOver="popUp('elMenu2',event)" onMouseOut="popDown('elMenu2')" onClick="return false">资料管理</A></td>
      <td><A HREF="javascript:void(0)" onMouseOver="popUp('elMenu3',event)" onMouseOut="popDown('elMenu3')" onClick="return false">用户管理</A></td>
      <td><A HREF="javascript:void(0)" onMouseOver="popUp('elMenu4',event)" onMouseOut="popDown('elMenu4')" onClick="return false">课程管理</A></td>
      <td><A HREF="<%= request.getContextPath() %>/bbs/boardmanager.jsp" >论坛管理</A></td>
      <td><A HREF="<eschool:eschoolAttribute attribute="mailpath"/>/webmail" >邮件管理</A></td>
      <td><A HREF="signout" >注销</A></td>
    </tr>
  </table>