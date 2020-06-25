package studentcoursemanager.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import studentcoursemanager.client.frontend.ClientView;
import studentcoursemanager.server.clientcommunication.DropCourseException;

/**
 * Controller class thats controls the view and model provided
 * 
 * @author Nadim Asaduzzaman
 * @version 1.0
 * @since April 11, 2020
 */
public class ClientController
{
	/**
	 * Main view of clinet
	 */
	private ClientModel theModel;
	/**
	 * Main view of clinet
	 */
	private ClientView theView;
	/**
	 * Constructs a new ClientController object for both the view and model specified
	 * @param theView
	 * @param theModel
	 */
	public ClientController(ClientView theView, ClientModel theModel)
	{
		this.theModel = theModel;
		this.theView = theView;
		this.theView.addConnectListener(new ConnectListener());
		this.theView.addQuitListener(new QuitListener());
		this.theView.addAuthenticateListener(new AuthenticateListener());
		this.theView.addSuccessDisconnectListener(new SuccessDisconnectListener());
		this.theView.addLoginSubmitListener(new LoginSubmitListener());
		this.theView.addLoginDisconnectListener(new LoginDisconnectListener());
		this.theView.addStudentSearchCourseListener(new StudentSearchCourseListener());
		this.theView.addStudentListAllCoursesListener(new StudentListAllCoursesListener());
		this.theView.addStudentListUserCoursesListener(new StudentListUserCoursesListener());
		this.theView.addStudentAddCourseListener(new StudentAddCourseListener());
		this.theView.addStudentRemoveCourseListener(new StudentRemoveCourseListener());
		this.theView.addStudentLogoutListener(new StudentLogoutListener());
		this.theView.addStudentSearchSubmitListener(new StudentSearchSubmitListener());
		this.theView.addStudentSearchReturnListener(new StudentSearchReturnListener());
		this.theView.addStudentAddSubmitListener(new StudentAddSubmitListener());
		this.theView.addStudentAddReturnListener(new StudentAddReturnListener());
		this.theView.addStudentRemoveSubmitListener(new StudentRemoveSubmitListener());
		this.theView.addStudentRemoveReturnListener(new StudentRemoveReturnListener());
	}

	class ConnectListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String hostAddress = theView.getHostAddress();
			String port = theView.getHostPort();
			if(hostAddress == null || hostAddress.length() == 0)
			{
				theView.showErrorMsg(theView, "The Host Address field is empty.");
				return;
			}
			if(port == null || port.length() == 0)
			{
				theView.showErrorMsg(theView, "The Port field is empty.");
				return;
			}
			try
			{
				int portNum = Integer.parseInt(port);
				theModel.connectToServer(hostAddress, portNum);
				theView.closeConnectFrame();
				theView.showConnectionSuccessFrame();
			}
			catch(NumberFormatException err)
			{
				theView.showErrorMsg(theView, "The Port specified is not a valid integer.");
				return;
			}
			catch (UnknownHostException e1)
			{
				theView.showErrorMsg(theView, "Cannot connect to host. Please make sure the server is active and running.");
				return;
			}
			catch (IOException e1)
			{
				theView.showErrorMsg(theView, "IOException while connecting to host.\nError Message: " + e1.getMessage());
				return;
			}
		}
	}

	class QuitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeConnectFrame();
		}
	}
	
	class AuthenticateListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeConnectionSuccessFrame();
			theView.showLoginFrame();
		}
	}
	
	class SuccessDisconnectListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				theModel.disconnectFromServer();
			}
			catch (IOException e1)
			{

			}
			theView.closeConnectionSuccessFrame();
			theView.showConnectFrame();
		}
	}
	
	class LoginSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String studentID = theView.getStudentID();
			if(studentID == null || studentID.length() == 0)
			{
				theView.showErrorMsg(theView.getLoginView(), "The Password field is empty.");
				return;
			}
			try
			{
				theModel.login(studentID);
				theView.closeLoginFrame();
				theView.showStudentProfileFrame();
			}
			catch(LoginException e1)
			{
				theView.showErrorMsg(theView.getLoginView(), e1.getMessage());
				return;
			}
			catch(IOException e1)
			{
				theView.showErrorMsg(theView.getLoginView(), "IOException while communicating with server. Disconnected from server.");
				theView.closeLoginFrame();
				try
				{
					theModel.disconnectFromServer();
				}
				catch(IOException e2)
				{

				}
				theView.showConnectFrame();
				return;
			}
		}
	}
	
	class LoginDisconnectListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				theModel.disconnectFromServer();
			}
			catch (IOException e1)
			{
				theView.showErrorMsg(theView.getLoginView(), "IOException while listing course catalogue");
				return;
			}
			theView.closeLoginFrame();
			theView.showConnectFrame();
		}
	}
	
	class StudentSearchCourseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.showStudentSearchCourseFrame();
		}
	}
	
	class StudentListAllCoursesListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				theView.setStudentAllCoursesText(theModel.showCourseCatalogue());
			}
			catch (IOException e1)
			{
				theView.showErrorMsg(theView, "IOException while listing course catalogue");
				return;
			}
		}
	}
	
	class StudentListUserCoursesListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				theView.setStudentUserCoursesText(theModel.showStudentCourses());
			}
			catch(IOException e1)
			{
				theView.showErrorMsg(theView, "IOException while listing student courses");
				return;
			}
		}
	}
	
	class StudentAddCourseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.showStudentAddCourseFrame();
		}
	}
	
	class StudentRemoveCourseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.showStudentRemoveCourseFrame();
		}
	}
	
	class StudentLogoutListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeStudentProfileFrame();
			theModel.logout();
			theView.showLoginFrame();
		}
	}
	
	class StudentSearchSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				String courseName = theView.getStudentSearchCourseName();
				if(courseName == null || courseName.length() == 0)
				{
					theView.showErrorMsg(theView.getStudentSearchView(), "Course Name field is empty!");
					return;
				}
				String courseNumStr = theView.getStudentSearchCourseNum();
				if(courseNumStr == null || courseNumStr.length() == 0)
				{
					theView.showErrorMsg(theView.getStudentSearchView(), "Course Number field is empty!");
					return;
				}
				int courseNum = Integer.parseInt(courseNumStr);
				String response = theModel.searchCourse(courseName, courseNum);
				theView.showMsg(theView.getStudentSearchView(), response);
			}
			catch(NumberFormatException e1)
			{
				theView.showErrorMsg(theView.getStudentSearchView(), "Course Number is not an integer value!");
				return;
			}
			catch(SearchCourseException e1)
			{
				theView.showErrorMsg(theView.getStudentSearchView(), e1.getMessage());
				return;
			}
			catch(IOException e1)
			{
				theView.showErrorMsg(theView.getStudentSearchView(), "IOException while searching for course");
				return;
			}

			theView.closeStudentSearchCourseFrame();
		}
	}
	
	class StudentSearchReturnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeStudentSearchCourseFrame();
		}
	}
	
	class StudentAddSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{

			try
			{
				String courseFaculty = theView.getStudentAddCourseName();
				String courseNumStr = theView.getStudentAddCourseNum();
				String courseSectionStr = theView.getStudentAddCourseSection();
				int courseNumber = Integer.parseInt(courseNumStr);
				int sectionNumber = Integer.parseInt(courseSectionStr);
				theModel.registerCourse(courseFaculty, courseNumber, sectionNumber);
				theView.showMsg(theView.getStudentAddCourseView(), "Registration successful!");
			}
			catch(CourseRegistrationException e1)
			{
				theView.showErrorMsg(theView.getStudentAddCourseView(), e1.getMessage());
				return;
			}
			catch(NumberFormatException e1)
			{
				theView.showErrorMsg(theView.getStudentAddCourseView(), "Please check the course number and/or section number. It must be an integer.");
				return;
			}
			catch(IOException e1)
			{
				theView.showErrorMsg(theView.getStudentAddCourseView(), "IOException while communicating with server\n Error Message: " + e1.getMessage());
				return;
			}
			theView.closeStudentAddCourseFrame();
		}
	}
	
	class StudentAddReturnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeStudentAddCourseFrame();
		}
	}
	
	class StudentRemoveSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				String courseFaculty = theView.getStudentRemoveCourseName();
				String courseNumStr = theView.getStudentRemoveCourseNum();
				int courseNumber = Integer.parseInt(courseNumStr);
				theModel.dropCourse(courseFaculty, courseNumber);
				theView.showMsg(theView.getStudentRemoveCourseView(), "Dropped course successfully!");
			}
			catch(DropCourseException e1)
			{
				theView.showErrorMsg(theView.getStudentRemoveCourseView(), e1.getMessage());
				return;
			}
			catch(NumberFormatException e1)
			{
				theView.showErrorMsg(theView.getStudentRemoveCourseView(), "Please check the course number. It must be an integer.");
				return;
			}
			catch (IOException e1)
			{
				theView.showErrorMsg(theView.getStudentRemoveCourseView(), "IOException while communicating with server.\nError Message: " + e1.getMessage());
				return;
			}
			
			theView.closeStudentAddCourseFrame();
		}
	}
	
	class StudentRemoveReturnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeStudentRemoveCourseFrame();
		}
	}
}