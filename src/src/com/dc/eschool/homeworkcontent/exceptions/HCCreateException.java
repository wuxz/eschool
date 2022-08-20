package com.dc.eschool.homeworkcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class HCCreateException extends Exception
{
  public HCCreateException()
  {
    super();
  }
  public HCCreateException(String str)
  {
    super(str);
  }
  public HCCreateException(Exception e)
  {
    super("ProjectManagerCreateException :" + e.getMessage());
  }
}