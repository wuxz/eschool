package com.dc.eschool.studentexamcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

public class StudentExamContentCreateException extends Exception
{

  public StudentExamContentCreateException()
  {
    super();
  }

  public StudentExamContentCreateException(String str)
  {
    super(str);
  }

  public StudentExamContentCreateException(Exception e)
  {
    super("ProjectManagerCreateException :" + e.getMessage());
  }
}