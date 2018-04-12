package frontend.controller.student;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JPanel;

import frontend.controller.listeners.AssignmentLabelMouseListener;
import frontend.controller.listeners.UploadAssignmentButtonListener;
import frontend.controller.student.listeners.StudentSendButtonListener;
import frontend.view.pages.assignment.AssignmentPage;
import frontend.view.pages.assignment.AssignmentPageStudent;
import frontend.view.pages.components.CourseNavigationBarStudent;
import frontend.view.pages.components.PageNavigator;
import frontend.view.pages.compose.ComposeEmailPage;
import frontend.view.pages.compose.ComposeEmailPageStudent;
import frontend.view.pages.course.CoursePage;
import frontend.view.pages.discussion.DiscussionPage;
import frontend.view.pages.grade.GradePage;
import frontend.view.pages.home.HomePage;
import frontend.view.pages.items.assignment.AssignItem;
import frontend.view.pages.items.assignment.AssignItemStudent;
import frontend.view.pages.items.course.CourseItemStudent;
import frontend.view.pages.items.grade.GradeItem;
import frontend.view.pages.submission.SubmissionPage;
import frontend.view.pages.submission.SubmissionPageStudent;
import frontend.view.pages.items.submission.SubmitItem;
import shared.interfaces.StudentCommands;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Grade;
import shared.objects.Professor;
import shared.objects.SendMessage;
import shared.objects.Student;
import shared.objects.Submission;

/**
 * Holds all information for the STUDENT GUI and how it is set up.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class StudentGUI extends PageNavigator implements StudentCommands
{
	/**
	 * The user associated with this client-side GUI
	 */
	private Student student;

	/**
	 * Used to initialize the StudentGUI and the all its pages
	 *
	 * @param socket
	 *            Used to communicate between server and client
	 * @param user
	 *            The user who is running this client
	 */
	public StudentGUI(Socket socket, Student user)
	{
		super(socket);
		this.student = user;
		createHomePage();
	}

	/**
	 * Used to create the grades page
	 *
	 * @param course
	 *            The course which is associated with this page
	 */
	@SuppressWarnings("unchecked")
	private void createGradesPage(Course course)
	{
		GradePage gradePage = new GradePage(course);
	
		SendMessage<Course> gradesRequest = new SendMessage<Course>(course,
				CMD_RECEIVE + RECEIVE_GRADES);
		SendMessage<Course> assignmentsRequest = new SendMessage<Course>(course,
				CMD_RECEIVE + RECEIVE_ASSIGNMENTS);
	
		try
		{
			Vector<Grade> receivedGrades = (Vector<Grade>) client
					.sendMessage(gradesRequest);
	
			Vector<Assignment> assignments = (Vector<Assignment>) client
					.sendMessage(assignmentsRequest);
	
			for (int j = 0; j < receivedGrades.size(); j++)
			{
				for (int i = 0; i < assignments.size(); i++)
				{
					Assignment assignment = assignments.elementAt(i);
	
					if (receivedGrades.elementAt(j).getAssignID() == assignment
							.getId())
					{
						gradePage.addToBoxList(
								new GradeItem(assignment.getTitle(),
										receivedGrades.elementAt(j)));
					}
	
				}
			}
	
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
		completeCoursePage(gradePage, course);
	}

	/**
	 * Used to complete the course page by adding a navigation bar and listeners
	 *
	 * @param genericCoursePage
	 *            the course page to complete
	 * 
	 * @param course
	 *            the course that the page is associated with
	 */
	private void completeCoursePage(CoursePage<?, ?> genericCoursePage,
			Course course)
	{
		genericCoursePage
				.setCourseNavigationBar(new CourseNavigationBarStudent());
		genericCoursePage.createSidebarListeners(course, this);
		this.addPage(genericCoursePage);
	}

	@Override
	protected void createNewCourse(Course course, HomePage homePage)
	{
		super.createNewCourse(course, homePage);
		createGradesPage(course);
	}

	@Override
	protected void createCoursePage(Course course)
	{
		CoursePage coursePage = new CoursePage<>(course);
		completeCoursePage(coursePage, course);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createAssignmentPage(Course course)
	{
		AssignmentPageStudent assignmentPage = new AssignmentPageStudent(
				course);

		SendMessage<Course> receiveAssignments = new SendMessage<Course>(course,
				CMD_RECEIVE + RECEIVE_ASSIGNMENTS);

		try
		{

			Vector<Assignment> assignments = (Vector<Assignment>) client
					.sendMessage(receiveAssignments);

			for (int i = 0; i < assignments.size(); i++)
			{
				AssignItemStudent assignItemStudent = new AssignItemStudent(
						assignments.elementAt(i));
				assignmentPage.addToBoxList(assignItemStudent);

				assignItemStudent.getAssignmentLabel().addMouseListener(
						new AssignmentLabelMouseListener(assignments.get(i),
								client));
				// assignItemStudent.getUpload()
				// .addActionListener(new UploadAssignmentButtonListener(client,
				// course, assignmentPage));

				assignmentPage.addToBoxList(assignItemStudent);
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		completeCoursePage(assignmentPage, course);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createSubmissionPage(Course course)
	{
		SubmissionPageStudent submissionPageStudent = new SubmissionPageStudent(
				course, student);

		SendMessage<Course> requestSubmissions = new SendMessage<Course>(course,
				CMD_RECEIVE + RECEIVE_SUBMISSIONS);

		SendMessage<Course> requestAssignments = new SendMessage<Course>(course,
				CMD_RECEIVE + RECEIVE_ASSIGNMENTS);

		try
		{
			Vector<Submission> submissions = (Vector<Submission>) client
					.sendMessage(requestSubmissions);
			Vector<Assignment> assignments = (Vector<Assignment>) client
					.sendMessage(requestAssignments);

			for (int i = 0; i < assignments.size(); i++)
			{
				submissionPageStudent.addAssignment(assignments.elementAt(i));
			}

			for (int i = 0; i < submissions.size(); i++)
			{
				SubmitItem submitItem = new SubmitItem(
						submissions.elementAt(i));

				submissionPageStudent.addSubmission(submitItem);
			}

		} catch (IOException e)
		{
			// TODO: handle exception
		}

		completeCoursePage(submissionPageStudent, course);
	}

	@Override
	protected void createComposeEmailPage(Course course)
	{
		ComposeEmailPageStudent composeEmailPage = new ComposeEmailPageStudent(
				course);
		composeEmailPage.getSendButton()
				.addActionListener(new StudentSendButtonListener(course,
						composeEmailPage, client));

		SendMessage<Course> requestProfessor = new SendMessage<Course>(course,
				CMD_RECEIVE + RECEIVE_PROFESSOR);
		Professor professor = null;
		try
		{
			professor = (Professor) client.sendMessage(requestProfessor);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		composeEmailPage.getToField().setText(professor.getEmail());
		completeCoursePage(composeEmailPage, course);
	}

	@Override
	protected void createDiscussionPage(Course course)
	{
		DiscussionPage discussionPage = new DiscussionPage(course);
		completeCoursePage(discussionPage, course);
	}

	@Override
	protected void createCourseItem(Course course, HomePage homePage)
	{
		CourseItemStudent courseItem = new CourseItemStudent(course);
		courseItem.getViewButton()
				.addActionListener(new ViewCoursePageListener(course));
		homePage.addToBoxList(courseItem);
	}
}
