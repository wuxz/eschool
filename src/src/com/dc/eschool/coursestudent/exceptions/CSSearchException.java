package com.dc.eschool.coursestudent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

public class CSSearchException extends Exception
{

  public CSSearchException()
  {
    super();
  }

  public CSSearchException(String str)
  {
    super(str);
  }

  public CSSearchException(Exception e)
  {
    super("CSSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}