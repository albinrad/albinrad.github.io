package studentcoursemanager.server.clientcommunication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import studentcoursemanager.server.exceptions.CourseException;
import studentcoursemanager.server.exceptions.CoursePreReqException;
import studentcoursemanager.server.exceptions.RegistrationException;

import studentcoursemanager.server.resource.Course;
import studentcoursemanager.server.resource.CourseCatalogue;
import studentcoursemanager.server.resource.CourseOffering;
import studentcoursemanager.server.resource.CourseRegistration;
import studentcoursemanager.server.resource.Student;

/**
 * A class that handles the database for the course manager
 * 
 * @author Nadim Asaduzzaman (Albin Radaj)
 * @version 1.0
 * @since April 6, 2020
 */
public class DBManager
{
    /**
     * list of all registrations
     */
    private ArrayList<CourseRegistration> courseRegistrations;
    /**
     * list of all students
     */
    private ArrayList<Student> students;
    /**
     * course catalogue
     */
    private CourseCatalogue courseCatalogue;

    /**
     * constructs a new DBManager and initializes all lists as empty
     */
    public DBManager()
    {
        this.courseRegistrations = new ArrayList<CourseRegistration>();
        this.students = new ArrayList<Student>();
        this.courseCatalogue = new CourseCatalogue(new ArrayList<Course>());
    }
    /**
     * Method to use to load database from databse server
     * @param username Database access username
     * @param password database access password
     * @param dbURL database access url
     * @throws SQLException if SQLException occurs while reading from server
     */
    public void loadDB(String username, String password, String dbURL) throws SQLException
    {
        Connection dbConnection = DriverManager.getConnection(dbURL, username, password);

        this.loadCourseDB(dbConnection);
    }
    /**
     * Method to load course DB from DB server
     * @param dbConnection connection with database
     * @throws SQLException if SQLException occurs while reading from server
     */
    private void loadCourseDB(Connection dbConnection) throws SQLException
    {
        this.loadCourses(dbConnection);
        this.loadPreReqCourses(dbConnection);
        this.loadStudents(dbConnection);
        this.loadStudentRegs(dbConnection);
    }
    /**
     * Method to load all courses from DB server
     * @param dbConnection connection with database
     * @throws SQLException if SQLException occurs while reading from server
     */
    private void loadCourses(Connection dbConnection) throws SQLException
    {
    	Statement dbStatement = dbConnection.createStatement();
        ResultSet courseResults = dbStatement.executeQuery("SELECT * FROM coursedb.course_list");
        while(courseResults.next())
        {
            int courseId = courseResults.getInt("course_id");
            String courseFaculty = courseResults.getString("course_faculty");
            int courseNumber = courseResults.getInt("course_number");
            
            try
            {
                this.courseCatalogue.addCourseToCat(courseFaculty, courseNumber);
                this.loadCourseOffForCourse(dbConnection, courseId);
            }
            catch (CourseException e)
            {
                
            }
        }
    }
    /**
     * Method to load all pre-req courses from DB server
     * @param dbConnection connection with database
     * @throws SQLException if SQLException occurs while reading from server
     */
    private void loadPreReqCourses(Connection dbConnection) throws SQLException
    {
    	Statement dbStatement = dbConnection.createStatement();
        ResultSet courseResults = dbStatement.executeQuery("SELECT * FROM coursedb.pre_req_course_list");
        while(courseResults.next())
        {
            int courseId = courseResults.getInt("course_id");
            int preReqCourseId = courseResults.getInt("pre_req_course_id");
            Course course = getCourseInCat(dbConnection, courseId);
            Course preReqCourse = getCourseInCat(dbConnection, preReqCourseId);
            course.addPreReqCourse(preReqCourse);
        }
    }
    /**
     * Method to load all students from DB server
     * @param dbConnection connection with database
     * @throws SQLException if SQLException occurs while reading from server
     */
    private void loadStudents(Connection dbConnection) throws SQLException
    {
    	Statement dbStatement = dbConnection.createStatement();
        ResultSet studentResults = dbStatement.executeQuery("SELECT * FROM coursedb.student_list");
        while(studentResults.next())
        {
            String studentName = studentResults.getString("student_name");
            int studentId = studentResults.getInt("student_id");
            this.students.add(new Student(studentName, studentId));
        }
    }

    /**
     * Method to load registrations from DB server
     * @param dbConnection connection with database
     * @throws SQLException if SQLException occurs while reading from server
     */
    private void loadStudentRegs(Connection dbConnection) throws SQLException
    {
    	Statement dbStatement = dbConnection.createStatement();
        ResultSet regResults = dbStatement.executeQuery("SELECT * FROM coursedb.registration_list");
        while(regResults.next())
        {
            int courseOffId = regResults.getInt("course_offering_id");
            CourseOffering toRegister = this.getOfferingInCat(dbConnection, courseOffId);
            int studentId = regResults.getInt("student_id");
            Student regStudent = this.getStudent(studentId);
            CourseRegistration studentReg = new CourseRegistration();
            try
            {
                studentReg.completeRegistration(regStudent, toRegister);
                this.courseRegistrations.add(studentReg);
            }
            catch(RegistrationException | CoursePreReqException e)
            {

            }
        }
    }
    /**
     * Method to load course offering for course from DB server
     * @param dbConnection connection with database
     * @param courseId course id for course to load offerings for
     * @throws SQLException if SQLException occurs while reading from server
     */
    private void loadCourseOffForCourse(Connection dbConnection, int courseId) throws SQLException
    {
    	Statement dbStatement = dbConnection.createStatement();
        ResultSet courseOffResults = dbStatement.executeQuery("SELECT * FROM coursedb.course_offering_list WHERE course_id = '" + String.valueOf(courseId) + "'");
        while(courseOffResults.next())
        {
            int sectionNumber = courseOffResults.getInt("section_number");
            int sectionCapacity = courseOffResults.getInt("section_capacity");
            try
            {
            	Course course = getCourseInCat(dbConnection, courseId);
                this.courseCatalogue.createCourseOffering(course, sectionNumber, sectionCapacity);
            }
            catch(CourseException e)
            {
                throw new SQLException("CourseException\nError Message: " + e.getMessage());
            }
        }
    }
    /**
     * Method to get course from it's course id from DB server
     * @param dbConnection connection with database
     * @param courseId course id for course to load offerings for
     * @throws SQLException if SQLException occurs while reading from server
     * @return course representing the course id
     */
    private Course getCourseInCat(Connection dbConnection, int courseId) throws SQLException
    {
    	Statement dbStatement = dbConnection.createStatement();
        ResultSet courseResults = dbStatement.executeQuery("SELECT * FROM coursedb.course_list WHERE course_id ='" + String.valueOf(courseId) + "'");
        while(courseResults.next())
        {
            String courseName = courseResults.getString("course_faculty");
            int courseNumber = courseResults.getInt("course_number");
            return this.courseCatalogue.searchCat(courseName, courseNumber);
        }
        return null;
    }
    /**
     * Method to get course offering from it's course offering ID from DB server
     * @param dbConnection connection with database
     * @param courseOffId course offering id for course offering to get
     * @throws SQLException if SQLException occurs while reading from server
     * @return student if found, null otherwise
     */
    private CourseOffering getOfferingInCat(Connection dbConnection, int courseOffId) throws SQLException
    {
    	Statement dbStatement = dbConnection.createStatement();
        ResultSet courseOffResults = dbStatement.executeQuery("SELECT * FROM coursedb.course_offering_list WHERE course_offering_id = '" + String.valueOf(courseOffId) + "'");
        while(courseOffResults.next())
        {
            int courseId = courseOffResults.getInt("course_id");
            int sectionNumber = courseOffResults.getInt("section_number");
            Course courseInCat = this.getCourseInCat(dbConnection, courseId);
            return courseInCat.getCourseOfferingAt(sectionNumber-1);
        }
        return null;
    }

    /**
     * Method to use to get a student data from their ID
     * @param studentId id of student to get
     * @return student data if found, null otherwise
     */
    public Student getStudent(int studentId)
    {
        for (Student student : this.students)
        {
            if(student.getStudentId() == studentId)
            {
                return student;
            }
        }
        return null;
    }
    /**
     * Method to use to search for a course in catalogue
     * @param courseFaculty name of course to search
     * @param courseNumber number of course to search
     * @return course data if found, null otehrwise
     */
    public Course searchCourse(String courseFaculty, int courseNumber)
    {
        return this.courseCatalogue.searchCat(courseFaculty, courseNumber);
    }
    /**
     * Method to use to get course catalogue for DBManager
     * @return catalogue
     */
    public CourseCatalogue getCourseCat()
    {
        return this.courseCatalogue;
    }
    /**
     * Method to use to get offering for a course
     * @param courseFaculty course name to search
     * @param courseNumber course number to search
     * @param sectionNumber section of offering to search
     * @return course offering for section number specified
     */
    public CourseOffering getOffering(String courseFaculty, int courseNumber, int sectionNumber)
    {
        Course foundCourse = this.searchCourse(courseFaculty, courseNumber);
        if(foundCourse != null)
        {
            return foundCourse.getCourseOfferingAt(sectionNumber);
        }
        return null;
    }
    /**
     * Method to use to register for new course for a student via their student ID
     * @param studentId id of student to register offering for
     * @param offToReg offering to register
     * @throws RegistrationException if there are any problems registering the course for student
     * @throws CoursePreReqException if student fails to meet course requirements
     */
    public void registerNewCourse(int studentId, CourseOffering offToReg) throws RegistrationException, CoursePreReqException
    {
        Student studentToReg = this.getStudent(studentId);
        if(studentToReg != null)
        {
            CourseRegistration newReg = new CourseRegistration();
            newReg.completeRegistration(studentToReg, offToReg);
        }
    }
    /**
     * Method to use to drop a course for a student
     * @param studentId ID of student to drop course for
     * @param courseFactuly Course Name of course to drop
     * @param courseNumber Course Number of course to drop
     * @throws CourseException if there are problems dropping course for student
     */
    public void dropCourse(int studentId, String courseFactuly, int courseNumber) throws CourseException
    {
        Student studentToReg = this.getStudent(studentId);
        if(studentToReg != null)
        {
            for (CourseRegistration courseReg : studentToReg.getStudentRegList())
            {
                if(courseReg.getTheOffering().getTheCourse().getCourseName().compareTo(courseFactuly) == 0 && courseReg.getTheOffering().getTheCourse().getCourseNum() == courseNumber)
                {
                    courseReg.cancelRegistration();
                    return;
                }
            }
            throw new CourseException("Course is not in student's registration list.");
        }
    }
    /**
     * Method to use to get courses for a student via their student id
     * @param studentId ID of student to get all courses for
     * @return list of courses taken by student
     */
    public ArrayList<Course> getStudentCourses(int studentId)
    {
        Student studentToReg = this.getStudent(studentId);
        if(studentToReg != null)
        {
            ArrayList<Course> studentCourses = new ArrayList<Course>();
            for (CourseRegistration courseReg : studentToReg.getStudentRegList())
            {
                studentCourses.add(courseReg.getTheOffering().getTheCourse());
            }
            return studentCourses;
        }
        return null;
    }
    /**
     * Method to use to check if student ID specified is recognized by the DBManager
     * @param studentId id of student to search
     * @return true if user is in list of students, false otherwise
     */
    public boolean isRegisteredStudent(int studentId)
    {
        for (Student student : this.students)
        {
            if(student.getStudentId() == studentId)
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Method to use to add new course to catalogue
     * @param courseFaculty new course name
     * @param courseNumber new course number
     * @throws CourseException if there are problems adding course to catalogue
     */
    public void addCourse(String courseFaculty, int courseNumber) throws CourseException
    {
        this.courseCatalogue.addCourseToCat(courseFaculty, courseNumber);
    }
}