<html>

<head>
<title>New Page 1</title>
  <SCRIPT LANGUAGE=javascript>
    <!--

    function dosubmit(){

          if(window.form1.password.value!=window.form1.confirm.value){
            alert("“密码”和“确认密码不同”，请确认");
            return false;
          }
        }
      //-->
  </SCRIPT>
</head>
<jsp:useBean id="user" scope="page" class="com.dc.eschool.controller.web.UsersSearchWebImpl" />
<%
if(request.getParameter("modify")!=null&&request.getParameter("password").equals(request.getParameter("confirm")))
{
  user.changePassword(new Integer(request.getParameter("userId")),request.getParameter("password"));
  %>
  <SCRIPT LANGUAGE=javascript>
  <!--
      window.close();
  //-->
  </SCRIPT>
  <%
}
%>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<br>
<br>
<form method="POST" action="" ONSUBMIT="dosubmit()" name="form1">
	      <table border="0" width="100%" cellspacing="2" cellpadding="2">
		<tr>
		  <td align="right">密码：</td>
		  <td >
		      <p><input type="password" name="password" size="15"></p>
		  </td>
		</tr>
		<tr>
		  <td  align="right">确认密码：</td>
		  <td><input type="password" name="confirm" size="15"></td>
		</tr>
		<tr>
		  <td align="right"></td>
		  <td>
		  <input type="image" src="<%= request.getContextPath() %>/image/save_2.gif" width="68" height="22" name="submit" boder="0">
		  <input type="hidden" value="issubmit" name="modify">
		  </td>
		</tr>
	      </table>
</form>
</body>

</html>