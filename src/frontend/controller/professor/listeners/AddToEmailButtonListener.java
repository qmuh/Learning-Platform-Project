package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontend.controller.Client;
import frontend.view.pages.compose.ComposeEmailPageProfessor;
import shared.objects.Course;

/**
 *  Used to add a student email to send
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class AddToEmailButtonListener implements ActionListener
{
	/**
	 * The client used to communicate with server
	 */
	private Client client;

	/**
	 * The specific course
	 */
	private Course course;

	/**
	 * The page where the button is located
	 */
	private ComposeEmailPageProfessor myEmailPage;

	/**
	 * Constructor for this inner class
	 * 
	 * @param client
	 *            The client
	 * @param course
	 *            The course
	 * @param myPage
	 *            The page
	 */
	public AddToEmailButtonListener(Client client, Course course,
			ComposeEmailPageProfessor myPage)
	{
		this.client = client;
		this.course = course;
		this.myEmailPage = myPage;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("ADD TO ALL LISTENER");
		String add = myEmailPage.getSelected();
		if (add != null)
		{
			myEmailPage.appendEmail(add);
		}

	}

}