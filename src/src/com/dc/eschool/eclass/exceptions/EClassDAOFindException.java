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
 * AccountDAOFinderException is an exception that extends the
 * AccountDAOAppException. This is thrown by the DAOs of the eclass
 * component when there is no row corresponding to a primary key
 */
public class EClassDAOFindException extends EClassDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public EClassDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public EClassDAOFindException ()
    {
        super();
    }

}