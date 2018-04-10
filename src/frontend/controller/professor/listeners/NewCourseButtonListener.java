package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import frontend.controller.Client;
import frontend.controller.professor.ProfessorGUI;
import frontend.view.pages.HomePage;
import shared.interfaces.ProfessorCommands;
import shared.objects.Course;
import shared.objects.Professor;
import shared.objects.SendMessage;

public class NewCourseButtonListener
		implements ActionListener, ProfessorCommands
{
	private Client client;
	private ProfessorGUI professorGUI;
	private HomePage homePage;

	public NewCourseButtonListener(ProfessorGUI professorGUI, Client client,
			HomePage page)
	{
		this.professorGUI = professorGUI;
		this.client = client;
		this.homePage = page;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		JTextField courseName = new JTextField(30);

		Object[] toDisplay =
			{ "Enter the preferred Course Name", courseName };

		int response = JOptionPane.showConfirmDialog(null, toDisplay,
				"Insert node information", JOptionPane.OK_CANCEL_OPTION);

		if (response == JOptionPane.OK_OPTION)
		{
			if (courseName.getText().length() > 40)
			{
				JOptionPane.showMessageDialog(homePage, "Course Name");
			} else
				try
				{
					Course course = new Course(
							professorGUI.getProfessor().getId(),
							courseName.getText(), false);
					client.onlySendMessage(new SendMessage<Course>(course,
							CMD_INSERT + INSERT_COURSE));

					professorGUI.createNewCourse(course, homePage);

				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
		}
	}
}