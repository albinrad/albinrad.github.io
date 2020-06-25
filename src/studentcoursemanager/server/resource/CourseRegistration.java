package studentcoursemanager.server.resource;

import studentcoursemanager.server.exceptions.CoursePreReqException;
import studentcoursemanager.server.exceptions.RegistrationException;

public class CourseRegistration
{
	private Student theStudent;
	private CourseOffering theOffering;
	private char grade;
	public void completeRegistration(Student st, CourseOffering to) throws RegistrationException, CoursePreReqException
	{
		theStudent = st;
		theOffering = to;
		if(theStudent.meetCoursePreReq(theOffering.getTheCourse()))
		{
			addRegistration();
			return;
        }
        coursePreReqConditionFail();
	}
	private void coursePreReqConditionFail() throws CoursePreReqException
	{
        StringBuilder preReqErrorMessage = new StringBuilder();
        preReqErrorMessage.append("Student does not meet course prerequisites for course" + this.theOffering.getTheCourse().getCourseName() + this.theOffering.getTheCourse().getCourseNum() + ". Student must take the following listed course before they can register for this course:");
		for(Course preReqCourse : theOffering.getTheCourse().getPreReqs())
		{
			for(CourseRegistration studentReg : this.theStudent.getStudentRegList())
			{
				if(preReqCourse.equals(studentReg.getTheOffering().getTheCourse()))
				{
					break;
				}
			}
			preReqErrorMessage.append(preReqCourse + "\n");
		}
        throw new CoursePreReqException(preReqErrorMessage.toString());
	}
	private void addRegistration() throws RegistrationException
	{
		if(theStudent.getTotalRegisteredCourses() < 6)
		{
			if(theOffering.getTotalReg() < theOffering.getSecCap())
			{
				theStudent.addRegistration(this);
				theOffering.addRegistration(this);
				return;
            }
            throw new RegistrationException("Unable to complete registation as the selected course offering has reached maximum capacity. Try a different course offering if available.");
        }
        throw new RegistrationException("Unable to complete registration as student has reached course registration limit.");
	}
	public Student getTheStudent()
	{
		return theStudent;
	}
	public void setTheStudent(Student theStudent)
	{
		this.theStudent = theStudent;
	}
	public CourseOffering getTheOffering()
	{
		return theOffering;
	}
	public void setTheOffering(CourseOffering theOffering)
	{
		this.theOffering = theOffering;
	}
	public char getGrade()
	{
		return grade;
	}
	public void setGrade(char grade)
	{
		this.grade = grade;
	}
	@Override
	public String toString()
	{
		String st = "\n";
		st += "Student Name:	" + getTheStudent() + "\n";
		st += "The Offering:	" + getTheOffering () + "\n";
		st += "Grade:			" + getGrade();
		st += "\n-----------\n";
		return st;
	}
	public void cancelRegistration()
	{
		if(theOffering == null || theStudent == null)
		{
			return;
		}
		theOffering.removeRegistration(this);
		theStudent.removeRegistration(this);
	}
}