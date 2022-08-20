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
 * CourseDAODuKeyException is an exception that extends the
 * CourseDAOAppException. This is thrown by the DAOs of the course
 * component when a row is already found with a given primary key
 */
public class CourseDAODuKeyException extends CourseDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CourseDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CourseDAODuKeyException ()
    {
        super();
    }

}