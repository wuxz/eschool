package com.dc.eschool.homework.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class HWDeleteException extends Exception
{

  public HWDeleteException()
  {
    super();
  }
  public HWDeleteException(String str)
  {
    super(str);
  }
  public HWDeleteException(Exception e)
  {
    super("ProjectManagerDeleteException :" + e.getMessage());
  }
}