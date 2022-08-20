<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<%@ page import="com.dc.eschool.controller.web.ESchoolWebImpl,com.dc.eschool.util.WebKeys" %>
<%
	String usertype = "teacher";
	ESchoolWebImpl ew = (ESchoolWebImpl)session.getAttribute(WebKeys.ESchoolWebKey);
	if (ew != null && "管理员".equals(ew.getUserType()))
		usertype = "admin";

    String type=request.getParameter("type");
    if (type==null) type="test";
%>
<script language="JavaScript">
  function oncoursechange()
  {
      var coursestr="";
      if(course.value!="")
      {
	  coursestr='&course='+course.value;
      }
      window.document.location.href = 'projectlist?type=<%=type%>' + coursestr;
  }
</script>
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
                  <table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>自习考试 &gt;<eschool:eschoolAttribute attribute="projecttype"/><br>
                      </td>
                    </tr>
                    <tr>
                      <td valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="70"><a href="projectlist?type=test"><img src="<%= request.getContextPath() %>/image/tab_exam_<%="test".equals(type) ? "active" : "normal"%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="projectlist?type=selfstudy"><img src="<%= request.getContextPath() %>/image/tab_selfstudy_<%="selfstudy".equals(type) ? "active" : "normal"%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="projectlist?type=exercise"><img src="<%= request.getContextPath() %>/image/tab_exercise_<%="exercise".equals(type) ? "active" : "normal"%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="projectlist?type=review"><img src="<%= request.getContextPath() %>/image/tab_review_<%="review".equals(type) ? "active" : "normal"%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="projectlist?type=selftest"><img src="<%= request.getContextPath() %>/image/tab_selftest_<%="selftest".equals(type) ? "active" : "normal"%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="projectlist?type=listening"><img src="<%= request.getContextPath() %>/image/tab_listening_<%="listening".equals(type) ? "active" : "normal"%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="projectlist?type=assignment"><img src="<%= request.getContextPath() %>/image/tab_task_<%="assignment".equals(type) ? "active" : "normal"%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70">&nbsp;</td>
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
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td>
                              <select name="course" onchange="javascript: oncoursechange();">

                                <option value="">科目</option>
                                <%
                                	String strSelected = "selected_course_" + request.getParameter("course");
                                %>
                                <eschool:courseSearchList numItems="0" clause='<%=usertype%>'>
                                  <eschool:items>
                                  <option value="<eschool:courseAttribute attribute="courseID"/>" <eschool:courseAttribute attribute='<%=strSelected%>'/>><eschool:courseAttribute attribute="coursename"/></option>
                                  </eschool:items>
                                </eschool:courseSearchList>
                              </select>
                            </td>
                          </tr>
			  <eschool:projectSearchList numItems='20' clause='<%=type%>' course='<%=request.getParameter("course")%>'>
			  <tr>
                            <td align="right">
                              <table border="0" cellspacing="1" cellpadding="2" width="160" align="right">
                                <tr>
                                  <eschool:firstForm action='projectlist'>
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action='projectlist'>
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="projectlist">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="projectlist">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                  </eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">
                              <table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="#000000">
                                <tr bgcolor="#CCCCCC" align="center">
                                  <td>名称</td>
                                  <td>修改时间</td>
                                  <td>科目</td>
                                  <td>说明</td>
                                  <td>状态</td>
                                  <td>开始时间</td>
                                  <td>结束时间</td>
                                  <td>创建人</td>
                                </tr>
                                  <%
				    String urlString="";
                                    if ("test".equals(type))
                                    {
                                        urlString="testmanager";
                                    }else
                                    {
                                        urlString="othermanager";
                                    }
                                  %>

				  <eschool:items>
                                    <tr bgcolor="#f0f0f0">
                                      <td><a href="<%=urlString%>?action=updateproject&projectId=<eschool:projectAttribute attribute="id"/>&type=<eschool:projectAttribute attribute="type"/>"><eschool:projectAttribute attribute="name"/></a></td>
                                      <td><eschool:projectAttribute attribute="modifydate"/></td>
                                      <td><eschool:projectAttribute attribute="coursename"/></td>
                                      <td><eschool:projectAttribute attribute="info"/></td>
                                      <td><eschool:projectAttribute attribute="state"/></td>
                                      <td><eschool:projectAttribute attribute="startdate"/></td>
                                      <td><eschool:projectAttribute attribute="enddate"/></td>
                                      <td><eschool:projectAttribute attribute="creator"/></td>
                                    </tr>
				  </eschool:items>
                              </table>
                            </td>
                          </tr>
                          <tr>
                            <td align="right">
                              <table border="0" cellspacing="1" cellpadding="2" width="160" align="right">
                                <tr>
                                  <eschool:firstForm action='projectlist'>
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action='projectlist'>
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="projectlist">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="projectlist">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                  </eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
			  </eschool:projectSearchList>
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

