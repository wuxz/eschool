package com.dc.eschool.homework.exceptions;

import java.lang.RuntimeException;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class HWDAOSysException extends RuntimeException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public HWDAOSysException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public HWDAOSysException ()
    {
        super();
    }
}