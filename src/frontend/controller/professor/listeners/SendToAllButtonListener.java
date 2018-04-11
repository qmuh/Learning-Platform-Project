package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import frontend.controller.Client;
import frontend.view.pages.ComposeEmailPage;
import shared.interfaces.ProfessorCommands;
import shared.objects.Course;
import shared.objects.SendMessage;
import shared.objects.Student;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class SendToAllButtonListener
		implements ActionListener, ProfessorCommands
{
	/**
	 * client to connect
	 */
	private Client client;
	
	/**
	 * The course itself
	 */
	private Course course;
	
	/**
	 * The ComposeEmailPage where the button is located
	 */
	private ComposeEmailPage composePage;

	/**
	 * @param client
	 * @param course
	 * @param composeEmailPage
	 */
	public SendToAllButtonListener(Client client, Course course,
			ComposeEmailPage composeEmailPage)
	{
		this.client = client;
		this.course = course;
		this.composePage = composeEmailPage;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			composePage.clearEmail();
			@SuppressWarnings("unchecked")
			Vector<Student> enrollList = (Vector<Student>) client
					.sendMessage(new SendMessage<Course>(course,
							CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS));

			for (int i = 0; i < enrollList.size(); i++)
			{
				String add = enrollList.get(i).getEmail();
				composePage.appendEmail(add);
			}

		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

	}

}