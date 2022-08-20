package com.dc.eschool.examinationscore.exceptions;

/**
 * ExaminationScoreDAOFinderException is an exception that extends the
 * ExaminationScoreDAOAppException. This is thrown by the DAOs of the ExaminationScore
 * component when there is no row corresponding to a primary key
 */
public class ExaminationScoreDAOFinderException extends ExaminationScoreDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ExaminationScoreDAOFinderException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ExaminationScoreDAOFinderException ()
    {
        super();
    }
}
