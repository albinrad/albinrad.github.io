package studentcoursemanager.server.exceptions;

/**
 * A class that represents a course exception
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class CourseException extends Exception
{
    private static final long serialVersionUID = 1L;
    public CourseException(String errMsg)
    {
        super(errMsg);
    }
}