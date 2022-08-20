package  com.dc.eschool.answeritem.exceptions;

/**
 * AnswerItemDAODBUpdateException is an exception that extends the
 * AnswerItemDAOAppException. This is thrown by the DAOs of the AnswerItem
 * component when there is an error while writing/updating databases
 */
public class AnswerItemDAODBUpdateException extends AnswerItemDAOAppException
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public AnswerItemDAODBUpdateException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public AnswerItemDAODBUpdateException ()
    {
        super();
    }
}
