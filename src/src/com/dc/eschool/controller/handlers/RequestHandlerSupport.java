package com.dc.eschool.controller.handlers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.dc.eschool.controller.event.MainEvent;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public abstract class RequestHandlerSupport implements RequestHandler
{
  protected ServletContext context;

  public void setServletContext(ServletContext context)
  {
    this.context = context;
  }

  public void doStart(HttpServletRequest request) {}

  public void doEnd(HttpServletRequest request) {}
}