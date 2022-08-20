package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class CDAORemoveException extends Exception
{

  public CDAORemoveException()
  {
    super();
  }
  public CDAORemoveException(String str)
  {
    super(str);
  }
  public CDAORemoveException(Exception e)
  {
    super("CDAORemoveException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}