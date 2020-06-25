package studentcoursemanager.server.resource;

import java.util.ArrayList;

import studentcoursemanager.server.exceptions.CourseException;

/**
 * A class that represents a course catalogue to hold a list of courses in
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class CourseCatalogue
{
	/**
	 * List of courses in catalogue
	 */
	private ArrayList<Course> courseList;
	/**
	 * Constructs a new CourseCatalogue object by setting the list
	 * @param courseList initial course list to set to
	 */
	public CourseCatalogue(ArrayList<Course> courseList)
	{
		this.courseList = courseList;
	}
	/**
	 * Method to use to add new course to catalogue
	 * @param courseName Name on new course
	 * @param courseNum Number of new course
	 * @throws CourseException if any problem occurs while adding course
	 */
	public void addCourseToCat(String courseName, int courseNum) throws CourseException
	{
		courseList.add(new Course(courseName, courseNum));
	}
	/**
	 * Method to use to remove course from catalogue
	 * @param courseName Name of course to remove
	 * @param courseNum Number of course ot remove
	 * @throws CourseException if any problem occurs while removing course
	 */
	public void removeCourseFromCat(String courseName, int courseNum) throws CourseException
	{
		for(Course course : this.courseList)
		{
			if(courseName.equals(course.getCourseName()) && courseNum == course.getCourseNum())
			{
				this.courseList.remove(course);
				return;
			}
		}
		throw new CourseException("Unable to find course to remove.");
	}
	/**
	 * Method to use to add new course offering to a course
	 * @param c course to offering for
	 * @param secNum section number of new offering
	 * @param secCap section capacity of new offering
	 * @throws CourseException if any probme occurs while creating course offering
	 */
	public void createCourseOffering(Course c, int secNum, int secCap) throws CourseException
	{
		if(c != null)
		{
			CourseOffering theOffering = new CourseOffering(secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	/**
	 * Method to use to search for a course in the catalogue
	 * @param courseName course name to search
	 * @param courseNum course number to search
	 * @return the course if found, null otherwise
	 */
	public Course searchCat(String courseName, int courseNum)
	{
		for(Course c : courseList)
		{
			if(courseName.equals(c.getCourseName()) && courseNum == c.getCourseNum())
			{
				return c;
			}	
		}
		return null;
	}
	public ArrayList<Course> getCourseList()
	{
		return courseList;
	}
	public void setCourseList(ArrayList<Course> courseList)
	{
		this.courseList = courseList;
	}
	@Override
	public String toString()
	{
		String st = "All courses in the catalogue: \n";
		for (Course c : courseList)
		{
			st += c;
			st += "\n";
		}
		return st;
	}
}