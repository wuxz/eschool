package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class CMSearchException extends Exception
{

  public CMSearchException()
  {
    super();
  }
  public CMSearchException(String str)
  {
    super(str);
  }
  public CMSearchException(Exception e)
  {
    super("CMSearchException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}