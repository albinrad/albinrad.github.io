package studentcoursemanager.adminclient.frontend;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * The main view that shows when an admin client logs in, provides a 
 * menu with a range of options for the admin.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class AdminView extends JFrame {
	/**
	 * The view that shows when the user selects "Search Course Catalogue"
	 */
	protected AdminSearchCourseView searchCourseView;
	/**
	 * The view that shows when the user selects "Create Course"
	 */
	protected AdminCreateCourseView createCourseView;

	private ConnectToServerView loginView;
	
	/**
	 * Text area that shows all courses in catalog when requested
	 */
	private JTextArea textAreaAllCourses;
	/**
	 * The welcome message that will show the student's user name
	 */
	private JLabel labelWelcomeMsg;
	
	/**
	 * Button to list all courses in course catalog
	 */
	protected JButton buttonAllCourses;
	/**
	 * Button to create a course
	 */
	protected JButton buttonCreateCourse;
	/**
	 * Button to search courses
	 */
	protected JButton buttonSearchCourses;
	/**
	 * Button to logout from the admin's profile
	 */
	protected JButton buttonLogout;
	
	/**
	 * Creates the AdminView frame
	 */
	public AdminView() {
		setResizable(false);
		setTitle("Admin Profile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 280);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.WEST);
		panelButtons.setLayout(new GridLayout(3, 1, 0, 0));
		
		buttonSearchCourses = new JButton("Search Course Catalogue");
		panelButtons.add(buttonSearchCourses);
		buttonAllCourses = new JButton("View Course Catalogue");
		panelButtons.add(buttonAllCourses);
		buttonCreateCourse = new JButton("Create Course");
		panelButtons.add(buttonCreateCourse);
		
		JPanel panelTextArea = new JPanel();
		contentPane.add(panelTextArea, BorderLayout.EAST);
		panelTextArea.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel labelAdminTitle = new JLabel("Student Course Manager");
		labelAdminTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelAdminTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelTextArea.add(labelAdminTitle);
		
		JPanel textPanel = new JPanel();
		panelTextArea.add(textPanel);
		textPanel.setLayout(new BorderLayout(0, 0));
		
		labelWelcomeMsg = new JLabel("Welcome");
		labelWelcomeMsg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelWelcomeMsg.setVerticalAlignment(SwingConstants.TOP);
		labelWelcomeMsg.setHorizontalAlignment(SwingConstants.CENTER);
		textPanel.add(labelWelcomeMsg, BorderLayout.CENTER);
		
		JLabel labelAllCourses = new JLabel("All Courses:");
		labelAllCourses.setHorizontalAlignment(SwingConstants.CENTER);
		textPanel.add(labelAllCourses, BorderLayout.SOUTH);
		JScrollPane scrollAllPane = new JScrollPane();
		scrollAllPane.setPreferredSize(new Dimension(200, 2));
		panelTextArea.add(scrollAllPane);
		
		textAreaAllCourses = new JTextArea();
		scrollAllPane.setViewportView(textAreaAllCourses);
		
		JPanel logoutPanel = new JPanel();
		contentPane.add(logoutPanel, BorderLayout.SOUTH);
		
		buttonLogout = new JButton("Logout");
		logoutPanel.add(buttonLogout);
		
		searchCourseView = new AdminSearchCourseView();
		createCourseView = new AdminCreateCourseView();
		loginView = new ConnectToServerView();
	}
	
	/**
	 * Shows an error message to a specific frame
	 * @param frame The parent frame that the error message will show to
	 * @param error The error message that will be shown
	 */
	public void showErrorMsg(JFrame frame, String error)
	{
		JOptionPane.showMessageDialog(frame, error, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Shows a message to a specific frame
	 * @param frame The parent frame that the message will show to
	 * @param message The message that will be shown
	 */
	public void showMsg(JFrame frame, String message)
	{
		JOptionPane.showMessageDialog(frame, message, "MESSAGE", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * Setter for the all courses text area
	 * @param courses All courses in the course catalog
	 */
	public void setAdminAllCourses(String courses) {
		textAreaAllCourses.setText(courses);
	}
	
	/**
	 * Shows the AdminView frame
	 */
	public void showFrame() {
		setVisible(true);
	}
	/**
	 * Closes the AdminView frame, clears all text areas
	 */
	public void closeFrame() {
		setVisible(false);
		this.searchCourseView.dispose();
		this.createCourseView.dispose();
		this.dispose();
	}

	/**
	 * Shows the Admin Create Course view
	 */
	public void showAdminCreateCourseFrame()
	{
		createCourseView.showFrame();
	}
	/**
	 * Closes the Admin Create Course view
	 */
	public void closeAdminCreateCourseFrame()
	{
		createCourseView.closeFrame();
	}
	public void closeLoginFrame()
	{
		loginView.closeFrame();
	}
	/**
	 * Shows the Admin Search Course view
	 */
	public void showAdminSearchCourseFrame()
	{
		searchCourseView.showFrame();
	}
	public void showConnectToServerFrame()
	{
		loginView.showFrame();
	}
	/**
	 * Closes the Admin Search Course view
	 */
	public void closeAdminSearchCourseFrame()
	{
		searchCourseView.closeFrame();
	}
	
	public String getAdminSearchCourseName() {
		return searchCourseView.getSearchCourseName();
	}
	public String getAdminSearchCourseNum() {
		return searchCourseView.getSearchCourseNum();
	}
	// AdminCreateCourseView getters
	public String getAdminCreateCourseName() {
		return createCourseView.getCreateCourseName();
	}
	public String getAdminCreateCourseNum() {
		return createCourseView.getCreateCourseNum();
	}
	public String getHostAddress()
	{
		return loginView.getHostAddress();
	}
	public String getPort()
	{
		return loginView.getHostPort();
	}
	
	// AdminView listeners
	public void addAdminSearchCourseListener(ActionListener listener)
	{
		buttonSearchCourses.addActionListener(listener);
	}
	public void addAdminListAllCoursesListener(ActionListener listener)
	{
		buttonAllCourses.addActionListener(listener);
	}
	public void addAdminCreateCourseListener(ActionListener listener)
	{
		buttonCreateCourse.addActionListener(listener);
	}
	public void addAdminLogoutListener(ActionListener listener)
	{
		buttonLogout.addActionListener(listener);
	}
	// AdminCreateCourseView listeners
	public void addAdminCreateSubmitListener(ActionListener listener)
	{
		createCourseView.buttonCreateCourse.addActionListener(listener);
	}
	public void addAdminCreateReturnListener(ActionListener listener)
	{
		createCourseView.buttonReturn.addActionListener(listener);
	}
	// AdminSearchCourseView listeners
	public void addAdminSearchSubmitListener(ActionListener listener)
	{
		searchCourseView.buttonSearchCourse.addActionListener(listener);
	}
	public void addAdminSearchReturnListener(ActionListener listener)
	{
		searchCourseView.buttonReturn.addActionListener(listener);
	}
	public AdminSearchCourseView getAdminSearchCourseView()
	{
		return this.searchCourseView;
	}
	public AdminCreateCourseView getAdminCreateCourseView()
	{
		return this.createCourseView;
	}
	public ConnectToServerView getConnectToServerView()
	{
		return this.loginView;
	}
	public void addConnectListener(ActionListener listener)
	{
		loginView.buttonConnect.addActionListener(listener);
	}
	public void addQuitListener(ActionListener listener)
	{
		loginView.buttonQuit.addActionListener(listener);
	}
}
