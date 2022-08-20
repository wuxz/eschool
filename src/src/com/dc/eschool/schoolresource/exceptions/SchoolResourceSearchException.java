package com.dc.eschool.schoolresource.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

public class SchoolResourceSearchException extends Exception
{
/**
 * Default constructor. Takes no arguments
 */
  public SchoolResourceSearchException()
  {
    super();
  }
/**
 * Constructor
 * @param str    a string that explains what the exception condition is
 */
  public SchoolResourceSearchException(String str)
  {
    super(str);
  }
  public SchoolResourceSearchException(Exception e)
  {
    super("SchoolResourceSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}