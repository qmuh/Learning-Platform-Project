package frontend.view.pages.components;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import frontend.controller.listeners.CoursePageNavigationButtonListener;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.interfaces.PageNames;
import shared.objects.Course;

public abstract class CourseNavigationBar extends JPanel
		implements WondrisInfo, PageNames
{
	private static final long serialVersionUID = 1L;

	protected WButton assignmentButton, submissionButton, discussionButton,
			composeEmailButton;

	public CourseNavigationBar()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		assignmentButton = new WButton("Assignments");
		submissionButton = new WButton("Submissions");
		discussionButton = new WButton("Discussion");
		composeEmailButton = new WButton("Compose Email");
	}

	public void createListeners(Course course, PageNavigator pageNavigator)
	{
		assignmentButton.addActionListener(
				new CoursePageNavigationButtonListener(course, ASSIGNMENT_PAGE,
						pageNavigator));

		submissionButton.addActionListener(
				new CoursePageNavigationButtonListener(course, SUBMISSION_PAGE,
						pageNavigator));

		discussionButton.addActionListener(
				new CoursePageNavigationButtonListener(course, DISCUSSION_PAGE,
						pageNavigator));

		composeEmailButton.addActionListener(
				new CoursePageNavigationButtonListener(course,
						COMPOSE_EMAIL_PAGE, pageNavigator));
	}

}
