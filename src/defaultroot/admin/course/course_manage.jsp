<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<form action="validatenewcourse?<%=request.getQueryString()%>" method="post" name="addcourse" ONSUBMIT="return dosubmit()">
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
            <eschool:courseDetails>
            <table width="90%" align="center">
              <tr>
                <td width="30%" align="right">
                  <eschool:courseDetailsAttr attribute="title"/>�γ�
                </td>
                <td>&nbsp
                </td>
              </tr>
	      <% String action=request.getParameter("action") ;
	         if(action==null) action ="";
		 if(!action.equals("createcourse"))
		 {
	      %>
              <tr>
                <td align="right">
                  <a href="coursemanager?<%=request.getQueryString()%>">�γ���Ϣ</a>
                  <a href="coursestudent?<%=request.getQueryString()%>">ѧ���б�</a>
                </td>
                <td>&nbsp;
                </td>
              </tr>
	      <% }%>
              <tr>
                <td align="right">
                  &nbsp;
                </td>
                <td>&nbsp;
                </td>
              </tr>
              <tr>
                <td align="right">
                  �γ���
                  </td>
                  <td>
                  <input type="text" name="courseName" value="<eschool:courseDetailsAttr attribute="courseName"/>">
                  </td>
              </tr>
              <tr>
                <td align="right">
                  ʱ��
                  </td>
                  <td>
                  <input type="text" name="startDate" value="<eschool:courseDetailsAttr attribute="startDate"/>">
                  ��<input type="text" name="endDate" value="<eschool:courseDetailsAttr attribute="endDate"/>">
                  <br>�밴���¸�ʽ����(YYYYMMDD)��20010101
                  </td>
              </tr>
              <tr>
                <td align="right">
                  ˵��
                  </td>
                  <td>
                  <textarea name="info" rows="4" ><eschool:courseDetailsAttr attribute="info"/></textarea>
                  </td>
              </tr>
              <tr>
                <td align="right">
                  ״̬
                </td>
                <td>
                <select name="state">
                    <option value="">��ѡ��״̬</option>
                    <option value="��ͣ" <eschool:courseDetailsAttr attribute="selected_state_��ͣ"/>>��ͣ</option>
                    <option value="��δ��ʼ" <eschool:courseDetailsAttr attribute="selected_state_��δ��ʼ"/>>��δ��ʼ</option>
                    <option value="�����" <eschool:courseDetailsAttr attribute="selected_state_�����"/>>�����</option>
                    <option value="������" <eschool:courseDetailsAttr attribute="selected_state_������"/>>������</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td align="right">
                  ��ʦ
                </td>
                <td>
                  <select name="teacher">
                    <option value="">��ѡ���ʦ</option>
                    <eschool:usersSearchList numItems='0' clause='usertype' value='teacher'>
                      <eschool:items>
                        <option value="<eschool:usersAttribute attribute="userID"/>"<eschool:usersAttribute attribute="selectedteacherid"/>><eschool:usersAttribute attribute="name"/></option>
                      </eschool:items>
                    </eschool:usersSearchList>
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
                  <input type="image" src="<%= request.getContextPath() %>/image/<eschool:courseDetailsAttr attribute="button"/>_2.gif" width="68" height="22" name="submit">
                </td>
              </tr>
            </table>
            </eschool:courseDetails>
          </td>
          <td background="<%= request.getContextPath() %>/image/content_right_fill.gif" width="17"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="17" height="1"></td>
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
