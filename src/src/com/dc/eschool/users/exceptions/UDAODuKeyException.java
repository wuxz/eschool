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
 * UDAODuKeyException is an exception that extends the
 * UDAOAppException. This is thrown by the DAOs of the users
 * component when a row is already found with a given primary key
 */
public class UDAODuKeyException extends UDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public UDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public UDAODuKeyException ()
    {
        super();
    }

}