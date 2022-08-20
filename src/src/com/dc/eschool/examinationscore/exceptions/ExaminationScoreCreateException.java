package com.dc.eschool.examinationscore.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */

/**
 * ExaminationScoreCreateException is an exception that extends the
 * standard Exception. This is thrown by the the ExaminationScore
 * component when there is some failure because of user error
 */
public class ExaminationScoreCreateException extends Exception
{
  /**
   * Default constructor. Takes no arguments
   */
  public ExaminationScoreCreateException()
  {
    super();
  }
  /**
   * Constructor
   * @param str    a string that explains what the exception condition is
   */
  public ExaminationScoreCreateException(String str)
  {
    super(str);
  }
  public ExaminationScoreCreateException(Exception e)
  {
    super("ExaminationScoreManagerCreateException :" + e.getMessage());
  }
}