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
 * ScoreStatisticSearchException is an exception that extends the
 * standard Exception. This is thrown by the the ScoreStatistic
 * component when there is some failure because of user error
 */
public class ScoreStatisticSearchException extends Exception
{
  /**
   * Default constructor. Takes no arguments
   */
  public ScoreStatisticSearchException()
  {
    super();
  }
  /**
   * Constructor
   * @param str    a string that explains what the exception condition is
   */
  public ScoreStatisticSearchException(String str)
  {
    super(str);
  }
  public ScoreStatisticSearchException(Exception e)
  {
    super("ScoreStatisticSearchException occured: " + e.getClass().getName()
                                            + "__" + e.getMessage());
  }
}