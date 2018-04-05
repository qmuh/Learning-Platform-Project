package sharedobjects;

import java.io.Serializable;

public class Grade implements Serializable
{

	/**
	 * The name of the student who holds this grade
	 */
	private int id = 50;
	
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
