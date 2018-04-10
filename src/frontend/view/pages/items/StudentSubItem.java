package frontend.view.pages.items;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.view.pages.components.BoxList;
import shared.objects.Student;
import shared.objects.Submission;

public class StudentSubItem extends GeneralItem
{
	private Student student;
	private BoxList<SubmitItem> submissionItems;
	
	public StudentSubItem(Student student)
	{
		super(BoxLayout.Y_AXIS,Integer.toString(student.getId()));
		this.submissionItems = new BoxList<SubmitItem>();
		
		this.student = student;
		this.add(studentSubmissionsPanel());
	}

	private JPanel studentSubmissionsPanel()
	{
		JPanel studentSubmissionPanel = new JPanel();
		studentSubmissionPanel.setOpaque(false);
		studentSubmissionPanel.setLayout(new BoxLayout(studentSubmissionPanel, BoxLayout.Y_AXIS));
		
		studentSubmissionPanel.add(studentNamePanel());
		studentSubmissionPanel.add(submissionItems);
		return studentSubmissionPanel;
	}
	
	private JPanel studentNamePanel()
	{
		JPanel studentNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel studentName = new JLabel(student.getFirstName() + " " + student.getLastName());
		
		studentName.setFont(TEXT_FONT);
		
		studentNamePanel.add(studentName);
		return studentNamePanel;
	}

	@Override
	public int getId()
	{
		return student.getId();
	}

	public void addSubmission(Submission submission)
	{
		submissionItems.add(new SubmitItem(submission));
	}

}
