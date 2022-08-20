package com.dc.eschool.projectcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author  kurt xiang
 * @version 1.0
 */


/**
 * ProjectContentAppException is an exception that extends the
 * standard Exception. This is thrown by the the ProjectContent
 * component when there is some failure because of user error
 */
public class ProjectContentAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ProjectContentAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ProjectContentAppException ()
    {
        super();
    }

}