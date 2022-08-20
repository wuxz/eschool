package com.dc.eschool.answeritem.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

public class AnswerItemCreateException extends Exception
{
/**
 * Default constructor. Takes no arguments
 */
  public AnswerItemCreateException()
  {
    super();
  }
/**
 * Constructor
 * @param str    a string that explains what the exception condition is
 */  
  public AnswerItemCreateException(String str)
  {
    super(str);
  }
  public AnswerItemCreateException(Exception e)
  {
    super("AnswerItemManagerCreateException :" + e.getMessage());
  }
}