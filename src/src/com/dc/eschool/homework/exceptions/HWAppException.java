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
 * HWAppException is an exception that extends the
 * standard Exception. This is thrown by the the HomeWork
 * component when there is some failure because of user error
 */
public class HWAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public HWAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public HWAppException ()
    {
        super();
    }

}