package com.dc.eschool.examinationscore.exceptions;

/**
 * ExaminationScoreDAODupKeyException is an exception that extends the
 * ExaminationScoreDAOAppException. This is thrown by the DAOs of the ExaminationScore
 * component when a row is already found with a given primary key
 */
public class ExaminationScoreDAODupKeyException extends ExaminationScoreDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ExaminationScoreDAODupKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ExaminationScoreDAODupKeyException ()
    {
        super();
    }
}
