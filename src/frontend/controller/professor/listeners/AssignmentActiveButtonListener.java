package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import frontend.controller.Client;
import frontend.interfaces.ColourPalette;
import frontend.view.pages.components.customSwing.WButton;
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
		System.err.println("Assignment Activity");
		try
		{
			WButton activeButton = (WButton) e.getSource();
			if (assignment.getActive())
			{
				System.out.println("INACTIVE");
				client.onlySendMessage(new SendMessage<Assignment>(assignment,
						CMD_MODIFY + MODIFY_ASSIGNMENT_INACTIVE));
				activeButton.setText("ACTIVATE");
				activeButton.setBackground(BACKGROUND_COLOUR);
			} else
			{
				System.out.println("ACTIVE");

				client.onlySendMessage(new SendMessage<Assignment>(assignment,
						CMD_MODIFY + MODIFY_ASSIGNMENT_ACTIVE));
				activeButton.setText("DEACTIVATE");
				activeButton.setBackground(CONTRAST_COLOR);

			}
			assignment.setActive();
		} catch (IOException e1)
		{
			System.out.println("Unable to change the course active state");
			e1.printStackTrace();
		}
	}
}