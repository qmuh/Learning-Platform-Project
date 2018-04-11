package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import frontend.controller.Client;
import frontend.interfaces.ColourPalette;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WButtonActivatable;
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
			WButtonActivatable activeButton = (WButtonActivatable) e.getSource();
			if (course.getActive())
			{
				client.onlySendMessage(new SendMessage<Course>(course,
						CMD_MODIFY + MODIFY_COURSE_INACTIVE));
				activeButton.setActive(false);
			} else
			{
				client.onlySendMessage(new SendMessage<Course>(course,
						CMD_MODIFY + MODIFY_COURSE_ACTIVE));
				activeButton.setActive(true);
			}

			course.setActive();
		} catch (IOException e1)
		{
			System.out.println("Unable to change the course active state");
			e1.printStackTrace();
		}

	}

}