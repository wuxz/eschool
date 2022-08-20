    <table bgcolor="#FFFFFF" width="100%" height="600" border="0">
      <tr>
        <td>
          <br>
          <form action="verifysignin" method="post" name="signinform">
          <table width="400" border="0" cellspacing="1" cellpadding="2" align="center">
            <tr>
              <td align="center"><img src="<%= request.getContextPath() %>/image/logo_tongang.gif" width="147" height="124"></td>
            </tr>
            <tr>
              <td align="center"><img src="<%= request.getContextPath() %>/image/login_title.gif" width="96" height="35"></td>
            </tr>
            <tr>
              <td align="center">用户登录</td>
            </tr>
            <tr>
              <td align="center">
                <table width="300" border="0" cellspacing="1" cellpadding="2">
                  <tr>
                    <td width="80">用户名：</td>
                    <td>
                      <input type="text" name="loginName">
                    </td>
                  </tr>
                  <tr>
                    <td>密码：</td>
                    <td>
                      <input type="password" name="password">
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td align="center"><input type="image" src="<%= request.getContextPath() %>/image/btn_login.gif" border="0" width="79" height="35" name="submit">
                <a href="javascript:signinform.reset();"><img src="<%= request.getContextPath() %>/image/btn_cancle.gif" width="82" height="35" border="0"></a></td>
            </tr>
          </table>
          </form>
        </td>
      </tr>
    </table>
