package com.dc.eschool.homework.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * HWDAODuKeyException is an exception that extends the
 * HWDAOAppException. This is thrown by the DAOs of the homeWork
 * component when a row is already found with a given primary key
 */
public class HWDAODuKeyException extends HWDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public HWDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public HWDAODuKeyException ()
    {
        super();
    }

}