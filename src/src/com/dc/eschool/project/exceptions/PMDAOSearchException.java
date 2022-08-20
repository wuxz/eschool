package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class PMDAOSearchException extends Exception
{

  public PMDAOSearchException()
  {
    super();
  }
  public PMDAOSearchException(String str)
  {
    super(str);
  }
  public PMDAOSearchException(Exception e)
  {
    super("PMDAOSearchException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}