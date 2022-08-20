package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */


/**
 * ContentAppException is an exception that extends the
 * standard Exception. This is thrown by the the Content
 * component when there is some failure because of user error
 */
public class ContentAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ContentAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ContentAppException ()
    {
        super();
    }

}