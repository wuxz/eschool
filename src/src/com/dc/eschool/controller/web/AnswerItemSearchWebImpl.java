package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.answeritem.dao.AnswerItemMgrDAO;
import com.dc.eschool.answeritem.mgrbean.*;
import com.dc.eschool.answeritem.model.AnswerItemModel;

import com.dc.eschool.answeritem.ejb.*;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.answeritem.exceptions.*;

/**
 * Title:Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:Dc
 * @author wangshui
 * @version 1.0
 */

public class AnswerItemSearchWebImpl implements Serializable
{
  private AnswerItemSLHome home = null;

  public AnswerItemSearchWebImpl()
  {
  }
  public ListChunk searchAnswerItem(String clause, int startindex, int count)
  {
    ListChunk lc = null;
    try
    {
      lc = getManager().searchAnswerItem(clause, startindex, count);
    }
    catch(Exception e)
    {
      String str = e.getMessage();
      Debug.println(str);
    }
    return lc;
  }

  public AnswerItemModel getAnswerItem(String answerItemId)
  {
    AnswerItemModel am=new AnswerItemModel();
    try
    {
        am=getManager().getAnswerItem(answerItemId);
    } catch(Exception se)
    {
      System.out.println("getAnswerItem():" + se.getMessage());
    }
    return am;
  }

  private AnswerItemSL getManager() throws ControllerException
  {
    AnswerItemSL remote = null;
    try
    {
      if(home == null)
        home = EJBUtil.getAnswerItemSLHome();
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