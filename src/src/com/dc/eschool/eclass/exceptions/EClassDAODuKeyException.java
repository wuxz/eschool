package com.dc.eschool.eclass.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * EClassDAODuKeyException is an exception that extends the
 * EClassDAOAppException. This is thrown by the DAOs of the eclass
 * component when a row is already found with a given primary key
 */
public class EClassDAODuKeyException extends EClassDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public EClassDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public EClassDAODuKeyException ()
    {
        super();
    }

}