package com.dc.eschool.users.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class UCreateException extends Exception
{
    public UCreateException()
    {
      super();
    }

    public UCreateException(String str)
    {
      super(str);
    }

    public UCreateException(Exception e)
    {
      super("UsersManagerCreateException :" + e.getMessage());
    }
}