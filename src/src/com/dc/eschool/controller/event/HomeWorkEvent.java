package com.dc.eschool.controller.event;

import java.io.Serializable;
import com.dc.eschool.homework.model.HomeWorkModel;

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
 * the EJB Controller that a change needs to be made in the HomeWork
 * and HomeWorkMgr model data.
 */
public class HomeWorkEvent extends MainEventSupport {
    public static final int CREATE_HOMEWORK = 0;
    public static final int DELETE_HOMEWORK = 1;
    public static final int UPDATE_HOMEWORK = 2;
    public static final int REFRESH_HOMEWORK = 3;

    private int actionType;
    private HomeWorkModel hm=new HomeWorkModel();

    public HomeWorkEvent() {}

    public int getActionType() {
	return actionType;
    }
    public String getEventName() {
	return "java:comp/env/event/HomeWorkEvent";
    }
    public void setActionType(int actionType)
    {
      this.actionType = actionType;
    }
    public void setHm(HomeWorkModel hm)
    {
      this.hm = hm;
    }
    public HomeWorkModel getHm()
    {
      return hm;
    }
}
