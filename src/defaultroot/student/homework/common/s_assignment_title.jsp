                <%@ page import="com.dc.eschool.util.WebKeys" %>
                  <%
                    String S_URL=(String)session.getAttribute(WebKeys.CurrentScreenKey);
                    String S_detail="off";
                    String S_subject="off";
                    String S_put="off";
                    String S_uploaded = "off";
                    if(S_URL.equals("HOMEWORK_LIST_STUDENT")){
                      S_subject="on";
                    }else if(S_URL.equals("HOMEWORK_UPLOAD_STUDENT")){
                      S_put="on";
                    }else if(S_URL.equals("UPLOADED_HOMEWORK")){
                      S_uploaded="on";
                    }else{
                      S_detail="on";
                    }

                    System.out.println("s_url :" + S_URL);
                  %>
                  <table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>
                        <p>自学考试 &gt; 作业 &gt; <eschool:eschoolAttribute attribute="coursename"/></p>
                        <p>&nbsp;</p>
                      </td>
                    </tr>
                    <tr>
                      <td valign="top">
                        <table width="100%"border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="61"><a href="<%= request.getContextPath() %>/main/studenthomework?projectId=<%=request.getParameter("projectId")%>&type=assignment"><img src="<%= request.getContextPath() %>/image/info_detail_<%=S_detail%>.gif" width="61" height="19" border="0"></a></td>
                            <td width="61"><a href="<%= request.getContextPath() %>/main/studenthomeworklist?projectId=<%=request.getParameter("projectId")%>&type=assignment"><img src="<%= request.getContextPath() %>/image/work_table_<%=S_subject%>.gif" width="61" height="19" border="0"></a></td>
                            <td width="61"><a href="<%= request.getContextPath() %>/main/studenthomeworkupload?projectId=<%=request.getParameter("projectId")%>&type=assignment"><img src="<%= request.getContextPath() %>/image/give_work_<%=S_put%>.gif" width="61" height="19" border="0"></a></td>
                            <td width="61"><a href="<%= request.getContextPath() %>/main/uploadedhomework?projectId=<%=request.getParameter("projectId")%>&type=assignment"><img src="<%= request.getContextPath() %>/image/uploaded_work_<%=S_uploaded%>.gif" width="61" height="19" border="0"></a></td>
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
                      <td align="right" bgcolor="#006596"><img src="/image/tab_topright_corner.gif" width="17" height="15"></td>
                    </tr>
                  </table>