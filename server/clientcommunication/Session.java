package studentcoursemanager.server.clientcommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A abstract class that handles an active connection with a client
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public abstract class Session
{
    /**
     * DBManager associated with session
     */
    private DBManager dbManager;
    /**
     * Stream to read user data from
     */
    private BufferedReader clientIn;
    /**
     * Stream to write data to
     */
    private PrintWriter clientOut;
    /**
     * Starts communication with the server
     * @throws IOException if IOException occurs while communicating
     */
    public abstract void communicate() throws IOException;
    /**
     * Constructs a new Session object by setting the DBManager and the streams associated with client
     * @param clientIn Client's Input Stream
     * @param clientOut Client's Output Stream
     * @param dbManager DBManager for session to obtain data from
     */
    public Session(BufferedReader clientIn, PrintWriter clientOut, DBManager dbManager)
    {
        this.dbManager = dbManager;
        this.clientIn = clientIn;
        this.clientOut = clientOut;
    }
    public BufferedReader getClientIn()
    {
        return this.clientIn;
    }
    public PrintWriter getClientOut()
    {
        return this.clientOut;
    }
    public DBManager getDBManager()
    {
        return this.dbManager;
    }
}