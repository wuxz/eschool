package com.dc.eschool.controller.event;

import java.io.Serializable;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * This event is sent from the web tier to the EJB Controller to notify
 * the EJB Controller that a change needs to be made in the Users
 * and UsersMgr model data.
 */
public class UsersEvent extends MainEventSupport
{
    public static final int CREATE_USER = 0;
    public static final int DELETE_USER = 1;
    public static final int UPDATE_USER = 2;
    public static final int REFRESH_USER = 3;

    private int actionType;
    private com.dc.eschool.users.model.UsersModel um;

    public UsersEvent()
    {
    }

    public int getActionType()
    {
        return actionType;
    }

    public String getEventName()
    {
        return "java:comp/env/event/UsersEvent";
    }
    public void setUm(com.dc.eschool.users.model.UsersModel um)
    {
      this.um = um;
    }
    public com.dc.eschool.users.model.UsersModel getUm()
    {
      return um;
    }
    public void setActionType(int actionType)
    {
      this.actionType = actionType;
    }
}
