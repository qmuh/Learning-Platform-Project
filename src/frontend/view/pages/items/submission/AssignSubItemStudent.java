package frontend.view.pages.items.submission;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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
		
		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.add(studentSubItem, BorderLayout.NORTH);
		this.add(wrapper);
	}

	@Override
	public void addSubmission(SubmitItem submitItem)
	{
		
		this.studentSubItem.addSubmissionItem(submitItem);
	}
}
