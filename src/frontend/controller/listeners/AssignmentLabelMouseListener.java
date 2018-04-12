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
import shared.objects.Assignment;
import shared.objects.SendMessage;
import shared.objects.Submission;

public class AssignmentLabelMouseListener implements MouseListener, UserCommands
{

	/**
	 * The submission asscociated with the listener
	 */
	private Assignment assignment;

	/**
	 * The client used to communicate with the server
	 */
	private Client client;

	/** The listener for submissions, used by the professor
		 * 
		 * @param submission The submission
		 * @param client2 The client
		 */
		public AssignmentLabelMouseListener(Assignment assignment, Client client2)
		{
			this.assignment = assignment;
			client = client2;	
		}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		try
		{
			byte[] file = (byte[]) client
					.sendMessage(new SendMessage<Assignment>(assignment,
							CMD_RECEIVE + RECEIVE_FILE_ASSIGNMENT));
			String home = System.getProperty("user.home");

			String[] fileName = assignment.getPath().split("/");
			File newFile = new File(
					home + "/Downloads/" + fileName[fileName.length - 1]);

			if (!newFile.exists())
				newFile.createNewFile();
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(file);
			bos.close();

		} catch (IOException e1)
		{
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
