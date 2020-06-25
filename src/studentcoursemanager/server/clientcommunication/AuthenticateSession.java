package studentcoursemanager.server.clientcommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A runnable class which identifies user and start communication with user till connection closed
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class AuthenticateSession implements Runnable
{
	/**
	 * The socket associated with client
	 */
	private Socket clientSocket;
	/**
	 * The DBManager which will be used for current session to retrieve data
	 */
	private DBManager dbManager;
	/**
	 * Client's Input Stream
	 */
	private BufferedReader clientIn;
	/**
	 * Client's Output Stream
	 */
	private PrintWriter clientOut;
	/**
	 * Constructs a new AuthenticateSession object by setting the passed arguements
	 * @param clientSocket the client socket to get streams from
	 * @param dbManager the DBManager to retrieve data from
	 */
    public AuthenticateSession(Socket clientSocket, DBManager dbManager)
    {
        try
        {
            this.clientSocket = clientSocket;
            this.clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.clientOut = new PrintWriter(clientSocket.getOutputStream(), false);
            this.dbManager = dbManager;
        }
        catch(IOException e)
        {
            this.closeConnection();
        }
    }
    @Override
    public void run()
    {
        if(this.clientIn != null && this.clientOut != null && this.clientSocket != null && this.dbManager != null)
        {
        	try
        	{
            	String clientType = this.clientIn.readLine();
            	switch(clientType)
            	{
            	case "STUDENT":
            		boolean quit = false;
            		while(!quit)
            		{
            			String userCommand = this.clientIn.readLine();
            			switch(userCommand)
            			{
            			case "COMMAND: LOGIN STUDENT":
                    		String studentIdStr = this.clientIn.readLine();
                    		int studentId = Integer.parseInt(studentIdStr);
                    		if(this.dbManager.isRegisteredStudent(studentId))
                    		{
                    			this.clientOut.printf("SUCCESS\n");
                    			this.clientOut.flush();
                    			StudentSession successStudentSession = new StudentSession(clientIn, clientOut, dbManager, studentId);
                    			successStudentSession.communicate();
                    		}
                    		else
                    		{
                    			this.clientOut.printf("ERROR\nStudent ID is not registered.\n");
                    			this.clientOut.flush();
                    		}
                    		break;
            			default:
            				quit = true;
            				break;
            			}
            		}

            		break;
            	case "ADMIN":
        			AdminSession successAdminSession = new AdminSession(clientIn, clientOut, dbManager);
        			successAdminSession.communicate();
            		break;
            	default:
            		break;
            	}
        	}
        	catch(IOException e)
        	{
        		
        	}
        }
	}
	/**
	 * Method to use to close connection with client
	 */
    private void closeConnection()
    {
        try
        {
            if(!this.clientSocket.isClosed())
            {
                this.clientIn.close();
                this.clientOut.close();
                this.clientSocket.close();
            }
        }
        catch(IOException e)
        {
            
        }
    }
}