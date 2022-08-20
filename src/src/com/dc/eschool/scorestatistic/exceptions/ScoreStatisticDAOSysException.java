package   com.dc.eschool.scorestatistic.exceptions;

import java.lang.RuntimeException;

/**
 * ScoreStatisticDAOSysException is an exception that extends the standard
 * RunTimeException Exception. This is thrown by the DAOs of the ScoreStatistic
 * component when there is some irrecoverable error (like SQLException)
 */
public class ScoreStatisticDAOSysException extends RuntimeException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public ScoreStatisticDAOSysException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public ScoreStatisticDAOSysException ()
    {
        super();
    }
}
