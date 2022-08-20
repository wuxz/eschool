package com.dc.eschool.controller.event;

import java.io.Serializable;
import com.dc.eschool.course.model.CourseModel;

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
 * the EJB Controller that a change needs to be made in the Course
 * and CourseMgr model data.
 */
public class CourseEvent extends MainEventSupport
{
    public static final int CREATE_COURSE = 0;
    public static final int DELETE_COURSE = 1;
    public static final int UPDATE_COURSE = 2;
    public static final int REFRESH_COURSE = 3;

    private int actionType;
    private CourseModel cm=new CourseModel();

    public CourseEvent()
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
        return "java:comp/env/event/CourseEvent";
    }
    public void setCm(CourseModel cm)
    {
      this.cm = cm;
    }
    public CourseModel getCm()
    {
      return cm;
    }
}
