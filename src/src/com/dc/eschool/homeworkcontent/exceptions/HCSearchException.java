package com.dc.eschool.homeworkcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class HCSearchException extends Exception
{

  public HCSearchException()
  {
    super();
  }
  public HCSearchException(String str)
  {
    super(str);
  }
  public HCSearchException(Exception e)
  {
    super("HCSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}