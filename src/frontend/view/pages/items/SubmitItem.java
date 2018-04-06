package frontend.view.pages.items;

import javax.swing.Box;
import javax.swing.BoxLayout;

import shared.objects.Submission;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
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
