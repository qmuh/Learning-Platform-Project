package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import frontend.controller.Client;
import frontend.view.pages.EnrollmentPage;
import shared.interfaces.ProfessorCommands;
import shared.objects.SendMessage;
import shared.objects.Student;

public class EnrollmentPageSearchButtonListener
		implements ActionListener, ProfessorCommands
{
	private Client client;

	private EnrollmentPage enrollmentPage;

	public EnrollmentPageSearchButtonListener(EnrollmentPage enrollmentPage,
			Client client)
	{
		this.client = client;
		this.enrollmentPage = enrollmentPage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String search = enrollmentPage.getSearchFieldText();
		try
		{
			Vector<Student> searchResult = new Vector<Student>();
			if (enrollmentPage.isSearchById())
			{
				SendMessage<Integer> sendMessage = new SendMessage<Integer>(
						Integer.parseInt(search),
						CMD_RECEIVE + RECEIVE_STUDENT_BY_ID);

				Student myResult = (Student) client.sendMessage(sendMessage);

				searchResult.add(myResult);
				
			} else if (enrollmentPage.isSearchByLastName())
			{
				SendMessage<String> sendMessage = new SendMessage<String>(
						search, CMD_RECEIVE + RECEIVE_STUDENT_BY_LASTNAME);

				searchResult = (Vector<Student>) client
						.sendMessage(sendMessage);
				
			}

			enrollmentPage.setStudentList(searchResult);
		} catch (NumberFormatException e2)
		{
			System.out.println(
					"Incorrect Login Value EnteredExitedHandler for search id");
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

	}
}