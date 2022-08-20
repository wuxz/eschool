package com.dc.eschool.course.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class CourseDeleteException extends Exception
{

  public CourseDeleteException()
  {
    super();
  }

  public CourseDeleteException(String str)
  {
    super(str);
  }

  public CourseDeleteException(Exception e)
  {
    super("CourseDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}