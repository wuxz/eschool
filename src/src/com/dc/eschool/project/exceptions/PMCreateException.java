package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class PMCreateException extends Exception
{

  public PMCreateException()
  {
    super();
  }
  public PMCreateException(String str)
  {
    super(str);
  }
  public PMCreateException(Exception e)
  {
    super("ProjectManagerCreateException :" + e.getMessage());
  }
}