package studentcoursemanager.client.frontend;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import studentcoursemanager.adminclient.frontend.AdminView;

/**
 * A class that contains functionality for all GUI on the client's end.
 * 
 * @author Albin Radaj (Partner: Nadim Asaduzzaman)
 * @version 1.0
 * @since April 6, 2020
 */
@SuppressWarnings("serial")
public class ClientView extends JFrame {
	/**
	 * The first view that will be visible to the client to connect to a server
	 */
	private ConnectToServerView connectServerView;
	/**
	 * The view that shows once connection to server is successful
	 */
	private ConnectionSuccessfulView connectSuccessView;
	/**
	 * The view that prompts the user to login
	 */
	private LoginView loginView;
	/**
	 * The main view that shows when a student client logs in
	 */
	private StudentView studentView;
	/**
	 * The main view that shows when an admin client logs in
	 */
	private AdminView adminView;
	/**
	 * Initializes each view
	 */
	public ClientView()
	{
		connectServerView = new ConnectToServerView();
		connectSuccessView = new ConnectionSuccessfulView();
		loginView = new LoginView();
		studentView = new StudentView();
		adminView = new AdminView();
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
	 * Shows the first frame that will be visible to the user
	 */
	public void showConnectFrame()
	{
		connectServerView.showFrame();
	}
	/**
	 * Closes the first frame that will be visible to the user
	 */
	public void closeConnectFrame()
	{
		connectServerView.closeFrame();
	}
	/**
	 * Shows the Connection Successful view
	 */
	public void showConnectionSuccessFrame()
	{
		connectSuccessView.showFrame();
	}
	/**
	 * Closes the Connection Successful view
	 */
	public void closeConnectionSuccessFrame()
	{
		connectSuccessView.closeFrame();
	}
	/**
	 * Shows the Login view
	 */
	public void showLoginFrame()
	{
		loginView.showFrame();
	}
	/**
	 * Closes the Login view
	 */
	public void closeLoginFrame()
	{
		loginView.closeFrame();
	}
	/**
	 * Shows the Student Profile view
	 */
	public void showStudentProfileFrame()
	{
		studentView.showFrame();
	}
	/**
	 * Closes the Student Profile view
	 */
	public void closeStudentProfileFrame()
	{
		studentView.closeFrame();
	}
	/**
	 * Shows the Student Add Course view
	 */
	public void showStudentAddCourseFrame()
	{
		studentView.addCourseView.showFrame();
	}
	/**
	 * Closes the Student Add Course view
	 */
	public void closeStudentAddCourseFrame()
	{
		studentView.addCourseView.closeFrame();
	}
	/**
	 * Shows the Student Remove Course view
	 */
	public void showStudentRemoveCourseFrame()
	{
		studentView.removeCourseView.showFrame();
	}
	/**
	 * Closes the Student Remove Course view
	 */
	public void closeStudentRemoveCourseFrame()
	{
		studentView.removeCourseView.closeFrame();
	}
	/**
	 * Shows the Student Search Course view
	 */
	public void showStudentSearchCourseFrame()
	{
		studentView.searchCourseView.showFrame();
	}
	/**
	 * Closes the Student Search Course view
	 */
	public void closeStudentSearchCourseFrame()
	{
		studentView.searchCourseView.closeFrame();
	}
	/**
	 * Shows the Admin Profile view
	 */
	public void showAdminProfileFrame()
	{
		adminView.showFrame();
	}
	/**
	 * Closes the Admin Profile view
	 */
	public void closeAdminProfileFrame()
	{
		adminView.closeFrame();
	}

	/**
	 * Use to get the login view for client
	 * @return Login View of client
	 */
	public LoginView getLoginView()
	{
		return this.loginView;
	}
	
	/* GETTERS AND SETTERS FOR ALL FRAMES
	 * NOTE: Documentation for this area not necessary, 
	 * refer to individual views for full documentation
	 */
	// ConnectToServerView getters
	public String getHostAddress()
	{
		return connectServerView.getHostAddress();
	}
	public String getHostPort()
	{
		return connectServerView.getHostPort();
	}
	// LoginView getters
	public String getStudentID()
	{
		return loginView.getStudentID();
	}
//	public String getLoginPassword()
//	{
//		return loginView.getLoginPassword();
//	}
	// StudentView setters
	public void setStudentAllCoursesText(String courses)
	{
		studentView.setAllCoursesText(courses);
	}
	public void setStudentUserCoursesText(String courses)
	{
		studentView.setUserCoursesText(courses);
	}
	public void setStudentWelcomeMsg(String username)
	{
		studentView.setWelcomeMessage(username);
	}
	// StudentAddCourseView getters
	public String getStudentAddCourseName() {
		return studentView.addCourseView.getAddCourseName();
	}
	public String getStudentAddCourseNum() {
		return studentView.addCourseView.getAddCourseNum();
	}
	public String getStudentAddCourseSection() {
		return studentView.addCourseView.getAddCourseSection();
	}
	// StudentRemoveCourseView getters
	public String getStudentRemoveCourseName() {
		return studentView.removeCourseView.getRemoveCourseName();
	}
	public String getStudentRemoveCourseNum() {
		return studentView.removeCourseView.getRemoveCourseNum();
	}
	// StudentSearchCourseView getters / show dialog
	public String getStudentSearchCourseName() {
		return studentView.searchCourseView.getSearchCourseName();
	}
	public String getStudentSearchCourseNum() {
		return studentView.searchCourseView.getSearchCourseNum();
	}
	public void showStudentCourse(String course) { 
		JOptionPane.showMessageDialog(studentView, course);
	}
	// AdminView setters
	public void setAdminAllCoursesText(String courses) {
		adminView.setAdminAllCourses(courses);
	}
	
	// LISTENERS FOR ALL BUTTONS
	// ConnectToServerView listeners
	public void addConnectListener(ActionListener listener) {
		connectServerView.buttonConnect.addActionListener(listener);
	}
	public void addQuitListener(ActionListener listener) {
		connectServerView.buttonQuit.addActionListener(listener);
	}
	// ConnectionSuccessfulView listeners
	public void addAuthenticateListener(ActionListener listener) { 
		connectSuccessView.buttonAuthenticate.addActionListener(listener);
	}
	public void addSuccessDisconnectListener(ActionListener listener) { 
		connectSuccessView.buttonDisconnect.addActionListener(listener);
	}
	// LoginView listeners
	public void addLoginSubmitListener(ActionListener listener) {
		loginView.buttonCompleteLogin.addActionListener(listener);
	}
	public void addLoginDisconnectListener(ActionListener listener) {
		loginView.buttonDisconnect.addActionListener(listener);
	}
	// StudentView listeners
	public void addStudentSearchCourseListener(ActionListener listener) {
		studentView.buttonSearchCourses.addActionListener(listener);
	}
	public void addStudentListAllCoursesListener(ActionListener listener) {
		studentView.buttonListAllCourses.addActionListener(listener);
	}
	public void addStudentListUserCoursesListener(ActionListener listener) {
		studentView.buttonListUserCourses.addActionListener(listener);
	}
	public void addStudentAddCourseListener(ActionListener listener) {
		studentView.buttonAddUserCourse.addActionListener(listener);
	}
	public void addStudentRemoveCourseListener(ActionListener listener) {
		studentView.buttonRemoveUserCourse.addActionListener(listener);
	}
	public void addStudentLogoutListener(ActionListener listener) {
		studentView.buttonLogout.addActionListener(listener);
	}
	// StudentSearchCourseView listeners
	public void addStudentSearchSubmitListener(ActionListener listener) {
		studentView.searchCourseView.buttonSearchCourse.addActionListener(listener);
	}
	public void addStudentSearchReturnListener(ActionListener listener) {
		studentView.searchCourseView.buttonReturn.addActionListener(listener);
	}
	// StudentAddCourseView listeners
	public void addStudentAddSubmitListener(ActionListener listener) {
		studentView.addCourseView.buttonAddCourse.addActionListener(listener);
	}
	public void addStudentAddReturnListener(ActionListener listener) {
		studentView.addCourseView.buttonReturn.addActionListener(listener);
	}
	// StudentRemoveCourseView listeners
	public void addStudentRemoveSubmitListener(ActionListener listener) {
		studentView.removeCourseView.buttonRemoveCourse.addActionListener(listener);
	}
	public void addStudentRemoveReturnListener(ActionListener listener) {
		studentView.removeCourseView.buttonReturn.addActionListener(listener);
	}
	public StudentSearchCourseView getStudentSearchView() {
		return this.studentView.getStudentSearchCourseView();
	}
	public StudentAddCourseView getStudentAddCourseView()
	{
		return this.studentView.getStudentAddCourseView();
	}
	public StudentRemoveCourseView getStudentRemoveCourseView()
	{
		return this.studentView.getStudentRemoveCourseView();
	}
	public StudentSearchCourseView getStudentSearchCourseView()
	{
		return this.studentView.getStudentSearchCourseView();
	}
}
