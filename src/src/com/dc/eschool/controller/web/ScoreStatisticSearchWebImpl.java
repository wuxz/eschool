package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.scorestatistic.dao.ScoreStatisticMgrDAO;
import com.dc.eschool.scorestatistic.mgrbean.*;
import com.dc.eschool.scorestatistic.model.ScoreStatisticModel;

import com.dc.eschool.scorestatistic.ejb.*;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.scorestatistic.exceptions.*;

/**
 * Title: Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:   DC
 * @author wangshui
 * @version 1.0
 */

public class ScoreStatisticSearchWebImpl implements Serializable
{
  private ScoreStatisticSLHome home = null;

  public ScoreStatisticSearchWebImpl()
  {

  }
  public ListChunk searchScoreStatistic(String clause, int startindex, int count)
  {
	ListChunk lc = null;
	try
	{
	  lc = getManager().searchScoreStatistic(clause, startindex, count);

	}
	catch(Exception e)
	{
	  String str = e.getMessage();
	  Debug.println(str);
	}

	return lc;

  }

  public ListChunk searchScoreStatisticProject(String projectID, int startindex, int count, boolean isAsc)
  {
	ListChunk lc = null;
	try
	{
	  lc = getManager().searchScoreStatisticProject(projectID, startindex, count, isAsc);

	}
	catch(Exception e)
	{
	  String str = e.getMessage();
	  Debug.println(str);
	}

	return lc;

  }

  public ScoreStatisticModel getScoreStatistic(String scoreStatisticId){
	ScoreStatisticModel sm=null;
	try
	{

		sm=getManager().getScoreStatistic(scoreStatisticId);

	} catch(Exception se)
	{
	  System.out.println("getScoreStatistic():" + se.getMessage());
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
  private ScoreStatisticSL getManager() throws ControllerException
  {
	ScoreStatisticSL remote = null;
	try
	{
	  if(home == null)
		home = EJBUtil.getScoreStatisticSLHome();

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