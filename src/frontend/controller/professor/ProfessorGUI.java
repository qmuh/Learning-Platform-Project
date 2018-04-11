package frontend.controller.professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frontend.controller.Client;
import frontend.controller.listeners.*;
import frontend.controller.professor.listeners.*;
import frontend.view.pages.AssignmentPage;
import frontend.view.pages.ComposeEmailPage;
import frontend.view.pages.CoursePage;
import frontend.view.pages.DiscussionPage;
import frontend.view.pages.EnrollmentPage;
import frontend.view.pages.HomePage;
import frontend.view.pages.SubmissionPage;
import frontend.view.pages.components.PageNavigator;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.items.CourseItem;
import frontend.view.pages.items.SubmitItem;
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
	 * Object needed to communicate effectively with the server
	 */
	private Client client;

	/**
	 * Constructor for this class, it pre-loads the pages
	 *
	 * @param socket
	 * @param user
	 */
	public ProfessorGUI(Socket socket, Professor user)
	{
		super();
		this.client = new Client();
		this.client.connectToServer(socket);
		this.professor = user;
		createHomePage();
	}

	@SuppressWarnings("unchecked")
	public void showAllStudents(Course course, EnrollmentPage enrollmentPage)
	{
		try
		{
			Vector<Student> myList = (Vector<Student>) client
					.sendMessage(new SendMessage<>(CMD_RECEIVE + RECEIVE_ALL_STUDENTS));
			enrollmentPage.setStudentList(myList);

			Vector<Student> enrollList = (Vector<Student>) client
					.sendMessage(new SendMessage<Course>(course, CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS));
			enrollmentPage.setEnrolledList(enrollList);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Professor getProfessor()
	{
		return professor;
	}

	public void createNewCourse(Course course, HomePage homePage)
	{
		createCourseItem(course, homePage);
		createCoursePage(course);
		createAssignmentPage(course);
		createSubmissionPage(course);
		createEnrollmentPage(course);
		createComposeEmailPage(course);
		createDiscussionPage(course);
	}

	@SuppressWarnings("unchecked")
	private void createHomePage()
	{
		HomePage homePage = (HomePage) this.searchPage(HOME_PAGE);

		SendMessage<Course> message = new SendMessage<Course>(CMD_RECEIVE + RECEIVE_COURSES);
		try
		{
			Vector<Course> coursesList = (Vector<Course>) this.client.sendMessage(message);

			if (coursesList != null)
			{
				for (int i = 0; i < coursesList.size(); i++)
				{
					Course course = coursesList.elementAt(i);

					createNewCourse(course, homePage);

					System.out.println("Course name is: " + coursesList.get(i).getName());
				}
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		homePage.setNewCourseListener(new NewCourseButtonListener(this, client, homePage));
		homePage.displayPage();
	}

	@SuppressWarnings("unchecked")
	private void createSubmissionPage(Course course)
	{
		SubmissionPage submissionPage = new SubmissionPage(course);
		submissionPage.createSidebarListeners(course, this);

		SendMessage<Course> requestAssignments = new SendMessage<Course>(course, CMD_RECEIVE + RECEIVE_ALL_ASSIGNMENTS);
		SendMessage<Course> requestStudents = new SendMessage<Course>(course,
				CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS);
		SendMessage<Course> requestSubmissions = new SendMessage<Course>(course, CMD_RECEIVE + RECEIVE_ALL_SUBMISSIONS);

		try
		{
			Vector<Assignment> assignments = (Vector<Assignment>) this.client.sendMessage(requestAssignments);
			Vector<Student> students = (Vector<Student>) this.client.sendMessage(requestStudents);
			Vector<Submission> submissions = (Vector<Submission>) this.client.sendMessage(requestSubmissions);

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
				submitItem.getGradeButton()
						.addActionListener(new GradeSubmissionButtonListener(client, course, submitItem));
				submitItem.getAssignmentLink().addMouseListener(new SubmissionLabelMouseListener(submission, client));
				
				submissionPage.addSubmission(submitItem);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		this.addPage(submissionPage);
	}

	/**
	 * @param course
	 * @param homePage
	 */
	private void createCourseItem(Course course, HomePage homePage)
	{
		CourseItem courseItem = new CourseItem(course);
		courseItem.setViewButtonListener(new ViewCoursePageListener(course));
		courseItem.setActiveButtonListener(new CourseActiveButtonListener(client, course));
		homePage.addToBoxList(courseItem);
	}

	private void createCoursePage(Course course)
	{
		CoursePage coursePage = new CoursePage(course);

		coursePage.createSidebarListeners(course, this);

		this.addPage(coursePage);
	}

	private void createAssignmentPage(Course course)
	{
		AssignmentPage assignmentPage = new AssignmentPage(course);
		assignmentPage.createSidebarListeners(course, this);

		assignmentPage.setUploadButtonListener(new UploadButtonListener(client, course, assignmentPage));
		assignmentPage.setBrowseButtonListener(new BrowseButtonListener(assignmentPage));
		showAllAssignments(course, assignmentPage);

		this.addPage(assignmentPage);
	}

	@SuppressWarnings("unchecked")
	private void createComposeEmailPage(Course course)
	{
		ComposeEmailPage composeEmailPage = new ComposeEmailPage(course);
		composeEmailPage.createSidebarListeners(course, this);
		// composeEmailPage
		// .setMyEmailButtonListener(new MyEmailsButtonListener(course));
		this.addPage(composeEmailPage);

		composeEmailPage.setSendToAllButtonListener(new SendToAllButtonListener(client, course, composeEmailPage));

		composeEmailPage.setSendButtonListener(new SendButtonListener(client, course, composeEmailPage));
		composeEmailPage.setAddToEmailButtonListener(new AddToEmailButtonListener(client, course, composeEmailPage));

		// Sets the enrolled J-List to help email choosing
		try
		{
			Vector<Student> enrollList = (Vector<Student>) client
					.sendMessage(new SendMessage<Course>(course, CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS));
			composeEmailPage.setStudentList(enrollList);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createDiscussionPage(Course course)
	{
		DiscussionPage discussionPage = new DiscussionPage(course);
		discussionPage.createSidebarListeners(course, this);

		this.addPage(discussionPage);
		// TODO: create reply button listener
	}

	@SuppressWarnings("unchecked")
	private void showAllAssignments(Course course, AssignmentPage assignmentPage)
	{
		try
		{
			Vector<Assignment> myList = (Vector<Assignment>) client
					.sendMessage(new SendMessage<Course>(course, CMD_RECEIVE + RECEIVE_ALL_ASSIGNMENTS));
			for (Assignment assignment : myList)
			{
				assignmentPage.createAssignItem(assignment, new AssignmentActiveButtonListener(client, assignment));
			}

			// assignmentPage.setAssignmentVector(myList);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void createEnrollmentPage(Course course)
	{
		EnrollmentPage enrollmentPage = new EnrollmentPage(course);
		enrollmentPage.createSidebarListeners(course, this);
		enrollmentPage.setSearchButtonListener(new EnrollmentPageSearchButtonListener(enrollmentPage));
		enrollmentPage
				.setEnrollmentPageButtonListener(new EnrollmentButtonListener(this, client, enrollmentPage, course));

		enrollmentPage.setEnrollmentListListener(
				new EnrollmentListSelectionListener(enrollmentPage.getEnrollmentButton(), course));

		this.addPage(enrollmentPage);

		showAllStudents(course, enrollmentPage);
	}

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
							new SendMessage<>(Integer.parseInt(search), CMD_RECEIVE + RECEIVE_STUDENT_BY_ID));
					searchResult.add(myResult);
				} else if (enrollmentPage.isSearchByLastName())
				{
					searchResult = (Vector<Student>) client
							.sendMessage(new SendMessage<String>(search, CMD_RECEIVE + RECEIVE_STUDENT_BY_LASTNAME));
				}

				enrollmentPage.setStudentList(searchResult);
			} catch (NumberFormatException e2)
			{
				System.out.println("Incorrect Login Value EnteredExitedHandler for search id");
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}

		}
	}

	private class EnrollmentListSelectionListener implements ListSelectionListener
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
				StudentEnrollment toSend = new StudentEnrollment(selectedStudent.getId(), myCourse.getId());

				try
				{
					Boolean isEnrolled = (Boolean) client.sendMessage(
							new SendMessage<StudentEnrollment>(toSend, CMD_RECEIVE + RECEIVE_STUDENT_IS_ENROLLED));

					if (isEnrolled)
					{
						enrollmentButton.setText(UNENROLL_LABEL);
					} else
					{
						enrollmentButton.setText(ENROLL_LABEL);
					}
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				enrollmentButton.setEnabled(true);
			}
		}
	}
}