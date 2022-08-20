package com.dc.eschool.projectcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class ProjectContentSearchException extends Exception
{

  public ProjectContentSearchException()
  {
    super();
  }

  public ProjectContentSearchException(String str)
  {
    super(str);
  }

  public ProjectContentSearchException(Exception e)
  {
    super("ProjectContentSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}