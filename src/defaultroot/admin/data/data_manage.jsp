<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<%
  String action="";
  String title="";
  String schoolResourceID = request.getParameter("schoolResourceID");
  if (schoolResourceID == null || schoolResourceID.equals("") || schoolResourceID.equals("null"))
  {
  	schoolResourceID = null;
    action="createSchoolResource";
    title = "添加";
  }else{
    action="updateSchoolResource";
    title = "编辑";
  }
%>
<script language="javascript">
<!--
	function onclick_del()
	{
		fm1.action="updateschoolresource?action=deleteSchoolResource";
		fm1.submit();
	}

    function dosubmit()
    {
      if(window.name.value=="")
      {
        alert("名称不能为空！");
		return false;
      }
    }
-->
</script>
<form action="updateschoolresource?action=<%=action%>" method="post" name=fm1 id=fm1 ONSUBMIT="return dosubmit();">
<%
	if (schoolResourceID != null)
	{
%>
<input type=hidden name="schoolResourceID" value="<%=schoolResourceID%>">
<%
	}
%>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td width="160">
    <eschool:insert parameter="HtmlNavigator" />
    </td>
    <td>
      <eschool:schoolresourceDetails>
      <table width="600" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16"><img src="<%= request.getContextPath() %>/image/content_topleft_corner.gif" width="16" height="17"></td>
                <td background="<%= request.getContextPath() %>/image/content_top_fill.gif" valign="top"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="16"></td>
                <td width="17"><img src="<%= request.getContextPath() %>/image/content_topright_corner.gif" width="17" height="17"></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td background="<%= request.getContextPath() %>/image/content_left_fill.gif" width="16"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="16" height="1"></td>
                <td bgcolor="#FFFFFF" valign="top">


                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top"> <br>
                        　　学校资料<%=title%><br><br>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center">
                          <tr>
                            <td height="28">
                              <table width="100%" border="0" cellspacing="1" cellpadding="2" align="right">
                                <tr>
                                  <td width="30%" align="right">名称</td>
                                  <td><input type="text" name="name" value="<eschool:schoolresourceDetailsAttr attribute="name"/>"></td>
                                </tr>
                                <tr>
                                  <td align="right">链接</td>
                                  <td><input type="text" name="docURL" value="<eschool:schoolresourceDetailsAttr attribute="docURL"/>"></td>
                                </tr>
                                <tr>
                                  <td align="right">状态</td>
                                  <td><select name="allow">
                                        <option value = "公布" <eschool:schoolresourceDetailsAttr attribute="selected_allow_公布"/>>公布</option>
                                        <option value = "不公布" <eschool:schoolresourceDetailsAttr attribute="selected_allow_不公布"/>>不公布</option>
                                      </select>
                                  </td>
                                </tr>
                                <tr>
                                  <td align="right">科目</td>
                                  <td>
                                   <select name="subjectID">
                                    <eschool:subjectSearchList numItems='0' clause=''>
                                      <eschool:items>
                                        <option value="<eschool:subjectAttribute attribute="subjectID"/>"  <eschool:subjectAttribute attribute="selectedcourseid"/>><eschool:subjectAttribute attribute="name"/></option>
                                      </eschool:items>
                                    </eschool:subjectSearchList>
                                
                                   </select>
                                  </td>
                                </tr>
                                <tr>
                                  <td align="right">说明</td>
                                  <td><textarea name="explain" rows="4"><eschool:schoolresourceDetailsAttr attribute='explain'/></textarea></td>
                                </tr>
                                <tr>
                                  <td></td>
                                  <td></td>
                                </tr>
                                <tr>
                                  <td></td>
                                  <td>
                                      <input type="image" src="<%= request.getContextPath() %>/image/<eschool:schoolresourceDetailsAttr attribute="button"/>_3.gif" width="68" height="22" name="submit">
<%
                                      	if (schoolResourceID != null)
                                      	{
%>
                                      <img src="<%= request.getContextPath() %>/image/del_3.gif" width="68" height="22" onclick="onclick_del()">
<%
                                      	}
                                      %>
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                        </table>
                        </td>
                      <td background="<%= request.getContextPath() %>/image/line_table_right.gif" width="22">&nbsp;</td>
                    </tr>
                    <tr>
                      <td><img src="<%= request.getContextPath() %>/image/tab_downleft_corner.gif" width="22" height="19"></td>
                      <td background="<%= request.getContextPath() %>/image/line_table_down.gif">&nbsp;</td>
                      <td><img src="<%= request.getContextPath() %>/image/tab_downright_corner.gif" width="22" height="19"></td>
                    </tr>
                  </table>


                  </td>
                <td background="<%= request.getContextPath() %>/image/content_right_fill.gif" width="17"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="17" height="1"></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16"><img src="<%= request.getContextPath() %>/image/content_downleft_corner.gif" width="16" height="17"></td>
                <td background="<%= request.getContextPath() %>/image/content_down_fill.gif"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="17"></td>
                <td width="17"><img src="<%= request.getContextPath() %>/image/content_downritht_corner.gif" width="17" height="17"></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      </eschool:schoolresourceDetails>
    </td>
  </tr>
</table>
</form>
