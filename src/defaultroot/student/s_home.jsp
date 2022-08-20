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
                  <table width="96%" border="0" cellspacing="2" cellpadding="8" align="center">

                    <tr valign="top">
                      <td>
                        <table width="272" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
                          <tr>
                            <td valign="bottom">课程</td>
                          </tr>
                          <tr>
                            <td valign="bottom"><img src="<%= request.getContextPath() %>/image/content_line_1.gif" width="272" height="8"></td>
                          </tr>
                          <tr>
                            <td valign="top">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td valign="top">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="3">
                                      <tr bgcolor="#d8dfe5">
                                        <td  width="33%">课程名称</td>
                                        <td  width="40%">时间</td>
                                        <td  width="27%">说明</td>
                                      </tr>
                                      <eschool:courseSearchList numItems="5" clause="student">
                                        <eschool:items>
                                          <tr bgcolor="#efefef">
                                            <td><eschool:courseAttribute attribute="courseName"/></a></td>
                                            <td><eschool:courseAttribute attribute="startDate"/></td>
                                            <td><eschool:courseAttribute attribute="info"/></td>
                                          </tr>
                                        </eschool:items>
                                      </eschool:courseSearchList>
                                      <tr bgcolor="#efefef">
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><a href="scourseinfo?method=student">更多&nbsp;&gt;&gt;</a></td>
                                      </tr>
                                    </table>
                                  </td>
                                  <td align="right" width="4" valign="top"><img src="<%= request.getContextPath() %>/image/content_dot_1.gif" width="4" height="22"></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </td>
                      <td>
                        <table width="272" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
                          <tr>
                            <td valign="bottom">考试</td>
                          </tr>
                          <tr>
                            <td valign="bottom"><img src="<%= request.getContextPath() %>/image/content_line_2.gif" width="272" height="10"></td>
                          </tr>
                          <tr>
                            <td valign="top">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td valign="top">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="3">
                                      <tr bgcolor="#e3e8d4">
                                        <td>考试名称</td>
                                        <td>开始时间</td>
                                        <td>课程</td>
                                      </tr>
		                        <eschool:projectSearchList numItems="5" clause="test" emptyString="">
			                      <eschool:items>
                                            <tr bgcolor="#efefef">
                                              <td><a href="studenttest?projectId=<eschool:projectAttribute attribute="id"/>&type=<eschool:projectAttribute attribute="type"/>"><eschool:projectAttribute attribute="name"/></a></td>
                                              <td><eschool:projectAttribute attribute="startdate"/></td>
                                              <td><eschool:projectAttribute attribute="coursename"/></td>
                                            </tr>
			                      </eschool:items>
		                        </eschool:projectSearchList>
                                      <tr bgcolor="#efefef">
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><a href="studentprojectlist?type=test">更多&nbsp;&gt;&gt;</a></td>
                                      </tr>
                                    </table>
                                  </td>
                                  <td align="right" width="4" valign="top"><img src="<%= request.getContextPath() %>/image/content_dot_2.gif" width="4" height="22"></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr valign="top">
                      <td>
                        <table width="272" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
                          <tr>
                            <td valign="bottom">作业</td>
                          </tr>
                          <tr>
                            <td valign="bottom"><img src="<%= request.getContextPath() %>/image/content_line_3.gif" width="272" height="10"></td>
                          </tr>
                          <tr>
                            <td valign="top">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td valign="top">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="3">
                                      <tr bgcolor="#d9e5e3">
                                        <td width="33%">作业名称</td>
                                        <td width="40%">时间</td>
                                        <td width="27%">课程</td>
                                      </tr>
                                      <eschool:projectSearchList numItems="5" clause="assignment" emptyString="">
                                        <eschool:items>
                                          <tr bgcolor="#efefef">
                                            <td><a href="studenthomework?projectId=<eschool:projectAttribute attribute="id"/>&type=<eschool:projectAttribute attribute="type"/>"><eschool:projectAttribute attribute="name"/></a></td>
                                            <td><eschool:projectAttribute attribute="startdate"/></td>
                                            <td><eschool:projectAttribute attribute="coursename"/></td>
                                          </tr>
                                        </eschool:items>
                                      </eschool:projectSearchList>
                                      <tr bgcolor="#efefef">
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><a href="studentprojectlist?type=assignment">更多&nbsp;&gt;&gt;</a></td>
                                      </tr>
                                    </table>
                                  </td>
                                  <td align="right" width="4" valign="top"><img src="<%= request.getContextPath() %>/image/content_dot_3.gif" width="4" height="22"></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </td>
                      <td>
                        <table width="272" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
                          <tr>
                            <td valign="bottom">听力</td>
                          </tr>
                          <tr>
                            <td valign="bottom"><img src="<%= request.getContextPath() %>/image/content_line_4.gif" width="272" height="8"></td>
                          </tr>
                          <tr>
                            <td valign="top">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td valign="top">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="3">
                                      <tr bgcolor="#E5d8df">
                                        <td width="33%">听力名称</td>
                                        <td width="40%">时间</td>
                                        <td width="27%">课程</td>
                                      </tr>
                                      <eschool:projectSearchList numItems="5" clause="listening" emptyString="">
                                        <eschool:items>
                                        <tr bgcolor="#efefef">
                                          <td><a href="studentother?projectId=<eschool:projectAttribute attribute="id"/>&type=<eschool:projectAttribute attribute="type"/>"><eschool:projectAttribute attribute="name"/></a></td>
                                          <td><eschool:projectAttribute attribute="startdate"/></td>
                                          <td><eschool:projectAttribute attribute="coursename"/></td>
                                        </tr>
			                </eschool:items>
		                      </eschool:projectSearchList>
                                      <tr bgcolor="#efefef">
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><a href="studentprojectlist?type=listening">更多&nbsp;&gt;&gt;</a></td>
                                      </tr>
                                    </table>
                                  </td>
                                  <td align="right" width="4" valign="top"><img src="<%= request.getContextPath() %>/image/content_dot_4.gif" width="4" height="22"></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr valign="top">
                      <td>
                        <table width="272" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
                          <tr>
                            <td valign="bottom">自习</td>
                          </tr>
                          <tr>
                            <td valign="bottom"><img src="<%= request.getContextPath() %>/image/content_line_5.gif" width="272" height="10"></td>
                          </tr>
                          <tr>
                            <td valign="top">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td valign="top">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="3">
                                      <tr bgcolor="#e5ded8">
                                        <td width="33%">自习名称</td>
                                        <td width="40%">时间</td>
                                        <td width="27%">课程</td>
                                      </tr>
                                      <eschool:projectSearchList numItems="5" clause="selfstudy" emptyString="">
			                <eschool:items>
                                          <tr bgcolor="#efefef">
                                            <td><a href="studentother?projectId=<eschool:projectAttribute attribute="id"/>&type=<eschool:projectAttribute attribute="type"/>"><eschool:projectAttribute attribute="name"/></a></td>
                                            <td><eschool:projectAttribute attribute="startdate"/></td>
                                            <td><eschool:projectAttribute attribute="coursename"/></td>
                                          </tr>
			                </eschool:items>
		                      </eschool:projectSearchList>
                                      <tr bgcolor="#efefef">
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><a href="studentprojectlist?type=selfstudy">更多&nbsp;&gt;&gt;</a></td>
                                      </tr>
                                    </table>
                                  </td>
                                  <td align="right" width="4" valign="top"><img src="<%= request.getContextPath() %>/image/content_dot_5.gif" width="4" height="22"></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </td>
                      <td>
                        <table width="272" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
                          <tr>
                            <td valign="bottom">复习</td>
                          </tr>
                          <tr>
                            <td valign="bottom"><img src="<%= request.getContextPath() %>/image/content_line_6.gif" width="272" height="10"></td>
                          </tr>
                          <tr>
                            <td valign="top">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <td valign="top">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="3">
                                      <tr bgcolor="#d9e5d9">
                                        <td width="33%">复习名称</td>
                                        <td width="40%">时间</td>
                                        <td width="27%">课程</td>
                                      </tr>
                                      <eschool:projectSearchList numItems="5" clause="review" emptyString="">
			                <eschool:items>
                                          <tr bgcolor="#efefef">
                                            <td><a href="studentother?projectId=<eschool:projectAttribute attribute="id"/>&type=<eschool:projectAttribute attribute="type"/>"><eschool:projectAttribute attribute="name"/></a></td>
                                            <td><eschool:projectAttribute attribute="startdate"/></td>
                                            <td><eschool:projectAttribute attribute="coursename"/></td>
                                          </tr>
			                </eschool:items>
		                      </eschool:projectSearchList>
                                      <tr bgcolor="#efefef">
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td><a href="projectlist?type=review">更多&nbsp;&gt;&gt;</a></td>
                                      </tr>
                                    </table>
                                  </td>
                                  <td align="right" width="4" valign="top"><img src="<%= request.getContextPath() %>/image/content_dot_6.gif" width="4" height="22"></td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
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