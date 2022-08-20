package  com.dc.eschool.scorestatistic.exceptions;

/**
 * ScoreStatisticDAODBUpdateException is an exception that extends the
 * ScoreStatisticDAOAppException. This is thrown by the DAOs of the ScoreStatistic
 * component when there is an error while writing/updating databases
 */
public class ScoreStatisticDAODBUpdateException extends ScoreStatisticDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ScoreStatisticDAODBUpdateException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ScoreStatisticDAODBUpdateException ()
    {
        super();
    }
}
