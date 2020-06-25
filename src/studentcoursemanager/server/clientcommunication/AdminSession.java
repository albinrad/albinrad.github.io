package studentcoursemanager.server.clientcommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import studentcoursemanager.server.exceptions.CourseException;
import studentcoursemanager.server.resource.Course;
import studentcoursemanager.server.resource.CourseCatalogue;

/**
 * A class that indicates an active session with an admin
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class AdminSession extends Session
{
	/**
	 * Constructs a new AdminSession object with the arguements passed
	 * @param clientIn Client's Input Stream
	 * @param clientOut Client's Output Steam
	 * @param dbManager DBManager associated wth session
	 */
    public AdminSession(BufferedReader clientIn, PrintWriter clientOut, DBManager dbManager)
    {
        super(clientIn, clientOut, dbManager);
    }

    @Override
    public void communicate()
    {
    	try
    	{
            boolean endConnection = false;
            while (!endConnection)
            {
                String userResponse = this.getClientIn().readLine();
                if (userResponse == null)
                {
                	this.getClientOut().printf("ERROR\nreceived null while reading from user.%s");
                    return;
                }
                switch (userResponse)
                {
	                case "COMMAND: ADD COURSE":
	                    this.addCourse();
	                    break;
                    case "COMMAND: SEARCH COURSE":
                        this.searchCourse();
                        break;
                    case "COMMAND: SHOW COURSE CATALOGUE":
                        this.showCourseCat();
                        break;
                    case "COMMAND: LOGOUT":
                        return;
                    default:
                    	this.getClientOut().printf("ERROR\nCommand not recognized\n%s", userResponse);
                        return;
                }
            }
    	}
		catch(IOException e)
		{
			this.getClientOut().printf("ERROR\nIOException while getting data from user\n");
			return;
		}
    	finally
    	{
    		this.getClientOut().flush();
    	}
    }
    /**
	 * Method to use if user wants to add a new course to catalogue
	 */
    private void addCourse()
    {
    	try
    	{
        	String courseFaculty = this.getClientIn().readLine();
        	String courseNumberStr = this.getClientIn().readLine();

        	int courseNumber = Integer.parseInt(courseNumberStr);
        	this.getDBManager().addCourse(courseFaculty, courseNumber);
        	this.getClientOut().printf("SUCCESS\nAdded Course Successfully!\n");
        	this.getClientOut().flush();
    	}
		catch(NumberFormatException e)
		{
			this.getClientOut().printf("ERROR\nPlease check the Course Number. It must be an integer.\n");
			this.getClientOut().flush();
			return;
		}
		catch (IOException e)
		{
			this.getClientOut().printf("ERROR\nIOException while getting data from user\n");
			this.getClientOut().flush();
			return;
		}
    	catch (CourseException e)
    	{
			this.getClientOut().printf("ERROR\n%s\n", e.getMessage());
			this.getClientOut().flush();
			return;
		}
    }
	/**
	 * Method to use if user wants to search for a course
	 */
	private void searchCourse()
    {
    	try
    	{
        	String courseFaculty = this.getClientIn().readLine();
        	String courseNumberStr = this.getClientIn().readLine();

        	int courseNumber = Integer.parseInt(courseNumberStr);
        	Course foundCourse = this.getDBManager().searchCourse(courseFaculty, courseNumber);
        	if(foundCourse == null)
        	{
        		this.getClientOut().printf("ERROR\nCourse Not Found.\n");
        		return;
        	}
        	this.getClientOut().printf("SUCCESS\n%s\n", foundCourse.toString());
    	}
		catch(NumberFormatException e)
		{
			this.getClientOut().printf("ERROR\nPlease check the Course Number. It must be an integer.\n");
			return;
		}
		catch (IOException e)
		{
			this.getClientOut().printf("ERROR\nIOException while getting data from user\n");
			return;
		}
    	finally
    	{
    		this.getClientOut().flush();
    	}
    }
	/**
	 * Method to use if user wants to show course catalogue
	 */
	private void showCourseCat()
    {
    	CourseCatalogue courseCat = this.getDBManager().getCourseCat();
    	this.getClientOut().printf("SUCCESS\n%s\n", courseCat.toString());
    	this.getClientOut().flush();
    }
}