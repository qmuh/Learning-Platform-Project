package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontend.controller.Client;
import frontend.view.pages.ComposeEmailPage;
import shared.objects.Course;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class SendButtonListener implements ActionListener
{
	private Course course;

	private Client client;

	private ComposeEmailPage emailPage;

	public SendButtonListener(Client client, Course course,
			ComposeEmailPage email)
	{
		this.client = client;
		this.course = course;
		ComposeEmailPage emailPage;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO:
	}
}