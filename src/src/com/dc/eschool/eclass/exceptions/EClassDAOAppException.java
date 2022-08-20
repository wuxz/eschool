package com.dc.eschool.eclass.exceptions;

/**
 * Title:        ESchool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * EClassDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the eclass
 * component when there is some failure because of user error
 */
public class EClassDAOAppException extends Exception
{

  /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public EClassDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public EClassDAOAppException ()
    {
        super();
    }
}