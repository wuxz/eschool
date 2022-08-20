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
 * ProjectContentDAOFinderException is an exception that extends the
 * ProjectContentDAOAppException. This is thrown by the DAOs of the ProjectContent
 * component when there is no row corresponding to a primary key
 */
public class ProjectContentDAOFindException extends ProjectContentDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ProjectContentDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ProjectContentDAOFindException ()
    {
        super();
    }

}