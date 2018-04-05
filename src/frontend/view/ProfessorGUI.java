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
import frontend.view.pages.items.CourseItem;

public class ProfessorGUI extends PageNavigator
{
	private Professor thisProfessor;
	private ClientController clientController;
	
	public ProfessorGUI(Socket socket) {
		super();
		this.clientController = new ClientController();
		this.clientController.connectToServer(socket);
		
		HomePage homePage = createHomePage();
		CoursePage coursePage = createCoursePage();
		AssignmentPage assignmentPage = createAssignmentPage();
		
		
		pages.put(HOME_PAGE, homePage);
		pages.put(COURSE_PAGE, coursePage);
		pages.put(ASSIGNMENT_PAGE, assignmentPage);
		
		
		this.add(new HomePage());
	}
	
	private HomePage createHomePage()
	{
		HomePage homePage = new HomePage();
		homePage.setHomeButtonListener(new HomeButtonListener());
		homePage.setCoursesButtonListener(new CourseButtonListener());
		return homePage;
	}
	
	private AssignmentPage createAssignmentPage()
	{
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private CoursePage createCoursePage()
	{
		SendMessage message = new SendMessage(null, "RECEIVE COURSES");
		BoxList<CourseItem> boxList = new BoxList<CourseItem>();
		Vector<Course> courses = new Vector<Course>();
		
		try
		{
			courses = (Vector<Course>) this.clientController.sendMessage(message);
			
			for (int i = 0; i < courses.size(); i++)
			{
				boxList.addItem(new CourseItem(courses.elementAt(i)));
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return new CoursePage(boxList);
	}
	
	public class HomeButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage("HOME");
		}
	}
	
	public class CourseButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			showPage("COURSES");
		}
	}


	
}
