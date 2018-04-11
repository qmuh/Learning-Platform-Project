package frontend.controller;

import java.net.Socket;

import frontend.view.pages.AssignmentPage;
import frontend.view.pages.ComposeEmailPage;
import frontend.view.pages.CoursePage;
import frontend.view.pages.DiscussionPage;
import frontend.view.pages.GradePage;
import frontend.view.pages.HomePage;
import frontend.view.pages.SubmissionPage;
import frontend.view.pages.components.CourseNavigationBarStudent;
import frontend.view.pages.components.PageNavigator;
import shared.interfaces.StudentCommands;
import shared.objects.Course;
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
	protected void createCoursePage(Course course)
	{
		CoursePage coursePage = new CoursePage<>(course);
		coursePage.setCourseNavigationBar(new CourseNavigationBarStudent());
		coursePage.createSidebarListeners(course, this);
		this.addPage(coursePage);
	}


	@Override
	protected void createAssignmentPage(Course course)
	{
		AssignmentPage assignmentPage = new AssignmentPage(course);
		assignmentPage.setCourseNavigationBar(new CourseNavigationBarStudent());
		assignmentPage.createSidebarListeners(course, this);
		this.addPage(assignmentPage);
	}


	@Override
	protected void createSubmissionPage(Course course)
	{
		SubmissionPage submissionPage = new SubmissionPage(course);
		submissionPage.setCourseNavigationBar(new CourseNavigationBarStudent());
		submissionPage.createSidebarListeners(course, this);
		this.addPage(submissionPage);
	}


	@Override
	protected void createComposeEmailPage(Course course)
	{
		ComposeEmailPage composeEmailPage = new ComposeEmailPage(course);
		composeEmailPage.setCourseNavigationBar(new CourseNavigationBarStudent());
		composeEmailPage.createSidebarListeners(course, this);
		this.addPage(composeEmailPage);
	}
	
	private void createGradesPage(Course course)
	{
		GradePage gradePage = new GradePage(course);
		
		
		this.addPage(gradePage);
	}


	@Override
	protected void createDiscussionPage(Course course)
	{
		DiscussionPage discussionPage = new DiscussionPage(course);
		discussionPage.setCourseNavigationBar(new CourseNavigationBarStudent());
		discussionPage.createSidebarListeners(course, this);
		this.addPage(discussionPage);
	}


}
