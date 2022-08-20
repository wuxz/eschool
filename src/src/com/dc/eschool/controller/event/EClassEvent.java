package com.dc.eschool.controller.event;

import java.io.Serializable;
import com.dc.eschool.eclass.model.EClassModel;

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
 * the EJB Controller that a change needs to be made in the EClass
 * and EClassMgr model data.
 */
public class EClassEvent extends MainEventSupport
{
    public static final int CREATE_ECLASS = 0;
    public static final int DELETE_ECLASS = 1;
    public static final int UPDATE_ECLASS = 2;
    public static final int REFRESH_ECLASS = 3;

    private int actionType;
    private EClassModel ecm = new EClassModel();

    public EClassEvent()
    {
    }

    public int getActionType()
    {
        return actionType;
    }

    public void setActionType(int actionType)
    {
      this.actionType = actionType;
    }
    public String getEventName()
    {
        return "java:comp/env/event/EClassEvent";
    }
    public void setEcm(EClassModel ecm)
    {
      this.ecm = ecm;
    }
    public EClassModel getEcm()
    {
      return ecm;
    }
}
