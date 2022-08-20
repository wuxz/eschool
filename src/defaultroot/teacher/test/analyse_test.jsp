<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
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
                      <td bgcolor="#E3EDEC" valign="top">
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td align="right"><a href="<%= request.getContextPath() %>/main/testlist?action=<%=request.getParameter("action")%>&projectId=<%=request.getParameter("projectId")%>&type=<%=request.getParameter("type")%>">返回试题列表</a></td>
                          </tr>
                          <tr>
                            <td>试题名称：<eschool:eschoolAttribute attribute="projectname"/></td>
                            <td>
                              <p align="right"><a href="<%= request.getContextPath() %>/teacher/test/analyse_test_print.jsp?<%=request.getQueryString()%>" target=_blank><img border="0" src="<%= request.getContextPath() %>/image/print.gif" width="21" height="21"></a> 
                              <a href="analysetest?action=<%=request.getParameter("action")%>&type=<%=request.getParameter("type")%>&contentID=<%=request.getParameter("contentID")%>&projectId=<%=request.getParameter("projectId")%>"><img border="0" src="<%= request.getContextPath() %>/image/ascending.gif" width="21" height="21"></a>
                              <a href="analysetest?action=<%=request.getParameter("action")%>&type=<%=request.getParameter("type")%>&contentID=<%=request.getParameter("contentID")%>&projectId=<%=request.getParameter("projectId")%>&order=desc"><img border="0" src="<%= request.getContextPath() %>/image/descending.gif" width="21" height="21"></a/></p> 
                            </td> 
                          </tr>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>题号</td>
                            <td>答对数</td>
                            <td>答错数</td>
                            <td>正确率</td>
                          </tr>
                          <eschool:scorestatisticSearchList numItems='0' clause='<%=request.getParameter("projectId")%>' value='<%=request.getParameter("order")%>'>
                          	<eschool:items>
                          <tr>
                            <td bgcolor="#FFFFFF"><eschool:scorestatisticAttribute attribute="itemNumber"/></td>
                            <td bgcolor="#FFFFFF"><eschool:scorestatisticAttribute attribute="rightAnswer"/></td>
                            <td bgcolor="#FFFFFF"><eschool:scorestatisticAttribute attribute="wrongAnswer"/></td>
                            <td bgcolor="#FFFFFF"><eschool:scorestatisticAttribute attribute="statistic"/>%</td>
                          </tr>
                          	</eschool:items>
                          </eschool:scorestatisticSearchList>
                        </table>
                        <p>&nbsp;</p></td>
                      </td>
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
