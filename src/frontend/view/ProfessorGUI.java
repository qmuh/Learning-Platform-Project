package frontend.view;

import sharedobjects.Course;
import sharedobjects.Professor;
import sharedobjects.SendMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;

import frontend.components.BoxList;
import frontend.components.PageNavigator;
import frontend.controller.ClientController;
import frontend.view.pages.AssignmentPage;
import frontend.view.pages.HomePage;
import frontend.view.pages.CoursePage;
import frontend.view.pages.Page;
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
		createCoursePage();
	}

	private void createCoursePage()
	{
		CoursePage coursePage = (CoursePage) this.searchPage(COURSE_PAGE);
	}

	private void createHomePage()
	{
		@SuppressWarnings("unchecked")
		HomePage homePage = (HomePage) this.searchPage(HOME_PAGE);

		@SuppressWarnings("unchecked")
		SendMessage message = new SendMessage(null, "RECEIVE COURSES");
		BoxList<CourseItem> boxList = new BoxList<CourseItem>();
		Vector<Course> coursesList = new Vector<Course>();

		try
		{
			coursesList = (Vector<Course>) this.clientController.sendMessage(message);

			if (coursesList != null)
			{
				for (int i = 0; i < coursesList.size(); i++)
				{
					Course course = coursesList.elementAt(i);
					CoursePage coursePage = new CoursePage(course);
					this.addPage(coursePage, coursePage.getName());
					
					CourseItem courseItem = new CourseItem(course);
					courseItem.setViewButtonListener(new ViewCoursePageListener(courseItem));
					boxList.addItem(courseItem);
					
					System.out.println("Course name is: " + coursesList.get(i).getName());
				}
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}
		homePage.setBoxList(boxList);
		homePage.setNewCourseListener(new NewCourseButtonListener(homePage));
		homePage.displayPage();
		// TODO: Set listeners for all view buttons
	}

	private class NewCourseButtonListener implements ActionListener
	{
		private HomePage thisPage;

		public NewCourseButtonListener(HomePage page)
		{
			thisPage = page;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{

			JTextField courseName = new JTextField(30);
			Object[] toDisplay =
			{ "Enter the preferred Course Name", courseName };

			int response = JOptionPane.showConfirmDialog(null, toDisplay, "Insert node information",
					JOptionPane.OK_CANCEL_OPTION);

			if (response == JOptionPane.OK_OPTION)
			{
				if (courseName.getText().length() > 40)
				{
					JOptionPane.showMessageDialog(null, "Course Name");
				} else
					try
					{
						Course course = new Course(thisProfessor.getId(), courseName.getText(), false);
						clientController.onlySendMessage(new SendMessage<Course>(course, "INSERT COURSE"));
						thisPage.addToBoxList(new CourseItem(course));

					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
			}

		}

	}
	
	private class ViewCoursePageListener implements ActionListener
	{
		/**
		 * The course identification number to use to communicate with the server.
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
