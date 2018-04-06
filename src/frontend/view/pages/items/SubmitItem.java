package frontend.view.pages.items;

import javax.swing.Box;
import javax.swing.BoxLayout;

import sharedobjects.Submission;

public class SubmitItem extends GeneralItem
{

	private Submission submission;
	public SubmitItem(Submission submission)
	{
		super(BoxLayout.X_AXIS, Integer.toString(submission.getId()));
		this.submission = submission;
	}
	@Override
	public int getId()
	{
		return submission.getId();
	}
	
}
