package studentcoursemanager.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * A class to control the view and model for the server
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class ServerController
{
    /**
     * The view to control by the server
     */
    private ServerView theView;
    /**
     * The model to control by the server
     */
    private ServerModel theModel;

    /**
     * Constructs a new ServerController object by controlling the view and the model.
     * @param theView the view to control
     * @param theModel the model to control
     */
    public ServerController(ServerView theView, ServerModel theModel)
    {
        this.theView = theView;
        this.theModel = theModel;
        theView.addStartListener(new StartServerListener());
        theView.addStopListener(new StopServerListener());
        theView.addLoadDBListener(new LoadDBListener());
    }

    class StartServerListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	int port, maxConnections;
        	try
        	{
                port = theView.getPort();
        	}
        	catch(NumberFormatException err)
        	{
        		theView.showErrorMessage("Port specified is not an integer. Please try again.");
        		return;
        	}

        	try
        	{
                maxConnections = theView.getMaxConnections();
        	}
        	catch(NumberFormatException err)
        	{
        		theView.showErrorMessage("Max connections specified is not an integer. Please try again.");
        		return;
        	}
        	
            try
            {
                theModel.startServer(port, maxConnections);
                theView.showMessage("The server has started!");
                theView.disableStartButton();
                theView.enableStopButton();
                theView.disableLoadDBButton();
                theView.disablePortField();
                theView.disableMaxConnectionsField();
            }
            catch(IOException err)
            {
                theView.showErrorMessage("ERROR: IOException while running server. Shutting server down.\nError Message: " + err.getMessage());
                try
                {
                	theModel.stopServer();
                }
                catch(IOException suberr)
                {
                	theView.showErrorMessage("IOException while closing server\nError Message: " + err.getMessage());
                }
            }
        }
    }

    class StopServerListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
            	theModel.stopServer();
            	theView.showMessage("The server has stopped!");
            }
            catch(IOException suberr)
            {
            	theView.showErrorMessage("IOException while closing server\nError Message: " + suberr.getMessage());
            }
            finally
            {
                theView.enableStartButton();
                theView.disableStopButton();
                theView.enableLoadDBButton();
                theView.enablePortField();
                theView.enableMaxConnectionsField();
			}
        }
    }

    class LoadDBListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String userName = theView.getUserInput("Please enter the username to access MySQL DB:");
            if(userName == null)
            {
                return;
            }
            String password = theView.getUserInput("Please enter the password to access MySQL DB:");
            if(password == null)
            {
                return;
            }
            String dbURL = theView.getUserInput("Please enter the URL to access MySQL DB:");
            if(dbURL == null)
            {
                return;
            }

            try
            {
                theModel.loadDB(userName, password, dbURL);
            }
            catch(SQLException err)
            {
                theView.showErrorMessage("ERROR: Failed to load DB.\nException Messsage: " + err.getMessage());
                return;
            }
            theView.showMessage("Loaded DB successfully!");
        }
    }
}