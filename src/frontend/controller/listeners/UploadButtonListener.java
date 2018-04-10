package frontend.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import frontend.controller.Client;
import frontend.controller.professor.listeners.AssignmentActiveButtonListener;
import frontend.view.pages.AssignmentPage;
import shared.interfaces.ProfessorCommands;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.SendMessage;

/**
 * Listener for the upload button on the assignment page
 *
 */
public class UploadButtonListener implements ActionListener, ProfessorCommands
{
	private Client client;
	private Course course;
	private AssignmentPage assignmentPage;

	public UploadButtonListener(Client client, Course course,
			AssignmentPage assignPage)
	{
		this.client = client;
		this.course = course;
		this.assignmentPage = assignPage;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println(assignmentPage.getFile().getPath());
		String append[] = assignmentPage.getFile().getPath()
				.split(File.pathSeparator);
		if (assignmentPage.getFile() != null)
		{
			Assignment myUpload = new Assignment(course.getId(),
					append[append.length - 1],
					assignmentPage.getUploadField().getText(), false,
					assignmentPage.getDate());
			try
			{
				client.onlySendMessage(new SendMessage<Assignment>(myUpload,
						CMD_INSERT + INSERT_ASSIGNMENT));

				long length = assignmentPage.getFile().length();
				byte[] content = new byte[(int) length]; // Converting Long
															// to Int
				FileInputStream fis = new FileInputStream(
						assignmentPage.getFile());

				BufferedInputStream bos = new BufferedInputStream(fis);
				bos.read(content, 0, (int) length);

				client.getObjectOut().writeObject(content);
				client.getObjectOut().flush();

				assignmentPage.createAssignItem(myUpload,
						new AssignmentActiveButtonListener(client, myUpload));
				// showAllAssignments(course, assignmentPage);

			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
}