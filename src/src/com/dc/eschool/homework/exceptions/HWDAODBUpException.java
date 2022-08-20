package com.dc.eschool.homework.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * HWDAODBUpException is an exception that extends the
 * HWDAOAppException. This is thrown by the DAOs of the account
 * component when there is an error while writing/updating databases
 */
public class HWDAODBUpException extends HWDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public HWDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public HWDAODBUpException ()
    {
        super();
    }
}