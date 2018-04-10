package shared.objects;

import java.io.Serializable;
import java.util.Random;

public class Grade implements Serializable
{

	/**
	 * The name of the student who holds this grade
	 */
	private int id;

	/**
	 * The id for the student who holds this grade
	 */
	private int student_id;

	/**
	 * The rounded value of the string
	 */
	private int grade;

	/**
	 * Name for the assignment which this grade is attributed too
	 */
	private int assignID;

	private int courseID;

	public Grade(int ID, int stuID, int grade, int assign, int course)
	{
		id = ID;
		student_id = stuID;
		this.grade = grade;
		assignID = assign;
		courseID = course;
	}

	public Grade(int stuID, int grade, int assign, int course)
	{
		Random random = new Random();
		id = random.nextInt(3000) + 2000;
		student_id = stuID;
		this.grade = grade;
		assignID = assign;
		courseID = course;
	}

	
	public int getId()
	{
		return id;
	}

	public int getStudent_id()
	{
		return student_id;
	}

	public int getGrade()
	{
		return grade;
	}

	public int getAssignID()
	{
		return assignID;
	}

	public int getCourseID()
	{
		return courseID;
	}

}
