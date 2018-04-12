package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.WindowConstants;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.AssignSubItem;
import frontend.view.pages.items.CourseItem;
import frontend.view.pages.items.GeneralItem;
import frontend.view.pages.items.SubmitItem;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Student;
import shared.objects.Submission;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class SubmissionPage extends CoursePage<AssignSubItem, Submission>
{
	private HashMap<Integer, AssignSubItem> assignmentMap;

	public SubmissionPage(Course course)
	{
		super(course);
		this.setName(SUBMISSION_PAGE + course.getId());
		this.setPageTitle("Submissions");

		this.assignmentMap = new HashMap<Integer, AssignSubItem>();
		bodyCenter.add(createSubmissionPage(), BorderLayout.CENTER);
	}

	private JScrollPane createSubmissionPage()
	{
		JPanel view = new JPanel();
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

		JScrollPane submissionScrollPane = new JScrollPane(view);
		submissionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return submissionScrollPane;
	}

	public void addAssignment(Assignment assignment, Vector<Student> classList)
	{
		AssignSubItem assignmentItem = new AssignSubItem(assignment, classList);
		this.assignmentMap.put(assignment.getId(), assignmentItem);
		this.itemDisplay.add(assignmentItem);
	}

	public void addSubmission(SubmitItem submitItem)
	{
		Submission submission = submitItem.getSubmission();
		AssignSubItem assignSubItem = this.assignmentMap.get(submission.getAssign_id());
		assignSubItem.addSubmission(submitItem);
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		SubmissionPage submissionPage = new SubmissionPage(new Course(1010101, "ENGG 201", true));

		frame.add(submissionPage);
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
