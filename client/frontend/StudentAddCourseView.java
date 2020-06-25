package studentcoursemanager.client.frontend;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * View that prompts the student for a specific course to register.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class StudentAddCourseView extends JFrame {
	/**
	 * Text field for the course name
	 */
	private JTextField fieldCourseName;
	/**
	 * Text field for the course number
	 */
	private JTextField fieldCourseNum;
	/**
	 * Text field for the course section
	 */
	private JTextField fieldCourseSection;
	
	/**
	 * Button to complete registering the course
	 */
	protected JButton buttonAddCourse;
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
					StudentAddCourseView frame = new StudentAddCourseView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the StudentAddCourseView frame
	 */
	public StudentAddCourseView() {
		setResizable(false);
		setTitle("Register Course");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel addCourseTitle = new JLabel("Register a Course");
		addCourseTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		addCourseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(addCourseTitle, BorderLayout.NORTH);
		
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
		
		JLabel labelCourseSection = new JLabel("Enter Course Section: ");
		multiInputPanel.add(labelCourseSection);
		fieldCourseSection = new JTextField();
		multiInputPanel.add(fieldCourseSection);
		fieldCourseSection.setColumns(5);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		buttonAddCourse = new JButton("Add");
		buttonPanel.add(buttonAddCourse);
		buttonReturn = new JButton("Return to Menu");
		buttonPanel.add(buttonReturn);
	}
	
	/**
	 * Getter for the course name to register
	 * @return The course name
	 */
	public String getAddCourseName() {
		return fieldCourseName.getText();
	}
	/**
	 * Getter for the course number to register
	 * @return The course number
	 */
	public String getAddCourseNum() {
		return fieldCourseNum.getText();
	}
	/**
	 * Getter for the course section to register
	 * @return The course section
	 */
	public String getAddCourseSection() {
		return fieldCourseSection.getText();
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
