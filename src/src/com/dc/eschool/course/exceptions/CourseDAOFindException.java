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
 * CourseDAOFinderException is an exception that extends the
 * CourseDAOAppException. This is thrown by the DAOs of the course
 * component when there is no row corresponding to a primary key
 */
public class CourseDAOFindException extends CourseDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CourseDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CourseDAOFindException ()
    {
        super();
    }

}