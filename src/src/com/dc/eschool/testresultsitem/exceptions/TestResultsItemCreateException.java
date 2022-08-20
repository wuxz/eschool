package com.dc.eschool.testresultsitem.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */
/**
 * TestResultsItemCreateException is an exception that extends the
 * standard Exception. This is thrown by the the TestResultsItem
 * component when there is some failure because of user error
 */
public class TestResultsItemCreateException extends Exception
{
/**
 * Default constructor. Takes no arguments
 */
  public TestResultsItemCreateException()
  {
    super();
  }
/**
 * Constructor
 * @param str    a string that explains what the exception condition is
 */
  public TestResultsItemCreateException(String str)
  {
    super(str);
  }
  public TestResultsItemCreateException(Exception e)
  {
    super("TestResultsItemManagerCreateException :" + e.getMessage());
  }
}