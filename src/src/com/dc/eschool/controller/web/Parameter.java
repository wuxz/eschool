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

public class Parameter implements java.io.Serializable
{

    private String key;
    private String value;
    private boolean direct;

    public Parameter(String key, String value, boolean direct)
    {
        this.key = key;
        this.value = value;
        this.direct = direct;
    }

    public boolean isDirect()
    {
        return direct;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    public String toString()
    {
        return "[Parameter: key=" + key + ", value=" + value + ", direct="+ direct + "]";
    }
}