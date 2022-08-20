package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric Chen
 * @version 1.0
 */


public class URLMapping implements java.io.Serializable
{
    private String url;
    private boolean useRequestHandler = false;
    private boolean useFlowHandler = false;
    private String flowHandler = null;
    private String requestHandler = null;
    private HashMap resultMappings;
    private String screen;
    private boolean requiresSignin = false;
    private String popedom = "111";
    public URLMapping(String url, String screen)
    {
        this.url = url;
        this.screen = screen;
    }

    public URLMapping(String url,
                      String screen,
                      boolean useRequestHandler,
                      boolean useFlowHandler,
                      String requestHandler,
                      String flowHandler,
                      HashMap resultMappings,
                      boolean requiresSignin,
                      String popedom)
    {
        this.url = url;
        this.flowHandler = flowHandler;
        this.requestHandler = requestHandler;
        this.useRequestHandler = useRequestHandler;
        this.useFlowHandler = useFlowHandler ;
        this.resultMappings = resultMappings;
        this.screen = screen;
        this.requiresSignin = requiresSignin;
        this.popedom = popedom;
    }

    public boolean requiresSignin()
    {
        return requiresSignin;
    }

    public boolean useFlowHandler()
    {
        return useFlowHandler;
    }

    public boolean useRequestHandler()
    {
        return useRequestHandler;
    }

    public String getRequestHandler()
    {
        return requestHandler;
    }

    public String getFlowHandler()
    {
        return flowHandler;
    }

    public String getScreen()
    {
        return screen;
    }

    public String getResultScreen(String key)
    {
        if (resultMappings != null)
        {
            return (String)resultMappings.get(key);
        } else
        {
            return null;
        }
    }

    public HashMap getResultMappings()
    {
        return resultMappings;
    }

    public String toString()
    {
        return "[URLMapping: url=" + url +
                ", useRequestHandler=" + useRequestHandler +
                ", useFlowHandler=" + useFlowHandler +
                ", requestHandler=" + requestHandler +
                ", flowHandler=" + flowHandler +
                ", resultMappings=" + resultMappings +
                "]";
    }
    public String getPopedom()
    {
      return popedom;
    }
}
