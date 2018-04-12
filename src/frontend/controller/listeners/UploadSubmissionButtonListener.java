package frontend.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import frontend.controller.Client;
import frontend.view.pages.assignment.AssignmentPageStudent;
import shared.interfaces.StudentCommands;
import shared.objects.Course;

public class UploadSubmissionButtonListener implements ActionListener, StudentCommands
{

	/**
	 * The client
	 */
	private Client client;

	/**
	 * The specific course associated with this listener
	 */
	private Course course;
	
	private  AssignmentPageStudent assignPage;
	
	public UploadSubmissionButtonListener(Client client, Course course, AssignmentPageStudent studentPage)
	{
		this.client = client;
		this.course = course;
		assignPage = studentPage;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		File selectedFile;
		JFileChooser fileBrowser = new JFileChooser();
		if (fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			selectedFile = fileBrowser.getSelectedFile();
			assignPage.setFile(selectedFile);

		} else
		{
			assignPage.setFile(null);
		}
		
	}

}
