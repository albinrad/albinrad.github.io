package studentcoursemanager.server.resource;

import java.util.ArrayList;

import studentcoursemanager.server.exceptions.CourseException;

/**
 * A class that represents a course
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class Course
{
	/**
	 * Name of course
	 */
	private String courseName;
	/**
	 * Number of course
	 */
	private int courseNum;
	/**
	 * List of pre-requisite courses needed for course
	 */
	private ArrayList<Course> preReq;
	/**
	 * List of offerings available for course
	 */
	private ArrayList<CourseOffering> offeringList;

	/**
	 * Constructrs a new Course object by inititializing the course name and number
	 * @param courseName the course name to set
	 * @param courseNum the course number to set
	 */
	public Course(String courseName, int courseNum)
	{
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}
	/**
	 * Method to use to add a new pre-req course to current course
	 * @param preReqCourse pre-req course to add
	 */
	public void addPreReqCourse(Course preReqCourse)
	{
		preReq.add(preReqCourse);
	}
	/**
	 * Method to use to get list of pre-req courses for current course
	 * @return the list of pre-req courses
	 */
	public ArrayList<Course> getPreReqs()
	{
		return this.preReq;
	}
	/**
	 * Method to check if student meets course requirment for current course
	 * @param student to check for pre-req courses
	 * @return true if student meets course requirrment, false otherwise
	 */
	public boolean meetCoursePreReq(Student student)
	{
		return student.meetCoursePreReq(this);
	}
	/**
	 * Method to use to add offering to the course
	 * @param offering new offering to add
	 * @throws CourseException if any issue occurs while adding course
	 */
	public void addOffering(CourseOffering offering) throws CourseException
	{
		if (offering != null && offering.getTheCourse() == null)
		{
			offering.setTheCourse(this);
			if(!offering.getTheCourse().getCourseName().equals(courseName) || offering.getTheCourse().getCourseNum() != courseNum)
			{
                throw new CourseException("Error! This section belongs to another course!");
			}
			offeringList.add(offering);
		}
	}
	public String getCourseName()
	{
		return courseName;
	}
	public void setCourseName(String courseName)
	{
		this.courseName = courseName;
	}
	public int getCourseNum()
	{
		return courseNum;
	}
	public void setCourseNum(int courseNum)
	{
		this.courseNum = courseNum;
	}
	@Override
	public String toString()
	{
		String st = "\n";
		st += getCourseName() + " " + getCourseNum();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
		{
			st += c;
		}
		st += "\n-------\n";
		return st;
	}
	/**
	 * Method to use to get offering at a specific position
	 * @param i positing to get offering from
	 * @return offering at position specificed, null if not found
	 */
	public CourseOffering getCourseOfferingAt(int i)
	{
		if (i < 0 || i >= offeringList.size())
		{
			return null;
		}
		else
		{
			return offeringList.get(i);
		}
	}
}