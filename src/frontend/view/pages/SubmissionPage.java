package frontend.view.pages;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.items.GeneralItem;
import frontend.view.pages.items.course.CourseItem;
import frontend.view.pages.items.submission.AssignSubItem;
import frontend.view.pages.items.submission.SubmitItem;
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
abstract public class SubmissionPage
		extends CoursePage<AssignSubItem, Submission>
{
	private static final long serialVersionUID = 1L;

	protected HashMap<Integer, AssignSubItem> assignmentMap;

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

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		// SubmissionPageProfessor submissionPage = new SubmissionPageProfessor(
		Course engg201 = new Course(1010101, "ENGG 201", true);
		//
		 Assignment assignment = new Assignment(3322, 1010101, "ASSIGNMENT ",
		 "PATH", true, "DATE");
		//
		// Vector<Student> students = new Vector<Student>();
		//
		Student qasim = new Student(1003, "Qasim", "Muhammad", "qm@email.com",
				"222");
		//
		// students.add(
		// new Student(1005, "Jimmy", "Truong", "jt@email.com", "222"));
		//
		// submissionPage.addAssignment(assignment, students);
		//
//		 Submission qasimSubmission = new Submission(3322, 1003, "PATH", 2,
//		 "COMMENT", "TITLE", "TIME");
//		 SubmitItem qasimSubmitItem = new SubmitItem(qasimSubmission);

		// submissionPage.addSubmission(qasimSubmitItem);

		SubmissionPageStudent studentPage = new SubmissionPageStudent(engg201,
				qasim);

		studentPage.addAssignment(assignment);

		 Submission qasimSubmission = new Submission(3322, 1003, "PATH", 2,
				 "COMMENT", "TITLE", "TIME");

		 SubmitItem qasimItem = new SubmitItem(qasimSubmission);



//		studentPage.addSubmission(qasimItem);

		frame.add(studentPage);
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
