package frontend.view;

import sharedobjects.Course;
import sharedobjects.Professor;
import sharedobjects.SendMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

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
	
	public ProfessorGUI(Socket socket) {
		super();
		this.clientController = new ClientController();
		this.clientController.connectToServer(socket);
		
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
			
			for (int i = 0; i < courses.size(); i++)
			{
				boxList.addItem(new CourseItem(courses.elementAt(i)));
				System.out.println("This course is " + courses.get(i).getName());
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		coursePage.setBoxList(boxList);
	}
	
	private AssignmentPage createAssignmentPage()
	{
		return null;
	}
	
	


	
}
