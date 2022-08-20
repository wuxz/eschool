package  com.dc.eschool.testresultsitem.exceptions;

/**
 * TestResultsItemDAOFinderException is an exception that extends the
 * TestResultsItemDAOAppException. This is thrown by the DAOs of the TestResultsItem
 * component when there is no row corresponding to a primary key
 */
public class TestResultsItemDAOFinderException extends TestResultsItemDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public TestResultsItemDAOFinderException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public TestResultsItemDAOFinderException ()
    {
        super();
    }
}
