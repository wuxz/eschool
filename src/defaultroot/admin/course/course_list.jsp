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
            <form action="course?method=search" method="post">
            <table border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
                <td align="center">关键字：<input type="text" name="value">&nbsp;
                <input type="image" src="<%= request.getContextPath() %>/image/search_2.gif" border="0" height="22" width="68" name="submit"></td>
              </tr>
            </table>
            </form>
            <br><br>课程列表&nbsp;&nbsp;
            <a href="coursemanager?action=createcourse">添加新课程</a><br><br>
            <eschool:courseSearchList numItems="10" clause='<%=request.getParameter("method")%>' value='<%=request.getParameter("value")%>'>
            <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
              <tr>
                <td height="28">
                  <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                    <tr>
                      <eschool:firstForm action='course'>
                      <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                      </eschool:firstForm>

                      <eschool:prevForm action="course">
                      <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                      </eschool:prevForm>
                      <td align="center"><eschool:pageForm/></td>

                      <eschool:nextForm action="course">
                      <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                      </eschool:nextForm>

                      <eschool:lastForm action="course">
                      <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                      </eschool:lastForm>

                    </tr>
                  </table>
                </td>
              </tr>
            </table>
            <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
              <tr bgcolor="#CCCCCC">
                              <td>课程名</td>
                              <td>开始时间</td>
                              <td>结束时间</td>
                              <td>说明</td>
                              <td>状态</td>
                              <td>教师</td>
                              <td>操作</td>
                            </tr>
                            <eschool:items>
                              <tr>
                                <td bgcolor="#FFFFFF"><a href="coursemanager?action=updatecourse&courseId=<eschool:courseAttribute attribute="courseID"/>"><eschool:courseAttribute attribute="courseName"/></a></td>
                                <td bgcolor="#FFFFFF"><eschool:courseAttribute attribute="startDate"/></td>
                                <td bgcolor="#FFFFFF"><eschool:courseAttribute attribute="endDate"/></td>
                                <td bgcolor="#FFFFFF"><eschool:courseAttribute attribute="info"/></td>
                                <td bgcolor="#FFFFFF"><eschool:courseAttribute attribute="state"/></td>
                                <td bgcolor="#FFFFFF"><eschool:courseAttribute attribute="teacherName"/></td>
                                <td bgcolor="#FFFFFF"><a href="validatenewcourse?action=deletecourse&courseId=<eschool:courseAttribute attribute="courseID"/>">删除</a></td>
                              </tr>
                            </eschool:items>
                          </table>
                          <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                            <tr>
                              <td height="28">
                                <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                  <tr>
                                    <eschool:firstForm action="course">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                    </eschool:firstForm>

                                    <eschool:prevForm action="course">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                    </eschool:prevForm>
                                    <td align="center"><eschool:pageForm/></td>

                                    <eschool:nextForm action="course">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                    </eschool:nextForm>

                                    <eschool:lastForm action="course">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                    </eschool:lastForm>
                                  </tr>
                                </table>
                              </td>
                            </tr>
                          </table>
                        </eschool:courseSearchList>
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
