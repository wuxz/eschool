<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<%
  String actionM="updatetestresultitem";
  /**if (request.getParameter("schoolResourceID")==null)
  {
    actionM="createtestresultitem";
  }else{
    actionM="updatetestresultitem";
  }
  */
%>
<SCRIPT LANGUAGE=javascript>
    <!--

    function onsaveclick()
    {
    	if (window.fm1.all("size") == null || window.fm1.size.value < 2)
    	{
    		window.alert("没有题目供批阅");
    		
    		return false;
    	}
    	
    	return true;
    }

      //-->

</SCRIPT>
<form action="validatenewtestresultitem" method="post"  name="fm1" id="fm1" onsubmit="return onsaveclick();">
<input type="hidden" name="action" value="<%=actionM%>">
<input type="hidden" name="projectId" value="<%=request.getParameter("projectId")%>">
<input type="hidden" name="type" value="<%=request.getParameter("type")%>">
<input type="hidden" name="contentID" value="<%=request.getParameter("contentID")%>">
<input type="hidden" name="studentID" value="<%=request.getParameter("studentID")%>">
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
                  <eschool:insert parameter="HtmlTopTitle" />
                  <table width="564" border="0" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                      <td background="<%= request.getContextPath() %>/image/line_table_left.gif" width="22">&nbsp;</td>
                      <td bgcolor="#E3EDEC" valign="top"> <br>
                        　　阅卷<br>
                        <table border="0" width="520" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td valign="top" width="100">
                              <table width="100%" border="0" cellspacing="0" cellpadding="2" align="center">
                                <tr bgcolor="#CCCCCC">
                                  <td><p align="center">学生姓名</p></td>
                          		</tr>
                          		<eschool:projectDetails>
	                          		<eschool:contentSearchList numItems='0' clause='<%=request.getParameter("projectId")%>'>
	                          			<eschool:items>
                          		<tr>
                                  <td bgcolor="#FFFFFF"><eschool:contentAttribute attribute="name"/></td>
                          		</tr>
                                  			<eschool:courseStudentAttribute attribute="testapprove"/>
	                          			</eschool:items>
	                          		</eschool:contentSearchList>
                        	  </table>
                       		</td>
                       		<td valign="top" width="450">

                              <table width="98%" border="0" cellspacing="0" cellpadding="0" align="right">
                                <tr>
                                  <td>考试信息</td>
                                </tr>
                                <tr>
                                  <td>试题名称：<eschool:eschoolAttribute attribute="projectname"/> &nbsp;&nbsp;&nbsp;&nbsp;课程：<eschool:eschoolAttribute attribute="coursename"/></td>
                                </tr>
                                <tr>
                                  <td><eschool:projectDetailsAttr attribute="score"/></td>
                                </tr>
                                <tr>
                                  <td>&nbsp;</td>
                                </tr>
	                          </eschool:projectDetails>
                                <tr>
                                  <td>学生答案</td>
                                </tr>
                                <tr>
                                  <td>
                                    <table width="100%" border="0" cellspacing="1" cellpadding="2" align="right" bgcolor="#000000">
                                      <tr bgcolor="#CCCCCC">
                                        <td>题号</td>
                                        <td>正确答案</td>
                                        <td>学生答案</td>
                                        <td>对错</td>
                                      </tr>
                              <eschool:testresultitemSearchList numItems="0" clause='<%=request.getParameter("contentID")%>'>
                                      <% int i =1;%>
                                      <eschool:items>
                                      <tr>
                                        <input type="hidden" name="answerNumber_<%=i%>" value="<eschool:testresultitemAttribute attribute="AnswerNumber"/>">
                                        <input type="hidden" name="testResultItemID_<%=i%>" value="<eschool:testresultitemAttribute attribute="TestResultItemID"/>">
                                        <td bgcolor="#FFFFFF"><%=i%></td>
                                        <td bgcolor="#FFFFFF"><eschool:testresultitemAttribute attribute="RightAnswer"/></td>
                                        <td bgcolor="#FFFFFF"><eschool:testresultitemAttribute attribute="Answer"/></td>
                                        <td bgcolor="#FFFFFF">
                                          <input type="radio" name="right_<%=i%>" value="right" <eschool:testresultitemAttribute attribute="RightChecked"/>>对
                                          <input type="radio" name="right_<%=i%>" value="wrong" <eschool:testresultitemAttribute attribute="WrongChecked"/>>错
                                        </td>
                                      </tr><%i++;%>
                                      </eschool:items>
                                      <input type="hidden" name="size" value="<%=i%>">
                                      <input type="hidden" name="contentid" value="<%=request.getParameter("contentID")%>">
                              </eschool:testresultitemSearchList>
                                    </table>
                                  </td>
                                  <tr>
                                    <td align="right">
                                      <input type=image src="<%= request.getContextPath() %>/image/btn_save.gif" width="77" height="31" border="0">
                                    </td>
                                  </tr>
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
    </td>
  </tr>
</table>
</form>