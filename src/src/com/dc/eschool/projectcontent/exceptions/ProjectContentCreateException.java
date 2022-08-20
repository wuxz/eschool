package com.dc.eschool.projectcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class ProjectContentCreateException extends Exception
{

  public ProjectContentCreateException()
  {
    super();
  }

  public ProjectContentCreateException(String str)
  {
    super(str);
  }

  public ProjectContentCreateException(Exception e)
  {
    super("ProjectContentManagerCreateException :" + e.getMessage());
  }
}