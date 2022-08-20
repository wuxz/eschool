<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<br>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td>
    <table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
        <td width="16"><img src="<%= request.getContextPath() %>/image/content_topleft_corner.gif" width="16" height="17"></td>
        <td background="<%= request.getContextPath() %>/image/content_top_fill.gif" valign="top"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="16"></td>
        <td width="17"><img src="<%= request.getContextPath() %>/image/content_topright_corner.gif" width="17" height="17"></td>
      </tr>
    </table>

    <table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
        <td background="<%= request.getContextPath() %>/image/content_left_fill.gif" width="16"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="16" height="1"></td>
        <td bgcolor="#FFFFFF" valign="top">
        <br>
        <a href="coursemanager?<%=request.getQueryString()%>">课程信息</a>
        <a href="coursestudent?<%=request.getQueryString()%>">学生列表</a>
        <br>
        <form action="coursestudent?method=search&<%=request.getQueryString()%>" method="post">
        <table border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td align="center">关键字：<input type="text" name="value">&nbsp;<input type="image" src="<%= request.getContextPath() %>/image/search_2.gif" border="0" height="22" width="68" name="submit"></td>
          </tr>
        </table>
        </form>
        <br>从以下用户列表中选择学生<br>
          <eschool:usersSearchList numItems="10" clause='<%=request.getParameter("method")%>' value='<%=request.getParameter("value")%>'>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
                                  <eschool:firstForm action='coursestudent'>
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                  </eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>用户名</td>
                            <td>姓名</td>
                            <td>性别</td>
                            <td>生日</td>
                            <td>电话</td>
                            <td>e-mail</td>
                            <td>身份</td>
                            <td>组别</td>
                            <td>状态</td>
                            <td>操作</td>
                          </tr>

                            <eschool:items>
                              <tr>
                                <td bgcolor="#FFFFFF"><a href="usermanager?action=updateUser&userId=<eschool:usersAttribute attribute="userID"/>"><eschool:usersAttribute attribute="loginName"/></a></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="name"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="gender"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="birthday"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="tel"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="email"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="userType"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="className"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="state"/></td>
                                <td bgcolor="#FFFFFF"><a href="selectstudent?action=createcoursestudent&student=<eschool:usersAttribute attribute="userID"/>&courseId=<%=request.getParameter("courseId")%>">选择</a></td>
                              </tr>
                            </eschool:items>

                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
                                  <eschool:firstForm action='coursestudent'>
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                  </eschool:lastForm>                                  </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </eschool:usersSearchList>
                    <br>在本课程中已加入的学生<br>
                    <eschool:usersSearchList numItems="10" clause='course' value='<%=request.getParameter("courseId")%>'>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
                                  <eschool:firstForm action='coursestudent'>
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                  </eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>用户名</td>
                            <td>姓名</td>
                            <td>性别</td>
                            <td>生日</td>
                            <td>电话</td>
                            <td>e-mail</td>
                            <td>身份</td>
                            <td>组别</td>
                            <td>状态</td>
                            <td>操作</td>
                          </tr>

                            <eschool:items>
                              <tr>
                                <td bgcolor="#FFFFFF"><a href="usermanager?action=updateUser&userId=<eschool:usersAttribute attribute="userID"/>"><eschool:usersAttribute attribute="loginName"/></a></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="name"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="gender"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="birthday"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="tel"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="email"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="userType"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="className"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="state"/></td>
                                <td bgcolor="#FFFFFF"><a href="selectstudent?action=deletecoursestudent&student=<eschool:usersAttribute attribute="userID"/>&courseId=<%=request.getParameter("courseId")%>">删除</a></td>
                              </tr>
                            </eschool:items>

                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
                                  <eschool:firstForm action='coursestudent'>
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="coursestudent">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                  </eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </eschool:usersSearchList>
                  </td>
                <td background="<%= request.getContextPath() %>/image/content_right_fill.gif" width="17"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="17" height="1"></td>
              </tr>
            </table>
      <table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td width="16"><img src="<%= request.getContextPath() %>/image/content_downleft_corner.gif" width="16" height="17"></td>
          <td background="<%= request.getContextPath() %>/image/content_down_fill.gif"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="17"></td>
          <td width="17"><img src="<%= request.getContextPath() %>/image/content_downritht_corner.gif" width="17" height="17"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
