package frontend.controller.student;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import frontend.view.pages.AssignmentPage;
import frontend.view.pages.AssignmentPageStudent;
import frontend.view.pages.ComposeEmailPage;
import frontend.view.pages.ComposeEmailPageStudent;
import frontend.view.pages.CoursePage;
import frontend.view.pages.DiscussionPage;
import frontend.view.pages.GradePage;
import frontend.view.pages.HomePage;
import frontend.view.pages.SubmissionPage;
import frontend.view.pages.components.CourseNavigationBarStudent;
import frontend.view.pages.components.PageNavigator;
import frontend.view.pages.items.CourseItem;
import frontend.view.pages.items.CourseItemStudent;
import frontend.view.pages.items.GradeItem;
import shared.interfaces.StudentCommands;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.Grade;
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
	protected void createCoursePage(Course course)
	{
		CoursePage coursePage = new CoursePage<>(course);
		completeCoursePage(coursePage, course);
	}

	@Override
	protected void createAssignmentPage(Course course)
	{
		AssignmentPageStudent assignmentPage = new AssignmentPageStudent(
				course);
		completeCoursePage(assignmentPage, course);
	}

	@Override
	protected void createSubmissionPage(Course course)
	{
		SubmissionPage submissionPage = new SubmissionPage(course);
		completeCoursePage(submissionPage, course);
	}

	@Override
	protected void createComposeEmailPage(Course course)
	{
		ComposeEmailPageStudent composeEmailPage = new ComposeEmailPageStudent(course);
		SendMessage<Course> requestProfessor = new SendMessage<>(CMD_RECEIVE + RECEIVE_PROFESSOR);
		completeCoursePage(composeEmailPage, course);
	}

	@SuppressWarnings("unchecked")
	private void createGradesPage(Course course)
	{
		GradePage gradePage = new GradePage(course);

		SendMessage<Course> gradesReqeuest = new SendMessage<Course>(course,
				CMD_RECEIVE + RECEIVE_GRADES);
		SendMessage<Course> assignmentsRequest = new SendMessage<Course>(course,
				CMD_RECEIVE + RECEIVE_ASSIGNMENTS);

		try
		{

			Vector<Grade> grades = (Vector<Grade>) this.client
					.sendMessage(gradesReqeuest);
			
			Vector<Assignment> assignments = (Vector<Assignment>) this.client
					.sendMessage(assignmentsRequest);

			for (int j = 0; j < grades.size() ; j++)
			{
				for (int i = 0; i < assignments.size(); i++)
				{
					Assignment assignment = assignments.elementAt(i);
					if (grades.elementAt(j).getAssignID() == assignment.getId())
					{
						gradePage.addToBoxList(
								new GradeItem(assignment.getTitle(), grades.elementAt(j)));
					}
				}
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		completeCoursePage(gradePage, course);
	}

	private void completeCoursePage(CoursePage<?, ?> genericCoursePage,
			Course course)
	{
		genericCoursePage
				.setCourseNavigationBar(new CourseNavigationBarStudent());
		genericCoursePage.createSidebarListeners(course, this);
		this.addPage(genericCoursePage);
	}

	@Override
	protected void createDiscussionPage(Course course)
	{
		DiscussionPage discussionPage = new DiscussionPage(course);
		completeCoursePage(discussionPage, course);
	}
	
	@Override
	protected void createCourseItem(Course course, HomePage homePage)
	{
		CourseItemStudent courseItem = new CourseItemStudent(course);
		courseItem.getViewButton().addActionListener(new ViewCoursePageListener(course));
		homePage.addToBoxList(courseItem);
		
	}

}
