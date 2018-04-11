package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import frontend.controller.Client;
import frontend.interfaces.ColourPalette;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WButtonActivatable;
import shared.interfaces.ProfessorCommands;
import shared.objects.Assignment;
import shared.objects.SendMessage;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 *  Used to activate/deactivate an assignment
 */
public class AssignmentActiveButtonListener
		implements ActionListener, ProfessorCommands, ColourPalette
{
	/**
	 * The client where the associated client it
	 */
	private Client client;
	
	/**
	 * The assignment where this button would be
	 */
	private Assignment assignment;

	/** Constructor for the inner class
	 * @param client The client 
	 * @param course The specific course
	 */
	public AssignmentActiveButtonListener(Client client, Assignment course)
	{
		this.client = client;
		this.assignment = course;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			WButtonActivatable activeButton = (WButtonActivatable) e.getSource();
			if (assignment.getActive())
			{
				client.onlySendMessage(new SendMessage<Assignment>(assignment,
						CMD_MODIFY + MODIFY_ASSIGNMENT_INACTIVE));
				activeButton.setActive(false);
			} else
			{
				client.onlySendMessage(new SendMessage<Assignment>(assignment,
						CMD_MODIFY + MODIFY_ASSIGNMENT_ACTIVE));
				activeButton.setActive(true);

			}
			assignment.setActive();
		} catch (IOException e1)
		{
			System.out.println("Unable to change the course active state");
			e1.printStackTrace();
		}
	}
}