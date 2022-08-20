package com.dc.eschool.examinationscore.exceptions;

import java.lang.RuntimeException;

/**
 * ExaminationScoreDAOSysException is an exception that extends the standard
 * RunTimeException Exception. This is thrown by the DAOs of the ExaminationScore
 * component when there is some irrecoverable error (like SQLException)
 */
public class ExaminationScoreDAOSysException extends RuntimeException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ExaminationScoreDAOSysException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ExaminationScoreDAOSysException ()
    {
        super();
    }
}
