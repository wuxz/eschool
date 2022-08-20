package com.dc.eschool.examinationscore.exceptions;

/**
 * ExaminationScoreDAODBUpdateException is an exception that extends the
 * ExaminationScoreDAOAppException. This is thrown by the DAOs of the ExaminationScore
 * component when there is an error while writing/updating databases
 */
public class ExaminationScoreDAODBUpdateException extends ExaminationScoreDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ExaminationScoreDAODBUpdateException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ExaminationScoreDAODBUpdateException ()
    {
        super();
    }
}
