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
 * ProjectContentDAODuKeyException is an exception that extends the
 * ProjectContentDAOAppException. This is thrown by the DAOs of the ProjectContent
 * component when a row is already found with a given primary key
 */
public class ProjectContentDAODuKeyException extends ProjectContentDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ProjectContentDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ProjectContentDAODuKeyException ()
    {
        super();
    }

}