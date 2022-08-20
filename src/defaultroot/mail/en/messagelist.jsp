<%-- Message List --%>
<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
<input type="hidden" name="acton" value="message">
<input type="hidden" name="todo" value="">
    <table width="90%" cellpadding="1" cellspacing="1" border="0">
        <tr bgcolor="#000000">
            <td nowrap colspan="6" width="100%"><img src="../images/mailbox_small.png" width="20" height="20" alt="邮箱"><b><font color="#FFFFFF" face="Arial, Helvetica, sans-serif">
                邮件</font></b>
				<font size="-1" face="Arial, Helvetica, sans-serif" color="#ffffff">(按 <%= htmlhelper.getSortCriteriaSelect(folder,sorthandler) %>)排序</font>
			</td>
        </tr>
        <tr bgcolor="#dadada">
            <td nowrap width="1%"><font color="#000000" face="Arial,Helvetica"><b>#</b></font></td>
            <td nowrap width="1%"><font color="#000000" face="Arial,Helvetica"><b>标志</b></font></td>
            <td nowrap width="1%"><font color="#000000" face="Arial,Helvetica"><b>附件</b></font></td>
            <td nowrap width="40%"><font color="#000000" face="Arial,Helvetica"><b>发信人</b></font></td>
            <td nowrap width="40%"><font color="#000000" face="Arial,Helvetica"><b>主题</b></font></td>
            <td nowrap width="17%"><font color="#000000" face="Arial,Helvetica"><b>日期</b></font></td>
        </tr>
        <%-- loop over messages --%>
		<% 	JwmaMessageInfo[] infos=folder.listMessageInfos();
	   		for (int index=0;index<infos.length;index++) {
				JwmaMessageInfo msg=infos[index];
				//Prepare datestring
				String date=htmlhelper.formatDate(msg.getDate());
				if (msg.isSent()) {
					date="<i>"+date+"</i>";
				}
				//Prepare subject
				String msgsubject=msg.getSubject();
				if(msgsubject==null || msgsubject.equals("")) {
					msgsubject="<em>No subject</em>";
				} else {
					//just clean out whitespace
					msgsubject.trim();
				}
		%>
        <tr>
            <td bgcolor="#eeeeee" width="1%">
                <input type="checkbox" name="numbers" value="<%= msg.getMessageNumber() %>">
            </td>
            <td bgcolor="#eeeeee" nowrap width="1%">
				<font face="Arial,Helvetica" size="-1">
                <% if (msg.isNew()) { %>N<% } %> <% if (msg.isRead()) { %>R<% } %>
                <% if (msg.isAnswered()) { %>A<% } %> <% if (msg.isDeleted()) { %>D<% } %>
                </font>
			</td>
            <td bgcolor="#eeeeee" nowrap width="1%">
				<font face="Arial,Helvetica" size="-1">
                <%= ((msg.isMultipart())? "y":"&nbsp;") %>
				</font>
			</td>
            <td bgcolor="#eeeeee" width="40%">
				<font face="Arial,Helvetica" size="-1">
                <%= ((msg.isReceived())? msg.getFrom():("<i>"+msg.getTo()+"</i>")) %>
                </font>
			</td>
            <td bgcolor="#dddddd" width="40%">
				<font face="Arial,Helvetica" size="-1">
                <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=display&amp;number=<%= msg.getMessageNumber() %>">
          		<%= msgsubject %>
				</a>
				</font>
			</td>
            <td bgcolor="#eeeeee" nowrap width="17%">
				<font face="Arial,Helvetica" size="-1"><%= date %></font>
			</td>
        </tr>
        <%	}//for end %> <%-- end loop --%>
        <tr>
            <td colspan="6" bgcolor="#000000" width="100%">
                <input type="button" name="delete" value="删除" onclick="submitDelete(this.form,'message');">
                &nbsp;&nbsp;
                <input type="button" name="move" value="转移到:" onclick="submitMove(this.form,'message');">
                <%= htmlhelper.getDestinationsSelect(store.listMessageMoveTargets()) %>
            </td>
        </tr>
    </table>
</form>

<%-- End Message List --%>