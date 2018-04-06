package frontend.view.pages;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.SubmitItem;
import sharedobjects.Assignment;
import sharedobjects.Course;
import sharedobjects.Submission;

public class SubmissionPage extends Page<SubmitItem, Submission>
{

	private Course course;
	private Assignment assignment;
	private Submission submission;
	
	public SubmissionPage(BoxList<SubmitItem> boxList)
	{
		super();
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub
		
	}
}
