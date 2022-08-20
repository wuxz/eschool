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
 * the EJB Controller that a change needs to be made in the Subject
 * and SubjectMgr model data.
 */
public class SubjectEvent extends MainEventSupport
{
    public static final int CREATE_SUBJECT = 0;
    public static final int DELETE_SUBJECT = 1;
    public static final int UPDATE_SUBJECT = 2;
    public static final int REFRESH_SUBJECT = 3;

    private int actionType;
    private com.dc.eschool.subject.model.SubjectModel sm;

    public SubjectEvent()
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
        return "java:comp/env/event/SubjectEvent";
    }
    public void setSm(com.dc.eschool.subject.model.SubjectModel sm)
    {
      this.sm = sm;
    }
    public com.dc.eschool.subject.model.SubjectModel getSm()
    {
      return sm;
    }
}
