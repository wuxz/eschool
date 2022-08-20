package com.dc.eschool.subject.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class SubjectSearchException extends Exception
{

  public SubjectSearchException()
  {
    super();
  }

  public SubjectSearchException(String str)
  {
    super(str);
  }

  public SubjectSearchException(Exception e)
  {
    super("SubjectSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}