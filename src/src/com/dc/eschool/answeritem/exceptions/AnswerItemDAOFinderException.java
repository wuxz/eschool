package com.dc.eschool.answeritem.exceptions;

/**
 * AnswerItemDAOFinderException is an exception that extends the
 * AnswerItemDAOAppException. This is thrown by the DAOs of the AnswerItem
 * component when there is no row corresponding to a primary key
 */
public class AnswerItemDAOFinderException extends AnswerItemDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public AnswerItemDAOFinderException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public AnswerItemDAOFinderException ()
    {
        super();
    }
}
