package com.dc.eschool.controller.ejb;

import java.rmi.RemoteException;
import java.util.Vector;

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
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SigninStateHandler extends StateHandlerSupport
{
    public void perform(MainEvent event) throws ControllerException
    {
        SigninEvent se = (SigninEvent)event;
        Debug.println("SigninEvent: " + se);

        // validate that the password matches the  one in the account
         UsersModel usersModel = null;
         try
         {
             UsersSLHome home = EJBUtil.getUsersSLHome();
             UsersSL users = home.create();
             usersModel = users.isLogin(se.getLoginName(),se.getPassword());

             if (usersModel.getUserID()!=null) {
                 Debug.println("Password valid for =" + se.getLoginName());
                 machine.setAttribute(WebKeys.UserIDKey,usersModel.getUserID());
                 machine.setAttribute(WebKeys.UserTypeKey,usersModel.getUserType());
                 EJBUtil.getSessionSLHome().create().removeAll();
             } else {
                 Debug.println("Invalide password: for =" + se.getLoginName());
                    System.out.println("password is wrong");
             }
         } catch (RemoteException re)
         {
             System.out.println("Irrecoverable error while trying to verify the the user while signin : " + re);
         } catch (javax.naming.NamingException ne)
         {
             System.out.println("Irrecoverable error while trying to verify the the user while signin : " + ne);
         }catch(CreateException ce)
         {
            System.out.println("Ejb createException:"+ce) ;
         }

    }
}