<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<eschool:projectDetails/>
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
                  <table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>
                        <p>&nbsp;</p>
                      </td>
                    </tr>
                    <tr>
                      <td valign="top">
	                  <eschool:projectDetails>
                        <table width="100%"border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="61"><a href="studenttest?projectId=<eschool:projectDetailsAttr attribute="id"/>&type=<eschool:projectDetailsAttr attribute="type"/>"><img src="<%= request.getContextPath() %>/image/info_detail_on.gif" width="61" height="19" border="0"></a></td>
                            <td width="61">&nbsp;</td>
                            <td width="61">&nbsp;</td>
                            <td width="61">&nbsp;</td>
                            <td width="61">&nbsp;</td>
                            <td width="61">&nbsp;</td>
                            <td width="61">&nbsp;</td>
                            <td width="61">&nbsp;</td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td align="right" bgcolor="#006596"><img src="<%= request.getContextPath() %>/image/tab_topright_corner.gif" width="17" height="15"></td>
                    </tr>
                  </table>
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top">
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top">&nbsp;</td>
                          </tr>
                          <tr>
                            <td valign="top" width="60">名称：</td>
                            <td valign="top">
                              <eschool:projectDetailsAttr attribute="name"/>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">时间：</td>
                            <td valign="top">
                              <eschool:projectDetailsAttr attribute="startdate"/>
                              至
                              <eschool:projectDetailsAttr attribute="enddate"/>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">说明：</td>
                            <td valign="top">
                              <eschool:projectDetailsAttr attribute="info"/><br>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">课程：</td>
                            <td valign="top">
                              <eschool:projectDetailsAttr attribute="coursename"/>
                            </td>
                          </tr>
                        </table>
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
                <td background="<%= request.getContextPath() %>/image/content_right_fill.gif" width="17"><img src="/image/spacer.gif" width="17" height="1"></td>
              </tr>
            </table>
	                  </eschool:projectDetails>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16"><img src="<%= request.getContextPath() %>/image/content_downleft_corner.gif" width="16" height="17"></td>
                <td background="<%= request.getContextPath() %>/image/content_down_fill.gif"><img src="/image/spacer.gif" width="1" height="17"></td>
                <td width="17"><img src="<%= request.getContextPath() %>/image/content_downritht_corner.gif" width="17" height="17"></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
