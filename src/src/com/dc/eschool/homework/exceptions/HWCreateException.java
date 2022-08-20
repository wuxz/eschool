package com.dc.eschool.homework.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class HWCreateException extends Exception
{

  public HWCreateException()
  {
    super();
  }
  public HWCreateException(String str)
  {
    super(str);
  }
  public HWCreateException(Exception e)
  {
    super("ProjectManagerCreateException :" + e.getMessage());
  }
}