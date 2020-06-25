package studentcoursemanager.client;

/**
 * A class that indicates login exception
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class LoginException extends Exception
{
	private static final long serialVersionUID = -8526198814511679082L;
	public LoginException(String errMsg)
	{
		super(errMsg);
	}
}