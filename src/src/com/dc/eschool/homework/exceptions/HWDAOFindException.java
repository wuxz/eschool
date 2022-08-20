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
 * AccountDAOFinderException is an exception that extends the
 * AccountDAOAppException. This is thrown by the DAOs of the account
 * component when there is no row corresponding to a primary key
 */
public class HWDAOFindException extends HWDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public HWDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public HWDAOFindException ()
    {
        super();
    }

}