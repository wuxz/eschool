package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.rmi.RemoteException;

import com.dc.eschool.util.*;
import com.dc.eschool.controller.event.MainEvent;
import com.dc.eschool.controller.exception.*;
import com.dc.eschool.controller.handlers.RequestHandler;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class RequestProcessor implements java.io.Serializable
{
    private ServletContext context;
    private HashMap urlMappings;

    /** Empty constructor for use by the JSP engine. */
    public RequestProcessor() {}


    public void init(ServletContext context)
    {
        this.context = context;
        urlMappings = (HashMap)context.getAttribute(WebKeys.URLMappingsKey);
    }


    private URLMapping getURLMapping(String urlPattern)
    {
        if ((urlMappings != null) && urlMappings.containsKey(urlPattern))
        {
            return (URLMapping)urlMappings.get(urlPattern);
        } else
        {
            return null;
        }
    }


    /**
    * This method is the core of the RequestProcessor. It receives all requests
    *  and generates the necessary events.
    */
    public void processRequest(HttpServletRequest request) throws ControllerException,ListenerException
    {
        MainEvent event = null;
        String selectedUrl = request.getPathInfo();
        ModelManager mm = (ModelManager)request.getSession().getAttribute(WebKeys.ModelManagerKey);

        EJBControllerWebImpl ecwi = (EJBControllerWebImpl)request.getSession().getAttribute(WebKeys.WebControllerKey);
        if (ecwi == null)
        {
            ecwi = new EJBControllerWebImpl(request.getSession());
            mm.setECWI(ecwi);
            request.getSession().setAttribute(WebKeys.WebControllerKey, ecwi);
        }
       RequestHandler handler = getHandler(selectedUrl);
       if (handler != null)
       {
           handler.setServletContext(context);
           handler.doStart(request);
           event = handler.processRequest(request);
           if (event != null)
           {
                   Collection updatedModelList = ecwi.handleEvent(event);
                   mm.notifyListeners(updatedModelList);
           }
           handler.doEnd(request);
        }
    }

    /**
     * This method load the necessary RequestHandler class necessary to process a the
     * request for the specified URL.
     */
    private RequestHandler getHandler(String selectedUrl)
    {
        RequestHandler handler = null;
        URLMapping urlMapping = getURLMapping(selectedUrl);

        String requestProcessorString = null;
        if (urlMapping != null)
        {
            requestProcessorString = urlMapping.getRequestHandler();
            if (urlMapping.useRequestHandler())
            {
                try
                {
                    handler = (RequestHandler)getClass().getClassLoader().loadClass(requestProcessorString).newInstance();
                } catch (Exception ex)
                {
                   Debug.println("RequestProcessor caught loading handler: " + ex);
                }
            }
        }
        return handler;
    }

}
