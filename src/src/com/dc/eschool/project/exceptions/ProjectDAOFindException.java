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
 * ProjectDAOFinderException is an exception that extends the
 * ProjectDAOAppException. This is thrown by the DAOs of the Project
 * component when there is no row corresponding to a primary key
 */
public class ProjectDAOFindException extends ProjectDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ProjectDAOFindException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ProjectDAOFindException ()
    {
        super();
    }

}