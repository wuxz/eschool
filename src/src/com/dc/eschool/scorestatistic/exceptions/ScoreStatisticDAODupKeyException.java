package  com.dc.eschool.scorestatistic.exceptions;

/**
 * ScoreStatisticDAODupKeyException is an exception that extends the
 * ScoreStatisticDAOAppException. This is thrown by the DAOs of the ScoreStatistic
 * component when a row is already found with a given primary key
 */
public class ScoreStatisticDAODupKeyException extends ScoreStatisticDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ScoreStatisticDAODupKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ScoreStatisticDAODupKeyException ()
    {
        super();
    }
}
