package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class ProjectCreateException extends Exception
{

  public ProjectCreateException()
  {
    super();
  }

  public ProjectCreateException(String str)
  {
    super(str);
  }

  public ProjectCreateException(Exception e)
  {
    super("ProjectManagerCreateException :" + e.getMessage());
  }
}