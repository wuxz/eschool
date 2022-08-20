package com.dc.eschool.users.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * UDAODBUpException is an exception that extends the
 * UDAOAppException. This is thrown by the DAOs of the users
 * component when there is an error while writing/updating databases
 */
public class UDAODBUpException extends UDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public UDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public UDAODBUpException ()
    {
        super();
    }
}