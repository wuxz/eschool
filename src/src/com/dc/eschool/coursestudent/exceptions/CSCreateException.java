package com.dc.eschool.coursestudent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class CSCreateException extends Exception
{

  public CSCreateException()
  {
    super();
  }

  public CSCreateException(String str)
  {
    super(str);
  }

  public CSCreateException(Exception e)
  {
    super("CourseStudentManagerCreateException :" + e.getMessage());
  }
}