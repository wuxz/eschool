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
 * StudentExamContentDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the StudentExamContent
 * component when there is some failure because of user error
 */
public class StudentExamContentDAOAppException extends Exception
{

  /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public StudentExamContentDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public StudentExamContentDAOAppException ()
    {
        super();
    }
}