package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.testresultsitem.dao.TestResultsItemMgrDAO;
import com.dc.eschool.testresultsitem.mgrbean.*;
import com.dc.eschool.testresultsitem.model.TestResultsItemModel;

import com.dc.eschool.testresultsitem.ejb.*;
import com.dc.eschool.util.*;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.testresultsitem.exceptions.*;

/**
 * Title: Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:   DC
 * @author wangshui
 * @version 1.0
 */

public class TestResultItemSearchWebImpl implements Serializable
{
  private TestResultsItemSLHome home = null;

  public TestResultItemSearchWebImpl()
  {
  }
  public ListChunk searchTestResultItem(String clause, int startindex, int count) throws ControllerException
  {
    ListChunk lc = null;
    try
    {
      lc = getManager().searchTestResultsItem(clause, startindex, count);
    }
    catch(Exception e)
    {
      String str = e.getMessage();
      throw new ControllerException(str);
    }

    return lc;

  }

  public TestResultsItemModel getTestResultItem(String testResultItemId){
    TestResultsItemModel tm=null;
    try
    {

        tm=getManager().getTestResultsItem(testResultItemId);

    } catch(Exception se)
    {
      System.out.println("getTestResultItem():" + se.getMessage());
    }
    return tm;
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
  private TestResultsItemSL getManager() throws ControllerException
  {
    TestResultsItemSL remote = null;
    try
    {
      if(home == null)
        home = EJBUtil.getTestResultsItemSLHome();
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