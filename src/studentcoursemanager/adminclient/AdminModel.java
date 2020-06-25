package studentcoursemanager.adminclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import studentcoursemanager.client.SearchCourseException;

/**
 * View that prompts the admin for a specific course to create.
 * 
 * @author Nadim Asaduzzaman (Partner: Albin Radaj)
 * @version 1.0
 * @since April 17, 2020
 */
public class AdminModel
{
	/**
	 * Server's input stream to read server data from
	 */
	private BufferedReader serverIn;
	/**
	 * Server's output stream to communicate with server
	 */
	private PrintWriter serverOut;
	/**
	 * Socket for connection with server
	 */
	private Socket socket;

	/**
	 * Method to connect with a server at a specified port
	 * @param hostAddress address of server host
	 * @param port port the server is serving on
	 * @throws UnknownHostException if host can't be reached
	 * @throws IOException when IOException occurs
	 */
	public void connectToServer(String hostAddress, int port) throws UnknownHostException, IOException
	{
		this.socket = new Socket(hostAddress, port);
		this.serverIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.serverOut = new PrintWriter(this.socket.getOutputStream(), false);
		// SERVER EXPECTS FIRST readLine FROM CLIENT AS USER APP IDENTITY (i.e. client must identify itself first before doing anything. Closes connection if client is recognized)
		this.serverOut.printf("ADMIN\n");
	}
	/**
	 * Method to send command to add course to server's database
	 * @param courseName course name of new course to add
	 * @param courseNumber course number of new course to add
	 * @throws IOException when IOException occurs
	 * @throws AddCourseException when there is an issue with adding course
	 */
	public void addCourse(String courseName, int courseNumber) throws IOException, AddCourseException
	{
		this.serverOut.printf("COMMAND: ADD COURSE\n%s\n%d\n", courseName, courseNumber);
		this.serverOut.flush();
		String serverResponseType = this.serverIn.readLine();
		switch(serverResponseType)
		{
		case "SUCCESS":
			while(this.serverIn.ready())
			{
				this.serverIn.readLine();
			}
		case "ERROR":
			String errResp = "";
			while(this.serverIn.ready())
			{
				errResp += this.serverIn.readLine() + "\n";
			}
			throw new AddCourseException(errResp);
		default:
			throw new AddCourseException("Unknown Response from server");
		}
	}
	/**
	 * Method to request server to show course offering
	 * @return Course Catalogue Data
	 * @throws IOException when IOException occurs
	 */
	public String showCourseCatalogue() throws IOException
	{
		this.serverOut.printf("COMMAND: SHOW COURSE CATALOGUE\n");
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
	 * 
	 * @param courseName name of course to search
	 * @param courseNum id of course to search
	 * @return Course Data
	 * @throws SearchCourseException when there is an issue with searching for a course
	 * @throws IOException when IOException occurs
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
	 * Method to disconnect from a server
	 * @throws IOException
	 */
	public void disconnectFromServer() throws IOException
	{
		this.serverIn.close();
		this.serverOut.close();
		this.socket.close();
	}
}
