package com.dc.eschool.eclass.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class EClassCreateException extends Exception
{
    public EClassCreateException()
    {
      super();
    }

    public EClassCreateException(String str)
    {
      super(str);
    }

    public EClassCreateException(Exception e)
    {
      super("ProjectManagerCreateException :" + e.getMessage());
    }
}