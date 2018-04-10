package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import frontend.controller.Client;
import frontend.view.pages.items.SubmitItem;
import shared.objects.Course;
import shared.objects.Submission;

public class GradeSubmissionButtonListener implements ActionListener
{

	private Client client;
	
	private Course course;
	
	private Submission submission;
	
	private JTextField gradeField;
	
	public GradeSubmissionButtonListener(Client client, Course course, SubmitItem submitItem)
	{
		this.client = client;
		this.course = course;
		this.submission = submitItem.getSubmission();
		this.gradeField = submitItem.getGradeTextField();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		
	}

}
