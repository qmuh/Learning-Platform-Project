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

public class AssignmentActiveButtonListener
		implements ActionListener, ProfessorCommands, ColourPalette
{
	private Client client;
	private Assignment assignment;

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