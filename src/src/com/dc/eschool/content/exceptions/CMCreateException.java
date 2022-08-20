package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class CMCreateException extends Exception
{

  public CMCreateException()
  {
    super();
  }
  public CMCreateException(String str)
  {
    super(str);
  }
  public CMCreateException(Exception e)
  {
    super("CMCreateException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}