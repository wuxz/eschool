package com.dc.eschool.homeworkcontent.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * HCDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the homeworkContent
 * component when there is some failure because of user error
 */
public class HCDAOAppException extends Exception
{
  /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public HCDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public HCDAOAppException ()
    {
        super();
    }
}