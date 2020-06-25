package studentcoursemanager.server;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * A class that contains functionality for all GUI on the server's end.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class ServerView extends JFrame
{
	/**
	 * Text field for the server port
	 */
	private JTextField portField;
	/**
	 * Text field for the maximum number of connections to the server
	 */
	private JTextField maxConnectionsField;
	
	/**
	 * Button to start the server
	 */
	protected JButton startServerBtn;
	/**
	 * Button to stop the server
	 */
	protected JButton stopServerBtn;
	/**
	 * Button to load DB from backup
	 */
	protected JButton loadDBBtn;

	/**
	 * Creates the ServerView frame
	 */
	public ServerView()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 145);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel labelServerTitle = new JLabel("Server Course Management Server");
		labelServerTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelServerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelServerTitle, BorderLayout.NORTH);
		
		JPanel panelMultiInputs = new JPanel();
		contentPane.add(panelMultiInputs, BorderLayout.CENTER);
		panelMultiInputs.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel labelPort = new JLabel("Server Port: ");
		panelMultiInputs.add(labelPort);
		portField = new JTextField();
		panelMultiInputs.add(portField);
		portField.setColumns(4);
		
		JLabel labelMaxConnections = new JLabel("Max Connections: ");
		panelMultiInputs.add(labelMaxConnections);
		maxConnectionsField = new JTextField();
		panelMultiInputs.add(maxConnectionsField);
		maxConnectionsField.setColumns(4);
		
		JPanel panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new BorderLayout(0, 0));
		
		loadDBBtn = new JButton("Load DB From Backup ");
		panelButtons.add(loadDBBtn, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panelButtons.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		startServerBtn = new JButton("Start");
		panel.add(startServerBtn);
		stopServerBtn = new JButton("Stop");
		panel.add(stopServerBtn);
		this.disableStopButton();
	}
	
	/**
	 * Getter for the server port
	 * @return The server port
	 */
	public int getPort()
	{
		return Integer.parseInt(portField.getText());
	}
	/**
	 * Getter for the max number of connections to the server
	 * @return The max number of connections
	 */
	public int getMaxConnections()
	{
		return Integer.parseInt(maxConnectionsField.getText());
	}
	
	/**
	 * Shows an error message to the frame
	 * @param error The error message that will be shown
	 */
	public void showErrorMessage(String error)
	{
		JOptionPane.showMessageDialog(this, error, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Shows a message to the frame
	 * @param message The message that will be shown
	 */
	public void showMessage(String message)
	{
		JOptionPane.showMessageDialog(this, message, "MESSAGE", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * Gets input from user by showing an Input Dialog
	 * @param message Message for Input Dialog
	 */
	public String getUserInput(String message)
	{
		return JOptionPane.showInputDialog(this, message);
	}
	
	/**
	 * Shows the frame
	 */
	public void showMainFrame()
	{
		setVisible(true);
	}
	
	/**
	 * Disables the Stop Button from being clicked
	 */
	public void disableStopButton()
	{
		this.stopServerBtn.setEnabled(false);
	}
	
	/**
	 * Enables the Stop Button to allow clicking
	 */
	public void enableStopButton()
	{
		this.stopServerBtn.setEnabled(true);
	}
	
	/**
	 * Disables the Start Button from being clicked
	 */
	public void disableStartButton()
	{
		this.startServerBtn.setEnabled(false);
	}
	
	/**
	 * Enables the Start Button to allow clicking
	 */
	public void enableStartButton()
	{
		this.startServerBtn.setEnabled(true);
	}
	
	/**
	 * Disables the Load DB Button from being clicked
	 */
	public void disableLoadDBButton()
	{
		this.loadDBBtn.setEnabled(false);
	}
	
	/**
	 * Enables the Load DB Button to allow clicking
	 */
	public void enableLoadDBButton()
	{
		this.loadDBBtn.setEnabled(true);
	}
	
	/**
	 * Disables the max connections field to disable text editing
	 */
	public void disableMaxConnectionsField()
	{
		this.maxConnectionsField.setEditable(false);
	}
	
	/**
	 * Enables the max connections field to allow text editing
	 */
	public void enableMaxConnectionsField()
	{
		this.maxConnectionsField.setEditable(true);
	}
	
	/**
	 * Disables the max connections field to disable text editing
	 */
	public void disablePortField()
	{
		this.portField.setEditable(false);
	}
	
	/**
	 * Enables the max connections field to allow text editing
	 */
	public void enablePortField()
	{
		this.portField.setEditable(true);
	}
	
	// LISTENERS FOR ALL BUTTONS
	
	public void addLoadDBListener(ActionListener listener)
	{
		loadDBBtn.addActionListener(listener);
	}
	public void addStartListener(ActionListener listener)
	{
		startServerBtn.addActionListener(listener);
	}
	public void addStopListener(ActionListener listener)
	{
		stopServerBtn.addActionListener(listener);
	}
}
