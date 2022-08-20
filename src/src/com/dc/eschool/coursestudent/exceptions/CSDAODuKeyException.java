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
 * CSDAODuKeyException is an exception that extends the
 * CSDAOAppException. This is thrown by the DAOs of the coursestudent
 * component when a row is already found with a given primary key
 */
public class CSDAODuKeyException extends CSDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CSDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CSDAODuKeyException ()
    {
        super();
    }

}