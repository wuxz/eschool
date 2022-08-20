<SCRIPT LANGUAGE=javascript>
    <!--

    function changepassword(valueStr)
    {
          win=window.open("<%= request.getContextPath() %>/admin/user/change_password.jsp?userId="+valueStr,"更改密码","height=300,width=400,left=300,top=150,resizable=yes,scrollbars=yes");
    }
      //-->
</SCRIPT>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<form action="validateadmin?<%=request.getQueryString()%>" method="post" name="adduser" ONSUBMIT="return dosubmit()">
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
          <eschool:userDetails>
            <table width="90%" align="center">
              <tr>
                <td width="30%" align="right">
                  <eschool:userDetailsAttr attribute="title"/>用户
                </td>
                <td>&nbsp;
                </td>
              </tr>
              <tr>
                <td align="right">
                  &nbsp;
                </td>
                <td>&nbsp;
                </td>
              </tr>
              <tr>
                <td align="right">
                  用户登录名
                  </td>
                  <td>
                  <input type="text" name="loginName" value="<eschool:userDetailsAttr attribute="LoginName"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  姓名
                  </td>
                  <td>
                  <input type="text" name="name" value="<eschool:userDetailsAttr attribute="Name"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  性别
                  </td>
                  <td>
                  <select name="gender">
                      <option value="">请选择性别</option>
                      <option value="男" <eschool:userDetailsAttr attribute="maleSelected"/>>男</option>
                      <option value="女" <eschool:userDetailsAttr attribute="femaleSelected"/>>女</option>
                  </select>
                  </td>
              </tr>
              <tr>
                <td align="right">
                  生日
                  </td>
                  <td>
                  <input type="text" name="birthday" value="<eschool:userDetailsAttr attribute="Birthday"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  电话
                  </td>
                  <td>
                  <input type="text" name="tel" value="<eschool:userDetailsAttr attribute="Tel"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  E-mail
                  </td>
                  <td>
                  <input type="text" name="email" value="<eschool:userDetailsAttr attribute="Email"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  通信地址
                  </td>
                  <td>
                  <input type="text" name="address" value="<eschool:userDetailsAttr attribute="Address"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  组别
                  </td>
                  <td>
                  <select name="classID">
                  <option value="">请选择组别</option>
                    <eschool:eclassSearchList numItems="0" clause=''>
                      <eschool:items>
                        <option value="<eschool:eclassAttribute attribute="classID"/>" <eschool:eclassAttribute attribute="selecteduserid"/>><eschool:eclassAttribute attribute="name"/></option>
                      </eschool:items>
                    </eschool:eclassSearchList>
                  </select>
                  </td>
              </tr>
              <tr>
                <td align="right">
                  身份
                </td>
                <td>
                  <select name="userType">
                    <option value="">请选择身份</option>
                    <option value="学生" <eschool:userDetailsAttr attribute="selected_usertype_学生"/>>学生</option>
                    <option value="教师" <eschool:userDetailsAttr attribute="selected_usertype_教师"/>>教师</option>
                    <option value="管理员" <eschool:userDetailsAttr attribute="selected_usertype_管理员"/>>管理员</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td align="right">
                  状态
                </td>
                <td>
                  <select name="state">
                    <option value="">请选择状态</option>
                    <option value="正式" <eschool:userDetailsAttr attribute="selected_state_正式"/>>正式</option>
                    <option value="暂停" <eschool:userDetailsAttr attribute="selected_state_暂停"/>>暂停</option>
                    <option value="申请" <eschool:userDetailsAttr attribute="selected_state_申请"/>>申请</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td align="right">
                  <a href="javascript:changepassword(<eschool:userDetailsAttr attribute="userID"/>)">修改密码</a>
                </td>
                <td>
                  <input type="hidden" name="action" value="updateUser">
                  <input type="hidden" name="userId" value="<eschool:userDetailsAttr attribute="userID"/>">
                </td>
              </tr>
              <tr>
                <td align="right">
                  &nbsp;
                </td>
                <td>
                  <input type="image" src="<%= request.getContextPath() %>/image/<eschool:userDetailsAttr attribute="button"/>_2.gif" width="68" height="22" name="submit" boder="0">
                </td>
              </tr>
            </table>
            </eschool:userDetails>
          </td>
          <td background="<%= request.getContextPath() %>/image/content_right_fill.gif" width="17"><input type="image" src="<%= request.getContextPath() %>/image/spacer.gif" width="17" height="1" name="reset"></td>
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
