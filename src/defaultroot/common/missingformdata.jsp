<%--
 % Report the missing fields to the user after a form validation
 % fails.
--%>

<%@ page import="com.dc.eschool.controller.web.MissingFormDataException" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>

  <table width="779" border="0" cellspacing="2" cellpadding="2">
    <tr valign="top">
      <td>
	<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
	  <tr>
	    <td width="16"><img src="<%= request.getContextPath() %>/image/content_topleft_corner.gif" width="16" height="17"></td>
	    <td background="<%= request.getContextPath() %>/image/content_top_fill.gif" valign="top"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="16"></td>
	    <td width="17"><img src="<%= request.getContextPath() %>/image/content_topright_corner.gif" width="17" height="17"></td>
	  </tr>
	</table>
	<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
	  <tr>
	    <td background="<%= request.getContextPath() %>/image/content_left_fill.gif" width="16"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="16" height="1"></td>
	    <td bgcolor="#FFFFFF" valign="top">
	      <table width="90%" align="center" height="300">
		<tr>
		  <td width="30%" valign="top">
		    <p>
		      <%
			MissingFormDataException error =
			  (MissingFormDataException)request.getAttribute("missingFormData");
			Collection missingFields = null;
			if (error != null)
			  missingFields = error.getMissingFields();
		      %>

		      <font size="5" color="red"><%= error.getMessage() %></font>
		      <p>
			请返回上页检查以下的属性，并确保正确的输入，然后重新递交：
		      <ul>

		      <%
			if (missingFields != null) {
			  Iterator it = missingFields.iterator();
			  while (it.hasNext()) {
			    String item = (String)it.next();
		      %>

			  <li><%= item %></li>

		      <%   } %>
		      <% } %>

		      </ul>

		      <p><a href="javascript:history.back();">返回上页</a></p>
		  </td>
		</tr>
	      </table>
	    </td>
	    <td background="<%= request.getContextPath() %>/image/content_right_fill.gif" width="17"><input type="image" src="<%= request.getContextPath() %>/image/spacer.gif" width="17" height="1" name="reset"></td>
	  </tr>
	</table>

	<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
	  <tr>
	    <td width="16"><img src="<%= request.getContextPath() %>/image/content_downleft_corner.gif" width="16" height="17"></td>
	    <td background="<%= request.getContextPath() %>/image/content_down_fill.gif"><img src="<%= request.getContextPath() %>/image/spacer.gif" width="1" height="17"></td>
	    <td width="17"><img src="<%= request.getContextPath() %>/image/content_downritht_corner.gif" width="17" height="17"></td>
	  </tr>
	</table>
      </td>
    </tr>
  </table>
