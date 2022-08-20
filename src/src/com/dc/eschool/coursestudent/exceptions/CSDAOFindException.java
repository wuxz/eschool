package com.dc.eschool.coursestudent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * CSDAOFinderException is an exception that extends the
 * CSDAOAppException. This is thrown by the DAOs of the coursestudent
 * component when there is no row corresponding to a primary key
 */
public class CSDAOFindException extends CSDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CSDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CSDAOFindException ()
    {
        super();
    }

}