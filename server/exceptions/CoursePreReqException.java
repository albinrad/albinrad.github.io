package studentcoursemanager.server.exceptions;

/**
 * An exception class indicating failure to meet course pre-requisite
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class CoursePreReqException extends Exception
{
	private static final long serialVersionUID = 2532572939071804362L;

	public CoursePreReqException(String message)
	{
		super(message);
	}
}