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
 * CourseAppException is an exception that extends the
 * standard Exception. This is thrown by the the course
 * component when there is some failure because of course error
 */
public class CourseAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public CourseAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public CourseAppException ()
    {
        super();
    }

}