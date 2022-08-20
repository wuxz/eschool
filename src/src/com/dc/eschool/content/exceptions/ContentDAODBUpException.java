package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * ContentDAODBUpException is an exception that extends the
 * ContentDAOAppException. This is thrown by the DAOs of the Content
 * component when there is an error while writing/updating databases
 */
public class ContentDAODBUpException extends ContentDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ContentDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ContentDAODBUpException ()
    {
        super();
    }
}