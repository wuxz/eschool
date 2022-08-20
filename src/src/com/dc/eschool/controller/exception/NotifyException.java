package com.dc.eschool.controller.exception;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class NotifyException extends Exception implements java.io.Serializable
{
  public NotifyException()
  {
  }

  public NotifyException(String str)
  {
    super(str);
  }
}