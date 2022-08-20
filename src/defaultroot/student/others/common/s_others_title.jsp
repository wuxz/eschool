                <%@ page import="com.dc.eschool.util.WebKeys" %>
                  <%
                    String S_URL=(String)session.getAttribute(WebKeys.CurrentScreenKey);
                  %>
                  <table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>
                        <p>вто╟©╪йт &gt;<eschool:eschoolAttribute attribute="projecttype"/>  &gt; <eschool:eschoolAttribute attribute="coursename"/></p>
                        <p>&nbsp;</p>
                      </td>
                    </tr>
                    <tr>
                      <td valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="61"><a href="<%= request.getContextPath() %>/main/studentother?projectId=<%=request.getParameter("projectId")%>&type=<%=request.getParameter("type")%>"><img src="<%= request.getContextPath() %>/image/tab_detailinfo_<%="OTHER_STUDENT".equals(S_URL) ? "active" : "normal"%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="61"><a href="<%= request.getContextPath() %>/main/studentotherlist?projectId=<%=request.getParameter("projectId")%>&type=<%=request.getParameter("type")%>"><img src="<%= request.getContextPath() %>/image/tab_subjectlist_<%="OTHER_LIST_STUDENT".equals(S_URL) ? "active" : "normal"%>.gif" width="70" height="19" border="0"></a></td>
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