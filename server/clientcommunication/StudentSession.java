package studentcoursemanager.server.clientcommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import studentcoursemanager.server.exceptions.CoursePreReqException;
import studentcoursemanager.server.exceptions.RegistrationException;
import studentcoursemanager.server.resource.Course;
import studentcoursemanager.server.resource.CourseCatalogue;
import studentcoursemanager.server.resource.CourseRegistration;

/**
 * A class that indicates an active session with a student
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class StudentSession extends Session
{
	/**
	 * Student ID for client
	 */
	private int studentId;
	/**
	 * Constructs a new StudentSession with the arguements passed
	 * @param clientIn Client's Input Stream
	 * @param clientOut Client's Output Steam
	 * @param dbManager DBManager associated wth session
	 * @param studentId ID of student for session
	 * @throws IOException if IOException occurs while communicating with user
	 */
	public StudentSession(BufferedReader clientIn, PrintWriter clientOut, DBManager dbManager, int studentId) throws IOException
    {
        super(clientIn, clientOut, dbManager);
        this.studentId = studentId;
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
                    case "COMMAND: SEARCH COURSE":
                        this.searchCourse();
                        break;
                    case "COMMAND: REGISTER COURSE":
                        this.registerNewCourseOffering();
                        break;
                    case "COMMAND: DROP COURSE":
                        this.dropCourse();
                        break;
                    case "COMMAND: VIEW STUDENT COURSE":
                        this.showStudentCourses();
                        break;
                    case "COMMAND: VIEW ALL COURSE":
                        this.showCourseCatalogue();
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
	 * Method to use if user wants to register to a course offering
	 */
    private void registerNewCourseOffering()
    {
		try
		{
			String courseFaculty = this.getClientIn().readLine();
			String courseNumberStr = this.getClientIn().readLine();
			String sectionNumberStr = this.getClientIn().readLine();
			int courseNumber = Integer.parseInt(courseNumberStr);
			int sectionNumber = Integer.parseInt(sectionNumberStr);
			CourseRegistration newReg = new CourseRegistration();
	    	newReg.completeRegistration(this.getDBManager().getStudent(studentId), this.getDBManager().searchCourse(courseFaculty, courseNumber).getCourseOfferingAt(sectionNumber - 1));
	    	this.getClientOut().printf("SUCCESS\n");
		}
		catch (IOException e)
		{
			this.getClientOut().printf("ERROR\nIOException while getting data from user\n");
			return;
		}
		catch(NumberFormatException e)
		{
			this.getClientOut().printf("ERROR\nPlease check the Course Number and/or Section Number. It must be an integer.\n");
			return;
		}
		catch(RegistrationException e)
		{
			this.getClientOut().printf("ERROR\nRegistration Error: %s\n", e.getMessage());
			return;
		}
		catch(CoursePreReqException e)
		{
			this.getClientOut().printf("ERROR\nRegistration Pre Req Error: %s\n", e.getMessage());
			return;
		}
		finally
		{
			this.getClientOut().flush();
		}
    }
	/**
	 * Method to use if user wants to drop course
	 */
    private void dropCourse()
    {
		try
		{
			String courseFaculty = this.getClientIn().readLine();
			String courseNumberStr = this.getClientIn().readLine();
			int courseNumber = Integer.parseInt(courseNumberStr);
	    	for(CourseRegistration courseReg : this.getDBManager().getStudent(this.studentId).getStudentRegList())
	    	{
	    		Course courseForReg = courseReg.getTheOffering().getTheCourse();
	    		if((courseForReg.getCourseNum() == courseNumber) && (courseForReg.getCourseName().compareTo(courseFaculty) == 0))
	    		{
	    			courseReg.cancelRegistration();
	    			this.getClientOut().printf("SUCCESS\nDropped course successfully.\n");
	    			this.getClientOut().flush();
	    			return;
	    		}
	    	}
	    	this.getClientOut().printf("ERROR\nCourse entered is not in student's list. \n");
	    	this.getClientOut().flush();
		}
		catch(NumberFormatException e)
		{
			this.getClientOut().printf("ERROR\nPlease check the Course Number. It must be an integer.\n");
			this.getClientOut().flush();
			return;
		}
		catch(IOException e)
		{
			this.getClientOut().printf("ERROR\nIOException while reading user input.\n");
			this.getClientOut().flush();
			return;
		}
    }
	/**
	 * Method to use if user wants to get all courses taken by student
	 */
    private void showStudentCourses()
    {
    	String courseResponse = "";
    	for(Course course : this.getDBManager().getStudentCourses(this.studentId))
    	{
    		courseResponse += "Course Faculty: " + course.getCourseName() + ", Course Number: " + String.valueOf(course.getCourseNum()) + "\n";
    	}
    	this.getClientOut().printf("SUCCESS\n%s\n", courseResponse);
    	this.getClientOut().flush();
    }
	/**
	 * Method to use if user wants to get course catalogue
	 */
    private void showCourseCatalogue()
    {
    	CourseCatalogue courseCat = this.getDBManager().getCourseCat();
    	this.getClientOut().printf("SUCCESS\n%s\n", courseCat.toString());
    	this.getClientOut().flush();
    }
}