package frontend.view.pages.items;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import frontend.view.pages.components.BoxList;
import shared.objects.Assignment;
import shared.objects.Student;
import shared.objects.Submission;

public class AssignSubItem extends GeneralItem
{
	private static final long serialVersionUID = 1L;
	
	
	private Assignment assignment;
	private BoxList<StudentSubItem> studentList;
	private HashMap<Integer, StudentSubItem> studentItemMap;

	public AssignSubItem(Assignment assignment, Vector<Student> classList)
	{
		super(BoxLayout.Y_AXIS, Integer.toString(assignment.getId()));
		this.assignment = assignment;
		// extract assignment title
		
		this.studentList = new BoxList<StudentSubItem>();
		this.studentItemMap = new HashMap<Integer, StudentSubItem>();
		
		for (Student student : classList)
		{
			StudentSubItem item = new StudentSubItem(student); 
			studentItemMap.put(student.getId(), item);
			studentList.add(item);
		}
		
		MatteBorder matte = new MatteBorder(2, 2, 2, 2, Color.GREEN);
		TitledBorder titledBorder = new TitledBorder(matte);
		titledBorder.setTitle(assignment.getTitle());
		
		this.setBorder(titledBorder);
		this.add(studentList);
	}
	
	public void addSubmission(SubmitItem submitItem)
	{
		Submission submission = submitItem.getSubmission();
		studentItemMap.get(submission.getStudent_id()).addSubmissionItem(submitItem);
	}

	@Override
	public int getId()
	{
		return assignment.getId();
	}

}
