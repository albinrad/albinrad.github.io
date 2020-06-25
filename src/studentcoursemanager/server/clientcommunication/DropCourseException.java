package studentcoursemanager.server.clientcommunication;

/**
 * An exception class that indicates a problem while dropping a course
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class DropCourseException extends Exception
{
	private static final long serialVersionUID = 3346099123080210088L;
	public DropCourseException(String errMsg)
	{
		super(errMsg);
	}
}
