package frontend.controller.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import shared.interfaces.UserCommands;
import shared.objects.Submission;

public class SubmissionLabelMouseListener implements MouseListener, UserCommands
{
	private Submission submission;
	
	public SubmissionLabelMouseListener(Submission submission)
	{
		this.submission = submission;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

}
