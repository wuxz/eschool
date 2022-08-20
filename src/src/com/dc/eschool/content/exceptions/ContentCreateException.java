package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric
 * @version 1.0
 */

public class ContentCreateException extends Exception
{

  public ContentCreateException()
  {
    super();
  }

  public ContentCreateException(String str)
  {
    super(str);
  }

  public ContentCreateException(Exception e)
  {
    super("ProjectManagerCreateException :" + e.getMessage());
  }
}