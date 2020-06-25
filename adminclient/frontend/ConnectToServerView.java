package studentcoursemanager.adminclient.frontend;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Component;

/**
 * The first view that will be visible to the client, prompts the user 
 * for the server's host address and port.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class ConnectToServerView extends JFrame {
	/**
	 * Text field for the server host address
	 */
	private JTextField fieldHostAddress;
	/**
	 * Text field for the server port
	 */
	private JTextField fieldPort;
	
	/**
	 * Button to connect to the server
	 */
	protected JButton buttonConnect;
	/**
	 * Button to quit the program
	 */
	protected JButton buttonQuit;


	/**
	 * Creates the ConnectToServerView frame
	 */
	public ConnectToServerView() {
		setResizable(false);
		setTitle("Server Connect");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 165);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel labelFrameTitle = new JLabel("Student Course Manager");
		labelFrameTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelFrameTitle, BorderLayout.NORTH);
		
		JPanel inputPanel = new JPanel();
		contentPane.add(inputPanel, BorderLayout.CENTER);
		
		JLabel labelHostAddress = new JLabel("Server Host Address");
		inputPanel.add(labelHostAddress);
		
		Component spacer1 = Box.createVerticalStrut(30);
		inputPanel.add(spacer1);
		Component spacer2 = Box.createHorizontalStrut(30);
		inputPanel.add(spacer2);
		
		JLabel labelPort = new JLabel("Port");
		inputPanel.add(labelPort);
		
		Component spacer3 = Box.createHorizontalStrut(20);
		inputPanel.add(spacer3);
		
		fieldHostAddress = new JTextField();
		inputPanel.add(fieldHostAddress);
		fieldHostAddress.setColumns(10);
		
		Component spacer4 = Box.createHorizontalStrut(20);
		inputPanel.add(spacer4);
		
		fieldPort = new JTextField();
		inputPanel.add(fieldPort);
		fieldPort.setColumns(5);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		Component spacer5 = Box.createVerticalStrut(30);
		buttonPanel.add(spacer5);
		
		buttonConnect = new JButton("Connect");
		buttonPanel.add(buttonConnect);
		buttonQuit = new JButton("Quit");
		buttonPanel.add(buttonQuit);
	}
	
	/**
	 * Getter for the server host address
	 * @return The server host address
	 */
	public String getHostAddress() {
		return fieldHostAddress.getText();
	}
	/**
	 * Getter for the server port
	 * @return The server port
	 */
	public String getHostPort() {
		return fieldPort.getText();
	}
	
	/**
	 * Shows the ConnectToServerView frame
	 */
	public void showFrame() {
		setVisible(true);
	}
	/**
	 * Closes the ConnectToServerView frame, clears all text fields
	 */
	public void closeFrame() {
		setVisible(false);
		fieldHostAddress.setText("");
		fieldPort.setText("");
	}
}
