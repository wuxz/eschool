package com.dc.eschool.homeworkcontent.exceptions;

/**
 * Title:        ESchool
 * Description:  E-education
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Eric
 * @version 1.0
 */

/**
 * HCDAODuKeyException is an exception that extends the
 * HCDAOAppException. This is thrown by the DAOs of the homeWorkContent
 * component when a row is already found with a given primary key
 */
public class HCDAODuKeyException extends HCDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public HCDAODuKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public HCDAODuKeyException ()
    {
        super();
    }

}