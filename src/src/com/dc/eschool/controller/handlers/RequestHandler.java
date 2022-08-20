package com.dc.eschool.controller.handlers;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.exception.ControllerException;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public interface RequestHandler extends Serializable
{
    public void setServletContext(ServletContext context);
    public void doStart(HttpServletRequest request);
    public MainEvent processRequest(HttpServletRequest request) throws ControllerException;
    public void doEnd(HttpServletRequest request);
}