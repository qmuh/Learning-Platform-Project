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

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 *  Used to activate/deactivate a course, only for a professor
 */
public class CourseActiveButtonListener
		implements ActionListener, ColourPalette, ProfessorCommands
{
	/**
	 * Client used to tell server to activate or deactivate
	 */
	private Client client;
	
	/**
	 * The associated course
	 */
	private Course course;

	/** Constructor for the listener class
	 * @param client The client
	 * @param course The course
	 */
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