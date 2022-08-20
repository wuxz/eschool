package com.dc.eschool.subject.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class SubjectDeleteException extends Exception
{

  public SubjectDeleteException()
  {
    super();
  }

  public SubjectDeleteException(String str)
  {
    super(str);
  }

  public SubjectDeleteException(Exception e)
  {
    super("SubjectDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}