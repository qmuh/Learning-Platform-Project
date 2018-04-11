package frontend.controller.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import frontend.controller.Client;
import shared.interfaces.ProfessorCommands;
import shared.interfaces.UserCommands;
import shared.objects.SendMessage;
import shared.objects.Submission;

public class SubmissionLabelMouseListener implements MouseListener, ProfessorCommands
{
	private Submission submission;
	private Client client;
	
	public SubmissionLabelMouseListener(Submission submission, Client client2)
	{
		this.submission = submission;
		client = client2;	
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		 try
		{
			byte [] file = (byte [])client.sendMessage
					(new SendMessage<Submission>(submission,CMD_RECEIVE + RECEIVE_STUDENT_ASSIGNMENT));
			String home = System.getProperty("user.home");
			
			String[] fileName = submission.getPath().split("/");
			File newFile = new File(home+"/Downloads/" +  fileName[fileName.length -1]); 
			
			if(! newFile.exists())
				newFile.createNewFile();
				FileOutputStream writer = new FileOutputStream(newFile);
				BufferedOutputStream bos = new BufferedOutputStream(writer);
				bos.write(file);
				bos.close();
			
		}catch (IOException e1) {
			e1.getStackTrace();
		}
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
