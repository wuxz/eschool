package com.dc.eschool.controller.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.dc.eschool.controller.event.*;
import com.dc.eschool.controller.exception.*;
import com.dc.eschool.controller.web.StateMachine;

import com.dc.eschool.users.mgrbean.UsersSL;
import com.dc.eschool.users.mgrbean.UsersSLHome;
import com.dc.eschool.users.model.UsersModel;

import com.dc.eschool.util.*;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class UsersStateHandler extends StateHandlerSupport
{
    public void perform(MainEvent event) throws ControllerException
    {
        UsersEvent ue = (UsersEvent)event;
        switch(ue.getActionType())
        {
          case UsersEvent.CREATE_USER:
          {
              Debug.println("State Handled USERS_CREATE_EVENT");

              try
              {
                  UsersSL users=getManager();
                  Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		  UsersModel um=ue.getUm();
		  um.setCreateBy(userID);
		  um.setLastModifyBy(userID);
                  users.insertUsers(um);
              }catch(com.dc.eschool.users.exceptions.UCreateException uce)
              {
                  System.out.println(uce);
              } catch (java.rmi.RemoteException re)
              {
                  throw new EJBException("Irrecoverable error while creating users: " + re);
              }
          }break;
          case UsersEvent.UPDATE_USER:
          {
              Debug.println("State Handled USERS_UPDATE_EVENT");

              try
              {
                  UsersSL users=getManager();
                  Integer userID = (Integer)machine.getAttribute(WebKeys.UserIDKey);
		  UsersModel um=ue.getUm();
		  um.setLastModifyBy(userID);
                  users.updateUser(um);
              }catch(com.dc.eschool.users.exceptions.UCreateException uce)
              {
                  System.out.println(uce);
              } catch (java.rmi.RemoteException re)
              {
                  throw new EJBException("Irrecoverable error while creating users: " + re);
              }
          }break;
        }
    }

    private UsersSL getManager()
    {
        UsersSL users=null;
        UsersSLHome home =null;

        try
        {
            home = EJBUtil.getUsersSLHome();
            users = home.create();
        } catch (javax.naming.NamingException ce)
        {
            throw new EJBException("Irrecoverable error creating Users: " + ce);
        } catch (java.rmi.RemoteException re)
        {
            throw new EJBException("Irrecoverable error while creating users: " + re);
        } catch (CreateException ce)
        {
            System.out.println("Unable to create a new account for " + ce + " at this time");
        }
        return users;
    }
}