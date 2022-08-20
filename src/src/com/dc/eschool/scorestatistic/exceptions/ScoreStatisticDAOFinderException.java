package  com.dc.eschool.scorestatistic.exceptions;

/**
 * ScoreStatisticDAOFinderException is an exception that extends the
 * ScoreStatisticDAOAppException. This is thrown by the DAOs of the ScoreStatistic
 * component when there is no row corresponding to a primary key
 */
public class ScoreStatisticDAOFinderException extends ScoreStatisticDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ScoreStatisticDAOFinderException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ScoreStatisticDAOFinderException ()
    {
        super();
    }
}
