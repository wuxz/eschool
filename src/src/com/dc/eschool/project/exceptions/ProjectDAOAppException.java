package com.dc.eschool.project.exceptions;

/**
 * Title:        Eschool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:      DC
 * @author Eric CHEN
 * @version 1.0
 */

/**
 * ProjectDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the Project
 * component when there is some failure because of user error
 */
public class ProjectDAOAppException extends Exception
{

  /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ProjectDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ProjectDAOAppException ()
    {
        super();
    }
}