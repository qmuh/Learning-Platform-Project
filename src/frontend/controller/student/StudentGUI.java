package frontend.controller.student;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import frontend.view.pages.AssignmentPage;
import frontend.view.pages.ComposeEmailPage;
import frontend.view.pages.CoursePage;
import frontend.view.pages.DiscussionPage;
import frontend.view.pages.GradePage;
import frontend.view.pages.HomePage;
import frontend.view.pages.SubmissionPage;
import frontend.view.pages.components.CourseNavigationBarStudent;
import frontend.view.pages.components.PageNavigator;
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
		AssignmentPage assignmentPage = new AssignmentPage(course);
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
		ComposeEmailPage composeEmailPage = new ComposeEmailPage(course);
		completeCoursePage(composeEmailPage, course);
	}

	@SuppressWarnings("unchecked")
	private void createGradesPage(Course course)
	{
		GradePage gradePage = new GradePage(course);

		SendMessage<Course> gradesReqeuest = new SendMessage<Course>(course, CMD_RECEIVE + RECEIVE_GRADES);
		SendMessage<Course> assignmentsRequest = new SendMessage<Course>(course, CMD_RECEIVE + RECEIVE_ASSIGNMENTS);
		
		try
		{
			
			Vector<Grade> grades = (Vector<Grade>) this.client.sendMessage(gradesReqeuest);
			Vector<Assignment> assignments = (Vector<Assignment>) this.client.sendMessage(assignmentsRequest);
			
			for (Grade grade : grades)
			{
				for (int i = 0; i < assignments.size(); i++)
				{
					Assignment assignment = assignments.elementAt(i);
					if (grade.getAssignID() == assignment.getId())
					{
						gradePage.addToBoxList(new GradeItem(assignment.getTitle(), grade));
					}
				}
			}
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		completeCoursePage(gradePage, course);
	}

	private void completeCoursePage(CoursePage<?, ?> genericCoursePage, Course course)
	{
		genericCoursePage.setCourseNavigationBar(new CourseNavigationBarStudent());
		genericCoursePage.createSidebarListeners(course, this);
		this.addPage(genericCoursePage);
	}

	@Override
	protected void createDiscussionPage(Course course)
	{
		DiscussionPage discussionPage = new DiscussionPage(course);
		completeCoursePage(discussionPage, course);
	}

}
