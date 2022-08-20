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
 * HWDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the homework
 * component when there is some failure because of user error
 */
public class HWDAOAppException extends Exception
{

  /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public HWDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public HWDAOAppException ()
    {
        super();
    }
}