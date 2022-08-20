package com.dc.eschool.subject.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * SubjectDAODBUpException is an exception that extends the
 * SubjectDAOAppException. This is thrown by the DAOs of the Subject
 * component when there is an error while writing/updating databases
 */
public class SubjectDAODBUpException extends SubjectDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public SubjectDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public SubjectDAODBUpException ()
    {
        super();
    }
}