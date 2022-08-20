
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>

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
        <br><br>科目列表 <a href="subjectmanager?action=createsubject">添加科目</a><br>
                      <eschool:subjectSearchList numItems="10" clause=''>
                         <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
                                  <eschool:firstForm action="subject">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action="subject">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="subject">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="subject">
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
                            <td>操作</td>
                          </tr>

                            <eschool:items>
                              <tr>
                                <td bgcolor="#FFFFFF"><a href="subjectmanager?action=updatesubject&subjectId=<eschool:subjectAttribute attribute="subjectID"/>"><eschool:subjectAttribute attribute="name"/></a></td>
                                <td bgcolor="#FFFFFF"><a href="validatenewsubject?action=deletesubject&subjectId=<eschool:subjectAttribute attribute="subjectID"/>">删除</a></td>
                              </tr>
                            </eschool:items>

                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
                                  <eschool:firstForm action="subject">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action="subject">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="subject">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="subject">
                                  <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                  </eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </eschool:subjectSearchList>
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
