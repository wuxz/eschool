package com.dc.eschool.schoolresource.exceptions;

/**
 * Title:Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:   DC
 * @author wangshui
 * @version 1.0
 */

public class SchoolResourceDeleteException extends Exception
{
/**
 * Default constructor. Takes no arguments
 */
  public SchoolResourceDeleteException()
  {
    super();
  }

/**
 * Constructor
 * @param str    a string that explains what the exception condition is
 */
  public SchoolResourceDeleteException(String str)
  {
    super(str);
  }
  public SchoolResourceDeleteException(Exception e)
  {
    super("SchoolResourceDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}