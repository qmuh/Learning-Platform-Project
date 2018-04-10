package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import frontend.controller.Client;
import frontend.interfaces.ColourPalette;
import frontend.view.pages.components.customSwing.WButton;
import shared.interfaces.ProfessorCommands;
import shared.objects.Course;
import shared.objects.SendMessage;

public class CourseActiveButtonListener
		implements ActionListener, ColourPalette, ProfessorCommands
{
	private Client client;
	private Course course;

	public CourseActiveButtonListener(Client client, Course course)
	{
		this.client = client;
		this.course = course;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			WButton activeButton = (WButton) e.getSource();
			if (course.getActive())
			{
				client.onlySendMessage(new SendMessage<Course>(course,
						CMD_MODIFY + MODIFY_COURSE_INACTIVE));
				activeButton.setText("ACTIVATE");
				activeButton.setBackground(BACKGROUND_COLOUR);
			} else
			{
				client.onlySendMessage(new SendMessage<Course>(course,
						CMD_MODIFY + MODIFY_COURSE_ACTIVE));
				activeButton.setText("DEACTIVATE");
				activeButton.setBackground(CONTRAST_COLOR);
			}

			course.setActive();
		} catch (IOException e1)
		{
			System.out.println("Unable to change the course active state");
			e1.printStackTrace();
		}

	}

}