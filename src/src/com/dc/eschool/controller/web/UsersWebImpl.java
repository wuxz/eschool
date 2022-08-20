package com.dc.eschool.controller.web;

import java.util.Vector;

import com.dc.eschool.controller.web.ModelManager;
import com.dc.eschool.controller.web.ModelUpdateListener;
import com.dc.eschool.controller.exception.ListenerException;

import com.dc.eschool.users.mgrbean.UsersSL;
import com.dc.eschool.users.model.UsersModel;

import com.dc.eschool.util.JNDINames;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class UsersWebImpl implements ModelUpdateListener,java.io.Serializable
{
    private UsersSL usersSL;
    private UsersModel usersModel=new UsersModel();
    private boolean loggedIn;
    private String loginName;
    private String password;
    private ModelManager mm;

    public UsersWebImpl()
    {
    }

    public UsersWebImpl(ModelManager mm)
    {
        this.mm = mm;
        mm.addListener(JNDINames.USERS_MANAGER_EJBHOME,this);
    }

    public void performUpdate()
    {
        if (usersSL == null)
        {
            usersSL = mm.getUsersSL();
        }
        if (usersSL != null)
        {
            try {
                // check if the user user was ok"
                this.usersModel = usersSL.isLogin(getLoginName(),getPassword());
                if (usersModel != null) loggedIn = true;
            } catch(Exception e) {
                System.out.println("*** UsersWebImpl: preformUpdate caught " + e);
            }
        }
    }

    public void setLoggedIn(boolean loggedIn)
    {
      this.loggedIn = loggedIn;
    }
    public boolean isLoggedIn()
    {
      return loggedIn;
    }
    public void setLoginName(String loginName)
    {
      this.loginName = loginName;
    }
    public String getLoginName()
    {
      return loginName;
    }
    public void setPassword(String password)
    {
      this.password = password;
    }
    public String getPassword()
    {
      return password;
    }
    public UsersModel getUsersModel()
    {
      return usersModel;
    }
}