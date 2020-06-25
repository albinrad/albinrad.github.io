package studentcoursemanager.client.frontend;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * The view that will show once the client selects "Authenticate", 
 * prompts the user for a valid username and password for the manager.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class LoginView extends JFrame {
	/**
	 * Text field for the student's ID
	 */
	private JTextField fieldStudentID;

	/**
	 * Button to complete login
	 */
	protected JButton buttonCompleteLogin;
	/**
	 * Button to disconnect from the server
	 */
	protected JButton buttonDisconnect;
	
	/**
	 * Creates the LoginView frame
	 */
	public LoginView() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 140);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel fieldsPanel = new JPanel();
		contentPane.add(fieldsPanel, BorderLayout.CENTER);
		fieldsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel studentIDPanel = new JPanel();
		fieldsPanel.add(studentIDPanel, BorderLayout.NORTH);
		JLabel labelStudentID = new JLabel("Student ID: ");
		studentIDPanel.add(labelStudentID);
		
		fieldStudentID = new JTextField();
		studentIDPanel.add(fieldStudentID);
		fieldStudentID.setColumns(10);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		buttonCompleteLogin = new JButton("Login");
		panel.add(buttonCompleteLogin);
		buttonDisconnect = new JButton("Disconnect");
		panel.add(buttonDisconnect);
	}
	
	/**
	 * Getter for the user's student ID
	 * @return The user's student ID
	 */
	public String getStudentID() {
		return fieldStudentID.getText();
	}
	
	/**
	 * Shows the LoginView frame
	 */
	public void showFrame() {
		setVisible(true);
	}
	/**
	 * Closes the LoginView frame, clears all text fields
	 */
	public void closeFrame() {
		setVisible(false);
		fieldStudentID.setText("");
	}
}
