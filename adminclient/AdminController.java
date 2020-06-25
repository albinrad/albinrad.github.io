package studentcoursemanager.adminclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import studentcoursemanager.adminclient.frontend.AdminView;
import studentcoursemanager.client.SearchCourseException;

/**
 * View that prompts the admin for a specific course to create.
 * 
 * @author Nadim Asaduzzaman (Partner: Albin Radaj)
 * @version 1.0
 * @since April 17, 2020
 */
public class AdminController
{
    private AdminView theView;
    private AdminModel theModel;

	public AdminController(AdminView theView, AdminModel theModel)
	{
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addConnectListener(new ConnectListener());
        this.theView.addQuitListener(new QuitListener());
        this.theView.addAdminSearchCourseListener(new AdminSearchCourseListener());
        this.theView.addAdminListAllCoursesListener(new AdminListAllCoursesListener());
        this.theView.addAdminCreateCourseListener(new AdminCreateCourseListener());
        this.theView.addAdminLogoutListener(new AdminLogoutListener());
        this.theView.addAdminCreateSubmitListener(new AdminCreateSubmitListener());
        this.theView.addAdminCreateReturnListener(new AdminCreateReturnListener());
        this.theView.addAdminSearchSubmitListener(new AdminSearchSubmitListener());
        this.theView.addAdminSearchReturnListener(new AdminSearchReturnListener());
	}
	class ConnectListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String host = theView.getHostAddress();
			String portStr = theView.getPort();
			
			try
			{
				int port = Integer.parseInt(portStr);
				theModel.connectToServer(host, port);
				theView.closeLoginFrame();
				theView.setVisible(true);
				
			}
			catch(NumberFormatException err)
			{
				theView.showErrorMsg(theView.getConnectToServerView(), "Port field is not integer");
				return;
			}
			catch (UnknownHostException e1)
			{
				theView.showErrorMsg(theView.getConnectToServerView(), "Unable to connect to host");
				return;
			}
			catch (IOException e1)
			{
				theView.showErrorMsg(theView.getConnectToServerView(), "IOException while connecting with server");
				return;
			}
			
		}
	}
	class QuitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeFrame();
		}
	}
	class AdminSearchCourseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.showAdminSearchCourseFrame();
		}
	}
	class AdminListAllCoursesListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				String catData = theModel.showCourseCatalogue();
				theView.setAdminAllCourses(catData);
			}
			catch (IOException e1)
			{
				theView.showErrorMsg(theView, "IOException");
			}
		}
	}
	class AdminCreateCourseListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.showAdminCreateCourseFrame();
		}
	}
	class AdminLogoutListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeFrame();
		}
	}
	class AdminCreateSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String courseFaculty = theView.getAdminCreateCourseName();
			if(courseFaculty == null || courseFaculty.length() == 0)
			{
				theView.showErrorMsg(theView.getAdminCreateCourseView(), "Course faculty field is empty");
				return;
			}
			String courseNumStr = theView.getAdminCreateCourseNum();
			if(courseNumStr == null || courseNumStr.length() == 0)
			{
				theView.showErrorMsg(theView.getAdminCreateCourseView(), "Course Number field is empty");
				return;
			}
			int courseNum = Integer.parseInt(courseNumStr);
			try
			{
				theModel.addCourse(courseFaculty, courseNum);
				theView.showMsg(theView.getAdminCreateCourseView(), "Added Course Successfully!");
			}
			catch (IOException e1)
			{
				theView.showErrorMsg(theView, "IOException");
			}
			catch (AddCourseException e1)
			{
				theView.showErrorMsg(theView, e1.getMessage());
			}
		}
	}
	class AdminCreateReturnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeAdminCreateCourseFrame();
		}
	}
	class AdminSearchSubmitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				String courseName = theView.getAdminSearchCourseName();
				if(courseName == null || courseName.length() == 0)
				{
					theView.showErrorMsg(theView.getAdminSearchCourseView(), "Course Name field is empty!");
					return;
				}
				String courseNumStr = theView.getAdminSearchCourseNum();
				if(courseNumStr == null || courseNumStr.length() == 0)
				{
					theView.showErrorMsg(theView.getAdminSearchCourseView(), "Course Number field is empty!");
					return;
				}
				int courseNum = Integer.parseInt(courseNumStr);
				String response = theModel.searchCourse(courseName, courseNum);
				theView.showMsg(theView.getAdminSearchCourseView(), response);
			}
			catch(NumberFormatException e1)
			{
				theView.showErrorMsg(theView.getAdminSearchCourseView(), "Course Number is not an integer value!");
				return;
			}
			catch(SearchCourseException e1)
			{
				theView.showErrorMsg(theView.getAdminSearchCourseView(), e1.getMessage());
				return;
			}
			catch(IOException e1)
			{
				theView.showErrorMsg(theView.getAdminSearchCourseView(), "IOException while searching for course");
				return;
			}

			theView.closeAdminSearchCourseFrame();
		}
	}
	class AdminSearchReturnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			theView.closeAdminSearchCourseFrame();
		}
	}
}
