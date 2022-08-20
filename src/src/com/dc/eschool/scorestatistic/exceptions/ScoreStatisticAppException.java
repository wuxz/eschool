package com.dc.eschool.scorestatistic.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * ScoreStatisticAppException is an exception that extends the
 * standard Exception. This is thrown by the the ScoreStatistic
 * component when there is some failure because of user error
 */
public class ScoreStatisticAppException extends Exception
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ScoreStatisticAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ScoreStatisticAppException ()
    {
        super();
    }
}