<SCRIPT LANGUAGE=javascript>
    <!--

    function changepassword(valueStr)
    {
          win=window.open("<%= request.getContextPath() %>/admin/user/change_password.jsp?userId="+valueStr,"��������","height=300,width=400,left=300,top=150,resizable=yes,scrollbars=yes");
    }
      //-->
  </SCRIPT>
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
        <form action="users?method=search" method="post">
        <table border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td align="center">�ؼ��֣�<input type="text" name="value">&nbsp;<input type="image" src="<%= request.getContextPath() %>/image/search_2.gif" border="0" height="22" width="68" name="submit"></td>
          </tr>
        </table>
        </form>
        <br><br>�û��б� <a href="usermanager?action=createUser">����û�</a><br><br>
		      <table>
                          <tr>
                            <td>
                              <a href="users">ѧ��</a>
                              <a href="users?value=teacher">��ʦ</a>
                              <a href="users?value=admin">����Ա</a>
                            </td>
                          </tr>
                        </table>
                      <eschool:usersSearchList numItems="10" clause='<%=request.getParameter("method")%>' value='<%=request.getParameter("value")%>'>

                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
                                  <eschool:firstForm action="users">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action="users">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="users">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="users">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                  </eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>�û���</td>
                            <td>����</td>
                            <td>�Ա�</td>
                            <td>����</td>
                            <td>�绰</td>
                            <td>e-mail</td>
                            <td>���</td>
                            <td>���</td>
                            <td>״̬</td>
                            <td>����</td>
                          </tr>

                            <eschool:items>
                              <tr>
                                <td bgcolor="#FFFFFF"><a href="usermanager?action=updateUser&userId=<eschool:usersAttribute attribute="userID"/>"><eschool:usersAttribute attribute="loginName"/></a></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="name"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="gender"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="birthday"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="tel"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="email"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="userType"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="className"/></td>
                                <td bgcolor="#FFFFFF"><eschool:usersAttribute attribute="state"/></td>
                                <td bgcolor="#FFFFFF"><a href="javascript:changepassword(<eschool:usersAttribute attribute="userID"/>)">�޸�����</a></td>
                              </tr>
                            </eschool:items>

                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="160" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
                                  <eschool:firstForm action="users">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
                                  </eschool:firstForm>

                                  <eschool:prevForm action="users">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
                                  </eschool:prevForm>
                                  <td align="center"><eschool:pageForm/></td>

                                  <eschool:nextForm action="users">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
                                  </eschool:nextForm>

                                  <eschool:lastForm action="users">
                                    <td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
                                  </eschool:lastForm>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                      </eschool:usersSearchList>
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
