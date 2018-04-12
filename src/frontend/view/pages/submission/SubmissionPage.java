package frontend.view.pages.submission;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JScrollPane;

import frontend.view.pages.course.CoursePage;
import frontend.view.pages.items.submission.AssignSubItem;
import frontend.view.pages.items.submission.SubmitItem;
import shared.objects.Course;
import shared.objects.Submission;
import shared.objects.User;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
abstract public class SubmissionPage
		extends CoursePage<AssignSubItem, Submission>
{
	private static final long serialVersionUID = 1L;

	protected HashMap<Integer, AssignSubItem> assignmentMap;

	public SubmissionPage(Course course, User user)
	{
		super(course, user);
		this.setName(SUBMISSION_PAGE + course.getId());
		this.setPageTitle("Submissions");
		this.assignmentMap = new HashMap<Integer, AssignSubItem>();

		bodyCenter.add(createSubmissionPage(), BorderLayout.CENTER);
	}

	private JScrollPane createSubmissionPage()
	{
		JScrollPane submissionScrollPane = new JScrollPane(itemDisplay);
		submissionScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return submissionScrollPane;
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}

	public void addSubmission(SubmitItem submitItem)
	{
		Submission submission = submitItem.getSubmission();
		AssignSubItem assignSubItem = this.assignmentMap
				.get(submission.getAssign_id());
		assignSubItem.addSubmission(submitItem);
	}

}
