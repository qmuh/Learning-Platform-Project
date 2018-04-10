package frontend.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import frontend.controller.Client;
import frontend.view.pages.AssignmentPage;
import frontend.view.pages.ComposeEmailPage;
import frontend.view.pages.HomePage;
import frontend.view.pages.DiscussionPage;
import frontend.view.pages.SubmissionPage;
import frontend.view.pages.CoursePage;
import frontend.view.pages.EnrollmentPage;
import frontend.view.pages.components.PageNavigator;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.items.AssignItem;
import frontend.view.pages.items.CourseItem;
import shared.interfaces.ProfessorCommands;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Professor;
import shared.objects.SendMessage;
import shared.objects.Student;
import shared.objects.StudentEnrollment;

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
	private Professor thisProfessor;

	/**
	 * Object needed to communicate effectively with the server
	 */
	private Client clientController;

	/**
	 * Constructor for this class, it pre-loads the pages
	 *
	 * @param socket
	 * @param user
	 */
	public ProfessorGUI(Socket socket, Professor user)
	{
		super();
		this.clientController = new Client();
		this.clientController.connectToServer(socket);
		thisProfessor = user;
		createHomePage();
	}

	@SuppressWarnings("unchecked")
	private void createHomePage()
	{
		HomePage homePage = (HomePage) this.searchPage(HOME_PAGE);

		SendMessage<Course> message = new SendMessage<Course>(
				CMD_RECEIVE + RECEIVE_COURSES);
		Vector<Course> coursesList = new Vector<Course>();

		try
		{
			coursesList = (Vector<Course>) this.clientController
					.sendMessage(message);

			if (coursesList != null)
			{
				for (int i = 0; i < coursesList.size(); i++)
				{
					Course course = coursesList.elementAt(i);

					CoursePage<CourseItem, Course> coursePage = createCoursePage(
							course);
					createCourseItem(course, homePage);

					createAssignmentPage(course);
					createSubmissionPage(course);
					createEnrollmentPage(course);
					createComposeEmailPage(course);
					createDiscussionPage(course);
					System.out.println(
							"Course name is: " + coursesList.get(i).getName());
				}
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		homePage.setNewCourseListener(new NewCourseButtonListener(homePage));
		homePage.displayPage();
		// TODO: Set listeners for all view buttons
	}

	/**
	 * @param course
	 * @param homePage
	 */
	private void createCourseItem(Course course, HomePage homePage)
	{
		CourseItem courseItem = new CourseItem(course);
		courseItem
				.setViewButtonListener(new ViewCoursePageListener(courseItem));
		courseItem.setActiveButtonListener(
				new CourseActiveButtonListener(course));
		homePage.addToBoxList(courseItem);
	}

	private CoursePage createCoursePage(Course course)
	{
		CoursePage coursePage = new CoursePage(course);
		coursePage.setEnrollmentPageButtonListener(
				new EnrollmentPageButtonListener(course));
		coursePage.setAssignmentButtonListener(
				new AssignmentPageButtonListener(course));
		coursePage.setSubmissionButtonListener(
				new SubmissionPageButtonListener(course));
		coursePage.setComposeEmailButtonListener(
				new ComposeEmailPageButtonListener(course));
		coursePage.setDiscussionButtonListener(
				new DiscussionButtonListener(course));
		this.addPage(coursePage, coursePage.getName());
		return coursePage;
	}

	private void createAssignmentPage(Course course)
	{
		AssignmentPage assignmentPage = new AssignmentPage(course);
		assignmentPage.setEnrollmentPageButtonListener(
				new EnrollmentPageButtonListener(course));
		assignmentPage.setSubmissionButtonListener(
				new SubmissionPageButtonListener(course));
		assignmentPage.setComposeEmailButtonListener(
				new ComposeEmailPageButtonListener(course));
		assignmentPage.setDiscussionButtonListener(
				new DiscussionButtonListener(course));
		this.addPage(assignmentPage, assignmentPage.getName());
		assignmentPage.setUploadButtonListener(
				new UploadButtonListener(course, assignmentPage));
		assignmentPage.setBrowseButtonListener(
				new BrowseButtonListener(assignmentPage));
		showAllAssignments(course, assignmentPage);
	}

	private void createComposeEmailPage(Course course)
	{
		ComposeEmailPage composeEmailPage = new ComposeEmailPage(course);
		composeEmailPage.setEnrollmentPageButtonListener(
				new EnrollmentPageButtonListener(course));
		composeEmailPage.setAssignmentButtonListener(
				new AssignmentPageButtonListener(course));
		composeEmailPage.setSubmissionButtonListener(
				new SubmissionPageButtonListener(course));
//		composeEmailPage
//				.setMyEmailButtonListener(new MyEmailsButtonListener(course));
		this.addPage(composeEmailPage, composeEmailPage.getName());
		composeEmailPage.setSendToAllButtonListener(
				new SendToAllButtonListener(course, composeEmailPage));
		composeEmailPage.setDiscussionButtonListener(
				new DiscussionButtonListener(course));
		this.addPage(composeEmailPage, composeEmailPage.getName());
		composeEmailPage.setSendToAllButtonListener(
				new SendToAllButtonListener(course, composeEmailPage));
		composeEmailPage.setSendButtonListener(new SendButtonListener(course, composeEmailPage));
		composeEmailPage.setAddToEmailButtonListener(
				new AddToEmailButtonListener(course, composeEmailPage));

		// Sets the enrolled J-List to help email choosing
		try
		{
			Vector<Student> enrollList = (Vector<Student>) clientController
					.sendMessage(new SendMessage<Course>(course,
							CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS));
			composeEmailPage.setStudentList(enrollList);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createSubmissionPage(Course course)
	{
		SubmissionPage submissionPage = new SubmissionPage(course);
		submissionPage.setEnrollmentPageButtonListener(
				new EnrollmentPageButtonListener(course));
		submissionPage.setAssignmentButtonListener(
				new AssignmentPageButtonListener(course));
		submissionPage.setComposeEmailButtonListener(
				new ComposeEmailPageButtonListener(course));
		submissionPage.setDiscussionButtonListener(
				new DiscussionButtonListener(course));
		this.addPage(submissionPage, submissionPage.getName());
		// TODO: set up submission page listeners
	}

	private void createDiscussionPage(Course course)
	{
		DiscussionPage discussionPage = new DiscussionPage(course);
		discussionPage.setEnrollmentPageButtonListener(
				new EnrollmentPageButtonListener(course));
		discussionPage.setAssignmentButtonListener(
				new AssignmentPageButtonListener(course));
		discussionPage.setSubmissionButtonListener(
				new SubmissionPageButtonListener(course));
		discussionPage.setComposeEmailButtonListener(
				new ComposeEmailPageButtonListener(course));
		this.addPage(discussionPage, discussionPage.getName());
		// TODO: create reply button listener
	}

	@SuppressWarnings("unchecked")
	private void showAllAssignments(Course course,
			AssignmentPage assignmentPage)
	{
		try
		{
			Vector<Assignment> myList = (Vector<Assignment>) clientController
					.sendMessage(new SendMessage<Course>(course,
							CMD_RECEIVE + RECEIVE_ALL_ASSIGNMENTS));

			for (Assignment assignment : myList)
			{
				createAssignItem(course, assignmentPage, assignment);
			}

			// assignmentPage.setAssignmentVector(myList);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void createAssignItem(Course course, AssignmentPage assignmentPage,
			Assignment assignment)
	{
		AssignItem assignItem = new AssignItem(assignment);
		assignItem.setAssignmentActiveButtonListener(
				new AssignmentActiveButtonListener(assignment));
		assignmentPage.addToBoxList(assignItem);
		assignmentPage.displayPage();
	}

	private void createEnrollmentPage(Course course)
	{
		EnrollmentPage enrollmentPage = new EnrollmentPage(course);
		enrollmentPage.setAssignmentButtonListener(
				new AssignmentPageButtonListener(course));
		enrollmentPage.setSubmissionButtonListener(
				new SubmissionPageButtonListener(course));
		enrollmentPage.setComposeEmailButtonListener(
				new ComposeEmailPageButtonListener(course));
		enrollmentPage.setDiscussionButtonListener(
				new DiscussionButtonListener(course));
		this.addPage(enrollmentPage, enrollmentPage.getName());
		enrollmentPage.setSearchButtonListener(
				new SearchButtonListener(enrollmentPage));
		enrollmentPage.setEnrollmentPageButtonListener(
				new EnrollmentButtonListener(enrollmentPage, course));

		enrollmentPage
				.setEnrollmentListListener(new EnrollmentListSelectionListener(
						enrollmentPage.getEnrollmentButton(), course));

		showAllStudents(course, enrollmentPage);
	}

	@SuppressWarnings("unchecked")
	public void showAllStudents(Course course, EnrollmentPage enrollmentPage)
	{
		try
		{
			Vector<Student> myList = (Vector<Student>) clientController
					.sendMessage(new SendMessage<>(
							CMD_RECEIVE + RECEIVE_ALL_STUDENTS));
			enrollmentPage.setStudentList(myList);

			Vector<Student> enrollList = (Vector<Student>) clientController
					.sendMessage(new SendMessage<Course>(course,
							CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS));
			enrollmentPage.setEnrolledList(enrollList);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	private class SearchButtonListener implements ActionListener
	{
		private EnrollmentPage enrollmentPage;

		public SearchButtonListener(EnrollmentPage enrollmentPage)
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
					Student myResult = (Student) clientController.sendMessage(
							new SendMessage<>((int) Integer.parseInt(search),
									CMD_RECEIVE + RECEIVE_STUDENT_BY_ID));
					searchResult.add(myResult);
				} else if (enrollmentPage.isSearchByLastName())
				{
					searchResult = (Vector<Student>) clientController
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
					Boolean isEnrolled = (Boolean) clientController.sendMessage(
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				enrollmentButton.setEnabled(true);
			}
		}
	}

	private class EnrollmentButtonListener implements ActionListener
	{
		private EnrollmentPage enrollmentPage;
		private Course myCourse;

		public EnrollmentButtonListener(EnrollmentPage enrollmentPage,
				Course course)
		{
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
					Boolean isEnrolled = (Boolean) clientController.sendMessage(
							new SendMessage<StudentEnrollment>(toSend,
									CMD_RECEIVE + RECEIVE_STUDENT_IS_ENROLLED));

					if (isEnrolled)
					{
						clientController.onlySendMessage(
								new SendMessage<StudentEnrollment>(toSend,
										CMD_INSERT + INSERT_UNENROLLMENT));
						enrollmentButton.setText("Enroll");
					} else
					{
						clientController.onlySendMessage(
								new SendMessage<StudentEnrollment>(toSend,
										CMD_INSERT + INSERT_ENROLLMENT));
						enrollmentButton.setText("Unenroll");
					}

				} catch (IOException e1)
				{
					e1.printStackTrace();
				}

				showAllStudents(myCourse, enrollmentPage);

			}

			showAllStudents(myCourse, enrollmentPage);
			createComposeEmailPage(myCourse);
		}
	}

	private class UnenrollButtonListener implements ActionListener
	{
		private EnrollmentPage enrollmentPage;
		private Course myCourse;

		public UnenrollButtonListener(EnrollmentPage enrollmentPage,
				Course course)
		{
			this.enrollmentPage = enrollmentPage;
			myCourse = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{

			try
			{
				Student selectedStudent = enrollmentPage.getSelectedStudent();
				System.out.println(selectedStudent.getId());
				System.out.println(myCourse.getId());

				StudentEnrollment toSend = new StudentEnrollment(
						selectedStudent.getId(), myCourse.getId());

				clientController.onlySendMessage(
						new SendMessage<StudentEnrollment>(toSend,
								CMD_INSERT + INSERT_UNENROLLMENT));
			} catch (IOException e1)
			{
				e1.printStackTrace();
			} catch (NullPointerException e2)
			{
				e2.printStackTrace();
			}

			showAllStudents(myCourse, enrollmentPage);
			createComposeEmailPage(myCourse);
		}
	}

	private class NewCourseButtonListener implements ActionListener
	{
		private HomePage homePage;

		public NewCourseButtonListener(HomePage page)
		{
			homePage = page;
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
						Course course = new Course(thisProfessor.getId(),
								courseName.getText(), false);
						clientController
								.onlySendMessage(new SendMessage<Course>(course,
										CMD_INSERT + INSERT_COURSE));

						createCoursePage(course);
						createEnrollmentPage(course);
						createAssignmentPage(course);
						createDiscussionPage(course);
						createSubmissionPage(course);
						createComposeEmailPage(course);
						createCourseItem(course, homePage);

					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
			}
		}
	}

	private class CourseActiveButtonListener implements ActionListener
	{
		private Course course;

		public CourseActiveButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				WButton activeButton = (WButton) e.getSource();
				if (course.getActive())
				{
					clientController.onlySendMessage(new SendMessage<Course>(
							course, CMD_MODIFY + MODIFY_COURSE_INACTIVE));
					activeButton.setText("ACTIVATE");
					activeButton.setBackground(BACKGROUND_COLOUR);
				} else
				{
					clientController.onlySendMessage(new SendMessage<Course>(
							course, CMD_MODIFY + MODIFY_COURSE_ACTIVE));
					activeButton.setText("DEACTIVATE");
					activeButton.setBackground(CONTRAST_COLOR);
				}

				course.setActive();
			} catch (IOException e1)
			{
				System.out.println("Unable to change the course active state");
				e1.printStackTrace();
			}

		}

	}

	private class ViewCoursePageListener implements ActionListener
	{
		/**
		 * The course identification number to use to communicate with the
		 * server.
		 */
		private int courseId;

		public ViewCoursePageListener(CourseItem courseItem)
		{
			this.courseId = courseItem.getId();
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(COURSE_PAGE + courseId);
		}

	}

	private class EnrollmentPageButtonListener implements ActionListener
	{
		private Course course;

		public EnrollmentPageButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(ENROLLMENT_PAGE + course.getId());
		}

	}

	private class AssignmentPageButtonListener implements ActionListener
	{
		private Course course;

		public AssignmentPageButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(ASSIGNMENT_PAGE + course.getId());
		}

	}

	private class SubmissionPageButtonListener implements ActionListener
	{
		private Course course;

		public SubmissionPageButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(SUBMISSION_PAGE + course.getId());
		}
	}

	private class ComposeEmailPageButtonListener implements ActionListener
	{
		private Course course;

		public ComposeEmailPageButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(COMPOSE_EMAIL_PAGE + course.getId());
		}
	}

	private class DiscussionButtonListener implements ActionListener
	{
		private Course course;

		public DiscussionButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(DISCUSSION_PAGE + course.getId());
		}
	}

	private class AssignmentActiveButtonListener implements ActionListener
	{
		private Assignment assignment;

		public AssignmentActiveButtonListener(Assignment course)
		{
			this.assignment = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.err.println("Assignment Activity");
			try
			{
				WButton activeButton = (WButton) e.getSource();
				if (assignment.getActive())
				{
					System.out.println("INACTIVE");
					clientController.onlySendMessage(
							new SendMessage<Assignment>(assignment,
									CMD_MODIFY + MODIFY_ASSIGNMENT_INACTIVE));
					activeButton.setText("ACTIVATE");
					activeButton.setBackground(BACKGROUND_COLOUR);
				} else
				{
					System.out.println("ACTIVE");

					clientController.onlySendMessage(
							new SendMessage<Assignment>(assignment,
									CMD_MODIFY + MODIFY_ASSIGNMENT_ACTIVE));
					activeButton.setText("DEACTIVATE");
					activeButton.setBackground(CONTRAST_COLOR);

				}
				assignment.setActive();
			} catch (IOException e1)
			{
				System.out.println("Unable to change the course active state");
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Listener for the upload button on the assignment page
	 *
	 */
	private class UploadButtonListener implements ActionListener
	{
		private Course course;
		private AssignmentPage assignmentPage;

		public UploadButtonListener(Course course, AssignmentPage assignPage)
		{
			this.course = course;
			this.assignmentPage = assignPage;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println(assignmentPage.getFile().getPath());
			String append[] = assignmentPage.getFile().getPath().split(File.pathSeparator);
			if (assignmentPage.getFile() != null)
			{
				Assignment myUpload = new Assignment(course.getId(),
						append[append.length - 1],
						assignmentPage.getUploadField().getText(), false,
						assignmentPage.getDate());
				try
				{
					clientController.onlySendMessage(
							new SendMessage<Assignment>(myUpload,
									CMD_INSERT + INSERT_ASSIGNMENT));

					long length = assignmentPage.getFile().length();
					byte[] content = new byte[(int) length]; // Converting Long
																// to Int
					FileInputStream fis = new FileInputStream(
							assignmentPage.getFile());

					BufferedInputStream bos = new BufferedInputStream(fis);
					bos.read(content, 0, (int) length);

					clientController.getObjectOut().writeObject(content);
					clientController.getObjectOut().flush();

					createAssignItem(course, assignmentPage, myUpload);
					// showAllAssignments(course, assignmentPage);

				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * Listener for the browse button on the assignment page
	 *
	 */
	private class BrowseButtonListener implements ActionListener
	{
		private AssignmentPage assignPage;

		public BrowseButtonListener(AssignmentPage assignPage)
		{
			this.assignPage = assignPage;
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
				assignPage.getUploadField().setText(selectedFile.getPath());

			} else
			{
				assignPage.setFile(null);
			}

		}

	}

	/**
	 * Listener for the send to all button on the compose email page
	 *
	 */
	private class SendToAllButtonListener implements ActionListener
	{
		private Course course;
		private ComposeEmailPage composePage;

		public SendToAllButtonListener(Course course,
				ComposeEmailPage composeEmailPage)
		{
			this.course = course;
			composePage = composeEmailPage;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				composePage.clearEmail();
				@SuppressWarnings("unchecked")
				Vector<Student> enrollList = (Vector<Student>) clientController
						.sendMessage(new SendMessage<Course>(course,
								CMD_RECEIVE + RECEIVE_ALL_ENROLLED_STUDENTS));

				for (int i = 0; i < enrollList.size(); i++)
				{
					String add = enrollList.get(i).getEmail();
					composePage.appendEmail(add);
				}

			} catch (IOException e1)
			{
				e1.printStackTrace();
			}

		}

	}

	/**
	 * Listener for the send button on the compose email page
	 *
	 */
	private class SendButtonListener implements ActionListener
	{
		private Course course;
		ComposeEmailPage emailPage;

		public SendButtonListener(Course course, ComposeEmailPage email)
		{
			this.course = course;
			ComposeEmailPage emailPage;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{


		}

	}

	/**
	 * Listener for the add to email button on the compose email page
	 *
	 */
	private class AddToEmailButtonListener implements ActionListener
	{
		private Course course;
		private ComposeEmailPage myEmailPage;

		public AddToEmailButtonListener(Course course, ComposeEmailPage myPage)
		{
			this.course = course;
			myEmailPage = myPage;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			String add = myEmailPage.getSelected();
			if (add != null)
			{
				myEmailPage.appendEmail(add);
			}

		}

	}

}
