package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.users.model.UsersModel;
import com.dc.eschool.controller.web.UsersWebImpl;
import com.dc.eschool.controller.web.ModelManager;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.Debug;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SigninFlowHandler implements FlowHandler
{
    public void doStart(HttpServletRequest request)
    {
    }

    public String processFlow(HttpServletRequest request) throws ControllerException
    {
        Debug.println("SigninFlowHandler:processRequest");
        String nextScreen = null;
        ModelManager mm = (ModelManager)request.getSession().getAttribute(WebKeys.ModelManagerKey);
        UsersWebImpl userWeb = mm.getUsersWebImpl();
        UsersModel usersModel=null;
        usersModel=userWeb.getUsersModel();
        if (usersModel.getUserID()!=null)
        {
            String targetScreen = (String)request.getSession().getAttribute(WebKeys.SigninTargetURL);
            if (targetScreen != null)
            {
              return "TARGET_URL";
            }else
            {
              if(usersModel.getUserType().equals("管理员"))
              {
                return nextScreen = "1";
              }else if(usersModel.getUserType().equals("教师")){
                return nextScreen = "2";
              }else
              {
                return nextScreen = "3";
              }
            }
        } else
        {
            return nextScreen = "4";
        }
    }

    public void doEnd(HttpServletRequest request)
    {
    }
}
