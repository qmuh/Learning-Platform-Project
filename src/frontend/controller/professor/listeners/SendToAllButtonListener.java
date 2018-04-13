package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import frontend.controller.Client;
import frontend.view.pages.compose.ComposeEmailPage;
import frontend.view.pages.compose.ComposeEmailPageProfessor;
import shared.interfaces.ProfessorCommands;
import shared.objects.Course;
import shared.objects.SendMessage;
import shared.objects.Student;

/** Sets the emails to send to all enrolled students in a class
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018 Used to select all student for sending the email
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
	private ComposeEmailPageProfessor composePage;

	/**
	 * Constructor for this listener class
	 * 
	 * @param client
	 *            The client which is used for communication
	 * @param course
	 *            The specific course
	 * @param composeEmailPage
	 *            The page which contains information needed
	 */
	public SendToAllButtonListener(Client client, Course course,
			ComposeEmailPageProfessor composeEmailPage)
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
			System.out.println("SEND TO ALL LISTENER");
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