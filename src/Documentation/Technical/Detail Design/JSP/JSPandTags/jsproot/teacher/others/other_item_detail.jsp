<html>
<head>

<%@ include file="/common/layout.jsp" %>
</head>

<body bgcolor="#32009f" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/common/t_banner.jsp" %>
<br>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td width="160">
      <%@ include file="/common/t_navigator.jsp" %>
    </td>
    <td>
      <table width="600" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16"><img src="/image/content_topleft_corner.gif" width="16" height="17"></td>
                <td background="/image/content_top_fill.gif" valign="top"><img src="/image/spacer.gif" width="1" height="16"></td>
                <td width="17"><img src="/image/content_topright_corner.gif" width="17" height="17"></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td background="/image/content_left_fill.gif" width="16"><img src="/image/spacer.gif" width="16" height="1"></td>
                <td bgcolor="#FFFFFF" valign="top">
                  <%@ include file="common/t_others_title.jsp" %>
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top">
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top">&nbsp;</td>
                          </tr>
                          <tr>
                            <td valign="top" width="60">名称：</td>
                            <td valign="top">
                              <input type="text" name="textfield">
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">时间：</td>
                            <td valign="top">
                              <input type="text" name="textfield2">
                              至
                              <input type="text" name="textfield3">
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">说明：</td>
                            <td valign="top">
                              <textarea name="textfield4" cols="30" rows="4"></textarea>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">课程：</td>
                            <td valign="top">
                              <select name="select">
                                <option>一年级语文</option>
                              </select>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">状态：</td>
                            <td valign="top">
                              <input type="radio" name="radiobutton" value="radiobutton">
                              公布
                              <input type="radio" name="radiobutton" value="radiobutton">
                              不公布
                          </tr>
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top" align="center"><img src="/image/btn_save.gif" width="77" height="31" border="0"></td>
                          </tr>
                        </table>
                      </td>
                      <td background="/image/line_table_right.gif" width="22">&nbsp;</td>
                    </tr>
                    <tr>
                      <td><img src="/image/tab_downleft_corner.gif" width="22" height="19"></td>
                      <td background="/image/line_table_down.gif">&nbsp;</td>
                      <td><img src="/image/tab_downright_corner.gif" width="22" height="19"></td>
                    </tr>
                  </table>
                  <p>&nbsp;</p>
                  </td>
                <td background="/image/content_right_fill.gif" width="17"><img src="/image/spacer.gif" width="17" height="1"></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16"><img src="/image/content_downleft_corner.gif" width="16" height="17"></td>
                <td background="/image/content_down_fill.gif"><img src="/image/spacer.gif" width="1" height="17"></td>
                <td width="17"><img src="/image/content_downritht_corner.gif" width="17" height="17"></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<%@ include file="/common/foot.jsp" %>
<br>

</body>
</html>
