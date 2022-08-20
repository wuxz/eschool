package com.dc.eschool.users.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class USearchException extends Exception
{
    public USearchException()
    {
      super();
    }

    public USearchException(String str)
    {
      super(str);
    }

    public USearchException(Exception e)
    {
      super("USearchException occured: "+e.getClass().getName()+"__"+e.getMessage());
    }
}