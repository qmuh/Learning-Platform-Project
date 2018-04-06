package frontend.view.pages;

import java.util.ArrayList;

import frontend.components.BoxList;
import frontend.view.pages.items.StudentItem;
import sharedobjects.Course;
import sharedobjects.Student;

public class EnrollmentPage extends CoursePage
{
	private Course course;
	private ArrayList<Student> enrolledStudentList;
	
	public EnrollmentPage(BoxList<StudentItem> boxList) 
	{
		super();
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub
		
	}
}
