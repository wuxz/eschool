                  <%
                    String S_URL=request.getRequestURI().toString();
                    String S_detail="off";
                    String S_subject="off";
                    String S_put="off";
                    if(S_URL.equals("/student/homework/assignment_list.jsp")){
                      S_subject="on";
                    }else if(S_URL.equals("/student/homework/upload_assignment.jsp")){
                      S_put="on";
                    }else{
                      S_detail="on";
                    }
                  %>
                  <table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>
                        <p>自学考试 &gt; 作业 &gt; 一年级数学</p>
                        <p>&nbsp;</p>
                      </td>
                    </tr>
                    <tr>
                      <td valign="top">
                        <table width="100%"border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="61"><a href="/student/homework/assignment_detail.jsp"><img src="/image/info_detail_<%=S_detail%>.gif" width="61" height="19" border="0"></a></td>
                            <td width="61"><a href="/student/homework/assignment_list.jsp"><img src="/image/work_table_<%=S_subject%>.gif" width="61" height="19" border="0"></a></td>
                            <td width="61"><a href="/student/homework/upload_assignment.jsp"><img src="/image/give_work_<%=S_put%>.gif" width="61" height="19" border="0"></a></td>
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