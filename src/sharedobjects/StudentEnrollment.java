package sharedobjects;

import java.io.Serializable;
import java.util.Random;

public class StudentEnrollment implements Serializable
{
	
	/**
	 * The identification for enrolling a student to a class
	 */
	private int id;

	/**
	 * The id for the student who is enrolling
	 */
	private int student_id;
	
	/**
	 * The id for the course which the student is being enrolled into
	 */
	private int course_id;
	
	/**
	 * No idea ?????
	 */
	private Boolean enrolling;
	
	public StudentEnrollment( int stuID, int courseID)
	{
		Random random = new Random();
		id = random.nextInt(4000) + 3000;
		student_id = stuID;
		course_id = courseID;
	
	}

	public int getId()
	{
		return id;
	}

	public int getStudent_id()
	{
		return student_id;
	}

	public int getCourse_id()
	{
		return course_id;
	}

	public Boolean getEnrolling()
	{
		return enrolling;
	}
	
	
}
