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
 * AccountDAOFinderException is an exception that extends the
 * AccountDAOAppException. This is thrown by the DAOs of the users
 * component when there is no row corresponding to a primary key
 */
public class UDAOFindException extends UDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public UDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public UDAOFindException ()
    {
        super();
    }

}