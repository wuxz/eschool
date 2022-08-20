package com.dc.eschool.controller.handlers;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;

import com.dc.eschool.controller.event.SchoolResourceEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.ESchoolWebImpl;
import com.dc.eschool.util.*;

//import com.dc.eschool.controller.web.ModelManager;
//import com.dc.eschool.controller.web.UsersWebImpl;

/**
 * Users Handler
 *
*/
public class SchoolResourceHandler extends RequestHandlerSupport
{
	public MainEvent processRequest(HttpServletRequest request) throws ControllerException
	{
		Debug.println("Started creation of an SchoolResource Event");
		String action = request.getParameter("action");
		Debug.println("SchoolResourceHandler (web): action=" + action);
		if (action == null)
		{
			return null;
		}
		else if (action.equals("createSchoolResource"))
		{
			return createNewSchoolresourceEvent(request);
		}
		else if (action.equals("updateSchoolResource"))
		{
			return createUpdateSchoolResourceEvent(request);
		}
		else if (action.equals("deleteSchoolResource"))
		{
			return createDeleteSchoolResourceEvent(request);
		}
		return null;
	}

	private MainEvent createNewSchoolresourceEvent(HttpServletRequest request)
	{
		javax.servlet.http.HttpSession session = request.getSession();
		ESchoolWebImpl esw = (ESchoolWebImpl)session.getAttribute(WebKeys.ESchoolWebKey);
		Integer userID = esw.getUserID();

		SchoolResourceEvent event = new SchoolResourceEvent();

		String name = InterpretSQL.transform(request.getParameter("name").trim());
		String docurl=request.getParameter("docURL").trim();
		String allow=InterpretSQL.transform(request.getParameter("allow").trim());
		Integer subjectid = new Integer(request.getParameter("subjectID").trim());
		String explain=InterpretSQL.transform(request.getParameter("explain").trim());
		event.setInfo(name,docurl,allow,subjectid,explain, userID.toString());

		Debug.println("created update envent");

		return event;
	}

	private MainEvent createUpdateSchoolResourceEvent(HttpServletRequest request)
	{
		javax.servlet.http.HttpSession session = request.getSession();
		ESchoolWebImpl esw = (ESchoolWebImpl)session.getAttribute(WebKeys.ESchoolWebKey);
		Integer userID = esw.getUserID();

		SchoolResourceEvent event = new SchoolResourceEvent();

		Integer schoolResourceid=new Integer(request.getParameter("schoolResourceID").trim());
		String name=InterpretSQL.transform(request.getParameter("name").trim());
		String docurl=request.getParameter("docURL").trim();
		String allow=InterpretSQL.transform(request.getParameter("allow").trim());
		Integer subjectid=new Integer(request.getParameter("subjectID"));
		String explain=InterpretSQL.transform(request.getParameter("explain").trim());

		event.setInfo(schoolResourceid,name,docurl,allow,subjectid,explain, userID.toString());

		return event;
	}
	private MainEvent createDeleteSchoolResourceEvent(HttpServletRequest request)
	{
		SchoolResourceEvent event = new SchoolResourceEvent();

		Integer schoolResourceID=new Integer(request.getParameter("schoolResourceID").trim());
		event.setActionType(1);
		event.setSchoolResourceID(schoolResourceID);
		return event;
	}

}

