package  com.dc.eschool.scorestatistic.exceptions;

/**
 * ScoreStatisticDAOAppException is an exception that extends the
 * standard Exception. This is thrown by the DAOs of the ScoreStatistic
 * component when there is some failure because of user error
 */
public class ScoreStatisticDAOAppException extends Exception
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ScoreStatisticDAOAppException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ScoreStatisticDAOAppException ()
    {
        super();
    }
}
