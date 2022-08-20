package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class CMRemoveException extends Exception
{

  public CMRemoveException()
  {
    super();
  }
  public CMRemoveException(String str)
  {
    super(str);
  }
  public CMRemoveException(Exception e)
  {
    super("CMRemoveException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}