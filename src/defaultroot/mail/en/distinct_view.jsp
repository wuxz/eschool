<%-- Distinct Mode --%>

<%-- Display list of mailboxes and folders  --%>
<% if(folder.hasSubfolders() || folder.isType(JwmaFolder.TYPE_FOLDER)) {
		JwmaFolder[] folders=folder.listSubfolders(JwmaFolder.TYPE_FOLDER);
		JwmaFolder[] mailboxes=folder.listSubfolders(JwmaFolder.TYPE_MAILBOX);
		int rowdiff=folders.length-mailboxes.length;
%>
<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
	<input type="hidden" name="acton" value="">
	<input type="hidden" name="todo" value="">
	<table width="90%" cellpadding="1" cellspacing="1" border="0">
        <tr>
			<td bgcolor="#000000" colspan="2"> <img align="left" src="../images/folder_small.png" alt="Folder" width="20" height="20">
                <font face="Arial,Helvetica" size="+1" color="#ffffff"> <b><%= htmlhelper.getPathHierarchyNavigator(store,folder) %></b></font>
            </td>
		</tr>
		<tr>
			<td width="50%" height="100%" bgcolor="#eeeeee" align="left" valign="top">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
					<tr>
						<td height="25" width="100%" bgcolor="#dddddd">
							<font face="Arial,Helvetica"><b>文件夹</b></font>
						</td>
					</tr>
					<%-- loop over folders --%>
					<%	for (int index=0;index<folders.length;index++) { %>
					<tr>
						<td width="100%" height="25" align="left" valign="top" bgcolor="#eeeeee">
							<input type="checkbox" name="paths" value="<%= folders[index].getPath() %>">
							<font face="Arial,Helvetica" size=-1><b>
							<a href="<%= htmlhelper.getFolderDisplayAction(folders[index]) %>"><%= folders[index].getName() %></a>
							</b></font>
						</td>
					</tr>
					<% } %>
					<%-- end loop over folders --%>

					<%-- Correct row difference between folderlist and mailboxlist --%>
					<%
						while(rowdiff<0) {
							rowdiff++;
					%>
					<tr>
						<td height="25" bgcolor="#eeeeee">&nbsp;</td>
					</tr>
					<% } %>
					<%-- End correction --%>
				</table>
			</td>
			<td width="50%" height="100%" bgcolor="#eeeeee" align="left" valign="top">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" align="left">
					<tr>
						<td width="100%" height="25" bgcolor="#dddddd">
							<font face="Arial,Helvetica"><b>邮箱</b></font>
						</td>
					</tr>
					<%-- loop over mailboxes --%>
					<%	for (int index=0;index<mailboxes.length;index++) { %>
					<tr>
						<td width="100%" height="25" align="left" valign="top" bgcolor="#eeeeee">
							<input type="checkbox" name="paths" value="<%= mailboxes[index].getPath() %>">
							<font face="Arial,Helvetica" size=-1><b>
							<a href="<%= htmlhelper.getFolderDisplayAction(mailboxes[index]) %>"><%= mailboxes[index].getName() %></a>
							</b></font>
						</td>
					</tr>
					<% } %>
					<%-- end loop over mailboxes --%>
					<%-- Correct row difference between folderlist and mailboxlist --%>
					<%
						while(rowdiff>0) {
							rowdiff--;
					%>
					<tr>
						<td height="25" bgcolor="#eeeeee">&nbsp;</td>
					</tr>
					<% } %>
					<%-- End correction --%>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" width="100%" height="25" align="left" bgcolor="#000000" nowrap>
				<input type="button" name="delete" value="删除" onclick="submitDelete(this.form,'folder');"> &nbsp;&nbsp;
				<input type="button" name="move" value="移动到:" onclick="submitMove(this.form,'folder');">
				<%= htmlhelper.getDestinationsSelect(store.listFolderMoveTargets()) %>
				&nbsp;&nbsp;
				<input type="button" name="create" value="新建" onclick="submitCreate(this.form);">
				<select name="type">
					<option value="<%= JwmaFolder.TYPE_FOLDER %>" selected>文件夹</option>
					<option value="<%= JwmaFolder.TYPE_MAILBOX %>">邮箱</option>
				</select>
				<input type="text" name="aname" value="" size="20">
			</td>
		</tr>
	</table>
</form>
<%-- Display message list --%>
<% } else { %>
<table width="100%">
    <tr bgcolor="#FFFFFF">
        <td> <font color="#000000"><font face="Arial,Helvetica"><b><img src="../images/folder.png" width="36" height="36" alt="到文件夹" align="middle">
            <%= htmlhelper.getPathHierarchyNavigator(store,folder)%> </b></font></font>
        </td>
    </tr>
</table>
<%
		if(folder.hasMessages()) {
%>
	<%@ include file="messagelist.jsp" %>
<% 		} else { %>
			<i>邮箱为空</i>

<% 		}
	}
%>

<%-- End Distinct Mode --%>