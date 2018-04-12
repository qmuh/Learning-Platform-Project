package frontend.view.pages.items.submission;

import java.util.HashMap;
import java.util.Vector;

import frontend.view.pages.components.BoxList;
import shared.objects.Assignment;
import shared.objects.Student;
import shared.objects.Submission;

final public class AssignSubItemProfessor extends AssignSubItem
{

	private static final long serialVersionUID = 1L;

	private BoxList<StudentSubItem> studentList;

	private HashMap<Integer, StudentSubItem> studentItemMap;

	public AssignSubItemProfessor(Assignment assignment,
			Vector<Student> classList)
	{
		super(assignment);
		this.studentList = new BoxList<StudentSubItem>();
		this.studentItemMap = new HashMap<Integer, StudentSubItem>();

		// 
		for (Student student : classList)
		{
			StudentSubItem item = new StudentSubItem(student);
			studentItemMap.put(student.getId(), item);
			studentList.add(item);
		}

		this.add(studentList);
	}

	@Override
	public void addSubmission(SubmitItem submitItem)
	{
		Submission submission = submitItem.getSubmission();
		studentItemMap.get(submission.getStudent_id())
				.addSubmissionItem(submitItem);
	}
}
