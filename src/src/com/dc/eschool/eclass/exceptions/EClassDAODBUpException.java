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
 * EClassDAODBUpException is an exception that extends the
 * EClassDAOAppException. This is thrown by the DAOs of the eclass
 * component when there is an error while writing/updating databases
 */
public class EClassDAODBUpException extends EClassDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public EClassDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public EClassDAODBUpException ()
    {
        super();
    }
}