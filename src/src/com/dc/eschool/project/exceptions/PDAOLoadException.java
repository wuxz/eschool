package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class PDAOLoadException extends Exception
{

  public PDAOLoadException()
  {
    super();
  }
  public PDAOLoadException(String str)
  {
    super(str);
  }
  public PDAOLoadException(Exception e)
  {
    super("PDAOLoadException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}