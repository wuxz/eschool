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
 * ContentDAODuKeyException is an exception that extends the
 * ContentDAOAppException. This is thrown by the DAOs of the Content
 * component when a row is already found with a given primary key
 */
public class ContentDAODuKeyException extends ContentDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ContentDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ContentDAODuKeyException ()
    {
        super();
    }

}