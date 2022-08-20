package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      rangersoft
 * @author kurt Xiang
 * @version 1.0
 */

public class PMDeleteException extends Exception
{

  public PMDeleteException()
  {
    super();
  }
  public PMDeleteException(String str)
  {
    super(str);
  }
  public PMDeleteException(Exception e)
  {
    super("PMDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}