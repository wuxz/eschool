package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author kurt xiang
 * @version 1.0
 */

public class ProjectSearchException extends Exception
{

  public ProjectSearchException()
  {
    super();
  }

  public ProjectSearchException(String str)
  {
    super(str);
  }

  public ProjectSearchException(Exception e)
  {
    super("ProjectSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}