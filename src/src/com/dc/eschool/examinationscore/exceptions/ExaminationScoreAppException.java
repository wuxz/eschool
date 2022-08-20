package com.dc.eschool.examinationscore.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * ExaminationScoreAppException is an exception that extends the
 * standard Exception. This is thrown by the the ExaminationScore
 * component when there is some failure because of user error
 */
public class ExaminationScoreAppException extends Exception
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ExaminationScoreAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ExaminationScoreAppException ()
    {
        super();
    }
}