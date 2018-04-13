package frontend.view.pages.items.submission;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JPanel;

import frontend.view.pages.components.BoxList;
import shared.objects.Assignment;
import shared.objects.Student;
import shared.objects.Submission;

/**
 * Provides a class that stores assignment submissions inside of a container on
 * a page from a professor's perspective.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
final public class AssignSubItemProfessor extends AssignSubItem
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The list of students. Stores a student and their submissions.
	 */
	private BoxList<StudentSubItem> studentList;

	/**
	 * Stores the students in a map to easily add new submissions to the proper
	 * student.
	 */
	private HashMap<Integer, StudentSubItem> studentItemMap;

	public AssignSubItemProfessor(Assignment assignment,
			Vector<Student> classList)
	{
		super(assignment);
		this.studentList = new BoxList<StudentSubItem>();
		this.studentItemMap = new HashMap<Integer, StudentSubItem>();

		// Create a container within this container to group submissions by
		// student.
		for (Student student : classList)
		{
			StudentSubItem item = new StudentSubItem(student);
			item.setNameTitle();
			studentItemMap.put(student.getId(), item);
			studentList.add(item);
		}
		JPanel flowWrapper = new JPanel(new BorderLayout());
		flowWrapper.add(studentList, BorderLayout.NORTH);
		this.add(flowWrapper);
	}

	@Override
	public void addSubmission(SubmitItem submitItem)
	{
		Submission submission = submitItem.getSubmission();
		studentItemMap.get(submission.getStudent_id())
				.addSubmissionItem(submitItem);
	}
}
