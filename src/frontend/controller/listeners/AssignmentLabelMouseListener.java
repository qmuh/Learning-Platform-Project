package frontend.controller.listeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


import frontend.controller.Client;
import frontend.interfaces.ColourPalette;
import shared.interfaces.UserCommands;
import shared.objects.Assignment;
import shared.objects.SendMessage;

/**
 *  This class is used to allow downloading of files by clicking on a label
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class AssignmentLabelMouseListener
		implements MouseListener, UserCommands, ColourPalette
{

	/**
	 * The submission associated with the listener
	 */
	private Assignment assignment;

	/**
	 * The client used to communicate with the server
	 */
	private Client client;

	/**
	 * The listener for submissions, used by the professor
	 * 
	 * @param submission
	 *            The submission
	 * @param client2
	 *            The client
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
			System.out.println("Make it here");
			byte[] file = (byte[]) client
					.sendMessage(new SendMessage<Assignment>(assignment,
							CMD_RECEIVE + RECEIVE_ASSIGNMENT));

			String home = System.getProperty("user.home");

			String[] fileName = assignment.getPath()
					.split(Pattern.quote(File.separator));
			File newFile = new File(home + File.separator + "Downloads"
					+ File.separator + fileName[fileName.length - 1]);

			if (!newFile.exists())
				newFile.createNewFile();
			FileOutputStream writer = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(writer);
			bos.write(file);
			bos.close();

			JOptionPane.showMessageDialog((Component) e.getSource(),
					"Assignment " + assignment.getTitle() + " saved to "
							+ newFile.getPath(),
					"File Downloaded", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException e1)
		{
			e1.getStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		JLabel label = (JLabel) e.getSource();
		label.setForeground(BACKGROUND_COLOUR);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		JLabel label = (JLabel) e.getSource();
		label.setForeground(Color.BLACK);
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
