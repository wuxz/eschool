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

public class CourseStudentFlowHandler implements FlowHandler
{

  public void doStart(HttpServletRequest request)
  {
  }

  public String processFlow(HttpServletRequest request) throws ControllerException
  {
    String nextScreen = null;
    if(request.getAttribute(WebKeys.MissingFormDataKey) != null)
      nextScreen = "2";
    else
      nextScreen = "1";

    return nextScreen;
  }

  public void doEnd(HttpServletRequest request)
  {
  }
}