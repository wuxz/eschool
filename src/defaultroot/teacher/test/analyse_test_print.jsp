<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td bgcolor="#FFFFFF" valign="top">
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td>�������ƣ�<eschool:eschoolAttribute attribute="projectname"/></td>
                          </tr>
                        </table>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>���</td>
                            <td>�����</td>
                            <td>�����</td>
                            <td>��ȷ��</td>
                          </tr>
                          <eschool:scorestatisticSearchList numItems='0' clause='<%=request.getParameter("projectId")%>' value='<%=request.getParameter("order")%>'>
                          	<eschool:items>
                          <tr>
                            <td bgcolor="#FFFFFF"><eschool:scorestatisticAttribute attribute="itemNumber"/></td>
                            <td bgcolor="#FFFFFF"><eschool:scorestatisticAttribute attribute="rightAnswer"/></td>
                            <td bgcolor="#FFFFFF"><eschool:scorestatisticAttribute attribute="wrongAnswer"/></td>
                            <td bgcolor="#FFFFFF"><eschool:scorestatisticAttribute attribute="statistic"/>%</td>
                          </tr>
                          	</eschool:items>
                          </eschool:scorestatisticSearchList>
                        </table>
                        <p>&nbsp;</p></td>
                      </td>
                    </tr>
                  </table>
