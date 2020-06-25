package studentcoursemanager.client;

/**
 * A class that indicates during course registration
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class CourseRegistrationException extends Exception {
	private static final long serialVersionUID = 231115507277854221L;

	public CourseRegistrationException(String errMsg)
	{
		super(errMsg);
	}
}
