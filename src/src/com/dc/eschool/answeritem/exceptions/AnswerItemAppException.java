package com.dc.eschool.answeritem.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * AnswerItemAppException is an exception that extends the
 * standard Exception. This is thrown by the the AnswerItem
 * component when there is some failure because of user error
 */
public class AnswerItemAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public AnswerItemAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public AnswerItemAppException ()
    {
        super();
    }

}