package com.dc.eschool.answeritem.exceptions;

/**
 * Title:Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:Dc
 * @author wangshui
 * @version 1.0
 */

/**
 * AnswerItemDeleteException is an exception that extends the
 * standard Exception. This is thrown by the the AnswerItem
 * component when there is some failure because of answeritem error
 */
public class AnswerItemDeleteException extends Exception
{
/**
 * Default constructor. Takes no arguments
 */
  public AnswerItemDeleteException()
  {
    super();
  }
/**
 * Constructor
 * @param str    a string that explains what the exception condition is
 */
  public AnswerItemDeleteException(String str)
  {
    super(str);
  }
  public AnswerItemDeleteException(Exception e)
  {
    super("AnswerItemDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}