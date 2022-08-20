<table width="150" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr valign="top">
                <td width="11"><img src="<%= request.getContextPath() %>/image/menu_topleft_corner.gif" width="11" height="16"></td>
                <td background="<%= request.getContextPath() %>/image/menu_top_fill.gif"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="16"></td>
                <td align="center" width="14"><img src="<%= request.getContextPath() %>/image/menu_topright_corner.gif" width="14" height="16"></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td bgcolor="#FFFFFF" valign="top">
                  <table width="100%" border="0" cellspacing="2" cellpadding="2">
                    <tr>
                      <td align="center"><img src="<%= request.getContextPath() %>/image/leftMenu_title_selfstudy.gif" width="99" height="27"></td>
                    </tr>
                    <tr>
                      <td bgcolor="#f7efc5">浏览</td>
                    </tr>
                    <tr>
                      <td bgcolor="#efefef" align="center"><a href="projectlist?type=test">考试</a><br>
                        <a href="projectlist?type=assignment">作业</a><br>
                        <a href="projectlist?type=selfstudy">自习</a><br>
                        <a href="projectlist?type=review">复习</a><br>
                        <a href="projectlist?type=selftest">自测</a><br>
                        <a href="projectlist?type=listening">听力</a><br>
                        <a href="projectlist?type=exercise">练习</a></td>
                    </tr>
                    <tr>
                      <td bgcolor="#f7efc5">创建</td>
                    </tr>
                    <tr>
                      <td bgcolor="#efefef" align="center"><a href="testmanager?action=createproject&type=test">考试</a><br>
                        <a href="othermanager?action=createproject&type=assignment">作业</a><br>
                        <a href="othermanager?action=createproject&type=selfstudy">自习</a><br>
                        <a href="othermanager?action=createproject&type=review">复习</a><br>
                        <a href="othermanager?action=createproject&type=selftest">自测</a><br>
                        <a href="othermanager?action=createproject&type=listening">听力</a><br>
                        <a href="othermanager?action=createproject&type=exercise">练习</a></td>
                    </tr>
                    <tr>
                      <td bgcolor="#f7efc5">查询</td>
                    </tr>
                    <tr>
                      <td bgcolor="#efefef" align="center">
                        <p><a href="teacherteacherresource?personid=teacher">教师资料</a><br>
                          <a href="teacherschoolresourcelookup">学校资料</a><br>
                          <a href="teacherlookupgrade">成绩</a></p>
                        <p>&nbsp;</p>
                      </td>
                    </tr>
                  </table>
                </td>
                <td background="<%= request.getContextPath() %>/image/menu_rightshadow.gif" width="6"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="6" height="1"></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="11"><img src="<%= request.getContextPath() %>/image/menu_downleft_corner.gif" width="11" height="18"></td>
                <td background="<%= request.getContextPath() %>/image/menu_down_fill.gif"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="16"></td>
                <td width="14"><img src="<%= request.getContextPath() %>/image/menu_downright_corner.gif" width="14" height="18"></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
