package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;

import frontend.controller.Client;
import frontend.view.pages.items.SubmitItem;
import shared.interfaces.ProfessorCommands;
import shared.objects.Course;
import shared.objects.Grade;
import shared.objects.SendMessage;
import shared.objects.Submission;

public class GradeSubmissionButtonListener implements ActionListener, ProfessorCommands
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
		Grade myGrade = new Grade(submission.getStudent_id(), Integer.parseInt(gradeField.getText())
				,submission.getAssign_id(), course.getId());
		try
		{
			client.sendMessage(new SendMessage<Grade>(myGrade, CMD_INSERT + INSERT_GRADE));
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
	}

}
