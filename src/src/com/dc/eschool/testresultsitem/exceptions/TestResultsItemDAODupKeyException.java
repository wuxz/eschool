package  com.dc.eschool.testresultsitem.exceptions;

/**
 * TestResultsItemDAODupKeyException is an exception that extends the
 * TestResultsItemDAOAppException. This is thrown by the DAOs of the TestResultsItem
 * component when a row is already found with a given primary key
 */
public class TestResultsItemDAODupKeyException extends TestResultsItemDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public TestResultsItemDAODupKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public TestResultsItemDAODupKeyException ()
    {
        super();
    }
}
