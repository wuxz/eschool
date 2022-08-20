package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import javax.ejb.FinderException;

import com.dc.eschool.project.dao.ProjectMgrDAO;
import com.dc.eschool.project.mgrbean.*;

import com.dc.eschool.project.ejb.*;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.project.exceptions.*;


/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class ProjectSearchWebImpl implements Serializable
{
  private ProjectMgrSLHome home = null;

  public ProjectSearchWebImpl()
  {

  }
  public ListChunk searchProjects(String clause, int startindex, int count,String course,String value,String userType)
  {
    ListChunk lc = null;
    try
    {
      lc = getManager().searchProjects(clause, startindex, count,course,value,userType);

    }
    catch(Exception e)
    {
      String str = e.getMessage();
      Debug.println(str);
    }

    return lc;

  }

  private ProjectMgrSL getManager() throws ControllerException
  {
    ProjectMgrSL remote = null;
    try
    {
      if(home == null)
        home = EJBUtil.getPMSLHome();
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
  public com.dc.eschool.project.model.ProjectModel getProjectDetails(String pk)
  {
    com.dc.eschool.project.model.ProjectModel pm = null;
    try
    {
      System.out.println(pk);
      pm = getManager().getProject(pk);
    }
    catch(Exception e)
    {
      Debug.print(e);
    }
    return pm;
  }

}