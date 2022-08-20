package com.dc.eschool.controller.event;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SigninEvent extends MainEventSupport
{
    private String loginName;
    private String password;

    public SigninEvent(String loginName, String password)
    {
        this.loginName = loginName;
        this.password = password;
    }

    public String getLoginName()
    {
    return loginName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getEventName()
    {
        return "java:comp/env/event/SigninEvent";
    }

    public String toString()
    {
        return "SigninEvent: loginName=" + loginName + ", password=" + password;
    }

}