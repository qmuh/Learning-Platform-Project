package frontend.view.pages.items;

import javax.swing.Box;
import javax.swing.BoxLayout;

import sharedobjects.Submission;

public class SubmitItem extends Box
{

	private Submission submission;
	public SubmitItem(Submission submission)
	{
		super(BoxLayout.X_AXIS);
		this.submission = submission;
	}
	
}
