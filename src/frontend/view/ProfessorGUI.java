package frontend.view;

import sharedobjects.Course;
import sharedobjects.Professor;
import sharedobjects.SendMessage;
import sharedobjects.Student;

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

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.xml.internal.bind.v2.runtime.Name;

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

public class ProfessorGUI extends PageNavigator
{
	private Professor thisProfessor;
	private ClientController clientController;

	public ProfessorGUI(Socket socket, Professor toSet)
	{
		super();
		this.clientController = new ClientController();
		this.clientController.connectToServer(socket);
		thisProfessor = toSet;
		createHomePage();
	}

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
		this.addPage(coursePage, coursePage.getName());
		return coursePage;
	}

	private void createEnrollmentPage(Course course)
	{
		EnrollmentPage enrollmentPage = new EnrollmentPage(course);
		this.addPage(enrollmentPage, enrollmentPage.getName());
		enrollmentPage.setSearchButtonListener(
				new SearchButtonListener(enrollmentPage));
		enrollmentPage.setEnrollButtonListener(
				new EnrollButtonListener(enrollmentPage));
		enrollmentPage.setUnenrollButtonListener(
				new UnenrollButtonListener(enrollmentPage));
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
			if (enrollmentPage.isSearchById())
			{
				// search by id
			} else if (enrollmentPage.isSearchByLastName())
			{
				// search by last name
			}
			// TODO: SEARCH
		}
	}

	private class EnrollButtonListener implements ActionListener
	{
		private EnrollmentPage enrollmentPage;

		public EnrollButtonListener(EnrollmentPage enrollmentPage)
		{
			this.enrollmentPage = enrollmentPage;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			Student selectedStudent = enrollmentPage.getSelectedStudent();
			// enroll this student. TODO:
		}
	}

	private class UnenrollButtonListener implements ActionListener
	{
		private EnrollmentPage enrollmentPage;

		public UnenrollButtonListener(EnrollmentPage enrollmentPage)
		{
			this.enrollmentPage = enrollmentPage;
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Student selectedStudent = enrollmentPage.getSelectedStudent();
			// unenroll this student. TODO:
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

	private AssignmentPage createAssignmentPage()
	{
		return null;
	}

}
