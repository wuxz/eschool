<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
			  <%
			  String type=request.getParameter("type");
			  String s_URL="";
			  if (type==null) type="";
			  if(type.equals("test"))
			  {
			    s_URL="validatecontent";
			  }else
			  {
			    s_URL="validateother";
			  }

			  %>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td width="160">
      <eschool:insert parameter="HtmlNavigator" />
      <br>
      <p>&nbsp;</p>
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
                      <FORM ONSUBMIT="return dosubmit()" ENCTYPE="multipart/form-data" action="<%=s_URL%>?action=createcontent&projectId=<%=request.getParameter("projectId")%>&type=<%=request.getParameter("type")%>&contentID=<%=request.getParameter("contentID")%>" method="post" name="form1" target="_self">
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td>资料上传<br>
                              <br>
                              名称<br>
                              <input type="text" name="contentname">
                              <br>
                              选择文件<br>
                              <input type="file" name="filename">
                              <br>
                              <br>
                              <input type=hidden name=action_type value=creat_content>
                              <input type=image src="<%= request.getContextPath() %>/image/button_upload.gif" width="76" height="28" name=upload><br>
                              <br>
                              以下为您已经上传至服务器的资料<br>
                            </td>
                          </tr>
                        </table>

                       </form>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#f0f0f0">
                            <td width="20%">操作</td>
                            <td>文件名</td>
                            <td width="20%">大小</td>
                          </tr>
                          <eschool:contentSearchList numItems="0" clause='session' method='upload'>
                            <eschool:items>
                              <tr>
                                <td bgcolor="#FFFFFF" width="20%"><a href="<%=s_URL%>?action=deletecontent&projectId=<%=request.getParameter("projectId")%>&type=<%=request.getParameter("type")%>&contentID=<eschool:contentAttribute attribute='id'/>&projectContentID=<eschool:contentAttribute attribute='projectcontentid'/>">删除</a></td>
                                <td bgcolor="#FFFFFF"><eschool:contentAttribute attribute='name'/></td>
                                <td bgcolor="#FFFFFF" width="20%"><eschool:contentAttribute attribute='filesize'/></td>
                              </tr>
                            </eschool:items>
                          </eschool:contentSearchList>
                        </table>
                        <p>&nbsp;</p></td>
                      <td background="<%= request.getContextPath() %>/image/line_table_right.gif" width="22">&nbsp;</td>
                    </tr>
                    <tr>
                      <td><img src="<%= request.getContextPath() %>/image/tab_downleft_corner.gif" width="22" height="19"></td>
                      <td background="<%= request.getContextPath() %>/image/line_table_down.gif">&nbsp;</td>
                      <td><img src="<%= request.getContextPath() %>/image/tab_downright_corner.gif" width="22" height="19"></td>
                    </tr>
                  </table>
                  <p>&nbsp;</p>
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

