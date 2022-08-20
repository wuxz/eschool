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
 * StudentExamContentDAOFinderException is an exception that extends the
 * StudentExamContentDAOAppException. This is thrown by the DAOs of the StudentExamContent
 * component when there is no row corresponding to a primary key
 */
public class StudentExamContentDAOFindException extends StudentExamContentDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public StudentExamContentDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public StudentExamContentDAOFindException ()
    {
        super();
    }

}