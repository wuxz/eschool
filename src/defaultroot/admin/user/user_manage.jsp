<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<form action="validatenewuser?<%=request.getQueryString()%>" method="post" name="adduser" ONSUBMIT="return dosubmit()">
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
                  <eschool:userDetailsAttr attribute="title"/>�û�
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
                  �û���¼��
                  </td>
                  <td>
                  <input type="text" name="loginName" value="<eschool:userDetailsAttr attribute="LoginName"/>"> &nbsp;��ʹ��Ӣ���û���
                  </td>
              </tr>
              <tr>
                <td align="right">
                  ����
                  </td>
                  <td>
                  <input type="text" name="name" value="<eschool:userDetailsAttr attribute="Name"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  �Ա�
                  </td>
                  <td>
                  <select name="gender">
                    <option value="">��ѡ���Ա�</option>
                      <option value="��" <eschool:userDetailsAttr attribute="maleSelected"/>>��</option>
                      <option value="Ů" <eschool:userDetailsAttr attribute="femaleSelected"/>>Ů</option>
                  </select>
                  </td>
              </tr>
              <tr>
                <td align="right">
                  ����
                  </td>
                  <td>
                  <input type="text" name="birthday" value="<eschool:userDetailsAttr attribute="Birthday"/>">
                  <br>�밴���¸�ʽ����(YYYYMMDD)��20010101
                  </td>
              </tr>
              <tr>
                <td align="right">
                  �绰
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
                  ͨ�ŵ�ַ
                  </td>
                  <td>
                  <input type="text" name="address" value="<eschool:userDetailsAttr attribute="Address"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  ���
                  </td>
                  <td>
                  <select name="classID">
                    <option value="">��ѡ�����</option>
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
                  ���
                </td>
                <td>
                  <select name="userType">
                    <option value="">��ѡ�����</option>
                    <option value="ѧ��" <eschool:userDetailsAttr attribute="selected_usertype_ѧ��"/>>ѧ��</option>
                    <option value="��ʦ" <eschool:userDetailsAttr attribute="selected_usertype_��ʦ"/>>��ʦ</option>
                    <option value="����Ա" <eschool:userDetailsAttr attribute="selected_usertype_����Ա"/>>����Ա</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td align="right">
                  ״̬
                </td>
                <td>
                  <select name="state">
                    <option value="">��ѡ��״̬</option>
                    <option value="��ʽ" <eschool:userDetailsAttr attribute="selected_state_��ʽ"/>>��ʽ</option>
                    <option value="��ͣ" <eschool:userDetailsAttr attribute="selected_state_��ͣ"/>>��ͣ</option>
                    <option value="����" <eschool:userDetailsAttr attribute="selected_state_����"/>>����</option>
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

