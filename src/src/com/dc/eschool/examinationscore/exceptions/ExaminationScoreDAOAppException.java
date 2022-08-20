package com.dc.eschool.examinationscore.exceptions;

/**
 * ExaminationScoreDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the ExaminationScore
 * component when there is some failure because of user error
 */
public class ExaminationScoreDAOAppException extends Exception
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ExaminationScoreDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ExaminationScoreDAOAppException ()
    {
        super();
    }
}
