package com.dc.eschool.controller.web;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class Screen implements java.io.Serializable
{
    private String name;
    private HashMap parameters;

    public Screen(String name, HashMap parameters)
    {
        this.name = name;
        this.parameters = parameters;
    }

    public HashMap getParameters()
    {
        return parameters;
    }

    public Parameter getParameter(String key)
    {
        return (Parameter)parameters.get(key);
    }

    public String toString()
    {
        return "[Screen: name=" + name + ", parameters=" + parameters + "]";
    }
}

