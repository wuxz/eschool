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
 * SubjectDAOFinderException is an exception that extends the
 * SubjectDAOAppException. This is thrown by the DAOs of the Subject
 * component when there is no row corresponding to a primary key
 */
public class SubjectDAOFindException extends SubjectDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public SubjectDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public SubjectDAOFindException ()
    {
        super();
    }

}