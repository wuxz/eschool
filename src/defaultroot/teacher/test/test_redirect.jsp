<%@ page import="com.dc.eschool.controller.web.ESchoolWebImpl,com.dc.eschool.util.WebKeys" %>
<%
ESchoolWebImpl eschool=(ESchoolWebImpl)session.getAttribute(WebKeys.ESchoolWebKey);

Integer projectID=new Integer(0);
String url="";
String projectStr=request.getParameter("projectId");
if (projectStr==null) projectStr="0";

if (request.getParameter("projectId")==null&&eschool!=null)
{
    if(eschool.getProjectID()!=null)
    {
      projectID=eschool.getProjectID();
    }else
    {
      projectID=new Integer(16);
    }
}else
{
    projectID=new Integer(projectStr);
}

if(projectID.intValue()>0)
{
  if("test".equals(request.getParameter("type")))
  {
    url="testmanager?type="+request.getParameter("type")+"&action=updateproject&projectId="+projectID;
  }else
  {
    url="othermanager?type="+request.getParameter("type")+"&action=updateproject&projectId="+projectID;
  }
}else
{
  if("test".equals(request.getParameter("type")))
  {
    url="testmanager?type="+request.getParameter("type")+"&action=createproject";
  }else
  {
    url="othermanager?type="+request.getParameter("type")+"&action=createproject";
  }
}
%>

<SCRIPT LANGUAGE=javascript>
    window.location="<%=url%>";
</SCRIPT>
