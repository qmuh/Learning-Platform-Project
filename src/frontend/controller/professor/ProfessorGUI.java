package frontend.controller.professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frontend.controller.listeners.AssignmentLabelMouseListener;
import frontend.controller.listeners.BrowseButtonListener;
import frontend.controller.listeners.SubmissionLabelMouseListener;
import frontend.controller.listeners.UploadAssignmentButtonListener;
import frontend.controller.professor.listeners.AddToEmailButtonListener;
import frontend.controller.professor.listeners.AssignmentActiveButtonListener;
import frontend.controller.professor.listeners.CourseActiveButtonListener;
import frontend.controller.professor.listeners.EnrollmentButtonListener;
import frontend.controller.professor.listeners.GradeSubmissionButtonListener;
import frontend.controller.professor.listeners.NewCourseButtonListener;
import frontend.controller.professor.listeners.ProfessorSendButtonListener;
import frontend.controller.professor.listeners.SendToAllButtonListener;
import frontend.view.pages.Refresh;
import frontend.view.pages.assignment.AssignmentPageProfessor;
import frontend.view.pages.components.CourseNavigationBarProfessor;
import frontend.view.pages.components.PageNavigator;
import frontend.view.pages.components.PageNavigator.ViewCoursePageListener;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.compose.ComposeEmailPageProfessor;
import frontend.view.pages.course.CoursePage;
import frontend.view.pages.discussion.DiscussionPage;
import frontend.view.pages.enrollment.EnrollmentPage;
import frontend.view.pages.home.HomePage;
import frontend.view.pages.items.assignment.AssignItemProfessor;
import frontend.view.pages.items.course.CourseItemProfessor;
import frontend.view.pages.items.submission.SubmitItem;
import frontend.view.pages.submission.SubmissionPageProfessor;
import shared.interfaces.ProfessorCommands;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Professor;
import shared.objects.SendMessage;
import shared.objects.Student;
import shared.objects.StudentEnrollment;
import shared.objects.Submission;

/**
 * Class which handles the functionality of the Prrofessor GUI
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class ProfessorGUI extends PageNavigator implements ProfessorCommands
{
	private static final long serialVersionUID = 1L;

	/**
	 * The professor object which contains information needed for client server
	 * relations
	 */
	private Professor professor;

	/**
	 * Constructor for this class, it pre-loads the pages
	 *
	 * @param socket
	 *            Used to handle communication between user and server
	 * @param user
	 *            The professor object associated with this class
	 */
	public ProfessorGUI(Socket socket, Professor user)
	{
		super(socket, user);
		this.professor = user;
		createHomePage();
	}

	// /**
	// * Shows all students overall, and all enrolled student
	// *
	// * @param course
	// * The course where this is shown
	// * @param enrollmentPage
	// * The page for this
	// */
	// @SuppressWarnings("unchecked")
	// public void showAllStudents(Course course, EnrollmentPage enrollmentPage)
	// {
	// try
	// {
	// Vector<Student> myList = (Vector<Student>) client.sendMessage(
	// new SendMessage<>(CMD_RECEIVE + RECEIVE_ALL_STUDENTS));
	// enrollmentPage.setStudentList(myList);
	//
	// Vector<Student> enrollList = (Vector<Student>) client
	// .sendMessage(new SendMessage<Course>(course,
	// CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS));
	// enrollmentPage.setEnrolledList(enrollList);
	// } catch (IOException e)
	// {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Getter method for the professor
	 * 
	 * @return
	 */
	public Professor getProfessor()
	{
		return professor;
	}

	@Override
	public void createNewCourse(Course course, HomePage homePage)
	{
		super.createNewCourse(course, homePage);
		createEnrollmentPage(course);
	}

	// /**
	// * Shows all assignments for a course
	// *
	// * @param course
	// * The course
	// * @param assignmentPage
	// * The page where the assignmnets would be shown
	// */
	// @SuppressWarnings("unchecked")
	// private void showAllAssignments(Course course,
	// AssignmentPageProfessor assignmentPage)
	// {
	// try
	// {
	// SendMessage<Course> requestAssignments = new SendMessage<Course>(
	// course, CMD_RECEIVE + RECEIVE_ALL_ASSIGNMENTS);
	// Vector<Assignment> myList = (Vector<Assignment>) client
	// .sendMessage(requestAssignments);
	//
	// for (Assignment assignment : myList)
	// {
	// AssignItemProfessor assignItem = new AssignItemProfessor(
	// assignment);
	//
	// assignItem.getActiveButton().addActionListener(
	// new AssignmentActiveButtonListener(client, assignment));
	//
	// assignItem.getAssignmentLabel().addMouseListener(
	// new AssignmentLabelMouseListener(assignment, client));
	// assignmentPage.addToBoxList(assignItem);
	// }
	//
	// } catch (IOException e)
	// {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Creates the enrollment page and sets up it's listeners by calling them
	 * 
	 * @param course
	 *            The course
	 */
	@SuppressWarnings("unchecked")
	private void createEnrollmentPage(Course course)
	{
		EnrollmentPage enrollmentPage = new EnrollmentPage(course, professor);

		Refresh function = () ->
		{
			try
			{
				Vector<Student> myList = (Vector<Student>) client.sendMessage(
						new SendMessage<>(CMD_RECEIVE + RECEIVE_ALL_STUDENTS));
				enrollmentPage.setStudentList(myList);

				Vector<Student> enrollList = (Vector<Student>) client
						.sendMessage(new SendMessage<Course>(course,
								CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS));
				enrollmentPage.setEnrolledList(enrollList);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		};

		enrollmentPage.setRefreshBehaviour(function);

		enrollmentPage.setSearchButtonListener(
				new EnrollmentPageSearchButtonListener(enrollmentPage));
		enrollmentPage.setEnrollmentPageButtonListener(
				new EnrollmentButtonListener(this, client, enrollmentPage,
						course));
		enrollmentPage
				.setEnrollmentListListener(new EnrollmentListSelectionListener(
						enrollmentPage.getEnrollmentButton(), course));

		completeCoursePage(enrollmentPage, course);
	}

	/**
	 * Inner class used for the Enrollment Page search button
	 */
	private class EnrollmentPageSearchButtonListener implements ActionListener
	{
		private EnrollmentPage enrollmentPage;

		public EnrollmentPageSearchButtonListener(EnrollmentPage enrollmentPage)
		{
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
					Student myResult = (Student) client.sendMessage(
							new SendMessage<>(Integer.parseInt(search),
									CMD_RECEIVE + RECEIVE_STUDENT_BY_ID));
					searchResult.add(myResult);
				} else if (enrollmentPage.isSearchByLastName())
				{
					searchResult = (Vector<Student>) client
							.sendMessage(new SendMessage<String>(search,
									CMD_RECEIVE + RECEIVE_STUDENT_BY_LASTNAME));
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

	/**
	 * Inner class for the Enrollment list listener
	 */
	private class EnrollmentListSelectionListener
			implements ListSelectionListener
	{
		private static final String ENROLL_LABEL = "Enroll";
		private static final String UNENROLL_LABEL = "Unenroll";

		private WButton enrollmentButton;
		private Course myCourse;

		public EnrollmentListSelectionListener(WButton button, Course course)
		{
			this.enrollmentButton = button;
			this.myCourse = course;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			JList<Student> model = (JList<Student>) e.getSource();

			if (model.isSelectionEmpty())
			{
				enrollmentButton.setEnabled(false);
				enrollmentButton.setText(ENROLL_LABEL);
			} else
			{
				Student selectedStudent = model.getSelectedValue();
				StudentEnrollment toSend = new StudentEnrollment(
						selectedStudent.getId(), myCourse.getId());

				try
				{
					Boolean isEnrolled = (Boolean) client.sendMessage(
							new SendMessage<StudentEnrollment>(toSend,
									CMD_RECEIVE + RECEIVE_STUDENT_IS_ENROLLED));

					if (isEnrolled)
					{
						enrollmentButton.setText(UNENROLL_LABEL);
					} else
					{
						enrollmentButton.setText(ENROLL_LABEL);
					}
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}

				enrollmentButton.setEnabled(true);
			}
		}
	}

	/**
	 * Completes a course page by adding a navigation bar and its listeners
	 * 
	 * @param genericCoursePage
	 *            The course page to complete
	 * @param course
	 *            The associated course
	 */
	private void completeCoursePage(CoursePage<?, ?> genericCoursePage,
			Course course)
	{
		genericCoursePage
				.setCourseNavigationBar(new CourseNavigationBarProfessor());
		genericCoursePage.createSidebarListeners(course, this);
		this.addPage(genericCoursePage);
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
						CourseItemProfessor courseItemProfessor = new CourseItemProfessor(
								course);

						homePage.addToBoxList(courseItemProfessor);

						courseItemProfessor.getActiveButton().addActionListener(
								new CourseActiveButtonListener(client, course));
						courseItemProfessor.getViewButton().addActionListener(
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

		homePage.setNewCourseListener(
				new NewCourseButtonListener(this, client, homePage));
		homePage.enableActiveLabel();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createSubmissionPage(Course course)
	{
		SubmissionPageProfessor submissionPage = new SubmissionPageProfessor(
				course, professor);

		Refresh function = () ->
		{
			SendMessage<Course> requestAssignments = new SendMessage<Course>(
					course, CMD_RECEIVE + RECEIVE_ALL_ASSIGNMENTS);
			SendMessage<Course> requestStudents = new SendMessage<Course>(
					course, CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS);
			SendMessage<Course> requestSubmissions = new SendMessage<Course>(
					course, CMD_RECEIVE + RECEIVE_ALL_SUBMISSIONS);

			try
			{
				Vector<Assignment> assignments = (Vector<Assignment>) this.client
						.sendMessage(requestAssignments);
				Vector<Student> students = (Vector<Student>) this.client
						.sendMessage(requestStudents);
				Vector<Submission> submissions = (Vector<Submission>) this.client
						.sendMessage(requestSubmissions);

				if (assignments != null)
				{
					for (int i = 0; i < assignments.size(); i++)
					{
						Assignment assignment = assignments.elementAt(i);
						submissionPage.addAssignment(assignment, students);
					}
				}

				for (int i = 0; i < submissions.size(); i++)
				{
					Submission submission = submissions.elementAt(i);
					SubmitItem submitItem = new SubmitItem(submission);
					submitItem.getGradeButton().addActionListener(
							new GradeSubmissionButtonListener(client, course,
									submitItem));
					submitItem.getAssignmentLink().addMouseListener(
							new SubmissionLabelMouseListener(submission,
									client));

					submissionPage.addSubmission(submitItem);
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		};

		submissionPage.setRefreshBehaviour(function);

		completeCoursePage(submissionPage, course);
	}

	/**
	 * @param course
	 * @param homePage
	 */
	@Override
	protected void createCourseItem(Course course, HomePage homePage)
	{
		CourseItemProfessor courseItem = new CourseItemProfessor(course);
		courseItem.getActiveButton().addActionListener(
				new CourseActiveButtonListener(client, course));
		courseItem.getViewButton()
				.addActionListener(new ViewCoursePageListener(course));
		homePage.addToBoxList(courseItem);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createAssignmentPage(Course course)
	{
		AssignmentPageProfessor assignmentPage = new AssignmentPageProfessor(
				course, professor);

		Refresh function = () ->
		{
			try
			{
				SendMessage<Course> requestAssignments = new SendMessage<Course>(
						course, CMD_RECEIVE + RECEIVE_ALL_ASSIGNMENTS);
				Vector<Assignment> myList = (Vector<Assignment>) client
						.sendMessage(requestAssignments);

				for (Assignment assignment : myList)
				{
					AssignItemProfessor assignItem = new AssignItemProfessor(
							assignment);

					assignItem.getActiveButton().addActionListener(
							new AssignmentActiveButtonListener(client,
									assignment));

					assignItem.getAssignmentLabel().addMouseListener(
							new AssignmentLabelMouseListener(assignment,
									client));
					assignmentPage.addToBoxList(assignItem);
				}

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		};

		assignmentPage.setRefreshBehaviour(function);

		assignmentPage.setUploadButtonListener(
				new UploadAssignmentButtonListener(client, course,
						assignmentPage));
		assignmentPage.setBrowseButtonListener(
				new BrowseButtonListener(assignmentPage));

		completeCoursePage(assignmentPage, course);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void createComposeEmailPage(Course course)
	{
		ComposeEmailPageProfessor composeEmailPage = new ComposeEmailPageProfessor(
				course, professor);
		// composeEmailPage
		// .setMyEmailButtonListener(new MyEmailsButtonListener(course));

		// Sets the enrolled J-List to help email choosing
		Refresh function = () -> 
		{
			try
			{
				Vector<Student> enrollList = (Vector<Student>) client
						.sendMessage(new SendMessage<Course>(course,
								CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS));
				composeEmailPage.setStudentList(enrollList);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		};
		composeEmailPage.setRefreshBehaviour(function);
		

		composeEmailPage.getSendToAllButton().addActionListener(
				new SendToAllButtonListener(client, course, composeEmailPage));
		composeEmailPage.setSendButtonListener(new ProfessorSendButtonListener(
				client, course, composeEmailPage));
		composeEmailPage.getAddToEmailButton().addActionListener(
				new AddToEmailButtonListener(client, course, composeEmailPage));

		completeCoursePage(composeEmailPage, course);
	}

	@Override
	protected void createCoursePage(Course course)
	{
		@SuppressWarnings("rawtypes")
		CoursePage coursePage = new CoursePage(course, professor);

		coursePage.setRefreshBehaviour(new Refresh()
		{
			// Do nothing to refresh an empty course page.
			@Override
			public void refresh()
			{
			}
		});

		completeCoursePage(coursePage, course);
	}

	@Override
	protected void createDiscussionPage(Course course)
	{
		DiscussionPage discussionPage = new DiscussionPage(course, professor);
		
		Refresh function = () -> {
			// TODO:
		};
		
		discussionPage.setRefreshBehaviour(function);
		
		completeCoursePage(discussionPage, course);
	}
}
