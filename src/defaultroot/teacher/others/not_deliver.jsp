<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<jsp:useBean id="homeworkcontent" class="com.dc.eschool.controller.web.HomeWorkContentSearchWebImpl" scope="session"/>
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
                  自习考试>作业><eschool:eschoolAttribute attribute="coursename"/>
                  <br>
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top">
                        <eschool:insert parameter="HtmlTopTitle" />
                        未交作业学生名单
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <%
                          String projectId=request.getParameter("projectId");
                          Integer projectID=new Integer(0);
                          if (projectId!=null)
                          {
                            projectID=new Integer(projectId);
                          }
                          Vector undeliver=homeworkcontent.unHandInStudent(projectID);
                          for(int i=0;i<undeliver.size();i=i+4)
                          {
                            out.println("<tr bgcolor=\"#CCCCCC\">");
                              out.println("<td>");
                              Vector V_student=(Vector)undeliver.elementAt(i);
                              //out.println(V_student.elementAt(0));
                              out.println(V_student.elementAt(1));
                              out.println("</td>");

                              out.println("<td>");
                              if(i+1<undeliver.size())
                              {
                                V_student=(Vector)undeliver.elementAt(i+1);
                                //out.println(V_student.elementAt(0));
                                out.println(V_student.elementAt(1));
                              }
                              out.println("</td>");

                              out.println("<td>");
                              if(i+2<undeliver.size())
                              {
                                V_student=(Vector)undeliver.elementAt(i+2);
                                //out.println(V_student.elementAt(0));
                                out.println(V_student.elementAt(1));
                              }
                              out.println("</td>");

                              out.println("<td>");
                              if(i+3<undeliver.size())
                              {
                                V_student=(Vector)undeliver.elementAt(i+3);
                                //out.println(V_student.elementAt(0));
                                out.println(V_student.elementAt(1));
                              }
                              out.println("</td>");
                            out.println("</tr>");
                          }
                          %>
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
