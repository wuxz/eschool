<%@ page session="false" import="dtw.webmail.JwmaKernel" %>

<%--
 Redirect to main controller to obtain a jwma session, and be redirected
 to the systems default language.
 To obtain the main controller url via an interface would be more
 clean, thus consider this implementation as workaround.
--%>
<%	response.sendRedirect(
		response.encodeRedirectUrl(
			JwmaKernel.getReference().getMainControllerUrl()
		)
	);
%>
