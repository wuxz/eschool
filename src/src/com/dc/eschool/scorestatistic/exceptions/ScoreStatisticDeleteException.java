package com.dc.eschool.scorestatistic.exceptions;

/**
 * Title:Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:   DC
 * @author wangshui
 * @version 1.0
 */

public class ScoreStatisticDeleteException extends Exception
{
/**
 * Default constructor. Takes no arguments
 */
  public ScoreStatisticDeleteException()
  {
    super();
  }

/**
 * Constructor
 * @param str    a string that explains what the exception condition is
 */
  public ScoreStatisticDeleteException(String str)
  {
    super(str);
  }
  public ScoreStatisticDeleteException(Exception e)
  {
    super("ScoreStatisticDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}