package studentcoursemanager.adminclient;

/**
 * Exception class to indicate exception while adding course
 */
public class AddCourseException extends Exception
{
	private static final long serialVersionUID = -5615205244436349570L;

	public AddCourseException(String errMsg)
	{
		super(errMsg);
	}
}
