                  <%
                    String S_URL=request.getRequestURI().toString();
                    String S_detail="normal";
                    String S_subject="normal";
                    String S_put="normal";
                    String S_choice="normal";
                    String S_check="normal";
                    if(S_URL.equals("/teacher/test/question_list.jsp")){
                      S_subject="active";
                    }else if(S_URL.equals("/teacher/common/data_upload.jsp")){
                      S_put="active";
                    }else if(S_URL.equals("/teacher/common/select_data.jsp")){
                      S_choice="active";
                    }else if(S_URL.equals("/teacher/test/judge_paper.jsp")){
                      S_check="active";
                    }else{
                      S_detail="active";
                    }
                  %>
                  <table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>
                        <p>自学考试 &gt; 考试 &gt; 一年级语文</p>
                        <p>&nbsp;</p>
                      </td>
                    </tr>
                    <tr>
                      <td valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="70"><a href="/teacher/test/t_test_detail.jsp"><img src="/image/tab_detailinfo_<%=S_detail%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="/teacher/test/question_list.jsp"><img src="/image/tab_subjectlist_<%=S_subject%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="/teacher/common/data_upload.jsp"><img src="/image/tab_put_<%=S_put%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="/teacher/common/select_data.jsp"><img src="/image/tab_choice_<%=S_choice%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="/teacher/test/judge_paper.jsp"><img src="/image/tab_checksubject_<%=S_check%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70">&nbsp;</td>
                            <td width="70">&nbsp;</td>
                            <td width="70">&nbsp;</td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td align="right" bgcolor="#006596"><img src="/image/tab_topright_corner.gif" width="17" height="15"></td>
                    </tr>
                  </table>