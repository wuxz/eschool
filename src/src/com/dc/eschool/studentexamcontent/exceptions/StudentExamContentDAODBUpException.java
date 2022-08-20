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
 * StudentExamContentDAODBUpException is an exception that extends the
 * StudentExamContentDAOAppException. This is thrown by the DAOs of the StudentExamContent
 * component when there is an error while writing/updating databases
 */
public class StudentExamContentDAODBUpException extends StudentExamContentDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public StudentExamContentDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public StudentExamContentDAODBUpException ()
    {
        super();
    }
}