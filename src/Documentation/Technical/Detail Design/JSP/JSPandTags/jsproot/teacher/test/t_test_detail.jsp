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
                  <%@ include file="common/t_test_title.jsp" %>
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
                              <input type="text" name="textfield" value="期中考试">
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">时间：</td>
                            <td valign="top">
                              <input type="text" name="textfield2" value="10/20/2001">
                              至
                              <input type="text" name="textfield3" value="10/22/2001">
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">说明：</td>
                            <td valign="top">
                              <textarea name="textfield4" cols="30" rows="4">全校统考</textarea>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">课程：</td>
                            <td valign="top">
                              <select name="select">
                                <option selected>一年级语文</option>
                              </select>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">状态：</td>
                            <td valign="top">
                              <input type="radio" name="radiobutton" value="radiobutton" >
                              公布
                              <input type="radio" name="radiobutton" value="radiobutton" checked>
                              不公布
<input type="radio" name="radiobutton" value="radiobutton">
                              考试完毕</td>
                          </tr>
                          <tr>
                            <td valign="top">成绩：</td>
                            <td valign="top">
                              <input type="radio" name="radiobutton1" value="radiobutton">
                              公布
                              <input type="radio" name="radiobutton1" value="radiobutton" checked>
                              不公布</td>
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
