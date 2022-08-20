package com.dc.eschool.studentexamcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

public class StudentExamContentSearchException extends Exception
{

  public StudentExamContentSearchException()
  {
    super();
  }

  public StudentExamContentSearchException(String str)
  {
    super(str);
  }

  public StudentExamContentSearchException(Exception e)
  {
    super("StudentExamContentSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}