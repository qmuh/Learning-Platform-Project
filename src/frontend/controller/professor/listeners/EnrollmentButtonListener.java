package frontend.controller.professor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import frontend.controller.Client;
import frontend.controller.professor.ProfessorGUI;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.enrollment.EnrollmentPage;
import shared.interfaces.ProfessorCommands;
import shared.objects.Course;
import shared.objects.SendMessage;
import shared.objects.Student;
import shared.objects.StudentEnrollment;
/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 * Used to enroll students
 */
public class EnrollmentButtonListener
		implements ActionListener, ProfessorCommands
{
	/**
	 * ProfessorGUI object for use for the listener
	 */
	private ProfessorGUI professorGUI;
	
	/**
	 * The client associated with the client
	 */
	private Client client;
	
	/**
	 * The enrollment page 
	 */
	private EnrollmentPage enrollmentPage;
	
	/**
	 * The course 
	 */
	private Course myCourse;

	/** The constructor for the enrollment button listener
	 * @param professorGUI The professor GUI 
	 * @param client The connection used for connections
	 * @param enrollmentPage The enrollment page
	 * @param course The specific course
	 */
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