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
 * StudentExamContentDAODuKeyException is an exception that extends the
 * StudentExamContentDAOAppException. This is thrown by the DAOs of the StudentExamContent
 * component when a row is already found with a given primary key
 */
public class StudentExamContentDAODuKeyException extends StudentExamContentDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public StudentExamContentDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public StudentExamContentDAODuKeyException ()
    {
        super();
    }

}