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
 * ContentDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the Content
 * component when there is some failure because of user error
 */
public class ContentDAOAppException extends Exception
{

  /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ContentDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ContentDAOAppException ()
    {
        super();
    }
}