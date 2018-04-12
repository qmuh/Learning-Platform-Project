package frontend.controller.student;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import frontend.controller.listeners.AssignmentLabelMouseListener;
import frontend.controller.professor.listeners.CourseActiveButtonListener;
import frontend.controller.professor.listeners.NewCourseButtonListener;
import frontend.controller.listeners.SubmitSubmissionButtonListener;
import frontend.controller.listeners.UploadAssignmentButtonListener;
import frontend.controller.listeners.UploadSubmissionButtonListener;
import frontend.controller.student.listeners.StudentSendButtonListener;
import frontend.view.pages.Refresh;
import frontend.view.pages.assignment.AssignmentPageStudent;
import frontend.view.pages.components.CourseNavigationBarStudent;
import frontend.view.pages.components.PageNavigator;
import frontend.view.pages.components.PageNavigator.ViewCoursePageListener;
import frontend.view.pages.compose.ComposeEmailPageStudent;
import frontend.view.pages.course.CoursePage;
import frontend.view.pages.discussion.DiscussionPage;
import frontend.view.pages.grade.GradePage;
import frontend.view.pages.home.HomePage;
import frontend.view.pages.items.assignment.AssignItemStudent;
import frontend.view.pages.items.course.CourseItemProfessor;
import frontend.view.pages.items.course.CourseItemStudent;
import frontend.view.pages.items.grade.GradeItem;
import frontend.view.pages.items.submission.SubmitItem;
import frontend.view.pages.items.submission.SubmitItemStudent;
import frontend.view.pages.submission.SubmissionPageStudent;
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
	private static final long serialVersionUID = 1L;
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
		super(socket, user);
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
		GradePage gradePage = new GradePage(course, student);

		Refresh function = () ->
		{
			SendMessage<Course> gradesRequest = new SendMessage<Course>(course,
					CMD_RECEIVE + RECEIVE_GRADES);
			SendMessage<Course> assignmentsRequest = new SendMessage<Course>(
					course, CMD_RECEIVE + RECEIVE_ASSIGNMENTS);

			try
			{
				Vector<Grade> receivedGrades = (Vector<Grade>) client
						.sendMessage(gradesRequest);

				Vector<Assignment> assignments = (Vector<Assignment>) client
						.sendMessage(assignmentsRequest);

				for (int j = 0; j < receivedGrades.size(); j++)
				{	
					System.out.println("GRADE VALUE IS " +receivedGrades.get(j).getGrade());
					for (int i = 0; i < assignments.size(); i++)
					{
						Assignment assignment = assignments.elementAt(i);

						if (receivedGrades.elementAt(j)
								.getAssignID() == assignment.getId())
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
		};

		gradePage.setRefreshBehaviour(function);

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
		CoursePage coursePage = new CoursePage<>(course, student);

		Refresh function = () ->
		{

		};

		coursePage.setRefreshBehaviour(function);

		completeCoursePage(coursePage, course);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createAssignmentPage(Course course)
	{
		AssignmentPageStudent assignmentPage = new AssignmentPageStudent(course,
				student);

		Refresh function = () ->
		{
			SendMessage<Course> receiveAssignments = new SendMessage<Course>(
					course, CMD_RECEIVE + RECEIVE_ASSIGNMENTS);

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

					assignItemStudent.getUpload().addActionListener(
							new UploadSubmissionButtonListener(client, course,
									assignmentPage));

					Submission toGive = new Submission(
							assignments.elementAt(i).getId(), student.getId(),
							null, 0, null, null, null);

					assignItemStudent.getSubmit().addActionListener(
							new SubmitSubmissionButtonListener(client, course,
									assignmentPage, toGive));

					assignmentPage.addToBoxList(assignItemStudent);

					assignmentPage.addToBoxList(assignItemStudent);
				}

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		};

		assignmentPage.setRefreshBehaviour(function);

		completeCoursePage(assignmentPage, course);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createSubmissionPage(Course course)
	{
		SubmissionPageStudent submissionPageStudent = new SubmissionPageStudent(
				course, student);

		Refresh function = () ->
		{
			SendMessage<Course> requestSubmissions = new SendMessage<Course>(
					course, CMD_RECEIVE + RECEIVE_SUBMISSIONS);

			SendMessage<Course> requestAssignments = new SendMessage<Course>(
					course, CMD_RECEIVE + RECEIVE_ASSIGNMENTS);

			try
			{
				Vector<Submission> submissions = (Vector<Submission>) client
						.sendMessage(requestSubmissions);
				Vector<Assignment> assignments = (Vector<Assignment>) client
						.sendMessage(requestAssignments);

				for (int i = 0; i < assignments.size(); i++)
				{
					submissionPageStudent
							.addAssignment(assignments.elementAt(i));
				}

				for (int i = 0; i < submissions.size(); i++)
				{
					SubmitItemStudent submitItem = new SubmitItemStudent(
							submissions.elementAt(i));

					submissionPageStudent.addSubmission(submitItem);
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		};
		submissionPageStudent.setRefreshBehaviour(function);

		completeCoursePage(submissionPageStudent, course);
	}

	@Override
	protected void createComposeEmailPage(Course course)
	{
		ComposeEmailPageStudent composeEmailPage = new ComposeEmailPageStudent(
				course, student);
		// composeEmailPage.getSendButton()
		// .addActionListener(new StudentSendButtonListener(course,
		// composeEmailPage, client));

		Refresh function = () ->
		{
			SendMessage<Course> requestProfessor = new SendMessage<Course>(
					course, CMD_RECEIVE + RECEIVE_PROFESSOR);
			Professor professor = null;
			try
			{
				professor = (Professor) client.sendMessage(requestProfessor);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			composeEmailPage.getToField().setText(professor.getEmail());
		};

		composeEmailPage.setRefreshBehaviour(function);

		composeEmailPage.getSendButton()
				.addActionListener(new StudentSendButtonListener(course,
						composeEmailPage, client));

		completeCoursePage(composeEmailPage, course);
	}

	@Override
	protected void createDiscussionPage(Course course)
	{
		DiscussionPage discussionPage = new DiscussionPage(course, student);

		Refresh function = () ->
		{
			// TODO: Complete functionality.
		};

		discussionPage.setRefreshBehaviour(function);

		completeCoursePage(discussionPage, course);
	}

	@Override
	protected void createHomePage()
	{
		HomePage homePage = (HomePage) this.searchPage(HOME_PAGE);

		Refresh function = () ->
		{
			SendMessage<Course> message = new SendMessage<Course>(
					CMD_RECEIVE + RECEIVE_COURSES);
			try
			{
				@SuppressWarnings("unchecked")
				Vector<Course> coursesList = (Vector<Course>) this.client
						.sendMessage(message);

				if (coursesList != null)
				{
					for (int i = 0; i < coursesList.size(); i++)
					{
						Course course = coursesList.elementAt(i);
						CourseItemStudent courseItemStudent = new CourseItemStudent(
								course);

						homePage.addToBoxList(courseItemStudent);

						courseItemStudent.getViewButton().addActionListener(
								new ViewCoursePageListener(course));

						createNewCourse(course, homePage);

						System.out.println("Course name is: "
								+ coursesList.get(i).getName());
					}
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		};
		homePage.setRefreshBehaviour(function);

		homePage.refresh();
	}
}
