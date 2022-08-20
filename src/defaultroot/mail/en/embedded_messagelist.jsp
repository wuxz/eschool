<%-- Message List --%>


<table width="100%" cellpadding="1" cellspacing="1" border="0" height="100%" vspace="0" hspace="0">
    <tr bgcolor="#dadada">
    	<td height="25" nowrap colspan="6" width="100%">
			<font color="#000000" face="Arial, Helvetica, sans-serif"><b>消息</b></font>
			<font size="-1" face="Arial, Helvetica, sans-serif" color="#000000">(sorted by <%= htmlhelper.getSortCriteriaSelect(folder,sorthandler) %>)</font>
		</td>
	</tr>
    <tr bgcolor="#dadada">
        <td nowrap height="20" width="1%"> <font color="#000000" face="Arial,Helvetica"><b>#</b></font>
        </td>

        <td nowrap height="20" width="1%"> <font color="#000000" face="Arial,Helvetica"><b>标志</b></font>
        </td>

        <td nowrap height="20" width="1%"> <font color="#000000" face="Arial,Helvetica"><b>附件</b></font>
        </td>

        <td nowrap height="20" width="40%"> <font color="#000000" face="Arial,Helvetica"><b>来自于</b></font>
        </td>

        <td nowrap height="20" width="40%"> <font color="#000000" face="Arial,Helvetica"><b>主题</b></font>
        </td>

        <td nowrap height="20" width="17%"> <font color="#000000" face="Arial,Helvetica"><b>日期</b></font>
        </td>
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
					//clean up whitespace
					msgsubject.trim();
				}
		%>
        <tr>

        <td bgcolor="#eeeeee" height="20" width="1%">
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
        <td bgcolor="#eeeeee" width="40%">
			<font face="Arial,Helvetica" size="-1">
            <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=display&amp;number=<%= msg.getMessageNumber() %>">
            <i><%= msgsubject %></i>
			</a>
			</font>
		</td>

        <td bgcolor="#eeeeee" nowrap width="17%">
			<font face="Arial,Helvetica" size="-1">
            <%= date %>
			</font>
		</td>
		</tr>
        <%	}//for end %>
		<%-- end loop --%>
		<%-- A cell that swallows the rest size --%>
		<tr>
			<td bgcolor="#eeeeee" colspan="6">&nbsp;</td>
		</tr>

    <tr bgcolor="#dadada">
        <td colspan="6" height="25">
            <input type="button" name="delete" value="删除" onclick="submitDelete(this.form,'message');">
                &nbsp;&nbsp;
                <input type="button" name="move" value="移动到:" onclick="submitMove(this.form,'message');">
                <%= htmlhelper.getDestinationsSelect(store.listMessageMoveTargets()) %>
            </td>
        </tr>
    </table>


<%-- End Message List --%>