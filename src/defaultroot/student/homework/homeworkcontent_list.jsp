<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td width="160">
      <%@include file="/common/s_navigator.jsp" %>
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
                  <%@ include file="common/s_assignment_title.jsp" %>
				  <eschool:homeWorkContentSearchList numItems="20" field='HomeWorkID' clause='4'>
				  
				<table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
				  <tr>
				    <td height="28">
				      <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
					<tr>
					  <td width="12"><img src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" width="12" height="10"></td>
	
					  <eschool:prevForm action="homeworkstudentcontent">
					    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
					  </eschool:prevForm>
					  <td align="center">第1页/共1页</td>
	
					  <eschool:nextForm action="homeworkstudentcontent">
					    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
					  </eschool:nextForm>
					  <td width="12"><img src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" width="12" height="10"></td>
					</tr>
				      </table>
				    </td>
				  </tr>
				</table>
	
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top"> <br>
                        作业列表<br>
                        <br>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>名称</td>
                            <td>创建时间</td>
                            <td>状态</td>
                            <td>说明</td>
                          </tr>
					    <eschool:items>
                          <tr>
                            <td bgcolor="#FFFFFF"><a href="<%= request.getContextPath() %>/download?filename=<eschool:homeWorkContentAttribute attribute="docurl"/>&filecode=<eschool:homeWorkContentAttribute attribute="hashcode"/>&savename=<eschool:homeWorkContentAttribute attribute="savename"/>"><eschool:homeWorkContentAttribute attribute="contentName"/></a></td>
                            <td bgcolor="#FFFFFF"><eschool:homeWorkContentAttribute attribute="createTime"/></td>
                            <td bgcolor="#FFFFFF"><eschool:homeWorkContentAttribute attribute="state"/></td>
                            <td bgcolor="#FFFFFF"><eschool:homeWorkContentAttribute attribute="contentInfo"/></td>
                          </tr>
					    </eschool:items>
                        </table>

						<table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
						  <tr>
						    <td height="28">
						      <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
							<tr>
							  <td width="12"><img src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" width="12" height="10"></td>
							  <td width="12"><img src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" width="12" height="10"></td>
							  <td align="center">第1页/共1页</td>
							  <td width="12"><img src="><%= request.getContextPath() %>/image/ico_arrow_next.gif" width="12" height="10"></td>
							  <td width="12"><img src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" width="12" height="10"></td>
							</tr>
						      </table>
						    </td>
						  </tr>
						</table>
					      </eschool:homeWorkContentSearchList>

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
