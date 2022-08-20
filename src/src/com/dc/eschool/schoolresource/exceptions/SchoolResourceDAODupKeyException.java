package   com.dc.eschool.schoolresource.exceptions;

/**
 * SchoolResourceDAODupKeyException is an exception that extends the
 * SchoolResourceDAOAppException. This is thrown by the DAOs of the account
 * component when a row is already found with a given primary key
 */
public class SchoolResourceDAODupKeyException extends SchoolResourceDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public SchoolResourceDAODupKeyException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public SchoolResourceDAODupKeyException ()
    {
        super();
    }
}
