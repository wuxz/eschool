<html>
<head>

<%@include file="/common/layout.jsp" %>
</head>

<body bgcolor="#32009f" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<%@include file="/common/a_banner.jsp" %>
<br>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td>
      <table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td width="16"><img src="/image/content_topleft_corner.gif" width="16" height="17"></td>
          <td background="/image/content_top_fill.gif" valign="top"><img src="/image/spacer.gif" width="1" height="16"></td>
          <td width="17"><img src="/image/content_topright_corner.gif" width="17" height="17"></td>
        </tr>
      </table>

      <table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td background="/image/content_left_fill.gif" width="16"><img src="/image/spacer.gif" width="16" height="1"></td>
          <td bgcolor="#FFFFFF" valign="top">
            <table width="90%" align="center">
              <tr>
                <td width="30%" align="right">
                  添加新课程
                </td>
                <td>编辑课程信息
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
                  课程名
                  </td>
                  <td>
                  <input type="text" name="">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  时间
                  </td>
                  <td>
                  <input type="text" name="">至<input type="text" name="">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  说明
                  </td>
                  <td>
                  <textarea name="" rows="4" ></textarea>
                  </td>
              </tr>
              <tr>
                <td align="right">
                  状态
                </td>
                <td>
                <select name="">
                    <option>暂停</option>
                    <option>尚未开始</option>
                    <option>已完成</option>
                    <option>启用中</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td align="right">
                  教师
                </td>
                <td>
                  <select name="">
                    <option>王书生</option>
                    <option>周树人</option>
                    <option>润土</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td align="right">
                  &nbsp;
                </td>
                <td>
                  &nbsp;
                </td>
              </tr>
              <tr>
                <td align="right">
                  &nbsp;
                </td>
                <td>
                  <img src="/image/add_2.gif" width="68" height="22"><img src="/image/save_2.gif" width="68" height="22">
                </td>
              </tr>
            </table>
          </td>
          <td background="/image/content_right_fill.gif" width="17"><img src="/image/spacer.gif" width="17" height="1"></td>
        </tr>
      </table>
      <table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td width="16"><img src="/image/content_downleft_corner.gif" width="16" height="17"></td>
          <td background="/image/content_down_fill.gif"><img src="/image/spacer.gif" width="1" height="17"></td>
          <td width="17"><img src="/image/content_downritht_corner.gif" width="17" height="17"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<%@include file="/common/foot.jsp" %>
<br>

</body>
</html>
