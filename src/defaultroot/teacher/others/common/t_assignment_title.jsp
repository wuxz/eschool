<jsp:useBean id="homeworkcontent" class="com.dc.eschool.controller.web.HomeWorkContentSearchWebImpl" scope="session"/>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<%@ page import="com.dc.eschool.util.WebKeys" %>
<%
	String S_URL=(String)session.getAttribute(WebKeys.CurrentScreenKey);
	String S_approvehomework="off";
	String S_approvedupload="off";
	String S_notdeliver="off";
	if(S_URL.equals("APPROVE_HOMEWORK"))
		S_approvehomework="on";
	else if(S_URL.equals("APPROVED_UPLOAD"))
		S_approvedupload="on";
	else
		S_notdeliver="on";

	String projectId=request.getParameter("projectId");
	Integer projectID=new Integer(0);
	if (projectId!=null)
	{
	projectID=new Integer(projectId);
	}
%>

                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td align="right"><a href="otherlist?action=updateproject&projectId=<%=request.getParameter("projectId")%>&type=assignment">返回试题列表</a></td>
                          </tr>
                          <tr>
                            <td>批阅作业</td>
                          </tr>
                        </table>
                        <table width="92%" align="center">
                          <tr>
                            <td>
                            作业名称为<eschool:eschoolAttribute attribute="projectName"/><br>
                            请从以下列表中选择作业批改<br>
                            未批改作业为<%=homeworkcontent.getRestCount(projectID)%>份，
                            未交作业<%=homeworkcontent.unHandInStudent(projectID).size()%>人
                            </td>
                          </tr>
                        </table>
                  <table width="478" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="61"><a href="<%= request.getContextPath() %>/main/approvehomework?action=<%=request.getParameter("action")%>&projectId=<%=request.getParameter("projectId")%>&type=assignment"><img src="<%= request.getContextPath() %>/image/work_table_<%=S_approvehomework%>.gif" width="61" height="19" border="0"></a></td>
                            <td width="61"><a href="<%= request.getContextPath() %>/main/approvedupload?action=<%=request.getParameter("action")%>&projectId=<%=request.getParameter("projectId")%>&type=assignment"><img src="<%= request.getContextPath() %>/image/fazuoye_<%=S_approvedupload%>.gif" width="61" height="19" border="0"></a></td>
                            <td width="61"><a href="<%= request.getContextPath() %>/main/notdeliver?action=<%=request.getParameter("action")%>&projectId=<%=request.getParameter("projectId")%>&type=assignment"><img src="<%= request.getContextPath() %>/image/weijiao_<%=S_notdeliver%>.gif" width="61" height="19" border="0"></a></td>
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