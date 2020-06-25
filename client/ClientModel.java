package studentcoursemanager.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import studentcoursemanager.server.clientcommunication.DropCourseException;

/**
 * A class that contains "business log" on the client's end.
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class ClientModel
{
	/**
	 * Socket to communicate with to other people
	 */
	private BufferedReader serverIn;
	/**
	 * Socket to communicate with to other PEOPLE
	 */
	private PrintWriter serverOut;
	/**
	 * Server Socket connection with client
	 */
	private Socket socket;
	/**
	 * Method to use to connect with a server
	 * @param hostAddress host address to connect to
	 * @param port host port to connect to
	 * @throws UnknownHostException if unable to connect to host specified
	 * @throws IOException if IOException occurs
	 */
	public void connectToServer(String hostAddress, int port) throws UnknownHostException, IOException
	{
		this.socket = new Socket(hostAddress, port);
		this.serverIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.serverOut = new PrintWriter(this.socket.getOutputStream(), false);
		// SERVER EXPECTS FIRST readLine FROM CLIENT AS USER APP IDENTITY (i.e. client must identify itself first before doing anything. Closes connection if client is recognized)
		this.serverOut.printf("STUDENT\n");
	}
	
	/**
	 * Method to use to login with a server
	 * @param studentID id to login with
	 * @throws LoginException if here problems logging in
	 * @throws IOException if IOException occurs
	 */
	public boolean login(String studentID) throws LoginException, IOException
	{
		try
		{
			while(true)
			{
				this.serverOut.printf("COMMAND: LOGIN STUDENT\n%s\n", studentID);
				this.serverOut.flush();
				String serverResponseType = this.serverIn.readLine();
				if(serverResponseType == null)
				{
					throw new IOException("null response from server (connection closed)");
				}
				switch (serverResponseType)
				{
				case "SUCCESS":
					return true;
				case "ERROR":
					String errMsg = "";
					while(this.serverIn.ready())
					{
						String partResp = this.serverIn.readLine();
						errMsg += partResp + "\n";
					}
					throw new LoginException(errMsg);
				default:
					throw new LoginException("Invalid Response from server");
				}
			}

		}
		finally
		{
			this.serverOut.flush();
		}
	}

	/**
	 * Method to call to disconnect from a server;
	 * @throws IOException
	 */
	public void disconnectFromServer() throws IOException
	{
		this.serverIn.close();
		this.serverOut.close();
		this.socket.close();
	}

	/**
	 * Method to call to search for a course
	 * @param courseName course name for course to search
	 * @param courseNum course num for course to search
	 * @return course data
	 * @throws SearchCourseException
	 * @throws IOException if IOException occurs
	 */
	public String searchCourse(String courseName, int courseNum) throws SearchCourseException, IOException
	{
		this.serverOut.printf("COMMAND: SEARCH COURSE\n%s\n%d\n", courseName, courseNum);
		this.serverOut.flush();
		String serverResponseType = this.serverIn.readLine();
		if(serverResponseType == null)
		{
			throw new IOException("null response from server (connection closed)");
		}
		switch(serverResponseType)
		{
		case "SUCCESS":
			String response = "";
			while(this.serverIn.ready())
			{
				response += this.serverIn.readLine() + "\n";
			}
			return response;
		case "ERROR":
			String errMsg = "";
			while(this.serverIn.ready())
			{
				errMsg += this.serverIn.readLine() + "\n";
			}
			throw new SearchCourseException(errMsg);
		default:
			return null;
		}
	}

	/**
	 * Method to use to get course catalogue
	 * @return String containing course catalogue data
	 * @throws IOException if IOException occurs
	 */
	public String showCourseCatalogue() throws IOException
	{
		this.serverOut.printf("COMMAND: VIEW ALL COURSE\n");
		this.serverOut.flush();
		String serverResponseType = this.serverIn.readLine();
		switch(serverResponseType)
		{
		case "SUCCESS":
			String response = "";
			while(this.serverIn.ready())
			{
				response += this.serverIn.readLine() + "\n";
			}
			return response;
		default:
			return null;
		}
	}

	/**
	 * Method to use to get students courses
	 * @return String containing data for student's courses
	 * @throws IOException if IOException occurs IOException occurs
	 */
	public String showStudentCourses() throws IOException
	{
		this.serverOut.printf("COMMAND: VIEW STUDENT COURSE\n");
		this.serverOut.flush();
		String serverResponseType = this.serverIn.readLine();
		switch(serverResponseType)
		{
		case "SUCCESS":
			String response = "";
			while(this.serverIn.ready())
			{
				response += this.serverIn.readLine() + "\n";
			}
			return response;
		default:
			return null;
		}
	}
	
	/**
	 * Method to use to register for a course
	 * @param courseFaculty course name of course to register for
	 * @param courseNumber course number of course to register for
	 * @param sectionNumber section number of course to register for
	 * @throws CourseRegistrationException if there were problems with registration
	 * @throws IOException if IOException occurs
	 */
	public void registerCourse(String courseFaculty, int courseNumber, int sectionNumber) throws CourseRegistrationException, IOException
	{
		this.serverOut.printf("COMMAND: REGISTER COURSE\n%s\n%d\n%d\n", courseFaculty, courseNumber, sectionNumber);
		this.serverOut.flush();
		String serverResponseType = this.serverIn.readLine();
		switch(serverResponseType)
		{
		case "SUCCESS":
			return;
		case "ERROR":
			String errMsg = "";
			while(this.serverIn.ready())
			{
				errMsg += this.serverIn.readLine() + "\n";
			}
			throw new CourseRegistrationException(errMsg);
		default:
			throw new CourseRegistrationException("Invalid response from server");
		}
	}
	
	/**
	 * Method to use to logout from the server
	 */
	public void logout()
	{
		this.serverOut.printf("COMMAND: LOGOUT\n");
		this.serverOut.flush();
	}

	/**
	 * Methos to use to drop coueses
	 * @param courseFaculty course name of course to drop
	 * @param courseNumber course id of course to drop
	 * @throws IOException if IOException occurs
	 * @throws DropCourseException if course drop failed;
	 */
	public void dropCourse(String courseFaculty, int courseNumber) throws IOException, DropCourseException
	{
		this.serverOut.printf("COMMAND: DROP COURSE\n%s\n%d\n", courseFaculty, courseNumber);
		this.serverOut.flush();
		String serverResponseType = this.serverIn.readLine();
		if(serverResponseType == null)
		{
			throw new DropCourseException("No response from server");
		}
		switch(serverResponseType)
		{
		case "SUCCESS":
			while(this.serverIn.ready())
			{
				this.serverIn.readLine();
			}
			return;
		case "ERROR":
			String errMsg = "";
			while(this.serverIn.ready())
			{
				errMsg += this.serverIn.readLine() + "\n";
			}
			throw new DropCourseException(errMsg);
		default:
			throw new DropCourseException(serverResponseType);
		}
	}
}
