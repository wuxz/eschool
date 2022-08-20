package com.dc.eschool.controller.web;

import java.beans.Beans;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.eschool.util.*;
import com.dc.eschool.users.model.UsersModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class MainServlet extends HttpServlet
{
	private static final String CONTENT_TYPE = "text/html; charset=GBK";
	private HashMap urlMappings;

	/**Initialize global variables*/
	public void init() throws ServletException
	{
		String requestMappingsURL = null;
		try
		{
			requestMappingsURL = getServletContext().getResource("/WEB-INF/xml/requestmappings.xml").toString();
		} catch (java.net.MalformedURLException ex) {
			Debug.println("ScreenFlowManager: initializing ScreenFlowManager malformed URL exception: " + ex);
		}
		urlMappings = ScreenFlowXmlDAO.loadRequestMappings(requestMappingsURL);
		getServletContext().setAttribute(WebKeys.URLMappingsKey, urlMappings);
		String serverType = null;
		try {
			InitialContext ic = new InitialContext();
			serverType = (String)ic.lookup(JNDINames.SERVER_TYPE);
			getServletContext().setAttribute(WebKeys.ServerTypeKey, serverType);
		} catch (NamingException ex) {
			Debug.println("Server Type not specified in deployment descriptor: using default J2ee Security Adapter");
		}
		getScreenFlowManager();
		getRequestProcessor();
		Debug.println("MainServlet: Initialization complete");
	}

	/**Process the HTTP Get request*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String s_query=request.getQueryString();
		if(s_query==null)
		{
		  s_query="";
		}else
		{
		  s_query="?"+s_query;
		}
		String selectedURL = request.getPathInfo()+s_query;

		// the current tomcat is resetting the outputstream so this is a workaround
		Debug.println("MainServlet: PATH_INFO is "+selectedURL);
		if ((selectedURL != null) && selectedURL.equals("/white")) return;

		HttpSession session = request.getSession();
		ScreenFlowManager screenManager = null;
		ModelManager modelManager= (ModelManager)request.getSession().getAttribute(WebKeys.ModelManagerKey);
		if ( modelManager == null )
		{
			try
			{
				modelManager = (ModelManager) Beans.instantiate(this.getClass().getClassLoader(), "com.dc.eschool.controller.web.ModelManager");
			} catch (Exception exc) {
				throw new ServletException ("Cannot create bean of class ModelManager");
			}
			session.setAttribute(WebKeys.ModelManagerKey, modelManager);
			modelManager.init(getServletContext(), session);
		}
		Debug.println("MainServlet: url " + selectedURL);
		// check if url is protected before processing request

		/**
		 * get the DefinedScreen.
		 */
		URLMapping current = getURLMapping(selectedURL);

		/**
		 * redirecte the url to the correct page
		 */
		if ((current != null) && (current.requiresSignin()))
		{
			UsersWebImpl user = modelManager.getUsersWebImpl();
			if (user.isLoggedIn())
			{
				String enterScreen=getEnterScreen(user,current);
				if (enterScreen==null)
				{
					doProcess(request);
				}else
				{
					session.setAttribute(WebKeys.CurrentScreen, enterScreen);
				}
			} else
			{
				String signinScreen = getScreenFlowManager().getSigninScreen();
				session.setAttribute(WebKeys.CurrentScreen, signinScreen);
				session.setAttribute(WebKeys.SigninTargetURL, selectedURL);
			}
		} else
		{
			doProcess(request);
		}

		/**
		 *Default to the base language or the site.
		 *If a language is found in the session use that template.
		 */
		 Locale locale = JSPUtil.getLocale(request.getSession());
		 getServletConfig().getServletContext().getRequestDispatcher(getScreenFlowManager().getTemplate(locale)).forward(request, response);
	}

	/**Process the HTTP Post request*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

  /**Clean up resources*/
  public void destroy()
  {
  }

  private ScreenFlowManager getScreenFlowManager()
  {
	  ScreenFlowManager screenManager = (ScreenFlowManager)getServletContext().getAttribute(WebKeys.ScreenManagerKey);
	  if (screenManager == null )
	  {
		  Debug.println("MainServlet: Loading screen flow definitions");
		  screenManager = new ScreenFlowManager();
		  screenManager.init(getServletContext());
		  getServletContext().setAttribute(WebKeys.ScreenManagerKey, screenManager);
	  }
	  Debug.println("MainServlet: ....Loaded screen flow definitions");
	  return screenManager;
  }
  private RequestProcessor getRequestProcessor()
  {
	  RequestProcessor rp = (RequestProcessor)getServletContext().getAttribute(WebKeys.RequestProcessorKey);
	  if ( rp == null )
	  {
		  rp = new RequestProcessor();
		  rp.init(getServletContext());
		  getServletContext().setAttribute(WebKeys.RequestProcessorKey, rp);
	  }
	  return rp;
  }
  private URLMapping getURLMapping(String urlPattern)
  {
	  if ((urlMappings != null) && urlMappings.containsKey(urlPattern))
	  {
		  return (URLMapping)urlMappings.get(urlPattern);
	  } else
	  {
		  return null;
	  }
  }
  private void doProcess(HttpServletRequest request) throws ServletException
  {
	  try
	  {
		  getRequestProcessor().processRequest(request);
		  getScreenFlowManager().getNextScreen(request);
	  } catch (Throwable ex)
	  {
		  String className = ex.getClass().getName();
		  String exceptionScreen = getScreenFlowManager().getExceptionScreen(className);
		  Debug.println("MainServlet: target screen is: " + exceptionScreen);

		  /** put the exception in the request*/
		  request.setAttribute("javax.servlet.jsp.jspException", ex);

		  if (exceptionScreen != null)
		  {
			  request.getSession().setAttribute(WebKeys.CurrentScreen, exceptionScreen);
		  } else
		  {
			  /** send to general error screen*/
			  Debug.println("MainServlet: unknown exception: " + className);
			  ex.printStackTrace();
			  throw new ServletException("MainServlet: unknown exception: " + className);
		   }
	   }
	}

	/**
	 * this inner method is judge the user idnetity then redirect to the relation link
	 */
	private String getEnterScreen(UsersWebImpl user,URLMapping current)
	{
		UsersModel usersModel = new UsersModel();
		usersModel = user.getUsersModel();
		String popedomString = current.getPopedom();
		String returnValue=null;

		if(popedomString.length()>0)
		{
			if(usersModel.getUserType().equals(DatabaseSetup.TEACHER_TYPE_VALUE))
			{
				if(!popedomString.substring(1,2).equals("1"))
						  returnValue=getScreenFlowManager().getTeacherHome();
			}else if(usersModel.getUserType().equals(DatabaseSetup.STUDENT_TYPE_VALUE))
			{
				if(!popedomString.substring(2,3).equals("1"))
						  returnValue=getScreenFlowManager().getStudentHome();
			}else if(usersModel.getUserType().equals(DatabaseSetup.ADMIN_TYPE_VALUE))
			{
				if(!popedomString.substring(0,1).equals("1"))
						  returnValue=getScreenFlowManager().getAdminHome();
			}
		}
		return returnValue;
	}
}