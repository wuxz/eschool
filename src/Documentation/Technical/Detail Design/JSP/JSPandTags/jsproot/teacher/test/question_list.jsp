<html>
<head>

<%@include file="/common/layout.jsp" %>
</head>

<body bgcolor="#32009f" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@include file="/common/t_banner.jsp" %>
<br>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td width="160">
      <%@include file="/common/t_navigator.jsp" %>
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
                      <td bgcolor="#E3EDEC" valign="top"> <br>
                        试题列表<br>
                        <br>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>&nbsp;</td>
                            <td>试题名称</td>
                            <td>答题纸</td>
                            <td>状态</td>
                            <td>说明</td>
                            <td>创建人</td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF">
                              <input type="radio" name="radiobutton" value="radiobutton">
                            </td>
                            <td bgcolor="#FFFFFF">试题1</td>
                            <td bgcolor="#FFFFFF">答题纸1</td>
                            <td bgcolor="#FFFFFF">公开</td>
                            <td bgcolor="#FFFFFF"><a href="/teacher/common/test_property.jsp">A卷</a></td>
                            <td bgcolor="#FFFFFF">周树人</td>
                          </tr>
                          <tr bgcolor="#f0f0f0">
                            <td>
                              &nbsp;
                            </td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF">
                              &nbsp;
                            </td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                          </tr>
                          <tr bgcolor="#f0f0f0">
                            <td>
                              &nbsp;
                            </td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF">
                              &nbsp;
                            </td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                          </tr>
                          <tr bgcolor="#f0f0f0">
                            <td>
                              &nbsp;
                            </td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF">
                              &nbsp;
                            </td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                          </tr>
                        </table>
                        <table width="400" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td><img src="/image/btn_preview.gif" width="82" height="35" border="0"></td>
                            <td><a href="answer_sheet.jsp"><img src="/image/btn_paper.gif" width="87" height="35" border="0"></a></td>
                            <td><a href="analyse_test.jsp"><img src="/image/btn_total.gif" width="87" height="35" border="0"></a></td>
                            <td><img src="/image/btn_edit.gif" width="87" height="35" border="0"></td>
                            <td><img src="/image/btn_del.gif" width="87" height="35" border="0"></td>
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

<%@include file="/common/foot.jsp" %>
<br>

</body>
</html>
