<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<SCRIPT LANGUAGE=javascript>
    <!--

    function ondownloadclick()
    {
    	window.contentlist.imagename.value="downloadproject";
    }

    function onradioclick(url1, url2, url3, url4)
    {
    	window.contentlist.ischecked.value = "yes";
	window.contentlist.url.value = url1;
    	window.contentlist.projectcontentid.value = url2;
    	window.contentlist.state.value = url3;
        window.contentlist.contentid.value = url4;
    }

    function dosubmit()
    {
      if(window.contentlist.imagename.value=="approveproject")
      {
	window.contentlist.action="approvehomework?action=updatehomework&type=<%=request.getParameter("type")%>&projectId=<%=request.getParameter("projectId")%>";
        return true;
      }

      if(window.contentlist.ischecked.value=="")
      {
        alert("请先选择题！");
	return false;
      }

      if(window.contentlist.imagename.value=="downloadproject")
      {
        window.open(window.contentlist.url.value);
	return false;
      }else if(window.contentlist.imagename.value=="editproject")
      {
	window.open("<%= request.getContextPath() %>/teacher/common/test_property.jsp?contentID="+window.contentlist.contentid.value,"修改说明","height=300,width=400,left=300,top=150,resizable=yes,scrollbars=yes");
	return false;
      }else if(window.contentlist.imagename.value=="deleteproject")
      {
	window.contentlist.action="deleteothercontent?action=deletecontent&type=<%=request.getParameter("type")%>&contentID=" +window.contentlist.contentid.value+"&projectId=<%=request.getParameter("projectId")%>&projectContentID="+window.contentlist.projectcontentid.value;
      }
    }

    function oneditclick()
    {
    	window.contentlist.imagename.value="editproject";
    }

    function ondelclick()
    {
    	window.contentlist.imagename.value="deleteproject";
    }

    function onapproveclick()
    {
    	window.contentlist.imagename.value="approveproject";
    }

    function changeinfo(valueStr)
    {
          win=window.open("<%= request.getContextPath() %>/teacher/common/test_property.jsp?contentID="+valueStr,"修改说明","height=300,width=400,left=300,top=150,resizable=yes,scrollbars=yes");
    }

       //-->
</SCRIPT>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td width="160">
      <eschool:insert parameter="HtmlNavigator" />
    </td>
    <td>
      <table width="600" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16"><img src="<%= request.getContextPath() %>/image/content_topleft_corner.gif" width="16" height="17"></td>
                <td background="<%= request.getContextPath() %>/image/content_top_fill.gif" valign="top"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="16"></td>
                <td width="17"><img src="<%= request.getContextPath() %>/image/content_topright_corner.gif" width="17" height="17"></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td background="<%= request.getContextPath() %>/image/content_left_fill.gif" width="16"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="16" height="1"></td>
                <td bgcolor="#FFFFFF" valign="top">
                  <eschool:insert parameter="HtmlTopTitle" />
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top"> <br>
                        作业列表<br>
                        <br>
			<form action="" name="contentlist" ONSUBMIT="return dosubmit();" method="post">
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>&nbsp;</td>
                            <td>名称</td>
                            <td>创建时间</td>
                            <td>状态</td>
                            <td>说明</td>
                            <td>创建人</td>
                          </tr>
                          <eschool:contentSearchList numItems="0"  value='teacher' clause='<%=request.getParameter("projectId")%>'>
                            <eschool:items>
                              <tr>
                                <td bgcolor="#FFFFFF">
                                  <input type="radio" name="contentID" value="<eschool:contentAttribute attribute='id'/>" onclick="javascript:onradioclick('<%= request.getContextPath() %>/download?filename=<eschool:contentAttribute attribute="docurl"/>&filecode=<eschool:contentAttribute attribute="hashcode"/>&savename=<eschool:contentAttribute attribute="passedsavename"/>', '<eschool:contentAttribute attribute='projectcontentid'/>',  '<eschool:contentAttribute attribute='state'/>','<eschool:contentAttribute attribute='id'/>')">
                                </td>
                                <td bgcolor="#FFFFFF"><a href="<%= request.getContextPath() %>/download?filename=<eschool:contentAttribute attribute="docurl"/>&filecode=<eschool:contentAttribute attribute="hashcode"/>&savename=<eschool:contentAttribute attribute="passedsavename"/>"><eschool:contentAttribute attribute='name'/></a></td>
                                <td bgcolor="#FFFFFF"><eschool:contentAttribute attribute='createdate'/></td>
                                <td bgcolor="#FFFFFF"><eschool:contentAttribute attribute='state'/></td>
                                <td bgcolor="#FFFFFF"><a href="javascript:changeinfo(<eschool:contentAttribute attribute='id'/>)"><eschool:contentAttribute attribute='info'/></a></td>
                                <td bgcolor="#FFFFFF"><eschool:contentAttribute attribute='creatorname'/></td>
                              </tr>
                            </eschool:items>
                          </eschool:contentSearchList>
                        </table>
                        <table width="400" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td>
				<input type="hidden" name="url" value="">
			        <input type="hidden" name="state" value="">
			        <input type="hidden" name="projectcontentid" value="">
				<input type="hidden" name="imagename" value="">
				<input type="hidden" name="ischecked" value="">
                                <input type="hidden" name="contentid" value="">
			    <input type="image" name="submit" src="<%= request.getContextPath() %>/image/download_3.gif" width="68" height="22" onclick="javascript: ondownloadclick()"></td>
                            <td><input type="image" name="submit" src="<%= request.getContextPath() %>/image/btn_edit.gif" width="87" height="35" onclick="javascript: oneditclick()"></td>
                            <td><input type="image" name="submit" src="<%= request.getContextPath() %>/image/btn_del.gif" width="87" height="35" onclick="javascript: ondelclick()"></td>
			    <%
				    if ("assignment".equals(request.getParameter("type")))
				{
			    %>
                            <td><input type="image" name="submit" src="<%= request.getContextPath() %>/image/read_3.gif" width="68" height="22" onclick="javascript: onapproveclick()"></td>
			    <%
				    }
			    %>
                          </tr>
                        </table>
			</form>
                      </td>
                      <td background="<%= request.getContextPath() %>/image/line_table_right.gif" width="22">&nbsp;</td>
                    </tr>
                    <tr>
                      <td><img src="<%= request.getContextPath() %>/image/tab_downleft_corner.gif" width="22" height="19"></td>
                      <td background="<%= request.getContextPath() %>/image/line_table_down.gif">&nbsp;</td>
                      <td><img src="<%= request.getContextPath() %>/image/tab_downright_corner.gif" width="22" height="19"></td>
                    </tr>
                  </table>
                  </td>
                <td background="<%= request.getContextPath() %>/image/content_right_fill.gif" width="17"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="17" height="1"></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16"><img src="<%= request.getContextPath() %>/image/content_downleft_corner.gif" width="16" height="17"></td>
                <td background="<%= request.getContextPath() %>/image/content_down_fill.gif"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="17"></td>
                <td width="17"><img src="<%= request.getContextPath() %>/image/content_downritht_corner.gif" width="17" height="17"></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>