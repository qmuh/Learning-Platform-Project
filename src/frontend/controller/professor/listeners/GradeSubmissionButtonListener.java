package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontend.controller.Client;
import frontend.view.pages.items.SubmitItem;
import shared.objects.Submission;

public class GradeSubmissionButtonListener implements ActionListener
{

	private Client client;
	
	private Submission submission;
	
	
	public GradeSubmissionButtonListener(Client client, SubmitItem submitItem)
	{
		this.client = client;
		this.submission = submitItem.getSubmission();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		// TODO Auto-generated method stub
		
	}

}
