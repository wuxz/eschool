package com.dc.eschool.controller.event;

import com.dc.eschool.project.model.ProjectModel;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

/** This event transfer form data from web tier to ejb.
 *
 */
public class ProjectEvent extends MainEventSupport
{
  public static final int CREATE_PROJECT = 0;
  public static final int DELETE_PROJECT = 1;
  public static final int UPDATE_PROJECT = 2;
  public static final int REFRESH_PROJECT = 3;

  private int actionType;
  private ProjectModel pm = new ProjectModel();

  public ProjectEvent() {}

  public int getActionType()
  {
    return actionType;
  }
  public void setActionType(int actionType)
  {
    this.actionType = actionType;
  }
  public ProjectModel getPm()
  {
    return pm;
  }
  public void setPm(ProjectModel pm)
  {
    this.pm = pm;
  }
  /** the method return environment parameter of projectEvent.
   *  then ejb can handle event and get request from we tier.
   */
  public String getEventName()
  {
    return "java:comp/env/event/ProjectEvent";
  }
}