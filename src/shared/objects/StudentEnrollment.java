package shared.objects;

import java.io.Serializable;
import java.util.Random;

/**
 * Provides a class to represent a student enrollment object.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class StudentEnrollment implements Serializable
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;

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

	public StudentEnrollment(int stuID, int courseID)
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
}
