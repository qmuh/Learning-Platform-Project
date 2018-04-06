package frontend.view.pages;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.AssignItem;
import sharedobjects.Assignment;
import sharedobjects.Course;

public class AssignmentPage extends Page<AssignItem, Assignment>
{
	private Course course;
	private Assignment assignment;
	
	public AssignmentPage(BoxList<AssignItem> boxList)
	{
		super();
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub
		
	}
	
}
