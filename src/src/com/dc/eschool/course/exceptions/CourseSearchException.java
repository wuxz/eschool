package com.dc.eschool.course.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class CourseSearchException extends Exception
{

  public CourseSearchException()
  {
    super();
  }

  public CourseSearchException(String str)
  {
    super(str);
  }

  public CourseSearchException(Exception e)
  {
    super("CourseSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}