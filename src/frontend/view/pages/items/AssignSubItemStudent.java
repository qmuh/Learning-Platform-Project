package frontend.view.pages.items;

import shared.objects.Assignment;
import shared.objects.Student;

public class AssignSubItemStudent extends AssignSubItem
{
	private static final long serialVersionUID = 1L;
	
	private StudentSubItem studentSubItem;
	
	public AssignSubItemStudent(Assignment assignment, Student student)
	{
		super(assignment);
		this.studentSubItem = new StudentSubItem(student);
	}

	@Override
	public void addSubmission(SubmitItem submitItem)
	{
		this.studentSubItem.addSubmissionItem(submitItem);
	}
}
