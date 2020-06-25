package studentcoursemanager.client.frontend;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * The view that will show once the client connects to the server, 
 * gives user the option to begin authenticating or to disconnect
 * from the server.
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class ConnectionSuccessfulView extends JFrame {
	/**
	 * Button to go to the authentication window
	 */
	protected JButton buttonAuthenticate;
	/**
	 * Button to disconnect from the server
	 */
	protected JButton buttonDisconnect;

	/**
	 * Creates the ConnectionSuccessfulView frame
	 */
	public ConnectionSuccessfulView() {
		setResizable(false);
		setTitle("Connection Successful");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 140);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel labelFrameTitle = new JLabel("Student Course Manager");
		labelFrameTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelFrameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelFrameTitle, BorderLayout.NORTH);
		
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel labelSuccessful = new JLabel("Connection Successful with Server!");
		labelSuccessful.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelSuccessful.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(labelSuccessful);
		
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		buttonAuthenticate = new JButton("Authenticate");
		buttonPanel.add(buttonAuthenticate);
		buttonDisconnect = new JButton("Disconnect");
		buttonPanel.add(buttonDisconnect);
	}
	
	/**
	 * Shows the ConnectToServerView frame
	 */
	public void showFrame() {
		setVisible(true);
	}
	/**
	 * Closes the ConnectToServerView frame
	 */
	public void closeFrame() {
		setVisible(false);
	}
}
