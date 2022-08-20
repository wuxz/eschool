<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<%@ page import="com.dc.eschool.util.WebKeys" %>
<%
	String url = session.getAttribute(WebKeys.CurrentScreenKey).toString();
	
	String action = "";
	String role = "";
	if(url.equals("LOOKUPSCHOOLRESOURCE_ADMIN"))
	{
		action = "adminschoolresourcelookup";
		role = "admin";
	}
	else if(url.equals("LOOKUPSCHOOLRESOURCE_TEACHER"))
		action = "teacherschoolresourcelookup";
	else
		action = "studentschoolresourcelookup";
%>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td width="160">
    <eschool:insert parameter="HtmlNavigator" />
      <br>
<form action="<%=action%>" method="post" id=fm1 name=fm1>
      <table width="89%" border="0" cellspacing="1" cellpadding="2" align="center">
        <tr>
          <td valign="top"><img src="<%= request.getContextPath() %>/image/title_search_lib.gif" width="120" height="36"></td>
        </tr>
        <tr>
          <td valign="top"><font color="#FFFFFF">关键字<br>
            <input type="text" name="info" size="20">
            </font></td>
        </tr>
        <tr>
          <td valign="top"><font color="#FFFFFF">科目</font><br>
	          <select name="catalog">
	
	            <option value="">科目:</option>
	            <eschool:subjectSearchList numItems="0" clause=''>
	              <eschool:items>
	              <option value="<eschool:subjectAttribute attribute="subjectID"/>"><eschool:subjectAttribute attribute="name"/></option>
	              </eschool:items>
	            </eschool:subjectSearchList>
	          </select>
            </font> </td>
        </tr>
        <tr>
          <td valign="top"><font color="#FFFFFF">时间</font><br>
            <font color="#FFFFFF">
            <input type="text" name="time" size="20">
            </font> </td>
        </tr>
        <tr>
          <td><img src="<%= request.getContextPath() %>/image/button_search.gif" width="61" height="30" onclick="fm1.submit();"></td>
        </tr>
      </table>
      </form>
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
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top"> <br>
                        　　学校资料查询结果<br>
                        <br>
                        <eschool:schoolresourceSearchList numItems="20" keyword='<%=request.getParameter("info")%>' subjectID='<%=request.getParameter("catalog")%>' time='<%=request.getParameter("time")%>' role='<%=role%>'>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
									<eschool:firstForm action='<%=action%>'>
										<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
									    <input type=hidden name=keyword value="<%=request.getParameter("info")%>">
									    <input type=hidden name=catalog value="<%=request.getParameter("catalog")%>">
									    <input type=hidden name=time value="<%=request.getParameter("time")%>">
									</eschool:firstForm>
	
									<eschool:prevForm action='<%=action%>'>
										<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
									    <input type=hidden name=keyword value="<%=request.getParameter("info")%>">
									    <input type=hidden name=catalog value="<%=request.getParameter("catalog")%>">
									    <input type=hidden name=time value="<%=request.getParameter("time")%>">
									</eschool:prevForm>
									<td align="center"><eschool:pageForm/></td>
	
									<eschool:nextForm action='<%=action%>'>
										<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
									    <input type=hidden name=keyword value="<%=request.getParameter("info")%>">
									    <input type=hidden name=catalog value="<%=request.getParameter("catalog")%>">
									    <input type=hidden name=time value="<%=request.getParameter("time")%>">
									</eschool:nextForm>
									
									<eschool:lastForm action='<%=action%>'>
										<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
									    <input type=hidden name=keyword value="<%=request.getParameter("info")%>">
									    <input type=hidden name=catalog value="<%=request.getParameter("catalog")%>">
									    <input type=hidden name=time value="<%=request.getParameter("time")%>">
									</eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>名称</td>
                            <td>链接</td>
                            <td>创建人</td>
                            <td>修改时间</td>
                            <td>科目</td>
                            <td>开始时间</td>
                            <td>结束时间</td>
                            <td>详细信息</td>
                          </tr>
                          <eschool:items>
                          <tr bgcolor="#f0f0f0">
                              <td><eschool:schoolresourceAttribute attribute="name"/></td>
                              <td><a href='<eschool:schoolresourceAttribute attribute="docURL"/>'>链接</a></td>
                              <td><eschool:schoolresourceAttribute attribute="createByName"/></td>
                              <td><eschool:schoolresourceAttribute attribute="lastModifyDate"/></td>
                              <td><eschool:schoolresourceAttribute attribute="subjectName"/></td>
                              <td><eschool:schoolresourceAttribute attribute="startDate"/></td>
                              <td><eschool:schoolresourceAttribute attribute="endDate"/></td>
                              <td><eschool:schoolresourceAttribute attribute="explain"/></td>
                          </tr>
                          </eschool:items>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
									<eschool:firstForm action='<%=action%>'>
										<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
									    <input type=hidden name=keyword value="<%=request.getParameter("info")%>">
									    <input type=hidden name=subjectID value="<%=request.getParameter("catalog")%>">
									    <input type=hidden name=time value="<%=request.getParameter("time")%>">
									</eschool:firstForm>
	
									<eschool:prevForm action='<%=action%>'>
										<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
									    <input type=hidden name=keyword value="<%=request.getParameter("info")%>">
									    <input type=hidden name=subjectID value="<%=request.getParameter("catalog")%>">
									    <input type=hidden name=time value="<%=request.getParameter("time")%>">
									</eschool:prevForm>
									<td align="center"><eschool:pageForm/></td>
	
									<eschool:nextForm action='<%=action%>'>
										<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
									    <input type=hidden name=keyword value="<%=request.getParameter("info")%>">
									    <input type=hidden name=subjectID value="<%=request.getParameter("catalog")%>">
									    <input type=hidden name=time value="<%=request.getParameter("time")%>">
									</eschool:nextForm>
									
									<eschool:lastForm action='<%=action%>'>
										<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
									    <input type=hidden name=keyword value="<%=request.getParameter("info")%>">
									    <input type=hidden name=subjectID value="<%=request.getParameter("catalog")%>">
									    <input type=hidden name=time value="<%=request.getParameter("time")%>">
									</eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                        </eschool:schoolresourceSearchList>
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
