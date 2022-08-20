<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<table width="779" border="0" cellspacing="1" cellpadding="1" bgcolor="#FFFFFF">
  <tr valign="bottom">
    <td width="130" align="center" height="80"><img src="<%= request.getContextPath() %>/image/logo.gif" width="112" height="76"></td>
    <td width="640"><img src="<%= request.getContextPath() %>/image/AD.gif" width="632" height="65"></td>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="779" border="0" cellspacing="0" cellpadding="0" bgcolor="#409552">
  <tr valign="top">
    <td width="51" bgcolor="#32009f"><img src="<%= request.getContextPath() %>/image/navigator_leftcorner.gif" width="51" height="41"></td>
    <td valign="middle" align="center" width="10">&nbsp;</td>
    <td valign="middle" align="center" width="120"><a href="studentinfo" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image34','','<%= request.getContextPath() %>/image/haead_personalnfo_active.gif',1)"><img name="Image34" border="0" src="<%= request.getContextPath() %>/image/haead_personalnfo_normal.gif" width="109" height="41"></a></td>
    <td valign="middle" align="center" width="120"><a href="studentprojectlist?type=test" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image35','','<%= request.getContextPath() %>/image/haead_studyself_active.gif',1)"><img name="Image35" border="0" src="<%= request.getContextPath() %>/image/haead_studyself_normal.gif" width="109" height="41"></a></td>
    <td valign="middle" align="center" width="87"><a href="<%= request.getContextPath() %>/bbs/index.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image36','','<%= request.getContextPath() %>/image/haead_BBS_active.gif',1)"><img name="Image36" border="0" src="<%= request.getContextPath() %>/image/haead_BBS_normal.gif" width="76" height="41"></a></td>
    <td valign="middle" align="center" width="104"><a href="chatroom" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image37','','<%= request.getContextPath() %>/image/haead_chat_active.gif',1)"><img name="Image37" border="0" src="<%= request.getContextPath() %>/image/haead_chat_normal.gif" width="93" height="41"></a></td>
    <td><a href="<eschool:eschoolAttribute attribute="mailpath"/>/webmail" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image38','','<%= request.getContextPath() %>/image/haead_mail_active.gif',1)"><img name="Image38" border="0" src="<%= request.getContextPath() %>/image/haead_mail_normal.gif" width="80" height="41"></a></td>
  </tr>
</table>
<table width="779" border="0" cellspacing="0" cellpadding="0" bgcolor="#32009f">
  <tr>
    <td width="51"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="5"></td>
    <td background="<%= request.getContextPath() %>/image/navigator_shadow.gif" valign="top" height="5"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="5"></td>
  </tr>
</table>