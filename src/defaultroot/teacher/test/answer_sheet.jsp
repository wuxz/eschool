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
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>自习考试>考试><eschool:eschoolAttribute attribute="coursename"/></td>
                    </tr>
                  </table>
                  <br>
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top">
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td align="right"><a href="testlist?action=updateproject&projectId=<%=request.getParameter("projectId")%>&type=test">返回试题列表</a></td>
                          </tr>
                          <tr>
                            <td><eschool:eschoolAttribute attribute="projectName"/>答题纸及答案</td>
                          </tr>
                        </table>
                        <%
                          String str_URL="validateansweritem?"+request.getQueryString();
                          if(!"updateansweritem".equals(request.getParameter("action")))
                          {
                            str_URL="validateansweritem?action=createansweritem&contentID="+request.getParameter("contentID")+"&projectId="+request.getParameter("projectId");
                          }
                        %>
                        <form action="<%=str_URL%>" method="post" name="answersheet" ONSUBMIT="return dosubmit()">
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top"><a href="answeritem?action=createansweritem&contentID=<%=request.getParameter("contentID")%>&projectId=<%=request.getParameter("projectId")%>">添加新答案</a></td>
                          </tr>
			  <eschool:answerItemDetails>
                          <tr>
                            <td valign="top" width="60">题号：</td>
                            <td valign="top">
                              <input type="text" name="itemNumber" value="<eschool:answerItemDetailsAttr attribute="itemNumber"/>">
                              备选项数：<input type="text" name="answerNumber" value="<eschool:answerItemDetailsAttr attribute="answerNumber"/>">
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">类型：</td>
                            <td valign="top">
                              <select name="answertype">
                              <option value="">请选择类型</option>
                              <option value="选择题" <eschool:answerItemDetailsAttr attribute="selected_type_选择题"/>>选择题</option>
                              <option value="文字题" <eschool:answerItemDetailsAttr attribute="selected_type_文字题"/>>文字题</option>
                              </select>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">答案：</td>
                            <td valign="top">
                              <textarea name="answer" cols="30" rows="3"><eschool:answerItemDetailsAttr attribute="answer"/></textarea>
                              注意：选择答案请用半角逗号隔开，如"A,B,C"
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">备注：</td>
                            <td valign="top">
                              <textarea name="memo" cols="30" rows="3"><eschool:answerItemDetailsAttr attribute="memo"/></textarea>
                              此项不会被学生看到
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top" align="center">
                            <input type="image" src="<%= request.getContextPath() %>/image/<eschool:answerItemDetailsAttr attribute="button"/>_3.gif" width="68" height="22" border="0" name="submit"></td>
                          </tr>
			  </eschool:answerItemDetails>
                        </table>
                        </form>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>题号</td>
                            <td>类型</td>
                            <td>答案</td>
                            <td>备注</td>
                            <td>操作</td>
                          </tr>
                          <eschool:answerItemSearchList numItems="0" clause='<%=request.getParameter("contentID")%>'>
                            <eschool:items>
                          <tr>
                            <td bgcolor="#FFFFFF"><a href="answeritem?action=updateansweritem&projectId=<%=request.getParameter("projectId")%>&contentID=<%=request.getParameter("contentID")%>&answerItemId=<eschool:answerItemAttribute attribute="answerItemID"/>"><eschool:answerItemAttribute attribute="itemNumber"/></a></td>
                            <td bgcolor="#FFFFFF"><eschool:answerItemAttribute attribute="type"/></td>
                            <td bgcolor="#FFFFFF"><eschool:answerItemAttribute attribute="answer"/></td>
                            <td bgcolor="#FFFFFF"><eschool:answerItemAttribute attribute="memo"/></td>
                            <td bgcolor="#FFFFFF"><a href="validateansweritem?action=deleteansweritem&projectId=<%=request.getParameter("projectId")%>&contentID=<%=request.getParameter("contentID")%>&answerItemId=<eschool:answerItemAttribute attribute="answerItemID"/>">删除</a></td>
                          </tr>
                            </eschool:items>
                          </eschool:answerItemSearchList>
                        </table>
                        </td>
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