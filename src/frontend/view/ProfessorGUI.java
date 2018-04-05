package frontend.view;

import sharedobjects.Course;
import sharedobjects.Professor;
import sharedobjects.SendMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import frontend.components.BoxList;
import frontend.components.PageNavigator;
import frontend.controller.ClientController;
import frontend.view.pages.AssignmentPage;
import frontend.view.pages.CoursePage;
import frontend.view.pages.HomePage;
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

	
	private void createHomePage()
	{
		HomePage homePage = (HomePage) this.searchPage(HOME_PAGE);
	}

	private void createCoursePage()
	{
		@SuppressWarnings("unchecked")
		Page<CourseItem, Course> coursePage = (Page<CourseItem, Course>) this.searchPage(COURSE_PAGE);
		@SuppressWarnings("unchecked")
		SendMessage message = new SendMessage(null, "RECEIVE COURSES");
		BoxList<CourseItem> boxList = new BoxList<CourseItem>();
		Vector<Course> courses = new Vector<Course>();

		try
		{
			courses = (Vector<Course>) this.clientController.sendMessage(message);

			if (courses != null)
			{
				for (int i = 0; i < courses.size(); i++)
				{
					boxList.addItem(new CourseItem(courses.elementAt(i)));
				}
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		coursePage.setBoxList(boxList);
		coursePage.setCoursesButtonListener(new NewCourseButtonListener());
		coursePage.displayPage();
	}
	
	private class NewCourseButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			
			JTextField courseName = new JTextField(30);
			Object[] toDisplay = { "Enter the preferred Course Name", courseName};
			
			int response =JOptionPane.showConfirmDialog(null, toDisplay, "Insert node information", JOptionPane.OK_CANCEL_OPTION);
			
			if(response == JOptionPane.OK_OPTION)
			{
				if(courseName.getText().length() > 40)
				{
					JOptionPane.showMessageDialog(null,"Course Name");
				} else
					try
					{
						System.out.println(thisProfessor.getId());
						System.out.println("IM HERE");
						clientController.onlySendMessage(new SendMessage<Course>(new Course(thisProfessor.getId(), courseName.getText(), false) 
								, "INSERT COURSE"));
					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
			}
			
		}
		
	}
	
	private AssignmentPage createAssignmentPage()
	{
		return null;
	}
	
	

}
