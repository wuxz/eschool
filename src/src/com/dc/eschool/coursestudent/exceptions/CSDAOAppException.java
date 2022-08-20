package com.dc.eschool.coursestudent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

/**
 * CSDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the coursestudent
 * component when there is some failure because of user error
 */
public class CSDAOAppException extends Exception
{

  /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CSDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CSDAOAppException ()
    {
        super();
    }
}