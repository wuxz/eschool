package com.dc.eschool.homeworkcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class HCDeleteException extends Exception
{

  public HCDeleteException()
  {
	super();
  }
  public HCDeleteException(String str)
  {
	super(str);
  }
  public HCDeleteException(Exception e)
  {
	super("ProjectManagerDeleteException :" + e.getMessage());
  }
}