package com.dc.eschool.projectcontent.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric CHEN
 * @version 1.0
 */

public class ProjectContentDeleteException extends Exception
{

  public ProjectContentDeleteException()
  {
    super();
  }

  public ProjectContentDeleteException(String str)
  {
    super(str);
  }

  public ProjectContentDeleteException(Exception e)
  {
    super("ProjectContentDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}