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
 * SubjectAppException is an exception that extends the
 * standard Exception. This is thrown by the the Subject
 * component when there is some failure because of user error
 */
public class SubjectAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public SubjectAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public SubjectAppException ()
    {
        super();
    }

}