package com.dc.eschool.subject.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class SubjectCreateException extends Exception
{

  public SubjectCreateException()
  {
    super();
  }

  public SubjectCreateException(String str)
  {
    super(str);
  }

  public SubjectCreateException(Exception e)
  {
    super("ProjectManagerCreateException :" + e.getMessage());
  }
}