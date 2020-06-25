package studentcoursemanager.server.resource;

import java.util.ArrayList;

/**
 * A model class that represents a student
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class Student
{
	/**
	 * Name of student
	 */
	private String studentName;
	/**
	 * ID of student
	 */
	private int studentId;
	/**
	 * List of registration for student
	 */
	private ArrayList<CourseRegistration> studentRegList;
	/**
	 * Constructs a new Student object by setting the name and ID for the student
	 * @param studentName
	 * @param studentId
	 */
	public Student(String studentName, int studentId)
	{
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<CourseRegistration>();
	}
	/**
	 * Method to get the total number of courses registered by user
	 * @return the total number of registered courses
	 */
	public int getTotalRegisteredCourses()
	{
		return studentRegList.size();
	}
	/**
	 * Method to use to get student's list of course registrations
	 * @return student's list of course registrations
	 */
	public ArrayList<CourseRegistration> getStudentRegList()
	{
		return studentRegList;
	}
	/**
	 * Getter fucntion to get student's current name
	 * @return student's name
	 */
	public String getStudentName()
	{
		return studentName;
	}
	/**
	 * Setter method to set student's name
	 * @param studentName student's new name
	 */
	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}
	/**
	 * Getter function to get student's ID
	 * @return student's ID
	 */
	public int getStudentId()
	{
		return studentId;
	}
	/**
	 * Setter function to set the ID number for student
	 * @param studentId student's new ID number
	 */
	public void setStudentId(int studentId)
	{
		this.studentId = studentId;
	}
	/**
	 * Method to use to check if student meets pre-requisite for course specified
	 * @param courseToCheck course to check pre-req for
	 * @return true if student has taken all pre-req courses required, false otherwise.
	 */
	public boolean meetCoursePreReq(Course courseToCheck)
	{
		ArrayList<Course> preReqs = courseToCheck.getPreReqs();
		boolean meetReq = true;
		for (Course preReqCourse : preReqs)
		{
			if(meetReq)
			{
				boolean preReqFound = false;
				for (CourseRegistration reg : studentRegList)
				{
					if(reg.getTheOffering().getTheCourse() == preReqCourse)
					{
						preReqFound = true;
						break;
					}
				}
				if (!preReqFound)
				{
					meetReq = false;
				}
			}
			else
			{
				break;
			}
		}
		return meetReq;
	}
	@Override
	public String toString()
	{
		String st = "Student Name: " + getStudentName() + "\n" + "Student Id: " + getStudentId() + "\n\n";
		return st;
	}
	/**
	 * Method to use to add new registration for student
	 * @param registration registration to add
	 */
	public void addRegistration(CourseRegistration registration)
	{
		studentRegList.add(registration);
	}
	/**
	 * Method to use to remove registration from student's profile
	 * @param registration registration to remove
	 */
	public void removeRegistration(CourseRegistration registration)
	{
		if(studentRegList != null)
		{
			studentRegList.remove(registration);
		}
	}
}