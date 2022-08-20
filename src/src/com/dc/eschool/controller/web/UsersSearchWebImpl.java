package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Vector;
import javax.ejb.FinderException;

import com.dc.eschool.controller.exception.DAOException;
import com.dc.eschool.controller.exception.ControllerException;

import com.dc.eschool.users.ejb.*;
import com.dc.eschool.users.exceptions.*;
import com.dc.eschool.users.dao.UsersMgrDAO;
import com.dc.eschool.users.mgrbean.*;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.users.model.LoginInfoModel;

import com.dc.eschool.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class UsersSearchWebImpl implements Serializable
{
    private UsersSLHome home = null;

    public UsersSearchWebImpl()
    {
    }

    public ListChunk searchUsers(String clause,String value, int startindex, int count)
    {
        ListChunk lc = null;
        try
        {
            lc = getManager().searchUsers(clause,value, startindex, count);
        }
        catch(Exception e)
        {
            String str = e.getMessage();
            Debug.println(str);
        }
        return lc;
    }

    public UsersModel getUser(String userId)
    {
        UsersModel um=new UsersModel();
        try
        {
            um=getManager().getUser(userId);
        } catch(Exception se)
        {
            System.out.println("getUser():" + se.getMessage());
        }
        return um;
    }

    public UsersModel isLogin(String loginName,String password)
    {
        UsersModel usersModel=null;
        try
        {
            usersModel=getManager().isLogin(loginName,password);
        } catch(Exception se)
        {
            System.out.println(se.getMessage());
        }
        return usersModel;
    }

    public void changePassword(Integer userID, String password)
    {
	  try
	  {
	      getManager().changePassword(userID,password);
	  } catch(Exception se)
	  {
	      System.out.println("getUser():SQLException while changing password " +
			  se.getMessage());
	  }
    }

    private UsersSL getManager() throws ControllerException
    {
        UsersSL remote = null;
        try
        {
            if(home == null)
              home = EJBUtil.getUsersSLHome();

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