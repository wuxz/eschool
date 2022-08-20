package com.dc.eschool.projectcontent.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * ProjectContentDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the ProjectContent
 * component when there is some failure because of user error
 */
public class ProjectContentDAOAppException extends Exception
{

  /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ProjectContentDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ProjectContentDAOAppException ()
    {
        super();
    }
}