package frontend.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import frontend.controller.professor.listeners.NewCourseButtonListener;
import frontend.view.pages.GradePage;
import frontend.view.pages.HomePage;
import frontend.view.pages.components.PageNavigator;
import shared.interfaces.StudentCommands;
import shared.interfaces.UserCommands;
import shared.objects.Course;
import shared.objects.SendMessage;
import shared.objects.Student;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class StudentGUI extends PageNavigator implements StudentCommands
{
	private Student student;
	// Page??
	

	public StudentGUI(Socket socket, Student user)
	{
		super(socket);
		this.student = user;
		createHomePage();
	}


	@Override
	protected void createNewCourse(Course course, HomePage homePage)
	{
		super.createNewCourse(course, homePage);
		createGradesPage(course);
	}


	@Override
	protected void createAssignmentPage(Course course)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void createSubmissionPage(Course course)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void createComposeEmailPage(Course course)
	{
		// TODO Auto-generated method stub
		
	}
	
	private void createGradesPage(Course course)
	{
		GradePage gradePage = new GradePage(course);
		
		
		this.addPage(gradePage);
	}


}
