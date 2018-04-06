package frontend.view;

import sharedobjects.Course;
import sharedobjects.Professor;
import sharedobjects.SendMessage;
import sharedobjects.Student;
import sharedobjects.StudentEnrollment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import frontend.controller.ClientController;
import frontend.view.pages.AssignmentPage;
import frontend.view.pages.HomePage;
import frontend.view.pages.CoursePage;
import frontend.view.pages.EnrollmentPage;
import frontend.view.pages.Page;
import frontend.view.pages.components.BoxList;
import frontend.view.pages.components.PageNavigator;
import frontend.view.pages.items.CourseItem;

/**
 * Class which handles the functionality of the Prrofessor GUI
 *
 */
public class ProfessorGUI extends PageNavigator
{
	/**
	 * The professor object which contains informaion needed for client
	 * server relations
	 */
	private Professor thisProfessor;

	/**
	 * Object needed to communicate effectively with the server
	 */
	private ClientController clientController;

	/** Constructor for this class, it pre-loads the pages
	 * @param socket
	 * @param toSet
	 */
	public ProfessorGUI(Socket socket, Professor toSet)
	{
		super();
		this.clientController = new ClientController();
		this.clientController.connectToServer(socket);
		thisProfessor = toSet;
		createHomePage();
	}

	@SuppressWarnings("unchecked")
	private void createHomePage()
	{
		@SuppressWarnings("unchecked")
		HomePage homePage = (HomePage) this.searchPage(HOME_PAGE);

		@SuppressWarnings("unchecked")
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

					createEnrollmentPage(course);
					createAssignmentPage(course);

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
		courseItem
				.setActiveCheckBoxListener(new ActiveCheckBoxListener(course));
		homePage.addToBoxList(courseItem);
	}

	private CoursePage createCoursePage(Course course)
	{
		CoursePage coursePage = new CoursePage(course);
		coursePage.setEnrollmentButtonListener(new EnrollmentButtonListener(course));
		coursePage.setAssignmentButtonListener(new AssignmentButtonListener(course));
		coursePage.setGradesButtonListener(new GradesButtonListener(course));
		this.addPage(coursePage, coursePage.getName());
		return coursePage;
	}

	private void createAssignmentPage(Course course)
	{
		AssignmentPage assignmentPage = new AssignmentPage(course);
		assignmentPage.setEnrollmentButtonListener(new EnrollmentButtonListener(course));
		assignmentPage.setAssignmentButtonListener(new AssignmentButtonListener(course));
		assignmentPage.setGradesButtonListener(new GradesButtonListener(course));
		this.addPage(assignmentPage, assignmentPage.getName());
	}
	
	private void createEnrollmentPage(Course course)
	{
		EnrollmentPage enrollmentPage = new EnrollmentPage(course);
		enrollmentPage.setEnrollmentButtonListener(new EnrollmentButtonListener(course));
		enrollmentPage.setAssignmentButtonListener(new AssignmentButtonListener(course));
		enrollmentPage.setGradesButtonListener(new GradesButtonListener(course));
		this.addPage(enrollmentPage, enrollmentPage.getName());
		enrollmentPage.setSearchButtonListener(
				new SearchButtonListener(enrollmentPage));
		enrollmentPage.setEnrollButtonListener(
				new EnrollButtonListener(enrollmentPage, course));
		enrollmentPage.setUnenrollButtonListener(
				new UnenrollButtonListener(enrollmentPage, course));

		showAllStudents(course, enrollmentPage);

	}

	private AssignmentPage createAssignmentPage()
	{
		return null;
	}

	public void showAllStudents(Course course, EnrollmentPage enrollmentPage)
	{
		try
		{
			Vector<Student> myList = (Vector<Student>) clientController.sendMessage(new SendMessage<>(null, "RECEIVE ALLSTUDENTS"));
			enrollmentPage.setStudentList(myList);

			Vector<Student> enrollList = (Vector<Student>) clientController.sendMessage(new SendMessage<Course>(course, "RECEIVE ALLENROLLED"));
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
			try {

			Vector<Student> searchResult = new Vector<Student>();
			if (enrollmentPage.isSearchById())
			{
				Student myResult = (Student) clientController.sendMessage(new SendMessage<>((int)Integer.parseInt(search), "RECEIVE STUDENTBYID"));
				searchResult.add(myResult);
			} else if (enrollmentPage.isSearchByLastName())
			{
				searchResult = (Vector<Student>)clientController.sendMessage(new SendMessage<>(search, "RECEIVE STUDENTBYLAST"));
			}

			enrollmentPage.setStudentList(searchResult);
		}catch (NumberFormatException e2) {
			System.out.println("Incorrect Login Value EnteredExitedHandler for search id");
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

		public EnrollButtonListener(EnrollmentPage enrollmentPage, Course course)
		{
			this.enrollmentPage = enrollmentPage;
			myCourse = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			Student selectedStudent = enrollmentPage.getSelectedStudent();
			StudentEnrollment toSend = new StudentEnrollment(selectedStudent.getId(), myCourse.getId());
			try
			{
				clientController.onlySendMessage(new SendMessage<StudentEnrollment>(toSend, "INSERT ENROLL"));
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

		public UnenrollButtonListener(EnrollmentPage enrollmentPage, Course course)
		{
			this.enrollmentPage = enrollmentPage;
			myCourse = course;
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Student selectedStudent = enrollmentPage.getSelectedStudent();
			StudentEnrollment toSend = new StudentEnrollment(selectedStudent.getId(), myCourse.getId());
			try
			{
				clientController.onlySendMessage(new SendMessage<StudentEnrollment>(toSend, "INSERT UNENROLL"));
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
						createCourseItem(course, homePage);

					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
			}
		}
	}

	private class ActiveCheckBoxListener implements ActionListener
	{
		private Course course;

		public ActiveCheckBoxListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				JCheckBox checkBox = (JCheckBox) e.getSource();
				if (!checkBox.isSelected() && course.getActive())
				{
					clientController.onlySendMessage(
							new SendMessage(course, "MODIFY COURSEINACTIVE"));
				} else
				{
					clientController.onlySendMessage(
							new SendMessage(course, "MODIFY COURSEACTIVE"));
				}
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
			showPage(ENROLLMENT_PAGE+course.getId());
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
			showPage(ASSIGNMENT_PAGE+course.getId());
		}
		
	}
	private class GradesButtonListener implements ActionListener
	{
		private Course course;
		public GradesButtonListener(Course course)
		{
			this.course = course;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage(GRADES_PAGE+course.getId());
		}
		
	}

}
