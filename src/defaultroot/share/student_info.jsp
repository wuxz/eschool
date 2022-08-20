<SCRIPT LANGUAGE=javascript>
    <!--

    function changepassword(valueStr)
    {
          win=window.open("<%= request.getContextPath() %>/admin/user/change_password.jsp?userId="+valueStr,"更改密码","height=300,width=400,left=300,top=150,resizable=yes,scrollbars=yes");
    }
      //-->
</SCRIPT>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<br>
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
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top"> <br>
                        　　个人信息<br><br>
                        <form action="validatestudent?<%=request.getQueryString()%>" method="post" name="adduser" ONSUBMIT="return dosubmit()">
                        <eschool:userDetails>
                        <table width="85%" border="0" cellspacing="1" cellpadding="2" align="right" >
                          <tr>
                            <td>姓名：</td>
                            <td><eschool:userDetailsAttr attribute="Name"/></td>
                          </tr>
                          <tr>
                            <td>用户名：</td>
                            <td><eschool:userDetailsAttr attribute="loginName"/></td>
                          </tr>
                          <tr>
                            <td>联系电话：</td>
                            <td><input type="text" name="tel" value="<eschool:userDetailsAttr attribute="Tel"/>"></td>
                          </tr>
                          <tr>
                            <td>邮件地址：</td>
                            <td><input type="text" name="email" value="<eschool:userDetailsAttr attribute="Email"/>"></td>
                          </tr>
                          <tr>
                            <td>通讯地址：</td>
                            <td><input type="text" name="address" value="<eschool:userDetailsAttr attribute="Address"/>"></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td><a href="javascript:changepassword(<eschool:userDetailsAttr attribute="userID"/>)">修改密码</a></td>
                          </tr>
                          <tr>
                            <td>
                            <input type="hidden" name="action" value="updateUser">
                            <input type="hidden" name="userId" value="<eschool:userDetailsAttr attribute="userID"/>">
                            <input type="hidden" name="name" value="<eschool:userDetailsAttr attribute="name"/>">
                            <input type="hidden" name="loginName" value="<eschool:userDetailsAttr attribute="loginName"/>">
                            <input type="hidden" name="gender" value="<eschool:userDetailsAttr attribute="gender"/>">
                            <input type="hidden" name="classID" value="<eschool:userDetailsAttr attribute="classID"/>">
                            <input type="hidden" name="userType" value="<eschool:userDetailsAttr attribute="userType"/>">
                            <input type="hidden" name="state" value="<eschool:userDetailsAttr attribute="state"/>">
                            <input type="hidden" name="birthday" value="<eschool:userDetailsAttr attribute="birthday"/>">
                            </td>
                            <td><input type="image" src="<%= request.getContextPath() %>/image/save_3.gif" border="0" width="68" height="22" name="submit"></td>
                          </tr>
                        </table>
                        </eschool:userDetails>
                        </form>

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
