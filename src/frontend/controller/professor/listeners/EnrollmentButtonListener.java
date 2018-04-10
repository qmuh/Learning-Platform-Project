package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import frontend.controller.Client;
import frontend.controller.professor.ProfessorGUI;
import frontend.view.pages.EnrollmentPage;
import frontend.view.pages.components.customSwing.WButton;
import shared.interfaces.ProfessorCommands;
import shared.objects.Course;
import shared.objects.SendMessage;
import shared.objects.Student;
import shared.objects.StudentEnrollment;

public class EnrollmentButtonListener
		implements ActionListener, ProfessorCommands
{
	private ProfessorGUI professorGUI;
	private Client client;
	private EnrollmentPage enrollmentPage;
	private Course myCourse;

	public EnrollmentButtonListener(ProfessorGUI professorGUI, Client client,
			EnrollmentPage enrollmentPage, Course course)
	{
		this.professorGUI = professorGUI;
		this.client = client;
		this.enrollmentPage = enrollmentPage;
		this.myCourse = course;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Student selectedStudent = enrollmentPage.getSelectedStudent();
		WButton enrollmentButton = (WButton) e.getSource();
		if (selectedStudent != null)
		{
			StudentEnrollment toSend = new StudentEnrollment(
					selectedStudent.getId(), myCourse.getId());

			try
			{
				Boolean isEnrolled = (Boolean) client
						.sendMessage(new SendMessage<StudentEnrollment>(toSend,
								CMD_RECEIVE + RECEIVE_STUDENT_IS_ENROLLED));

				if (isEnrolled)
				{
					client.onlySendMessage(new SendMessage<StudentEnrollment>(
							toSend, CMD_INSERT + INSERT_UNENROLLMENT));
					enrollmentButton.setText("Enroll");
				} else
				{
					client.onlySendMessage(new SendMessage<StudentEnrollment>(
							toSend, CMD_INSERT + INSERT_ENROLLMENT));
					enrollmentButton.setText("Unenroll");
				}

				
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}

		professorGUI.showAllStudents(myCourse, enrollmentPage);
	}
}