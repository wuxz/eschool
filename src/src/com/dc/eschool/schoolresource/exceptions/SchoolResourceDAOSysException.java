package   com.dc.eschool.schoolresource.exceptions;

import java.lang.RuntimeException;

/**
 * SchoolResourceDAOSysException is an exception that extends the standard
 * RunTimeException Exception. This is thrown by the DAOs of the account
 * component when there is some irrecoverable error (like SQLException)
 */
public class SchoolResourceDAOSysException extends RuntimeException
{
    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public SchoolResourceDAOSysException (String str)
    {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public SchoolResourceDAOSysException ()
    {
        super();
    }
}
