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
 * UDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the users
 * component when there is some failure because of user error
 */
public class UDAOAppException extends Exception
{
  /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public UDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public UDAOAppException ()
    {
        super();
    }
}