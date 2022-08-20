package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class PDAOCreateException extends Exception
{

  public PDAOCreateException()
  {
    super();
  }
  public PDAOCreateException(String str)
  {
    super(str);
  }
  public PDAOCreateException(Exception e)
  {
    super("PDAOCreateException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}