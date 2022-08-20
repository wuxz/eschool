package com.dc.eschool.homework.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class HWSearchException extends Exception
{

  public HWSearchException()
  {
    super();
  }
  public HWSearchException(String str)
  {
    super(str);
  }
  public HWSearchException(Exception e)
  {
    super("HWSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}