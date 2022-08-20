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
 * CSDAODBUpException is an exception that extends the
 * CSDAOAppException. This is thrown by the DAOs of the coursestudent
 * component when there is an error while writing/updating databases
 */
public class CSDAODBUpException extends CSDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CSDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CSDAODBUpException ()
    {
        super();
    }
}