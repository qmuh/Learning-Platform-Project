package frontend.view.pages;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.SubmitItem;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Submission;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
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
