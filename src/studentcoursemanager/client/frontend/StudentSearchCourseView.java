package studentcoursemanager.client.frontend;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * View that prompts the student for a specific course to search.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class StudentSearchCourseView extends JFrame {
	/**
	 * Text field for the course name
	 */
	private JTextField fieldCourseName;
	/**
	 * Text field for the course number
	 */
	private JTextField fieldCourseNum;
	
	/**
	 * Button to complete searching the course
	 */
	protected JButton buttonSearchCourse;
	/**
	 * Button to return to the main student menu
	 */
	protected JButton buttonReturn;

	/**
	 * Launch the application. - TEMPORARY for MS-2 and testing
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentSearchCourseView frame = new StudentSearchCourseView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the StudentSearchCourseView frame
	 */
	public StudentSearchCourseView() {
		setResizable(false);
		setTitle("Search Course");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 120);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel searchCourseTitle = new JLabel("Search a Course");
		searchCourseTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		searchCourseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(searchCourseTitle, BorderLayout.NORTH);
		
		JPanel multiInputPanel = new JPanel();
		contentPane.add(multiInputPanel, BorderLayout.CENTER);
		multiInputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel labelCourseName = new JLabel("Enter Course Name: ");
		multiInputPanel.add(labelCourseName);
		fieldCourseName = new JTextField();
		multiInputPanel.add(fieldCourseName);
		fieldCourseName.setColumns(5);
		
		JLabel labelCourseNum = new JLabel("  Enter Course Number: ");
		multiInputPanel.add(labelCourseNum);
		fieldCourseNum = new JTextField();
		multiInputPanel.add(fieldCourseNum);
		fieldCourseNum.setColumns(5);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonSearchCourse = new JButton("Search");
		buttonPanel.add(buttonSearchCourse);
		buttonReturn = new JButton("Return to Menu");
		buttonPanel.add(buttonReturn);
	}
	
	/**
	 * Getter for the course name to search
	 * @return The course name
	 */
	public String getSearchCourseName() {
		return fieldCourseName.getText();
	}
	/**
	 * Getter for the course number to search
	 * @return The course number
	 */
	public String getSearchCourseNum() {
		return fieldCourseNum.getText();
	}
	
	/**
	 * Shows the frame
	 */
	public void showFrame() {
		setVisible(true);
	}
	/**
	 * Closes the frame, clears all text fields
	 */
	public void closeFrame() {
		setVisible(false);
		fieldCourseName.setText("");
		fieldCourseNum.setText("");
	}
}
