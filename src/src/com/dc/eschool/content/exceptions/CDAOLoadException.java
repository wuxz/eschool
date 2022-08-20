package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class CDAOLoadException extends Exception
{

  public CDAOLoadException()
  {
    super();
  }
  public CDAOLoadException(String str)
  {
    super(str);
  }
  public CDAOLoadException(Exception e)
  {
    super("CDAOLoadException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}