package com.dc.eschool.content.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class CDAOCreateException extends Exception
{

  public CDAOCreateException()
  {
    super();
  }
  public CDAOCreateException(String str)
  {
    super(str);
  }
  public CDAOCreateException(Exception e)
  {
    super("CDAOCreateException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}