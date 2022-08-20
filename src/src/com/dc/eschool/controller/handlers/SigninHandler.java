package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.SigninEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.ModelManager;
import com.dc.eschool.controller.web.UsersWebImpl;

import com.dc.eschool.util.Debug;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;
import com.dc.eschool.util.InterpretSQL;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SigninHandler extends RequestHandlerSupport
{
    public MainEvent processRequest(HttpServletRequest request)
    {
        Debug.println("Signin Handler: processRequest()");
        String loginName =  request.getParameter("loginName");
        String password =  request.getParameter("password");
        ModelManager mm = (ModelManager)request.getSession().getAttribute(WebKeys.ModelManagerKey);
        UsersWebImpl userWeb = mm.getUsersWebImpl();
        mm.getESchoolWebImpl();
        userWeb.setLoginName(InterpretSQL.transform(loginName));
        userWeb.setPassword(InterpretSQL.transform(password));
        return new SigninEvent(loginName, password);
    }

    public void doEnd(HttpServletRequest request)
    {
    }
}