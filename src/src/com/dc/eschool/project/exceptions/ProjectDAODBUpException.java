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
 * ProjectDAODBUpException is an exception that extends the
 * ProjectDAOAppException. This is thrown by the DAOs of the Project
 * component when there is an error while writing/updating databases
 */
public class ProjectDAODBUpException extends ProjectDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ProjectDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ProjectDAODBUpException ()
    {
        super();
    }
}