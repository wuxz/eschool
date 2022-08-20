package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class PDAOUpdateException extends Exception
{

  public PDAOUpdateException()
  {
    super();
  }
  public PDAOUpdateException(String str)
  {
    super(str);
  }
  public PDAOUpdateException(Exception e)
  {
    super("PDAOUpdateException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}