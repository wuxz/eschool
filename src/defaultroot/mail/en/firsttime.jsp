<%@ page session="true" import="java.text.SimpleDateFormat,dtw.webmail.model.*"%>
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
	JwmaPreferences prefs=(JwmaPreferences) session.getValue("jwma.preferences");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta http-equiv="Pragma" content="no-cache">
	<title>WebMail</title>
</head>
<body bgcolor="#ffffff" link="#666666" vlink="#666666" alink="#FFFFFF">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr align="left" valign="top">
        <td height="19" width="75%"><font size="+1" face="Arial, Helvetica, sans-serif"><b>欢迎使用WebMail</b></font></td>
        <td height="19" width="25%"><img src="../images/logo.png" width="195" height="36" align="right" alt="WebMail"></td>
    </tr>
    <tr align="left" valign="top">
        <td height="20" colspan="2" bgcolor="#000000">&nbsp;</td>
    </tr>
    <tr>
        <td height="364" align="left" valign="top" colspan="2">
            <table width="100%" border="0" cellspacing="1" cellpadding="10">
                <tr>
                    <td width="81%" valign="top">
                        <p><em><font face="Arial, Helvetica, sans-serif" size="+1">
                        因为您第一次使用，希望您填写姓名。<br>
                            您填写的姓名将在您发出的邮件上显示。<br>
                            一旦登录成功，我们仍然建议您设置设置属性，以便适合您个人的需要。</font></em>
                        <form method="post" action="<%= htmlhelper.getControllerUrl() %>">
                            <input type="hidden" name="acton" value="preferences">
                            <input type="hidden" name="todo" value="update">
                            <table border="0" width="90%" cellspacing="1" cellpadding="1">
                                <tr bgcolor="#000000" valign="bottom">
                                    <td colspan=2 align="left" height="23"><b><font face="Arial, Helvetica, sans-serif" color="#FFFFFF"><img src="../images/settings_small.png" width="20" height="20">
                                        个人数据 </font></b></td>
                                </tr>
                                <tr>
                                    <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>名
                                        </b></td>
                                    <td width="80%" align="left" valign="top" bgcolor="#eeeeee">
                                        <input type="text" name="firstname" size="40" maxlength="100" value="<%= prefs.getFirstname() %>">
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%" align="left" valign="top" bgcolor="#dddddd"><b>姓</b></td>
                                    <td width="80%" bgcolor="#eeeeee" align="left" valign="top">
                                        <input type="text" name="lastname" size="40" maxlength="100" value="<%= prefs.getLastname() %>">
                                    </td>
                                </tr>
                            </table>
                            <br>
                            <table border="0" width="90%" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td height="15" align="left" valign="top" width="25%">&nbsp;
                                    </td>
                                    <td height="15" align="right" valign="top" width="75%" nowrap>
                                        <input type="submit" name="submit" value="保存&继续&gt;&gt;">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2" bgcolor="#000000"><font size="-2" face="arial,helvetica" color="#FFFFFF">&copy;
            2001</font></td>
    </tr>
</table>
</body>
</html>