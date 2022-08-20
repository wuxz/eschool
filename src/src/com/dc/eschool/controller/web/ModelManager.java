package com.dc.eschool.controller.web;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.exception.*;
import com.dc.eschool.controller.ejb.*;
import com.dc.eschool.users.mgrbean.UsersSL;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * This interface provides a convenient set of methods for the
 * web tier components to access all the model objects.
 * This class also insures that only one copy of the  model objects
 * are created for web tier access by placing a reference to the
 * model objects in the session.
 * @author Eric
 */
public class ModelManager extends ModelUpdateNotifier
{
	private ServletContext context;
	private HttpSession session;
	private EJBControllerSF ecEjb = null;
	private UsersSL usersSL=null;
	private EJBControllerWebImpl ecwi = null;
	private ProjectSearchWebImpl pswi = null;
	private UsersSearchWebImpl uswi = null;
	private HomeWorkContentSearchWebImpl hmcswi = null;
	private ContentSearchWebImpl cswi = null;

  public ModelManager()
  {
  }

  public void init(ServletContext context, HttpSession session)
  {
		Debug.println("init modelmanager...");
		this.session = session;
		this.context = context;
		//insert webimplements objects initial here.
		getProjectSearch();
		getUsersSearch();
		getUsersWebImpl();
		getUsersSL();
		getEClassSearch();
		getSubjectSearch();
		getAnswerItemSearch();
		getTestResultItemSearch();
		getCourseSearch();
		getCourseStudentSearch();
		getContentSearch();
		getHomeWorkContentSearch();
		getESchoolWebImpl();
		getExaminationScoreSearch();
		getScoreStatisticSearch();
		getSchoolResourceSearch();

  }

	public void setECWI(EJBControllerWebImpl ecwi)
	{
	this.ecwi = ecwi;
	}
	public EJBControllerSF getECEJB() throws ControllerException
	{
	if (ecEjb == null)
	{
		try
		{
		ecEjb = EJBUtil.getEJBCtrlHome().create();

		}
		catch (CreateException ce)
		{
		throw new ControllerException("createException: "+ce.getMessage());
		}
		catch (RemoteException re)
		{
		throw new ControllerException("RemoteException: "+re.getMessage());
		}
		catch (javax.naming.NamingException ne)
		{
		 throw new ControllerException("NamingException: "+ne.getMessage());
		}
	}
	return ecEjb;
	}

	public ProjectSearchWebImpl getProjectSearch()
	{
	  ProjectSearchWebImpl search = (ProjectSearchWebImpl)
					context.getAttribute(WebKeys.SearchModelKey);
	  if(search == null)
	  {
	search = new ProjectSearchWebImpl();
	context.setAttribute(WebKeys.SearchModelKey,search);
	  }
	  return search;
	}

	public UsersSL getUsersSL()
	{
	if (usersSL == null)
	{
		try
		{
		usersSL = EJBUtil.getUsersSLHome().create();

		}
		catch (Exception ce)
		{
		System.out.println("createException: "+ce.getMessage());
		}
	}
	return usersSL;
	}

	public UsersSearchWebImpl getUsersSearch()
	{
	  UsersSearchWebImpl user_search = (UsersSearchWebImpl)
					context.getAttribute(WebKeys.UsersSearchKey);
	  if(user_search == null)
	  {
	user_search = new UsersSearchWebImpl();
	context.setAttribute(WebKeys.UsersSearchKey,user_search);
	  }
	  return user_search;
	}

	public UsersWebImpl getUsersWebImpl()
	{
	  UsersWebImpl user = (UsersWebImpl)
					session.getAttribute(WebKeys.UsersWebKey);
	  if(user== null)
	  {
	user= new UsersWebImpl(this);
	session.setAttribute(WebKeys.UsersWebKey,user);
	  }
	  return user;
	}

		public ESchoolWebImpl getESchoolWebImpl()
	{
	  ESchoolWebImpl eschool = (ESchoolWebImpl)
					session.getAttribute(WebKeys.ESchoolWebKey);
	  if(eschool== null)
	  {
			eschool= new ESchoolWebImpl(this);
			session.setAttribute(WebKeys.ESchoolWebKey,eschool);
	  }
	  return eschool;
	}

	public EClassSearchWebImpl getEClassSearch()
	{
	  EClassSearchWebImpl search = (EClassSearchWebImpl)
					context.getAttribute(WebKeys.EClassSearchKey);
	  if(search == null)
	  {
	search = new EClassSearchWebImpl();
	context.setAttribute(WebKeys.EClassSearchKey,search);
	  }
	  return search;
	}

	public SubjectSearchWebImpl getSubjectSearch()
	{
	  SubjectSearchWebImpl search = (SubjectSearchWebImpl)
					context.getAttribute(WebKeys.SubjectSearchKey);
	  if(search == null)
	  {
			  search = new SubjectSearchWebImpl();
				  context.setAttribute(WebKeys.SubjectSearchKey,search);
	  }
	  return search;
	}

		public AnswerItemSearchWebImpl getAnswerItemSearch()
	{
	  AnswerItemSearchWebImpl search = (AnswerItemSearchWebImpl)
					context.getAttribute(WebKeys.AnswerItemSearchKey);
	  if(search == null)
	  {
			  search = new AnswerItemSearchWebImpl();
				  context.setAttribute(WebKeys.AnswerItemSearchKey,search);
	  }
	  return search;
	}

		public TestResultItemSearchWebImpl getTestResultItemSearch()
	{
	  TestResultItemSearchWebImpl search = (TestResultItemSearchWebImpl)
					context.getAttribute(WebKeys.TestResultItemSearchKey);
	  if(search == null)
	  {
			  search = new TestResultItemSearchWebImpl();
				  context.setAttribute(WebKeys.TestResultItemSearchKey,search);
	  }
	  return search;
	}

	public CourseSearchWebImpl getCourseSearch()
	{
	  CourseSearchWebImpl search = (CourseSearchWebImpl)
					context.getAttribute(WebKeys.CourseSearchKey);
	  if(search == null)
	  {
	search = new CourseSearchWebImpl();
	context.setAttribute(WebKeys.CourseSearchKey,search);
	  }
	  return search;
	}

	public CSSearchWebImpl getCourseStudentSearch()
	{
	  CSSearchWebImpl search = (CSSearchWebImpl)
					context.getAttribute(WebKeys.CourseStudentSearchKey);
	  if(search == null)
	  {
	search = new CSSearchWebImpl();
	context.setAttribute(WebKeys.CourseStudentSearchKey,search);
	  }
	  return search;
	}
	public HomeWorkContentSearchWebImpl getHomeWorkContentSearch()
	{
	  HomeWorkContentSearchWebImpl search = (HomeWorkContentSearchWebImpl)
					context.getAttribute(WebKeys.HomeWorkContentSearchKey);
	  if(search == null)
	  {
		search = new HomeWorkContentSearchWebImpl();
		context.setAttribute(WebKeys.HomeWorkContentSearchKey,search);
	  }
	  return search;
	}

	public ContentSearchWebImpl getContentSearch()
	{
	  ContentSearchWebImpl search = (ContentSearchWebImpl)
					context.getAttribute(WebKeys.ContentSearchModelKey);
	  if(search == null)
	  {
		search = new ContentSearchWebImpl();
		context.setAttribute(WebKeys.ContentSearchModelKey,search);
	  }
	  return search;
	}

	public ExaminationScoreSearchWebImpl getExaminationScoreSearch()
	{
	  ExaminationScoreSearchWebImpl search = (ExaminationScoreSearchWebImpl)
					context.getAttribute(WebKeys.ExaminationScoreSearchKey);
	  if(search == null)
	  {
		search = new ExaminationScoreSearchWebImpl();
		context.setAttribute(WebKeys.ExaminationScoreSearchKey,search);
	  }
	  return search;
	}

	public SchoolResourceSearchWebImpl getSchoolResourceSearch()
	{
	  SchoolResourceSearchWebImpl search = (SchoolResourceSearchWebImpl)
					context.getAttribute(WebKeys.SchoolResourceSearchKey);
	  if(search == null)
	  {
		search = new SchoolResourceSearchWebImpl();
		context.setAttribute(WebKeys.SchoolResourceSearchKey,search);
	  }
	  return search;
	}

	public ScoreStatisticSearchWebImpl getScoreStatisticSearch()
	{
	  ScoreStatisticSearchWebImpl search = (ScoreStatisticSearchWebImpl)
					context.getAttribute(WebKeys.ScoreStatisticSearchKey);
	  if(search == null)
	  {
		search = new ScoreStatisticSearchWebImpl();
		context.setAttribute(WebKeys.ScoreStatisticSearchKey,search);
	  }
	  return search;
	}
}