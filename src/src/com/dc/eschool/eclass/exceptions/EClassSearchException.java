package com.dc.eschool.eclass.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class EClassSearchException extends Exception
{
    public EClassSearchException()
    {
      super();
    }

    public EClassSearchException(String str)
    {
      super(str);
    }

    public EClassSearchException(Exception e)
    {
      super("EClassSearchException occured: "+e.getClass().getName()+"__"+e.getMessage());
    }
}