<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<form action="validatenewcourse?<%=request.getQueryString()%>" method="post" name="addcourse" ONSUBMIT="return dosubmit()">
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
            <eschool:courseDetails>
            <table width="90%" align="center">
              <tr>
                <td width="30%" align="right">
                  <eschool:courseDetailsAttr attribute="title"/>课程
                </td>
                <td>&nbsp
                </td>
              </tr>
	      <% String action=request.getParameter("action") ;
	         if(action==null) action ="";
		 if(!action.equals("createcourse"))
		 {
	      %>
              <tr>
                <td align="right">
                  <a href="coursemanager?<%=request.getQueryString()%>">课程信息</a>
                  <a href="coursestudent?<%=request.getQueryString()%>">学生列表</a>
                </td>
                <td>&nbsp;
                </td>
              </tr>
	      <% }%>
              <tr>
                <td align="right">
                  &nbsp;
                </td>
                <td>&nbsp;
                </td>
              </tr>
              <tr>
                <td align="right">
                  课程名
                  </td>
                  <td>
                  <input type="text" name="courseName" value="<eschool:courseDetailsAttr attribute="courseName"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  时间
                  </td>
                  <td>
                  <input type="text" name="startDate" value="<eschool:courseDetailsAttr attribute="startDate"/>">
                  至<input type="text" name="endDate" value="<eschool:courseDetailsAttr attribute="endDate"/>">
                  <br>请按以下格式输入(YYYYMMDD)：20010101
                  </td>
              </tr>
              <tr>
                <td align="right">
                  说明
                  </td>
                  <td>
                  <textarea name="info" rows="4" ><eschool:courseDetailsAttr attribute="info"/></textarea>
                  </td>
              </tr>
              <tr>
                <td align="right">
                  状态
                </td>
                <td>
                <select name="state">
                    <option value="">请选择状态</option>
                    <option value="暂停" <eschool:courseDetailsAttr attribute="selected_state_暂停"/>>暂停</option>
                    <option value="尚未开始" <eschool:courseDetailsAttr attribute="selected_state_尚未开始"/>>尚未开始</option>
                    <option value="已完成" <eschool:courseDetailsAttr attribute="selected_state_已完成"/>>已完成</option>
                    <option value="启用中" <eschool:courseDetailsAttr attribute="selected_state_启用中"/>>启用中</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td align="right">
                  教师
                </td>
                <td>
                  <select name="teacher">
                    <option value="">请选择教师</option>
                    <eschool:usersSearchList numItems='0' clause='usertype' value='teacher'>
                      <eschool:items>
                        <option value="<eschool:usersAttribute attribute="userID"/>"<eschool:usersAttribute attribute="selectedteacherid"/>><eschool:usersAttribute attribute="name"/></option>
                      </eschool:items>
                    </eschool:usersSearchList>
                  </select>
                </td>
              </tr>
              <tr>
                <td align="right">
                  &nbsp;
                </td>
                <td>
                  &nbsp;
                </td>
              </tr>
              <tr>
                <td align="right">
                  &nbsp;
                </td>
                <td>
                  <input type="image" src="<%= request.getContextPath() %>/image/<eschool:courseDetailsAttr attribute="button"/>_2.gif" width="68" height="22" name="submit">
                </td>
              </tr>
            </table>
            </eschool:courseDetails>
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
</form>
