<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>

<form action="validatenewsubject?<%=request.getQueryString()%>" method="post" name="addsubject" ONSUBMIT="return dosubmit()">
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
          <eschool:subjectDetails>
            <table width="90%" align="center">
              <tr>
                <td width="30%" align="right">
                  <eschool:subjectDetailsAttr attribute="title"/>¿ÆÄ¿
                </td>
                <td>&nbsp
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
                  Ãû³Æ
                  </td>
                  <td>
                  <input type="text" name="name" value="<eschool:subjectDetailsAttr attribute="Name"/>">
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
                  <input type="image" src="<%= request.getContextPath() %>/image/<eschool:subjectDetailsAttr attribute="button"/>_2.gif" width="68" height="22" name="submit" boder="0">
                </td>
              </tr>
            </table>
            </eschool:subjectDetails>
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

