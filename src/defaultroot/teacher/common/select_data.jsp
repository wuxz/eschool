<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<%
			  String type=request.getParameter("type");
			  String s_URL="";
			  String s_page="";
			  if (type==null) type="";
			  if(type.equals("test"))
			  {
			    s_URL="validatepc";
			    s_page="selectproject";
			  }else
			  {
			    s_URL="validatepcother";
			    s_page="selectother";
			  }

%>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td width="160">
      <eschool:insert parameter="HtmlNavigator" />
      <br>
    <form action="" method="post">
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
          <td valign="top"><font color="#FFFFFF">教师</font><br>
            <font color="#FFFFFF">
            <input type="text" name="teacher" size="20">
            </font> </td>
        </tr>
        <tr>
          <td valign="top"><font color="#FFFFFF">时间</font><br>
            <font color="#FFFFFF">
            <input type="text" name="time" size="20">
            </font> </td>
        </tr>
        <tr>
          <td><input type=image src="<%= request.getContextPath() %>/image/button_search.gif" width="61" height="30"></td>
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
                  <eschool:insert parameter="HtmlTopTitle" />
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top"> <br>
                        　　查询结果<br>
			<eschool:pcSearchList clause='<%=request.getParameter("type")%>' numItems='10' value='<%=request.getParameter("teacher")%>' info='<%=request.getParameter("info")%>' time='<%=request.getParameter("time")%>'>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
				<tr>
				  <eschool:firstForm action='<%=s_page%>'>
				  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
				  </eschool:firstForm>

				  <eschool:prevForm action="<%=s_page%>">
				  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
				  </eschool:prevForm>
				  <td align="center"><eschool:pageForm/></td>

				  <eschool:nextForm action="<%=s_page%>">
				  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
				  </eschool:nextForm>

				  <eschool:lastForm action="<%=s_page%>">
				  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
				  </eschool:lastForm>

				</tr>
			      </table>
                            </td>
                          </tr>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>名称</td>
                            <td>内容</td>
                            <td>创建人</td>
                            <td>修改时间</td>
                            <td>科目</td>
                            <td>说明</td>
                            <td>开始时间</td>
                            <td>结束时间</td>
                            <td>操作</td>
                          </tr>

			  <eschool:items>
                          <tr>
                            <td bgcolor="#FFFFFF"><eschool:pcAttribute attribute='projectName'/></td>
                            <td bgcolor="#FFFFFF"><eschool:pcAttribute attribute='contentName'/></td>
                            <td bgcolor="#FFFFFF"><eschool:pcAttribute attribute='creator'/></td>
                            <td bgcolor="#FFFFFF"><eschool:pcAttribute attribute='lastModifyDate'/></td>
                            <td bgcolor="#FFFFFF"><eschool:pcAttribute attribute='courseName'/></td>
                            <td bgcolor="#FFFFFF"><eschool:pcAttribute attribute='info'/></td>
                            <td bgcolor="#FFFFFF"><eschool:pcAttribute attribute='startDate'/></td>
                            <td bgcolor="#FFFFFF"><eschool:pcAttribute attribute='enddate'/></td>
                            <td bgcolor="#FFFFFF"><a href="<%=s_URL%>?action=createpc&projectId=<%=request.getParameter("projectId")%>&type=<%=request.getParameter("type")%>&contentID=<eschool:pcAttribute attribute='contentID'/>">选择</a></td>
                          </tr>
                          </eschool:items>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
				<tr>
				  <eschool:firstForm action='<%=s_page%>'>
				  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
				  </eschool:firstForm>

				  <eschool:prevForm action="<%=s_page%>">
				  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
				  </eschool:prevForm>
				  <td align="center"><eschool:pageForm/></td>

				  <eschool:nextForm action="<%=s_page%>">
				  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
				  </eschool:nextForm>

				  <eschool:lastForm action="<%=s_page%>">
				  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
				  </eschool:lastForm>

				</tr>
			      </table>
                            </td>
                          </tr>
                        </table>
			</eschool:pcSearchList>
                        　　以下为您所选择的资料<br>
                        <br>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>名称</td>
                            <td>创建时间</td>
                            <td>状态</td>
                            <td>说明</td>
                            <td>操作</td>
                          </tr>
                          <eschool:contentSearchList numItems="0" clause='session' method='search'>
                            <eschool:items>
                              <tr bgcolor="#f0f0f0">
                                <td><eschool:contentAttribute attribute='name'/></td>
                                <td><eschool:contentAttribute attribute='createdate'/></td>
                                <td><eschool:contentAttribute attribute='state'/></td>
                                <td><eschool:contentAttribute attribute='info'/></td>
                                <td><a href="<%=s_URL%>?action=deletecontent&projectId=<%=request.getParameter("projectId")%>&type=<%=request.getParameter("type")%>&contentID=<eschool:contentAttribute attribute='id'/>&projectContentID=<eschool:contentAttribute attribute='projectcontentid'/>">删除</a></td>
                              </tr>
                            </eschool:items>
                          </eschool:contentSearchList>
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
