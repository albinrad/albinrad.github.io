package studentcoursemanager.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import studentcoursemanager.server.clientcommunication.AuthenticateSession;
import studentcoursemanager.server.clientcommunication.DBManager;

/**
 * A model class that holds the business login to manager server
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class ServerModel
{
    /**
     * The threadpool executor
     */
    private ExecutorService threadPoolExecutor;
    /**
     * The server socket
     */
    private ServerSocket serverSocket;
    /**
     * The DBManager for the server session
     */
    private DBManager dbManager;

    /**
     * Constructs a new ServerModel object by constructing a new DBManager object
     */
    public ServerModel()
    {
        this.dbManager = new DBManager();
    }

    /**
     * Method to use to load from a SQL Database
     * @param username sql username
     * @param password sql password
     * @param dbURL sql db url
     * @throws SQLException
     */
    public void loadDB(String username, String password, String dbURL) throws SQLException
    {
        this.dbManager.loadDB(username, password, dbURL);
    }

    /**
     * Method to use to start a server a specific port
     * @param bindPort port to start server on
     * @param maxThreads maximum concurrent users to allow
     * @throws IOException if IOException occurs
     */
    public void startServer(int bindPort, int maxThreads) throws IOException
    {
        this.threadPoolExecutor = Executors.newFixedThreadPool(maxThreads + 1);
        this.serverSocket = new ServerSocket(bindPort);
        this.threadPoolExecutor.submit(new BackgroundAccepter());
    }

    /**
     * Method to use to stop a server
     * @throws IOException if IOException occurs
     */
    public void stopServer() throws IOException
    {
        this.serverSocket.close();
        this.threadPoolExecutor.shutdown();
    }

    /**
     * A runnable class which always runs until the server stops
     */
    class BackgroundAccepter implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                while(true)
                {
                    Socket newAcceptedUser = serverSocket.accept();
                    threadPoolExecutor.submit(new AuthenticateSession(newAcceptedUser, dbManager));
                }
            }
            catch(IOException e)
            {
            	try
            	{
					stopServer();
				}
            	catch(IOException e1)
            	{
            		
				}
            }
        }
    }
}