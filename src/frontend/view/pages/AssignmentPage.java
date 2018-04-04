package frontend.view.pages;

import frontend.components.BoxList;
import sharedobjects.Assignment;
import sharedobjects.Course;

public class AssignmentPage extends Page
{
	private Course course;
	private Assignment assignment;
	
	public AssignmentPage(BoxList<?> boxList)
	{
		super(boxList);
	}
	
}
