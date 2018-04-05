package sharedobjects;

import java.io.Serializable;

public class CourseMessage implements Serializable
{
	private int courseId;
	private int userId;
	private String name;
	
	public CourseMessage(int course, int user)
	{
		courseId = course;
		userId = user;
	}
	
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
