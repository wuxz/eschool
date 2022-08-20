package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.homeworkcontent.dao.HWContentMgrDAO;
import com.dc.eschool.homeworkcontent.mgrbean.*;
import com.dc.eschool.homeworkcontent.model.HWContentModel;

import com.dc.eschool.homeworkcontent.ejb.*;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.homeworkcontent.exceptions.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class HomeWorkContentSearchWebImpl implements Serializable
{
  private HWContentSLHome home = null;

  public HomeWorkContentSearchWebImpl()
  {

  }

  public ListChunk searchHomeWorkContent(String clause, int startindex, int count, String value)
  {
	ListChunk lc = null;
	try
	{
	  lc = getManager().searchHomeWorkContent(clause, startindex, count, value);

	}
	catch(Exception e)
	{
	  String str = e.getMessage();
	  Debug.println(str);
	}

	return lc;

  }

  public int getRestCount(Integer projectID)
  {
      int returnValue=0;
      try
      {
          returnValue=getManager().getRestCount(projectID);
      }catch(Exception e)
      {
        System.out.println(e);
      }
      return returnValue;
  }

  public Vector unHandInStudent(Integer projectID)
  {
      Vector returnValue=new Vector();
      try
      {
          returnValue=getManager().unHandInStudent(projectID);
      }catch(Exception e)
      {
        System.out.println(e);
      }
      return returnValue;
  }
  private HWContentSL getManager() throws ControllerException
  {
	HWContentSL remote = null;
	try
	{
	  if(home == null)
		home = EJBUtil.getHWContentSLHome();
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