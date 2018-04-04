package frontend.view.pages;

import frontend.components.BoxList;
import sharedobjects.Assignment;
import sharedobjects.Course;
import sharedobjects.Submission;

public class SubmissionPage extends Page
{

	private Course course;
	private Assignment assignment;
	private Submission submission;
	
	public SubmissionPage(BoxList boxList)
	{
		super(boxList);
	}
}
