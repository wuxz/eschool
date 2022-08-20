                <%@ page import="com.dc.eschool.util.WebKeys" %>
                <%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
		  <%
            String projectId=request.getParameter("projectId");
            String type=request.getParameter("type");
            String p_action="";
            String u_action="";
            String p_url="";
            String u_url="";
            if(projectId!=null)
            {
                u_url="action=createcontent&projectId="+projectId+"&type="+type;
                p_url="action=updateproject&projectId="+projectId+"&type="+type;
            }else
            {
                p_url="action=createproject&type="+type;
            }
            String S_detailURL="testmanager?"+p_url;
		    String S_listURL="testlist?"+p_url;
		    String S_uploadURL="contentmanager?"+u_url;
		    String S_selectURL="selectproject?"+p_url;
		    String S_approveURL="judgepaper?"+p_url;
		    String action=request.getParameter("action");
		    if(action==null) action="createproject";
		    if(action.equals("createproject"))
		    {
		      S_listURL=S_detailURL;
		      S_uploadURL=S_detailURL;
		      S_selectURL=S_detailURL;
		      S_approveURL=S_detailURL;
		    }
		    String S_URL=session.getAttribute(WebKeys.CurrentScreenKey).toString();
                    String S_detail="normal";
                    String S_subject="normal";
                    String S_put="normal";
                    String S_choice="normal";
                    String S_check="normal";
                    if(S_URL.equals("TEST_LIST")){
                      S_subject="active";
                    }else if(S_URL.equals("CONTENT_MANAGER")){
                      S_put="active";
                    }else if(S_URL.equals("SELECT_PROJECT")){
                      S_choice="active";
                    }else if(S_URL.equals("JUDGEPAPER")){
                      S_check="active";
                    }else{
                      S_detail="active";
                    }
                  %>
                  <table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>
                        <p><eschool:eschoolAttribute attribute="projecttype"/> &gt;<eschool:eschoolAttribute attribute="coursename"/>  &gt; <eschool:eschoolAttribute attribute="projectName"/> </p>
                        <p>&nbsp;</p>
                      </td>
                    </tr>
                    <tr>
                      <td valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="70"><a href="<%=S_detailURL%>"><img src="<%= request.getContextPath() %>/image/tab_detailinfo_<%=S_detail%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="<%=S_listURL%>"><img src="<%= request.getContextPath() %>/image/tab_subjectlist_<%=S_subject%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="<%=S_uploadURL%>"><img src="<%= request.getContextPath() %>/image/tab_put_<%=S_put%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="<%=S_selectURL%>"><img src="<%= request.getContextPath() %>/image/tab_choice_<%=S_choice%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70"><a href="<%=S_approveURL%>"><img src="<%= request.getContextPath() %>/image/tab_checksubject_<%=S_check%>.gif" width="70" height="19" border="0"></a></td>
                            <td width="70">&nbsp;</td>
                            <td width="70">&nbsp;</td>
                            <td width="70">&nbsp;</td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                    <tr>
                      <td align="right" bgcolor="#006596"><img src="<%= request.getContextPath() %>/image/tab_topright_corner.gif" width="17" height="15"></td>
                    </tr>
                  </table>