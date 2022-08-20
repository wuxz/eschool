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
 * ProjectDAODuKeyException is an exception that extends the
 * ProjectDAOAppException. This is thrown by the DAOs of the Project
 * component when a row is already found with a given primary key
 */
public class ProjectDAODuKeyException extends ProjectDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ProjectDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ProjectDAODuKeyException ()
    {
        super();
    }

}