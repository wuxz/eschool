package  com.dc.eschool.testresultsitem.exceptions;

/**
 * TestResultsItemDAODBUpdateException is an exception that extends the
 * TestResultsItemDAOAppException. This is thrown by the DAOs of the TestResultsItem
 * component when there is an error while writing/updating databases
 */
public class TestResultsItemDAODBUpdateException extends TestResultsItemDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public TestResultsItemDAODBUpdateException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public TestResultsItemDAODBUpdateException ()
    {
        super();
    }
}
