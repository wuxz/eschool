package com.dc.eschool.schoolresource.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * SchoolResourceAppException is an exception that extends the
 * standard Exception. This is thrown by the the SchoolResource
 * component when there is some failure because of user error
 */
public class SchoolResourceAppException extends Exception
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public SchoolResourceAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public SchoolResourceAppException ()
    {
        super();
    }
}