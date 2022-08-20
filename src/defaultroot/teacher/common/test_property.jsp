<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<html>
<head>
  <title>资料属性</title>
</head>
  <SCRIPT LANGUAGE=javascript>
    <!--

    function dosubmit(){

          if(window.form1.info.value==""){
            alert("请填写说明");
            return false;
          }
          if(window.form1.state.value==""){
            alert("请选择状态");
            return false;
          }
        }
      //-->
  </SCRIPT>
</head>
<jsp:useBean id="test" scope="page" class="com.dc.eschool.controller.web.ContentSearchWebImpl" />
<%
com.dc.eschool.controller.web.UsersWebImpl uw = (com.dc.eschool.controller.web.UsersWebImpl)session.getAttribute(com.dc.eschool.util.WebKeys.UsersWebKey);
com.dc.eschool.controller.web.ESchoolWebImpl ew = (com.dc.eschool.controller.web.ESchoolWebImpl)session.getAttribute(com.dc.eschool.util.WebKeys.ESchoolWebKey);

String userID = null;
if (uw != null)
    userID = uw.getUsersModel().getUserID().toString();
System.out.println("userid:" + userID);
System.out.println("contentID:" + request.getParameter("contentID"));

if(request.getParameter("modifytestcontent")!=null)
{
  if (test.changeInfo(ew.getUserType(), userID, new Integer(request.getParameter("contentID")),request.getParameter("info"),request.getParameter("state")))
  {
%>
  <SCRIPT LANGUAGE=javascript>
  <!--
      window.close();
  //-->
  </SCRIPT>
<%
  }
  else
  {
%>
  <SCRIPT LANGUAGE=javascript>
  <!--
      window.alert("对不起，你无权更改不是你创建的资料！");
  //-->
  </SCRIPT>
<%
  }
}
%>
<body bgcolor="#ffffff">
<form method="POST" action="" name="form1">
<table border="0" width="300" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%">
      <p align="center">资料属性</td>
  </tr>
  <tr>
    <td width="100%">
    <eschool:contentDetails>
      <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td width="34%" valign="top">
            <p align="right">说明：</td>
          <td width="66%">
              <p align="left"><textarea rows="3" name="info" cols="22"><eschool:contentDetailsAttr attribute="info"/></textarea></p>

          </td>
        </tr>
        <tr>
          <td width="34%">
            <p align="right">状态：</td>
          <td width="66%"><input type="radio" value="公布" name="state" <eschool:contentDetailsAttr attribute="checked_state_公布"/>>公布&nbsp; <input type="radio" value="不公布" name="state" <eschool:contentDetailsAttr attribute="checked_state_不公布"/>>不公布</td>
        </tr>
        <tr>
          <td width="34%"></td>
          <td width="66%"><input type="image" src="<%= request.getContextPath() %>/image/save_2.gif" width="68" height="22" border="0" name="submit"></td>
        </tr>
      </table>
    </eschool:contentDetails>
    <input type="hidden" name="modifytestcontent" value="modifytestcontent">
    </td>
  </tr>
</table>
</form>


</body>
</html>
