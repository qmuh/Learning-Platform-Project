package sharedobjects;

import java.io.Serializable;

/**
 * @author qasimmuhammad
 * Used to send messages pertaining to students and courses together
 */
public class CourseMessage implements Serializable
{
	/**
	 * Course ID
	 */
	private int courseId;
	
	/**
	 * Id related to course, can be of prof or student
	 */
	private int userId;
	
	/**
	 * Name of the student/professor
	 */
	private String name;
	
	/** Initializes the object
	 * @param course
	 * @param user
	 */
	public CourseMessage(int course, int user)
	{
		courseId = course;
		userId = user;
	}
	
	/**
	 * @param course
	 * @param userName
	 */
	public CourseMessage(int course, String userName)
	{
		courseId = course;
		name = userName;
	}

	public int getCourseId()
	{
		return courseId;
	}

	public int getUserId()
	{
		return userId;
	}

	public String getName()
	{
		return name;
	}
	
	
	
}
