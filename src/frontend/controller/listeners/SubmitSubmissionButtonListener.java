package frontend.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import frontend.controller.Client;
import frontend.controller.professor.listeners.AssignmentActiveButtonListener;
import frontend.view.pages.assignment.AssignmentPageStudent;
import frontend.view.pages.items.assignment.AssignItemProfessor;
import shared.interfaces.StudentCommands;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.SendMessage;
import shared.objects.Submission;

/**
 * Used for allowing the ability to submit a subission by the user
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class SubmitSubmissionButtonListener
		implements ActionListener, StudentCommands
{

	/**
	 * The client
	 */
	private Client client;

	/**
	 * The specific course associated with this listener
	 */
	private Course course;

	private AssignmentPageStudent assignmentPage;

	private Submission submission;

	public SubmitSubmissionButtonListener(Client client, Course course,
			AssignmentPageStudent studentPage, Submission toSend)
	{
		this.client = client;
		this.course = course;
		assignmentPage = studentPage;
		submission = toSend;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		String fileNam[] = assignmentPage.getFile().getAbsolutePath()
				.split(Pattern.quote(File.separator));

		System.out.println(fileNam[fileNam.length - 1]);
		String append[] = assignmentPage.getFile().getPath()
				.split(Pattern.quote(File.separator));

		submission.setPath(assignmentPage.getFile().getPath());
		submission.setTitle(fileNam[fileNam.length - 1]);

		System.out.println("TITLE NAME:: " + submission.getTitle()
				+ append[append.length - 1]);

		submission.setDate(assignmentPage.getDate());
		submission.setComment("");

		if (assignmentPage.getFile() != null)
		{

			try
			{
				client.onlySendMessage(new SendMessage<Submission>(submission,
						CMD_INSERT + INSERT_SUBMISSION));

				long length = assignmentPage.getFile().length();
				byte[] content = new byte[(int) length]; // Converting Long
															// to Int
				FileInputStream fis = new FileInputStream(
						assignmentPage.getFile());

				BufferedInputStream bos = new BufferedInputStream(fis);
				bos.read(content, 0, (int) length);

				client.getObjectOut().writeObject(content);
				client.getObjectOut().flush();

			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}

	}

}
