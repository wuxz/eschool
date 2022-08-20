package com.dc.eschool.project.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Kurt Xiang
 * @version 1.0
 */

public class ProjectDeleteException extends Exception
{

  public ProjectDeleteException()
  {
    super();
  }

  public ProjectDeleteException(String str)
  {
    super(str);
  }

  public ProjectDeleteException(Exception e)
  {
    super("ProjectDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}