package com.dc.eschool.controller.event;

import java.io.Serializable;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * This event is sent from the web tier to the EJB Controller to notify
 * the EJB Controller that a user has logged out of the application.
 */
public class SignoutEvent extends MainEventSupport
{

    public SignoutEvent()
    {
    }

    public String toString()
    {
        return "SignoutEvent()";
    }
}