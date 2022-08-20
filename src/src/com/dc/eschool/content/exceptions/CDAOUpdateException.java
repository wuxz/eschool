package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class CDAOUpdateException extends Exception
{

  public CDAOUpdateException()
  {
    super();
  }
  public CDAOUpdateException(String str)
  {
    super(str);
  }
  public CDAOUpdateException(Exception e)
  {
    super("CDAOUpdateException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}