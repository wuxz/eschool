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
 * ContentDAOFinderException is an exception that extends the
 * ContentDAOAppException. This is thrown by the DAOs of the Content
 * component when there is no row corresponding to a primary key
 */
public class ContentDAOFindException extends ContentDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ContentDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ContentDAOFindException ()
    {
        super();
    }

}