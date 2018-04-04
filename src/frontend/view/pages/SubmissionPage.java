package frontend.view.pages;

import frontend.components.BoxList;
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
		super(boxList);
	}
}
