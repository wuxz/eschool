package com.dc.eschool.controller.event;

import java.io.Serializable;
import com.dc.eschool.coursestudent.model.CourseStudentModel;

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
 * the EJB Controller that a change needs to be made in the CourseStudent
 * and CourseStudentMgr model data.
 */
public class CourseStudentEvent extends MainEventSupport
{
    public static final int CREATE_COURSESTUDENT = 0;
    public static final int DELETE_COURSESTUDENT = 1;
    public static final int UPDATE_COURSESTUDENT = 2;
    public static final int REFRESH_COURSESTUDENT = 3;

    private int actionType;
    private CourseStudentModel csm=new CourseStudentModel();

    public CourseStudentEvent()
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
        return "java:comp/env/event/CourseStudentEvent";
    }
    public void setCsm(CourseStudentModel csm)
    {
      this.csm = csm;
    }
    public CourseStudentModel getCsm()
    {
      return csm;
    }
}
