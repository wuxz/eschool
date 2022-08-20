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
                  <table width="550" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                    <tr> 
                      <td valign="top"> 
                        <a href="schooldatalist">学校资料列表</a> <a href="schoolresourcemanager?action=createSchoolResource">添加新资料</a>
                      </td>
                      <br>
                    </tr>
                      <td valign="top">
                      <form action="schooldatalist" method="post">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td>关键字<input type="text" name="keyword"></td>
                          </tr>
                          <tr>
                              <select name="subjectID">

                                <option value="">科目</option>
                                <eschool:subjectSearchList numItems="0" clause=''>
                                  <eschool:items>
                                  <option value="<eschool:subjectAttribute attribute="subjectID"/>"><eschool:subjectAttribute attribute="name"/></option>
                                  </eschool:items>
                                </eschool:subjectSearchList>
                              </select>
                          </tr><br><br>
                            <input type=image src="<%= request.getContextPath() %>/image/search_2.gif"><br><br></td>
                          </tr>
                        </table>
                        </form>
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
                            <td valign="top">
                              <eschool:schoolresourceSearchList numItems="20" keyword='<%=request.getParameter("keyword")%>' subjectID='<%=request.getParameter("subjectID")%>' role='admin'>
                              <table width="100%" border="0" cellspacing="1" cellpadding="2" bgcolor="#000000">
                                <tr bgcolor="#CCCCCC" align="center">
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
                                  <td><a href="schoolresourcemanager?action=updateSchoolResource&schoolResourceID=<eschool:schoolresourceAttribute attribute='schoolresourceid'/>"><eschool:schoolresourceAttribute attribute="name"/></a></td>
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
                            </td>
                          </tr>
                          <tr>
                            <td align="right">
								<table border="0" cellspacing="1" cellpadding="2" width="160" align="right">
									<tr>
										<eschool:firstForm action='schooldatalist'>
											<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
										    <input type=hidden name=keyword value="<%=request.getParameter("keyword")%>">
										    <input type=hidden name=subjectID value="<%=request.getParameter("subjectID")%>">
										</eschool:firstForm>
		
										<eschool:prevForm action='schooldatalist'>
											<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
										    <input type=hidden name=keyword value="<%=request.getParameter("keyword")%>">
										    <input type=hidden name=subjectID value="<%=request.getParameter("subjectID")%>">
										</eschool:prevForm>
										<td align="center"><eschool:pageForm/></td>
		
										<eschool:nextForm action='schooldatalist'>
											<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
										    <input type=hidden name=keyword value="<%=request.getParameter("keyword")%>">
										    <input type=hidden name=subjectID value="<%=request.getParameter("subjectID")%>">
										</eschool:nextForm>
										
										<eschool:lastForm action='schooldatalist'>
											<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
										    <input type=hidden name=keyword value="<%=request.getParameter("keyword")%>">
										    <input type=hidden name=subjectID value="<%=request.getParameter("subjectID")%>">
										</eschool:lastForm>
									</tr>
								</table>
                              </eschool:schoolresourceSearchList>
                            </td>
                          </tr>
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

