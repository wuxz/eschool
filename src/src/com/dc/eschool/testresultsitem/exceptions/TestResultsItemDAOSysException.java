package  com.dc.eschool.testresultsitem.exceptions;

import java.lang.RuntimeException;

/**
 * TestResultsItemDAOSysException is an exception that extends the standard
 * RunTimeException Exception. This is thrown by the DAOs of the TestResultsItem
 * component when there is some irrecoverable error (like SQLException)
 */
public class TestResultsItemDAOSysException extends RuntimeException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public TestResultsItemDAOSysException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public TestResultsItemDAOSysException ()
    {
        super();
    }
}
