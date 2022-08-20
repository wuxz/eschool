package com.dc.eschool.schoolresource.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

public class SchoolResourceCreateException extends Exception
{
/**
 * Default constructor. Takes no arguments
 */
  public SchoolResourceCreateException()
  {
    super();
  }
/**
 * Constructor
 * @param str    a string that explains what the exception condition is
 */
  public SchoolResourceCreateException(String str)
  {
    super(str);
  }
  public SchoolResourceCreateException(Exception e)
  {
    super("SchoolResourceManagerCreateException :" + e.getMessage());
  }
}