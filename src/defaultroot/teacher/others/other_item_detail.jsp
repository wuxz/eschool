<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<eschool:projectDetails />
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
		  <eschool:insert parameter="HtmlTopTitle" />
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top">
			<eschool:projectDetails>
			<form action="validateproject?<%=request.getQueryString()%>" method="post" name="projectadd" ONSUBMIT="return dosubmit()">
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top">&nbsp;</td>
                          </tr>
                          <tr>
                            <td valign="top" width="60">名称：</td>
                            <td valign="top">
                              <input type="text" name="name" value="<eschool:projectDetailsAttr attribute="name"/>">
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">时间：</td>
                            <td valign="top">
                              <input type="text" name="startDate" value="<eschool:projectDetailsAttr attribute="startdate"/>">
                              至
                              <input type="text" name="endDate" value="<eschool:projectDetailsAttr attribute="enddate"/>">
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">说明：</td>
                            <td valign="top">
                              <textarea name="info" cols="30" rows="4"><eschool:projectDetailsAttr attribute="info"/></textarea>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">课程：</td>
                            <td valign="top">
                              <select name="courseID">
                              <eschool:courseSearchList numItems="0" clause="teacher">
                              	<eschool:items>
                                <option value="<eschool:courseAttribute attribute="courseID"/>" <eschool:courseAttribute attribute="selectedprojectid"/>><eschool:courseAttribute attribute="courseName"/></option>
                                </eschool:items>
                              </eschool:courseSearchList>
                              </select>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">状态：</td>
                            <td valign="top">
                              <input type="radio" name="state" value="公布" <eschool:projectDetailsAttr attribute="checked_state_公布"/>>
                              公布
                              <input type="radio" name="state" value="不公布"  <eschool:projectDetailsAttr attribute="checked_state_不公布"/>>
                              不公布
                          </tr>
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top" align="center"><input type="image" src="<%= request.getContextPath() %>/image/btn_save.gif" width="77" height="31" border="0" name="submit"></td>
                          </tr>
			  <input type="hidden" name="publishResult" value="others">
                        </table>
			</form>
			</eschool:projectDetails>
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
