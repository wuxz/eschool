package com.dc.eschool.coursestudent.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */


/**
 * CSAppException is an exception that extends the
 * standard Exception. This is thrown by the the coursestudent
 * component when there is some failure because of user error
 */
public class CSAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CSAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CSAppException ()
    {
        super();
    }

}