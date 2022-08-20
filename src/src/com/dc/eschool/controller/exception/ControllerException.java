package com.dc.eschool.controller.exception;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

/**
 * This exception is the base class for all the event exceptions.
 */
public class ControllerException extends Exception  implements java.io.Serializable
{

    public ControllerException()
    {
    }

    public ControllerException(String str)
    {
        super(str);
    }
}
