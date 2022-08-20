package   com.dc.eschool.answeritem.exceptions;

/**
 * AnswerItemDAODupKeyException is an exception that extends the
 * AnswerItemDAOAppException. This is thrown by the DAOs of the AnswerItem
 * component when a row is already found with a given primary key
 */
public class AnswerItemDAODupKeyException extends AnswerItemDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public AnswerItemDAODupKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public AnswerItemDAODupKeyException ()
    {
        super();
    }
}
