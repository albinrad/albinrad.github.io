package studentcoursemanager.adminclient.frontend;
import java.awt.BorderLayout;
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
 * View that prompts the admin for a specific course to create.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class AdminCreateCourseView extends JFrame {
	/**
	 * Text field for the course name
	 */
	private JTextField fieldCourseName;
	/**
	 * Text field for the course number
	 */
	private JTextField fieldCourseNum;
	
	/**
	 * Button to complete creating the course
	 */
	protected JButton buttonCreateCourse;
	/**
	 * Button to return to the main admin menu
	 */
	protected JButton buttonReturn;

	/**
	 * Creates the AdminCreateCourseView frame
	 */
	public AdminCreateCourseView() {
		setResizable(false);
		setTitle("Create Course");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 120);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel createCourseTitle = new JLabel("Create a Course");
		createCourseTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		createCourseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(createCourseTitle, BorderLayout.NORTH);
		
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
		
		buttonCreateCourse = new JButton("Create");
		buttonPanel.add(buttonCreateCourse);
		buttonReturn = new JButton("Return to Menu");
		buttonPanel.add(buttonReturn);
	}
	
	/**
	 * Getter for the course name to create
	 * @return The course name
	 */
	public String getCreateCourseName() {
		return fieldCourseName.getText();
	}
	/**
	 * Getter for the course number to create
	 * @return The course number
	 */
	public String getCreateCourseNum() {
		return fieldCourseNum.getText();
	}
	
	/**
	 * Shows the frame
	 */
	public void showFrame() {
		setVisible(true);
	}
	/**
	 * Close the frame, clears all text fields
	 */
	public void closeFrame() {
		setVisible(false);
		fieldCourseName.setText("");
		fieldCourseNum.setText("");
	}
}
