package com.dc.eschool.controller.handlers;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.exception.*;
/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class ContentFlowHandler implements FlowHandler
{

  public void doStart(HttpServletRequest request)
  {
  }
  public String processFlow(HttpServletRequest request) throws ControllerException
  {
    String nextScreen = null;
    System.out.println(request.getParameter(com.dc.eschool.util.WebKeys.MissingFormDataKey));
    if(request.getAttribute(com.dc.eschool.util.WebKeys.MissingFormDataKey) != null)
      nextScreen = "2";
    else
      nextScreen = "1";

    return nextScreen;
  }
  public void doEnd(HttpServletRequest request)
  {
  }
}