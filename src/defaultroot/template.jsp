<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="eschool" %>
<html>
  <head>
    <title>
      <eschool:insert parameter="HtmlTitle" />
    </title>
    <eschool:insert parameter="HtmlScript" />
    <%@include file="/common/layout.jsp" %>
  </head>

<body bgcolor="#32009f" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
  <eschool:insert parameter="HtmlBanner" />
  <eschool:insert parameter="HtmlBody" />
  <eschool:insert parameter="HtmlFooter" />
</body>
</html>
