package com.dc.eschool.controller.event;

import com.dc.eschool.content.model.ContentModel;

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
public class ContentEvent extends MainEventSupport
{
  public static final int CREATE_CONTENT = 0;
  public static final int DELETE_CONTENT = 1;
  public static final int UPDATE_CONTENT = 2;
  public static final int REFRESH_CONTENT = 3;
  public static final int CREATE_PROJRCT_CONTENT = 4;

  private int actionType;
  private ContentModel cm= new ContentModel();

  public ContentEvent() {}

  public int getActionType()
  {
    return actionType;
  }
  public void setActionType(int actionType)
  {
    this.actionType = actionType;
  }
  /** the method return environment parameter of contentEvent.
   *  then ejb can handle event and get request from we tier.
   */
  public String getEventName()
  {
    return "java:comp/env/event/ContentEvent";
  }
  public void setCm(ContentModel cm)
  {
    this.cm = cm;
  }
  public ContentModel getCm()
  {
    return cm;
  }
}