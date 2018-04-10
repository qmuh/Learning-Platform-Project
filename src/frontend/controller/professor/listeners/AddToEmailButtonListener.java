package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontend.controller.Client;
import frontend.view.pages.ComposeEmailPage;
import shared.objects.Course;

/**
 * Listener for the add to email button on the compose email page
 *
 */
public class AddToEmailButtonListener implements ActionListener
{
	private Client client;
	private Course course;
	private ComposeEmailPage myEmailPage;
	

	public AddToEmailButtonListener(Client client, Course course, ComposeEmailPage myPage)
	{
		this.client = client;
		this.course = course;
		this.myEmailPage = myPage;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String add = myEmailPage.getSelected();
		if (add != null)
		{
			myEmailPage.appendEmail(add);
		}

	}

}