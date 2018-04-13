package frontend.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import frontend.controller.Client;
import frontend.view.pages.assignment.AssignmentPageStudent;
import shared.interfaces.StudentCommands;
import shared.objects.Course;

public class UploadSubmissionButtonListener
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

	private  AssignmentPageStudent assignPage;
	
	private JTextField uploadFile;

	public UploadSubmissionButtonListener(Client client, Course course, AssignmentPageStudent studentPage, JTextField upload)
	{
		this.client = client;
		this.course = course;
		assignPage = studentPage;
		uploadFile = upload;
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
			uploadFile.setText(selectedFile.getAbsolutePath());

		} else
		{
			assignPage.setFile(null);
		}

	}

}
