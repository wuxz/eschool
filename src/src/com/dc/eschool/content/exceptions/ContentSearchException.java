package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class ContentSearchException extends Exception
{

  public ContentSearchException()
  {
    super();
  }

  public ContentSearchException(String str)
  {
    super(str);
  }

  public ContentSearchException(Exception e)
  {
    super("ContentSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}