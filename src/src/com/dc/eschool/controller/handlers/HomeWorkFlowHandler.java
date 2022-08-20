package com.dc.eschool.controller.handlers;

import javax.servlet.http.HttpServletRequest;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.util.WebKeys;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class HomeWorkFlowHandler implements FlowHandler
{

  public void doStart(HttpServletRequest request) {}
  public String processFlow(HttpServletRequest request) throws ControllerException
  {
    String nextScreen = null;
    if(request.getAttribute(WebKeys.MissingFormDataKey) != null)
    {
      nextScreen = "2";
    }else if(request.getParameter("action").equals("createhomework"))
    {
      nextScreen = "1";
    }else if(request.getParameter("action").equals("updatehomework"))
    {
      nextScreen = "3";
    }else if(request.getParameter("action").equals("deletehomework"))
    {
      nextScreen = "4";
    }
    return nextScreen;
  }
  public void doEnd(HttpServletRequest request) {}
}