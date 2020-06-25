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
 * View that prompts the student for a specific course to drop.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class StudentRemoveCourseView extends JFrame {
	/**
	 * Text field for the course name
	 */
	private JTextField fieldCourseName;
	/**
	 * Text field for the course number
	 */
	private JTextField fieldCourseNum;
	
	/**
	 * Button to complete dropping the course
	 */
	protected JButton buttonRemoveCourse;
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
					StudentRemoveCourseView frame = new StudentRemoveCourseView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the StudentRemoveCourseView frame
	 */
	public StudentRemoveCourseView() {
		setResizable(false);
		setTitle("Drop Course");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 120);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel removeCourseTitle = new JLabel("Drop a Course");
		removeCourseTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		removeCourseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(removeCourseTitle, BorderLayout.NORTH);
		
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
		
		buttonRemoveCourse = new JButton("Drop");
		buttonPanel.add(buttonRemoveCourse);
		buttonReturn = new JButton("Return to Menu");
		buttonPanel.add(buttonReturn);
	}
	
	/**
	 * Getter for the course name to drop
	 * @return The course name
	 */
	public String getRemoveCourseName() {
		return fieldCourseName.getText();
	}
	/**
	 * Getter for the course number to drop
	 * @return The course number
	 */
	public String getRemoveCourseNum() {
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
