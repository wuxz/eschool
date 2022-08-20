package com.dc.eschool.users.exceptions;

import java.lang.RuntimeException;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class UDAOSysException extends RuntimeException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public UDAOSysException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public UDAOSysException ()
    {
        super();
    }
}