package com.dc.eschool.scorestatistic.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */
/**
 * ScoreStatisticCreateException is an exception that extends the
 * standard Exception. This is thrown by the the ScoreStatistic
 * component when there is some failure because of user error
 */
public class ScoreStatisticCreateException extends Exception
{
  /**
   * Default constructor. Takes no arguments
   */
  public ScoreStatisticCreateException()
  {
    super();
  }
  /**
   * Constructor
   * @param str    a string that explains what the exception condition is
   */
  public ScoreStatisticCreateException(String str)
  {
    super(str);
  }
  public ScoreStatisticCreateException(Exception e)
  {
    super("ScoreStatisticManagerCreateException :" + e.getMessage());
  }
}