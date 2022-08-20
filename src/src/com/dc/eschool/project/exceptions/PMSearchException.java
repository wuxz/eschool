package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class PMSearchException extends Exception
{

  public PMSearchException()
  {
    super();
  }
  public PMSearchException(String str)
  {
    super(str);
  }
  public PMSearchException(Exception e)
  {
    super("PMSearchException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}