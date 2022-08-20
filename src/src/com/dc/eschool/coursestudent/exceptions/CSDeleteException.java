package com.dc.eschool.coursestudent.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class CSDeleteException extends Exception
{

  public CSDeleteException()
  {
    super();
  }

  public CSDeleteException(String str)
  {
    super(str);
  }

  public CSDeleteException(Exception e)
  {
    super("CSDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}