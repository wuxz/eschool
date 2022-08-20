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
 * This is the base class for all events used by the application.
 * Currently this class only implements Serializable to ensure that
 * all events may be sent the the EJB container via RMI-IIOP.
 */
public interface MainEvent  extends Serializable
{
    public String getEventName();
}