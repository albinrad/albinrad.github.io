package studentcoursemanager.server.resource;

import java.util.ArrayList;

/**
 * A model class that represents an offering for a course
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class CourseOffering
{
	/**
	 * Section number of the offering
	 */
	private int secNum;
	/**
	 * Section capacity for the offering
	 */
	private int secCap;
	/**
	 * Course that is represented bt offering
	 */
	private Course theCourse;
	/**
	 * List of Registrations for offering
	 */
	private ArrayList<CourseRegistration> offeringRegList;
	
	/**
	 * Constructs a new offering object setting the section number and capacity
	 * @param secNum the section number for offering
	 * @param secCap the section capacity for offering
	 */
	public CourseOffering (int secNum, int secCap)
	{
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <CourseRegistration>();
	}
	/**
	 * Method to use to get total number of registered for offering
	 * @return total students registered for offering
	 */
	public int getTotalReg()
	{
		return offeringRegList.size();
	}
	/**
	 * Getter function to get section's number
	 * @return section's number
	 */
	public int getSecNum()
	{
		return secNum;
	}
	/**
	 * Setter fucntion to set the section's number
	 * @param secNum new section number
	 */
	public void setSecNum(int secNum)
	{
		this.secNum = secNum;
	}
	/**
	 * Method to use to get the section's capacity
	 * @return section's capacity
	 */
	public int getSecCap()
	{
		return secCap;
	}
	/**
	 * Method to use to set the course for the offering
	 * @param secCap course to set offering to
	 */
	public void setSecCap(int secCap)
	{
		this.secCap = secCap;
	}
	/**
	 * Method to use to get the course represented by the offering
	 * @return the course represented by the offering
	 */
	public Course getTheCourse()
	{
		return theCourse;
	}
	/**
	 * Method to use to set the course for the offering
	 * @param theCourse course to set offering to
	 */
	public void setTheCourse(Course theCourse)
	{
		this.theCourse = theCourse;
	}
	/**
	 * Method to use to check if offering is full
	 * @return true if offering is full, false otherwise
	 */
	public boolean isFull()
	{
		boolean isFull = false;
		if(this.offeringRegList.size() < this.secCap)
		{
			isFull = true;
		}
		return isFull;
	}
	@Override
	public String toString () {
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\n";
		for(CourseRegistration reg : offeringRegList)
		{
			st += reg.getTheStudent() + "\n";
		}
		return st;
	}
	/**
	 * Method to add a student registration for course offering
	 * @param registration new registration to add
	 */
	public void addRegistration(CourseRegistration registration)
	{
		offeringRegList.add(registration);
	}
	/**
	 * Method to remove a student registration from course offering
	 * @param registration registration to remove
	 */
	public void removeRegistration(CourseRegistration registration)
	{
		if(registration != null)
		{
			offeringRegList.remove(registration);
		}
	}
}