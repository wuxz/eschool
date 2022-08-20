package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.schoolresource.dao.SchoolResourceMgrDAO;
import com.dc.eschool.schoolresource.mgrbean.*;
import com.dc.eschool.schoolresource.model.SchoolResourceModel;

import com.dc.eschool.schoolresource.ejb.*;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.schoolresource.exceptions.*;

/**
 * Title: Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:   DC
 * @author wangshui
 * @version 1.0
 */

public class SchoolResourceSearchWebImpl implements Serializable
{
  private SchoolResourceSLHome home = null;

  public SchoolResourceSearchWebImpl()
  {

  }

  public ListChunk searchSchoolResource(String keyword, String subjectID, String time, String role, int startindex, int count)
  {
	ListChunk lc = null;
	try
	{
	  lc = getManager().searchSchoolResource(keyword, subjectID, time, role, startindex, count);

	}
	catch(Exception e)
	{
	  String str = e.getMessage();
	  Debug.println(str);
	}

	return lc;

  }

  public SchoolResourceModel getSchoolResource(String schoolResourceId){
	SchoolResourceModel sm=null;
	try
	{

		sm=getManager().getSchoolResource(schoolResourceId);

	} catch(Exception se)
	{
	  System.out.println("getSchoolResource():" + se.getMessage());
	}
	return sm;
  }
/*
	public Vector isLogin(String loginName,String password){
		Vector userVector=null;
		try {
			userVector=getManager().isLogin(loginName,password);
		} catch(Exception se)
		{
			System.out.println(se.getMessage());
		}
		return userVector;
	}
*/
  private SchoolResourceSL getManager() throws ControllerException
  {
	SchoolResourceSL remote = null;
	try
	{
	  if(home == null)
		home = EJBUtil.getSchoolResourceSLHome();

		remote = home.create();
	}
	catch(javax.naming.NamingException ne)
	{
	  String str = "NamingException while get manager: "+ne.getMessage();
	  Debug.println(str);
	  throw new ControllerException(str);
	}
	catch(javax.ejb.CreateException ce)
	{
	  String str = "CreateException while get manager: "+ce.getMessage();
	  Debug.println(str);
	  throw new ControllerException(str);
	}
	catch(java.rmi.RemoteException re)
	{
	  String str = "RemoteException while get manager: "+re.getMessage();
	  Debug.println(str);
	  throw new ControllerException(str);
	}
	catch(Exception e)
	{
	  String str = "unknown Exception while get manager: "+e.getMessage();
	  Debug.println(str);
	  throw new ControllerException(str);
	}
	return remote;
  }
}