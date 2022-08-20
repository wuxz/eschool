package com.dc.eschool.testresultsitem.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class TestResultsItemDeleteException extends Exception
{

  public TestResultsItemDeleteException()
  {
    super();
  }

  public TestResultsItemDeleteException(String str)
  {
    super(str);
  }

  public TestResultsItemDeleteException(Exception e)
  {
    super("TestResultsItemDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
  }
}