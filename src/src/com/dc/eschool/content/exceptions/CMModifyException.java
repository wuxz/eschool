package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class CMModifyException extends Exception
{

  public CMModifyException()
  {
    super();
  }
  public CMModifyException(String str)
  {
    super(str);
  }
  public CMModifyException(Exception e)
  {
    super("CMModifyException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}