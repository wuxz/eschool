package com.dc.eschool.eclass.exceptions;

/**
 * Title:        ESchool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */


/**
 * EClassAppException is an exception that extends the
 * standard Exception. This is thrown by the the eclass
 * component when there is some failure because of user error
 */
public class EClassAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public EClassAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public EClassAppException ()
    {
        super();
    }

}