package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.examinationscore.dao.ExaminationScoreMgrDAO;
import com.dc.eschool.examinationscore.mgrbean.*;
import com.dc.eschool.examinationscore.model.ExaminationScoreModel;

import com.dc.eschool.examinationscore.ejb.*;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.examinationscore.exceptions.*;

/**
 * Title: Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:   DC
 * @author wangshui
 * @version 1.0
 */

public class ExaminationScoreSearchWebImpl implements Serializable
{
  private ExaminationScoreSLHome home = null;

  public ExaminationScoreSearchWebImpl()
  {
  }
  public ListChunk searchExaminationScore(String clause, int startindex, int count) throws ControllerException
  {
    ListChunk lc = null;
    try
    {
      lc = getManager().searchExaminationScore(clause, startindex, count);
    }
    catch(Exception e)
    {
      String str = e.getMessage();
      throw new ControllerException(str);
    }

    return lc;

  }

  public ExaminationScoreModel getExaminationScore(String examinationScoreId){
    ExaminationScoreModel em=null;
    try
    {

        em=getManager().getExaminationScore(examinationScoreId);

    } catch(Exception se)
    {
      System.out.println("getExaminationScore():" + se.getMessage());
    }
    return em;
  }

  private ExaminationScoreSL getManager() throws ControllerException
  {
    ExaminationScoreSL remote = null;
    try
    {
      if(home == null)
        home = EJBUtil.getExaminationScoreSLHome();
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