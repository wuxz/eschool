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
 * HCDAODBUpException is an exception that extends the
 * HCDAOAppException. This is thrown by the DAOs of the homeWorkCOntent
 * component when there is an error while writing/updating databases
 */
public class HCDAODBUpException extends HCDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public HCDAODBUpException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public HCDAODBUpException ()
    {
        super();
    }
}