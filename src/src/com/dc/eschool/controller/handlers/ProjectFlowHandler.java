package com.dc.eschool.controller.handlers;

import javax.servlet.http.HttpServletRequest;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class ProjectFlowHandler implements FlowHandler
{

  public void doStart(HttpServletRequest request)
  {
  }

  public String processFlow(HttpServletRequest request) throws com.dc.eschool.controller.exception.ControllerException
  {
    String nextScreen = null;
    if(request.getAttribute(com.dc.eschool.util.WebKeys.MissingFormDataKey) != null)
      nextScreen = "2";
    else
      nextScreen = "1";

    return nextScreen;
  }
  public void doEnd(HttpServletRequest request) {}
}