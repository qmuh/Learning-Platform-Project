package frontend.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontend.controller.Client;
import frontend.view.pages.AssignmentPageProfessor;
import shared.interfaces.StudentCommands;
import shared.objects.Course;

public class UploadSubmissionButtonListener implements ActionListener, StudentCommands
{

	/**
	 * The client
	 */
	private Client client;

	/**
	 * The specific course associated with this listener
	 */
	private Course course;

	/** The upload button listener, uploads an assignment to the server
	 * @param client The client
	 * @param course The course
	 * @param assignPage The Assignment page
	 */
	public UploadSubmissionButtonListener(Client client, Course course)
	{
		this.client = client;
		this.course = course;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
