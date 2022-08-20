<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<table width="779" border="0" cellspacing="2" cellpadding="2">
  <tr valign="top">
    <td width="20">&nbsp;</td>
    <td width="160">
      <eschool:insert parameter="HtmlNavigator" />
    </td>
    <td>
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
                      <p>自习考试 &gt; <eschool:eschoolAttribute attribute="projecttype"/> &gt; <eschool:eschoolAttribute attribute="coursename"/>
                    </tr>
                  </table>
                  <br>
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top">
                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
                          <tr>
                            <td>成绩查询</td>
                          </tr>
                        </table>
                        <form action="studentlookupgrade" method="post">
	                        <table width="98%" border="0" cellspacing="2" cellpadding="2" align="center">
	                          <tr>
	                            <td valign="top">&nbsp;</td>
	                            <td valign="top">&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td valign="top">
	                              <input type="hidden" name="studentID" value="">
	                              科目：
	                              <select name="courseID">
	                               <eschool:courseSearchList numItems="0" clause='student'>
	                              	<eschool:items>
	                              <option value='<eschool:courseAttribute attribute="courseID"/>'><eschool:courseAttribute attribute="courseName"/></option>
	                              	</eschool:items>
	                              </eschool:courseSearchList>
	                              </select>
	                            </td>
	                          </tr>
	                          <tr>
	                            <td valign="top">时间从 <input type="text" name="fromDate">至<input type="text" name="toDate"></td>
	                          </tr>
                          <tr>
                            <td valign="top">&nbsp;</td>
                            <td valign="top" align="center"><input type="image" src="<%= request.getContextPath() %>/image/search_3.gif" width="68" height="22" border="0"></td>
                          </tr>
	                      </form>
                        </table>
                        <br>
                        查询结果
                        <br>
                        <br>
                              <eschool:examinationScoreSearchList numItems='20'>
                        <table width="92%" border="0" cellspacing="1" cellpadding="2" align="center" bgcolor="#000000">
                          <tr bgcolor="#CCCCCC">
                            <td>排名</td>
                            <td>科目</td>
                            <td>时间</td>
                            <td>学生姓名</td>
                            <td>成绩</td>
                          </tr>
                          <tr>
                              	<eschool:items>
                            <td bgcolor="#FFFFFF"><eschool:examinationScoreAttribute attribute="grade"/></td>
                            <td bgcolor="#FFFFFF"><eschool:examinationScoreAttribute attribute="courseName"/></td>
                            <td bgcolor="#FFFFFF"><eschool:examinationScoreAttribute attribute="date"/></td>
                            <td bgcolor="#FFFFFF"><eschool:examinationScoreAttribute attribute="studentName"/></td>
                            <td bgcolor="#FFFFFF"><eschool:examinationScoreAttribute attribute="score"/></td>
                              	</eschool:items>
                          </tr>
                        </table>
						<table border="0" cellspacing="1" cellpadding="2" width="160" align="right">
							<tr>
								<eschool:firstForm action='studentlookupgrade'>
									<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_first.gif" border="0" width="12" height="10" value="First"></td>
									<input type=hidden name=studentID value='<%=request.getParameter("studentName")%>'
									<input type=hidden name=courseID value='<%=request.getParameter("courseID")%>'
									<input type=hidden name=fromDate value='<%=request.getParameter("fromDate")%>'
									<input type=hidden name=toDate value='<%=request.getParameter("toDate")%>'
								</eschool:firstForm>

								<eschool:prevForm action='studentlookupgrade'>
									<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_pre.gif" border="0" width="12" height="10" value="Prev"></td>
									<input type=hidden name=studentID value='<%=request.getParameter("studentName")%>'
									<input type=hidden name=courseID value='<%=request.getParameter("courseID")%>'
									<input type=hidden name=fromDate value='<%=request.getParameter("fromDate")%>'
									<input type=hidden name=toDate value='<%=request.getParameter("toDate")%>'
								</eschool:prevForm>
								<td align="center"><eschool:pageForm/></td>

								<eschool:nextForm action='studentlookupgrade'>
									<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_next.gif" border="0" width="12" height="10" value="Next"></td>
									<input type=hidden name=studentID value='<%=request.getParameter("studentName")%>'
									<input type=hidden name=courseID value='<%=request.getParameter("courseID")%>'
									<input type=hidden name=fromDate value='<%=request.getParameter("fromDate")%>'
									<input type=hidden name=toDate value='<%=request.getParameter("toDate")%>'
								</eschool:nextForm>
								
								<eschool:lastForm action='studentlookupgrade'>
									<td width="12"><input type="image" src="<%= request.getContextPath() %>/image/ico_arrow_last.gif" border="0" width="12" height="10" value="Last"></td>
									<input type=hidden name=studentID value='<%=request.getParameter("studentName")%>'
									<input type=hidden name=courseID value='<%=request.getParameter("courseID")%>'
									<input type=hidden name=fromDate value='<%=request.getParameter("fromDate")%>'
									<input type=hidden name=toDate value='<%=request.getParameter("toDate")%>'
								</eschool:lastForm>
							</tr>
						</table>
							  </eschool:examinationScoreSearchList>
                        </td>
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
    </td>
  </tr>
</table>
