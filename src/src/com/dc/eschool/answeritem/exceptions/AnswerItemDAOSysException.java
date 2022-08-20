package com.dc.eschool.answeritem.exceptions;

import java.lang.RuntimeException;

/**
 * AnswerItemDAOSysException is an exception that extends the standard
 * RunTimeException Exception. This is thrown by the DAOs of the AnswerItem
 * component when there is some irrecoverable error (like SQLException)
 */
public class AnswerItemDAOSysException extends RuntimeException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public AnswerItemDAOSysException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public AnswerItemDAOSysException ()
    {
        super();
    }
}
