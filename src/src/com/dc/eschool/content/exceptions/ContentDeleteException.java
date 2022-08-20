package com.dc.eschool.content.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class ContentDeleteException extends Exception
{

  public ContentDeleteException()
  {
    super();
  }

  public ContentDeleteException(String str)
  {
    super(str);
  }

  public ContentDeleteException(Exception e)
  {
    super("ContentDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}