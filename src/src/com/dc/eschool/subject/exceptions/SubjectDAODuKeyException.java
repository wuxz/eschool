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
 * SubjectDAODuKeyException is an exception that extends the
 * SubjectDAOAppException. This is thrown by the DAOs of the Subject
 * component when a row is already found with a given primary key
 */
public class SubjectDAODuKeyException extends SubjectDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public SubjectDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public SubjectDAODuKeyException ()
    {
        super();
    }

}