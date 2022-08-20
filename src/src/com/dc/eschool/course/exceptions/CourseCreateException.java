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
 * CourseCreateException is an exception that extends the
 * standard Exception. This is thrown by the the course
 * component when there is some failure because of course error
 */
public class CourseCreateException extends Exception
{

  public CourseCreateException()
  {
    super();
  }

  public CourseCreateException(String str)
  {
    super(str);
  }

  public CourseCreateException(Exception e)
  {
    super("ProjectManagerCreateException :" + e.getMessage());
  }
}