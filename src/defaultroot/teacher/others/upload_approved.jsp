<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
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
                自习考试>作业><eschool:eschoolAttribute attribute="coursename"/>
                  <br>
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top">
                        <eschool:insert parameter="HtmlTopTitle" />
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td>
                              <br>
                              <form name="form1" ONSUBMIT="return dosubmit()" method="post" Action="validatehomework?action=updatehomework&projectId=<%=request.getParameter("projectId")%>&type=assignment" ENCTYPE="multipart/form-data" target="_self">
                                选择文件<br>
                                <input type="file" name="homework">
                                <br>
                                <input type="image" src="<%= request.getContextPath() %>/image/button_upload.gif" width="76" height="28" name="submit"><br>
                                <br>
                              </form>
                              以下为您已经下发的资料<br>
                            </td>
                          </tr>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#f0f0f0">
                            <td>文件名</td>
                            <td>大小</td>
                          </tr>
                          <eschool:hwContentSearchList numItems="0" clause='session'>
                            <eschool:items>
                              <tr>
                                <td bgcolor="#FFFFFF"><eschool:hwContentAttribute attribute="docURL"/></td>
                                <td bgcolor="#FFFFFF"><eschool:hwContentAttribute attribute="size"/></td>
                              </tr>
                            </eschool:items>
                          </eschool:hwContentSearchList>
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
