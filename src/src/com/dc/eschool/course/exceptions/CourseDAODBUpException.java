package com.dc.eschool.course.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * CourseDAODBUpException is an exception that extends the
 * CourseDAOAppException. This is thrown by the DAOs of the course
 * component when there is an error while writing/updating databases
 */
public class CourseDAODBUpException extends CourseDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CourseDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CourseDAODBUpException ()
    {
        super();
    }
}