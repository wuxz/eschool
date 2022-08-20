package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;

import com.dc.eschool.subject.ejb.*;
import com.dc.eschool.subject.exceptions.*;
import com.dc.eschool.subject.dao.SubjectMgrDAO;
import com.dc.eschool.subject.mgrbean.*;
import com.dc.eschool.subject.model.SubjectModel;

import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SubjectSearchWebImpl implements Serializable
{
  private SubjectSLHome home = null;

  public SubjectSearchWebImpl()
  {

  }
  public ListChunk searchSubject(String clause, int startindex, int count)
  {
    ListChunk lc = null;
    try
    {
      lc = getManager().searchSubject(clause, startindex, count);

    }
    catch(Exception e)
    {
      String str = e.getMessage();
      Debug.println(str);
    }

    return lc;

  }

  public SubjectModel getSubject(String subjectId){
    SubjectModel um= new SubjectModel();
    try
    {
        um=getManager().getSubject(subjectId);
    } catch(Exception se)
    {
      System.out.println("getSubject():" + se.getMessage());
    }
    return um;
  }

  private SubjectSL getManager() throws ControllerException
  {
    SubjectSL remote = null;
    try
    {
      if(home == null)
        home = EJBUtil.getSubjectSLHome();
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