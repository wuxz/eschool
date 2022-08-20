package com.dc.eschool.controller.handlers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.ejb.RemoveException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.event.SignoutEvent;
import com.dc.eschool.controller.exception.ControllerException;
import com.dc.eschool.controller.web.ModelManager;
import com.dc.eschool.util.Debug;
import com.dc.eschool.util.WebKeys;
import com.dc.eschool.util.JSPUtil;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * SignoutHandler
 * This class removes the ControllerEJB and destroys
 * the current HttpSession.
 *
*/
public class SignoutHandler extends RequestHandlerSupport
{
    public MainEvent processRequest(HttpServletRequest request) throws ControllerException
    {
        Debug.println("Creating Signout Event");
        return new SignoutEvent();
    }

    public void doEnd(HttpServletRequest request)
    {
        ModelManager mm = (ModelManager)request.getSession().getAttribute(WebKeys.ModelManagerKey);
        Debug.println("Signout doEnd");

        try
        {
        //remove the ShoppingClientControllerEJB
         mm.getECEJB().remove();
        } catch (java.rmi.RemoteException rex)
        {
            Debug.println("SignoutHandler error removing ShoppingClientController: " + rex);
        } catch (RemoveException rem)
        {
            Debug.println("SignoutHandler error removing ShoppingClientController: " + rem);
        } catch (ControllerException ce)
        {
            Debug.println("SignoutHandler error removing ShoppingClientController: " + ce);
        }
        request.getSession().invalidate();
        // get new session and put in a new gui controller
        HttpSession validSession = request.getSession(true);
        // put the previous language in the session so the proper signout message is displayed
        mm = new ModelManager();
        request.getSession().setAttribute(WebKeys.ModelManagerKey, mm);
        mm.init(context, validSession);
        request.getSession().setAttribute(WebKeys.ModelManagerKey, mm);
    }
}