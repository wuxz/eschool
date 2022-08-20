<%@ page session="true" %>
<%@ page contentType="text/html;charset=gb2312" %>
<%@ include file="incdb.jsp"%>
<%@ page import="com.dc.eschool.controller.web.ESchoolWebImpl,com.dc.eschool.util.WebKeys" %>
<jsp:useBean id="bbs" class="com.dc.eschool.bbs.BbsDAO" scope="page"/>
<%
	String tempSTR,whereTo;
	int bbsid;

	tempSTR=request.getParameter("bbsid");
	if (tempSTR==null)
		bbsid=0;
	else
		bbsid=java.lang.Integer.parseInt(tempSTR);

	ESchoolWebImpl ew = (ESchoolWebImpl)session.getAttribute(WebKeys.ESchoolWebKey);
	if (ew == null || !"¹ÜÀíÔ±".equals(ew.getUserType()))
	{
%>
<jsp:forward page='about.jsp'/>
<%
	}
	else
	{
		bbs.delBbs(bbsid);
		whereTo="bbslist.jsp?method=8&boardid="+request.getParameter("boardid");
%>
<jsp:forward page='<%=whereTo%>'/>
<%
	}
%>