package frontend.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import frontend.controller.Client;
import frontend.view.pages.AssignmentPage;
import frontend.view.pages.ComposeEmailPage;
import frontend.view.pages.HomePage;
import frontend.view.pages.MyEmailsPage;
import frontend.view.pages.SubmissionPage;
import frontend.view.pages.CoursePage;
import frontend.view.pages.EnrollmentPage;
import frontend.view.pages.components.PageNavigator;
import frontend.view.pages.items.AssignItem;
import frontend.view.pages.items.CourseItem;
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
public class ProfessorGUI extends PageNavigator
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

		SendMessage message = new SendMessage(null, "RECEIVE COURSES");
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

					CoursePage coursePage = createCoursePage(course);
					createCourseItem(course, homePage);

					createAssignmentPage(course);
					createSubmissionPage(course);
					createEnrollmentPage(course);
					createComposeEmailPage(course);
					createMyEmailsPage(course);
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
		coursePage.setEnrollmentButtonListener(
				new EnrollmentButtonListener(course));
		coursePage.setAssignmentButtonListener(
				new AssignmentButtonListener(course));
		coursePage.setSubmissionButtonListener(
				new SubmissionButtonListener(course));
		coursePage.setComposeEmailButtonListener(
				new ComposeEmailButtonListener(course));
		coursePage.setMyEmailButtonListener(
				new MyEmailsButtonListener(course));
		this.addPage(coursePage, coursePage.getName());
		return coursePage;
	}

	private void createAssignmentPage(Course course)
	{
		AssignmentPage assignmentPage = new AssignmentPage(course);
		assignmentPage.setEnrollmentButtonListener(
				new EnrollmentButtonListener(course));
		assignmentPage.setSubmissionButtonListener(
				new SubmissionButtonListener(course));
		assignmentPage.setComposeEmailButtonListener(
				new ComposeEmailButtonListener(course));
		assignmentPage.setMyEmailButtonListener(
				new MyEmailsButtonListener(course));
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
		composeEmailPage.setEnrollmentButtonListener(
				new EnrollmentButtonListener(course));
		composeEmailPage.setAssignmentButtonListener(
				new AssignmentButtonListener(course));
		composeEmailPage.setSubmissionButtonListener(
				new SubmissionButtonListener(course));
		composeEmailPage.setMyEmailButtonListener(
				new MyEmailsButtonListener(course));
		this.addPage(composeEmailPage, composeEmailPage.getName());
		composeEmailPage.setSendToAllButtonListener(
				new SendToAllButtonListener(course));
		composeEmailPage.setSendButtonListener(
				new SendButtonListener(course));
		composeEmailPage.setAddToEmailButtonListener(
				new AddToEmailButtonListener(course));
	}
	
	private void createSubmissionPage(Course course)
	{
		SubmissionPage submissionPage = new SubmissionPage(course);
		submissionPage.setEnrollmentButtonListener(
				new EnrollmentButtonListener(course));
		submissionPage.setAssignmentButtonListener(
				new AssignmentButtonListener(course));
		submissionPage.setComposeEmailButtonListener(
				new ComposeEmailButtonListener(course));
		submissionPage.setMyEmailButtonListener(
				new MyEmailsButtonListener(course));
		this.addPage(submissionPage, submissionPage.getName());
		// TODO: set up submission page listeners
	}
	
	private void createMyEmailsPage(Course course)
	{
		MyEmailsPage myEmailsPage = new MyEmailsPage(course);
		myEmailsPage.setEnrollmentButtonListener(
				new EnrollmentButtonListener(course));
		myEmailsPage.setAssignmentButtonListener(
				new AssignmentButtonListener(course));
		myEmailsPage.setSubmissionButtonListener(
				new SubmissionButtonListener(course));
		myEmailsPage.setComposeEmailButtonListener(
				new ComposeEmailButtonListener(course));
		this.addPage(myEmailsPage, myEmailsPage.getName());
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
							"RECEIVE ALLASSIGNMENTS"));

			for (Assignment assignment : myList)
			{
				createAssignItem(course, assignmentPage, assignment);
			}

			//assignmentPage.setAssignmentVector(myList);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void createAssignItem(Course course, AssignmentPage assignmentPage,
			Assignment assignment)
	{
		AssignItem assignItem = new AssignItem(assignment);
		assignItem.setActiveCheckboxListener(
				new AssignmentActiveCheckBoxListener(assignment));
		assignmentPage.addToBoxList(assignItem);
		assignmentPage.displayPage();
	}

	private void createEnrollmentPage(Course course)
	{
		EnrollmentPage enrollmentPage = new EnrollmentPage(course);
		enrollmentPage.setAssignmentButtonListener(
				new AssignmentButtonListener(course));
		enrollmentPage.setSubmissionButtonListener(
				new SubmissionButtonListener(course));
		enrollmentPage.setComposeEmailButtonListener(
				new ComposeEmailButtonListener(course));
		enrollmentPage.setMyEmailButtonListener(
				new MyEmailsButtonListener(course));
		this.addPage(enrollmentPage, enrollmentPage.getName());
		enrollmentPage.setSearchButtonListener(
				new SearchButtonListener(enrollmentPage));
		enrollmentPage.setEnrollButtonListener(
				new EnrollButtonListener(enrollmentPage, course));
		enrollmentPage.setUnenrollButtonListener(
				new UnenrollButtonListener(enrollmentPage, course));

		showAllStudents(course, enrollmentPage);
	}

	@SuppressWarnings("unchecked")
	public void showAllStudents(Course course, EnrollmentPage enrollmentPage)
	{
		try
		{
			Vector<Student> myList = (Vector<Student>) clientController
					.sendMessage(
							new SendMessage<>(null, "RECEIVE ALLSTUDENTS"));
			enrollmentPage.setStudentList(myList);

			Vector<Student> enrollList = (Vector<Student>) clientController
					.sendMessage(new SendMessage<Course>(course,
							"RECEIVE ALLENROLLED"));
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
									"RECEIVE STUDENTBYID"));
					searchResult.add(myResult);
				} else if (enrollmentPage.isSearchByLastName())
				{
					searchResult = (Vector<Student>) clientController
							.sendMessage(new SendMessage<>(search,
									"RECEIVE STUDENTBYLAST"));
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

	private class EnrollButtonListener implements ActionListener
	{
		private EnrollmentPage enrollmentPage;
		private Course myCourse;

		public EnrollButtonListener(EnrollmentPage enrollmentPage,
				Course course)
		{
			this.enrollmentPage = enrollmentPage;
			myCourse = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			Student selectedStudent = enrollmentPage.getSelectedStudent();
			StudentEnrollment toSend = new StudentEnrollment(
					selectedStudent.getId(), myCourse.getId());
			try
			{
				clientController.onlySendMessage(
						new SendMessage<StudentEnrollment>(toSend,
								"INSERT ENROLL"));
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}

			showAllStudents(myCourse, enrollmentPage);
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
			Student selectedStudent = enrollmentPage.getSelectedStudent();
			StudentEnrollment toSend = new StudentEnrollment(
					selectedStudent.getId(), myCourse.getId());
			try
			{
				clientController.onlySendMessage(
						new SendMessage<StudentEnrollment>(toSend,
								"INSERT UNENROLL"));
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}

			showAllStudents(myCourse, enrollmentPage);

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
					JOptionPane.showMessageDialog(null, "Course Name");
				} else
					try
					{
						Course course = new Course(thisProfessor.getId(),
								courseName.getText(), false);
						clientController
								.onlySendMessage(new SendMessage<Course>(course,
										"INSERT COURSE"));

						createCoursePage(course);
						createEnrollmentPage(course);
						createAssignmentPage(course);
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
				JButton activeButton = (JButton)e.getSource();
				if (course.getActive())
				{
					clientController.onlySendMessage(
							new SendMessage(course, "MODIFY COURSEINACTIVE"));
					activeButton.setText("ACTIVATE");
					activeButton.setBackground(BACKGROUND_COLOUR);
				} else
				{
					clientController.onlySendMessage(
							new SendMessage(course, "MODIFY COURSEACTIVE"));
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

	private class EnrollmentButtonListener implements ActionListener
	{
		private Course course;

		public EnrollmentButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(ENROLLMENT_PAGE + course.getId());
		}

	}

	private class AssignmentButtonListener implements ActionListener
	{
		private Course course;

		public AssignmentButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(ASSIGNMENT_PAGE + course.getId());
		}

	}

	private class SubmissionButtonListener implements ActionListener
	{
		private Course course;

		public SubmissionButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(SUBMISSION_PAGE + course.getId());
		}
	}
	
	private class ComposeEmailButtonListener implements ActionListener
	{
		private Course course;

		public ComposeEmailButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(COMPOSE_EMAIL_PAGE + course.getId());
		}
	}
	
	private class MyEmailsButtonListener implements ActionListener
	{
		private Course course;

		public MyEmailsButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(MY_EMAIL_PAGE + course.getId());
		}
	}

	private class AssignmentActiveCheckBoxListener implements ActionListener
	{
		private Assignment assignment;

		public AssignmentActiveCheckBoxListener(Assignment course)
		{
			this.assignment = course;
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.err.println("Assignment Activity");
			try
			{
				JCheckBox checkBox = (JCheckBox) e.getSource();
				if (!checkBox.isSelected() && assignment.getActive())
				{
					System.out.println("INACTIVE");
					clientController.onlySendMessage(new SendMessage(assignment,
							"MODIFY ASSIGNINACTIVE"));
				} else
				{
					System.out.println("ACTIVE");

					clientController.onlySendMessage(
							new SendMessage(assignment, "MODIFY ASSIGNACTIVE"));
				}
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
			String append[] = assignmentPage.getFile().getPath().split("/");
			if (assignmentPage.getFile() != null)
			{
				Assignment myUpload = new Assignment(course.getId(),
						append[append.length -1],
						assignmentPage.getUploadField().getText(), false,
						assignmentPage.getDate().toString());
				try
				{
					clientController.onlySendMessage(
							new SendMessage<Assignment>(myUpload,
									"INSERT ASSIGNMENT"));

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
//					showAllAssignments(course, assignmentPage);

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
			}

			else
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
		public SendToAllButtonListener(Course course)
		{
			this.course = course;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * Listener for the send button on the compose email page
	 *
	 */
	private class SendButtonListener implements ActionListener
	{
		private Course course;
		public SendButtonListener(Course course)
		{
			this.course = course;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * Listener for the add to email button on the compose email page
	 *
	 */
	private class AddToEmailButtonListener implements ActionListener
	{
		private Course course;
		public AddToEmailButtonListener(Course course)
		{
			this.course = course;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}

}
