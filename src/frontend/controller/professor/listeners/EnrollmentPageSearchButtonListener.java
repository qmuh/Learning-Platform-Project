package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import frontend.controller.Client;
import frontend.view.pages.enrollment.EnrollmentPage;
import shared.interfaces.ProfessorCommands;
import shared.objects.SendMessage;
import shared.objects.Student;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018 Used for searching for the enrollment page
 */
public class EnrollmentPageSearchButtonListener
		implements ActionListener, ProfessorCommands
{
	/**
	 * The client object
	 */
	private Client client;

	/**
	 * The enrollment page
	 */
	private EnrollmentPage enrollmentPage;

	/**
	 * The enrollment page listener constructor
	 * 
	 * @param enrollmentPage
	 *            The page to add
	 * @param client
	 *            The client connection
	 */
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