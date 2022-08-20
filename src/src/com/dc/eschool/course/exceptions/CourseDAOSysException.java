package com.dc.eschool.course.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class CourseDAOSysException extends RuntimeException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CourseDAOSysException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CourseDAOSysException ()
    {
        super();
    }
}