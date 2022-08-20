package com.dc.eschool.studentexamcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */


/**
 * StudentExamContentAppException is an exception that extends the
 * standard Exception. This is thrown by the the StudentExamContent
 * component when there is some failure because of user error
 */
public class StudentExamContentAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public StudentExamContentAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public StudentExamContentAppException ()
    {
        super();
    }

}