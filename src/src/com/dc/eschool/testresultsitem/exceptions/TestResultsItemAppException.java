package com.dc.eschool.testresultsitem.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author wangshui
 * @version 1.0
 */


/**
 * TestResultsItemAppException is an exception that extends the
 * standard Exception. This is thrown by the the TestResultsItem
 * component when there is some failure because of user error
 */
public class TestResultsItemAppException extends Exception
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public TestResultsItemAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public TestResultsItemAppException ()
    {
        super();
    }
}