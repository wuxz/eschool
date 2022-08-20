package   com.dc.eschool.schoolresource.exceptions;

/**
 * SchoolResourceDAODBUpdateException is an exception that extends the
 * SchoolResourceDAOAppException. This is thrown by the DAOs of the SchoolResource
 * component when there is an error while writing/updating databases
 */
public class SchoolResourceDAODBUpdateException extends SchoolResourceDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public SchoolResourceDAODBUpdateException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public SchoolResourceDAODBUpdateException ()
    {
        super();
    }
}
