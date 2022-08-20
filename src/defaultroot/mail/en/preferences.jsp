<%@ page session="true" import="dtw.webmail.model.*" %>

<%-- Ensure authenticated & valid session --%>
<%
	if(session.isNew()
       || session.getValue("jwma.session")==null
       || session.getValue("jwma.session.authenticated")==null) {
    		response.sendRedirect(response.encodeRedirectUrl("login.jsp"));
			return;
    }
%>
<%-- Prepare references for use in the page --%>
<%
	JwmaHtmlHelper htmlhelper=(JwmaHtmlHelper) session.getValue("jwma.htmlhelper");
	JwmaInboxInfo inbox=(JwmaInboxInfo) session.getValue("jwma.inboxinfo");
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
	JwmaError error=null;
    Object o=session.getValue("jwma.error");
	if (o!=null) {
		error=(JwmaError) o;
    }
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="Pragma" content="no-cache">
    <title>WebMail</title>
</head>

<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">

<%-- Header & Menu --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	    <td height="36" width="81%"> <img src="../images/inbox.png" alt="�ռ���" width="36" height="36" align="middle">
            <font face="Arial,Helvetica" size="-1"><b><a href="<%= htmlhelper.getFolderDisplayAction(inbox) %>">�ռ���</a></b>
            (<em><%= inbox.getNewMessageCount() %> new </em>/<%= inbox.getMessageCount()%>)
            </font> </td>
        <td height="36" width="19%"><img src="../images/logo.png" width="195" height="36" align="right" alt="WebMail"><a name="top"></a></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="3">
    <tr bgcolor="#000000">
        <td width="9%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=redirect&amp;view=main"><img src="../images/main_small.png" width="20" height="20" border="0" alt="������ҳ"></a></td>
        <td width="3%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose"><img src="../images/compose_small.png" width="20" height="20" border="0" alt="д��"></a></td>
        <td width="10%" align="center"><a href="addressbook.jsp"><img src="../images/addresses_small.png" width="20" height="20" border="0" alt="��ַ��"></a></td>
        <td width="7%" align="center"><img src="../images/settings_small.png" width="20" height="20" border="0" alt="����"></td>
        <td width="6%" align="center"><a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout"><img src="../images/logout_small.png" width="21" height="20" border="0" alt="�ǳ�"></a></td>
        <td width="65%" bgcolor="#000000">&nbsp;</td>
    </tr>
    <tr bgcolor="#dadada">
        <td width="9%" align="center" nowrap> <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=redirect&amp;view=main">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b>������ҳ</b></font>
            </a> </td>
        <td width="3%" align="center" nowrap> <a href="<%= htmlhelper.getControllerUrl() %>?acton=message&amp;todo=compose">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b>д��</b></font>
            </a> </td>
        <td width="10%" align="center" nowrap> <a href="addressbook.jsp"> <font face="Arial, Helvetica, sans-serif" size="-1"><b>��ַ��</b></font>
            </a> </td>
        <td width="7%" align="center" bgcolor="#dadada" nowrap> <font face="Arial, Helvetica, sans-serif" size="-1"><b>����</b></font>
        </td>
        <td width="6%" align="center" nowrap> <a href="<%= htmlhelper.getControllerUrl() %>?acton=session&amp;todo=logout">
            <font face="Arial, Helvetica, sans-serif" size="-1"><b>�ǳ�</b></font>
            </a> </td>
        <td width="65%" nowrap><font color="#00000"></font></td>
    </tr>
</table>
<%-- End Header & Menu --%>
<%-- Display inlined error --%>
<% if (error!=null && !error.isDisplayed()) { %>
	<font size="+1" color="#ff0000"><b><%= error.getDescription(true) %></b></font><br>
<% } %>
<%-- End inlined error --%>

			<form method="post" action="<%= htmlhelper.getControllerUrl() %>">
				<input type="hidden" name="acton" value="preferences">
				<input type="hidden" name="todo" value="update">


    <table border="0" width="90%" cellspacing="1" cellpadding="1">
        <tr>
            <td colspan=2 bgcolor="#000000" align="left"><b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF">Personal
                Data </font></b></td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>��
                </b></td>
            <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
                <input type="text" name="firstname" size="40" maxlength="100" value="<%= prefs.getFirstname() %>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>��</b></td>
            <td width="80%" bgcolor="#eeeeee" align="left" valign="top">
                <input type="text" name="lastname" size="40" maxlength="100" value="<%= prefs.getLastname() %>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>Ĭ��ǩ����</b></td>
            <td width="80%" bgcolor="#eeeeee" align="left" valign="top">
                <textarea name="signature" cols="40" rows="5"><%= prefs.getSignature() %></textarea>
            </td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>����ǩ����</b></td>
            <td width="80%" bgcolor="#eeeeee" align="left" valign="top">
                <textarea name="altsignature" rows="5" cols="40"><%= prefs.getAltSignature() %></textarea>
            </td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd" height="20"><b>������</b></td>
            <td width="80%" bgcolor="#eeeeee" align="left" valign="top" height="20">
                <input type="text" name="from" size="40" maxlength="100" value="<%= prefs.getFrom() %>">
            </td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd" height="20"><b>�ظ���</b></td>
            <td width="80%" bgcolor="#eeeeee" align="left" valign="top" height="20">
                <input type="text" name="replyto" size="40" maxlength="100" value="<%= prefs.getReplyTo() %>">
            </td>
        </tr>
    </table>
  <br>

    <table border="0" width="90%" cellspacing="1" cellpadding="1">
        <tr>
            <td colspan=2 bgcolor="#000000" align="left"><b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF">ϵͳ����</font></b></td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>����</b></td>
            <td width="80%" align="left" valign="top" bgcolor="#eeeeee"> <%= htmlhelper.getLanguageSelect(prefs) %>
                <font face="Arial, Helvetica, sans-serif"><br>
                </font>*<font size="-2" face="Arial, Helvetica, sans-serif">�������ڴ�����������ʾ���ԡ�
                </font></td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>Url-����</b></td>
            <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
                <input type="checkbox" name="urlsensitive"  value="true" <%= ((prefs.isUrlSensitive())? "checked":"") %>>
                <font face="Arial, Helvetica, sans-serif">��Ч<br>
                </font>*<font size="-2" face="Arial, Helvetica, sans-serif">�ʼ��е�URL�����Ե�����롣 </font></td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>ʹ���Զ�ǩ��</b></td>
            <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
                <input type="checkbox" name="autosigning"  value="true" <%= ((prefs.isAutoSigning())? "checked":"") %>>
                <font face="Arial, Helvetica, sans-serif">��Ч<br>
                </font>*<font size="-2" face="Arial, Helvetica, sans-serif">�������ʼ�ĩβ���Ĭ��ǩ����</font></td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>����ԭ�ʼ�</td>
            <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
                <p>
                    <input type="checkbox" name="autoquote"  value="true" <%= ((prefs.isAutoQuote())? "checked":"") %>>
                    <font face="Arial, Helvetica, sans-serif">��Ч<br>
                    </font>*<font size="-2" face="Arial, Helvetica, sans-serif">�ظ��ʼ�ʱ����ԭ�ʼ���</font></p>
            </td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>�ظ��ַ����</b></td>
            <td width="80%" bgcolor="#eeeeee">
                <p>
                    <input type="text" name="quotechar" size="10" maxlength="1" value="<%= prefs.getQuoteChar() %>">
                    <br>
                    *<font size="-2" face="Arial, Helvetica, sans-serif">���лظ��ż��еĻظ����ַ���</font></p>
            </td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd">
                <p><b>���淢�͵��ʼ����ѷ��ʼ���</b></p>
            </td>
            <td width="80%" bgcolor="#eeeeee">
                <input type="checkbox" name="autoarchivesent" value="true" <%= ((prefs.isAutoArchiveSent())? "checked":"") %>>
                <font face="Arial, Helvetica, sans-serif">��Ч</font><br>
                *<font size="-2" face="Arial, Helvetica, sans-serif">�����Է����ʼ����ѷ��ʼ���</font></td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>������</b></td>
            <td width="80%" bgcolor="#eeeeee">
                <input type="text" name="sentmailarchive" size="40" maxlength="100" value="<%= prefs.getSentMailArchive() %>">
                <br>
                * <font size="-2" face="Arial, Helvetica, sans-serif"> ����ʼ��佫��Ϊ�����䡣</font></td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>�Զ��ƶ����Ķ��ʼ�</b></td>
            <td width="80%" bgcolor="#eeeeee">
                <input type="checkbox" name="automoveread"  value="true" <%= ((prefs.isAutoMoveRead())? "checked":"") %>>
                <font face="Arial, Helvetica, sans-serif">��Ч</font><br>
                * <font size="-2" face="Arial, Helvetica, sans-serif">�Զ����ƶ����Ķ����ʼ��������Ķ��ʼ��䡱</font></td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>���Ķ��ʼ���</b></td>
            <td width="80%" bgcolor="#eeeeee">
                <input type="text" name="readmailarchive" size="40" maxlength="100" value="<%= prefs.getReadMailArchive() %>">
                <br>
                * <font size="-2" face="Arial, Helvetica, sans-serif"> �Ѿ��Ķ������ʼ��洢���ļ��С�</font></td>
        </tr>
        <tr>
            <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>�Զ����������</b></td>
            <td width="80%" bgcolor="#eeeeee">
                <input type="checkbox" name="autoempty" value="true" <%= ((prefs.isAutoEmpty())? "checked":"") %>>
                <font face="Arial, Helvetica, sans-serif">��Ч</font><br>
                *<font size="-2" face="Arial, Helvetica, sans-serif">�ڵǳ����Զ������������</font></td>
        </tr>
    </table>
  <br>
  <table border="0" width="90%" cellspacing="0" cellpadding="0">
    <tr>
      <td height="15" align="left" valign="top" width="25%">&nbsp; </td>
      <td height="15" align="right" valign="top" width="75%" nowrap>
                    <input type="reset" name="reset" value="ȡ��">
        <input type="submit" name="submit" value="�޸�">
      </td>
    </tr>
  </table>
</form>


<%-- Footer --%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr bgcolor="#000000" valign="bottom">
        <td> <a href="#top"><img src="../images/up_small.png" width="15" height="15" align="right" border="0" alt="��ҳ��"></a>
            <font size="-2" face="Arial, Helvetica, sans-serif" color="#FFFFFF">&copy;
            2001 </font> </td>
  </tr>
</table>
<%-- End Footer --%>
</body>
</html>
