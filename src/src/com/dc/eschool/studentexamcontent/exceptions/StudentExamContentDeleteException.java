package com.dc.eschool.studentexamcontent.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class StudentExamContentDeleteException extends Exception
{

  public StudentExamContentDeleteException()
  {
    super();
  }

  public StudentExamContentDeleteException(String str)
  {
    super(str);
  }

  public StudentExamContentDeleteException(Exception e)
  {
    super("StudentExamContentDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}