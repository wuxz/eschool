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
 * ProjectContentDAODBUpException is an exception that extends the
 * ProjectContentDAOAppException. This is thrown by the DAOs of the ProjectContent
 * component when there is an error while writing/updating databases
 */
public class ProjectContentDAODBUpException extends ProjectContentDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ProjectContentDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ProjectContentDAODBUpException ()
    {
        super();
    }
}