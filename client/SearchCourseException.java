package studentcoursemanager.client;

/**
 * A class that indicates Exception during search source
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class SearchCourseException extends Exception {

	private static final long serialVersionUID = 8188216019291785702L;
	public SearchCourseException(String errMsg)
	{
		super(errMsg);
	}
}
