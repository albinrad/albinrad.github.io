package studentcoursemanager.server.exceptions;

/**
 * An exception class indicating a problem with course registration
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class RegistrationException extends Exception
{
    private static final long serialVersionUID = 1L;
    public RegistrationException(String failMsg)
    {
        super(failMsg);
    }
}