package frontend.view.pages;

import java.util.ArrayList;

import frontend.components.BoxList;
import sharedobjects.Course;
import sharedobjects.Student;

public class EnrollmentPage extends Page
{
	private Course course;
	private ArrayList<Student> enrolledStudentList;
	
	public EnrollmentPage(BoxList<?> boxList) 
	{
		super(boxList);
	}
}
