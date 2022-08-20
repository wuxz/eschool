package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class PDAORemoveException extends Exception
{

  public PDAORemoveException()
  {
    super();
  }
  public PDAORemoveException(String str)
  {
    super(str);
  }
  public PDAORemoveException(Exception e)
  {
    super("PDAORemoveException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}