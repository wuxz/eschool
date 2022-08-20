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
 * ExaminationScoreSearchException is an exception that extends the
 * standard Exception. This is thrown by the the ExaminationScore
 * component when there is some failure because of user error
 */
public class ExaminationScoreSearchException extends Exception
{
  /**
   * Default constructor. Takes no arguments
   */
  public ExaminationScoreSearchException()
  {
    super();
  }
  /**
   * Constructor
   * @param str    a string that explains what the exception condition is
   */
  public ExaminationScoreSearchException(String str)
  {
    super(str);
  }
  public ExaminationScoreSearchException(Exception e)
  {
    super("ExaminationScoreSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}