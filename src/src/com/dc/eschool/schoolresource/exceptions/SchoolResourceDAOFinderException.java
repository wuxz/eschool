package   com.dc.eschool.schoolresource.exceptions;

/**
 * SchoolResourceDAOFinderException is an exception that extends the
 * SchoolResourceDAOAppException. This is thrown by the DAOs of the SchoolResource
 * component when there is no row corresponding to a primary key
 */
public class SchoolResourceDAOFinderException extends SchoolResourceDAOAppException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public SchoolResourceDAOFinderException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public SchoolResourceDAOFinderException ()
    {
        super();
    }
}
