package com.dc.eschool.answeritem.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

public class AnswerItemSearchException extends Exception
{
/**
 * Default constructor. Takes no arguments
 */
  public AnswerItemSearchException()
  {
    super();
  }
/**
 * Constructor
 * @param str    a string that explains what the exception condition is
 */
  public AnswerItemSearchException(String str)
  {
    super(str);
  }
  public AnswerItemSearchException(Exception e)
  {
    super("AnswerItemSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}