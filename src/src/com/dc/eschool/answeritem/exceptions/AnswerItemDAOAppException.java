package  com.dc.eschool.answeritem.exceptions;

/**
 * AnswerItemDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the account
 * component when there is some failure because of user error
 */
public class AnswerItemDAOAppException extends Exception
{

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public AnswerItemDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public AnswerItemDAOAppException ()
    {
        super();
    }

}
