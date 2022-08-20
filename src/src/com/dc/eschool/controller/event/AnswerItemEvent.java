package com.dc.eschool.controller.event;

import java.io.Serializable;
import com.dc.eschool.answeritem.model.AnswerItemModel;

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
 * the EJB Controller that a change needs to be made in the AnswerItem
 * and AnswerItemMgr model data.
 */
public class AnswerItemEvent extends MainEventSupport
{
    public static final int CREATE_ANSWERITEM = 0;
    public static final int DELETE_ANSWERITEM = 1;
    public static final int UPDATE_ANSWERITEM = 2;
    public static final int REFRESH_ANSWERITEM = 3;

    private int actionType;
    private AnswerItemModel am=new AnswerItemModel();

    public AnswerItemEvent()
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
        return "java:comp/env/event/AnswerItemEvent";
    }
    public void setAm(AnswerItemModel am)
    {
      this.am = am;
    }
    public AnswerItemModel getAm()
    {
      return am;
    }
}
