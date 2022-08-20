package com.dc.eschool.users.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class UDeleteException extends Exception
{

    public UDeleteException()
    {
      super();
    }

    public UDeleteException(String str)
    {
      super(str);
    }

    public UDeleteException(Exception e)
    {
      super("UDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
    }
}