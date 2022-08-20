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
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td>自习考试>考试>一年级语文</td>
                    </tr>
                  </table>
                  <br>
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top">
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td align="right"><a href="question_list.jsp">返回试题列表</a></td>
                          </tr>
                          <tr>
                            <td>试题1答题纸及答案</td>
                          </tr>
                        </table>
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top">&nbsp;</td>
                          </tr>
                          <tr>
                            <td valign="top" width="60">题号：</td>
                            <td valign="top">
                              <input type="text" name="textfield" value="6">
                              备选项数：<input type="text" name="textfield1" value="A,B,C,D">
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">类型：</td>
                            <td valign="top">
                              <select name="select">
                              <option selected>单选题</option>
                              <option>多选题</option>
                              <option>阅读</option>
                              <option>作文</option>
                              </select>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">答案：</td>
                            <td valign="top">
                              <textarea name="textfield4" cols="30" rows="3">C</textarea>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">备注：</td>
                            <td valign="top">
                              <textarea name="textfield5" cols="30" rows="3"></textarea>
                            </td>
                          </tr>
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top" align="center"><img src="/image/save_3.gif" width="68" height="22" border="0">
                            <img src="/image/add_3.gif" width="68" height="22" border="0"></td>
                          </tr>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>题号</td>
                            <td>类型</td>
                            <td>答案</td>
                            <td>备注</td>
                            <td>操作</td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF">1</td>
                            <td bgcolor="#FFFFFF">单选题</td>
                            <td bgcolor="#FFFFFF">A</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF"><a href="answer_sheet.jsp">删除</a></td>
                          </tr>
                          <tr bgcolor="#f0f0f0">
                            <td>2</td>
                            <td>单选题</td>
                            <td>C</td>
                            <td>&nbsp;</td>
                            <td><a href="answer_sheet.jsp">删除</a></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF">3</td>
                            <td bgcolor="#FFFFFF">单选题</td>
                            <td bgcolor="#FFFFFF">C</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF"><a href="answer_sheet.jsp">删除</a></td>
                          </tr>
                          <tr bgcolor="#f0f0f0">
                            <td>4</td>
                            <td>单选题</td>
                            <td>D</td>
                            <td>&nbsp;</td>
                            <td><a href="answer_sheet.jsp">删除</a></td>
                          </tr>
                          <tr>
                            <td bgcolor="#FFFFFF">5</td>
                            <td bgcolor="#FFFFFF">单选题</td>
                            <td bgcolor="#FFFFFF">D</td>
                            <td bgcolor="#FFFFFF">&nbsp;</td>
                            <td bgcolor="#FFFFFF"><a href="answer_sheet.jsp">删除</a></td>
                          </tr>
                          <tr bgcolor="#f0f0f0">
                            <td>&nbsp; </td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                        </table>
                        </td>
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
