package studentcoursemanager.client.frontend;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * The main view that shows when a student client logs in, provides a 
 * menu with a range of options for the student.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class StudentView extends JFrame {
	/**
	 * The view that shows when the user selects "Register Course"
	 */
	protected StudentAddCourseView addCourseView;
	/**
	 * The view that shows when the user selects "Drop Course"
	 */
	protected StudentRemoveCourseView removeCourseView;
	/**
	 * The view that shows when the user selects "Search Course"
	 */
	protected StudentSearchCourseView searchCourseView;
	
	/**
	 * Text area that shows all courses in catalog when requested
	 */
	private JTextArea textAreaAllCourses;
	/**
	 * Text area that shows courses the student is registered in
	 */
	private JTextArea textAreaUserCourses;
	/**
	 * The welcome message that will show the student's user name
	 */
	private JLabel labelWelcomeMsg;
	
	/**
	 * Button to search courses
	 */
	protected JButton buttonSearchCourses;
	/**
	 * Button to list all courses in course catalog
	 */
	protected JButton buttonListAllCourses;
	/**
	 * Button to list courses that the student is registered in
	 */
	protected JButton buttonListUserCourses;
	/**
	 * Button to register for a course
	 */
	protected JButton buttonAddUserCourse;
	/**
	 * Button to drop a course
	 */
	protected JButton buttonRemoveUserCourse;
	/**
	 * Button to logout from the student's profile
	 */
	protected JButton buttonLogout;

	/**
	 * Launch the application. - TEMPORARY for MS-2 and testing
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentView frame = new StudentView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the StudentView frame
	 */
	public StudentView() {
		setTitle("Student Profile");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 360);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.WEST);
		panelButtons.setLayout(new GridLayout(5, 1, 0, 0));
		
		buttonSearchCourses = new JButton("Search Course");
		panelButtons.add(buttonSearchCourses);
		buttonListAllCourses = new JButton("List All Courses");
		panelButtons.add(buttonListAllCourses);
		buttonListUserCourses = new JButton("List My Courses");
		panelButtons.add(buttonListUserCourses);
		buttonAddUserCourse = new JButton("Register Course");
		panelButtons.add(buttonAddUserCourse);
		buttonRemoveUserCourse = new JButton("Drop Course");
		panelButtons.add(buttonRemoveUserCourse);
		
		JPanel panelTextAreas = new JPanel();
		contentPane.add(panelTextAreas, BorderLayout.EAST);
		panelTextAreas.setLayout(new GridLayout(5, 1, 0, 0));
		
		JLabel labelStudentTitle = new JLabel("Student Course Manager");
		labelStudentTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelStudentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelTextAreas.add(labelStudentTitle);
		
		JPanel panel = new JPanel();
		panelTextAreas.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		labelWelcomeMsg = new JLabel("Welcome");
		labelWelcomeMsg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelWelcomeMsg.setVerticalAlignment(SwingConstants.TOP);
		labelWelcomeMsg.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelWelcomeMsg, BorderLayout.CENTER);
		
		JLabel labelAllCourses = new JLabel("All Courses:");
		labelAllCourses.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(labelAllCourses, BorderLayout.SOUTH);
		JScrollPane scrollAllPane = new JScrollPane();
		scrollAllPane.setPreferredSize(new Dimension(200, 2));
		panelTextAreas.add(scrollAllPane);
		
		textAreaAllCourses = new JTextArea();
		scrollAllPane.setViewportView(textAreaAllCourses);
		
		JLabel labelUserCourses = new JLabel("My Courses:");
		labelUserCourses.setVerticalAlignment(SwingConstants.BOTTOM);
		labelUserCourses.setHorizontalAlignment(SwingConstants.CENTER);
		panelTextAreas.add(labelUserCourses);
		JScrollPane scrollUserPane = new JScrollPane();
		panelTextAreas.add(scrollUserPane);
		
		textAreaUserCourses = new JTextArea();
		scrollUserPane.setViewportView(textAreaUserCourses);
		
		JPanel panelLogout = new JPanel();
		contentPane.add(panelLogout, BorderLayout.SOUTH);
		
		buttonLogout = new JButton("Logout");
		panelLogout.add(buttonLogout);
		
		addCourseView = new StudentAddCourseView();
		removeCourseView = new StudentRemoveCourseView();
		searchCourseView = new StudentSearchCourseView();
	}
	
	/**
	 * Setter for the all courses text area
	 * @param courses All courses in the course catalog
	 */
	public void setAllCoursesText(String courses) {
		textAreaAllCourses.setText(courses);
	}
	/**
	 * Setter for the student's registered courses
	 * @param courses Student's registered courses
	 */
	public void setUserCoursesText(String courses) {
		textAreaUserCourses.setText(courses);
	}
	/**
	 * Setter for the welcome message containing the student's user name
	 * @param username The student's user name
	 */
	public void setWelcomeMessage(String username) {
		labelWelcomeMsg.setText("Welcome, " + username);
	}
	
	/**
	 * Shows the StudentView frame
	 */
	public void showFrame() {
		setVisible(true);
	}
	/**
	 * Closes the StudentView frame, clears all text areas
	 */
	public void closeFrame() {
		setVisible(false);
		textAreaAllCourses.setText("");
		textAreaUserCourses.setText("");
		labelWelcomeMsg.setText("Welcome");
	}
	
	public StudentAddCourseView getStudentAddCourseView()
	{
		return this.addCourseView;
	}
	public StudentRemoveCourseView getStudentRemoveCourseView()
	{
		return this.removeCourseView;
	}
	public StudentSearchCourseView getStudentSearchCourseView()
	{
		return this.searchCourseView;
	}
}
