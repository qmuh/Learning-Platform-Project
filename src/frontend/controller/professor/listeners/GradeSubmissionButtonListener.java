package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;

import frontend.controller.Client;
import frontend.view.pages.items.submission.SubmitItem;
import frontend.view.pages.items.submission.SubmitItemProfessor;
import shared.interfaces.ProfessorCommands;
import shared.objects.Course;
import shared.objects.Grade;
import shared.objects.SendMessage;
import shared.objects.Submission;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018 Used the grade submission page
 */
public class GradeSubmissionButtonListener
		implements ActionListener, ProfessorCommands
{

	/**
	 * The client used by this listener class for communication
	 */
	private Client client;

	/**
	 * The specific course
	 */
	private Course course;

	/**
	 * The submissions that need to be graded
	 */
	private Submission submission;

	/**
	 * The grade field for each submission
	 */
	private JTextField gradeField;

	/**
	 * The constructor for this class
	 * 
	 * @param client
	 *            The client used for the communication
	 * @param course
	 *            The course
	 * @param submitItem
	 *            The submitItem which holds the assignment info
	 */
	public GradeSubmissionButtonListener(Client client, Course course,
			SubmitItemProfessor submitItem)
	{
		this.client = client;
		this.course = course;
		this.submission = submitItem.getSubmission();
		this.gradeField = submitItem.getGradeField();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Grade myGrade = new Grade(submission.getStudent_id(),
				Integer.parseInt(gradeField.getText()),
				submission.getAssign_id(), course.getId());
		try
		{
			client.onlySendMessage(
					new SendMessage<Grade>(myGrade, CMD_INSERT + INSERT_GRADE));
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

	}

}
