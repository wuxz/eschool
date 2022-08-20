package com.dc.eschool.eclass.exceptions;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

public class EClassDeleteException extends Exception
{
    public EClassDeleteException()
    {
      super();
    }

    public EClassDeleteException(String str)
    {
      super(str);
    }

    public EClassDeleteException(Exception e)
    {
      super("EClassDeleteException occured: "+e.getClass().getName()+"__"+e.getMessage());
    }
}